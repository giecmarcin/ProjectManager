package com.springapp.mvc.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2015-09-22.
 */

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, max = 50, message = "{min3max50}")
    private String name;

    @Size(min = 3, max = 50, message = "{min3max50}")
    private String lastname;

    @Email
    @Size(min = 3, max = 50, message = "{min3max50}")
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Project> projects;

    @ManyToMany(mappedBy = "listOfUsers", fetch = FetchType.EAGER)
    private List<Task> tasks;

    public User() {
        projects = new ArrayList<Project>();
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    @Override
    public String toString() {
       // final StringBuilder sb = new StringBuilder("User{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", email='").append(email).append('\'');
//        sb.append(", password='").append(password).append('\'');
//        sb.append(", enabled=").append(enabled);
//        sb.append(", roles=").append(roles);
//        sb.append('}');
        return name + " " + lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
