package com.example.hospitalsustemfx;

import java.util.ArrayList;

public class PatientList {
    // attributes
    private ArrayList<Patient> patients;
    public final int MAX_CAPACITY;

    // constructor
    public PatientList(int maxCapacity) {
        patients = new ArrayList<>();
        MAX_CAPACITY = maxCapacity;
    }
    // addPatient method
    public boolean addPatient(Patient patient) {
        if (!isFull()) {
            patients.add(patient);
            return true;
        } else {
            return false;
        }
    }
    // removePatient method
    public boolean removePatient(String NHSNumber) {
        Patient foundPatient = search(NHSNumber);
        if (foundPatient != null) {
            patients.remove(foundPatient);
            return true;
        } else {
            return false;
        }
    }
    // search method
    public Patient search(String NHSNumber) {
        for (Patient patient : patients) {
            if (patient.getNHSNumber().equals(NHSNumber)) {
                return patient;
            }
        }
        return null;
    }
    // getPatient method
    public Patient getPatient(int position) {
        if (position < 1 || position > getTotal()) {
            return null;
        } else {
            return patients.get(position - 1);
        }
    }
     // if the list is empty
    public boolean isEmpty() {
        return patients.isEmpty();
    }
    // if the list is full
    public boolean isFull() {
        return patients.size() == MAX_CAPACITY;
    }
    // get the total number of patients
    public int getTotal() {
        return patients.size();
    }
     // string representation of the list
    @Override
    public String toString() {
        return patients.toString();
    }
    // get the total number of patients
    public int getTotalPatients() {
        return patients.size();
    }
}
