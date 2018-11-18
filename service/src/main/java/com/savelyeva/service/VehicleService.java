package com.savelyeva.service;

import com.savelyeva.dao.VehicleDao;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.VehicleDto;
import com.savelyeva.model.Vehicle;
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

    @Autowired
    private VehicleService vehicleService;

    private final VehicleDao vehicleDao;

    @Transactional
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    public Optional<Vehicle> find(Long id) {
        return vehicleDao.find(id);
    }

    public List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto) {
        return vehicleDao.findByAttributes(vehicleDto, paginationDto);
    }

}
