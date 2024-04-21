package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

import static com.santeConnect.middlewares.ActivateObservers.activateObservers;

public class UpdateMedicalVisit  extends MedicalFileOperation<MedicalFile> {

    private MedicalVisit medicalVisit;

    public UpdateMedicalVisit(MedicalFileRepository repository, MedicalVisit medicalVisit) {
        super(repository);
        this.medicalVisit = medicalVisit;
    }

    @Override
    protected ResponseEntity<MedicalFile> perform(MedicalFile medicalFile) {
        medicalVisit.setMedicalFile(medicalFile);
        medicalFile.getMedicalVisitList().add(medicalVisit);
        var result = repository.save(medicalFile);
        // For Observer Pattern
        activateObservers(medicalFile);
        return ResponseEntity.ok(result);
    }
}
