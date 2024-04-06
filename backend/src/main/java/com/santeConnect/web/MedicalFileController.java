package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.middlewares.FetchMedicalFile;
import com.santeConnect.middlewares.MedicalFileOperation;
import com.santeConnect.middlewares.RemoveMedicalVisit;
import com.santeConnect.middlewares.UpdateMedicalVisit;
import com.santeConnect.repository.entities.MedicalFileRepository;

import java.util.List;

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

    /**
     * Route to get a medical file
     * @param id to get medical file from db
     * @return response code with medical file
     */
    @SuppressWarnings("unchecked")
    @GetMapping("medical-files/{id}")
    public ResponseEntity<MedicalFile> getMedicalFile(@PathVariable Long id) {
        MedicalFileOperation fetchMedicalFile = new FetchMedicalFile(repository);
        return fetchMedicalFile.execute(id);
    }

    /**
     * Route to add a medical visit also activates observers
     * @param id to get the medical file from db
     * @param medicalVisit to add associated medical visit
     * @return response code with medical file
     */
    @SuppressWarnings("unchecked")
    @PostMapping("medical-files/{id}/medical-visits")
    public ResponseEntity<MedicalFile> addMedicalVisit(@PathVariable Long id, @RequestBody MedicalVisit medicalVisit) {
        MedicalFileOperation updateMedicalVisit = new UpdateMedicalVisit(repository, medicalVisit);
        return updateMedicalVisit.execute(id);
    }

    /**
     *
     * @param id to get the medical file from db
     * @param visitId to rm associated medical visit
     * @return response code with message
     */
    @SuppressWarnings("unchecked")
    @DeleteMapping("medical-files/{id}/medical-visits")
    public ResponseEntity<String> deleteMedicalVisit(@PathVariable Long id, @RequestBody Long visitId) {
        MedicalFileOperation removeMedicalVisit = new RemoveMedicalVisit(repository, visitId);
        return removeMedicalVisit.execute(id);
    }
}
