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

public class ModelTest {

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

        Manufacturer manufacturer = session.find(Manufacturer.class, 1);

        Model sessionModel = Model.builder().manufacturer(manufacturer).model("Q5").build();
        Serializable savedId = session.save(sessionModel);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Manufacturer manufacturer = session.find(Manufacturer.class, 1);

        Model sessionModel = Model.builder().manufacturer(manufacturer).model("Q6").build();
        session.save(sessionModel);

        session.evict(sessionModel);

        Model databaseModel = session.find(Model.class, sessionModel.getId());

        session.getTransaction().commit();

        assertEquals(sessionModel, databaseModel);
    }
}