package com.savelyeva.model;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class DrivingLicenseTest {
/*
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

        Audit drivingPeriod = Audit.builder()
                .createdDate(Instant.now())
                .updatedDate(Instant.now())
                .build();
        DrivingLicense sessionDrivingLicense = DrivingLicense.builder()
                .serialNumber("FF123456")
                .drivingPeriod(drivingPeriod)
                .category(Category.B)
                .build();
        Serializable savedId = session.save(sessionDrivingLicense);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Audit drivingPeriod = Audit.builder()
                .createdDate(Instant.now())
                .updatedDate(Instant.now())
                .build();
        DrivingLicense sessionDrivingLicense = DrivingLicense.builder()
                .serialNumber("AB123456")
                .drivingPeriod(drivingPeriod)
                .category(Category.B)
                .build();
        session.save(sessionDrivingLicense);

        session.evict(sessionDrivingLicense);

        DrivingLicense databaseDrivingLicense = session.find(DrivingLicense.class, sessionDrivingLicense.getId());

        session.getTransaction().commit();

        assertEquals(sessionDrivingLicense, databaseDrivingLicense);
    }*/
}