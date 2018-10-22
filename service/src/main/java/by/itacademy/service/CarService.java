package by.itacademy.service;

import by.itacademy.dao.CarDao;
import by.itacademy.model.Car;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarService {

    private static final CarService INSTANCE = new CarService();

    public Car getDefaultCar() {
        return CarDao.getInstance().getDefaultCar();
    }

    public static CarService getInstance() {
        return INSTANCE;
    }
}
