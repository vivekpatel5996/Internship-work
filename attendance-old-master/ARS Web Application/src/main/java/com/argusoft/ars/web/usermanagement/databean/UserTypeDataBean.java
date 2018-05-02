/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * This class represents the various types of users available in the System.
 * @author mmodi
 */
@ManagedBean(name = "userTypeDataBean")
@ApplicationScoped
public class UserTypeDataBean {
    
    private String type;
    private String name;

    public UserTypeDataBean(String type, String name) {
        this.type = type;
        this.name = name;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    /*
     * Default Roles available in the system.
     */
    public List<UserTypeDataBean> getUserTypes() {
        
        List<UserTypeDataBean> userTypes = new ArrayList<UserTypeDataBean>();
        
        userTypes.add(new UserTypeDataBean("ROLE_ADMIN", "Administrator"));
        userTypes.add(new UserTypeDataBean("ROLE_DOCTOR", "Doctor"));
        userTypes.add(new UserTypeDataBean("ROLE_PATIENT", "Patient"));      
        
        return userTypes;
    }
}
