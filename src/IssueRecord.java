public class IssueRecord {

    private final int bookId;
    private final int memberId;
    private final String issueDate;
    private final String dueDate;

    private boolean returned;
    private String returnDate;

    public IssueRecord(int bookId,
                       int memberId,
                       String issueDate,
                       String dueDate) {

        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;

        this.returned = false;
        this.returnDate = "";
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void displayRecord() {

        System.out.println("Book ID    : " + bookId);
        System.out.println("Member ID  : " + memberId);
        System.out.println("Issue Date : " + issueDate);
        System.out.println("Due Date   : " + dueDate);

        System.out.println("Return Date: "
                + (returned ? returnDate : "Not returned"));

        System.out.println("Status     : "
                + (returned ? "Returned" : "Issued"));

        System.out.println("---------------------------------");
    }
}