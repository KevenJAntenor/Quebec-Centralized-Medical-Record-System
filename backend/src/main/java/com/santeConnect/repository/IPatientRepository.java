package com.santeConnect.repository;

import com.santeConnect.domain.entities.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface IPatientRepository extends CrudRepository<Patient, Long> {
}