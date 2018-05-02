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
 * @author sudhir
 */
@ManagedBean(name = "officialBreakDataBean")
@RequestScoped
public class OfficialBreakDataBean {
    private String officialBreakType;
    private Date fromDate;
    private Date toDate;
    private String reason;
    private Date appliedDate;
    private Date responseDate;
    private String adminComment;
    private String approvalStatus;
    private Long officialBreakId;
    private String appliedStatus;
    private Boolean isArchive;
    private Long userId;
    private Long responseBy;
    //private Float noOfDays;
    private String userName;
    private String cardLogFromDate;
    private String cardLogToDate;
    private String breakDuration;
    private String reasonValidationMessage;
    private String toDateValidationMessage;
    private String fromDateValidationMessage;
    private String responseByUserName;
    private long officialBreakDuration;
    

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getAppliedStatus() {
        return appliedStatus;
    }

    public void setAppliedStatus(String appliedStatus) {
        this.appliedStatus = appliedStatus;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Long getOfficialBreakId() {
        return officialBreakId;
    }

    public void setOfficialBreakId(Long officialBreakId) {
        this.officialBreakId = officialBreakId;
    }

    public String getOfficialBreakType() {
        return officialBreakType;
    }

    public void setOfficialBreakType(String officialBreakType) {
        this.officialBreakType = officialBreakType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getResponseBy() {
        return responseBy;
    }

    public void setResponseBy(Long responseBy) {
        this.responseBy = responseBy;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public String getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(String breakDuration) {
        this.breakDuration = breakDuration;
    }

    public void setFromDateValidationMessage(String fromDateValidationMessage) {
        this.fromDateValidationMessage = fromDateValidationMessage;
    }

    public void setReasonValidationMessage(String reasonValidationMessage) {
        this.reasonValidationMessage = reasonValidationMessage;
    }

    public void setToDateValidationMessage(String toDateValidationMessage) {
        this.toDateValidationMessage = toDateValidationMessage;
    }

    public String getFromDateValidationMessage() {
        return fromDateValidationMessage;
    }

    public String getReasonValidationMessage() {
        return reasonValidationMessage;
    }

    public String getToDateValidationMessage() {
        return toDateValidationMessage;
    }

    public String getCardLogFromDate() {
        return cardLogFromDate;
    }

    public void setCardLogFromDate(String cardLogFromDate) {
        this.cardLogFromDate = cardLogFromDate;
    }

    public String getCardLogToDate() {
        return cardLogToDate;
    }

    public void setCardLogToDate(String cardLogToDate) {
        this.cardLogToDate = cardLogToDate;
    }

    public String getResponseByUserName() {
        return responseByUserName;
    }

    public void setResponseByUserName(String responseByUserName) {
        this.responseByUserName = responseByUserName;
    }

    public long getOfficialBreakDuration() {
        return officialBreakDuration;
    }

    public void setOfficialBreakDuration(long officialBreakDuration) {
        this.officialBreakDuration = officialBreakDuration;
    }
    
}
