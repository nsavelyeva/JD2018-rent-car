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

public class StreetTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Street").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Country country = Country.builder().country("The Netherlands").build();
        session.save(country);
        City city = City.builder().country(country).city("Amsterdam").build();
        session.save(city);
        Street sessionStreet = Street.builder().city(city).street("Kalverstraat").build();
        Serializable savedId = session.save(sessionStreet);
        session.getTransaction().commit();
        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Country country = Country.builder().country("Беларусь").build();
        session.save(country);
        City city = City.builder().country(country).city("Минск").build();
        session.save(city);
        Street sessionStreet = Street.builder().city(city).street("пр. Независимости").build();
        session.save(sessionStreet);
        session.evict(sessionStreet);
        Street databaseStreet = session.find(Street.class, sessionStreet.getId());
        session.getTransaction().commit();
        assertEquals(sessionStreet, databaseStreet);
    }
}