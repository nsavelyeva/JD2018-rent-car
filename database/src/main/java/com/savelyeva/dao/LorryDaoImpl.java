package com.savelyeva.dao;

import com.savelyeva.model.Lorry;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LorryDaoImpl extends BaseDaoImpl<Long, Lorry> implements LorryDao {

}