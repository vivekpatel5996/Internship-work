/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "exitProcessDataBean")
@ViewScoped
public class ExitProcessDataBean {

    private Long exitId;
    private Long userId;
    private String userName;
    private String discription;
    private Date expectedReliveDate;
    private String fileName;
    private String fileExtenstion;
    private String adminComment;
    private byte[] fileData;
    private String mime;
    private String responseUserName;
    private String approvalStatus;
    private String appliedStatus;
    private Boolean isArchive;
    private String tempAdminComment;
    private Date actualReliveDate;

    public String getTempAdminComment() {
        return tempAdminComment;
    }

    public void setTempAdminComment(String tempAdminComment) {
        this.tempAdminComment = tempAdminComment;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
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

    public String getResponseUserName() {
        return responseUserName;
    }

    public void setResponseUserName(String responseUserName) {
        this.responseUserName = responseUserName;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Long getExitId() {
        return exitId;
    }

    public void setExitId(Long exitId) {
        this.exitId = exitId;
    }

    public Date getExpectedReliveDate() {
        return expectedReliveDate;
    }

    public void setExpectedReliveDate(Date expectedReliveDate) {
        this.expectedReliveDate = expectedReliveDate;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileDate) {
        this.fileData = fileDate;
    }

    public String getFileExtenstion() {
        return fileExtenstion;
    }

    public void setFileExtenstion(String fileExtenstion) {
        this.fileExtenstion = fileExtenstion;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    public Date getActualReliveDate() {
        return actualReliveDate;
    }

    public void setActualReliveDate(Date actualReliveDate) {
        this.actualReliveDate = actualReliveDate;
    }

    public void setExitProcessDataBean(ExitProcessDataBean exitProcessDataBean) {
        if (exitProcessDataBean != null) {
            this.exitId = exitProcessDataBean.getExitId();
            this.userId = exitProcessDataBean.getUserId();
            this.userName = exitProcessDataBean.getUserName();
            this.discription = exitProcessDataBean.getDiscription();
            this.expectedReliveDate = exitProcessDataBean.getExpectedReliveDate();
            this.fileName = exitProcessDataBean.getFileName();
            this.fileExtenstion = exitProcessDataBean.getFileExtenstion();
            this.adminComment = exitProcessDataBean.getAdminComment();
            this.fileData = exitProcessDataBean.getFileData();
            this.appliedStatus = exitProcessDataBean.getAppliedStatus();
            this.approvalStatus = exitProcessDataBean.getApprovalStatus();
            this.isArchive = exitProcessDataBean.getIsArchive();
            this.tempAdminComment = exitProcessDataBean.getTempAdminComment();
        }
    }

    public void setNull() {
        this.exitId = null;
        this.userId = null;
        this.userName = null;
        this.discription = null;
        this.expectedReliveDate = null;
        this.fileName = null;
        this.fileExtenstion = null;
        this.adminComment = null;
        this.fileData = null;
        this.appliedStatus = null;
        this.approvalStatus = null;
        this.isArchive = null;
        this.tempAdminComment = null;
    }
}
