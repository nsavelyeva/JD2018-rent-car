package com.savelyeva.service;

import com.savelyeva.model.Rent;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentService {

    private static final RentService INSTANCE = new RentService();

    public List<Rent> getAllRents() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        List<Rent> rents = session.createQuery("select e from Rent e", Rent.class).list();
        return rents;
    }

    public static RentService getInstance() {
        return INSTANCE;
    }
}
