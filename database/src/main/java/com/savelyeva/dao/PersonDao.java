package com.savelyeva.dao;

import com.savelyeva.model.Person;

public interface PersonDao extends BaseDao<Long, Person> {

    Person findByFirstName(String firstName);
}
