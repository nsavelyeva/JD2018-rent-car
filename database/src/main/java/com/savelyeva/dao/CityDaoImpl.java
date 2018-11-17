package com.savelyeva.dao;

import com.savelyeva.model.City;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CityDaoImpl extends BaseDaoImpl<Long, City> implements CityDao {

}