package com.savelyeva.service;

import com.savelyeva.model.DrivingLicense;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DrivingLicenseService {

    private static final DrivingLicenseService INSTANCE = new DrivingLicenseService();

    public List<DrivingLicense> getAllDrivingLicenses() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        List<DrivingLicense> drivingLicenses = session.createQuery("select d from DrivingLicense d", DrivingLicense.class).list();
        return drivingLicenses;
    }

    public static DrivingLicenseService getInstance() {
        return INSTANCE;
    }
}
