package com.example.hospitalsustemfx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HospitalMainMenu {
    /**
     * This class is the main class of the Hospital Management System.
     * It contains the main method that displays the main menu and handles the user's choice.
     * The main method creates the patient list, doctor list, and appointment list objects and reads the records from the files.
     * It then displays the main menu and handles the user's choice using a loop until the user chooses to quit.
     * The main method also contains methods to handle adding a patient, displaying patients, removing a patient, adding a doctor,
     * making an appointment, displaying appointments, adding a medical record, displaying doctors, displaying medical records,
     * and saving and quitting the program.
     * The main method also contains a method to display the main menu.
     * The main method also contains a method to print the welcome message and get the maximum number of records.
     *
     * @author Mouloud El guellab
     * @since 2024-04-14
     */
    // Main method
    public static void main(String[] args) {

        // pint welcome message and get the maximum number of records
        int maxRecords = printWelcomeMessage();

        // Create the patient list, doctor list, and appointment list objects and read the records from the files
        PatientList patientList;
        DoctorList doctorList;
        AppointmentList appointmentList;

        patientList = new PatientList(maxRecords);
        doctorList = new DoctorList();
        MedicalRecordsList medicalRecordsList = new MedicalRecordsList(maxRecords);
        appointmentList = new AppointmentList(maxRecords);
        // Read the records from the files
        PatientFileHandler.readRecords(patientList);
        PatientFileHandler.readDoctor(doctorList);
        PatientFileHandler.readAppointment(appointmentList, doctorList, patientList);
        PatientFileHandler.readMedicalRecords(medicalRecordsList);

        // Display the main menu and handle the user's choice using a loop until the user chooses to quit
        int choice;
        do {
            choice = displayMenu();

            switch (choice) {
                case 1:
                    addPatientHandler(maxRecords, patientList);
                    break;
                case 2:
                    displayPatientsHandler(patientList);
                    break;
                case 3:
                    removePatientHandler(patientList);
                    break;
                case 4:
                    addDoctorHandler(doctorList);
                    break;
                case 5:

                    makeAppointmentHandler(patientList, doctorList, appointmentList);
                    break;

                case 6:
                    displayAppointmentsHandler(appointmentList);
                    break;
                case 7:
                    addMedicalRecordHandler(medicalRecordsList);
                    break;
                case 8:
                    displayDoctorsHandler(doctorList);
                    break;
                case 9:
                    displayMedicalRecordsHandler(medicalRecordsList);
                    break;
                case 10:
                    saveAndQuitHandler(maxRecords, patientList, doctorList, appointmentList, maxRecords, medicalRecordsList);
                    break;
                default:
                    System.out.println("Enter a valid choice (1-10) only");
            }
        } while (choice != 10);
    }
     // Implementing adding a patient
     static void addPatientHandler(int maxPatients, PatientList patientList) {
         if (patientList.getTotalPatients() < maxPatients) {
             System.out.print("Enter patient name: ");
             String name = EasyScanner.nextLine();  // changed from EasyScanner.nextString() to EasyScanner.nextLine()
             System.out.print("Enter patient address: ");
             String address = EasyScanner.nextLine();
             System.out.print("Enter patient phone number: ");
             String phoneNumber;
             Pattern phoneNumberPattern = Pattern.compile("^\\d{11}$");
             Matcher phoneMatcher;
             while (true) {
                 phoneNumber = EasyScanner.nextLine();
                 phoneMatcher = phoneNumberPattern.matcher(phoneNumber);
                 if (phoneMatcher.matches()) {
                     break;
                 } else {
                     System.out.println("Invalid phone number format. Your number should be 11 digits.");
                 }
             }
             System.out.print("Enter patient NHS number: ");
             String NHSNumber = EasyScanner.nextLine();
             // Create a new patient object and add it to the patient list
             Patient patient = new Patient(name, address, phoneNumber, NHSNumber);
             patientList.addPatient(patient);
             System.out.println("Patient added successfully.");
         } else {
             System.out.println("Maximum number of patients reached.");
         }
     }
    // Implementing displaying patients
    static void displayPatientsHandler(PatientList patientList) {
        if (patientList.getTotal() == 0) {
            System.out.println("No patients found.");
        } else {
            System.out.println("List of Patients:");
            for (int i = 0; i < patientList.getTotal(); i++) {
                System.out.println(patientList.getPatient(i + 1).toString());
            }
        }
    }
 // Implementing removing a patient
    static void removePatientHandler(PatientList patientList) {
        // Implement removing a patient
        System.out.print("Enter the NHS number to delete the patient : ");
        String NHSNumber = EasyScanner.nextLine();
        if (patientList.removePatient(NHSNumber)) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }
  // Implementing adding a doctor
    static void addDoctorHandler(DoctorList doctorList) {
        // Implement adding a doctor
        System.out.print("Enter doctor name: ");
        String name = EasyScanner.nextString();
        System.out.print("Enter doctor specialization: ");
        String specialization = EasyScanner.nextLine();

        EasyScanner.nextLine();
        System.out.print("Enter doctor contact number: ");
        // Validating phone number format
        String contactNumber;
        Pattern contactNumberPattern = Pattern.compile("^\\d{11}$");
        Matcher phoneMatcher;
        while (true) {
            contactNumber = EasyScanner.nextLine();
            phoneMatcher = contactNumberPattern.matcher(contactNumber);
            if (phoneMatcher.matches()) {
                break;
            } else {
                System.out.println("Invalid phone number format  your number should be 11 digits.");
            }
        }
        // Validating email format
        String email;
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher;
        while (true) {
            System.out.print("Enter doctor email: ");
            email = EasyScanner.nextLine();
            matcher = pattern.matcher(email);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        }

        System.out.print("Enter doctor address: ");
        String address = EasyScanner.nextLine();
        // Create a new doctor object and add it to the doctor list
        Doctor doctor = new Doctor(name, specialization, contactNumber, email, address);
        doctorList.addDoctor(doctor);
        System.out.println("Doctor added successfully.");
    }

    // Implementing making an appointment
    static void makeAppointmentHandler(PatientList patientList, DoctorList doctorList, AppointmentList appointmentList) {
        if (patientList.getTotalPatients() == 0 || doctorList.getTotal() == 0) {
            System.out.println("No patients or doctors available to make an appointment.");
        } else {
            System.out.print("Enter patient NHS number: ");
            String NHSNumber = EasyScanner.next();
            Patient patient = patientList.search(NHSNumber);
            if (patient == null) {
                System.out.println("Patient not found.");
                return;
            }

            System.out.print("Enter doctor name: ");
            String doctorName = EasyScanner.nextString();
            Doctor doctor = doctorList.search(doctorName);
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }

            System.out.print("Enter appointment ID: ");
            String appointmentId = EasyScanner.nextLine();
            EasyScanner.nextLine();

            LocalDateTime dateTime;
            while (true) {
                try {
                    System.out.print("Enter appointment date and time (format: yyyy-MM-dd HH:mm): ");
                    String dateTimeStr = EasyScanner.nextLine();
                    dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    break;  // will only reach this line if the date and time were valid, so we break the loop
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date or time. Please enter in the format: yyyy-MM-dd HH:mm");
                }
            }
            // Create a new appointment object and add it to the appointment list
            Appointment appointment = new Appointment(appointmentId, dateTime, doctor, patient);
            appointmentList.addAppointment(appointment);
            System.out.println("Appointment made successfully.");

        }
    }
    // Implementing displaying appointments
    static void displayAppointmentsHandler(AppointmentList appointmentList) {

        if (appointmentList.getTotalAppointments() <= 0) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("List of Appointments:");
            for (int i = 0; i < appointmentList.getTotalAppointments(); i++) {
                System.out.println(appointmentList.getAppointment(i).toString());
            }
        }
    }
     // Implementing adding a medical record
    static void addMedicalRecordHandler(MedicalRecordsList medicalRecordsList) {
        System.out.print("Enter patient NHS number: ");
        String NHSNumber = EasyScanner.nextLine();
        System.out.print("Enter patient name: ");
        String patientName = EasyScanner.nextLine();
        System.out.print("Enter record ID: ");
        String recordId = EasyScanner.nextLine();
        System.out.print("Enter doctor name: ");
        String doctorId = EasyScanner.nextLine();
        System.out.print("Enter diagnosis: ");
        String diagnosis = EasyScanner.nextLine();
        System.out.print("Enter treatment: ");
        String treatment = EasyScanner.nextLine();

        MedicalRecords medicalRecord = new MedicalRecords(NHSNumber, recordId, patientName, doctorId, diagnosis, treatment);
        if (medicalRecordsList.addRecord(medicalRecord)) {
            System.out.println("Medical record added successfully.");
        } else {
            System.out.println("Failed to add medical record. The list might be full.");
        }
    }

     // Implementing displaying doctors
    static void displayDoctorsHandler(DoctorList doctorList) {
        if (doctorList.getDoctors().isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("List of Doctors:");
            for (int i = 0; i < doctorList.getTotal(); i++) {
                System.out.println(doctorList.getDoctor(i + 1).toString());
            }
        }
    }
     // Implementing displaying medical records
    static void displayMedicalRecordsHandler(MedicalRecordsList medicalRecordsList) {
        if (medicalRecordsList.getTotal() == 0) {
            System.out.println("No medical records found.");
        } else {
            System.out.println("List of Medical Records:");
            for (int i = 0; i < medicalRecordsList.getTotal(); i++) {
                System.out.println(medicalRecordsList.getMedicalRecord(i).toString());
            }
        }
    }
    // Implementing saving and quitting the program
    static void saveAndQuitHandler(int maxPatients, PatientList patientList, DoctorList doctorList, AppointmentList appointmentList, int maxRecords, MedicalRecordsList medicalRecordsList) {      // Save patient records and exit the program
        PatientFileHandler.saveRecords(maxPatients, patientList);
        PatientFileHandler.saveDoctor(maxPatients, doctorList);
        PatientFileHandler.saveAppointment(maxPatients, appointmentList);
        PatientFileHandler.saveMedicalRecords(maxRecords, medicalRecordsList);

        System.out.println("Patient records saved successfully. Exiting the program.");
        System.exit(0);
    }

    // Implementing displaying the main menu i used the .repeat() method to make the menu look better and more organized it will only work if you are
    // using java sdk 11 or higher
    public static int displayMenu() {
        System.out.println(" ".repeat(60) + "|----+-----------------------------------------------------|");
        System.out.println(" ".repeat(60) + "| Hospital Management System by Mouloud El guellab 2164302 |");
        System.out.println(" ".repeat(60) + "|----+-----------------------------------------------------|");
        System.out.println(" ".repeat(60));
        System.out.println(" ".repeat(60) + "|***     Welcome to the Hospital Management System    **** |");
        System.out.println(" ".repeat(60) + "|                Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " |");
        System.out.println(" ".repeat(60));
        System.out.println(" ".repeat(60));
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 1. | Add Patient                                         |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 2. | Display Patients                                    |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 3. | Remove Patient                                      |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 4. | Add Doctor                                          |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 5. | Make Appointment                                    |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 6. | Display Appointments                                |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 7. | Add Medical Record                                  |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 8. | Display Doctors                                     |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 9. | Display Medical Records                             |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.println(" ".repeat(60) + "| 10.| Save and Quit                                       |");
        System.out.println(" ".repeat(60) + "+----+-----------------------------------------------------+");
        System.out.print(" ".repeat(60) + "  Enter choice (1-10): ");
        int choice = EasyScanner.nextInt();
        EasyScanner.nextLine();
        System.out.println();
        return choice;

    }
    // Implementing printing the welcome message
    public static int printWelcomeMessage() {
        System.out.println(" ".repeat(60) + "*".repeat(67));
        System.out.println(" ".repeat(60) + "*          Welcome to the Hospital Management System !!");
        System.out.println(" ".repeat(60) + "* Please input the maximum number of records your hospital can hold: ");
        System.out.println(" ".repeat(60) + "*".repeat(67));
        System.out.print(" ".repeat(60) + " ");
        return EasyScanner.nextInt();
    }


}