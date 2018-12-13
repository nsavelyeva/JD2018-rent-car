package com.savelyeva.database.dao;

import com.savelyeva.database.model.Street;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StreetDaoImpl extends BaseDaoImpl<Long, Street> implements StreetDao {

}