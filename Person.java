
public class Person {
    private String address;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
    private String BirthDate;
    private Integer socialNumbre;
    private Integer id;
    private String gender;

    public Person(String firstName, String lastName, String gender, String address, String phone, String email,
            Integer socialNumbre,
            String BirthDate, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.socialNumbre = socialNumbre;
        this.BirthDate = BirthDate;
        this.gender = gender;
        this.id = socialNumbre;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getSocialNumbre() {
        return socialNumbre;
    }

    public Integer getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSocialNumbre(String string) {
        this.socialNumbre = string;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", BirthDate=" + BirthDate + ", email=" + email + ", firstName="
                + firstName
                + ", lastName=" + lastName + ", gender=" + gender + ", phone=" + phone + ", socialNumbre="
                + socialNumbre + "]";
    }
}