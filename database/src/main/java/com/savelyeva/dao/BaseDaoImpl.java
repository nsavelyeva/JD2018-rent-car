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

//    public BaseDaoImpl(Class<E> clazz) {
//        this.clazz = clazz;
//    }

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
        session.update(entity);
    }

    @Override
    public void delete(E entity) {
        @Cleanup Session session = getSession();
        session.delete(entity);
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

//        return session.createQuery(format("select e from %s e", clazz.getSimpleName()), clazz).list();
    }

//    public abstract Class<E> getEntityClass();
}
