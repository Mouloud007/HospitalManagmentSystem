package com.example.hospitalsustemfx;

import java.util.ArrayList;

public class MedicalRecordsList {
    // attributes of the class
    private ArrayList<MedicalRecords> records;
    private final int MAX_MEDICAL_RECORDS;

    /**
     * Constructor initializes the empty medical record list and sets the maximum list size
     * @param maxRecords The maximum number of records in the list
     */
    public MedicalRecordsList(int maxRecords) {
        records = new ArrayList<>();
        MAX_MEDICAL_RECORDS = maxRecords;
    }




    /**
     * Searches for a medical record by its NHS number
     * @param NHSNumber The NHS number to search for
     * @return The medical record if found, null otherwise
     */
    public MedicalRecords search (String NHSNumber) {
        for (MedicalRecords record : records) {
            if (record.getNHSNumber().equals(NHSNumber)) {
                return record;
            }
        }
        return null;
    }


    /**
     * Adds a new medical record to the list
     * @param record The record to add
     * @return True if the record was added successfully, false otherwise
     */
    public boolean addRecord(MedicalRecords record) {
        if (records.size() < MAX_MEDICAL_RECORDS) {
            records.add(record);
            return true;
        }
        return false;
    }

    /**
     * Removes the record at the given index from the list
     * @param index The index of the record to remove
     * @return True if the record was removed successfully, false otherwise
     */
    public boolean removeRecord(int index) {
        if (index >= 0 && index < records.size()) {
            records.remove(index);
            return true;
        }
        return false;
    }
     // Returns the total number of records in the list
    public int getTotal() {
        return records.size();
    }
    public MedicalRecords getMedicalRecord(int index) {
        if (index >= 0 && index < records.size()) {
            return records.get(index);
        }
        return null;
    }

    /**
     * Checks if the record list is full
     * @return True if the list is full and false otherwise
     */
    public boolean isFull() {
        return records.size() == MAX_MEDICAL_RECORDS;
    }

    // Returns a string representation of the medical record list

    @Override
    public String toString() {
        return records.toString();
    }
    // Returns the records in the list as an array
    public MedicalRecords[] getRecords() {
        return records.toArray(new MedicalRecords[0]);
    }
    // Returns the record at the given index
    public Object getRecord(int i) {
        return records.get(i);
    }
}
