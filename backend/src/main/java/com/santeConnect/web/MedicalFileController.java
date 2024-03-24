package com.santeConnect.web;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.repository.entities.MedicalFileRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("medical-files/{id}")
    public MedicalFile getMedicalFile(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalFile not found with id " + id));
    }
}
