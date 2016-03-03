package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.TaskDao;
import com.springapp.mvc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Marcin on 2015-09-28.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public Task save(Task task) {
        return taskDao.create(task);
    }

    @Override
    public Task findById(Long id) {
        return taskDao.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public Task update(Task task) {
        return taskDao.update(task);
    }

    @Override
    public List<Task> findEndlessTaskByProject(Project project) {
        return taskDao.findEndlessTaskByProject(project);
    }

    @Override
    public Set<Task> tasksForCurrentUser(Project project, User currentUser) {
        List<Task> tasksInProject = project.getTasks();
        Set<Task> tasksForCurrentUser = new HashSet<Task>();
        if (tasksInProject != null) {
            for (Task t : tasksInProject) {
                if (t.getListOfUsers().contains(currentUser)) {
                    if (!(t.getStatus().equals("Zakończone") || t.getStatus().equals("Finished"))) {
                        tasksForCurrentUser.add(t);
                    }

                }
            }
        }
        return tasksForCurrentUser;
    }

    @Override
    public boolean grantTheRightToChangeTheStatus(User currentUser, Task task) {
        List<User> userInProject = task.getProject().getUsers();
        List<User> userInTask = task.getListOfUsers();

        if (userInProject.contains(currentUser) && userInTask.contains(currentUser)
                || currentUser.getEmail().equals(task.getProject().getEmailOfCreator())) {
            return true;
        } else {
            return false;
        }
    }


}
