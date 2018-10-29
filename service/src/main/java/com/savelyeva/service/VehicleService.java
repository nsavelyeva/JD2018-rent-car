package com.savelyeva.service;

import com.savelyeva.model.Vehicle;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleService {

    private static final VehicleService INSTANCE = new VehicleService();

    public List<Vehicle> getAllVehicles() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        List<Vehicle> vehicles = session.createQuery("select e from Vehicle e", Vehicle.class).list();
        return vehicles;
    }

    public static VehicleService getInstance() {
        return INSTANCE;
    }
}
