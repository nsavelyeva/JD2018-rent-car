package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car", schema = "rent_car")
@PrimaryKeyJoinColumn(name = "car_id")
@Access(AccessType.FIELD)
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
