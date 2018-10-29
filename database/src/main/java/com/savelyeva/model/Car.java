package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Table(name = "car", schema = "rent_car")
@PrimaryKeyJoinColumn(name = "car_id")
public class Car extends Vehicle {

    @Column(name = "passengers_capacity", nullable = false)
    private Short passengersCapacity;

    @Column(name = "trunk_capacity", nullable = false)
    private Short trunkCapacity;

    public Car(Vehicle vehicle, Short passengersCapacity, Short trunkCapacity) {
        super(vehicle.getIdModel(), vehicle.getColorId(), vehicle.getTransmission(), vehicle.getProducedYear(),
                vehicle.getFullPrice(), vehicle.getDayPrice(), vehicle.getAudit());
        this.passengersCapacity = passengersCapacity;
        this.trunkCapacity = trunkCapacity;
    }
}
