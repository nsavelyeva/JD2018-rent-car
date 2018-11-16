package com.savelyeva.service;

import com.savelyeva.dao.PersonDao;
import com.savelyeva.dao.PersonDaoImpl;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.PersonDto;
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

    public List<Person> findByAttributes(PersonDto personDto, PaginationDto paginationDto) {
        PersonDao personDao = PersonDaoImpl.getInstance();
        List<Person> persons = personDao.findByAttributes(personDto, paginationDto);
        return persons;
    }

}
