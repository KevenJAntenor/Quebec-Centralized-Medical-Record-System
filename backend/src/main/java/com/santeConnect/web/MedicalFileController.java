package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
