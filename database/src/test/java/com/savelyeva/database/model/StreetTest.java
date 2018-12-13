package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.StreetDaoImpl;
import com.savelyeva.database.data.CreateTestData;
import com.savelyeva.database.util.ApplicationContextHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class StreetTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        StreetDaoImpl streetDaoImpl = ApplicationContextHolder.getBean("streetDaoImpl", StreetDaoImpl.class);
        sessionFactory = streetDaoImpl.getSessionFactory();
        testData = ApplicationContextHolder.getBean("createTestData", CreateTestData.class);
        testData.importTestData();
    }

    @AfterClass
    public static void closeFactory() {
        //sessionFactory.close();
        //testData.removeTestData(sessionFactory);
    }

    @Test
    public void checkSaveEntity() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        City city = session.find(City.class, 1L);

        Street sessionStreet = Street.builder()
                .city(city)
                .street("Rapenburgerstraat")
                .build();
        Serializable savedId = session.save(sessionStreet);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        City city = session.find(City.class, 1L);

        Street sessionStreet = Street.builder()
                .city(city)
                .street("Plantage Middenlaan")
                .build();
        session.save(sessionStreet);

        session.evict(sessionStreet);

        Street databaseStreet = session.find(Street.class, sessionStreet.getId());

        session.getTransaction().commit();

        assertEquals(sessionStreet, databaseStreet);
    }
}