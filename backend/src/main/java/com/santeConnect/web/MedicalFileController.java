package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalFileFacade;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.domain.entities.ModificationObserver;
import com.santeConnect.repository.patient.MedicalFileRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalFileController {

    private final MedicalFileRepository repository;

    public MedicalFileController(MedicalFileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("medical-files")
    public ResponseEntity<List<MedicalFile>> getMedicalFiles() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("medical-files/{id}")
    public ResponseEntity<MedicalFile> getMedicalFile(@PathVariable Long id) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optionalFile.get());
    }

    // post route to add new medical visit to a medical file
    @PostMapping("medical-files/{id}/medical-visits")
    public ResponseEntity<MedicalFile> addMedicalVisit(@PathVariable Long id, @RequestBody MedicalVisit medicalVisit) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();
        MedicalFile medicalFile = optionalFile.get();
        medicalVisit.setMedicalFile(medicalFile);
        medicalFile.getMedicalVisitList().add(medicalVisit);
        var result = repository.save(medicalFile);
        // For observer Pattern
        MedicalFileFacade facade = new MedicalFileFacade();
        ModificationObserver observer = new ModificationObserver(facade);
        facade.setMedicalFile(medicalFile);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("medical-files/{id}/medical-visits")
    public ResponseEntity<String> deleteMedicalVisit(@PathVariable Long id, @RequestBody Long visitId) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();
        MedicalFile medicalFile = optionalFile.get();
        Optional<MedicalVisit> visit = medicalFile.getMedicalVisitList().stream().filter(m -> {
            System.out.println(m.getId().toString());
            return m.getId() == visitId;
        })
                .findFirst();
        if (visit.isEmpty())
            return ResponseEntity.notFound().build();
        System.out.println("OUI > " + medicalFile.getMedicalVisitList().remove(visit.get()));
        var result = repository.save(medicalFile);
        return ResponseEntity.ok("Visit deleted");
    }
}
