package com.santeConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
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

    @JsonManagedReference
    @OneToMany(mappedBy = "medicalFile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalVisit> medicalVisitList;

    public MedicalFile(String insuranceNumber, Patient patient) {
        super();
        this.insuranceNumber = insuranceNumber;
        this.patient = patient;
    }
}
