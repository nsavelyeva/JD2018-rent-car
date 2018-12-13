package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.RentDaoImpl;
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
import java.time.Duration;
import java.time.Instant;

import static java.lang.Boolean.TRUE;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class RentTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        RentDaoImpl rentDaoImpl = ApplicationContextHolder.getBean("rentDaoImpl", RentDaoImpl.class);
        sessionFactory = rentDaoImpl.getSessionFactory();
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

        Instant now = Instant.now();
        Audit somePeriod = Audit.builder()
                .createdDate(now.minus(Duration.ofDays(2)))
                .updatedDate(now)
                .build();

        Person person = session.find(Person.class, 1L);
        Car car = session.find(Car.class, 1L);

        Rent sessionRent = Rent.builder()
                .person(person)
                .vehicle(car)
                .rentPeriod(somePeriod)
                .cost(1000)
                .paid(TRUE)
                .build();
        Serializable savedId = session.save(sessionRent);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Instant now = Instant.now();
        Audit somePeriod = Audit.builder()
                .createdDate(now.minus(Duration.ofDays(2)))
                .updatedDate(now)
                .build();

        Person person = session.find(Person.class, 2L);
        Lorry lorry = session.find(Lorry.class, 1L);

        Rent sessionRent = Rent.builder()
                .person(person)
                .vehicle(lorry)
                .rentPeriod(somePeriod)
                .cost(1000)
                .paid(TRUE)
                .build();
        session.save(sessionRent);

        session.evict(sessionRent);

        Rent databaseRent = session.find(Rent.class, sessionRent.getId());

        session.getTransaction().commit();

        assertEquals(sessionRent, databaseRent);
    }
}