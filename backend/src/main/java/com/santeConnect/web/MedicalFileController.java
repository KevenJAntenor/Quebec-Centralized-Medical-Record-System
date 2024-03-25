package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.repository.entities.MedicalFileRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalFileController {

    private final MedicalFileRepository repository;

    public MedicalFileController(MedicalFileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("medical-files")
    public Iterable<MedicalFile> getMedicalFiles() {
        return repository.findAll();
    }

    @GetMapping("medical-files/{id}")
    public MedicalFile getMedicalFile(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalFile not found with id " + id));
    }

    // post route to add new medical visit to a medical file
    @PostMapping("medical-files/{id}/medical-visits")
    public MedicalFile addMedicalVisit(@PathVariable Long id, @RequestBody MedicalVisit medicalVisit) {
        MedicalFile medicalFile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalFile not found with id " + id));
        medicalVisit.setMedicalFile(medicalFile); // Add this line
        medicalFile.getMedicalVisitList().add(medicalVisit);
        return repository.save(medicalFile);
    }
}
