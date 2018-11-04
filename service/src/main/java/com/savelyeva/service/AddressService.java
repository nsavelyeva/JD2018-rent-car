package com.savelyeva.service;

import com.savelyeva.model.Address;
import com.savelyeva.connection.ConnectionManager;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressService {

    private static final AddressService INSTANCE = new AddressService();

    public static AddressService getInstance() {
        return INSTANCE;
    }

    public List<Address> getAllAddresses() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        List<Address> addresses = session.createQuery("select a from Address a", Address.class).list();
        return addresses;
    }

}
