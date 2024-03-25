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
@Table(name = "medical_visit")
public class MedicalVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String establishment;

    private String doctor;

    @Column(name = "date_of_visit")
    private String dateOfVisit;

    private String diagnostic;

    private String treatment;

    private String summary;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "medical_file_id")
    private MedicalFile medicalFile;

    public MedicalVisit(String establishment, String doctor, String dateOfVisit, String diagnostic, String treatment, String summary, String notes) {
        this.establishment = establishment;
        this.doctor = doctor;
        this.dateOfVisit = dateOfVisit;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
        this.summary = summary;
        this.notes = notes;
    }
}
