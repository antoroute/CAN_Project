import java.time.LocalDate;

public class Treatment {
    private Doctor doctor;
    private Patient patient;
    private String diagnosis;
    private String medication;
    private LocalDate startDate;
    private LocalDate endDate;

    public Treatment(Doctor doctor, Patient patient, String diagnosis, String medication, LocalDate startDate,
            LocalDate endDate) {
        if (patient.getDoctorsId() != doctor.getId()) {
            throw new IllegalArgumentException("The doctor and the patient are not linked");
        }
        this.doctor = doctor;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter and setter methods
    // Getters
    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Setters
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
