package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//@RepositoryRestResource(path="ToChangeNameInRestApi")
public interface MedicalFileRepository extends CrudRepository<MedicalFile, Long> {
    Optional<MedicalFile> findMedicalFileByInsuranceNumber(@Param("insurance_number") String insuranceNumber);
}
