package com.santeConnect;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.Patient;
import com.santeConnect.domain.users.AppUser;
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

@SpringBootApplication
@EnableMethodSecurity
public class SanteConnectApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SanteConnectApplication.class);

	private final MedicalFileRepository medicalFileRepository;

	private final PatientRepository patientRepository;

	private final AppUserRepository appUserRepository;

	public SanteConnectApplication(MedicalFileRepository medicalFileRepository,
								   PatientRepository patientRepository,
								   AppUserRepository appUserRepository) {
		this.medicalFileRepository = medicalFileRepository;
		this.patientRepository = patientRepository;
		this.appUserRepository = appUserRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SanteConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Patient patient1 = new Patient();
		patient1.setLastName("Doe");
		patient1.setFirstName("John");
		patient1.setDateOfBirth("1990-01-01");
		patient1.setGender("Male");
		patient1.setKnownParent1("Jane Doe");
		patient1.setKnownParent2("Joe Doe");
		patient1.setCityOfBirth("New York");

		Patient patient2 = new Patient();
		patient2.setLastName("Smith");
		patient2.setFirstName("Jane");
		patient2.setDateOfBirth("1992-02-02");
		patient2.setGender("Female");
		patient2.setKnownParent1("John Smith");
		patient2.setKnownParent2("Jill Smith");
		patient2.setCityOfBirth("Los Angeles");
		patientRepository.saveAll(Arrays.asList(patient1, patient2));

		MedicalFile medicalFile1 = new MedicalFile();
		medicalFile1.setInsuranceNumber("ABCD12345678");
		medicalFile1.setPatient(patient1);

		medicalFileRepository.save(medicalFile1);

		MedicalFile medicalFile2 = new MedicalFile();
		medicalFile2.setInsuranceNumber("BCDE12345678");
		medicalFile2.setPatient(patient2);

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