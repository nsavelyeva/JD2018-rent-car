package com.savelyeva.dao;

import com.savelyeva.model.DrivingLicense;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DrivingLicenseDaoImpl extends BaseDaoImpl<Long, DrivingLicense> implements DrivingLicenseDao {

}