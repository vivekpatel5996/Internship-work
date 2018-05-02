/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author harshit
 */
@Entity
@Table(name = "exit_process")
@NamedQueries({
    @NamedQuery(name = "ExitProcess.findAll", query = "SELECT e FROM ExitProcess e")})
public class ExitProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "expected_relieve_date")
    @Temporal(TemporalType.DATE)
    private Date expectedRelieveDate;
    @Column(name = "actual_relieve_date")
    @Temporal(TemporalType.DATE)
    private Date actualRelieveDate;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;
    @Column(name = "doc_name")
    private String docName;
    @Basic(optional = false)
    @Column(name = "requested_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedDate;
    @Column(name = "admin_comment")
    private String adminComment;
    @Column(name = "approval_status")
    private String approvalStatus;
    @Id
    @Basic(optional = false)
    @Column(name = "exit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exitId;
    @Column(name = "response_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @Column(name = "applied_status")
    private String appliedStatus;
    @Column(name = "is_notification_show")
    private Boolean isNotificationShow;
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "response_by")
    private Long responseBy;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "doc_data")
    private byte[] docData;

    public ExitProcess() {
    }

    public ExitProcess(Long exitId) {
        this.exitId = exitId;
    }

    public ExitProcess(Long exitId, Date expectedRelieveDate, String reason, Date requestedDate, String approvalStatus, long userId) {
        this.exitId = exitId;
        this.expectedRelieveDate = expectedRelieveDate;
        this.reason = reason;
        this.requestedDate = requestedDate;
        this.approvalStatus = approvalStatus;
        this.userId = userId;
    }

    public Date getExpectedRelieveDate() {
        return expectedRelieveDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setExpectedRelieveDate(Date expectedRelieveDate) {
        this.expectedRelieveDate = expectedRelieveDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public Boolean getIsNotificationShow() {
        return isNotificationShow;
    }

    public void setIsNotificationShow(Boolean isNotificationShow) {
        this.isNotificationShow = isNotificationShow;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getExitId() {
        return exitId;
    }

    public void setExitId(Long exitId) {
        this.exitId = exitId;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getAppliedStatus() {
        return appliedStatus;
    }

    public void setAppliedStatus(String appliedStatus) {
        this.appliedStatus = appliedStatus;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Long getResponseBy() {
        return responseBy;
    }

    public void setResponseBy(Long responseBy) {
        this.responseBy = responseBy;
    }

    public byte[] getDocData() {
        return docData;
    }

    public void setDocData(byte[] docData) {
        this.docData = docData;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Date getActualRelieveDate() {
        return actualRelieveDate;
    }

    public void setActualRelieveDate(Date actualRelieveDate) {
        this.actualRelieveDate = actualRelieveDate;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exitId != null ? exitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExitProcess)) {
            return false;
        }
        ExitProcess other = (ExitProcess) object;
        if ((this.exitId == null && other.exitId != null) || (this.exitId != null && !this.exitId.equals(other.exitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.ExitProcess[ exitId=" + exitId + " ]";
    }
}
