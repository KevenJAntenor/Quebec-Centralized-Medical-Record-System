package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.repository.patient.MedicalFileRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.santeConnect.middlewares.ActivateObservers.activateObservers;

public class FetchMedicalFile {

    public static ResponseEntity<MedicalFile> fetchMedicalFile(MedicalFileRepository repository, Long id) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalFile.get());
    }

    public static ResponseEntity<MedicalFile> updateMedicalVisit(MedicalFileRepository repository, Long id, MedicalVisit medicalVisit) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();
        MedicalFile medicalFile = optionalFile.get();
        medicalVisit.setMedicalFile(medicalFile);
        medicalFile.getMedicalVisitList().add(medicalVisit);
        var result = repository.save(medicalFile);
        // For observer Pattern
        activateObservers(medicalFile);
        return ResponseEntity.ok(result);
    }

    public static ResponseEntity<String> removeMedicalVisit(MedicalFileRepository repository, Long id, Long visitId) {
        Optional<MedicalFile> optionalFile = repository.findById(id);
        if (optionalFile.isEmpty())
            return ResponseEntity.notFound().build();
        MedicalFile medicalFile = optionalFile.get();
        Optional<MedicalVisit> visit = medicalFile.getMedicalVisitList().stream().filter(m -> {
                    System.out.println(m.getId().toString());
                    return m.getId() == visitId;
                })
                .findFirst();
        if (visit.isEmpty())
            return ResponseEntity.notFound().build();
        System.out.println("OUI > " + medicalFile.getMedicalVisitList().remove(visit.get()));
        var result = repository.save(medicalFile);
        // For observer Pattern
        activateObservers(medicalFile);
        return ResponseEntity.ok("Visit deleted");
    }

}
