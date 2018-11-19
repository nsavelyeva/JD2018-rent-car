package com.savelyeva.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address", schema = "rent_car")
public class Address implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "street_id")
    private Street street;

    @Column(nullable = false)
    private String building;

    @Column(nullable = false)
    private String flat;

    public Address(Street street, String building, String flat) {
        this.street = street;
        this.building = building;
        this.flat = flat;
    }
}