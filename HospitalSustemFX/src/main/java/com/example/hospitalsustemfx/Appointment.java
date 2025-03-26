package com.example.hospitalsustemfx;

import java.time.LocalDateTime;

public class Appointment {
    // Attributes of the class
    private String appointmentId;
    private LocalDateTime dateTime;
    private Doctor doctor;
    private Patient patient;
    // Constructor of the class
    public Appointment(String appointmentId, LocalDateTime dateTime, Doctor doctor, Patient patient) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
    }


    // Getters and setters for all attributes

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    // toString method to display the object in a string format
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", dateTime=" + dateTime +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
