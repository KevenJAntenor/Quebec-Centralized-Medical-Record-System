package com.santeConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

//@ToString
@Getter
@Setter
@AllArgsConstructor
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
}
