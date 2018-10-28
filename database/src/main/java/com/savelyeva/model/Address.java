package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;


@Data
@ToString(exclude = "street")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address", schema = "rent_car")
@Access(AccessType.FIELD)
public class Address implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_id")
    private Street street;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "flat", nullable = false)
    private String flat;

    public Address(Street street, String building, String flat) {
        this.street = street;
        this.building = building;
        this.flat = flat;
    }
}