package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Invitation;
import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.ProjectService;
import com.springapp.mvc.service.TaskService;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-12-20.
 */
@Controller
@RequestMapping("/projects")
public class OtherProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/other")
    public String getProjects(Model model) {
        List<Project> othersProjects = projectService.getOthersProjects(userService.getUserFromContext());
        model.addAttribute("otherProjects", othersProjects);
        return "otherProjects";
    }

    @RequestMapping("/other/project")
    public String getYourProjects(@RequestParam("idProject") Long id, Map<String, Object> modelMap) {

        Project project = projectService.findById(id);
        User currentUser = userService.getUserFromContext();
        modelMap.put("projectDetails", project);
        List<Task> tasksInProject = projectService.findTasksByProject(project);


        List<User> users = project.getUsers();
        modelMap.put("tasksInProject", taskService.tasksForCurrentUser(project, currentUser));
        Invitation invitation = new Invitation();
        modelMap.put("newInvitation", invitation);
        User user = userService.getUserFromContext();
        if (users.contains(currentUser)) {
            return "projectDetails";
        } else {
            return "errorAccess";
        }
    }
}
