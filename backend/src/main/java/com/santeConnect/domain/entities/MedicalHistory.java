package com.santeConnect.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnostic;

    private String treatment;

    private String doctor;

    private String startDate;

    private String endDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_file_id")
    private MedicalFile medicalFile;

    public MedicalHistory(String diagnostic, String treatment, String doctor, String startDate, String endDate) {
        super();
        this.diagnostic = diagnostic;
        this.treatment = treatment;
        this.doctor = doctor;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
