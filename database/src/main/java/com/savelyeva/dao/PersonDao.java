package com.savelyeva.dao;

import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.PersonDto;
import com.savelyeva.model.Person;

import java.util.List;


public interface PersonDao extends BaseDao<Long, Person> {

    List<Person> findByAttributes(PersonDto personDto, PaginationDto paginationDto);
}
