package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.domain.entities.MedicalHistory;
import com.santeConnect.middlewares.FetchMedicalFile;
import com.santeConnect.middlewares.MedicalFileOperation;
import com.santeConnect.middlewares.RemoveMedicalVisit;
import com.santeConnect.middlewares.UpdateMedicalVisit;
import com.santeConnect.middlewares.RemoveMedicalHistory;
import com.santeConnect.middlewares.UpdateMedicalHistory;
import com.santeConnect.repository.entities.MedicalFileRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MedicalFileController {

    @Autowired
    private MedicalFileRepository repository;

    @GetMapping("medical-files")
    public ResponseEntity<List<MedicalFile>> getMedicalFiles() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * Route to get a medical file
     * 
     * @param id to get medical file from db
     * @return response code with medical file
     */
    @GetMapping("medical-files/{id}")
    public ResponseEntity<MedicalFile> getMedicalFile(@PathVariable Long id) {
        MedicalFileOperation fetchMedicalFile = new FetchMedicalFile(repository);
        return fetchMedicalFile.execute(id);
    }

    /**
     * Route to add a medical visit also activates observers
     * 
     * @param id           to get the medical file from db
     * @param medicalVisit to add associated medical visit
     * @return response code with medical file
     */
    @PostMapping("medical-files/{id}/medical-visits")
    public ResponseEntity<MedicalFile> addMedicalVisit(@PathVariable Long id,
            @RequestBody @Valid MedicalVisit medicalVisit) {
        MedicalFileOperation updateMedicalVisit = new UpdateMedicalVisit(repository, medicalVisit);
        return updateMedicalVisit.execute(id);
    }

    /**
     *
     * @param id      to get the medical file from db
     * @param visitId to rm associated medical visit
     * @return response code with message
     */
    @DeleteMapping("medical-files/{id}/medical-visits")
    public ResponseEntity<String> deleteMedicalVisit(@PathVariable Long id, @RequestBody Long visitId) {
        MedicalFileOperation removeMedicalVisit = new RemoveMedicalVisit(repository, visitId);
        return removeMedicalVisit.execute(id);
    }

    /**
     * Route to add a medical history to a medical file
     * 
     * @param id             to get the medical file from db
     * @param medicalHistory to add associated medical history
     * @return response code with medical file
     */
    @PostMapping("medical-files/{id}/medical-histories")
    public ResponseEntity<MedicalFile> addMedicalHistory(@PathVariable Long id,
            @Valid @RequestBody MedicalHistory medicalHistory) {
        MedicalFileOperation updateMedicalHistory = new UpdateMedicalHistory(repository, medicalHistory);
        return updateMedicalHistory.execute(id);
    }

    /**
     *
     * @param id        to get the medical file from db
     * @param historyId to remove associated medical history
     * @return response code with message
     */
    @DeleteMapping("medical-files/{id}/medical-histories")
    public ResponseEntity<String> deleteMedicalHistory(@PathVariable Long id, @RequestBody Long historyId) {
        MedicalFileOperation removeMedicalHistory = new RemoveMedicalHistory(repository, historyId);
        return removeMedicalHistory.execute(id);
    }
}
