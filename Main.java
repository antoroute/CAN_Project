<<<<<<< HEAD
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String args[]) throws IOException {
        Doctor docteur = new Doctor("Jonathan Keller", LocalDate.of(2017, 1, 13), "3 Westminster Street", "T01234", "banae");
        Doctor docteur1 = new Doctor("Jonathan Banane", LocalDate.of(2017, 1, 13), "3 Westminster Street", "T01234", "banae");
        Patient patient = new Patient("Noah", LocalDate.of(2017, 1, 13), "banane", "0637871981", "noah.Attia@gmail.com");
        MCS mcs = new MCS();
        mcs.addDoctor(docteur);
        mcs.addDoctor(docteur1);
        mcs.addPatient(patient);
        mcs.addDoctor(docteur);
        mcs.addDoctor(docteur1);
    }
}
=======
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Création d'un rendez-vous
        Appointment appointment = new Appointment("Rendez-vous chez le médecin",
                LocalDateTime.of(2023, 2, 28, 10, 0),
                LocalDateTime.of(2023, 2, 28, 11, 0), 8, 1, "Rendez-vous pour un contrôle");
        Appointment appointment2 = new Appointment("Rendez-vous chez le médecin",
                LocalDateTime.of(2023, 2, 28, 10, 0),
                LocalDateTime.of(2023, 2, 28, 11, 0), 80, 15, "Rendez-vous pour un contrôle");

        // Sauvegarde du rendez-vous dans un fichier JSON
        appointment.saveToFile();
        appointment2.saveToFile();
    }
}
>>>>>>> f5ddb25beb69268e0e3d07b6185a96fa3ea0b73d
