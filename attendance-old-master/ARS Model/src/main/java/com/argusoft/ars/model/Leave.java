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
@Table(name = "leave")
@NamedQueries({
    @NamedQuery(name = "Leave.findAll", query = "SELECT l FROM Leave l")})
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "leave_type")
    private String leaveType;
    @Column(name = "leave_subject")
    private String leaveSubject;
    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @Column(name = "from_date_leave_type")
    private String fromDateLeaveType;
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Column(name = "to_date_leave_type")
    private String toDateLeaveType;
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "applied_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedDate;
    @Column(name = "response_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @Column(name = "admin_comment")
    private String adminComment;
    @Basic(optional = false)
    @Column(name = "approval_status")
    private String approvalStatus;
    @Id
    @Basic(optional = false)
    @Column(name = "leave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    @Column(name = "applied_status")
    private String appliedStatus;
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Basic(optional = false)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "response_by")
    private Long responseBy;
    @Column(name = "holiday_id")
    private Long holidayId;
    @Column(name = "is_notification_show")
    private Boolean isNotificationShow;

    public Leave() {
    }

    public Leave(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Leave(Long leaveId, String leaveType, String leaveSubject, Date fromDate, String fromDateLeaveType, Date appliedDate, String approvalStatus, long userId) {
        this.leaveId = leaveId;
        this.leaveType = leaveType;
        this.leaveSubject = leaveSubject;
        this.fromDate = fromDate;
        this.fromDateLeaveType = fromDateLeaveType;
        this.appliedDate = appliedDate;
        this.approvalStatus = approvalStatus;
        this.userId = userId;
    }

    public Boolean getIsNotificationShow() {
        return isNotificationShow;
    }

    public void setIsNotificationShow(Boolean isNotificationShow) {
        this.isNotificationShow = isNotificationShow;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveSubject() {
        return leaveSubject;
    }

    public void setLeaveSubject(String leaveSubject) {
        this.leaveSubject = leaveSubject;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDateLeaveType() {
        return fromDateLeaveType;
    }

    public void setFromDateLeaveType(String fromDateLeaveType) {
        this.fromDateLeaveType = fromDateLeaveType;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getToDateLeaveType() {
        return toDateLeaveType;
    }

    public void setToDateLeaveType(String toDateLeaveType) {
        this.toDateLeaveType = toDateLeaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getAdminComment() {
        return adminComment;
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

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getResponseBy() {
        return responseBy;
    }

    public void setResponseBy(Long responseBy) {
        this.responseBy = responseBy;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveId != null ? leaveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leave)) {
            return false;
        }
        Leave other = (Leave) object;
        if ((this.leaveId == null && other.leaveId != null) || (this.leaveId != null && !this.leaveId.equals(other.leaveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.Leave[ leaveId=" + leaveId + " ]";
    }
}
