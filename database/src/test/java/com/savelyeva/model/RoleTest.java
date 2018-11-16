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

public class RoleTest {

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

        Role sessionRole = Role.builder()
                .role("Администратор")
                .build();
        Serializable savedId = session.save(sessionRole);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Role sessionRole = Role.builder()
                .role("Модератор")
                .build();
        session.save(sessionRole);

        session.evict(sessionRole);

        Role databaseRole = session.find(Role.class, sessionRole.getId());

        session.getTransaction().commit();

        assertEquals(sessionRole, databaseRole);
    }
}