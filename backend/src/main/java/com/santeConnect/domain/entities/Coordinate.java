package com.santeConnect.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @Column(name = "personal_phone_number")
    private String personalPhoneNumber;

    @Column(name = "work_phone_number")
    private String workPhoneNumber;

    @Column
    private String email;

    public Coordinate(String address, String personalPhoneNumber, String workPhoneNumber, String email) {
        this.address = address;
        this.personalPhoneNumber = personalPhoneNumber;
        this.workPhoneNumber = workPhoneNumber;
        this.email = email;
    }
}
