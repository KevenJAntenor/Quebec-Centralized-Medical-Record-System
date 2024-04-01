package com.santeConnect.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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

    @OneToOne
    private Coordinate coordinate;

    public Patient(String lastName, String firstName, String dateOfBirth, String gender, String knownParent1,
            String knownParent2, String cityOfBirth, Coordinate coordinate) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.knownParent1 = knownParent1;
        this.knownParent2 = knownParent2;
        this.cityOfBirth = cityOfBirth;
        this.coordinate = coordinate;
    }
}
