package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lorry", schema = "rent_car")
@PrimaryKeyJoinColumn(name = "car_id")
@Access(AccessType.FIELD)
public class Lorry extends Vehicle {

    @Column(name = "carrying_capacity", nullable = false)
    private Integer carryingCapacity;

    public Lorry(Vehicle vehicle, Integer carryingCapacity) {
        super(vehicle.getIdModel(), vehicle.getColorId(), vehicle.getTransmission(), vehicle.getProducedYear(),
                vehicle.getFullPrice(), vehicle.getDayPrice(), vehicle.getAudit());
        this.carryingCapacity = carryingCapacity;
    }

}
