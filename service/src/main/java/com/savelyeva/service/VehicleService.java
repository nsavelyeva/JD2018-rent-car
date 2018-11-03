package com.savelyeva.service;

import com.savelyeva.model.Vehicle;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleService {

    private static final VehicleService INSTANCE = new VehicleService();

    public List<Vehicle> getAllVehicles() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        List<Vehicle> vehicles = session.createQuery("select v from Vehicle v", Vehicle.class).list();
        return vehicles;
    }

    public static VehicleService getInstance() {
        return INSTANCE;
    }
}
