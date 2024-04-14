package com.santeConnect;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.domain.entities.MedicalHistory;
import com.santeConnect.domain.entities.Patient;
import com.santeConnect.domain.users.AppUser;
import com.santeConnect.repository.entities.*;
import com.santeConnect.repository.users.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableMethodSecurity
public class SanteConnectApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SanteConnectApplication.class);

    private final MedicalFileRepository medicalFileRepository;

    private final PatientRepository patientRepository;

    private final CoordinateRepository coordinateRepository;

    private final MedicalVisitRepository medicalVisitRepository;

    private final MedicalHistoryRepository medicalHistoryRepository;

    private final AppUserRepository appUserRepository;

    public SanteConnectApplication(MedicalFileRepository medicalFileRepository,
            PatientRepository patientRepository,
            CoordinateRepository coordinateRepository,
            MedicalVisitRepository medicalVisitRepository,
            AppUserRepository appUserRepository,
            MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalFileRepository = medicalFileRepository;
        this.patientRepository = patientRepository;
        this.coordinateRepository = coordinateRepository;
        this.medicalVisitRepository = medicalVisitRepository;
        this.appUserRepository = appUserRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SanteConnectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        MedicalVisit visit1 = new MedicalVisit("Establishment 1", "Doctor 1", formatter.parse("2022-01-01"),
                "Diagnostic 1",
                "Treatment 1", "Summary 1", "Notes 1");
        MedicalVisit visit2 = new MedicalVisit("Establishment 2", "Doctor 2", formatter.parse("2022-02-01"),
                "Diagnostic 2",
                "Treatment 2", "Summary 2", "Notes 2");
        MedicalVisit visit3 = new MedicalVisit("Establishment 3", "Doctor 3", formatter.parse("2022-03-01"),
                "Diagnostic 3",
                "Treatment 3", "Summary 3", "Notes 3");
        MedicalVisit visit4 = new MedicalVisit("Establishment 4", "Doctor 4", formatter.parse("2022-04-01"),
                "Diagnostic 4",
                "Treatment 4", "Summary 4", "Notes 4");

        MedicalHistory history1 = new MedicalHistory("Diagnostic 1", "Treatment 1", "Doctor 1",
                formatter.parse("2022-01-01"), formatter.parse("2022-01-31"));
        MedicalHistory history2 = new MedicalHistory("Diagnostic 2", "Treatment 2", "Doctor 2",
                formatter.parse("2022-02-01"), formatter.parse("2022-02-28"));
        MedicalHistory history3 = new MedicalHistory("Diagnostic 3", "Treatment 3", "Doctor 3",
                formatter.parse("2022-03-01"), formatter.parse("2022-03-31"));
        MedicalHistory history4 = new MedicalHistory("Diagnostic 4", "Treatment 4", "Doctor 4",
                formatter.parse("2022-04-01"), formatter.parse("2022-04-30"));

        Coordinate coordinate1 = new Coordinate();
        coordinate1.setAddress("2020 Rue Saint-Urbain, Montréal, QC H2X 4E1, Canada");
        coordinate1.setPersonalPhoneNumber("+1(514)123-1234");
        coordinate1.setWorkPhoneNumber("+1(438)123-4567");
        coordinate1.setEmail("john-doe@uqam.com");

        Coordinate coordinate2 = new Coordinate();
        coordinate2.setAddress("1234 Rue Saint-Denis, Montréal, QC H2X 3J6, Canada");
        coordinate2.setPersonalPhoneNumber("+1(514)234-5678");
        coordinate2.setWorkPhoneNumber("+1(438)234-5678");
        coordinate2.setEmail("jane-smith@uqam.com");

        Coordinate coordinate3 = new Coordinate();
        coordinate3.setAddress("1234 Rue Saint-Denis, Montréal, QC H2X 3J6, Canada");
        coordinate3.setPersonalPhoneNumber("+1(514)234-5678");
        coordinate3.setWorkPhoneNumber("+1(438)234-5678");
        coordinate3.setEmail("test@tea.com");

        Coordinate coordinate4 = new Coordinate();
        coordinate4.setAddress("1234 Rue Saint-Denis, Montréal, QC H2X 3J6, Canada");
        coordinate4.setPersonalPhoneNumber("+1(514)234-5678");
        coordinate4.setWorkPhoneNumber("+1(438)234-5678");
        coordinate4.setEmail("email@gmail.coim");

        Coordinate coordinate5 = new Coordinate();

        Coordinate coordinate6 = new Coordinate();

        Coordinate coordinate7 = new Coordinate();

        Coordinate coordinate8 = new Coordinate();

        Coordinate coordinate9 = new Coordinate();

        Coordinate coordinate10 = new Coordinate();

        Coordinate coordinate11 = new Coordinate();

        Coordinate coordinate12 = new Coordinate();

        Coordinate coordinate13 = new Coordinate();

        coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2));
        coordinateRepository.saveAll(Arrays.asList(coordinate3, coordinate4));
        coordinateRepository.saveAll(Arrays.asList(coordinate5, coordinate6));
        coordinateRepository.saveAll(Arrays.asList(coordinate7, coordinate8));
        coordinateRepository.saveAll(Arrays.asList(coordinate9, coordinate10));
        coordinateRepository.saveAll(Arrays.asList(coordinate11, coordinate12));
        coordinateRepository.save(coordinate13);

        Patient patient1 = new Patient();
        patient1.setLastName("Doe");
        patient1.setFirstName("John");
        patient1.setDateOfBirth("1990-01-01");
        patient1.setGender("Male");
        patient1.setKnownParent1("Jane Doe");
        patient1.setKnownParent2("Joe Doe");
        patient1.setCityOfBirth("New York");
        patient1.setCoordinate(coordinate1);

        Patient patient2 = new Patient();
        patient2.setLastName("Smith");
        patient2.setFirstName("Jane");
        patient2.setDateOfBirth("1992-02-02");
        patient2.setGender("Female");
        patient2.setKnownParent1("John Smith");
        patient2.setKnownParent2("Jill Smith");
        patient2.setCityOfBirth("Los Angeles");
        patient2.setCoordinate(coordinate2);

        Patient patient3 = new Patient();
        patient3.setLastName("Doe");
        patient3.setCoordinate(coordinate3);

        Patient patient4 = new Patient();
        patient4.setLastName("Smith");
        patient4.setCoordinate(coordinate4);

        Patient patient5 = new Patient();
        patient5.setLastName("Doe");
        patient5.setCoordinate(coordinate5);

        Patient patient6 = new Patient();
        patient6.setLastName("Smith");
        patient6.setCoordinate(coordinate6);

        Patient patient7 = new Patient();
        patient7.setLastName("Doe");
        patient7.setCoordinate(coordinate7);

        Patient patient8 = new Patient();
        patient8.setLastName("Smith");
        patient8.setCoordinate(coordinate8);

        Patient patient9 = new Patient();
        patient9.setLastName("Doe");
        patient9.setCoordinate(coordinate9);

        Patient patient10 = new Patient();
        patient10.setLastName("Smith");
        patient10.setCoordinate(coordinate10);

        Patient patient11 = new Patient();
        patient11.setLastName("Doe");
        patient11.setCoordinate(coordinate11);

        Patient patient12 = new Patient();
        patient12.setLastName("Smith");
        patient12.setCoordinate(coordinate12);

        Patient patient13 = new Patient();
        patient13.setLastName("Doe");
        patient13.setCoordinate(coordinate13);

        patientRepository.saveAll(Arrays.asList(patient1, patient2));
        patientRepository.saveAll(Arrays.asList(patient3, patient4));
        patientRepository.saveAll(Arrays.asList(patient5, patient6));
        patientRepository.saveAll(Arrays.asList(patient7, patient8));
        patientRepository.saveAll(Arrays.asList(patient9, patient10));
        patientRepository.saveAll(Arrays.asList(patient11, patient12));
        patientRepository.save(patient13);

        MedicalFile medicalFile1 = new MedicalFile();
        medicalFile1.setInsuranceNumber("ABCD12345678");
        medicalFile1.setPatient(patient1);

        MedicalFile medicalFile2 = new MedicalFile();
        medicalFile2.setInsuranceNumber("BCDE12345678");
        medicalFile2.setPatient(patient2);

        visit1.setMedicalFile(medicalFile1);
        visit2.setMedicalFile(medicalFile1);
        visit3.setMedicalFile(medicalFile2);
        visit4.setMedicalFile(medicalFile2);

        history1.setMedicalFile(medicalFile1);
        history2.setMedicalFile(medicalFile1);
        history3.setMedicalFile(medicalFile2);
        history4.setMedicalFile(medicalFile2);

        List<MedicalVisit> medicalVisits1 = Arrays.asList(visit1, visit2);
        List<MedicalVisit> medicalVisits2 = Arrays.asList(visit3, visit4);

        List<MedicalHistory> medicalHistories1 = Arrays.asList(history1, history2);
        List<MedicalHistory> medicalHistories2 = Arrays.asList(history3, history4);

        medicalFile1.setMedicalVisitList(medicalVisits1);
        medicalFile2.setMedicalVisitList(medicalVisits2);

        medicalFile1.setMedicalHistoryList(medicalHistories1);
        medicalFile2.setMedicalHistoryList(medicalHistories2);

        MedicalFile medicalFile3 = new MedicalFile();
        medicalFile3.setInsuranceNumber("CDEF12345678");
        medicalFile3.setPatient(patient3);
        MedicalFile medicalFile4 = new MedicalFile();
        medicalFile4.setInsuranceNumber("DEFG12345678");
        medicalFile4.setPatient(patient4);
        MedicalFile medicalFile5 = new MedicalFile();
        medicalFile5.setInsuranceNumber("EFGH12345678");
        medicalFile5.setPatient(patient5);
        MedicalFile medicalFile6 = new MedicalFile();
        medicalFile6.setInsuranceNumber("FGHI12345678");
        medicalFile6.setPatient(patient6);
        MedicalFile medicalFile7 = new MedicalFile();
        medicalFile7.setInsuranceNumber("GHIJ12345678");
        medicalFile7.setPatient(patient7);
        MedicalFile medicalFile8 = new MedicalFile();
        medicalFile8.setInsuranceNumber("HIJK12345678");
        medicalFile8.setPatient(patient8);
        MedicalFile medicalFile9 = new MedicalFile();
        medicalFile9.setInsuranceNumber("IJKL12345678");
        medicalFile9.setPatient(patient9);
        MedicalFile medicalFile10 = new MedicalFile();
        medicalFile10.setInsuranceNumber("JKLM12345678");
        medicalFile10.setPatient(patient10);
        MedicalFile medicalFile11 = new MedicalFile();
        medicalFile11.setInsuranceNumber("KLMN12345678");
        medicalFile11.setPatient(patient11);
        MedicalFile medicalFile12 = new MedicalFile();
        medicalFile12.setInsuranceNumber("LMNO12345678");
        medicalFile12.setPatient(patient12);
        MedicalFile medicalFile13 = new MedicalFile();
        medicalFile13.setInsuranceNumber("MNOP12345678");
        medicalFile13.setPatient(patient13);

        medicalFileRepository.save(medicalFile3);
        medicalFileRepository.save(medicalFile4);
        medicalFileRepository.save(medicalFile5);
        medicalFileRepository.save(medicalFile6);
        medicalFileRepository.save(medicalFile7);
        medicalFileRepository.save(medicalFile8);
        medicalFileRepository.save(medicalFile9);
        medicalFileRepository.save(medicalFile10);
        medicalFileRepository.save(medicalFile11);
        medicalFileRepository.save(medicalFile12);
        medicalFileRepository.save(medicalFile13);

        medicalFileRepository.save(medicalFile1);
        medicalFileRepository.save(medicalFile2);

        medicalVisitRepository.saveAll(medicalVisits1);
        medicalVisitRepository.saveAll(medicalVisits2);

        medicalHistoryRepository.saveAll(medicalHistories1);
        medicalHistoryRepository.saveAll(medicalHistories2);

        // Fetch all medical Files and log to console
        for (MedicalFile medicalFile : medicalFileRepository.findAll()) {
            logger.info("Medical File ID: {}, Insurance Number: {}, Patient ID: {}, Patient Name: {} {}",
                    medicalFile.getId(), medicalFile.getInsuranceNumber(), medicalFile.getPatient().getId(),
                    medicalFile.getPatient().getFirstName(), medicalFile.getPatient().getLastName());
        }

        // Username user, password: user
        appUserRepository.save(new AppUser("user",
                "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue",
                "USER"));

        appUserRepository.save(new AppUser("admin",
                "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW",
                "ADMIN"));
    }
}
