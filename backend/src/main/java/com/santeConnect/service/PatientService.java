package com.santeConnect.service;

import com.santeConnect.domain.entities.Patient;
import com.santeConnect.domain.exception.InvalidCoordinateException;
import com.santeConnect.domain.strategy.CoordinateModificationStrategy;
import com.santeConnect.repository.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public void updatePatientCoordinates(Patient patient, CoordinateModificationStrategy modificationStrategy) {
        var coordinates = patient.getCoordinate();
        try {
            modificationStrategy.modify(coordinates);
        } catch (InvalidCoordinateException e) {
            return;
        }
        patient.setCoordinate(coordinates);
        repository.save(patient);
    }

}
