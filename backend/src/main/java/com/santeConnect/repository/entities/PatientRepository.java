package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByFirstName(@Param("first_name")String firstName);
}