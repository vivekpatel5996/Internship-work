/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "leaveQuotaDataBean")
@RequestScoped
public class LeaveQuotaDataBean {

    private Long userId;
    private String userName;
    private String empId;
    private Float availableCasualLeave;
    private Float availableEarnLeave;
    private Integer availableRestrictedHoliday;
    private Float lossOfPayLeaves;
    private Boolean isCasualLeaveTaken;
    private Boolean isEarnLeaveTaken;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Boolean getIsCasualLeaveTaken() {
        return isCasualLeaveTaken;
    }

    public void setIsCasualLeaveTaken(Boolean isCasualLeaveTaken) {
        this.isCasualLeaveTaken = isCasualLeaveTaken;
    }

    public Boolean getIsEarnLeaveTaken() {
        return isEarnLeaveTaken;
    }

    public void setIsEarnLeaveTaken(Boolean isEarnLeaveTaken) {
        this.isEarnLeaveTaken = isEarnLeaveTaken;
    }

    public Float getLossOfPayLeaves() {
        if (lossOfPayLeaves != null) {
            return Math.round(lossOfPayLeaves * 100.0F) / 100.0F;
        } else {
            return 0F;
        }
    }

    public void setLossOfPayLeaves(Float lossOfPayLeaves) {
        this.lossOfPayLeaves = lossOfPayLeaves;
    }

    public Float getAvailableCasualLeave() {
        if (availableCasualLeave != null) {
            return Math.round(availableCasualLeave * 100.0F) / 100.0F;
        }
        return 0F;
    }

    public void setAvailableCasualLeave(Float availableCasualLeave) {
        this.availableCasualLeave = availableCasualLeave;
    }

    public Float getAvailableEarnLeave() {
        if (availableEarnLeave != null) {
            return Math.round(availableEarnLeave * 100.0F) / 100.0F;
        }
        return 0F;
    }

    public void setAvailableEarnLeave(Float availableEarnLeave) {
        this.availableEarnLeave = availableEarnLeave;
    }

    public Integer getAvailableRestrictedHoliday() {
        return availableRestrictedHoliday;
    }

    public void setAvailableRestrictedHoliday(Integer availableRestrictedHoliday) {
        this.availableRestrictedHoliday = availableRestrictedHoliday;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
