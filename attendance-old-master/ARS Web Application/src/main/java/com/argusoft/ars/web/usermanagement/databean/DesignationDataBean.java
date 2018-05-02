/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "designationDataBean")
@RequestScoped
public class DesignationDataBean {

    private Long desgId;
    @Length(min = 3, max = 55, message = "Please enter designation name between 3 To 55 Character")
    private String desgName;
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

    public Long getDesgId() {
        return desgId;
    }

    public void setDesgId(Long desgId) {
        this.desgId = desgId;
    }

    public String getDesgName() {
        return desgName;
    }

    public void setDesgName(String desgName) {
        this.desgName = desgName;
    }
}
