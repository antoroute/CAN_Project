<<<<<<< HEAD
import java.time.LocalDate;

public class Patient {
    private String name;
    private LocalDate dateOfBirth;
    private String insuranceCompany;
    private String contactNumber;
    private String email;

    public Patient(String name, LocalDate dateOfBirth, String insuranceCompany, String contactNumber, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.insuranceCompany = insuranceCompany;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getter and setter methods
    // Getters
    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
=======
import java.io.*;
import java.util.*;

public class Patient extends Person {
    private ArrayList<Integer> doctorId;
    private ArrayList<Integer> prescriptionsId;

    public Patient(String firstName, String lastName, String gender, String address, String phone, String email,
            String socialNumbre,
            String BirthDate, Integer id, ArrayList<Integer> prescriptionsId) {
        super(firstName, lastName, gender, address, phone, email, id, BirthDate, id);
        this.prescriptionsId = prescriptionsId;
    }

    public Patient(String firstName, String lastName, String gender, String address, String phone, String email,
            String socialNumbre,
            String BirthDate, Integer id, ArrayList<Integer> doctorId, ArrayList<Integer> prescriptionsId) {
        super(firstName, lastName, gender, address, phone, email, id, BirthDate, id);
        this.doctorId = doctorId;
        this.prescriptionsId = prescriptionsId;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "\nDoctor ID: " + doctorId + "\nPrescriptions ID: " + prescriptionsId;
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        setFirstName(scanner.nextLine());
        System.out.print("Enter last name: ");
        setLastName(scanner.nextLine());
        System.out.print("Enter gender: ");
        setGender(scanner.nextLine());
        System.out.print("Enter address: ");
        setAddress(scanner.nextLine());
        System.out.print("Enter phone: ");
        setPhone(scanner.nextLine());
        System.out.print("Enter email: ");
        setEmail(scanner.nextLine());
        System.out.print("Enter social number: ");
        setSocialNumbre(scanner.nextLine());
        System.out.print("Enter birth date: ");
        setBirthDate(scanner.nextLine());
        System.out.print("Enter doctor ID: ");
        doctorId.add(scanner.nextInt());
        System.out.print("Enter prescriptions ID: ");
        prescriptionsId.add(scanner.nextInt());
    }

    public void save() {
        try {
            File file = new File("Users.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            ArrayList<Patient> patients = new ArrayList<Patient>();
            while (line != null) {
                Patient patient = new Patient(line.split(",")[0], line.split(",")[1],
                        line.split(",")[3], line.split(",")[4], line.split(",")[5], line.split(",")[6],
                        line.split(",")[7], Integer.parseInt(line.split(",")[8]), line.split(",")[9],line.split(",")[10]);
                patients.add(patient);
                line = bufferedReader.readLine();
            }
            (String firstName, String lastName, String gender, String address, String phone, String email,
            String socialNumbre,
            String BirthDate, Integer id, ArrayList<Integer> prescriptionsId) 
            bufferedReader.close();
            patients.add(this);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Patient patient : patients) {
                bufferedWriter
                        .write(patient.name + "," + patient.gender + "," + patient.id + "," + patient.address + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(int id, String field, String value) {
        try {
            File file = new File("Users.json");
            if (!file.exists()) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            ArrayList<Patient> patients = new ArrayList<Patient>();
            while (line != null) {
                Patient patient = new Patient(line.split(",")[0], line.split(",")[1],
                        Integer.parseInt(line.split(",")[2]), line.split(",")[3]);
                if (patient.id == id) {
                    switch (field) {
                        case "address":
                            patient.address = value;
                            break;
                        case "phone":
                            patient.phone = value;
                            break;
                        case "email":
                            patient.email = value;
                            break;
                        case "doctorId":
                            patient.doctorId.add(Integer.parseInt(value));
                            break;
                    }
                }
                patients.add(patient);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Patient patient : patients) {
                bufferedWriter
                        .write(patient.name + "," + patient.gender + "," + patient.id + "," + patient.address + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            File file = new File("Users.json");
            if (!file.exists()) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            ArrayList<Patient> patients = new ArrayList<Patient>();
            while (line != null) {
                Patient patient = new Patient(line.split(",")[0], line.split(",")[1],
                        Integer.parseInt(line.split(",")[2]), line.split(",")[3]);
                if (patient.id != id) {
                    patients.add(patient);
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Patient patient : patients) {
                bufferedWriter
                        .write(patient.name + "," + patient.gender + "," + patient.id + "," + patient.address + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void search(String field, String value) {
        try {
            File file = new File("Users.json");
            if (!file.exists()) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                Patient patient = new Patient(line.split(",")[0], line.split(",")[1],
                        Integer.parseInt(line.split(",")[2]), line.split(",")[3]);
                switch (field) {
                    case "fistName":
                        if (patient.name.equals(value)) {
                            patient.display();
                        }
                        break;
                    case "id":
                        if (patient.id == Integer.parseInt(value)) {
                            patient.display();
                        }
                        break;
                    case "address":
                        if (patient.address.equals(value)) {
                            patient.display();
                        }
                        break;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
>>>>>>> f5ddb25beb69268e0e3d07b6185a96fa3ea0b73d
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public void setPhone(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}