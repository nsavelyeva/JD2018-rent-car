package com.savelyeva.dao;

import com.savelyeva.model.Person;

public class PersonDaoImpl extends BaseDaoImpl<Long, Person> implements PersonDao {

    private static final PersonDao INSTANCE = new PersonDaoImpl();

    @Override
    public Person findByFirstName(String firstName) {
        return null;
    }

    public static PersonDao getInstance() {
        return INSTANCE;
    }
}
