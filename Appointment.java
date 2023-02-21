<<<<<<< HEAD
import java.time.LocalDateTime;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentDate;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
    }

    // Getter and setter methods
     // Getters
     public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    // Setters
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
=======
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
>>>>>>> f5ddb25beb69268e0e3d07b6185a96fa3ea0b73d
