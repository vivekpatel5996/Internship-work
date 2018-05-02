package com.concretepage.security.controller;

import com.concretepage.security.model.User;
import com.concretepage.security.model.UserDao;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class LoginController {

    @Autowired
    UserDao userDao;

  @RequestMapping(value = "/newlogin", method = RequestMethod.POST)
    public ModelAndView success(@ModelAttribute("User") User user) {

        System.out.println("here in login");
        boolean result = userDao.ValidateUser(user);
        ModelAndView m = new ModelAndView("success");
        if (result) {
            m = new ModelAndView("success");
            m.addObject("msg", "true");
            return m;
        } else {
            m = new ModelAndView("success");
            m.addObject("msg", "false");
            return m;   
        }
      //  map.addAttribute("msg", "Successfully logged in");

    }

    @RequestMapping(value = "/loginpage")
    public String newloginpage(ModelMap map) {

        map.addAttribute("msg", "new login page");
        return "customlogin";

    }
//    @RequestMapping("/")
//    public String afterLogin(){
//        return "customlogin";
//    }
   
    @RequestMapping("/success")
    public String gety(){
        
        return "success";
    }
}
