package com.savelyeva.service.services;

import com.savelyeva.database.dao.DrivingLicenseDao;
import com.savelyeva.database.model.DrivingLicense;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrivingLicenseService {

    private final DrivingLicenseDao drivingLicenseDao;

    @Transactional
    public List<DrivingLicense> getAllDrivingLicenses() {
        return drivingLicenseDao.findAll();
    }

}
