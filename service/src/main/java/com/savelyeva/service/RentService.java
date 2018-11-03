package com.savelyeva.service;

import com.savelyeva.model.Rent;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentService {

    private static final RentService INSTANCE = new RentService();

    public List<Rent> getAllRents() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        List<Rent> rents = session.createQuery("select r from Rent r", Rent.class).list();
        return rents;
    }

    public static RentService getInstance() {
        return INSTANCE;
    }
}
