package com.savelyeva.dao;

import com.savelyeva.model.Model;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ModelDaoImpl extends BaseDaoImpl<Integer, Model> implements ModelDao {

}