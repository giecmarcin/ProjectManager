package com.springapp.mvc.repository;

import com.springapp.mvc.domain.User;

/**
 * Created by Marcin on 2015-09-25.
 */
public interface UserDao extends GenericDao<User> {
    public User findByName(String name);
    public User findByEmail(String email);
}
