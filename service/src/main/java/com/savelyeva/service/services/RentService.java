package com.savelyeva.service.services;

import com.savelyeva.database.dao.RentDao;
import com.savelyeva.database.model.Rent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RentService {

    private final RentDao rentDao;

    @Transactional
    public List<Rent> getAllRents() {
        return rentDao.findAll();
    }

}
