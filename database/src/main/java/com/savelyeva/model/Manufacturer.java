package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "manufacturer", schema = "rent_car")
@Access(AccessType.FIELD)
public class Manufacturer implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "manufacturer", unique = true, nullable = false)
    private String manufacturer;

    public Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
