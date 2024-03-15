package com.santeConnect.adapter.api;

import com.santeConnect.domain.MedicalFile;
import com.santeConnect.domain.MedicalFileRepo;
import com.santeConnect.domain.Patient;

import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MedicalFileRepoSqlite implements MedicalFileRepo {

    private static final String SQL_INSERT_NEW_PATIENT 
            = "insert into patient (last_name, first_name) values (:last_name, :first_name)";

    private static final String SQL_INSERT_NEW_MEDICAL_FILE 
            = "insert into medical_file (insurance_number, patient_id) values (:insurance_number, :patient_id)";

    private static final String SQL_FIND_MEDICAL_FILE_FOR_INSURANCE_NUMBER
            = "select * from medical_file where insurance_number=:insurance_number ";

    private final Jdbi jdbi;

    public MedicalFileRepoSqlite(DataSource dataSource) {
        jdbi = Jdbi.create(dataSource);
    }

    @Override
    public void create(MedicalFile medicalFile) {
        int patientId = createPatient(medicalFile.getPatient());
        jdbi.useHandle(handle -> {
            handle.createUpdate(SQL_INSERT_NEW_MEDICAL_FILE)
                    .bind("insurance_number", medicalFile.getInsuranceNumber())
                    .bind("patient_id", patientId)
                    .execute();
        });
    }

    // Only public for testing TODO: change to package private
    public int createPatient(Patient patient) {
        return jdbi.withHandle(handle -> {
            // Insert the patient and get the generated ID
            return handle.createUpdate(SQL_INSERT_NEW_PATIENT)
                    .bind("last_name", patient.getLastName())
                    .bind("first_name", patient.getFirstName())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(int.class)
                    .one();
        });
    }

    // @Override
    // public Optional<MedicalFile> getFromInsuranceNumber(String insuranceNumber) {
    // // return Optional.empty();
    // return jdbi.withHandle(handle -> {
    // return handle.createQuery("select * from medical_file where insurance_number
    // = :insurance_number")
    // .bind("insurance_number", insuranceNumber)
    // .mapTo(MedicalFile.class)
    // .findFirst();
    // });
    // }

    /**
     * @param insuranceNumber - assumes insuranceNumber is unique in this
     *                        implementation
     *                        One insurance number can have one active medical file
     *                        at any time
     */
    // @Override
    // public Optional<MedicalFile> getFromInsuranceNumber(String insuranceNumber) {
    //     return jdbi.withHandle(handle -> {
    //         Optional<MedicalFile> medicalFile = handle
    //                 .createQuery("select * from medical_file where insurance_number = :insuranceNumber")
    //                 .bind("insuranceNumber", insuranceNumber)
    //                 .map((rs, ctx) -> new MedicalFile(rs.getString("insurance_number"), null))
    //                 .findFirst();
    
    //         return medicalFile;
    //     });
    // }

    @Override
    public Optional<MedicalFile> getFromInsuranceNumber(String insuranceNumber) {
        return jdbi.withHandle(handle -> {
            Optional<MedicalFile> medicalFile = handle
                    .createQuery("select * from medical_file where insurance_number = :insuranceNumber")
                    .bind("insuranceNumber", insuranceNumber)
                    .map((rs, ctx) -> {
                        int patientId = rs.getInt("patient_id");
                        Patient patient = getPatient(patientId);
                        return new MedicalFile(rs.getString("insurance_number"), patient);
                    })
                    .findFirst();
    
            return medicalFile;
        });
    }

    private Patient getPatient(int patientId) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("select * from patient where id = :patientId")
                    .bind("patientId", patientId)
                    .map((rs, ctx) -> new Patient(rs.getString("last_name"), rs.getString("first_name")))
                    .findOne()
                    .orElse(null);
        });
    }

}
