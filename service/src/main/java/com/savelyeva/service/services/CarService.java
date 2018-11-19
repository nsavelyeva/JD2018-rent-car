package com.savelyeva.service.services;

import com.savelyeva.database.dao.CarDao;
import com.savelyeva.database.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    private final CarDao carDao;

    @Transactional
    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    @Transactional
    public Optional<Car> find(Long id) {
        return carDao.find(id);
    }
}
