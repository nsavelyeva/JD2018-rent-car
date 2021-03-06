package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@ToString(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lorry", schema = "rent_car")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Lorry extends Vehicle {

    @Column(name = "carrying_capacity", nullable = false)
    private Integer carryingCapacity;

    public Lorry(Vehicle vehicle, Integer carryingCapacity) {
        super(vehicle.getModel(), vehicle.getColor(), vehicle.getTransmission(), vehicle.getProducedYear(),
                vehicle.getFullPrice(), vehicle.getDayPrice(), vehicle.getAudit());
        this.carryingCapacity = carryingCapacity;
    }

}
