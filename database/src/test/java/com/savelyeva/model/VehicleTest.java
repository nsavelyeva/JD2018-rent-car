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
        Color color = Color.builder()
                .color("yellow")
                .build();
        session.save(color);

        Manufacturer manufacturerCar = Manufacturer.builder()
                .manufacturer("Nissan")
                .build();
        session.save(manufacturerCar);

        Model modelCar = Model.builder()
                .manufacturer(manufacturerCar)
                .model("Juke")
                .build();
        session.save(modelCar);

        Vehicle vehicleCar = new Vehicle(modelCar, color, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, audit);
        session.save(vehicleCar);

        Manufacturer manufacturerLorry = Manufacturer.builder()
                .manufacturer("БелАЗ")
                .build();
        session.save(manufacturerLorry);

        Model modelLorry = Model.builder()
                .manufacturer(manufacturerLorry)
                .model("БелАЗ-75710")
                .build();
        session.save(modelLorry);

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
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Audit audit = new Audit(Instant.now());

        Color color = Color.builder()
                .color("жёлтый")
                .build();
        session.save(color);

        Manufacturer manufacturerCar = Manufacturer.builder()
                .manufacturer("Toyota")
                .build();
        session.save(manufacturerCar);

        Model modelCar = Model.builder()
                .manufacturer(manufacturerCar)
                .model("RAV4")
                .build();
        session.save(modelCar);

        Vehicle vehicleCar = new Vehicle(modelCar, color, Transmission.AUTOMATIC, Short.valueOf("2017"), 12000, 60, audit);
        session.save(vehicleCar);

        Manufacturer manufacturerLorry = Manufacturer.builder()
                .manufacturer("МАЗ")
                .build();
        session.save(manufacturerLorry);

        Model modelLorry = Model.builder()
                .manufacturer(manufacturerLorry)
                .model("МАЗ-6517 X9-410")
                .build();
        session.save(modelLorry);

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
