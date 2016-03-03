package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.CommonService;
import com.springapp.mvc.service.ProjectService;
import com.springapp.mvc.service.TaskService;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-09-28.
 */

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/task/add", method = RequestMethod.GET)
    public String getAddNewTaskForm(Map<String, Object> modelMap, @RequestParam("idProject") Long id, @RequestParam(value = "idTask", required = false) Long idTask) {
        Project project = projectService.findById(id);
        User currentUser = userService.getUserFromContext();
        List<User> users = project.getUsers();
        Task newTask = null;
        if (idTask == null) {
            newTask = new Task();
        } else {
            newTask = taskService.findById(idTask);
        }
        modelMap.put("userList", users);
        modelMap.put("newTask", newTask);
        if (currentUser.getEmail().equals(project.getEmailOfCreator())) {
            return "addTask";
        } else {
            return "errorAccess";
        }

    }

    @RequestMapping(value = {"/task/add"/*, "task/edit"*/}, method = RequestMethod.POST)
    public ModelAndView processAddNewTaskForm(HttpServletRequest request, @RequestParam(value = "users", required = true) List<String> users, @RequestParam(value = "idTask", required = false) Long idTask, @Valid @ModelAttribute("newTask") Task
            newTask, BindingResult result) {
        String id = request.getParameter("idProject");
        Project project = projectService.findById(Long.parseLong(id));
        newTask.setProject(project);

        List<User> choosenUsers = userService.getUsersById(users);
        newTask.setListOfUsers(choosenUsers);


        String[] suppressedFields = result.getSuppressedFields();
        if (result.hasErrors()) {
            return new ModelAndView("addTask", "userList", project.getUsers());
            //throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        Task task = null;
        if (idTask != null) {
            task = taskService.findById(idTask);
        }
        if (task == null) {
            taskService.save(newTask);
            projectService.update(project);
        } else {
            newTask.setId(task.getId());
            taskService.update(newTask);
            projectService.update(project);
        }

        return new ModelAndView("redirect:/projects/project?id=" + id);
    }

    @RequestMapping(value = "/projects/other/project/status", method = RequestMethod.GET)
    public String getStatusTaskForm(Map<String, Object> modelMap, @RequestParam("idProject") Long idProject, @RequestParam("idTask") Long idTask) {
        String language = LocaleContextHolder.getLocale().getDisplayLanguage();
        Map<String, String> statuses = commonService.statuses(language, false);
        Task task = taskService.findById(idTask);

        modelMap.put("statuses", statuses);
        modelMap.put("statusOfTask", task);
        Project project = projectService.findById(idProject);

        User currentUser = userService.getUserFromContext();
        if (taskService.grantTheRightToChangeTheStatus(currentUser, task)) {
            return "status";
        } else {
            return "errorAccess";
        }

    }

    @RequestMapping(value = "/projects/other/project/status", method = RequestMethod.POST)
    public ModelAndView processStatusTaskForm(HttpServletRequest request, @ModelAttribute("statusOfTask") Task
            task, BindingResult result) {

        String idProject = request.getParameter("idProject");
        String idTask = request.getParameter("idTask");
        Long id = Long.parseLong(idTask);

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        Task oldTask = taskService.findById(id);
        oldTask.setStatus(task.getStatus());
        taskService.update(oldTask);
        if (oldTask.getProject().getEmailOfCreator().equals(userService.getUserFromContext().getEmail())) {
            return new ModelAndView("redirect:/projects/project?id=" + idProject);
        } else {
            return new ModelAndView("redirect:/projects/other/project?idProject=" + idProject);
        }


    }

    @InitBinder("newTask")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id", "status", "progress", "priority", "project");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    @InitBinder("statusOfTask")
    public void initialiseBinderInvention(WebDataBinder binder) {
        binder.setDisallowedFields("id", "name", "description", "dateStart", "dateEnd", "progress", "priority", "project");
    }
}
