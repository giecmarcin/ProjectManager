package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Role;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.RoleDao;
import com.springapp.mvc.repository.UserDao;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2015-09-22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {

        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<Role>();
        roles.add(roleDao.findByName("ROLE_USER"));
        user.setRoles(roles);
        return userDao.create(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserFromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByEmail(auth.getName());
        return user;
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> getUsersById(List<String> idOfUsers) {
        List<User> users = new ArrayList<User>();
        for(String id: idOfUsers){
            users.add(userDao.findById(Long.parseLong(id)));
        }
        return users;
    }
}
