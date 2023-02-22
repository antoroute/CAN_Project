import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

        public static void main(String args[]) throws IOException {

                MCS mcs = new MCS();

                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println("\n");
                        System.out.println("Enter a number to perform an action: ");
                        System.out.println("1. Add a new patient");
                        System.out.println("2. Add a new doctor");
                        System.out.println("3. Add a new treatment from a doctor to a patient");
                        System.out.println("4. Make an appointment");
                        System.out.println("5. Show a patient information");
                        System.out.println("6. Show a doctor information");
                        System.out.println("7. Show a patient's treatment information");
                        System.out.println("8. Show a patient's appointment information");
                        System.out.println("9. Show a doctor's appointment information");
                        System.out.println("10. Delete element");
                        System.out.println("0. Exit");
                        int choice = scanner.nextInt();

                        switch (choice) {
                                case 1:
                                        addPatient();
                                        break;
                                case 2:
                                        addDoctor();
                                        break;
                                case 3:
                                        addTreatment();
                                        break;
                                case 4:
                                        makeAnAppointments();
                                        break;
                                case 5:
                                        displayPatientInfo();
                                        break;
                                case 6:
                                        displayDoctorInfo();
                                        break;
                                case 7:
                                        displayTreatmentInfo();
                                        break;
                                case 8:
                                        displayAppointmentInfo();
                                        break;
                                case 9:
                                        displayDoctorAppointmentInfo();
                                        break;
                                case 10:
                                        deleteElement();
                                        break;
                                case 0:
                                        System.exit(0);
                                default:
                                        System.out.println("Invalid choice. Try again.");
                        }
                }
        }

        public static void addDoctor() throws IOException {
                Scanner scanner = new Scanner(System.in);
                int id = 0;

                try {
                        File file = new File("doctor.json");
                        if (!file.exists()) {
                                id = 0;
                        } else {
                                Path path = Paths.get("doctor.json");
                                byte[] bytes = Files.readAllBytes(path);
                                String json = new String(bytes, StandardCharsets.UTF_8);
                                JSONObject jsonObject = new JSONObject(json);
                                JSONArray jsonDoctors = jsonObject.getJSONArray("doctors");
                                JSONObject lastJsonObject = jsonDoctors.getJSONObject(jsonDoctors.length() - 1);

                                id = lastJsonObject.getInt("id") + 1;
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                System.out.println("Enter the doctor's full name: ");
                String name = scanner.nextLine();
                System.out.println("Enter the doctor's date of birth : ");
                System.out.println("years : ");
                Integer year = scanner.nextInt();
                System.out.println("months : ");
                Integer month = scanner.nextInt();
                System.out.println("days : ");
                Integer day = scanner.nextInt();
                LocalDate dob = LocalDate.of(year, month, day);
                System.out.println("Enter the doctor's specialization: ");
                String specialization = scanner.nextLine();
                System.out.println("Enter the doctor's license number: ");
                String licenseNumber = scanner.nextLine();
                System.out.println("Enter the doctor's hospital name: ");
                String hospital = scanner.nextLine();

                Doctor doctor = new Doctor(name, id, dob, specialization, licenseNumber, hospital);

                try {
                        MCS mcs = new MCS();
                        mcs.addDoctor(doctor);
                        System.out.println("Doctor " + name + " added to database.");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static void addPatient() throws IOException {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the patient's full name: ");
                String name = scanner.nextLine();
                System.out.println("Enter the patient's date of birth : ");
                System.out.println("years : ");
                Integer year = scanner.nextInt();
                System.out.println("months : ");
                Integer month = scanner.nextInt();
                System.out.println("days : ");
                Integer day = scanner.nextInt();
                LocalDate dob = LocalDate.of(year, month, day);
                System.out.println("Enter the patient's insurance company: ");
                String insuranceCompany = scanner.nextLine();
                System.out.println("Enter the patient's contact number: ");
                String contactNumber = scanner.nextLine();
                System.out.println("Enter the patient's email: ");
                String email = scanner.nextLine();
                System.out.println("Enter the patient's doctor id: ");
                Integer DoctorsId = scanner.nextInt();

                Patient patient = new Patient(name, dob, insuranceCompany, contactNumber, email, DoctorsId);

                try {
                        MCS mcs = new MCS();
                        mcs.addPatient(patient);
                        System.out.println("Patient " + name + " added to database.");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static void addTreatment() throws IOException {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the patient's name: ");
                String patientName = scanner.nextLine();
                Patient patient = MCS.findPatientByName(patientName);
                System.out.println("Enter the treatment type: ");
                String treatmentType = scanner.nextLine();
                System.out.println("Enter the doctor's name: ");
                String doctorName = scanner.nextLine();
                Doctor docteur = MCS.findDoctorByName(doctorName);
                System.out.println("Enter the treatment description: ");
                String treatmentDescription = scanner.nextLine();
                System.out.println("Enter the treatment start date: ");
                System.out.println("years : ");
                Integer yearStart = scanner.nextInt();
                System.out.println("months : ");
                Integer monthStart = scanner.nextInt();
                System.out.println("days : ");
                Integer dayStart = scanner.nextInt();
                LocalDate dobStart = LocalDate.of(yearStart, monthStart, dayStart);
                System.out.println("Enter the end treatment date: ");
                System.out.println("years : ");
                Integer yearEnd = scanner.nextInt();
                System.out.println("months : ");
                Integer monthEnd = scanner.nextInt();
                System.out.println("days : ");
                Integer dayEnd = scanner.nextInt();
                LocalDate dobEnd = LocalDate.of(yearEnd, monthEnd, dayEnd);

                Treatment traitement = new Treatment(docteur, patient, treatmentType, treatmentDescription, dobStart,
                                dobEnd);

                try {
                        MCS mcs = new MCS();
                        mcs.addTreatment(traitement);
                        System.out.println(treatmentDescription + " added to database.");
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

        public static void makeAnAppointments() throws IOException {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the patient's name: ");
                String patientName = scanner.nextLine();
                Patient patient = MCS.findPatientByName(patientName);
                System.out.println("Enter the doctor's name: ");
                String doctorName = scanner.nextLine();
                Doctor docteur = MCS.findDoctorByName(doctorName);
                System.out.println("Enter the appointment date: ");
                System.out.println("years : ");
                Integer year = scanner.nextInt();
                System.out.println("months : ");
                Integer month = scanner.nextInt();
                System.out.println("days : ");
                Integer day = scanner.nextInt();
                System.out.println("hour : ");
                Integer hour = scanner.nextInt();
                System.out.println("minutes : ");
                Integer minutes = scanner.nextInt();
                LocalDateTime dob = LocalDateTime.of(year, month, day, hour, minutes);

                Appointment appointment = new Appointment(docteur, patient, dob);

                try {
                        MCS mcs = new MCS();
                        mcs.addAppointment(appointment);
                        System.out.println("Appointment added to database.");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static void displayPatientInfo() throws IOException {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the patient's name: ");
                String patientName = scanner.nextLine();
                Patient patient = MCS.findPatientByName(patientName);

                if (patient == null) {
                        System.out.println("Could not find patient in the database.");
                        return;
                }

                System.out.println("\n");
                System.out.println("Name: " + patient.getName());
                System.out.println("Date of Birth: " + patient.getDateOfBirth());
                System.out.println("Email: " + patient.getEmail());
                System.out.println("Phone Number: " + patient.getContactNumber());
                System.out.println("Insurance Company: " + patient.getInsuranceCompany());
                ArrayList<Treatment> treatments = MCS.findTreatmentByPatientName(patientName);
                System.out.println("Medical History: ");
                for (Treatment treatment : treatments) {
                        System.out.println("Treatment Diagnostic: " + treatment.getDiagnosis());
                }
        }

        public static void displayDoctorInfo() throws IOException {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the doctor's name: ");
                String doctorName = scanner.nextLine();
                Doctor doctor = MCS.findDoctorByName(doctorName);

                if (doctor == null) {
                        System.out.println("Could not find patient in the database.");
                        return;
                }

                System.out.println("\n");
                System.out.println("Name: " + doctor.getName());
                System.out.println("Date of Birth: " + doctor.getDateOfBirth());
                System.out.println("Specialty: " + doctor.getSpecialty());
                System.out.println("License Number: " + doctor.getLicenseNumber());
                System.out.println("Hospital: " + doctor.getHospital());
                System.out.println("Id: " + doctor.getId());

        }

        public static void displayTreatmentInfo() throws IOException {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the patient's name: ");
                String patientName = scanner.nextLine();

                ArrayList<Treatment> treatments = MCS.findTreatmentByPatientName(patientName);

                if (treatments == null) {
                        System.out.println("Could not find treatment for this patient in the database.");
                        return;
                }

                for (Treatment treatment : treatments) {

                        System.out.println("\n");
                        System.out.println("Patient: " + treatment.getPatient().getName());
                        System.out.println("Doctor: " + treatment.getDoctor().getName());
                        System.out.println("Treatment Diagnostic: " + treatment.getDiagnosis());
                        System.out.println("Treatment Medication: " + treatment.getMedication());
                        System.out.println("Treatment Start date: " + treatment.getStartDate());
                        System.out.println("Treatment End date: " + treatment.getEndDate());
                }

        }

        public static void displayAppointmentInfo() throws IOException {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the patient's name: ");
                String patientName = scanner.nextLine();

                ArrayList<Appointment> appointments = MCS.findAppointmentByPatientName(patientName);

                if (appointments == null) {
                        System.out.println("Could not find appointment for this patient in the database.");
                        return;
                }

                for (Appointment appointment : appointments) {

                        System.out.println("\n");
                        System.out.println("Patient: " + appointment.getPatient().getName());
                        System.out.println("Doctor: " + appointment.getDoctor().getName());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                }

        }

        public static void displayDoctorAppointmentInfo() throws IOException {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the doctor's name: ");
                String doctorName = scanner.nextLine();

                ArrayList<Appointment> appointments = MCS.findAppointmentByDoctorName(doctorName);

                if (appointments == null) {
                        System.out.println("Could not find appointment for this doctor in the database.");
                        return;
                }

                for (Appointment appointment : appointments) {

                        System.out.println("\n");
                        System.out.println("Patient: " + appointment.getPatient().getName());
                        System.out.println("Doctor: " + appointment.getDoctor().getName());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                }

        }

        public static void deleteElement() throws IOException {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the name of the number of the object you want to delete: ");
                System.out.println("1. Patient");
                System.out.println("2. Doctor");
                System.out.println("3. Appointment");

                int choice = scanner.nextInt();

                switch (choice) {
                        case 1:
                                System.out.println("Enter the patient's name: ");
                                String patientName = scanner.nextLine();
                                MCS.deleteObjectsByAttribute("patient", "name", patientName);
                                break;
                        case 2:
                                System.out.println("Enter the doctor's name: ");
                                String doctorName = scanner.nextLine();
                                MCS.deleteObjectsByAttribute("doctor", "name", doctorName);
                                break;
                        case 3:
                                System.out.println("Enter the appointment's date: ");
                                String appointmentDate = scanner.nextLine();
                                MCS.deleteObjectsByAttribute("appointment", "appointmentDate", appointmentDate);
                                break;
                        default:
                                System.out.println("Invalid choice");
                                break;
                }
        }

}
