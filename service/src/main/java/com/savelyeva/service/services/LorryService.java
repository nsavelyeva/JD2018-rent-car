package com.savelyeva.service.services;

import com.savelyeva.database.dao.LorryDao;
import com.savelyeva.database.model.Lorry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LorryService {

    private final LorryDao lorryDao;

    @Transactional
    public List<Lorry> getAllLorries() {
        return lorryDao.findAll();
    }

    @Transactional
    public Optional<Lorry> find(Long id) {
        return lorryDao.find(id);
    }
}
