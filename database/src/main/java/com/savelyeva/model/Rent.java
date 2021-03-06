package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@ToString //(exclude = {"person", "vehicle"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
@Entity
@Table(name = "rent", schema = "rent_car")
public class Rent implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdDate", column = @Column(name = "start_date")),
            @AttributeOverride(name = "updatedDate", column = @Column(name = "end_date", nullable = false))
    })
    private Audit rentPeriod;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Boolean paid;

    @Embedded
    @AttributeOverride(name = "info", column = @Column(name = "comment"))
    private Info comment;

    public Rent(Person person, Vehicle vehicle, Audit rentPeriod, Integer cost, Boolean paid) {
        this.person = person;
        this.vehicle = vehicle;
        this.rentPeriod = rentPeriod;
        this.cost = cost;
        this.paid = paid;
    }
}
