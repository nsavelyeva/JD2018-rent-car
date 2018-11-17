package com.savelyeva.model;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class VehicleTest {
/*
    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private LorryDao lorryDao;

    @Autowired
    private ModelDao modelDao;


    @Autowired
    private ColorDao colorDao;

    @BeforeClass
    public static void initDb() {
        CreateTestData.getInstance().importTestData();
    }

    @AfterClass
    public static void closeFactory() {
        //sessionFactory.close();
    }
    @Test
    public void checkSaveEntity() {
        Audit audit = new Audit(Instant.now());
        Color color = colorDao.find(1).get();
        Model modelCar = modelDao.find(1).get();
        Model modelLorry = modelDao.find(2).get();

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
    }*/
}
