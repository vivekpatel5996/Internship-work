/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author momodi
 */
@ManagedBean(name = "changePasswordDataBean")
@RequestScoped
public class ChangePasswordDataBean implements Serializable {

    private Long id;
//    @Length(min = 8, message = "Password should be of 8 characters.")
//    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\d.]*)?$", message = "Enter valid password")
    private String password;
//    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\d.]*)?$", message = "Enter valid confirm password")
//    @Length(min = 8, message = "Confirm Password should be of 8 characters.")
    private String rePassword;
    private String oldPassword;
    private Boolean passwordCheck;

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(Boolean passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    
    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
   
}
