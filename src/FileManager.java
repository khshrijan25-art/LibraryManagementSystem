import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String DATA_FOLDER =
            "data";

    private static final String BOOK_FILE =
            DATA_FOLDER + "/books.txt";

    private static final String MEMBER_FILE =
            DATA_FOLDER + "/members.txt";

    private static final String ISSUE_FILE =
            DATA_FOLDER + "/issue_records.txt";


    public FileManager() {

        new File(DATA_FOLDER).mkdirs();
    }


    // =========================
    // BOOK FILE
    // =========================

    public void saveAllBooks(
            Library library) {

        try (FileWriter writer =
                     new FileWriter(
                             BOOK_FILE)) {

            for (Book book :
                    library.getBooks()) {

                writer.write(
                        book.getBookId()
                        + "|"
                        + book.getTitle()
                        + "|"
                        + book.getAuthor()
                        + "|"
                        + book.isAvailable()
                        + "\n"
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving books."
            );
        }
    }


    public void loadBooks(
            Library library) {

        File file =
                new File(BOOK_FILE);

        if (!file.exists()) {
            return;
        }


        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line =
                    reader.readLine()) != null) {

                String[] parts =
                        line.split(
                                "\\|",
                                -1
                        );


                if (parts.length == 4) {

                    try {

                        int id =
                                Integer.parseInt(
                                        parts[0]
                                );

                        Book book =
                                new Book(
                                        id,
                                        parts[1],
                                        parts[2]
                                );

                        book.setAvailable(
                                Boolean.parseBoolean(
                                        parts[3]
                                )
                        );

                        library.addBookFromFile(
                                book
                        );

                    } catch (
                            NumberFormatException e) {

                        System.out.println(
                                "Invalid book data skipped."
                        );
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading books."
            );
        }
    }


    // =========================
    // MEMBER FILE
    // =========================

    public void saveAllMembers(
            Library library) {

        try (FileWriter writer =
                     new FileWriter(
                             MEMBER_FILE)) {

            for (Member member :
                    library.getMembers()) {

                writer.write(
                        member.getMemberId()
                        + "|"
                        + member.getName()
                        + "|"
                        + member.getPhone()
                        + "|"
                        + member.getEmail()
                        + "\n"
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving members."
            );
        }
    }


    public void loadMembers(
            Library library) {

        File file =
                new File(MEMBER_FILE);

        if (!file.exists()) {
            return;
        }


        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line =
                    reader.readLine()) != null) {

                String[] parts =
                        line.split(
                                "\\|",
                                -1
                        );


                if (parts.length == 4) {

                    try {

                        int id =
                                Integer.parseInt(
                                        parts[0]
                                );

                        Member member =
                                new Member(
                                        id,
                                        parts[1],
                                        parts[2],
                                        parts[3]
                                );

                        library.addMemberFromFile(
                                member
                        );

                    } catch (
                            NumberFormatException e) {

                        System.out.println(
                                "Invalid member data skipped."
                        );
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading members."
            );
        }
    }


    // =========================
    // ISSUE RECORD FILE
    // =========================

    public void saveAllIssueRecords(
            Library library) {

        try (FileWriter writer =
                     new FileWriter(
                             ISSUE_FILE)) {

            for (IssueRecord record :
                    library.getIssueRecords()) {

                writer.write(
                        record.getBookId()
                        + "|"
                        + record.getMemberId()
                        + "|"
                        + record.getIssueDate()
                        + "|"
                        + record.getDueDate()
                        + "|"
                        + record.isReturned()
                        + "|"
                        + record.getReturnDate()
                        + "\n"
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving issue records."
            );
        }
    }


    public void loadIssueRecords(
            Library library) {

        File file =
                new File(ISSUE_FILE);

        if (!file.exists()) {
            return;
        }


        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line =
                    reader.readLine()) != null) {

                String[] parts =
                        line.split(
                                "\\|",
                                -1
                        );


                if (parts.length == 5
                        || parts.length == 6) {

                    try {

                        int bookId =
                                Integer.parseInt(
                                        parts[0]
                                );

                        int memberId =
                                Integer.parseInt(
                                        parts[1]
                                );


                        IssueRecord record =
                                new IssueRecord(
                                        bookId,
                                        memberId,
                                        parts[2],
                                        parts[3]
                                );


                        record.setReturned(
                                Boolean.parseBoolean(
                                        parts[4]
                                )
                        );


                        if (parts.length == 6) {

                            record.setReturnDate(
                                    parts[5]
                            );
                        }


                        library.addIssueRecordFromFile(
                                record
                        );

                    } catch (NumberFormatException e) {

                        System.out.println(
                                "Invalid issue record skipped."
                        );
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading issue records."
            );
        }
    }
}