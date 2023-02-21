import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class MCS {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;
    private List<Treatment> treatments;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public MCS() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
        treatments = new ArrayList<>();
    }

    // Add methods for doctors, patients, appointments, and treatments

    public void addDoctor(Doctor doctor) throws IOException {
        doctors.add(doctor);
        saveDoctorsToFile();
    }

    public void addPatient(Patient patient) throws IOException {
        patients.add(patient);
        savePatientsToFile();
    }

    public void addAppointment(Appointment appointment) throws IOException {
        appointments.add(appointment);
        saveAppointmentsToFile();
    }

    public void addTreatment(Treatment treatment) throws IOException {
        treatments.add(treatment);
        saveTreatmentsToFile();
    }

    // Save methods for doctors, patients, appointments, and treatments

    private void saveDoctorsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("doctor.json");
        fileWriter.write("{\n");
        fileWriter.write("  \"doctors\": [\n");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            String name = doctor.getName();
            String dateOfBirth = doctor.getDateOfBirth().format(DATE_FORMATTER);
            String specialty = doctor.getSpecialty();
            String licenseNumber = doctor.getLicenseNumber();
            String hospital = doctor.getHospital();
            fileWriter.write(String.format("    {\"name\": \"%s\", \"dateOfBirth\": \"%s\", \"specialty\": \"%s\", \"licenseNumber\": \"%s\", \"hospital\": \"%s\"}",
                    name, dateOfBirth, specialty, licenseNumber, hospital));
            if (i < doctors.size() - 1) {
                fileWriter.write(",");
            }
            fileWriter.write("\n");
        }
        fileWriter.write("  ]\n");
        fileWriter.write("}");
        fileWriter.close();
    }

    private void savePatientsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("patient.json");
        fileWriter.write("{\n");
        fileWriter.write("  \"patients\": [\n");
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            String name = patient.getName();
            String dateOfBirth = patient.getDateOfBirth().format(DATE_FORMATTER);
            String insuranceCompany = patient.getInsuranceCompany();
            String contactNumber = patient.getContactNumber();
            String email = patient.getEmail();
            fileWriter.write(String.format("    {\"name\": \"%s\", \"dateOfBirth\": \"%s\", \"insuranceCompany\": \"%s\", \"contactNumber\": \"%s\", \"email\": \"%s\"}",
                    name, dateOfBirth, insuranceCompany, contactNumber, email));
            if (i < patients.size() - 1) {
                fileWriter.write(",");
            }
            fileWriter.write("\n");
        }
        fileWriter.write("  ]\n");
        fileWriter.write("}");
        fileWriter.close();
    }

    private void saveAppointmentsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("appointment.json");
        fileWriter.write("[\n");
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            Doctor doctor = appointment.getDoctor();
            Patient patient = appointment.getPatient();
            LocalDateTime appointmentDate = appointment.getAppointmentDate();
            String doctorName = doctor.getName();
            String patientName = patient.getName();
            String appointmentDateString = appointmentDate.format(DATE_TIME_FORMATTER);
            fileWriter.write(String.format("  {\"doctor\": \"%s\", \"patient\": \"%s\", \"appointmentDate\": \"%s\"}",
                    doctorName, patientName, appointmentDateString));
            if (i < appointments.size() - 1) {
                fileWriter.write(",");
            }
            fileWriter.write("\n");
        }
        fileWriter.write("]");
        fileWriter.close();
    }

    private void saveTreatmentsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("treatment.json");
        fileWriter.write("[\n");
        for (int i = 0; i < treatments.size(); i++) {
            Treatment treatment = treatments.get(i);
            Doctor doctor = treatment.getDoctor();
            Patient patient = treatment.getPatient();
            String diagnosis = treatment.getDiagnosis();
            String medication = treatment.getMedication();
            LocalDate startDate = treatment.getStartDate();
            LocalDate endDate = treatment.getEndDate();
            String doctorName = doctor.getName();
            String patientName = patient.getName();
            String startDateString = startDate.format(DATE_FORMATTER);
            String endDateString = endDate.format(DATE_FORMATTER);
            fileWriter.write(String.format("  {\"doctor\": \"%s\", \"patient\": \"%s\", \"diagnosis\": \"%s\", \"medication\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\"}",
            doctorName, patientName, diagnosis, medication, startDateString, endDateString));
    if (i < treatments.size() - 1) {
        fileWriter.write(",");
    }
    fileWriter.write("\n");
}
fileWriter.write("]");
fileWriter.close();
}

public void loadFromFiles() throws IOException {
loadDoctorsFromFile();
loadPatientsFromFile();
loadAppointmentsFromFile();
loadTreatmentsFromFile();
}

private void loadDoctorsFromFile() throws IOException {
Path path = Paths.get("doctor.json");
byte[] bytes = Files.readAllBytes(path);
String json = new String(bytes, StandardCharsets.UTF_8);
JSONObject jsonObject = new JSONObject(json);
JSONArray jsonDoctors = jsonObject.getJSONArray("doctors");
for (int i = 0; i < jsonDoctors.length(); i++) {
    JSONObject jsonDoctor = jsonDoctors.getJSONObject(i);
    String name = jsonDoctor.getString("name");
    LocalDate dateOfBirth = LocalDate.parse(jsonDoctor.getString("dateOfBirth"), DATE_FORMATTER);
    String specialty = jsonDoctor.getString("specialty");
    String licenseNumber = jsonDoctor.getString("licenseNumber");
    String hospital = jsonDoctor.getString("hospital");
    Doctor doctor = new Doctor(name, dateOfBirth, specialty, licenseNumber, hospital);
    doctors.add(doctor);
}
}

private void loadPatientsFromFile() throws IOException {
Path path = Paths.get("patient.json");
byte[] bytes = Files.readAllBytes(path);
String json = new String(bytes, StandardCharsets.UTF_8);
JSONObject jsonObject = new JSONObject(json);
JSONArray jsonPatients = jsonObject.getJSONArray("patients");
for (int i = 0; i < jsonPatients.length(); i++) {
    JSONObject jsonPatient = jsonPatients.getJSONObject(i);
    String name = jsonPatient.getString("name");
    LocalDate dateOfBirth = LocalDate.parse(jsonPatient.getString("dateOfBirth"), DATE_FORMATTER);
    String insuranceCompany = jsonPatient.getString("insuranceCompany");
    String contactNumber = jsonPatient.getString("contactNumber");
    String email = jsonPatient.getString("email");
    Patient patient = new Patient(name, dateOfBirth, insuranceCompany, contactNumber, email);
    patients.add(patient);
}
}

private void loadAppointmentsFromFile() throws IOException {
Path path = Paths.get("appointment.json");
byte[] bytes = Files.readAllBytes(path);
String json = new String(bytes, StandardCharsets.UTF_8);
JSONArray jsonArray = new JSONArray(json);
for (int i = 0; i < jsonArray.length(); i++) {
    JSONObject jsonObject = jsonArray.getJSONObject(i);
    String doctorName = jsonObject.getString("doctor");
    String patientName = jsonObject.getString("patient");
    LocalDateTime appointmentDate = LocalDateTime.parse(jsonObject.getString("appointmentDate"), DATE_TIME_FORMATTER);
    Doctor doctor = findDoctorByName(doctorName);
    Patient patient = findPatientByName(patientName);
    Appointment appointment = new Appointment(doctor, patient, appointmentDate);
    appointments.add(appointment);
}
}

private void loadTreatmentsFromFile() throws IOException {
Path path = Paths.get("treatment.json");
byte[] bytes = Files.readAllBytes(path);
String json = new String(bytes, StandardCharsets.UTF_8);
JSONArray jsonArray = new JSONArray(json);
for (int i = 0; i < jsonArray.length(); i++) {
    JSONObject jsonObject = jsonArray.getJSONObject(i);
    String doctorName = jsonObject.getString("doctor");
    String patientName = jsonObject.getString("patient");
    String diagnosis = jsonObject.getString("diagnosis");
    String medication = jsonObject.getString("medication");
    LocalDate startDate = LocalDate.parse(jsonObject.getString("startDate"), DATE_FORMATTER);
    LocalDate endDate = LocalDate.parse(jsonObject.getString("endDate"), DATE_FORMATTER);
    Doctor doctor = findDoctorByName(doctorName);
    Patient patient = findPatientByName(patientName);
    Treatment treatment = new Treatment(doctor, patient, diagnosis, medication, startDate, endDate);
    treatments.add(treatment);
}
}

private Doctor findDoctorByName(String name) {
for (Doctor doctor : doctors) {
    if (doctor.getName().equals(name)) {
        return doctor;
    }
}
return null;
}

private Patient findPatientByName(String name) {
for (Patient patient : patients) {
    if (patient.getName().equals(name)) {
        return patient;
    }
}
return null;
}
}


