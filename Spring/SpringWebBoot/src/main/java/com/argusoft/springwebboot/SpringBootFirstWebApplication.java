package com.argusoft.springwebboot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author vivek
 */
@SpringBootApplication
@ComponentScan("com.argusoft.springwebboot")
public class SpringBootFirstWebApplication {
        public static void main(String[] args) {

        SpringApplication.run(SpringBootFirstWebApplication.class,args);

    }
}
