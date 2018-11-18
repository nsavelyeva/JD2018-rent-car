package com.savelyeva.service;

import com.savelyeva.dao.PersonDao;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.PersonDto;
import com.savelyeva.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    @Autowired
    private PersonService personService;

    private final PersonDao personDao;

    @Transactional
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Transactional
    public Optional<Person> find(Long id) {
        return personDao.find(id);
    }

    @Transactional
    public List<Person> findByAttributes(PersonDto personDto, PaginationDto paginationDto) {
        return personDao.findByAttributes(personDto, paginationDto);
    }

}
