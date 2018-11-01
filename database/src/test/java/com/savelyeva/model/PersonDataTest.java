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
import java.time.Instant;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PersonDataTest {

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

        Person person = session.find(Person.class, 1L);

        PersonData sessionPersonData = PersonData.builder()
                .person(person)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("AB123456")
                .build();
        Serializable savedId = session.save(sessionPersonData);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Person person = session.find(Person.class, 2L);

        PersonData sessionPersonData = PersonData.builder()
                .person(person)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("CD123456")
                .build();
        session.save(sessionPersonData);

        session.evict(sessionPersonData);

        PersonData databasePersonData = session.find(PersonData.class, sessionPersonData.getId());

        session.getTransaction().commit();

        assertEquals(sessionPersonData, databasePersonData);
    }
}