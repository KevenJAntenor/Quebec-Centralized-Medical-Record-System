package com.santeConnect.domain.entities;

public class ModificationObserver implements Observer{

    private MedicalFile currentMedicalFile = null;
    private MedicalFile lastMedicalFile;
    private MedicalFileFacade medicalFileFacade;

    public ModificationObserver(MedicalFileFacade medicalFileFacade) {
        this.medicalFileFacade = medicalFileFacade;
        medicalFileFacade.attach(this);
    }

    @Override
    public void update() {
        lastMedicalFile = currentMedicalFile;
        this.currentMedicalFile = medicalFileFacade.getMedicalFile();
        archive();
    }

    public void archive() {
        // To be implemented when RAMQ interface is requested.
        // Check differences between current and last medical file
        // and save that modification with timestamp to archive.
    }
}
