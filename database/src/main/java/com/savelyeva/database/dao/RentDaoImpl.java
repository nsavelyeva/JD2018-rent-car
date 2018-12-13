package com.savelyeva.database.dao;

import com.savelyeva.database.model.Rent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RentDaoImpl extends BaseDaoImpl<Long, Rent> implements RentDao {

}