package com.savelyeva.database.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.VehicleDto;
import com.savelyeva.database.model.QRent;
import com.savelyeva.database.model.QVehicle;
import com.savelyeva.database.model.Vehicle;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class VehicleDaoImpl extends BaseDaoImpl<Long, Vehicle> implements VehicleDao {

    @Override
    public List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto) {
        Session session = this.getSessionFactory().getCurrentSession();

        BooleanBuilder predicate = new BooleanBuilder();
        if (!StringUtils.isEmpty(vehicleDto.getDayPrice())) {
            predicate = predicate.and(QVehicle.vehicle.dayPrice.loe(Integer.parseInt(vehicleDto.getDayPrice())));
        }
        if (!StringUtils.isEmpty(vehicleDto.getTransmission())) {
            predicate = predicate.and(QVehicle.vehicle.transmission.stringValue().eq(vehicleDto.getTransmission()));
        }
        if (!StringUtils.isEmpty(vehicleDto.getAvailable())) {
            predicate = predicate.and(QRent.rent.rentPeriod.updatedDate.before(Instant.now()));
        }
        return new JPAQuery<Vehicle>(session)
                .select(QVehicle.vehicle)
                .from(QVehicle.vehicle)
                .join(QVehicle.vehicle.rents, QRent.rent).on(QVehicle.vehicle.id.eq(QRent.rent.vehicle.id))
                .where(predicate)
                .limit(paginationDto.getLimit())
                .offset(paginationDto.getLimit() * (paginationDto.getPage() - 1))
                .fetch();
    }

}
