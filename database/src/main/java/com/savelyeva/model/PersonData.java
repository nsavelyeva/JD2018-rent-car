package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;


@Data
@ToString(exclude = "person")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person_data", schema = "rent_car")
@Access(AccessType.FIELD)
public class PersonData implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    private Instant birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "passport", unique = true, nullable = false)
    private String passport;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "driving_license_id")
    private Long drivingLicenseId;

    public PersonData(Person person, String firstName, String lastName, Instant birthDate, Gender gender, String passport) {
        this.person = person;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.passport = passport;
    }
}


