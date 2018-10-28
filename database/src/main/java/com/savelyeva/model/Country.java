package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "country", schema = "rent_car")
@Access(AccessType.FIELD)
public class Country implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "country", unique = true, nullable = false)
    private String country;

    public Country(String country) {
        this.country = country;
    }

}