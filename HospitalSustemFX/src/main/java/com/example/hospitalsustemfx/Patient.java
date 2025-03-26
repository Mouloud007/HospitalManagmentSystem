package com.example.hospitalsustemfx;

public class Patient {

    //attributes  of the patient
    private String name;
    private String address;
    private String phoneNumber;
    private String NHSNumber;
    private AppointmentList appointment;

    private MedicalRecordsList medicalRecord;
    public static final int MAX_APPOINTMENTS = 300;
    public static final int MAX_MEDICAL_RECORDS = 300;

    /**
     * Constructor initializes the details of the patient
     *
     * @param name:        name of the patient
     * @param address:     address of the patient
     * @param phoneNumber: phone number of the patient
     * @param NHSNumber:   NHS number of the patient
     */
    public Patient(String name, String address, String phoneNumber, String NHSNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.NHSNumber = NHSNumber;
        this.appointment = new AppointmentList(MAX_APPOINTMENTS);
        this.medicalRecord = new MedicalRecordsList(MAX_MEDICAL_RECORDS);
    }

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNHSNumber() {
        return NHSNumber;
    }

    public void setNHSNumber(String NHSNumber) {
        this.NHSNumber = NHSNumber;
    }

    public AppointmentList getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentList appointment) {
        this.appointment = appointment;
    }


    //toString method to display the details of the patient

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", NHSNumber='" + NHSNumber + '\'' +
                '}';
    }
    //method to add an appointment to the patient
    public boolean addAppointment(Appointment appointment) {
        return this.appointment.addAppointment(appointment);
    }

    // method to get medical records of the patient
    public MedicalRecordsList getMedicalRecords() {
        return this.medicalRecord;
    }
    //method to set medical records of the patient
    public void setMedicalRecord(MedicalRecordsList medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

}