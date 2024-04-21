package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MedicalHistoryRepositoryTest {

    private List<MedicalHistory> medicalHistoryList;
    private MedicalHistory medicalHistory;
    private MedicalFile medicalFile;
    private String insuranceNumber1;

    @Autowired
    private MedicalHistoryRepository repository;

    @Autowired
    private MedicalFileRepository medicalFileRepository;

    @BeforeEach
    void setUpObjects() {
        repository.deleteAll();
        medicalFileRepository.deleteAll();
        insuranceNumber1 = "ASOW12345678";
        medicalFile = new MedicalFile();
        medicalFile.setInsuranceNumber(insuranceNumber1);
        medicalHistory = new MedicalHistory();
        medicalHistoryList = new ArrayList<>();
        medicalHistoryList.add(medicalHistory);

        medicalFile.setMedicalHistoryList(medicalHistoryList);
        medicalFileRepository.save(medicalFile);
        medicalHistory.setMedicalFile(medicalFile);
        repository.save(medicalHistory);

    }

    @Test
    void saveMedicalVisit() {
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void assertMedicalFileHasMedicalVisit() {
        assertThat(medicalFileRepository.findByInsuranceNumber(insuranceNumber1).isPresent()).isTrue();
    }

    @Test
    void assertMedicalVisitHasMedicalFile() {
        assertThat(repository.findById(medicalHistory.getId()).get().getMedicalFile()).isEqualTo(medicalFile);
        assertThat(repository.findById(medicalHistory.getId()).get().getMedicalFile().getInsuranceNumber())
                .isEqualTo(insuranceNumber1);
    }

}