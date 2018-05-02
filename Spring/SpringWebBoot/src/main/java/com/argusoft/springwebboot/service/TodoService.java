package com.argusoft.springwebboot.service;


import com.argusoft.springwebboot.model.Todo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vivek
 */
@Service
public class TodoService {
 
        private static List<Todo> todos = new ArrayList<Todo>();

    private static int todoCount = 3;

    static {

        todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),false));

        todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(), false));

        todos.add(new Todo(3, "in28Minutes", "Learn Hibernate", new Date(),

                false));

    }

    public List<Todo> retrieveTodos(String user) {

        List<Todo> filteredTodos = new ArrayList<Todo>();

        for (Todo todo : todos) {

            if (todo.getUser().equals(user)) {

                filteredTodos.add(todo);

            }

        }

        return filteredTodos;

    }
    
}
