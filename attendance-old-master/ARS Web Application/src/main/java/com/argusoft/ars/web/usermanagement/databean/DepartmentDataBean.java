/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Harshit
 */
@ManagedBean(name = "departmentDataBean")
@RequestScoped
public class DepartmentDataBean {

    private Long depId;
    @Length(min = 3, max = 55, message = "Please enter department name between 3 To 55 Character")
    private String depName;
    private Long userId;
    private String empName;
    private boolean checked;
    private boolean addToThisDisable;

    public boolean isAddToThisDisable() {
        return addToThisDisable;
    }

    public void setAddToThisDisable(boolean addToThisDisable) {
        this.addToThisDisable = addToThisDisable;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Long getDepId() {
        return depId;
    }

    public String getDepName() {
        return depName;
    }
}
