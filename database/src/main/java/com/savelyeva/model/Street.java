package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;


@Data
@ToString(exclude = "city")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "street", schema = "rent_car")
@Access(AccessType.FIELD)
public class Street implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*@Column(name = "city_id", nullable = false)
    private Long cityId;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "street", nullable = false)
    private String street;

    public Street(City city, String street) {
        this.city = city;
        this.street = street;
    }
}