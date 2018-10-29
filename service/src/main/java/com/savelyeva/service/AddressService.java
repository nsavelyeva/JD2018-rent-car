package com.savelyeva.service;

import com.savelyeva.ConnectionManager;
import com.savelyeva.model.Address;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressService {

    private static final AddressService INSTANCE = new AddressService();

    public List<Address> getAllAddresses() {
        @Cleanup Session session = ConnectionManager.FACTORY.openSession();
        List<Address> addresses = session.createQuery("select a from Address a", Address.class).list();
        return addresses;
    }

    public static AddressService getInstance() {
        return INSTANCE;
    }
}
