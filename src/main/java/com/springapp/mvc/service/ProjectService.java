package com.springapp.mvc.service;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-09-27.
 */
public interface ProjectService {
    Project save(Project project);
    Project update(Project project);
    List<Project> findAll();
    Project findById(Long id);
    List<Project> findByEmail(String email);
    List<Task> findTasksByProject(Project project);
    void refresh(Project project);
    List<User> getUsersFromProject();
    List<Project> getOthersProjects(User user);

}
