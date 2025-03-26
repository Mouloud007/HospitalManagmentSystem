package com.example.hospitalsustemfx;

import java.util.ArrayList;
public class DoctorList {
    // DoctorList class is a list of doctors
    private ArrayList<Doctor> doctors;
    // Maximum capacity of the list
    private static final int MAX_CAPACITY = 300;

    // Constructor
    public DoctorList() {
        doctors = new ArrayList<>();

    }

    // Add a doctor to the list
    public boolean addDoctor(Doctor doctor) {
        if (!isFull()) {
            doctors.add(doctor);
            return true;
        } else {
            return false;
        }
    }
    // Remove a doctor from the list
    public boolean removeDoctor(String doctorName) {
        Doctor foundDoctor = search(doctorName);
        if (foundDoctor != null) {
            doctors.remove(foundDoctor);
            return true;
        } else {
            return false;
        }
    }
    // Search for a doctor in the list
    public Doctor search(String doctorName) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(doctorName)) {
                return doctor;
            }
        }
        return null;
    }
    // Get a doctor from the list
    public Doctor getDoctor(int position) {
        if (position < 1 || position > getTotal()) {
            return null;
        } else {
            return doctors.get(position - 1);
        }
    }
    // Check if the list is empty
    public boolean isEmpty() {
        return doctors.isEmpty();
    }

    // Check if the list is full
    public boolean isFull() {
        return doctors.size() == MAX_CAPACITY;
    }
    // Get the total number of doctors in the list
    public int getTotal() {
        return doctors.size();
    }
    // Get the list of doctors
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    // Set the list of doctors

    @Override
    public String toString() {
        return doctors.toString();
    }
}
