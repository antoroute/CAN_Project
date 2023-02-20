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