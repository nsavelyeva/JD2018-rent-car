package com.savelyeva.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@ToString(exclude = {"vehicles", "role"})
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person", schema = "rent_car")
@Access(AccessType.FIELD)
public class Person implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Embedded
    private Audit audit;

    @OneToOne(mappedBy = "person")
    private PersonData personData;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rent", schema = "rent_car",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<Vehicle> vehicles = new ArrayList<>();

    public Person(Role role, String login, String password, String email, Audit audit) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.email = email;
        this.audit = audit;
    }
}

