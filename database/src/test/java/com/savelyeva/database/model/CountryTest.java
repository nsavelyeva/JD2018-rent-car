package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.CountryDaoImpl;
import com.savelyeva.database.data.CreateTestData;
import com.savelyeva.database.util.ApplicationContextHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class CountryTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        CountryDaoImpl countryDaoImpl = ApplicationContextHolder.getBean("countryDaoImpl", CountryDaoImpl.class);
        sessionFactory = countryDaoImpl.getSessionFactory();
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

        Country sessionCountry = Country.builder()
                .country("Египет")
                .build();
        Serializable savedId = session.save(sessionCountry);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Country sessionCountry = Country.builder()
                .country("Беларусь")
                .build();
        session.save(sessionCountry);

        session.evict(sessionCountry);

        Country databaseCountry = session.find(Country.class, sessionCountry.getId());

        session.getTransaction().commit();

        assertEquals(sessionCountry, databaseCountry);
    }
}