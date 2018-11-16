package com.savelyeva.dao;

import com.savelyeva.model.BaseEntity;
import lombok.Cleanup;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.savelyeva.connection.ConnectionManager.getSession;

public abstract class BaseDaoImpl<P extends Serializable, E extends BaseEntity<P>> implements BaseDao<P, E> {

    private Class<E> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<E>) type.getActualTypeArguments()[1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public P save(E entity) {
        @Cleanup Session session = getSession();
        return (P) session.save(entity);
    }

    @Override
    public void update(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public E find(P id) {
        @Cleanup Session session = getSession();
        return session.find(clazz, id);
    }

    @Override
    public List<E> findAll() {
        @Cleanup Session session = getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);

        criteria.select(criteria.from(clazz));

        return session.createQuery(criteria).list();
    }

}
