package com.santeConnect;

import com.santeConnect.repository.IPatientRepository;
import lombok.extern.slf4j.Slf4j;
import com.santeConnect.repository.IMedicalFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SanteConnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SanteConnectApplication.class, args);
	}
	@Autowired
	private IMedicalFileRepository medicalFileRepository;

	@Autowired
	private IPatientRepository patientRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {
			medicalFileRepository.findAll().forEach(user -> {
				log.info(user.toString());
			});
		};
	}
}
