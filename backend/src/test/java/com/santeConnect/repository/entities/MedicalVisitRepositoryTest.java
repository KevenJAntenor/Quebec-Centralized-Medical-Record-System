package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MedicalVisitRepositoryTest {

    private List<MedicalVisit> medicalVisitList;
    private MedicalVisit medicalVisit;
    private MedicalFile medicalFile;
    private String insuranceNumber1;

    @Autowired
    private MedicalVisitRepository repository;

    @Autowired
    private MedicalFileRepository medicalFileRepository;

    @BeforeEach
    void setUpObjects() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        repository.deleteAll();
        medicalFileRepository.deleteAll();
        insuranceNumber1 = "ASOW12345678";
        medicalFile = new MedicalFile();
        medicalFile.setInsuranceNumber(insuranceNumber1);
        medicalVisit = new MedicalVisit();
        medicalVisit.setEstablishment("St-estach hospital");
        medicalVisit.setDoctor("Dr. John Doe");
        try {
            medicalVisit.setDateOfVisit(formatter.parse("2022-01-01"));
        } catch (ParseException e) {
        }
        medicalVisit.setDiagnostic("Diagnostic");
        medicalVisit.setTreatment("Treatment");
        medicalVisit.setSummary("Summary");
        medicalVisit.setNotes("Notes");

        medicalVisitList = new ArrayList<>();
        medicalVisitList.add(medicalVisit);

        medicalFile.setMedicalVisitList(medicalVisitList);
        medicalFileRepository.save(medicalFile);
        medicalVisit.setMedicalFile(medicalFile);
        repository.save(medicalVisit);

    }

    @Test
    void saveMedicalVisit() {
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void assertMedicalFileHasMedicalVisit() {
        assertThat(medicalFileRepository.findByInsuranceNumber(insuranceNumber1).isPresent()).isTrue();
        assertThat(medicalFileRepository.findByInsuranceNumber(insuranceNumber1).get().getMedicalVisitList()
                .size()).isEqualTo(1);
    }

    @Test
    void assertMedicalVisitHasMedicalFile() {
        assertThat(repository.findById(medicalVisit.getId()).get().getMedicalFile()).isEqualTo(medicalFile);
        assertThat(repository.findById(medicalVisit.getId()).get().getMedicalFile().getInsuranceNumber())
                .isEqualTo(insuranceNumber1);
    }

}
