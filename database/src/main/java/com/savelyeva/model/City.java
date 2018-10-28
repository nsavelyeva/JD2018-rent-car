package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@ToString(exclude = "streets")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "city", schema = "rent_car")
@Access(AccessType.FIELD)
public class City implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "city")
    private Set<Street> streets = new HashSet<>();

    public City(Country country, String city) {
        this.country = country;
        this.city = city;
    }
}