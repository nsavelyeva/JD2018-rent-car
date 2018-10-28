package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "color", schema = "rent_car")
@Access(AccessType.FIELD)
public class Color implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "color", unique = true, nullable = false)
    private String color;

    public Color(String color) {
        this.color = color;
    }
}
