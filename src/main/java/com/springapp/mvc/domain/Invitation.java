package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Marcin on 2015-10-04.
 */
@Entity
public class Invitation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long idProject;

    private String emailOfUser;

    private boolean isAccept;

    private boolean isVisible;


    @Temporal(TemporalType.DATE)
    private Date date;

    public Invitation() {
        isAccept = false;
        isVisible = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getEmailOfUser() {
        return emailOfUser;
    }

    public void setEmailOfUser(String emailOfUser) {
        this.emailOfUser = emailOfUser;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setIsAccept(boolean isAccept) {
        this.isAccept = isAccept;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
