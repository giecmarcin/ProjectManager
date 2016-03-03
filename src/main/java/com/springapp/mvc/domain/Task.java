package com.springapp.mvc.domain;

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
public class Task {
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

    @NotNull(message = "{validationNotEmpty}")
    private String status;

    private double progress;

    private String priority;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    /**
     * This field keep the list of users which is responsible for this task
     */
    @NotNull(message = "{validationNotEmpty}")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_in_tasks",
            joinColumns = {@JoinColumn(name = "id_task")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")}
    )
    private List<User> listOfUsers;

    public Task() {
        listOfUsers = new ArrayList<User>();
        status = new String();
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

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
}
