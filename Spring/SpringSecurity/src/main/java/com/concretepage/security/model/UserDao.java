/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concretepage.security.model;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author vivek
 */
public class UserDao 
{
    
    JdbcTemplate template;
    
    @Autowired
    DataSource ds;
    
    
    public boolean ValidateUser(User user)
    {
        template=new JdbcTemplate(ds);
        String name=user.getUsername();
        String password=user.getPassword();
        System.out.println("name,password"+name+" "+password);
       // String sql="insert into user(username,password) values('"+name+"',"+password+"')";
      //  int t=template.update(sql);
       
      String sql="select count(*) from user where username='"+name+"'AND password='"+password+"'";
      int count=template.queryForObject(sql,Integer.class );
       
      //int count=2;
        if(count<1)
        {
            System.out.println("No");
            return false;
        }
        else
        {    System.out.println("Yes");
        return true;
        }
    }

   
}
