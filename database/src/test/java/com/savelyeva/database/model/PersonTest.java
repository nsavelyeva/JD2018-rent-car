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
public class PersonTest {

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

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = session.find(Role.class, 1);

        Person sessionPerson = Person.builder()
                .role(role)
                .login("user")
                .password("secret")
                .email("user@example.com")
                .audit(audit)
                .build();
        Serializable savedId = session.save(sessionPerson);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = session.find(Role.class, 1);

        Person sessionPerson =  Person.builder()
                .role(role)
                .login("customer")
                .password("secret")
                .email("customer@example.com")
                .audit(audit)
                .build();
        session.save(sessionPerson);

        session.evict(sessionPerson);

        Person databasePerson = session.find(Person.class, sessionPerson.getId());

        session.getTransaction().commit();

        assertEquals(sessionPerson, databasePerson);
    }
}