package com.santeConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

//@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "known_parent1")
    private String knownParent1;

    @Column(name = "known_parent2")
    private String knownParent2;

    @Column(name = "city_of_birth")
    private String cityOfBirth;

    // @ManyToOne
    // @JoinColumn(name = "coordinate_id")
    // private Coordinate coordinate;
}