package com.savelyeva.dao;

import com.savelyeva.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CarDaoImpl extends BaseDaoImpl<Long, Car> implements CarDao {

}