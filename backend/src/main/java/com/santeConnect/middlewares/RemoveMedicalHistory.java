package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalHistory;
import com.santeConnect.repository.entities.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.santeConnect.middlewares.ActivateObservers.activateObservers;

public class RemoveMedicalHistory extends MedicalFileOperation<String> {

    private Long historyId;

    public RemoveMedicalHistory(MedicalFileRepository repository, Long historyId) {
        super(repository);
        this.historyId = historyId;
    }

    @Override
    protected ResponseEntity<String> perform(MedicalFile medicalFile) {
        Optional<MedicalHistory> history = medicalFile.getMedicalHistoryList().stream().filter(m -> m.getId() == historyId).findFirst();
        if (history.isEmpty())
            return ResponseEntity.notFound().build();
        medicalFile.getMedicalHistoryList().remove(history.get());
        var result = repository.save(medicalFile);
        // For Observer Pattern
        activateObservers(medicalFile);
        return ResponseEntity.ok("Medical History removed");
    }
}
