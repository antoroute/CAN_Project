import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
                Treatment traitement = new Treatment(docteur, patient, "Traitement de la banane",
                                "doliprame",
                                LocalDate.of(2017, 1, 13), LocalDate.of(2017, 5, 13));

                Appointment appointment = new Appointment(docteur, patient, LocalDateTime.of(2017, 1, 13, 10, 30));
                MCS mcs = new MCS();
                mcs.addDoctor(docteur);
                mcs.addDoctor(docteur1);
                mcs.addPatient(patient);
                mcs.addAppointment(appointment);
                mcs.addTreatment(traitement);
                MCS.deleteObjectsByAttribute("doctor", "name", "Jonathan Banane");
        }
}
