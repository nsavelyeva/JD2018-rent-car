package com.savelyeva.model;

import com.savelyeva.util.CreateTestData;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Serializable;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class StreetTest {

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

        City city = session.find(City.class, 1L);

        Street sessionStreet = Street.builder()
                .city(city)
                .street("Rapenburgerstraat")
                .build();
        Serializable savedId = session.save(sessionStreet);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        City city = session.find(City.class, 1L);

        Street sessionStreet = Street.builder()
                .city(city)
                .street("Plantage Middenlaan")
                .build();
        session.save(sessionStreet);

        session.evict(sessionStreet);

        Street databaseStreet = session.find(Street.class, sessionStreet.getId());

        session.getTransaction().commit();

        assertEquals(sessionStreet, databaseStreet);
    }
}