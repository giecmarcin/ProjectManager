package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.ProjectDao;
import com.springapp.mvc.repository.TaskDao;
import com.springapp.mvc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-09-27.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TaskDao taskDao;

    @Override
    public Project save(Project project) {
        return projectDao.create(project);
    }

    @Override
    public Project update(Project project) {
        return projectDao.update(project);
    }

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public List<Project> findByEmail(String email) {
        return projectDao.findByEmail(email);
    }

    @Override
    public List<Task> findTasksByProject(Project project) {
        return taskDao.findTasksByProject(project);
    }


    @Override
    @Transactional
    public void refresh(Project project) {
        projectDao.refresh(project);
    }

    @Override
    public List<User> getUsersFromProject() {
        return null;
    }

    @Override
    public List<Project> getOthersProjects(User user) {
        return projectDao.getOthersProject(user);
    }
}
