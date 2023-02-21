import java.io.IOException;
import java.time.LocalDate;

public class Main {
        public static void main(String args[]) throws IOException {
                Doctor docteur = new Doctor("Jonathan Keller", 1, LocalDate.of(2017, 1, 13), "3 Westminster Street",
                                "T01234",
                                "banae");
                Doctor docteur1 = new Doctor("Jonathan Banane", 2, LocalDate.of(2017, 1, 13), "3 Westminster Street",
                                "T01234",
                                "banae");
                Patient patient = new Patient("Noah", LocalDate.of(2017, 1, 13), "banane", "0637871981",
                                "noah.Attia@gmail.com", 1);

                MCS mcs = new MCS();
                mcs.addDoctor(docteur);
                mcs.addDoctor(docteur1);
                mcs.addPatient(patient);
                mcs.addDoctor(docteur);
                mcs.addDoctor(docteur1);
                MCS.deleteObjectsByAttribute("doctor", "name", "Jonathan Banane");
        }
}
