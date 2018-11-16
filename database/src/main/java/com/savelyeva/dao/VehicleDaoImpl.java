package com.savelyeva.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.VehicleDto;
import com.savelyeva.model.QRent;
import com.savelyeva.model.QVehicle;
import com.savelyeva.model.Vehicle;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.time.Instant;
import java.util.List;

import static com.savelyeva.connection.ConnectionManager.getSession;

public class VehicleDaoImpl extends BaseDaoImpl<Long, Vehicle> implements VehicleDao {

    private static final VehicleDao INSTANCE = new VehicleDaoImpl();

    public static VehicleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Vehicle> findByAttributes(VehicleDto vehicleDto, PaginationDto paginationDto) {
        @Cleanup Session session = getSession();

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
        /*
        return new JPAQuery<Vehicle>(session)
                .select(QVehicle.vehicle)
                .from(QVehicle.vehicle)
                .join(QVehicle.vehicle, QRent.rent.vehicle)
                .where(predicate)
                .limit(paginationDto.getLimit())
                .offset(paginationDto.getLimit() * (paginationDto.getPage() - 1))
                .fetch();

         */
    }

}
