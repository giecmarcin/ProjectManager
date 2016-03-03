package com.springapp.mvc.repository.impl;

import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

/**
 * Created by Marcin on 2015-09-25.
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User findByName(String name) {
        TypedQuery<User> query = em.createQuery(
                "select u from User u where u.name = :name", User.class);
        query.setParameter("name", name);
        try{
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createQuery(
                "select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        try{
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void refresh(User user) {
        em.refresh(user);
    }
}
