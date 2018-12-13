package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.PersonDaoImpl;
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
import java.time.Instant;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class PersonDataTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        PersonDaoImpl personDaoImpl = ApplicationContextHolder.getBean("personDaoImpl", PersonDaoImpl.class);
        sessionFactory = personDaoImpl.getSessionFactory();
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

        Person person = session.find(Person.class, 1L);

        PersonData sessionPersonData = PersonData.builder()
                .person(person)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("AB123456")
                .build();
        Serializable savedId = session.save(sessionPersonData);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = session.find(Person.class, 2L);

        PersonData sessionPersonData = PersonData.builder()
                .person(person)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("CD123456")
                .build();
        session.save(sessionPersonData);

        session.evict(sessionPersonData);

        PersonData databasePersonData = session.find(PersonData.class, sessionPersonData.getId());

        session.getTransaction().commit();

        assertEquals(sessionPersonData, databasePersonData);
    }
}