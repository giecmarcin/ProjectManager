package com.springapp.mvc.repository.impl;

import com.springapp.mvc.domain.Invitation;
import com.springapp.mvc.repository.InvitationDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Marcin on 2015-10-04.
 */
@Repository
public class InvitationDaoImpl extends GenericDaoImpl<Invitation> implements InvitationDao {
    @Override
    public void refresh(Invitation invitation) {
        em.refresh(invitation);
    }

    @Override
    public List<Invitation> getAllInvitationsForUser(String email) {
        TypedQuery<Invitation> query = em.createQuery(
                "select i from Invitation i where i.emailOfUser = :email and i.isAccept=false", Invitation.class);
        query.setParameter("email", email);
        List<Invitation> result = query.getResultList();
        if (result.isEmpty() || result == null) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    @Transactional
    public int confirmInvitation(Long idProject, Long idUser) {
        Query query = em.createNativeQuery("INSERT INTO project_user VALUES (?, ?)");
        query.setParameter(1, idProject);
        query.setParameter(2, idUser);
        return query.executeUpdate();
    }
}
