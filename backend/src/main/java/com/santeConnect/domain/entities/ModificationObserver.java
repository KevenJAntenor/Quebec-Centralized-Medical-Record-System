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

    /**
     * This method gets called everytime the medical file has been updated
     * For now it only prints a message, but it can be implemented to actually
     * archive the modifications into the archive database, however this
     * functionality is out of the scope of this current project
     *
     * It can be implemented to compare lastMedicalFile and currentMedicalFile
     * and then archive the differences in the Archive database with a timestamp
     */
    public void archive() {
        System.out.println("I, medical File number " + currentMedicalFile.getInsuranceNumber() + " have been Archived");
    }
}
