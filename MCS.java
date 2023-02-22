import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MCS {
    private static List<Doctor> doctors;
    private static List<Patient> patients;
    private static List<Appointment> appointments;
    private static List<Treatment> treatments;
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
        loadDoctorsFromFile();
        System.out.println(doctors.size());
        doctors.add(doctor);
        saveDoctorsToFile();
    }

    public void addPatient(Patient patient) throws IOException {
        loadPatientsFromFile();
        patients.add(patient);
        savePatientsToFile();
    }

    public void addAppointment(Appointment appointment) throws IOException {
        loadAppointmentsFromFile();
        appointments.add(appointment);
        saveAppointmentsToFile();
    }

    public void addTreatment(Treatment treatment) throws IOException {
        loadTreatmentsFromFile();
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
            Integer id = doctor.getId();
            String dateOfBirth = doctor.getDateOfBirth().format(DATE_FORMATTER);
            String specialty = doctor.getSpecialty();
            String licenseNumber = doctor.getLicenseNumber();
            String hospital = doctor.getHospital();
            fileWriter.write(String.format(
                    "    {\"name\": \"%s\",\"id\": \"%s\", \"dateOfBirth\": \"%s\", \"specialty\": \"%s\", \"licenseNumber\": \"%s\", \"hospital\": \"%s\"}",
                    name, id, dateOfBirth, specialty, licenseNumber, hospital));
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
            Integer doctorsId = patient.getDoctorsId();
            fileWriter.write(String.format(
                    "    {\"name\": \"%s\", \"dateOfBirth\": \"%s\", \"insuranceCompany\": \"%s\", \"contactNumber\": \"%s\", \"email\": \"%s\", \"doctorsId\": \"%s\"}",
                    name, dateOfBirth, insuranceCompany, contactNumber, email, doctorsId));
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
            fileWriter.write(String.format(
                    "  {\"doctor\": \"%s\", \"patient\": \"%s\", \"diagnosis\": \"%s\", \"medication\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\"}",
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

    private static void loadDoctorsFromFile() throws IOException {
        Path path = Paths.get("doctor.json");
        byte[] bytes = Files.readAllBytes(path);
        String json = new String(bytes, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonDoctors = jsonObject.getJSONArray("doctors");
        for (int i = 0; i < jsonDoctors.length(); i++) {
            JSONObject jsonDoctor = jsonDoctors.getJSONObject(i);
            String name = jsonDoctor.getString("name");
            Integer id = jsonDoctor.getInt("id");
            LocalDate dateOfBirth = LocalDate.parse(jsonDoctor.getString("dateOfBirth"), DATE_FORMATTER);
            String specialty = jsonDoctor.getString("specialty");
            String licenseNumber = jsonDoctor.getString("licenseNumber");
            String hospital = jsonDoctor.getString("hospital");
            Doctor doctor = new Doctor(name, id, dateOfBirth, specialty, licenseNumber, hospital);
            doctors.add(doctor);
        }
    }

    private static void loadPatientsFromFile() throws IOException {
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
            Integer doctorsId = jsonPatient.getInt("doctorsId");
            Patient patient = new Patient(name, dateOfBirth, insuranceCompany, contactNumber, email, doctorsId);
            patients.add(patient);
        }
    }

    private static void loadAppointmentsFromFile() throws IOException {
        Path path = Paths.get("appointment.json");
        byte[] bytes = Files.readAllBytes(path);
        String json = new String(bytes, StandardCharsets.UTF_8);
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String doctorName = jsonObject.getString("doctor");
            String patientName = jsonObject.getString("patient");
            LocalDateTime appointmentDate = LocalDateTime.parse(jsonObject.getString("appointmentDate"),
                    DATE_TIME_FORMATTER);
            Doctor doctor = findDoctorByName(doctorName);
            Patient patient = findPatientByName(patientName);
            Appointment appointment = new Appointment(doctor, patient, appointmentDate);
            appointments.add(appointment);
        }
    }

    private static void loadTreatmentsFromFile() throws IOException {
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

    static Doctor findDoctorByName(String name) throws IOException {
        loadDoctorsFromFile();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        System.out.println("Doctor not found");
        return null;
    }

    static Patient findPatientByName(String name) throws IOException {
        loadPatientsFromFile();
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        System.out.println("Patient not found");
        return null;
    }

    static ArrayList<Treatment> findTreatmentByPatientName(String name) throws IOException {
        loadTreatmentsFromFile();
        ArrayList<Treatment> treatmentsList = new ArrayList<>();
        for (Treatment treatment : treatments) {
            if (treatment.getPatient().getName().equals(name)) {
                treatmentsList.add(treatment);
            }
        }
        return treatmentsList;
    }

    static ArrayList<Appointment> findAppointmentByPatientName(String name) throws IOException {
        loadAppointmentsFromFile();
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equals(name)) {
                appointmentsList.add(appointment);
            }
        }
        return appointmentsList;
    }

    static ArrayList<Appointment> findAppointmentByDoctorName(String name) throws IOException {
        loadAppointmentsFromFile();
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().getName().equals(name)) {
                appointmentsList.add(appointment);
            }
        }
        return appointmentsList;
    }

    public static void deleteObjectsByAttribute(String objectName, String attributeName, String attributeValue) {
        String filePath = objectName + ".json";
        Path path = Paths.get(filePath);

        if (Files.exists(path)) {
            try {
                // Read the JSON file as a string
                byte[] bytes = Files.readAllBytes(path);
                String jsonString = new String(bytes, StandardCharsets.UTF_8);

                // Convert the string to a JSONObject
                JSONObject jsonObject = new JSONObject(jsonString);

                // Get the JSONArray for the object
                JSONArray jsonArray = jsonObject.getJSONArray(objectName + "s");

                // Search for the objects with the matching attribute value and remove them
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.getString(attributeName).equals(attributeValue)) {
                        jsonArray.remove(i);
                        i--; // move back one index after removing the object
                    }
                }

                // Write the updated JSONObject back to the file
                Files.writeString(path, jsonObject.toString(), StandardCharsets.UTF_8);
                System.out.println(objectName + " with " + attributeName + " " + attributeValue + " has been deleted.");

            } catch (IOException e) {
                System.out.println("An error occurred while reading or writing the JSON file.");
                e.printStackTrace();
            } catch (JSONException e) {
                System.out.println("An error occurred while parsing the JSON file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("The " + objectName + " file does not exist.");
        }
    }
}