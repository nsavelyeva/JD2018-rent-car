package com.savelyeva.database.dao;

import com.savelyeva.database.model.BaseEntity;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Getter
@Transactional
public abstract class BaseDaoImpl<P extends Serializable, E extends BaseEntity<P>> implements BaseDao<P, E> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<E> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.clazz = (Class<E>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseDaoImpl.class)[1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public P save(E entity) {
        return (P) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public Optional<E> find(P id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);

        criteria.select(criteria.from(clazz));

        return session.createQuery(criteria).list();
    }

}
