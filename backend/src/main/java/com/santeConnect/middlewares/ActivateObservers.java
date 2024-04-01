package com.santeConnect.middlewares;

import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalFileFacade;
import com.santeConnect.domain.entities.ModificationObserver;

public class ActivateObservers {

    public static void activateObservers(MedicalFile medicalFile) {
        MedicalFileFacade facade = new MedicalFileFacade();
        ModificationObserver observer = new ModificationObserver(facade);
        facade.setMedicalFile(medicalFile);
    }

}
