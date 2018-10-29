package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "driving_license", schema = "rent_car")
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