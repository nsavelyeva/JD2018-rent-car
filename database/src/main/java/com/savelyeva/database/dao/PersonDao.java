package com.savelyeva.database.dao;

import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.PersonDto;
import com.savelyeva.database.model.Person;

import java.util.List;


public interface PersonDao extends BaseDao<Long, Person> {

    List<Person> findByAttributes(PersonDto personDto, PaginationDto paginationDto);
}
