
# Hospital Management System (JavaFX)
![Hospital Management System Screenshot](hospital%20screenshot.png)

## Overview
The **Hospital Management System** is a comprehensive **JavaFX-based desktop application** built to facilitate the efficient management of vital operations within a hospital. It provides functionalities for managing patients, doctors, appointments, and medical records. The intuitive graphical user interface ensures ease of use for healthcare staff.
This project showcases the use of JavaFX for building dynamic and interactive user interfaces, along with robust backend logic for handling hospital operations.
## Features
### **Main Features**:
- **Patient Management**:
    - Add new patients with their personal information (name, NHS number, email address, and phone number).
    - Check for valid input through multiple field validations, e.g., email format, NHS number format, etc.
    - View and maintain a list of all registered patients.
    - Remove a patient from the records when necessary.

- **Doctor Management**:
    - Add doctors to the system with their respective information.
    - Maintain and display a list of all doctors available in the hospital.

- **Appointment Management**:
    - Schedule appointments by selecting patient and doctor details.
    - Display all appointments in an organized format.
    - Validate the provided date and time to avoid conflicts.

- **Medical Record Management**:
    - Add medical records for patients.
    - View the history of medical records for each patient.

### **Additional Functionalities**:
- **Welcome Dialog & User Input**:
    - Displays a welcome dialog upon application launch, allowing users to interact seamlessly.

- **Styled Components**:
    - The GUI contains customized, styled buttons, layouts, and intuitive controls for a fluid user experience.

- **Error Handling**:
    - Alerts for invalid user inputs or operations, such as entering an invalid name, phone number, or date format.
    - Pop-up confirmation on critical actions like saving and quitting the application.

- **Save and Quit**:
    - Ensures all entered data is saved persistently between sessions before the user exits the application.

## Technologies Used
- **Programming Language**: Java
- **Framework**: JavaFX
- **Development Environment**: IntelliJ IDEA
- **External Libraries**: None currently.
- **Java Version**: Works with Java 8 or higher.

## Project Structure
Below is a general structure of the project directory:
``` 
HospitalSustemFX/
│
├── src/
│   ├── main/
│       ├── java/
│           ├── com.example.hospitalsustemfx/
│               ├── HospitalMainMenuFX.java    // Main application entry point
│               ├── PatientList.java           // Logic for handling patient data
│               ├── DoctorList.java            // Logic for handling doctor data
│               ├── AppointmentList.java       // Logic for handling appointments
│               ├── MedicalRecordsList.java    // Logic for handling medical records
│               ├── utils/                     // Utility classes and helpers
│
├── Patients.dat // (Optional) Serialized patient data
└── README.md    // This README file
```
## Instructions to Run
### Requirements
- **Java Development Kit (JDK)**: Version **8** or higher.
- **JavaFX SDK**: Ensure JavaFX libraries are added to your project.

### Steps to Run
1. **Clone the Repository**: Clone this GitHub repository to your local system:
``` bash
   git clone https://github.com/Mouloud007/HospitalManagmentSystem.git
   cd HospitalManagmentSystem
```
1. **Open in IntelliJ IDEA**:
    - Open the folder in **IntelliJ IDEA**.
    - Configure the JavaFX library in the project settings:
        - Go to `File` → `Project Structure` → `Libraries` and add the `lib` folder of your JavaFX SDK.

2. **Compile and Run**:
    - Run the `HospitalMainMenuFX.java` file from IntelliJ's run menu.

3. **Run via Command Line (Optional)**: If you prefer, you can run the application via the command line:
``` bash
   java --module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls javafx.fxml -cp . com.example.hospitalsustemfx.HospitalMainMenuFX
```
1. **Interact with the Application**:
    - Use the graphical interface for managing patients, doctors, appointments, and medical records.

## Example Usage Scenarios
1. **Add a New Patient**:
    - Fill in the patient details (name, email, NHS number).
    - Use the GUI's Add Patient button to add this data to the system.

2. **Schedule an Appointment**:
    - Select a patient and their corresponding doctor.
    - Provide an appropriate date and time.
    - Submit the form to schedule an appointment.

3. **View Medical Records**:
    - Display the list of medical records associated with a specific patient.

## Enhancements in Future Versions
We plan to include the following additional features in future releases:
- **Database Integration**: Replace serialization with a database-backed solution for better scalability (e.g., MySQL or PostgreSQL).
- **Role-Based Access Control**: Users with specific roles (e.g., doctors, nurses, administrators) can be assigned permissions.
- **Advanced Search & Filters**:
    - Search by patient or doctor names.
    - Add filters for appointments by time, department, or doctor specialization.

- **Report Generation**: Export patient or appointment data in PDF or Excel format.

## Contributing
Contributions are welcome! Follow these steps to contribute:
1. Fork the repository.
2. Create a new feature branch:
``` bash
   git checkout -b feature-name
```
1. Commit your changes and push the branch to your forked repo.
2. Submit a pull request with a clear description of changes.

## License
This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more details.
## Author
Developed by **Mouloud007** as part of a project to demonstrate JavaFX and Object-Oriented Programming concepts.
