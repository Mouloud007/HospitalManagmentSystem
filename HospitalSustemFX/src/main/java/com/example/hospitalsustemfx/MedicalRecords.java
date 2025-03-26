package com.example.hospitalsustemfx;

public class MedicalRecords {


        // attributes  of the medical record

        private String recordId;
        private String patientName;
        private String doctorName;
        private String diagnosis;
        private String treatment;
        private String NHSNumber;



        /**
         * Constructor initializes the details of the medical record
         * @param recordId:    ID of the record
         * @param patientName: name of the patient
         * @param doctorName:  name of the doctor
         * @param diagnosis:   diagnosis of the patient
         * @param treatment:   treatment of the patient
         * @param NHSNumber:   NHS number of the patient
         */

       // Constructor
        public MedicalRecords(String recordId, String patientName, String doctorName, String diagnosis, String treatment, String NHSNumber) {
            this.recordId = recordId;
             this.patientName = patientName;
             this.doctorName = doctorName;
            this.diagnosis = diagnosis;
            this.treatment = treatment;
            this.NHSNumber = NHSNumber;
        }



    // Getters and setters for each field

        public String getRecordId() {
            return recordId;
        }

        public String getNHSNumber() {
            return NHSNumber;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
           this.patientName = patientName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public String getTreatment() {
            return treatment;
        }




          // toString method to print the medical record details
        @Override
        public String toString() {
            return "MedicalRecord{" +
                    "recordId='" + recordId + '\'' +
                    ", patientName='" + patientName + '\'' +
                    ", doctorName='" + doctorName + '\'' +
                    ", diagnosis='" + diagnosis + '\'' +
                    ", treatment='" + treatment + '\'' +
                    ", NHSNumber='" + NHSNumber + '\'' +
                    '}';
        }
    }