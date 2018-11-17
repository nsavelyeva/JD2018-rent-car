package com.savelyeva.dao;

import com.savelyeva.model.PersonData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDataDaoImpl extends BaseDaoImpl<Long, PersonData> implements PersonDataDao {

}
