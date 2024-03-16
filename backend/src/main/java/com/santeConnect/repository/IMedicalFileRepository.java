package com.santeConnect.repository;

import com.santeConnect.domain.entities.MedicalFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalFileRepository extends CrudRepository<MedicalFile, Long> {
}
