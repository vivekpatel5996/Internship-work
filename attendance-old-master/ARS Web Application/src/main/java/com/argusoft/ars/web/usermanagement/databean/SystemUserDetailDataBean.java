/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "systemUserDetailDataBean")
@RequestScoped
public class SystemUserDetailDataBean {

    private Long userId;
    private String Name;
    private Long depId;
    private String depName;
    private String desgName;
    private Long shiftId;
    private String shitName;
    private Long cardEnrollNo;
    private boolean checked;
    private String shiftName;
    private String empId;
    private Long[] userList;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    
    public Long[] getUserList() {
        return userList;
    }

    public void setUserList(Long[] userList) {
        this.userList = userList;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Long getCardEnrollNo() {
        return cardEnrollNo;
    }

    public void setCardEnrollNo(Long cardEnrollNo) {
        this.cardEnrollNo = cardEnrollNo;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDesgName() {
        return desgName;
    }

    public void setDesgName(String desgName) {
        this.desgName = desgName;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getShitName() {
        return shitName;
    }

    public void setShitName(String shitName) {
        this.shitName = shitName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
