package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Role;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.ProjectDao;
import com.springapp.mvc.repository.RoleDao;
import com.springapp.mvc.repository.UserDao;
import com.springapp.mvc.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2015-09-22.
 */

@Service
public class InitDbServiceImpl implements InitDbService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ProjectDao projectDao;


    @Override
    @PostConstruct
    public void init() {
        Role roleUser = roleDao.findByName("ROLE_USER");
        if (roleUser == null) {
            roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleDao.create(roleUser);
        }


        Role roleAdmin = roleDao.findByName("ROLE_ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleDao.create(roleAdmin);
        }


        User userAdmin = userDao.findByEmail("giecmarcin@outlook.com");
        if (userAdmin == null) {
            userAdmin = new User();
            userAdmin.setEnabled(true);
            userAdmin.setName("admin");
            userAdmin.setLastname("admin");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userAdmin.setPassword(encoder.encode("admin"));
            userAdmin.setEmail("giecmarcin@outlook.com");
            List<Role> roles = new ArrayList<Role>();
            roles.add(roleAdmin);
            roles.add(roleUser);
            userAdmin.setRoles(roles);
            userDao.create(userAdmin);
        }

        User user2 = userDao.findByEmail("test@o2.pl");
        if (user2 == null) {
            user2 = new User();
            user2.setName("Jan");
            user2.setLastname("Nawrot");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user2.setPassword(encoder.encode("test"));
            List<Role> roles = new ArrayList<Role>();
            roles.add(roleUser);
            user2.setRoles(roles);
            user2.setEmail("test@o2.pl");
            user2.setEnabled(true);
            userDao.create(user2);
        }
    }
}
