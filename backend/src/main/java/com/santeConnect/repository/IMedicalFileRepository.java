package com.santeConnect.repository;

import com.santeConnect.domain.entities.MedicalFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(path="ToChangeNameInRestApi")
//@Repository
public interface IMedicalFileRepository extends CrudRepository<MedicalFile, Long> {

    List<MedicalFile> findMedicalFileByInsuranceNumber(@Param("insurance_number") String insuranceNumber);
}
