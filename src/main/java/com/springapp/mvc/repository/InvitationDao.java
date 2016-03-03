package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Invitation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marcin on 2015-10-04.
 */
public interface InvitationDao extends GenericDao<Invitation> {
    List<Invitation> getAllInvitationsForUser(String email);
    public int confirmInvitation(Long idProject, Long idUser);
}
