package com.savelyeva.service;

import com.savelyeva.dao.LorryDao;
import com.savelyeva.model.Lorry;
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

    @Autowired
    private LorryService lorryService;

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
