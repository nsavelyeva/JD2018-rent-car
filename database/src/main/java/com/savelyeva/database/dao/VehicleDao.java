package com.savelyeva.database.dao;

import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.VehicleDto;
import com.savelyeva.database.model.Vehicle;

import java.util.List;


public interface VehicleDao extends BaseDao<Long, Vehicle> {

    List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto);
}
