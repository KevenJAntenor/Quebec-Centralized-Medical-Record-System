package com.santeConnect.domain.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfVisit;

    private String diagnostic;

    private String treatment;

    private String summary;

    private String notes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_file_id")
    private MedicalFile medicalFile;

    public MedicalVisit(String establishment, String doctor, Date dateOfVisit, String diagnostic, String treatment,
            String summary, String notes) {
        super();
        this.establishment = establishment;
        this.doctor = doctor;
        this.dateOfVisit = dateOfVisit;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
        this.summary = summary;
        this.notes = notes;
    }
}
