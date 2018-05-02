/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "shiftDataBean")
@RequestScoped
public class ShiftDataBean {

    private Long shiftId;
    @Length(min = 3, max = 55, message = "Please enter shift name between 3 To 55 Character")
    private String shiftName;
    private Date shiftStratTime;
    private Date shiftEndTime;
    private Boolean deleteButtonDisable;
    private Boolean addToThisButtonDisable;

    public Boolean getAddToThisButtonDisable() {
        return addToThisButtonDisable;
    }

    public void setAddToThisButtonDisable(Boolean addToThisButtonDisable) {
        this.addToThisButtonDisable = addToThisButtonDisable;
    }

    public Boolean getDeleteButtonDisable() {
        return deleteButtonDisable;
    }

    public void setDeleteButtonDisable(Boolean deleteButtonDisable) {
        this.deleteButtonDisable = deleteButtonDisable;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public Date getShiftStratTime() {
        return shiftStratTime;
    }

    public void setShiftStratTime(Date shiftStratTime) {
        this.shiftStratTime = shiftStratTime;
    }
}
