package com.springapp.mvc.service;

import com.springapp.mvc.domain.User;

import java.util.List;

/**
 * Created by Marcin on 2015-09-27.
 */
public interface UserService {
    User save(User user);
    User update(User user);
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    User getUserFromContext();
    List<User> getUsersById(List<String> idOfUsers);
}
