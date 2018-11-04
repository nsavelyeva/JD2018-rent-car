package com.savelyeva.dao;

import com.savelyeva.connection.ConnectionManager;
import com.savelyeva.model.Audit;
import com.savelyeva.model.Person;
import com.savelyeva.model.Role;
import com.savelyeva.util.CreateTestData;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

public class PersonDaoTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void initDb() {
        sessionFactory = ConnectionManager.getFactory();
        CreateTestData.getInstance().importTestData(sessionFactory);
    }

    @AfterClass
    public static void closeFactory() {
        sessionFactory.close();
    }

    @Test
    public void checkSave() {
        PersonDao personDao = PersonDaoImpl.getInstance();

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = RoleDaoImpl.getInstance().find(1);
        Person person = Person.builder()
                .role(role)
                .login("user")
                .password("secret")
                .email("user@example.com")
                .audit(audit)
                .build();
        Serializable savedId = personDao.save(person);

        Person personFound = personDao.find((Long) savedId);
        personDao.delete(personFound);

        assertNotNull(savedId);
    }

    @Test
    public void checkUpdate() {
        PersonDao personDao = PersonDaoImpl.getInstance();

        Person person = personDao.find(1L);
        String oldPassword = person.getPassword();
        String newPassword = oldPassword + "updated";
        person.setPassword(newPassword);

        personDao.update(person);
        Person personUpdated = personDao.find(1L);

        assertEquals(personUpdated.getPassword(), newPassword);
    }

    @Test
    public void checkDelete() {
        PersonDao personDao = PersonDaoImpl.getInstance();

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = RoleDaoImpl.getInstance().find(1);
        Person person = Person.builder()
                .role(role)
                .login("user")
                .password("secret")
                .email("user@example.com")
                .audit(audit)
                .build();
        Serializable savedId = personDao.save(person);

        Person personFound = personDao.find((Long) savedId);
        personDao.delete(personFound);

        Person deletedPerson = personDao.find((Long) savedId);
        assertNull(deletedPerson);
    }

    @Test
    public void checkFindById() {
        PersonDao personDao = PersonDaoImpl.getInstance();
        Person person = personDao.find(1L);
        assertNotNull(person);
    }

    @Test
    public void checkFindAll() {
        PersonDao personDao = PersonDaoImpl.getInstance();
        List<Person> persons = personDao.findAll();
        assertEquals(2, persons.size());
    }

    @Test
    public void checkFindByAttributes() {
        PersonDao personDao = PersonDaoImpl.getInstance();
        List<Person> persons = personDao.findByAttributes("", "FEMALE", "", 5, 1);
        assertEquals(2, persons.size());
    }
}
