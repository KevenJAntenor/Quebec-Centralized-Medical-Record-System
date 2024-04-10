package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.domain.strategy.AdressModificationStrategy;
import com.santeConnect.domain.strategy.CoordinateModification;
import com.santeConnect.domain.strategy.EmailModificationStrategy;
import com.santeConnect.domain.strategy.PersonalPhoneModificationStrategy;
import com.santeConnect.domain.strategy.WorkPhoneModificationStrategy;
import com.santeConnect.middlewares.FetchMedicalFile;
import com.santeConnect.middlewares.MedicalFileOperation;
import com.santeConnect.middlewares.RemoveMedicalVisit;
import com.santeConnect.middlewares.UpdateMedicalVisit;
import com.santeConnect.repository.entities.MedicalFileRepository;
import com.santeConnect.repository.entities.PatientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PutMapping("coordinate/{id}")
    public ResponseEntity<String> changeCoordinates(@PathVariable Long id, @RequestParam String workPhone,
            @RequestParam String personalPhone, @RequestParam String email, @RequestParam String address) {
        var patient = repository.findById(id);
        if (!patient.isPresent())
            return ResponseEntity.status(403).build();

        CoordinateModification modification = new CoordinateModification();
        if (StringUtils.hasText(workPhone)) {
            modification.setStrategy(new WorkPhoneModificationStrategy(workPhone));
            modification.execute(patient.get().getCoordinate());
        }
        if (StringUtils.hasText(personalPhone)) {
            modification.setStrategy(new PersonalPhoneModificationStrategy(personalPhone));
            modification.execute(patient.get().getCoordinate());
        }
        if (StringUtils.hasText(email)) {
            modification.setStrategy(new EmailModificationStrategy(email));
            modification.execute(patient.get().getCoordinate());
        }
        if (StringUtils.hasText(address)) {
            modification.setStrategy(new AdressModificationStrategy(address));
            modification.execute(patient.get().getCoordinate());
        }
        return ResponseEntity.ok().build();
    }
}
