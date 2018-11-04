package com.savelyeva.service;

import com.savelyeva.dao.PersonDao;
import com.savelyeva.dao.PersonDaoImpl;
import com.savelyeva.model.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();

    public static PersonService getInstance() {
        return INSTANCE;
    }

    public List<Person> findAll() {
        PersonDao personDao = PersonDaoImpl.getInstance();
        List<Person> persons = personDao.findAll();
        return persons;
    }

    public Person find(Long id) {
        PersonDao personDao = PersonDaoImpl.getInstance();
        Person person = personDao.find(id);
        return person;
    }

    public List<Person> findByAttributes(String email, String gender, String country, Integer limit, Integer page) {
        PersonDao personDao = PersonDaoImpl.getInstance();
        List<Person> persons = personDao.findByAttributes(email, gender, country, limit, page);
        return persons;
    }

}
