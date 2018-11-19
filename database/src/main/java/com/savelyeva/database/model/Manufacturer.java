package com.savelyeva.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "models")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "manufacturer", schema = "rent_car")
public class Manufacturer implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String manufacturer;

    @OneToMany(mappedBy = "manufacturer")
    private Set<Model> models = new HashSet<>();

    public Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
