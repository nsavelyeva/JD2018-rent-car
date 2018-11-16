package com.savelyeva.dao;

import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.VehicleDto;
import com.savelyeva.model.Vehicle;

import java.util.List;


public interface VehicleDao extends BaseDao<Long, Vehicle> {

    List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto);
}
