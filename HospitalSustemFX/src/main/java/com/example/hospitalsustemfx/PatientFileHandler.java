package com.example.hospitalsustemfx;

import java.io.*;
import java.time.LocalDateTime;

public class PatientFileHandler {

    // this class is used to save and read the records of the patient list,
    // doctor list,
    // appointment list and medical records list
    // i used the DataOutputStream and DataInputStream to write and read the records
    // from the files





    // Save the records to a files of the patient list
    public static void saveRecords(int maxPatients, PatientList patientList) {
        try {
            FileOutputStream patientFile = new FileOutputStream("Patients.dat");
            DataOutputStream patientWriter = new DataOutputStream(patientFile);
            patientWriter.writeInt(patientList.getTotal());
            for (int i = 1; i <= maxPatients; i++) {
                if (patientList.getPatient(i) != null) {
                    patientWriter.writeUTF(patientList.getPatient(i).getNHSNumber());
                    patientWriter.writeUTF(patientList.getPatient(i).getName());
                    patientWriter.writeInt(patientList.getPatient(i).getMedicalRecords().getTotal());
                    for (int j = 1; j <= patientList.getPatient(i).getMedicalRecords().getTotal(); j++) {
                        MedicalRecords medicalRecord = patientList.getPatient(i).getMedicalRecords().getMedicalRecord(j);
                        if (medicalRecord != null) {
                            patientWriter.writeUTF(medicalRecord.getRecordId());
                            patientWriter.writeUTF(medicalRecord.getDiagnosis());
                        }
                    }
                }
            }
            patientWriter.flush();
            patientWriter.close();
        } catch (IOException ioe) {
            System.out.println("Error writing file");
        }
    }

    // Read the records from the file of the patient list
    public static void readRecords(PatientList patientList) {
        try {
            FileInputStream patientFile = new FileInputStream("Patients.dat");
            DataInputStream patientReader = new DataInputStream(patientFile);

            String tempNHSNumber;
            String tempName = "";
            String tempRecordId = "";
            String tempDiagnosis = "";
            Patient tempPatient = null;
            MedicalRecords tempMedicalRecord;
            int tot = 0;
            int totRec = 0;

            tot = patientReader.readInt();
            for (int j = 1; j <= tot; j++) {
                tempNHSNumber = patientReader.readUTF();
                tempName = patientReader.readUTF();
                tempPatient = new Patient(tempName, "", "", tempNHSNumber);
                totRec = patientReader.readInt();
                for (int k = 1; k <= totRec; k++) {
                    tempRecordId = patientReader.readUTF();
                    tempDiagnosis = patientReader.readUTF();
                    tempMedicalRecord = new MedicalRecords(tempRecordId, tempName, "", tempDiagnosis, "", "" );
                    tempPatient.getMedicalRecords().addRecord(tempMedicalRecord);
                }
                patientList.addPatient(tempPatient);
            }
            patientReader.close();
        } catch (IOException ioe) {

            System.out.println(" Hi there if this is the first time you are running the program,\n " +
                       "this is normal you will see various messages that says no files !\n" +
                    " you ill not see them any more after you save some data and run the program again");
            System.out.println(" ");
            System.out.println("no records yet in  " + ioe.getMessage());
        }

    }
    // Save the records to a files of the doctor list
    public static void saveDoctor(int maxDoctors, DoctorList doctorList) {
        try {
            FileOutputStream doctorFile = new FileOutputStream("Doctors.dat");
            DataOutputStream doctorWriter = new DataOutputStream(doctorFile);
            doctorWriter.writeInt(doctorList.getTotal());
            for (int i = 1; i <= maxDoctors; i++) {
                if (doctorList.getDoctor(i) != null) {
                    doctorWriter.writeUTF(doctorList.getDoctor(i).getName());
                    doctorWriter.writeUTF(doctorList.getDoctor(i).getAddress());
                    doctorWriter.writeUTF(doctorList.getDoctor(i).getContactNumber());
                    doctorWriter.writeUTF(doctorList.getDoctor(i).getEmail());
                    doctorWriter.writeUTF(doctorList.getDoctor(i).getSpecialization());

                }
            }
            doctorWriter.flush();
            doctorWriter.close();
        } catch (IOException ioe) {
            System.out.println("Error writing file");
        }
    }
    // Read the records from the file of the doctor list
    public static void readDoctor(DoctorList doctorList) {
        try {
            FileInputStream doctorFile = new FileInputStream("Doctors.dat");
            DataInputStream doctorReader = new DataInputStream(doctorFile);

            String tempName = "";
            String tempAddress = "";
            String tempContactNumber = "";
            String tempEmail = "";
            String tempSpecialization = "";
            Doctor tempDoctor = null;
            int tot = 0;

            tot = doctorReader.readInt();
            for (int j = 1; j <= tot; j++) {
                tempName = doctorReader.readUTF();
                tempAddress = doctorReader.readUTF();
                tempContactNumber = doctorReader.readUTF();
                tempEmail = doctorReader.readUTF();
                tempSpecialization = doctorReader.readUTF();
                tempDoctor = new Doctor(tempName, tempSpecialization, tempContactNumber, tempEmail, tempAddress);
                doctorList.addDoctor(tempDoctor);
            }
            doctorReader.close();
        } catch (IOException ioe) {
            System.out.println("no records yet in  " + ioe.getMessage());
        }
    }


    // Save the records to a files of the appointment list
    static void saveAppointment(int maxAppointments, AppointmentList appointmentList) {
        try {
            FileOutputStream appointmentFile = new FileOutputStream("Appointments.dat");
            DataOutputStream appointmentWriter = new DataOutputStream(appointmentFile);

            // Write the number of appointments first
            appointmentWriter.writeInt(appointmentList.getTotalAppointments());

            for (int i = 0; i < appointmentList.getTotalAppointments(); i++) {
                Appointment appointment = appointmentList.getAppointment(i);
                appointmentWriter.writeUTF(appointment.getAppointmentId());
                appointmentWriter.writeUTF(appointment.getDateTime().toString());
                appointmentWriter.writeUTF(appointment.getDoctor().getName());
                appointmentWriter.writeUTF(appointment.getPatient().getNHSNumber());
            }

            appointmentWriter.close();
        } catch (IOException ioe) {
            System.out.println("No files yet " + ioe.getClass().getSimpleName());

        }
    }
    // Read the records from the file of the appointment list
    static void readAppointment(AppointmentList appointmentList, DoctorList doctorList, PatientList patientList) {
        try {
            FileInputStream appointmentFile = new FileInputStream("Appointments.dat");
            DataInputStream appointmentReader = new DataInputStream(appointmentFile);

            // Read the number of appointments first
            int totalAppointments = appointmentReader.readInt();

            for (int i = 0; i < totalAppointments; i++) {
                String appointmentId = appointmentReader.readUTF();
                LocalDateTime dateTime = LocalDateTime.parse(appointmentReader.readUTF());
                String doctorName = appointmentReader.readUTF();
                String patientNHSNumber = appointmentReader.readUTF();

                Doctor doctor = doctorList.search(doctorName);
                Patient patient = patientList.search(patientNHSNumber);

                if (doctor != null && patient != null) {
                    Appointment appointment = new Appointment(appointmentId, dateTime, doctor, patient);
                    appointmentList.addAppointment(appointment);
                }
            }

            appointmentReader.close();
        } catch (IOException ioe) {
            System.out.println("No records yet at  " + ioe.getMessage());
        }
    }
    // Save the records to a files of the medical records list
    public static void saveMedicalRecords(int maxRecords, MedicalRecordsList medicalRecordsList) {
        try {
            FileOutputStream medicalRecordsFile = new FileOutputStream("MedicalRecords.dat");
            DataOutputStream medicalRecordsWriter = new DataOutputStream(medicalRecordsFile);
            // Write the number of records first
            medicalRecordsWriter.writeInt(medicalRecordsList.getTotal());
            for (int i = 0; i < maxRecords; i++) {
                if (medicalRecordsList.getMedicalRecord(i) != null) {
                    medicalRecordsWriter.writeUTF(medicalRecordsList.getMedicalRecord(i).getRecordId());
                    medicalRecordsWriter.writeUTF(medicalRecordsList.getMedicalRecord(i).getPatientName());
                    medicalRecordsWriter.writeUTF(medicalRecordsList.getMedicalRecord(i).getDoctorName());
                    medicalRecordsWriter.writeUTF(medicalRecordsList.getMedicalRecord(i).getDiagnosis());
                    medicalRecordsWriter.writeUTF(medicalRecordsList.getMedicalRecord(i).getTreatment());
                    medicalRecordsWriter.writeUTF(medicalRecordsList.getMedicalRecord(i).getNHSNumber());
                }
            }
            medicalRecordsWriter.flush();
            medicalRecordsWriter.close();
        } catch (IOException ioe) {
            System.out.println("Error writing file");
        }
    }
    // Read the records from the file of the medical records list
    public static void readMedicalRecords(MedicalRecordsList medicalRecordsList) {
        try {
            FileInputStream medicalRecordsFile = new FileInputStream("MedicalRecords.dat");
            DataInputStream medicalRecordsReader = new DataInputStream(medicalRecordsFile);
            // Read the number of records first
            int totalRecords = medicalRecordsReader.readInt();
            String tempRecordId = "";
            String tempPatientName = "";
            String tempDoctorName = "";
            String tempDiagnosis = "";
            String tempTreatment = "";
            String tempNHSNumber = "";
            for (int i = 0; i < totalRecords; i++) {
                tempRecordId = medicalRecordsReader.readUTF();
                tempPatientName = medicalRecordsReader.readUTF();
                tempDoctorName = medicalRecordsReader.readUTF();
                tempDiagnosis = medicalRecordsReader.readUTF();
                tempTreatment = medicalRecordsReader.readUTF();
                tempNHSNumber = medicalRecordsReader.readUTF();
                MedicalRecords medicalRecord = new MedicalRecords(tempNHSNumber, tempRecordId, tempPatientName, tempDoctorName, tempDiagnosis, tempTreatment);
                medicalRecordsList.addRecord(medicalRecord);
            }
            medicalRecordsReader.close();
        } catch (IOException ioe) {
            System.out.println("no records yet in  " + ioe.getMessage());
        }
    }}