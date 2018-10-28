package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role", schema = "rent_car")
@Access(AccessType.FIELD)
public class Role implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role", unique = true, nullable = false)
    private String role;

    public Role(String role) {
        this.role = role;
    }
}
