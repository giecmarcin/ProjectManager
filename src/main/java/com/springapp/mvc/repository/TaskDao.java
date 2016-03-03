package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;

import java.util.List;

/**
 * Created by Marcin on 2015-09-27.
 */
public interface TaskDao extends GenericDao<Task> {
    List<Task> findTasksByProject(Project project);

    List<Task> findEndlessTaskByProject(Project project);
}
