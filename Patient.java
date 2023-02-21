import java.time.LocalDate;

public class Patient {
    private String name;
    private LocalDate dateOfBirth;
    private String insuranceCompany;
    private String contactNumber;
    private String email;

    public Patient(String name, LocalDate dateOfBirth, String insuranceCompany, String contactNumber, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.insuranceCompany = insuranceCompany;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getter and setter methods
    // Getters
    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public void setPhone(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}