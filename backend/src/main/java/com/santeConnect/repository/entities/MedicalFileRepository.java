package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicalFileRepository extends CrudRepository<MedicalFile, Long> {

    Optional<MedicalFile> findMedicalFileByInsuranceNumber(@Param("insurance_number") String insuranceNumber);

    List<MedicalFile> findAll();

    Optional<MedicalFile> findById(Long id);

    @SuppressWarnings("unchecked")
    MedicalFile save(MedicalFile file);
}
