package com.savelyeva.dao;

import com.savelyeva.model.Car;

public class CarDaoImpl extends BaseDaoImpl<Long, Car> implements CarDao {

    private static final CarDao INSTANCE = new CarDaoImpl();

    public static CarDao getInstance() {
        return INSTANCE;
    }

}
