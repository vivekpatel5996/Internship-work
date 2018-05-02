/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "manualCardEntryDataBean")
@RequestScoped
public class ManualCardEntryDataBean {

    private Long userId;
    private Long manualCardEntryId;
    private Date date;
    private Boolean inStatus;
    private Boolean outStatus;
    private String reason;
    private String adminComment;
    private String status;
    private String userName;
    private String inOutStatusValidation;
    private String responseUserName;
    private Boolean isLateEntry;

    public Boolean getIsLateEntry() {
        return isLateEntry;
    }

    public void setIsLateEntry(Boolean isLateEntry) {
        this.isLateEntry = isLateEntry;
    }

    public String getResponseUserName() {
        return responseUserName;
    }

    public void setResponseUserName(String responseUserName) {
        this.responseUserName = responseUserName;
    }

    public String getInOutStatusValidation() {
        return inOutStatusValidation;
    }

    public void setInOutStatusValidation(String inOutStatusValidation) {
        this.inOutStatusValidation = inOutStatusValidation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getInStatus() {
        return inStatus;
    }

    public void setInStatus(Boolean inStatus) {
        this.inStatus = inStatus;
    }

    public Long getManualCardEntryId() {
        return manualCardEntryId;
    }

    public void setManualCardEntryId(Long manualCardEntryId) {
        this.manualCardEntryId = manualCardEntryId;
    }

    public Boolean getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(Boolean outStatus) {
        this.outStatus = outStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setManualCardEntryDataBean(ManualCardEntryDataBean cardEntryDataBean) {
        this.userId = cardEntryDataBean.getUserId();
        this.manualCardEntryId = cardEntryDataBean.getManualCardEntryId();
        this.date = cardEntryDataBean.getDate();
        this.inStatus = cardEntryDataBean.getInStatus();
        this.outStatus = cardEntryDataBean.getOutStatus();
        this.reason = cardEntryDataBean.getReason();
        this.adminComment = cardEntryDataBean.getAdminComment();
        this.status = cardEntryDataBean.getStatus();
        this.userName = cardEntryDataBean.getUserName();
        this.inOutStatusValidation = cardEntryDataBean.getInOutStatusValidation();
        this.responseUserName = cardEntryDataBean.getResponseUserName();
    }

    public void setNull() {
        this.userId = null;
        this.manualCardEntryId = null;
        this.date = null;
        this.inStatus = null;
        this.outStatus = null;
        this.reason = null;
        this.adminComment = null;
        this.status = null;
        this.userName = null;
        this.inOutStatusValidation = null;
        this.responseUserName = null;
    }
}
