/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "cardInventoryDataBean")
@RequestScoped
public class CardInventoryDataBean {

    private String cardEnrollNo;
    private Long cardId;
    private String vendor;
    private boolean isAssigne;
    private String reason;
    private Long assigneUserId;
    private String Name;
    private Long tempCardEnrollNo;
    private Long id;

    public Long getAssigneUserId() {
        return assigneUserId;
    }

    public void setAssigneUserId(Long assigneUserId) {
        this.assigneUserId = assigneUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTempCardEnrollNo() {
        return tempCardEnrollNo;
    }

    public void setTempCardEnrollNo(Long tempCardEnrollNo) {
        this.tempCardEnrollNo = tempCardEnrollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCardEnrollNo() {
        return cardEnrollNo;
    }

    public void setCardEnrollNo(String cardEnrollNo) {
        this.cardEnrollNo = cardEnrollNo;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public boolean isIsAssigne() {
        return isAssigne;
    }

    public void setIsAssigne(boolean isAssigne) {
        this.isAssigne = isAssigne;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setNull() {
        this.cardEnrollNo = null;
        this.cardId = null;
        this.vendor = null;
        this.reason = null;
        this.assigneUserId = null;
        this.Name = null;
    }
}
