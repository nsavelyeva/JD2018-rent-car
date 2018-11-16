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

public class ColorTest {

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

        Color sessionColor = Color.builder()
                .color("blue")
                .build();
        Serializable savedId = session.save(sessionColor);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Color sessionColor = Color.builder()
                .color("black")
                .build();
        session.save(sessionColor);

        session.evict(sessionColor);

        Color databaseColor = session.find(Color.class, sessionColor.getId());

        session.getTransaction().commit();

        assertEquals(sessionColor, databaseColor);
    }
}