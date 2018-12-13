package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.VehicleDaoImpl;
import com.savelyeva.database.data.CreateTestData;
import com.savelyeva.database.util.ApplicationContextHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class VehicleTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        VehicleDaoImpl vehicleDaoImpl = ApplicationContextHolder.getBean("vehicleDaoDaoImpl", VehicleDaoImpl.class);
        sessionFactory = vehicleDaoImpl.getSessionFactory();
        testData = ApplicationContextHolder.getBean("createTestData", CreateTestData.class);
        testData.importTestData();
    }

    @AfterClass
    public static void closeFactory() {
        //sessionFactory.close();
        //testData.removeTestData(sessionFactory);
    }

    @Test
    public void checkSaveEntity() {
        session = sessionFactory.getCurrentSession();
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

        assertNotNull(savedCarId);
        assertNotNull(savedLorryId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
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

        for (int i = 0; i < databaseVehicles.size(); i++) {
            assertEquals(sessionVehicles.get(i), databaseVehicles.get(i));
        }
    }
}
