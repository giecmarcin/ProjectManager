package com.springapp.mvc.service;

import com.springapp.mvc.domain.Invitation;
import com.springapp.mvc.domain.Project;

import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-10-04.
 */
public interface InvitationService {
    Invitation save(Invitation invitation);
    Invitation update(Invitation invitation);
    Invitation findById(Long id);
    List<Invitation> getAllInvitationsForUser(String email);

    List<Project> getProjectsFromInvitations(String email);
    int confirmInvitation(Long idProject, Long idUser);
}
