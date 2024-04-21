package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalHistory;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

import static com.santeConnect.middlewares.ActivateObservers.activateObservers;

public class UpdateMedicalHistory  extends MedicalFileOperation<MedicalFile> {

    private MedicalHistory medicalHistory;

    public UpdateMedicalHistory(MedicalFileRepository repository, MedicalHistory medicalHistory) {
        super(repository);
        this.medicalHistory = medicalHistory;
    }

    @Override
    protected ResponseEntity<MedicalFile> perform(MedicalFile medicalFile) {
        medicalHistory.setMedicalFile(medicalFile);
        medicalFile.getMedicalHistoryList().add(medicalHistory);
        var result = repository.save(medicalFile);
        // For Observer Pattern
        activateObservers(medicalFile);
        return ResponseEntity.ok(result);
    }
}
