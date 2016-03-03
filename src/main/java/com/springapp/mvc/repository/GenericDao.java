package com.springapp.mvc.repository;

import java.util.List;

/**
 * Created by Marcin on 2015-09-25.
 */
public interface GenericDao<T> {
    T create(T t);

    void delete(Object id);

    T findById(Object id);

    T update(T t);

    List<T> findAll();

    void refresh(T t);
}
