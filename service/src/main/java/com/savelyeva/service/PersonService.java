package com.savelyeva.service;

import com.savelyeva.dao.PersonDaoImplDraft;
import com.savelyeva.model.Person;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();

    public List<Person> getAllPersons() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        //List<Person> persons = session.createQuery("select p from Person p", Person.class).list();
        List<Person> persons = PersonDaoImplDraft.getInstance().findAll(session);
        return persons;
    }

    public Person getPerson(Long id) {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        Person person = PersonDaoImplDraft.getInstance().findById(session, id);
        return person;
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }
}
