import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Book> books =
            new ArrayList<>();

    private final List<Member> members =
            new ArrayList<>();

    private final List<IssueRecord> issueRecords =
            new ArrayList<>();


    // =========================
    // BOOK METHODS
    // =========================

    public boolean addBook(Book book) {

        if (bookExists(book.getBookId())) {

            System.out.println(
                    "Book ID already exists."
            );

            return false;
        }

        books.add(book);

        System.out.println(
                "Book added successfully."
        );

        return true;
    }


    public boolean bookExists(int id) {

        return findBook(id) != null;
    }


    private Book findBook(int id) {

        for (Book book : books) {

            if (book.getBookId() == id) {
                return book;
            }
        }

        return null;
    }


    public boolean updateBook(
            int id,
            String title,
            String author) {

        Book book = findBook(id);

        if (book == null) {

            System.out.println(
                    "Book not found."
            );

            return false;
        }

        book.setTitle(title);
        book.setAuthor(author);

        System.out.println(
                "Book updated successfully."
        );

        return true;
    }


    public boolean deleteBook(int id) {

        Book book = findBook(id);

        if (book == null) {

            System.out.println(
                    "Book not found."
            );

            return false;
        }

        if (!book.isAvailable()) {

            System.out.println(
                    "Cannot delete an issued book."
            );

            return false;
        }

        books.remove(book);

        System.out.println(
                "Book deleted successfully."
        );

        return true;
    }


    public void displayAllBooks() {

        if (books.isEmpty()) {

            System.out.println(
                    "No books available."
            );

            return;
        }

        for (Book book : books) {

            book.displayBook();
        }
    }


    public void searchBook(int id) {

        Book book = findBook(id);

        if (book == null) {

            System.out.println(
                    "Book not found."
            );

        } else {

            System.out.println(
                    "Book found:"
            );

            book.displayBook();
        }
    }


    // =========================
    // MEMBER METHODS
    // =========================

    public boolean addMember(Member member) {

        if (memberExists(member.getMemberId())) {

            System.out.println(
                    "Member ID already exists."
            );

            return false;
        }

        members.add(member);

        System.out.println(
                "Member added successfully."
        );

        return true;
    }


    public boolean memberExists(int id) {

        return findMember(id) != null;
    }


    private Member findMember(int id) {

        for (Member member : members) {

            if (member.getMemberId() == id) {
                return member;
            }
        }

        return null;
    }


    public boolean updateMember(
            int id,
            String name,
            String phone,
            String email) {

        Member member = findMember(id);

        if (member == null) {

            System.out.println(
                    "Member not found."
            );

            return false;
        }

        member.setName(name);
        member.setPhone(phone);
        member.setEmail(email);

        System.out.println(
                "Member updated successfully."
        );

        return true;
    }


    public boolean deleteMember(int id) {

        Member member = findMember(id);

        if (member == null) {

            System.out.println(
                    "Member not found."
            );

            return false;
        }

        for (IssueRecord record : issueRecords) {

            if (record.getMemberId() == id
                    && !record.isReturned()) {

                System.out.println(
                        "Cannot delete a member with an issued book."
                );

                return false;
            }
        }

        members.remove(member);

        System.out.println(
                "Member deleted successfully."
        );

        return true;
    }


    public void displayAllMembers() {

        if (members.isEmpty()) {

            System.out.println(
                    "No members registered."
            );

            return;
        }

        for (Member member : members) {

            member.displayMember();
        }
    }


    // =========================
    // ISSUE BOOK
    // =========================

    public boolean issueBook(
            int bookId,
            int memberId,
            String issueDate,
            String dueDate) {

        Book book = findBook(bookId);

        if (book == null) {

            System.out.println(
                    "Book not found."
            );

            return false;
        }

        if (!book.isAvailable()) {

            System.out.println(
                    "Book is already issued."
            );

            return false;
        }

        if (!memberExists(memberId)) {

            System.out.println(
                    "Member not found."
            );

            return false;
        }

        if (issueDate.isBlank()
                || dueDate.isBlank()) {

            System.out.println(
                    "Dates cannot be empty."
            );

            return false;
        }

        IssueRecord record =
                new IssueRecord(
                        bookId,
                        memberId,
                        issueDate,
                        dueDate
                );

        issueRecords.add(record);

        book.setAvailable(false);

        System.out.println(
                "Book issued successfully."
        );

        return true;
    }


    // =========================
    // RETURN BOOK
    // =========================

    public boolean returnBook(
            int bookId,
            String returnDate) {

        Book book = findBook(bookId);

        if (book == null) {

            System.out.println(
                    "Book not found."
            );

            return false;
        }

        if (book.isAvailable()) {

            System.out.println(
                    "Book is already available."
            );

            return false;
        }

        if (returnDate.isBlank()) {

            System.out.println(
                    "Return date cannot be empty."
            );

            return false;
        }

        for (IssueRecord record : issueRecords) {

            if (record.getBookId() == bookId
                    && !record.isReturned()) {

                record.setReturned(true);
                record.setReturnDate(returnDate);

                book.setAvailable(true);

                System.out.println(
                        "Book returned successfully."
                );

                return true;
            }
        }

        System.out.println(
                "Active issue record not found."
        );

        return false;
    }


    // =========================
    // CALCULATE FINE
    // =========================

    public void calculateFine(
            int bookId,
            String returnDate) {

        IssueRecord activeRecord = null;

        for (IssueRecord record : issueRecords) {

            if (record.getBookId() == bookId
                    && !record.isReturned()) {

                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {

            System.out.println(
                    "No active issue record found for this book."
            );

            return;
        }

        try {

            LocalDate dueDate =
                    parseDate(
                            activeRecord.getDueDate()
                    );

            LocalDate actualReturnDate =
                    parseDate(returnDate);


            long daysLate =
                    actualReturnDate.toEpochDay()
                    - dueDate.toEpochDay();


            long lateDays =
                    Math.max(0, daysLate);


            long fine =
                    lateDays * 10;


            System.out.println(
                    "Due Date        : "
                    + dueDate
            );

            System.out.println(
                    "Return Date     : "
                    + actualReturnDate
            );

            System.out.println(
                    "Days Late       : "
                    + lateDays
            );

            System.out.println(
                    "Fine (Rs. 10/day): Rs. "
                    + fine
            );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            System.out.println(
                    "Use DD/MM/YYYY or YYYY-MM-DD."
            );
        }
    }


    private LocalDate parseDate(String date) {

        DateTimeFormatter[] formats = {

                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy"
                ),

                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd"
                )
        };


        for (DateTimeFormatter format : formats) {

            try {

                return LocalDate.parse(
                        date,
                        format
                );

            } catch (Exception ignored) {
            }
        }

        throw new IllegalArgumentException(
                "Invalid date"
        );
    }


    // =========================
    // AVAILABLE BOOKS
    // =========================

    public void displayAvailableBooks() {

        boolean found = false;

        for (Book book : books) {

            if (book.isAvailable()) {

                book.displayBook();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No available books."
            );
        }
    }


    // =========================
    // ISSUED BOOKS
    // =========================

    public void displayIssuedBooks() {

        boolean found = false;

        for (Book book : books) {

            if (!book.isAvailable()) {

                book.displayBook();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No issued books."
            );
        }
    }


    // =========================
    // TRANSACTION HISTORY
    // =========================

    public void displayAllIssueRecords() {

        if (issueRecords.isEmpty()) {

            System.out.println(
                    "No issue records available."
            );

            return;
        }

        for (IssueRecord record :
                issueRecords) {

            record.displayRecord();
        }
    }


    // =========================
    // REPORT
    // =========================

    public void generateReport() {

        int available = 0;
        int issued = 0;
        int returned = 0;


        for (Book book : books) {

            if (book.isAvailable()) {
                available++;
            } else {
                issued++;
            }
        }


        for (IssueRecord record :
                issueRecords) {

            if (record.isReturned()) {
                returned++;
            }
        }


        System.out.println();
        System.out.println(
                "========== LIBRARY REPORT =========="
        );

        System.out.println(
                "Total Books        : "
                        + books.size()
        );

        System.out.println(
                "Available Books    : "
                        + available
        );

        System.out.println(
                "Issued Books       : "
                        + issued
        );

        System.out.println(
                "Total Members      : "
                        + members.size()
        );

        System.out.println(
                "Transactions       : "
                        + issueRecords.size()
        );

        System.out.println(
                "Returned Books     : "
                        + returned
        );

        System.out.println(
                "===================================="
        );
    }


    // =========================
    // FILE SUPPORT
    // =========================

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<IssueRecord> getIssueRecords() {
        return issueRecords;
    }


    public void addBookFromFile(Book book) {

        if (!bookExists(book.getBookId())) {
            books.add(book);
        }
    }


    public void addMemberFromFile(Member member) {

        if (!memberExists(member.getMemberId())) {
            members.add(member);
        }
    }


    public void addIssueRecordFromFile(
            IssueRecord record) {

        issueRecords.add(record);
    }
}