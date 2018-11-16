package com.savelyeva.service;

import com.savelyeva.dao.CarDao;
import com.savelyeva.dao.CarDaoImpl;
import com.savelyeva.model.Car;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarService {

    private static final CarService INSTANCE = new CarService();

    public static CarService getInstance() {
        return INSTANCE;
    }

    public List<Car> getAllCars() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        List<Car> cars = session.createQuery("select c from Car c", Car.class).list();
        return cars;
    }

    public Car find(Long id) {
        CarDao carDao = CarDaoImpl.getInstance();
        Car car = carDao.find(id);
        return car;
    }
}
