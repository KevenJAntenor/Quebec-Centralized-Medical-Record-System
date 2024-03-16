package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.repository.IMedicalFileRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalFileController {

    private final IMedicalFileRepository repository;

    public MedicalFileController(IMedicalFileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("medicalFiles")
    public Iterable<MedicalFile> getMedicalFiles() {
        return repository.findAll();
    }

}
