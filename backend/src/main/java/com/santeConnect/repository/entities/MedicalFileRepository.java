package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path="medical-files")
public interface MedicalFileRepository extends CrudRepository<MedicalFile, Long> {

    // findBy returns List usually, but insuranceNumber is unique so Optional is fine
    Optional<MedicalFile> findByInsuranceNumber(@Param("insuranceNumber") String insuranceNumber);

    List<MedicalFile> findAll();
    Optional<MedicalFile> findById(Long id);

    @SuppressWarnings("unchecked")
    MedicalFile save(MedicalFile file);
}
