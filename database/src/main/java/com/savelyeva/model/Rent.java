package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = {"person", "vehicle"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
@Entity
@Table(name = "rent", schema = "rent_car")
@Access(AccessType.FIELD)
public class Rent implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "created_date", column = @Column(name = "start_date")),
            @AttributeOverride(name = "updated_date", column = @Column(name = "end_date", nullable = false))
    })
    private Audit rentPeriod;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "paid", nullable = false)
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
