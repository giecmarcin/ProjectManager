package com.springapp.mvc.repository.impl;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.ProjectDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcin on 2015-09-27.
 */
@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project> implements ProjectDao {

    @Override
    public List<Project> findByEmail(String email) {
        TypedQuery<Project> query = em.createQuery(
                "select p from Project p where p.emailOfCreator = :email", Project.class);
        query.setParameter("email", email);
        List<Project> result = query.getResultList();
        if (result.isEmpty() || result == null) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<User> getUsersFromProject() {
        TypedQuery<User> query = em.createQuery(
                "select p from Project p where p.emailOfCreator = :email", User.class);
        query.setParameter("email", "Test");
        List<User> result = query.getResultList();
        if (result.isEmpty() || result == null) {
            return null;
        } else {
            return result;
        }
    }

    @Transactional
    @Override
    public List<Project> getOthersProject(User user) {
        List<Project> projects = new ArrayList<Project>();
        final StringBuilder sb = new StringBuilder();
        sb.append("select id, dateEnd, dateStart, description, emailOfCreator, name, status, visible ");
        sb.append("from project p join project_user pu on (p.id = pu.projects_id) ");
        sb.append("where pu.users_id =:idOfUser and p.emailOfCreator!=:emailOfUser");
        sb.append(" and p.visible=true");

        Query query = em.createNativeQuery(sb.toString());
        query.setParameter("idOfUser", user.getId());
        query.setParameter("emailOfUser", user.getEmail());
        List<Object> objects = query.getResultList();

        Iterator itr = objects.iterator();
        while ((itr.hasNext())) {
            Object[] obj = (Object[]) itr.next();

            //now you have one array of Object for each row
            BigInteger id = (BigInteger) (obj[0]);
            Date dateEnd = (Date) (obj[1]);
            Date dateStart = (Date) (obj[2]);
            String description = (String) (obj[3]);
            String emailOfCreator = (String) (obj[4]);
            String name = (String) (obj[5]);
            String status = (String) (obj[6]);
            boolean visible = (boolean) (obj[7]);
            Project p = new Project();
            p.setId(id.longValue());
            p.setDateEnd(dateEnd);
            p.setDateStart(dateStart);
            p.setDescription(description);
            p.setEmailOfCreator(emailOfCreator);
            p.setName(name);
            p.setStatus(status);
            p.setVisible(visible);
            projects.add(p);
        }
        return projects;
    }

    @Override
    public void refresh(Project project) {
        em.refresh(project);
    }
}
