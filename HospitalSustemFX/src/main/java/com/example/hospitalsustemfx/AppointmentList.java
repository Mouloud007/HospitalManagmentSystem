package com.example.hospitalsustemfx;

import java.util.ArrayList;

public class AppointmentList {
    // The list of appointments
    // The maximum number of appointments that can be stored in the list

    private ArrayList<Appointment> appointments;
    private final int MAX_APPOINTMENTS;

     // Constructor
    public AppointmentList(int maxAppointments) {
        appointments = new ArrayList<>();
        MAX_APPOINTMENTS = maxAppointments;
    }
    // Getters and Setters
    public Appointment getAppointment(int index) {
        if (index >= 0 && index < appointments.size()) {
            return appointments.get(index);
        }
        return null;
    }

    // Adds an appointment to the list
    public boolean addAppointment(Appointment appointment) {
        if (appointments.size() < MAX_APPOINTMENTS) {
            appointments.add(appointment);
            return true;
        }
        return false;
    }

    // Removes an appointment from the list
    public boolean removeAppointment(int index) {
        if (index >= 0 && index < appointments.size()) {
            appointments.remove(index);
            return true;
        }
        return false;
    }
    // Checks if the list is full
    public boolean isFull() {
        return appointments.size() == MAX_APPOINTMENTS;
    }

    // Returns the total number of appointments
    @Override
    public String toString() {
        return appointments.toString();
    }
     // Returns the total number of appointments
    public int getTotalAppointments() {
        return appointments.size();
    }
     // Returns the total number of appointments
    public int getTotal() {
        return 0;
    }

    // Returns the list of appointments
    public Appointment[] getAppointments() {
        return appointments.toArray(new Appointment[0]);

    }
}
