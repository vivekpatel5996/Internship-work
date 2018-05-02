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
@Table(name = "manual_card_entry")
@NamedQueries({
    @NamedQuery(name = "ManualCardEntry.findAll", query = "SELECT m FROM ManualCardEntry m")})
public class ManualCardEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "in_status")
    private boolean inStatus;
    @Basic(optional = false)
    @Column(name = "out_status")
    private boolean outStatus;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "approval_status")
    private String approvalStatus;
    @Basic(optional = false)
    @Column(name = "applied_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedDate;
    @Column(name = "admin_comment")
    private String adminComment;
    @Column(name = "applied_status")
    private String appliedStatus;
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Column(name = "response_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @Basic(optional = false)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "response_by")
    private Long responseBy;
    @Id
    @Basic(optional = false)
    @Column(name = "entry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;
    @Column(name = "is_notification_show")
    private Boolean isNotificationShow;

    public ManualCardEntry() {
    }

    public ManualCardEntry(Long entryId) {
        this.entryId = entryId;
    }

    public ManualCardEntry(Long entryId, Date fromDate, Date toDate, boolean inStatus, boolean outStatus, String reason, String approvalStatus, Date appliedDate, long userId) {
        this.entryId = entryId;
        this.date = fromDate;
        this.inStatus = inStatus;
        this.outStatus = outStatus;
        this.reason = reason;
        this.approvalStatus = approvalStatus;
        this.appliedDate = appliedDate;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date fromDate) {
        this.date = fromDate;
    }

    public boolean getInStatus() {
        return inStatus;
    }

    public void setInStatus(boolean inStatus) {
        this.inStatus = inStatus;
    }

    public boolean getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(boolean outStatus) {
        this.outStatus = outStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
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

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
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

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Boolean getIsNotificationShow() {
        return isNotificationShow;
    }

    public void setIsNotificationShow(Boolean isNotificationShow) {
        this.isNotificationShow = isNotificationShow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entryId != null ? entryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManualCardEntry)) {
            return false;
        }
        ManualCardEntry other = (ManualCardEntry) object;
        if ((this.entryId == null && other.entryId != null) || (this.entryId != null && !this.entryId.equals(other.entryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.ManualCardEntry[ entryId=" + entryId + " ]";
    }
}
