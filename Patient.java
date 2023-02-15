import java.lang.reflect.Array;
import java.util.ArrayList;

public class Patient extends Person {
    public Integer doctorId;
    public ArrayList prescriptions;

    public Patient(String firstName, String lastName, String address, String phone, String email, String socialNumbre,
            String BirthDate, Integer id, Integer doctorId, ArrayList prescriptions) {
        super(firstName, lastName, address, phone, email, socialNumbre, BirthDate, id);
        this.doctorId = doctorId;
        this.prescriptions = prescriptions;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
