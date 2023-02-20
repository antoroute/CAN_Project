import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer doctorId;
    private Integer patientId;
    private String description;

    public Appointment(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer doctorId,
            Integer patientId, String description) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.description = description;
    }

    public void saveToFile() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"title\":\"").append(title).append("\",");
        json.append("\"DoctorId\":").append(doctorId).append(",");
        json.append("\"PatientId\":").append(patientId).append(",");
        json.append("\"description\":\"").append(description).append("\",");
        json.append("\"startDateTime\":\"").append(startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .append("\",");
        json.append("\"endDateTime\":\"").append(endDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .append("\"");

        json.append("}");

        // Ajout de l'objet JSON dans le fichier
        try {
            FileWriter fileWriter = new FileWriter("appointments.json", true);
            fileWriter.write(json.toString());
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}