package com.savelyeva.service;

import com.savelyeva.dao.CarDao;
import com.savelyeva.dao.CarDaoImpl;
import com.savelyeva.model.Car;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    @Autowired
    private CarService carService;

    private final CarDao carDao;

    /*@Transactional
    public Long save(Car car) {
        return carDao.save(car);
    }*/

    @Transactional
    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    @Transactional
    public Optional<Car> find(Long id) {
        return carDao.find(id);
    }
}
