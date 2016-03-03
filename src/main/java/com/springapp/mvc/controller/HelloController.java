package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marcin on 2015-09-25.
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello(){
        return "index";
    }
}
