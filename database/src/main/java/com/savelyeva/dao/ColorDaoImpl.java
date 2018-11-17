package com.savelyeva.dao;

import com.savelyeva.model.Color;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ColorDaoImpl extends BaseDaoImpl<Integer, Color> implements ColorDao {

}
