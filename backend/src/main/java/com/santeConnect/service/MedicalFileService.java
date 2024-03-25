package com.santeConnect.service;

import com.santeConnect.domain.entities.MedicalFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MedicalFileService {

    @PreAuthorize("hasRole('ADMIN')")
    public void updateMedicalFile(MedicalFile medicalFile){
        // TODO
    }

}
