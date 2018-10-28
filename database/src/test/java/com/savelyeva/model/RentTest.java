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
        Role role = Role.builder().role("Администратор").build();
        session.save(role);
        Instant now = Instant.now();
        Audit somePeriod = Audit.builder().created_date(now.minus(Duration.ofDays(2))).updated_date(now).build();
        Person person = Person.builder().role(role).login("person").password("secret").email("person@example.com").audit(somePeriod).build();
        session.save(person);
        Vehicle vehicle = new Vehicle(1, 1, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, somePeriod);
        session.save(vehicle);
        Car car = new Car(vehicle, Short.valueOf("4"), Short.valueOf("300"));
        session.save(car);
        Rent sessionRent = Rent.builder().person(person).vehicle(car).rentPeriod(somePeriod).cost(1000).paid(TRUE).build();
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
        Audit somePeriod = Audit.builder().created_date(now.minus(Duration.ofDays(2))).updated_date(now).build();
        Person person = Person.builder().role(role).login("customer").password("secret").email("customer@example.com").audit(somePeriod).build();
        session.save(person);
        Vehicle vehicle = new Vehicle(1, 1, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, somePeriod);
        session.save(vehicle);
        Car car = new Car(vehicle, Short.valueOf("4"), Short.valueOf("300"));
        session.save(car);
        Rent sessionRent = Rent.builder().person(person).vehicle(car).rentPeriod(somePeriod).cost(1000).paid(TRUE).build();
        session.save(sessionRent);
        session.evict(sessionRent);
        Rent databaseRent = session.find(Rent.class, sessionRent.getId());
        session.getTransaction().commit();
        assertEquals(sessionRent, databaseRent);
    }
}