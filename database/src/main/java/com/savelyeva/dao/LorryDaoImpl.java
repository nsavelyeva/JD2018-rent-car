package com.savelyeva.dao;

import com.savelyeva.model.Lorry;

public class LorryDaoImpl extends BaseDaoImpl<Long, Lorry> implements LorryDao {

    private static final LorryDao INSTANCE = new LorryDaoImpl();

    public static LorryDao getInstance() {
        return INSTANCE;
    }

}