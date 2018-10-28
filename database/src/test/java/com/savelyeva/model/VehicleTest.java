package com.savelyeva.model;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class VehicleTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Vehicle ").executeUpdate();
            session.createQuery("delete from Car ").executeUpdate();
            session.createQuery("delete from Lorry ").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Audit audit = new Audit(Instant.now());
        System.out.println(audit);
        Vehicle vehicleCar = new Vehicle(1, 1, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, audit);
        Vehicle vehicleLorry = new Vehicle(5, 5, Transmission.MECHANIC, Short.valueOf("2013"), 100000, 500, audit);
        session.save(vehicleCar);
        session.save(vehicleLorry);
        Car car = new Car(vehicleCar, Short.valueOf("4"), Short.valueOf("300"));
        Lorry lorry = new Lorry(vehicleLorry, 450000);
        Serializable savedCarId = session.save(car);
        Serializable savedLorryId = session.save(lorry);
        session.getTransaction().commit();
        assertNotNull(savedCarId);
        assertNotNull(savedLorryId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Audit audit = new Audit(Instant.now());
        Vehicle vehicleCar = new Vehicle(1, 1, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, audit);
        Vehicle vehicleLorry = new Vehicle(5, 5, Transmission.MECHANIC, Short.valueOf("2013"), 100000, 500, audit);
        session.save(vehicleCar);
        session.save(vehicleLorry);
        Car car = new Car(vehicleCar, Short.valueOf("4"), Short.valueOf("300"));
        Lorry lorry = new Lorry(vehicleLorry, 450000);
        session.save(car);
        session.save(lorry);
        List<Vehicle> sessionVehicles = session.createQuery("select e from Vehicle e", Vehicle.class).list();
        System.out.println(sessionVehicles);
        session.evict(car);
        session.evict(lorry);
        session.evict(vehicleCar);
        session.evict(vehicleLorry);
        List<Vehicle> databaseVehicles = session.createQuery("select e from Vehicle e", Vehicle.class).list();
        session.getTransaction().commit();
        for (int i = 0; i < databaseVehicles.size(); i++) {
            assertEquals(sessionVehicles.get(i), databaseVehicles.get(i));
        }
    }
}
