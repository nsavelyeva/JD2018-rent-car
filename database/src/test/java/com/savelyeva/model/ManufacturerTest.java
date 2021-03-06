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

public class ManufacturerTest {

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

        Manufacturer sessionManufacturer = Manufacturer.builder()
                .manufacturer("Peugeot")
                .build();
        Serializable savedId = session.save(sessionManufacturer);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Manufacturer sessionManufacturer = Manufacturer.builder()
                .manufacturer("МАЗ")
                .build();
        session.save(sessionManufacturer);

        session.evict(sessionManufacturer);

        Manufacturer databaseManufacturer = session.find(Manufacturer.class, sessionManufacturer.getId());

        session.getTransaction().commit();

        assertEquals(sessionManufacturer, databaseManufacturer);
    }
}