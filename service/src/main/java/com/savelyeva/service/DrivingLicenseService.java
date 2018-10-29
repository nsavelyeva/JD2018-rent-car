package com.savelyeva.service;

import com.savelyeva.model.DrivingLicense;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DrivingLicenseService {

    private static final DrivingLicenseService INSTANCE = new DrivingLicenseService();

    public List<DrivingLicense> getAllDrivingLicenses() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        List<DrivingLicense> drivingLicenses = session.createQuery("select e from DrivingLicense e", DrivingLicense.class).list();
        return drivingLicenses;
    }

    public static DrivingLicenseService getInstance() {
        return INSTANCE;
    }
}
