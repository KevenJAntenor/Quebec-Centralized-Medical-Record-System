package com.santeConnect.domain;

import java.util.Optional;

public interface MedicalFileRepo {

    void create(MedicalFile medicalFile);

    Optional<MedicalFile> getFromInsuranceNumber(String insuranceNumber);
}
