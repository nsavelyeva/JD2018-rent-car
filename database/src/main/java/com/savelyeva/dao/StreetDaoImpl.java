package com.savelyeva.dao;

import com.savelyeva.model.Street;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StreetDaoImpl extends BaseDaoImpl<Long, Street> implements StreetDao {

}