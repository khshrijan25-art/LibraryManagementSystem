public class Member {

    private final int memberId;
    private String name;
    private String phone;
    private String email;

    public Member(int memberId,
                   String name,
                   String phone,
                   String email) {

        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayMember() {

        System.out.println("Member ID : " + memberId);
        System.out.println("Name      : " + name);
        System.out.println("Phone     : " + phone);
        System.out.println("Email     : " + email);

        System.out.println("---------------------------------");
    }
}