package com.santeConnect.repository;

import com.santeConnect.domain.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}