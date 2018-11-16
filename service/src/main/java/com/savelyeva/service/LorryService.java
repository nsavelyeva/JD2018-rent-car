package com.savelyeva.service;

import com.savelyeva.dao.LorryDao;
import com.savelyeva.dao.LorryDaoImpl;
import com.savelyeva.connection.ConnectionManager;
import com.savelyeva.model.Lorry;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LorryService {

    private static final LorryService INSTANCE = new LorryService();

    public static LorryService getInstance() {
        return INSTANCE;
    }

    public List<Lorry> getAllLorries() {
        @Cleanup Session session = ConnectionManager.getFactory().openSession();
        List<Lorry> lorries = session.createQuery("select l from Lorry l", Lorry.class).list();
        return lorries;
    }

    public Lorry find(Long id) {
        LorryDao lorryDao = LorryDaoImpl.getInstance();
        Lorry lorry = lorryDao.find(id);
        return lorry;
    }
}
