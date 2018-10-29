package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lorry", schema = "rent_car")
@PrimaryKeyJoinColumn(name = "car_id")
public class Lorry extends Vehicle {

    @Column(name = "carrying_capacity", nullable = false)
    private Integer carryingCapacity;

    public Lorry(Vehicle vehicle, Integer carryingCapacity) {
        super(vehicle.getIdModel(), vehicle.getColorId(), vehicle.getTransmission(), vehicle.getProducedYear(),
                vehicle.getFullPrice(), vehicle.getDayPrice(), vehicle.getAudit());
        this.carryingCapacity = carryingCapacity;
    }

}
