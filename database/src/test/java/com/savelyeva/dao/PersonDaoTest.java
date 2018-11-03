package com.savelyeva.dao;

import com.savelyeva.model.Person;
import com.savelyeva.util.CreateTestData;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class PersonDaoTest {

    /*private static SessionFactory sessionFactory;

    @BeforeClass
    public static void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        CreateTestData.getInstance().importTestData(sessionFactory);
    }

    @AfterClass
    public static void closeFactory() {
        sessionFactory.close();
    }*/

    @Test
    public void checkFindById() {
        //@Cleanup Session session = sessionFactory.openSession();
        PersonDao personDao = PersonDaoImpl.getInstance();
        Person person = personDao.find(1L);
        System.out.println(person);
        assertNotNull(person);
    }

}
