package com.santeConnect.repository.patient;

import com.santeConnect.domain.entities.MedicalVisit;
import org.springframework.data.repository.CrudRepository;

public interface MedicalVisitRepository extends CrudRepository<MedicalVisit, Long> {
}
