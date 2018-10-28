package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "driving_license", schema = "rent_car")
@Access(AccessType.FIELD)
public class DrivingLicense implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "created_date", column = @Column(name = "issued_date")),
            @AttributeOverride(name = "updated_date", column = @Column(name = "expire_date", nullable = false))
    })
    private Audit drivingPeriod;

    @Enumerated(EnumType.STRING)
    private Category category;

    public DrivingLicense(String serialNumber, Audit drivingPeriod, Category category) {
        this.serialNumber = serialNumber;
        this.drivingPeriod = drivingPeriod;
        this.category = category;

    }
}