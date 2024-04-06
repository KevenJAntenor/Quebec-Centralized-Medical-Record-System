package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

public class FetchMedicalFile extends MedicalFileOperation<MedicalFile>{

    public FetchMedicalFile(MedicalFileRepository repository) {
        super(repository);
    }

    @Override
    protected ResponseEntity<MedicalFile> performOperation(MedicalFile medicalFile) {
        return ResponseEntity.ok(medicalFile);
    }
}
