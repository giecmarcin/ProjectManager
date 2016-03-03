package com.springapp.mvc.repository.impl;

import com.springapp.mvc.repository.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Marcin on 2015-09-25.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }


    @Transactional
    public T create(final T t) {
        this.em.persist(t);
        return t;
    }


    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }


    @Transactional
    public T update(final T t) {
        return this.em.merge(t);
    }


    public T findById(final Object id) {
        return (T) this.em.find(type, id);
    }

    public List<T> findAll() {
        return em.createQuery("select t from " + type.getSimpleName() + " t").getResultList();
    }

    @Transactional
    public void Refresh(final T t) {
        em.refresh(t);
    }
}
