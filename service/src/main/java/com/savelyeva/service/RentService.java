package com.savelyeva.service;

import com.savelyeva.dao.RentDao;
import com.savelyeva.model.Rent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RentService {

    @Autowired
    private RentService rentService;

    private final RentDao rentDao;

    @Transactional
    public List<Rent> getAllRents() {
        return rentDao.findAll();
    }

}
