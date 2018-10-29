package com.savelyeva;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionManager {

    public static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

}
