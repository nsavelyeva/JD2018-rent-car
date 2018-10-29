package com.savelyeva.service;

import com.savelyeva.ConnectionManager;
import com.savelyeva.model.Person;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();

    public List<Person> getAllPersons() {
        @Cleanup Session session = ConnectionManager.FACTORY.openSession();
        List<Person> persons = session.createQuery("select p from Person p", Person.class).list();
        return persons;
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }
}
