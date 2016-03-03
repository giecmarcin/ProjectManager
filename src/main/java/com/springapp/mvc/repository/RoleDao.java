package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Role;

/**
 * Created by Marcin on 2015-09-25.
 */
public interface RoleDao extends GenericDao<Role> {
    Role findByName(String name);
}
