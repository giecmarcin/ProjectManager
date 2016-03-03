package com.springapp.mvc.repository.impl;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.repository.TaskDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Marcin on 2015-09-27.
 */
@Repository
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {
    @Override
    public void refresh(Task task) {
        em.refresh(task);
    }

    @Override
    public List<Task> findTasksByProject(Project project) {

            TypedQuery<Task> query = em.createQuery(
                    "select t from Task t where t.project = :project", Task.class);
            query.setParameter("project", project);
            try{
                return query.getResultList();
            }catch (Exception e){
                return null;
            }

    }

    @Override
    public List<Task> findEndlessTaskByProject(Project project) {

        TypedQuery<Task> query = em.createQuery(
                "select t from Task t where t.project = :project and t.status<>'Finished' or t.status<>'Zakończone'", Task.class);
        query.setParameter("project", project);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }
}
