package com.savelyeva.database.dao;

import com.savelyeva.database.model.DrivingLicense;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DrivingLicenseDaoImpl extends BaseDaoImpl<Long, DrivingLicense> implements DrivingLicenseDao {

}