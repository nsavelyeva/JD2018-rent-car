package com.savelyeva.dao;

import com.savelyeva.model.Person;

import java.util.List;

public interface PersonDao extends BaseDao<Long, Person> {

    List<Person> findByAttributes(String email, String gender, String foreigners, Integer limit, Integer page);

}
