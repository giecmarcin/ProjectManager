package com.springapp.mvc.controller;

import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;


/**
 * Created by Marcin on 2015-09-22.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String showRegister(Model model, @RequestParam(value = "idUser", required = false) Long idUser) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("redirect:/"); //zmien jeszcze przekierowanie
        }
        if(userService.save(user)!=null){
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/register/available")
    @ResponseBody
    public String available(@RequestParam String emailOfUser){
        Boolean availableEmail = userService.findByEmail(emailOfUser) == null;
        return  availableEmail.toString();
    }

    @RequestMapping(value = "/users/edit/", method = RequestMethod.GET)
    public String gedEditUserForm(@RequestParam("idUser") Long idUser, Map<String, Object> modelMap) {
        User user = userService.findById(idUser);
        modelMap.put("userToEdit", user);
        return "editUser";
    }

    @RequestMapping(value = "/users/edit/", method = RequestMethod.POST)
    public ModelAndView gedEditUserForm(@RequestParam("idUser") Long idUser, /*@Valid*/ @ModelAttribute("userToEdit") User userToEdit, BindingResult result) {
        User user = userService.findById(idUser);
        if (result.hasErrors()) {
            System.out.print("Były błędy");
            return new ModelAndView("redirect:users/edit/?idUser=" + idUser);
        } else {
            user.setName(userToEdit.getName());
            user.setLastname(userToEdit.getLastname());
            user.setEmail(userToEdit.getEmail());
            user.setPassword(userToEdit.getPassword());
            userService.update(user);
            return new ModelAndView("redirect:/users");
        }

    }

//    @InitBinder("userToEdit")
//    public void initialiseBinderInvention(WebDataBinder binder) {
//        binder.setAllowedFields("email", "name", "lastname", "password");
//    }
}
