package com.savelyeva.service;

import com.savelyeva.model.Person;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();

    public List<Person> getAllPersons() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        List<Person> persons = session.createQuery("select e from Person e", Person.class).list();
        return persons;
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }
}
