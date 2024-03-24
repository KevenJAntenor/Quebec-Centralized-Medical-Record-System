package com.santeConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@ToString
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medical_file")
public class MedicalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insurance_number", unique = true, nullable = false)
    private String insuranceNumber;

    @OneToOne
    private Patient patient;

    @OneToMany
    private List<MedicalVisit> medicalVisitList;

    public MedicalFile(String insuranceNumber, Patient patient) {
        super();
        this.insuranceNumber = insuranceNumber;
        this.patient = patient;
    }
}
