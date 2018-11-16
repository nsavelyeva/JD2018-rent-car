package com.savelyeva.service;

import com.savelyeva.dao.VehicleDao;
import com.savelyeva.dao.VehicleDaoImpl;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.VehicleDto;
import com.savelyeva.model.Vehicle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleService {

    private static final VehicleService INSTANCE = new VehicleService();

    public static VehicleService getInstance() {
        return INSTANCE;
    }

    public List<Vehicle> findAll() {
        VehicleDao vehicleDao = VehicleDaoImpl.getInstance();
        List<Vehicle> vehicles = vehicleDao.findAll();
        return vehicles;
    }

    public Vehicle find(Long id) {
        VehicleDao vehicleDao = VehicleDaoImpl.getInstance();
        Vehicle vehicle = vehicleDao.find(id);
        return vehicle;
    }

    public List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto) {
        VehicleDao vehicleDao = VehicleDaoImpl.getInstance();
        List<Vehicle> vehicles = vehicleDao.findByAttributes(vehicleDto, paginationDto);
        return vehicles;
    }

}
