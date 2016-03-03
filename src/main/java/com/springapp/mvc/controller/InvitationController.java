package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Invitation;
import com.springapp.mvc.domain.Project;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.InvitationService;
import com.springapp.mvc.service.ProjectService;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2015-10-05.
 */
@Controller
@RequestMapping("/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/all")
    public String allInvitation(Map<String, Object> modelMap) {
        String email = userService.getUserFromContext().getEmail();
        List<Project> projects = invitationService.getProjectsFromInvitations(email);
        List<Invitation> invitations = invitationService.getAllInvitationsForUser(email);
        modelMap.put("projects", projects);
        modelMap.put("invitations", invitations);
        return "allInvitation";
    }

    @RequestMapping(value = "all/{idProject}/{idInvitation}")
    public ModelAndView confirmInvitation(@PathVariable(value = "idProject") Long idProject, @PathVariable(value = "idInvitation") Long idInvitation) {
        Invitation invitation = invitationService.findById(idInvitation);
        invitation.setIsAccept(true);
        invitation.setIsVisible(false);
        invitationService.update(invitation);

        Project project = projectService.findById(idProject);
        User user = userService.getUserFromContext();
        invitationService.confirmInvitation(project.getId(),user.getId());
        //projectService.refresh(project);

        return new ModelAndView("redirect:/invitations/all");
    }
}
