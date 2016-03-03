package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.User;

import java.util.List;

/**
 * Created by Marcin on 2015-09-27.
 */
public interface ProjectDao extends GenericDao<Project> {
    public List<Project> findByEmail(String email);
    List<User> getUsersFromProject();
    List<Project> getOthersProject(User user);
}
