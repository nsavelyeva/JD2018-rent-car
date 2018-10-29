package com.savelyeva.model;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.time.Instant;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Person").executeUpdate();
        session.createQuery("DELETE FROM Role").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = Role.builder().role("Администратор").build();
        session.save(role);

        Person sessionPerson =  Person.builder()
                .role(role)
                .login("person")
                .password("secret")
                .email("person@example.com")
                .audit(audit)
                .build();
        Serializable savedId = session.save(sessionPerson);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = Role.builder().role("Пользователь").build();
        session.save(role);

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