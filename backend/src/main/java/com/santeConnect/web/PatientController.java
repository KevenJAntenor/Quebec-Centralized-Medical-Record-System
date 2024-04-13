package com.santeConnect.web;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.entities.Patient;
import com.santeConnect.domain.strategy.AdressModificationStrategy;
import com.santeConnect.domain.strategy.CoordinateModification;
import com.santeConnect.domain.strategy.EmailModificationStrategy;
import com.santeConnect.domain.strategy.PersonalPhoneModificationStrategy;
import com.santeConnect.domain.strategy.WorkPhoneModificationStrategy;
import com.santeConnect.repository.entities.PatientRepository;

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
        Patient patientFound = patient.get();
        Coordinate patientCoordinates = patientFound.getCoordinate();
        if (StringUtils.hasText(workPhone)) {
            modification.setStrategy(new WorkPhoneModificationStrategy(workPhone));
            modification.execute(patientCoordinates);
        }
        if (StringUtils.hasText(personalPhone)) {
            modification.setStrategy(new PersonalPhoneModificationStrategy(personalPhone));
            modification.execute(patientCoordinates);
        }
        if (StringUtils.hasText(email)) {
            modification.setStrategy(new EmailModificationStrategy(email));
            modification.execute(patientCoordinates);
        }
        if (StringUtils.hasText(address)) {
            modification.setStrategy(new AdressModificationStrategy(address));
            modification.execute(patientCoordinates);
        }
        patientFound.setCoordinate(patientCoordinates);
        repository.save(patientFound);
        return ResponseEntity.ok().build();
    }
}
