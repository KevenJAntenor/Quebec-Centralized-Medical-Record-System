package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.entities.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTest {
    @Autowired
    private PatientRepository repository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Test
    void savePatient() {
        Coordinate coordinate = new Coordinate("123, Rue Saint-Estauche, Montréal", "5141231231", "5143456345", "alice@gmail.com");

        coordinateRepository.save(coordinate);

        Patient patient = new Patient("Smiths", "Alice", "1985-12-01", "Female", "Robert Smith", "Susan Smith", "Los Angeles", coordinate);

        repository.save(patient);
        assertThat(repository.findByFirstName("Alice").isPresent()).isTrue();
        assertThat(repository.findByFirstName("Random").isEmpty()).isTrue();
    }

    @Test
    void deletePatient() {
        Coordinate coordinate = new Coordinate("123, Rue Saint-Estauche, Montréal", "5141231231", "5143456345", "alice@gmail.com");
        coordinateRepository.save(coordinate);
        Patient patient = new Patient("Smiths", "Alice", "1985-12-01", "Female", "Robert Smith", "Susan Smith", "Los Angeles", coordinate);

        repository.save(patient);
        repository.delete(patient);
        assertThat(repository.findByFirstName("Alice").isEmpty()).isTrue();
    }


}
