package com.savelyeva.database.dao;

import com.savelyeva.database.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ModelDaoImpl extends BaseDaoImpl<Integer, Model> implements ModelDao {

}