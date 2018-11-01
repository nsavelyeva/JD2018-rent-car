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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CityTest {

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

        Country country = session.find(Country.class, 1);

        City sessionCity = City.builder()
                .country(country)
                .city("Каир")
                .build();
        Serializable savedId = session.save(sessionCity);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country country = session.find(Country.class, 1);

        City sessionCity = City.builder()
                .country(country)
                .city("Орша")
                .build();
        session.save(sessionCity);

        session.evict(sessionCity);

        City databaseCity = session.find(City.class, sessionCity.getId());

        session.getTransaction().commit();

        assertEquals(sessionCity, databaseCity);
    }
}