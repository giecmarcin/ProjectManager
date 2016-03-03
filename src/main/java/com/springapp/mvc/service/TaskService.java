package com.springapp.mvc.service;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Marcin on 2015-09-28.
 */
public interface TaskService {
    Task save(Task task);
    Task findById(Long id);
    List<Task> findAll();

    Task update(Task task);

    List<Task> findEndlessTaskByProject(Project project);

    Set<Task> tasksForCurrentUser(Project project, User currentUser);

    boolean grantTheRightToChangeTheStatus(User currentUser, Task task);

}
