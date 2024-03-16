package com.santeConnect;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.Patient;
import com.santeConnect.repository.PatientRepository;
import com.santeConnect.repository.MedicalFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SanteConnectApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SanteConnectApplication.class);

	private MedicalFileRepository medicalFileRepository;

	private PatientRepository patientRepository;

	public SanteConnectApplication(MedicalFileRepository medicalFileRepository, PatientRepository patientRepository) {
		this.medicalFileRepository = medicalFileRepository;
		this.patientRepository = patientRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SanteConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add owner objects and save these to db
// Create the first Patient object
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

	}
}