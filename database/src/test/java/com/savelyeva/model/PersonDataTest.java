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

public class PersonDataTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM PersonData").executeUpdate();
        session.createQuery("DELETE FROM Person").executeUpdate();
        session.createQuery("DELETE FROM Role").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Role role = Role.builder().role("Администратор").build();
        session.save(role);
        Instant now = Instant.now();
        Audit audit = Audit.builder().created_date(now).build();
        Person person = Person.builder().role(role).login("person").password("secret").email("person@example.com").audit(audit).build();
        session.save(person);
        PersonData sessionPersonData = PersonData.builder().person(person).firstName("Natallia").lastName("Savelyeva").birthDate(now).gender(Gender.FEMALE).passport("AB123456").build();
        Serializable savedId = session.save(sessionPersonData);
        session.getTransaction().commit();
        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Role role = Role.builder().role("Пользователь").build();
        session.save(role);
        Instant now = Instant.now();
        Audit audit =  Audit.builder().created_date(Instant.now()).build();
        Person person = Person.builder().role(role).login("natallia").password("secret").email("natallia@example.com").audit(audit).build();
        session.save(person);
        PersonData sessionPersonData = PersonData.builder().person(person).firstName("Natallia").lastName("Savelyeva").birthDate(now).gender(Gender.FEMALE).passport("CD123456").build();
        session.save(sessionPersonData);
        session.evict(sessionPersonData);
        PersonData databasePersonData = session.find(PersonData.class, sessionPersonData.getId());
        session.getTransaction().commit();
        assertEquals(sessionPersonData, databasePersonData);
    }
}