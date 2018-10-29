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

public class ModelTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Model").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Manufacturer manufacturer = Manufacturer.builder().manufacturer("Toyota").build();
        session.save(manufacturer);

        Model sessionModel = Model.builder().manufacturer(manufacturer).model("Corola").build();
        Serializable savedId = session.save(sessionModel);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Manufacturer manufacturer = Manufacturer.builder().manufacturer("Ford").build();
        session.save(manufacturer);

        Model sessionModel = Model.builder().manufacturer(manufacturer).model("Focus").build();
        session.save(sessionModel);

        session.evict(sessionModel);

        Model databaseModel = session.find(Model.class, sessionModel.getId());

        session.getTransaction().commit();

        assertEquals(sessionModel, databaseModel);
    }
}