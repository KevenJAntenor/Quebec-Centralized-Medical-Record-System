package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class MedicalFileOperation<T> {

    protected MedicalFileRepository repository;

    public MedicalFileOperation(MedicalFileRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<T> execute(Long id) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();
        return performOperation(optionalFile.get());
    }

    protected abstract ResponseEntity<T> performOperation(MedicalFile medicalFile);
}
