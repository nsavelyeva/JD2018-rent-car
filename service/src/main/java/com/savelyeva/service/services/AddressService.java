package com.savelyeva.service.services;

import com.savelyeva.database.dao.AddressDao;
import com.savelyeva.database.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {

    private final AddressDao addressDao;

    @Transactional
    public List<Address> getAllAddresses() {
        return addressDao.findAll();
    }

}
