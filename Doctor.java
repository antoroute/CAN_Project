<<<<<<< HEAD
import java.time.LocalDate;

public class Doctor {
    private String name;
    private LocalDate dateOfBirth;
    private String specialty;
    private String licenseNumber;
    private String hospital;

    public Doctor(String name, LocalDate dateOfBirth, String specialty, String licenseNumber, String hospital) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.hospital = hospital;
    }

    // Getter and setter methods
     // Getters
     public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getHospital() {
        return hospital;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
=======
import java.util.ArrayList;

public class Doctor extends Person {
    ArrayList<Integer> patients;
    Integer speciality;

    public Doctor(String firstName, String lastName, String address, String phone, String email, String socialNumbre,
            String BirthDate, Integer id, Integer speciality, ArrayList<Integer> patients) {
        super(firstName, lastName, address, phone, email, socialNumbre, BirthDate, id);
        this.patients = patients;
        this.speciality = speciality;
    }

    public void addPatient(Integer patientId) {
        this.patients.add(patientId);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
>>>>>>> f5ddb25beb69268e0e3d07b6185a96fa3ea0b73d
