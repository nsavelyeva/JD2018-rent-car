package com.savelyeva.service;

import com.savelyeva.model.Address;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressService {

    private static final AddressService INSTANCE = new AddressService();

    public List<Address> getAllAddresses() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        List<Address> addresses = session.createQuery("select e from Address e", Address.class).list();
        return addresses;
    }

    public static AddressService getInstance() {
        return INSTANCE;
    }
}
