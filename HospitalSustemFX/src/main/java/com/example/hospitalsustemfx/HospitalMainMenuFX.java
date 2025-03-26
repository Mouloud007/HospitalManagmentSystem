package com.example.hospitalsustemfx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

public class HospitalMainMenuFX extends Application {

    private int maxRecords;
    private PatientList patientList;
    private DoctorList doctorList;
    private MedicalRecordsList medicalRecordsList;
    private AppointmentList appointmentList;

    public int showWelcomeDialog() {
        int maxRecords = 0;
        boolean validInput = false;

        while (!validInput) {
            TextInputDialog dialog = new TextInputDialog("100");
            dialog.setTitle("Welcome to the Hospital Management System");
            dialog.setHeaderText("Welcome to the Hospital Management System !!");
            dialog.setContentText("Please input the maximum number of records your hospital can hold:");

            Optional<String> result = dialog.showAndWait();

            try {
                maxRecords = Integer.parseInt(result.orElse("100"));
                validInput = true;
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please input a number.");
                alert.showAndWait();
            }
        }

        return maxRecords;
    }
    @Override
    public void start(Stage primaryStage) {
        maxRecords = showWelcomeDialog();
        patientList = new PatientList(maxRecords);
        doctorList = new DoctorList();
        medicalRecordsList = new MedicalRecordsList(maxRecords);
        appointmentList = new AppointmentList(maxRecords);
        PatientFileHandler.readRecords(patientList);
        PatientFileHandler.readDoctor(doctorList);
        PatientFileHandler.readAppointment(appointmentList, doctorList, patientList);
        PatientFileHandler.readMedicalRecords(medicalRecordsList);



        primaryStage.setTitle("Hospital Main Menu");

        VBox root = new VBox(50);
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.TOP_CENTER);

        Label headingLabel = new Label("Hospital Main Menu");
        headingLabel.setTextFill(Color.web("#0076a3"));
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        FlowPane buttonBox = new FlowPane(Orientation.HORIZONTAL, 30, 30);
        buttonBox.setAlignment(Pos.CENTER);

        Button addPatientBtn = createStyledButton("Add Patient");
        Button displayPatientsBtn = createStyledButton("Display Patients");
        Button removePatientBtn = createStyledButton("Remove Patient");
        Button addDoctorBtn = createStyledButton("Add Doctor");
        Button makeAppointmentBtn = createStyledButton("Make Appointment");
        Button displayAppointmentsBtn = createStyledButton("Display Appointments");
        Button addMedicalRecordBtn = createStyledButton("Add Medical Record");
        Button displayDoctorsBtn = createStyledButton("Display Doctors");
        Button displayMedicalRecordsBtn = createStyledButton("Display Medical Records");
        Button saveAndQuitBtn = createStyledButton("Save and Quit");

        addPatientBtn.setOnAction(e -> addPatientHandler());
        displayPatientsBtn.setOnAction(e -> displayPatientsHandler());
        removePatientBtn.setOnAction(e -> removePatientHandler());
        addDoctorBtn.setOnAction(e -> addDoctorHandler());
        makeAppointmentBtn.setOnAction(e -> makeAppointmentHandler(patientList, doctorList, appointmentList));
        displayAppointmentsBtn.setOnAction(e -> displayAppointmentsHandler(appointmentList));
        addMedicalRecordBtn.setOnAction(e -> addMedicalRecordHandler());
        displayDoctorsBtn.setOnAction(e -> displayDoctorsHandler());
        displayMedicalRecordsBtn.setOnAction(e -> displayMedicalRecordsHandler());
        saveAndQuitBtn.setOnAction(e -> saveAndQuitHandler(primaryStage));

        buttonBox.getChildren().addAll(
                addPatientBtn, displayPatientsBtn, removePatientBtn, addDoctorBtn, makeAppointmentBtn,
                displayAppointmentsBtn, addMedicalRecordBtn, displayDoctorsBtn, displayMedicalRecordsBtn,
                saveAndQuitBtn
        );

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/hospital.jpg")));
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(800);
        imageView.setFitWidth(1000);
        imageView.setPreserveRatio(true);

        root.getChildren().addAll(headingLabel, buttonBox);
        root.getChildren().add(imageView);
        Label label = new Label("Copy right 2024 Mouloud El guellab 2164302");
        label.setFont(Font.font("Arial", FontWeight.LIGHT, 16));
        label.setTextFill(Color.web("black"));
        root.getChildren().add(label);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        Scene scene = new Scene(scrollPane, 1000, 800);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #0076a3; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: Arial;");
        button.setMinWidth(100); // Set minimum width
        button.setMinHeight(30); // Set minimum height
        return button;
    }

    private void addPatientHandler() {
        Stage addPatientStage = new Stage();
        addPatientStage.setTitle("Add Patient");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField nhsNumberField = new TextField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);
        grid.add(new Label("Phone Number:"), 0, 2);
        grid.add(phoneNumberField, 1, 2);
        grid.add(new Label("NHS Number:"), 0, 3);
        grid.add(nhsNumberField, 1, 3);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String nhsNumber = nhsNumberField.getText();

            if (isValidName(name)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Name cannot contain numbers.");
                alert.showAndWait();
                return;
            }

            if (isValidPhoneNumber(phoneNumber)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid 11-digit phone number.");
                alert.showAndWait();
                return;
            }

            if (!isValidNhsNumber(nhsNumber)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("NHS Number can only contain numbers.");
                alert.showAndWait();
                return;
            }

            Patient patient = new Patient(name, address, phoneNumber, nhsNumber);
            patientList.addPatient(patient);
            PatientFileHandler.saveRecords(maxRecords, patientList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patients Information");
            alert.setHeaderText(null);
            alert.setContentText("Patient added successfully.");
            alert.showAndWait();

            addPatientStage.close();
        });

        grid.add(submitBtn, 1, 4);

        Scene scene = new Scene(grid, 300, 200);
        addPatientStage.setScene(scene);
        addPatientStage.show();
    }

    private boolean isValidName(String name) {
        // Check if the name contains only alphabetic characters
        return !name.matches("[a-zA-Z]+");
    }

    private boolean isValidNhsNumber(String nhsNumber) {
        // Check if the NHS number contains only numeric characters
        return nhsNumber.matches("\\d+");
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phone number is exactly 11 digits long and contains only numeric characters
        return !phoneNumber.matches("\\d{11}");
    }
    private boolean isValidEmail(String email) {
        // Check if the email address matches a valid email format
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private void displayPatientsHandler() {
        if (patientList == null || patientList.getTotal() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No patients found.");
            alert.showAndWait();
        } else {
            StringBuilder patientsInfo = new StringBuilder();
            patientsInfo.append("List of Patients:\n");
            for (int i = 0; i < patientList.getTotal(); i++) {
                Patient patient = patientList.getPatient(i+1); // Changed from i+1 to i
                if (patient != null) {
                    patientsInfo.append(patient).append("\n"); // Added toString() call
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patients Information");
            alert.setHeaderText(null);
            alert.setContentText("Patient added successfully.\n" + patientsInfo); // Combined the two strings
            alert.showAndWait();
        }
    }





    private void removePatientHandler() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Patient");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the NHS number to delete the patient:");
        dialog.showAndWait().ifPresent(nhsNumber -> {
            if (patientList.removePatient(nhsNumber)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Patient removed successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Patient not found.");
                alert.showAndWait();
            }
        });
    }

    private void addDoctorHandler() {
        Stage addDoctorStage = new Stage();
        addDoctorStage.setTitle("Add Doctor");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField nameField = new TextField();
        TextField specializationField = new TextField();
        TextField contactNumberField = new TextField();
        TextField emailField = new TextField();
        TextField addressField = new TextField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Specialization:"), 0, 1);
        grid.add(specializationField, 1, 1);
        grid.add(new Label("Contact Number:"), 0, 2);
        grid.add(contactNumberField, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);
        grid.add(new Label("Address:"), 0, 4);
        grid.add(addressField, 1, 4);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String name = nameField.getText();
            String specialization = specializationField.getText();
            String contactNumber = contactNumberField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            if (isValidName(name)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Name cannot contain numbers.");
                alert.showAndWait();
                return;
            }

            if (isValidPhoneNumber(contactNumber)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid 11-digit phone number.");
                alert.showAndWait();
                return;
            }

            if (!isValidEmail(email)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid email address.");
                alert.showAndWait();
                return;
            }

            Doctor doctor = new Doctor(name, specialization, contactNumber, email, address);
            doctorList.addDoctor(doctor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Doctor added successfully.");
            alert.showAndWait();

            addDoctorStage.close();
        });

        grid.add(submitBtn, 1, 5);

        Scene scene = new Scene(grid, 300, 250);
        addDoctorStage.setScene(scene);
        addDoctorStage.show();
    }






    private void displayDoctorsHandler() {
        if (doctorList.getTotal() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No doctors found.");
            alert.showAndWait();
        } else {
            StringBuilder doctorsInfo = new StringBuilder();
            doctorsInfo.append("List of Doctors:\n");
            for (int i = 0; i < doctorList.getTotal(); i++) {
                Doctor doctor = doctorList.getDoctor(i+1);
                if (doctor != null) {
                    doctorsInfo.append(doctor).append("\n");
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Doctors Information");
            alert.setHeaderText(null);
            alert.setContentText(doctorsInfo.toString());
            alert.showAndWait();
        }
    }



    private void makeAppointmentHandler(PatientList patientList, DoctorList doctorList, AppointmentList appointmentList) {
        Stage makeAppointmentStage = new Stage();
        makeAppointmentStage.setTitle("Make Appointment");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField appointmentIdField = new TextField();
        TextField dateTimeField = new TextField();
        TextField doctorNameField = new TextField();
        TextField patientNHSNumberField = new TextField();

        grid.add(new Label("Appointment ID:"), 0, 0);
        grid.add(appointmentIdField, 1, 0);
        grid.add(new Label("Date and Time (YYYY-MM-DD HH:MM):"), 0, 1);
        grid.add(dateTimeField, 1, 1);
        grid.add(new Label("Doctor's Name:"), 0, 2);
        grid.add(doctorNameField, 1, 2);
        grid.add(new Label("Patient's NHS Number:"), 0, 3);
        grid.add(patientNHSNumberField, 1, 3);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String appointmentId = appointmentIdField.getText();
            String dateTimeStr = dateTimeField.getText();
            String doctorName = doctorNameField.getText();
            String patientNHSNumber = patientNHSNumberField.getText();
// Print appointment details for verification
            System.out.println("Appointment ID: " + appointmentId);
            System.out.println("Date and Time: " + dateTimeStr);
            System.out.println("Doctor's Name: " + doctorName);
            System.out.println("Patient's NHS Number: " + patientNHSNumber);




            if (!isValidDateTime(dateTimeStr)) {
                showErrorAlert("Invalid date or time format. Please enter in the format: YYYY-MM-DD HH:MM");
                return;
            }

            Patient patient = patientList.search(patientNHSNumber);
            Doctor doctor = doctorList.search(doctorName);

            if (patient == null || doctor == null) {
                showErrorAlert("Patient or doctor not found.");
                return;
            }

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Appointment appointment = new Appointment(appointmentId, dateTime, doctor, patient);
            appointmentList.addAppointment(appointment);

            showAlert();

            makeAppointmentStage.close();
        });

        grid.add(submitBtn, 1, 4);

        Scene scene = new Scene(grid, 400, 200);
        makeAppointmentStage.setScene(scene);
        makeAppointmentStage.show();
    }

    private void displayAppointmentsHandler(AppointmentList appointmentList) {
        if (appointmentList == null) {
            showErrorAlert("Appointment list is null.");
            return;
        }

        int totalAppointments = appointmentList.getTotalAppointments();

        if (totalAppointments == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No appointments found.");
            alert.showAndWait();
        } else {
            StringBuilder appointmentsInfo = new StringBuilder();
            appointmentsInfo.append("List of Appointments:\n");
            for (int i = 0; i < totalAppointments; i++) {
                Appointment appointment = appointmentList.getAppointment(i);
                if (appointment != null) {
                    appointmentsInfo.append(appointment).append("\n");
                }
            }
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointments Information");
                alert.setHeaderText(null);
                alert.setContentText(appointmentsInfo.toString());
                alert.showAndWait();
            });
        }
    }


    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Appointment made successfully.");
        alert.showAndWait();
    }

    private boolean isValidDateTime(String dateTimeStr) {
        // Check if the date and time format is valid
        try {
            LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }



    private void addMedicalRecordHandler() {
        Stage addMedicalRecordStage = new Stage();
        addMedicalRecordStage.setTitle("Add Medical Record");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField recordIdField = new TextField();
        TextField patientNameField = new TextField();
        TextField doctorNameField = new TextField();
        TextField diagnosisField = new TextField();
        TextField treatmentField = new TextField();
        TextField nhsNumberField = new TextField();

        grid.add(new Label("Record ID:"), 0, 0);
        grid.add(recordIdField, 1, 0);
        grid.add(new Label("Patient's Name:"), 0, 1);
        grid.add(patientNameField, 1, 1);
        grid.add(new Label("Doctor's Name:"), 0, 2);
        grid.add(doctorNameField, 1, 2);
        grid.add(new Label("Diagnosis:"), 0, 3);
        grid.add(diagnosisField, 1, 3);
        grid.add(new Label("Treatment:"), 0, 4);
        grid.add(treatmentField, 1, 4);
        grid.add(new Label("Patient's NHS Number:"), 0, 5);
        grid.add(nhsNumberField, 1, 5);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String recordId = recordIdField.getText();
            String patientName = patientNameField.getText();
            String doctorName = doctorNameField.getText();
            String diagnosis = diagnosisField.getText();
            String treatment = treatmentField.getText();
            String nhsNumber = nhsNumberField.getText();

            MedicalRecords medicalRecord = new MedicalRecords(recordId, patientName, doctorName, diagnosis, treatment, nhsNumber);
            medicalRecordsList.addRecord(medicalRecord);
            PatientFileHandler.saveMedicalRecords(maxRecords, medicalRecordsList);

            addMedicalRecordStage.close();
        });

        grid.add(submitBtn, 1, 6);

        Scene scene = new Scene(grid, 400, 250);
        addMedicalRecordStage.setScene(scene);
        addMedicalRecordStage.show();
    }


    private void displayMedicalRecordsHandler() {
        // Implement displaying medical records with JavaFX UI
        if (medicalRecordsList.getTotal() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No medical records found.");
            alert.showAndWait();
        } else {
            StringBuilder medicalRecordsInfo = new StringBuilder();
            medicalRecordsInfo.append("List of Medical Records:\n");
            for (int i = 0; i < medicalRecordsList.getTotal(); i++) {
                MedicalRecords medicalRecord = medicalRecordsList.getMedicalRecord(i);
                if (medicalRecord != null) {
                    medicalRecordsInfo.append(medicalRecord).append("\n");
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Medical Records Information");
            alert.setHeaderText(null);
            alert.setContentText(medicalRecordsInfo.toString());
            alert.showAndWait();
        }
    } 


    private void saveAndQuitHandler(Stage primaryStage) {
        PatientFileHandler.saveRecords(maxRecords ,patientList);
        PatientFileHandler.saveAppointment(maxRecords, appointmentList);
        PatientFileHandler.saveMedicalRecords(maxRecords, medicalRecordsList);
        PatientFileHandler.saveDoctor(maxRecords,doctorList);


        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}