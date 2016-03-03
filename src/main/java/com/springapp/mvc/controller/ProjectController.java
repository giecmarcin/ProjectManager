package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Invitation;
import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.Task;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.CommonService;
import com.springapp.mvc.service.InvitationService;
import com.springapp.mvc.service.ProjectService;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * Created by Marcin on 2015-09-27.
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProjectForm(Map<String, Object> modelMap, @RequestParam(value = "id", required = false) Long id) {


        Project newProject = null;
        if (id == null) {
            newProject = new Project();
        } else {
            newProject = projectService.findById(id);
        }
        modelMap.put("newProject", newProject);
        String language = LocaleContextHolder.getLocale().getDisplayLanguage();
        Map<String, String> statuses = commonService.statuses(language, true);
        modelMap.put("statuses", statuses);
        return "addProject";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView processAddNewProjectForm(@RequestParam(value = "id", required = false) Long idProject, @Valid @ModelAttribute("newProject") Project
                                                   newProject, BindingResult result) {
        User user = userService.getUserFromContext();
        newProject.getUsers().add(user);
        newProject.setEmailOfCreator(user.getEmail());
        newProject.setVisible(true);


        String[] suppressedFields = result.getSuppressedFields();
        if (result.hasErrors()) {
            String language = LocaleContextHolder.getLocale().getDisplayLanguage();
            Map<String, String> statuses = commonService.statuses(language, true);
            return new ModelAndView("addProject", "statuses", statuses);
            //throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        //continue on success
        Project project = null;
        if (idProject != null) {
            project = projectService.findById(idProject);
        }


        if (project == null) {
            projectService.save(newProject);
            return new ModelAndView("redirect:/projects/your");
        } else {
            newProject.setId(project.getId());
            projectService.update(newProject);
            return new ModelAndView("redirect:/projects/your");
        }
    }
    @RequestMapping("/project")
    public String getYourProjects(@RequestParam("id") Long id, Map<String, Object> modelMap) {

        Project project = projectService.findById(id);
        modelMap.put("projectDetails", project);
        List<Task> taksInProject = projectService.findTasksByProject(project);

        List<User> users = project.getUsers();
        for (User u : users) {
            System.out.println(u.getName());
        }

        modelMap.put("tasksInProject", taksInProject);
        Invitation invitation = new Invitation();
        modelMap.put("newInvitation", invitation);
        User user = userService.getUserFromContext();
        if (user.getEmail().equals(project.getEmailOfCreator())) {
            return "projectDetails";
        } else {
            return "errorAccess";
        }
    }

    @RequestMapping("/your")
    public String getProjects(Model model) {
        User user = userService.getUserFromContext();
        model.addAttribute("yourProjects", projectService.findByEmail(user.getEmail()));
        return "yourProjects";
    }

    @RequestMapping("/statusTask")
    public void changeStatusOfTask(@RequestParam("id") Long id, Map<String, Object> modelMap) {

    }

    @InitBinder("newProject")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id", /*"status",*/ "emailOfCreator", "visible", "users", "tasks");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ModelAndView processAddNewInvention(HttpServletRequest request, @ModelAttribute("newInvitation") Invitation invitation, BindingResult result
    ) {
        String idProject = request.getParameter("id");
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        invitation.setIdProject(Long.parseLong(idProject));
        invitation.setDate(date);
        invitation.setId(null);
        invitationService.save(invitation);
        String url = "/projects/project?id=" + idProject;
        return new ModelAndView("redirect:" + url);
    }

    @InitBinder("newInvitation")
    public void initialiseBinderInvention(WebDataBinder binder) {
        binder.setDisallowedFields("idProject", "isAccept");
    }
}
