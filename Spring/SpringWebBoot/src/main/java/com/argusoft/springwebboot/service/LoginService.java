package com.argusoft.springwebboot.service;

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
public class LoginService {
    
        public boolean validateUser(String userid, String password) {

        // in28minutes, dummy

        return userid.equalsIgnoreCase("in28minutes")

                && password.equalsIgnoreCase("dummy");

    }


    
}
