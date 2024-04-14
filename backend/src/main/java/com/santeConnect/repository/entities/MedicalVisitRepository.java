package com.santeConnect.repository.entities;

import com.santeConnect.domain.entities.MedicalVisit;
import org.springframework.data.repository.CrudRepository;
import com.santeConnect.domain.entities.MedicalFile;
import java.util.List;


public interface MedicalVisitRepository extends CrudRepository<MedicalVisit, Long> {
    List<MedicalVisit> findByMedicalFile(MedicalFile medicalFile);
}
