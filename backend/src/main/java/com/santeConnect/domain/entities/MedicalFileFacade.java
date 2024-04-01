package com.santeConnect.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Act both as a Facade for MedicalFile, and as a Subject for observers
 */
public class MedicalFileFacade implements Subject{

    private List<Observer> observers;
    private MedicalFile medicalFile;

    public MedicalFileFacade() {
        observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void medicalFileUpdated() {
        notifyObservers();
    }

    public void setMedicalFile(MedicalFile medicalFile) {
        this.medicalFile = medicalFile;
        medicalFileUpdated();
    }

    public MedicalFile getMedicalFile() {
        return medicalFile;
    }
}
