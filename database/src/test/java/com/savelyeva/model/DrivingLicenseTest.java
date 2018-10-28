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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class DrivingLicenseTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM DrivingLicense").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Audit drivingPeriod = Audit.builder().created_date(Instant.now()).updated_date(Instant.now()).build();
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
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Audit drivingPeriod = Audit.builder().created_date(Instant.now()).updated_date(Instant.now()).build();
        DrivingLicense sessionDrivingLicense = DrivingLicense.builder()
                .serialNumber("FF123456")
                .drivingPeriod(drivingPeriod)
                .category(Category.B)
                .build();
        session.save(sessionDrivingLicense);
        session.evict(sessionDrivingLicense);
        DrivingLicense databaseDrivingLicense = session.find(DrivingLicense.class, sessionDrivingLicense.getId());
        session.getTransaction().commit();
        assertEquals(sessionDrivingLicense, databaseDrivingLicense);
    }
}