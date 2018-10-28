package com.savelyeva.model;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CityTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM City").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Country country = Country.builder().country("Египет").build();
        session.save(country);
        City sessionCity = City.builder().country(country).city("Каир").build();
        Serializable savedId = session.save(sessionCity);
        session.getTransaction().commit();
        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Country country = Country.builder().country("Беларусь").build();
        session.save(country);
        City sessionCity = City.builder().country(country).city("Орша").build();
        session.save(sessionCity);
        session.evict(sessionCity);
        City databaseCity = session.find(City.class, sessionCity.getId());
        session.getTransaction().commit();
        assertEquals(sessionCity, databaseCity);
    }
}