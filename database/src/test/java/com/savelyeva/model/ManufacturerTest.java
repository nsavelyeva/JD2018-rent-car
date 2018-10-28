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

public class ManufacturerTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Manufacturer").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Manufacturer sessionManufacturer = Manufacturer.builder().manufacturer("Peugeot").build();
        Serializable savedId = session.save(sessionManufacturer);
        session.getTransaction().commit();
        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Manufacturer sessionManufacturer = Manufacturer.builder().manufacturer("МАЗ").build();
        session.save(sessionManufacturer);
        session.evict(sessionManufacturer);
        Manufacturer databaseManufacturer = session.find(Manufacturer.class, sessionManufacturer.getId());
        session.getTransaction().commit();
        assertEquals(sessionManufacturer, databaseManufacturer);
    }
}