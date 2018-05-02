/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.springwebboot.controller;

import com.argusoft.springwebboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vivek
 */
@Controller
public class TodoController {

    @Autowired

    TodoService service;

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)

    public String showTodos(ModelMap model) {

        String name = (String) model.get("name");
        System.out.println("name:"+name);
        model.put("todos", service.retrieveTodos(name));

        return "list-todos";

    }

}
