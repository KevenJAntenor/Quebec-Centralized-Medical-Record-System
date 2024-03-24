package com.santeConnect;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.domain.entities.Patient;
import com.santeConnect.domain.users.AppUser;
import com.santeConnect.repository.entities.CoordinateRepository;
import com.santeConnect.repository.entities.MedicalVisitRepository;
import com.santeConnect.repository.entities.PatientRepository;
import com.santeConnect.repository.entities.MedicalFileRepository;
import com.santeConnect.repository.users.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMethodSecurity
public class SanteConnectApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SanteConnectApplication.class);

	private final MedicalFileRepository medicalFileRepository;

	private final PatientRepository patientRepository;

	private final CoordinateRepository coordinateRepository;

	private final MedicalVisitRepository medicalVisitRepository;

	private final AppUserRepository appUserRepository;

	public SanteConnectApplication(MedicalFileRepository medicalFileRepository,
								   PatientRepository patientRepository,
								   CoordinateRepository coordinateRepository,
								   MedicalVisitRepository medicalVisitRepository,
								   AppUserRepository appUserRepository) {
		this.medicalFileRepository = medicalFileRepository;
		this.patientRepository = patientRepository;
		this.coordinateRepository = coordinateRepository;
		this.medicalVisitRepository = medicalVisitRepository;
		this.appUserRepository = appUserRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SanteConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MedicalVisit visit1 = new MedicalVisit("Establishment 1", "Doctor 1", "2022-01-01", "Diagnostic 1", "Treatment 1", "Summary 1", "Notes 1");
		MedicalVisit visit2 = new MedicalVisit("Establishment 2", "Doctor 2", "2022-02-01", "Diagnostic 2", "Treatment 2", "Summary 2", "Notes 2");
		MedicalVisit visit3 = new MedicalVisit("Establishment 3", "Doctor 3", "2022-03-01", "Diagnostic 3", "Treatment 3", "Summary 3", "Notes 3");
		MedicalVisit visit4 = new MedicalVisit("Establishment 4", "Doctor 4", "2022-04-01", "Diagnostic 4", "Treatment 4", "Summary 4", "Notes 4");

		List<MedicalVisit> medicalVisits1 = Arrays.asList(visit1, visit2);
		List<MedicalVisit> medicalVisits2 = Arrays.asList(visit3, visit4);

		medicalVisitRepository.saveAll(medicalVisits1);
		medicalVisitRepository.saveAll(medicalVisits2);

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

		coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2));

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

		patientRepository.saveAll(Arrays.asList(patient1, patient2));

		MedicalFile medicalFile1 = new MedicalFile();
		medicalFile1.setInsuranceNumber("ABCD12345678");
		medicalFile1.setPatient(patient1);
		medicalFile1.setMedicalVisitList(medicalVisits1);

		medicalFileRepository.save(medicalFile1);

		MedicalFile medicalFile2 = new MedicalFile();
		medicalFile2.setInsuranceNumber("BCDE12345678");
		medicalFile2.setPatient(patient2);
		medicalFile2.setMedicalVisitList(medicalVisits2);

		medicalFileRepository.save(medicalFile2);

//		 Fetch all cars and log to console
		for (MedicalFile medicalFile : medicalFileRepository.findAll()) {
			logger.info("Medical File ID: {}, Insurance Number: {}, Patient ID: {}, Patient Name: {} {}", medicalFile.getId(), medicalFile.getInsuranceNumber(), medicalFile.getPatient().getId(), medicalFile.getPatient().getFirstName(), medicalFile.getPatient().getLastName());
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