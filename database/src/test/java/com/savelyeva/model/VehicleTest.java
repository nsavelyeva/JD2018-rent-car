package com.savelyeva.model;

import com.savelyeva.util.CreateTestData;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class VehicleTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        CreateTestData.getInstance().importTestData(sessionFactory);
    }

    @AfterClass
    public static void closeFactory() {
        sessionFactory.close();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Audit audit = new Audit(Instant.now());
        Color color = session.find(Color.class, 1);
        Model modelCar = session.find(Model.class, 1);
        Model modelLorry = session.find(Model.class, 2);

        Vehicle vehicleCar = new Vehicle(modelCar, color, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, audit);
        session.save(vehicleCar);

        Vehicle vehicleLorry = new Vehicle(modelLorry, color, Transmission.MECHANIC, Short.valueOf("2013"), 100000, 500, audit);
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
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Audit audit = new Audit(Instant.now());
        Color color = session.find(Color.class, 1);
        Model modelCar = session.find(Model.class, 1);
        Model modelLorry = session.find(Model.class, 2);

        Vehicle vehicleCar = new Vehicle(modelCar, color, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, audit);
        session.save(vehicleCar);

        Vehicle vehicleLorry = new Vehicle(modelLorry, color, Transmission.MECHANIC, Short.valueOf("2013"), 100000, 500, audit);
        session.save(vehicleLorry);

        Car car = new Car(vehicleCar, Short.valueOf("4"), Short.valueOf("300"));
        Lorry lorry = new Lorry(vehicleLorry, 450000);

        session.save(car);
        session.save(lorry);

        List<Vehicle> sessionVehicles = session.createQuery("select e from Vehicle e", Vehicle.class).list();

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
