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
import java.time.Instant;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PersonTest {

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
        @Cleanup Session session = sessionFactory.openSession();
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