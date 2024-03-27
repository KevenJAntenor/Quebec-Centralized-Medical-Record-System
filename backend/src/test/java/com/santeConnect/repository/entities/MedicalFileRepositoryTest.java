package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MedicalFileRepositoryTest {

    private Coordinate coordinate;
    private Patient patient;
    private MedicalFile medicalFile;
    private String insuranceNumber1;
    private String insuranceNumber2;

    @Autowired
    private MedicalFileRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @BeforeEach
    void setUpObjects() {
        repository.deleteAll();
        patientRepository.deleteAll();
        coordinateRepository.deleteAll();
        coordinate = new Coordinate("123, Rue Saint-Estauche, Montréal", "5141231231", "5143456345", "alice@gmail.com");
        patient = new Patient("Smiths", "Alice", "1985-12-01", "Female", "Robert Smith", "Susan Smith", "Los Angeles",
                coordinate);
        insuranceNumber1 = "ASOW12345678";
        insuranceNumber2 = "ADWA12341234";
        medicalFile = new MedicalFile(insuranceNumber1, patient);

        coordinateRepository.save(coordinate);
        patientRepository.save(patient);
        repository.save(medicalFile);
    }

    @Test
    void saveMedialFileWithoutPatient() {
        repository.deleteAll();
        MedicalFile medicalFile = new MedicalFile(insuranceNumber2, null);
        repository.save(medicalFile);
        assertThat(repository.findMedicalFileByInsuranceNumber(insuranceNumber2).isPresent()).isTrue();
        assertThat(repository.findMedicalFileByInsuranceNumber(insuranceNumber2).get().getPatient()).isNull();
    }

    @Test
    void saveMedicalFileWithPatient() {
        assertThat(repository.findMedicalFileByInsuranceNumber(insuranceNumber1).isPresent()).isTrue();
        assertThat(repository.findMedicalFileByInsuranceNumber(insuranceNumber1).get().getPatient()).isEqualTo(patient);
    }

    @Test
    void deleteMedicalFile() {
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }

}
