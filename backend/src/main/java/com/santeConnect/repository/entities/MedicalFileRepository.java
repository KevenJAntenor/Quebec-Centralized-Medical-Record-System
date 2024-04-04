package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalFile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path="medical-files")
public interface MedicalFileRepository extends CrudRepository<MedicalFile, Long> {

    // findBy returns List usually, but insuranceNumber is unique so Optional is fine
    Optional<MedicalFile> findByInsuranceNumber(@Param("insuranceNumber") String insuranceNumber);

    // Using query because we are looking inside patient, no medical file
    @Query("SELECT m FROM MedicalFile m WHERE CONCAT(m.patient.firstName, ' ', m.patient.lastName) LIKE %:fullName%")
    List<MedicalFile> findByPatientFullName(@Param("fullName") String fullName);

    // Temporary method to get all medical files 
    // should use prSpring Data Rest to expose the repository
    List<MedicalFile> findAll();
    Optional<MedicalFile> findById(Long id);

    // TODO why is this here?
    // @SuppressWarnings("unchecked")
    // MedicalFile save(MedicalFile file);
}
