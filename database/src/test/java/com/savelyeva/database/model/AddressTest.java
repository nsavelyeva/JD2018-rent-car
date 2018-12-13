package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.AddressDaoImpl;
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
public class AddressTest {

    //@Autowired
    //private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        AddressDaoImpl addressDaoImpl = ApplicationContextHolder.getBean("addressDaoImpl", AddressDaoImpl.class);
        sessionFactory = addressDaoImpl.getSessionFactory();
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
        session = sessionFactory.getCurrentSession();
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