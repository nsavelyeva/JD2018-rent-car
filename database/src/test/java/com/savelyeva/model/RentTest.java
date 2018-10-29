package com.savelyeva.model;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

import static java.lang.Boolean.TRUE;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class RentTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Rent").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Role role = Role.builder()
                .role("Администратор")
                .build();
        session.save(role);

        Instant now = Instant.now();
        Audit somePeriod = Audit.builder()
                .createdDate(now.minus(Duration.ofDays(2)))
                .updatedDate(now)
                .build();

        Person person = Person.builder()
                .role(role)
                .login("person")
                .password("secret")
                .email("person@example.com")
                .audit(somePeriod).build();
        session.save(person);

        Color color = Color.builder()
                .color("yellow")
                .build();
        session.save(color);

        Manufacturer manufacturerCar = Manufacturer.builder()
                .manufacturer("Nissan")
                .build();
        session.save(manufacturerCar);

        Model modelCar = Model.builder()
                .manufacturer(manufacturerCar)
                .model("Juke")
                .build();
        session.save(modelCar);

        Vehicle vehicle = new Vehicle(modelCar, color, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, somePeriod);
        session.save(vehicle);

        Car car = new Car(vehicle, Short.valueOf("4"), Short.valueOf("300"));
        session.save(car);

        Rent sessionRent = Rent.builder()
                .person(person)
                .vehicle(car)
                .rentPeriod(somePeriod)
                .cost(1000)
                .paid(TRUE)
                .build();
        Serializable savedId = session.save(sessionRent);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Role role = Role.builder().role("Пользователь").build();
        session.save(role);

        Instant now = Instant.now();
        Audit somePeriod = Audit.builder()
                .createdDate(now.minus(Duration.ofDays(2)))
                .updatedDate(now)
                .build();

        Person person = Person.builder()
                .role(role)
                .login("customer")
                .password("secret")
                .email("customer@example.com")
                .audit(somePeriod).build();
        session.save(person);

        Color color = Color.builder()
                .color("жёлтый")
                .build();
        session.save(color);

        Manufacturer manufacturerLorry = Manufacturer.builder()
                .manufacturer("БелАЗ")
                .build();
        session.save(manufacturerLorry);

        Model modelLorry = Model.builder()
                .manufacturer(manufacturerLorry)
                .model("БелАЗ-75710")
                .build();
        session.save(modelLorry);

        Vehicle vehicle = new Vehicle(modelLorry, color, Transmission.MECHANIC, Short.valueOf("2013"), 100000, 500, somePeriod);
        session.save(vehicle);

        Lorry lorry = new Lorry(vehicle, 450000);
        session.save(lorry);

        Rent sessionRent = Rent.builder()
                .person(person)
                .vehicle(lorry)
                .rentPeriod(somePeriod)
                .cost(1000)
                .paid(TRUE)
                .build();
        session.save(sessionRent);

        session.evict(sessionRent);

        Rent databaseRent = session.find(Rent.class, sessionRent.getId());

        session.getTransaction().commit();

        assertEquals(sessionRent, databaseRent);
    }
}