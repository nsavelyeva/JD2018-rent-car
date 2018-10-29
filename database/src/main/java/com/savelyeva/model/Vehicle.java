package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "persons")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "vehicle", schema = "rent_car")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "info", column = @Column(name = "description"))
    private Info description;

    @Column(name = "model_id", nullable = false)
    private Integer idModel;

    @Column(name = "color_id", nullable = false)
    private Integer colorId;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "produced_year", nullable = false)
    private Short producedYear;

    @Column(name = "full_price", nullable = false)
    private Integer fullPrice;

    @Column(name = "day_price", nullable = false)
    private Integer dayPrice;

    @Embedded
    private Audit audit;

    @Embedded
    @AttributeOverride(name = "info", column = @Column(name = "comment"))
    private Info comment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rent", schema = "rent_car",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons = new ArrayList<>();

    public Vehicle(Integer idModel, Integer colorId, Transmission transmission, Short producedYear,
                   Integer fullPrice, Integer dayPrice, Audit audit) {
        this.idModel = idModel;
        this.colorId = colorId;
        this.transmission = transmission;
        this.producedYear = producedYear;
        this.fullPrice = fullPrice;
        this.dayPrice = dayPrice;
        this.audit = audit;
    }

}
