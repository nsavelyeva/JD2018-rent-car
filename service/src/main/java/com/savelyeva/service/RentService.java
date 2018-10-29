package com.savelyeva.service;

import com.savelyeva.ConnectionManager;
import com.savelyeva.model.Rent;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentService {

    private static final RentService INSTANCE = new RentService();

    public List<Rent> getAllRents() {
        @Cleanup Session session = ConnectionManager.FACTORY.openSession();
        List<Rent> rents = session.createQuery("select r from Rent r", Rent.class).list();
        return rents;
    }

    public static RentService getInstance() {
        return INSTANCE;
    }
}
