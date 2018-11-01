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

public class AddressTest {

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

        Street street = session.find(Street.class, 1L);

        Address sessionAddress = Address.builder()
                .street(street)
                .building("121/1")
                .flat("12a")
                .build();
        Serializable savedId = session.save(sessionAddress);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Street street = session.find(Street.class, 1L);

        Address sessionAddress = Address.builder()
                .street(street)
                .building("121/1")
                .flat("12a")
                .build();
        session.save(sessionAddress);

        session.evict(sessionAddress);

        Address databaseAddress = session.find(Address.class, sessionAddress.getId());
        session.getTransaction().commit();

        assertEquals(sessionAddress, databaseAddress);
    }
}