package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CoordinateRepositoryTest {

    private Coordinate coordinate;
    private Long id;

    @Autowired
    private CoordinateRepository repository;

    @BeforeEach
    void setUpObjects() {
        // repository.deleteAll();
        coordinate = new Coordinate("123, Rue Saint-Estauche, Montréal", "5141231231", "5143456345", "alice@gmail.com");
        repository.save(coordinate);
        id = coordinate.getId();
    }

    @Test
    void saveCoordinate() {

        assertThat(repository.existsById(id)).isTrue();
        assertThat(repository.findById(id).get()).isEqualTo(coordinate);
    }

    @Test
    void deletePatient() {
        repository.delete(coordinate);
        assertThat(repository.existsById(id)).isFalse();
    }

}
