package com.springapp.mvc.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 2015-09-25.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "{validationNotEmpty}")
    @Size(min = 3, max = 50, message = "{min3max50}")
    private String name;

    @Size(min = 3, max = 255, message = "{min3max255}")
    private String description;


    @NotNull(message = "{validationNotEmpty}")
    @Temporal(TemporalType.DATE)
    private Date dateStart;

    @NotNull(message = "{validationNotEmpty}")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    @NotBlank(message = "{validationNotEmpty}")
    private String status;

    private String emailOfCreator;

    private boolean visible;




    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Project() {
        users = new ArrayList<User>();
        tasks = new ArrayList<Task>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailOfCreator() {
        return emailOfCreator;
    }

    public void setEmailOfCreator(String emailOfCreator) {
        this.emailOfCreator = emailOfCreator;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
