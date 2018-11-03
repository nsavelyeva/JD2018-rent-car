package com.savelyeva.dao;

import com.savelyeva.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<P extends Serializable, E extends BaseEntity<P>> {

    P save(E entity);

    void update(E entity);

    void delete(E entity);

    E find(P id);

    List<E> findAll();
}

