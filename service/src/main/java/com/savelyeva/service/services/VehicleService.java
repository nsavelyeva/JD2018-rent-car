package com.savelyeva.service.services;

import com.savelyeva.database.dao.VehicleDao;
import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.VehicleDto;
import com.savelyeva.database.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleService {

    private final VehicleDao vehicleDao;

    @Transactional
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    @Transactional
    public Optional<Vehicle> find(Long id) {
        return vehicleDao.find(id);
    }

    @Transactional
    public List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto) {
        return vehicleDao.findByAttributes(vehicleDto, paginationDto);
    }

}
