package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Invitation;
import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.InvitationDao;
import com.springapp.mvc.service.InvitationService;
import com.springapp.mvc.service.ProjectService;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-10-04.
 */
@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationDao invitationDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Override
    public Invitation save(Invitation invitation) {
        return invitationDao.create(invitation);
    }

    @Override
    public Invitation update(Invitation invitation) {
        return invitationDao.update(invitation);
    }

    @Override
    public Invitation findById(Long id) {
        return invitationDao.findById(id);
    }

    @Override
    public List<Invitation> getAllInvitationsForUser(String email) {
        return invitationDao.getAllInvitationsForUser(email);
    }

    @Override
    public List<Project> getProjectsFromInvitations(String email) {
        List<Invitation> invitations = invitationDao.getAllInvitationsForUser(email);
        if (invitations != null) {
            List<Project> projects = new ArrayList<Project>();
            for (Invitation invitation : invitations) {
                projects.add(projectService.findById(invitation.getIdProject()));
            }
            return projects;
        }
        return null;
    }

    @Override
    @Transactional
    public int confirmInvitation(Long idProject, Long idUser) {
        return invitationDao.confirmInvitation(idProject,idUser);
    }
}
