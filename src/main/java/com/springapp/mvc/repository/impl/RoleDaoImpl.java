package com.springapp.mvc.repository.impl;

import com.springapp.mvc.domain.Role;
import com.springapp.mvc.repository.RoleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by Marcin on 2015-09-25.
 */

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = em.createQuery(
                "select r from Role r where r.name = :name", Role.class);
        query.setParameter("name", name);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

    @Override
    public void refresh(Role role) {
        em.refresh(role);
    }
}
