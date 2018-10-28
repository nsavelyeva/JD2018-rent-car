package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;


@Data
@ToString(exclude = "manufacturer")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "model", schema = "rent_car")
@Access(AccessType.FIELD)
public class Model implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    public Model(Manufacturer manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }
}


