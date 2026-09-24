import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner =
                new Scanner(System.in)) {

        Library library =
                new Library();

        FileManager fileManager =
                new FileManager();


        // Load saved data
        fileManager.loadBooks(library);
        fileManager.loadMembers(library);
        fileManager.loadIssueRecords(library);


        int choice = -1;


        do {

            System.out.println();
            System.out.println(
                    "================================="
            );

            System.out.println(
                    "     LIBRARY MANAGEMENT SYSTEM"
            );

            System.out.println(
                    "================================="
            );

            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");

            System.out.println();

            System.out.println("6. Add Member");
            System.out.println("7. Display Members");
            System.out.println("8. Update Member");
            System.out.println("9. Delete Member");

            System.out.println();

            System.out.println("10. Issue Book");
            System.out.println("11. Return Book");
            System.out.println("12. Calculate Fine");

            System.out.println();

            System.out.println("13. Show Available Books");
            System.out.println("14. Show Issued Books");
            System.out.println("15. Transaction History");
            System.out.println("16. Reports");

            System.out.println();

            System.out.println("0. Exit");

            System.out.println(
                    "================================="
            );

            System.out.print(
                    "Enter your choice: "
            );


            // Menu input validation
            if (!scanner.hasNextInt()) {

                System.out.println(
                        "Please enter a valid number."
                );

                scanner.nextLine();

                continue;
            }


            choice =
                    scanner.nextInt();

            scanner.nextLine();


            switch (choice) {


                // =========================
                // ADD BOOK
                // =========================

                case 1 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID: "
                            );


                    System.out.print(
                            "Enter Book Title: "
                    );

                    String title =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter Author: "
                    );

                    String author =
                            scanner.nextLine()
                                    .trim();


                    if (title.isBlank()
                            || author.isBlank()) {

                        System.out.println(
                                "Title and author cannot be empty."
                        );

                        break;
                    }


                    Book book =
                            new Book(
                                    id,
                                    title,
                                    author
                            );


                    if (library.addBook(book)) {

                        fileManager.saveAllBooks(
                                library
                        );
                    }
                }


                // =========================
                // DISPLAY BOOKS
                // =========================

                case 2 -> {

                    library.displayAllBooks();
                }


                // =========================
                // SEARCH BOOK
                // =========================

                case 3 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID to search: "
                            );

                    library.searchBook(id);
                }


                // =========================
                // UPDATE BOOK
                // =========================

                case 4 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID to update: "
                            );


                    if (!library.bookExists(id)) {

                        System.out.println(
                                "Book not found."
                        );

                        break;
                    }


                    System.out.print(
                            "Enter New Title: "
                    );

                    String title =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter New Author: "
                    );

                    String author =
                            scanner.nextLine()
                                    .trim();


                    if (title.isBlank()
                            || author.isBlank()) {

                        System.out.println(
                                "Title and author cannot be empty."
                        );

                        break;
                    }


                    if (library.updateBook(
                            id,
                            title,
                            author)) {

                        fileManager.saveAllBooks(
                                library
                        );
                    }
                }


                // =========================
                // DELETE BOOK
                // =========================

                case 5 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID to delete: "
                            );


                    if (library.deleteBook(id)) {

                        fileManager.saveAllBooks(
                                library
                        );
                    }
                }


                // =========================
                // ADD MEMBER
                // =========================

                case 6 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Member ID: "
                            );


                    System.out.print(
                            "Enter Member Name: "
                    );

                    String name =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter Phone: "
                    );

                    String phone =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter Email: "
                    );

                    String email =
                            scanner.nextLine()
                                    .trim();


                    if (name.isBlank()
                            || phone.isBlank()
                            || email.isBlank()) {

                        System.out.println(
                                "Member information cannot be empty."
                        );

                        break;
                    }


                    Member member =
                            new Member(
                                    id,
                                    name,
                                    phone,
                                    email
                            );


                    if (library.addMember(member)) {

                        fileManager.saveAllMembers(
                                library
                        );
                    }
                }


                // =========================
                // DISPLAY MEMBERS
                // =========================

                case 7 -> {

                    library.displayAllMembers();
                }


                // =========================
                // UPDATE MEMBER
                // =========================

                case 8 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Member ID to update: "
                            );


                    if (!library.memberExists(id)) {

                        System.out.println(
                                "Member not found."
                        );

                        break;
                    }


                    System.out.print(
                            "Enter New Name: "
                    );

                    String name =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter New Phone: "
                    );

                    String phone =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter New Email: "
                    );

                    String email =
                            scanner.nextLine()
                                    .trim();


                    if (name.isBlank()
                            || phone.isBlank()
                            || email.isBlank()) {

                        System.out.println(
                                "Member information cannot be empty."
                        );

                        break;
                    }


                    if (library.updateMember(
                            id,
                            name,
                            phone,
                            email)) {

                        fileManager.saveAllMembers(
                                library
                        );
                    }
                }


                // =========================
                // DELETE MEMBER
                // =========================

                case 9 -> {

                    int id =
                            readPositiveInt(
                                    scanner,
                                    "Enter Member ID to delete: "
                            );


                    if (library.deleteMember(id)) {

                        fileManager.saveAllMembers(
                                library
                        );
                    }
                }


                // =========================
                // ISSUE BOOK
                // =========================

                case 10 -> {

                    int bookId =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID: "
                            );


                    int memberId =
                            readPositiveInt(
                                    scanner,
                                    "Enter Member ID: "
                            );


                    System.out.print(
                            "Enter Issue Date (DD/MM/YYYY): "
                    );

                    String issueDate =
                            scanner.nextLine()
                                    .trim();


                    System.out.print(
                            "Enter Due Date (DD/MM/YYYY): "
                    );

                    String dueDate =
                            scanner.nextLine()
                                    .trim();


                    if (library.issueBook(
                            bookId,
                            memberId,
                            issueDate,
                            dueDate)) {

                        fileManager.saveAllBooks(
                                library
                        );

                        fileManager.saveAllIssueRecords(
                                library
                        );
                    }
                }


                // =========================
                // RETURN BOOK
                // =========================

                case 11 -> {

                    int bookId =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID: "
                            );


                    System.out.print(
                            "Enter Return Date (DD/MM/YYYY): "
                    );

                    String returnDate =
                            scanner.nextLine()
                                    .trim();


                    if (library.returnBook(
                            bookId,
                            returnDate)) {

                        fileManager.saveAllBooks(
                                library
                        );

                        fileManager.saveAllIssueRecords(
                                library
                        );
                    }
                }


                // =========================
                // CALCULATE FINE
                // =========================

                case 12 -> {

                    int bookId =
                            readPositiveInt(
                                    scanner,
                                    "Enter Book ID: "
                            );


                    System.out.print(
                            "Enter Return Date (DD/MM/YYYY): "
                    );

                    String returnDate =
                            scanner.nextLine()
                                    .trim();


                    library.calculateFine(
                            bookId,
                            returnDate
                    );
                }


                // =========================
                // AVAILABLE BOOKS
                // =========================

                case 13 -> {

                    System.out.println();
                    System.out.println(
                            "AVAILABLE BOOKS"
                    );

                    library.displayAvailableBooks();
                }


                // =========================
                // ISSUED BOOKS
                // =========================

                case 14 -> {

                    System.out.println();
                    System.out.println(
                            "CURRENTLY ISSUED BOOKS"
                    );

                    library.displayIssuedBooks();
                }


                // =========================
                // TRANSACTION HISTORY
                // =========================

                case 15 -> {

                    System.out.println();
                    System.out.println(
                            "TRANSACTION HISTORY"
                    );

                    library.displayAllIssueRecords();
                }


                // =========================
                // REPORTS
                // =========================

                case 16 -> {

                    library.generateReport();
                }


                // =========================
                // EXIT
                // =========================

                case 0 -> {

                    System.out.println(
                            "Thank you for using the Library Management System."
                    );
                }


                default -> {

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
                }
            }

        } while (choice != 0);


                }
    }


    // =========================
    // INPUT VALIDATION
    // =========================

    private static int readPositiveInt(
            Scanner scanner,
            String prompt) {

        while (true) {

            System.out.print(prompt);


            if (scanner.hasNextInt()) {

                int value =
                        scanner.nextInt();

                scanner.nextLine();


                if (value > 0) {

                    return value;
                }


                System.out.println(
                        "ID must be greater than 0."
                );

            } else {

                System.out.println(
                        "Please enter a valid number."
                );

                scanner.nextLine();
            }
        }
    }
}