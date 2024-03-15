package com.santeConnect.domain;

public class MedicalFile {

    private String insuranceNumber;
    private Patient patient;

    public MedicalFile(String insuranceNumber, Patient patient) {
        this.insuranceNumber = insuranceNumber;
        this.patient = patient;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
