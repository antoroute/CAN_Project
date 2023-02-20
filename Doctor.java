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
