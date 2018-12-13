package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.CityDaoImpl;
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
public class CityTest {

    //@Autowired
    //private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        CityDaoImpl cityDaoImpl = ApplicationContextHolder.getBean("cityDaoImpl", CityDaoImpl.class);
        sessionFactory = cityDaoImpl.getSessionFactory();
        CreateTestData testData = ApplicationContextHolder.getBean("createTestData", CreateTestData.class);
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

        Country country = session.find(Country.class, 1);

        City sessionCity = City.builder()
                .country(country)
                .city("Каир")
                .build();
        Serializable savedId = session.save(sessionCity);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Country country = session.find(Country.class, 1);

        City sessionCity = City.builder()
                .country(country)
                .city("Орша")
                .build();
        session.save(sessionCity);

        session.evict(sessionCity);

        City databaseCity = session.find(City.class, sessionCity.getId());

        session.getTransaction().commit();

        assertEquals(sessionCity, databaseCity);
    }
}