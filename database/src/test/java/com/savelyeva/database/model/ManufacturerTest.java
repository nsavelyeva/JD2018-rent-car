package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.ManufacturerDaoImpl;
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
public class ManufacturerTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        ManufacturerDaoImpl manufacturerDaoImpl = ApplicationContextHolder.getBean("manufacturerDaoImpl", ManufacturerDaoImpl.class);
        sessionFactory = manufacturerDaoImpl.getSessionFactory();
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

        Manufacturer sessionManufacturer = Manufacturer.builder()
                .manufacturer("Peugeot")
                .build();
        Serializable savedId = session.save(sessionManufacturer);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
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