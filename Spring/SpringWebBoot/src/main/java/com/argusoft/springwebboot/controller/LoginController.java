package com.argusoft.springwebboot.controller;
import com.argusoft.springwebboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vivek
 */

@Controller
@SessionAttributes("name")
public class LoginController {
        
    @Autowired

    LoginService service;
    
    

    @RequestMapping(value="/login", method = RequestMethod.GET)

    public String showLoginPage(ModelMap model){
        System.out.println("Here");
        return "login";

    }
    
        @RequestMapping(value="/loginsubmit", method = RequestMethod.POST)

    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){

        boolean isValidUser = service.validateUser(name, password);

        if (!isValidUser) {

            model.put("errorMessage", "Invalid Credentials");

            return "login";

        }

        model.put("name", name);
            System.out.println("login"+name);
        model.put("password", password);

        return "welcome";

    }
    
//    public static void main(String[] args) {
//        SpringApplication.run(LoginController.class, args);
//    }
    
}
