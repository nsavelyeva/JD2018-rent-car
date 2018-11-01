package com.savelyeva.model;

import com.savelyeva.util.CreateTestData;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

import static java.lang.Boolean.TRUE;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class RentTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        CreateTestData.getInstance().importTestData(sessionFactory);
    }

    @AfterClass
    public static void closeFactory() {
        sessionFactory.close();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Instant now = Instant.now();
        Audit somePeriod = Audit.builder()
                .createdDate(now.minus(Duration.ofDays(2)))
                .updatedDate(now)
                .build();

        Person person = session.find(Person.class, 1L);
        Car car = session.find(Car.class, 1L);

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
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Instant now = Instant.now();
        Audit somePeriod = Audit.builder()
                .createdDate(now.minus(Duration.ofDays(2)))
                .updatedDate(now)
                .build();

        Person person = session.find(Person.class, 2L);
        Lorry lorry = session.find(Lorry.class, 1L);

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