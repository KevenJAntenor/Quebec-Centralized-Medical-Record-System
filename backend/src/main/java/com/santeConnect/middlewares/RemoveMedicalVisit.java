package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.santeConnect.middlewares.ActivateObservers.activateObservers;

public class RemoveMedicalVisit extends MedicalFileOperation<String> {

    private Long visitId;

    public RemoveMedicalVisit(MedicalFileRepository repository, Long visitId) {
        super(repository);
        this.visitId = visitId;
    }

    @Override
    protected ResponseEntity<String> performOperation(MedicalFile medicalFile) {
        Optional<MedicalVisit> visit = medicalFile.getMedicalVisitList().stream().filter(m -> m.getId() == visitId).findFirst();
        if (visit.isEmpty())
            return ResponseEntity.notFound().build();
        medicalFile.getMedicalVisitList().remove(visit.get());
        var result = repository.save(medicalFile);
        // For Observer Pattern
        activateObservers(medicalFile);
        return ResponseEntity.ok("Visit removed");
    }
}
