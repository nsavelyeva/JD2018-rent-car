package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.DrivingLicenseDaoImpl;
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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class DrivingLicenseTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        DrivingLicenseDaoImpl drivingLicenseDaoImpl = ApplicationContextHolder.getBean("drivingLicenseDaoImpl", DrivingLicenseDaoImpl.class);
        sessionFactory = drivingLicenseDaoImpl.getSessionFactory();
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
        session = sessionFactory.getCurrentSession();
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
    }
}