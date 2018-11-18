package com.savelyeva.service;

import com.savelyeva.dao.AddressDao;
import com.savelyeva.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {

    @Autowired
    private AddressService addressService;

    private final AddressDao addressDao;

    @Transactional
    public List<Address> getAllAddresses() {
        return addressDao.findAll();
    }

}
