/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author argus2
 */
@Entity
@Table(name = "official_break")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OfficialBreak.findAll", query = "SELECT o FROM OfficialBreak o"),
    @NamedQuery(name = "OfficialBreak.findByType", query = "SELECT o FROM OfficialBreak o WHERE o.type = :type"),
    @NamedQuery(name = "OfficialBreak.findByFromDate", query = "SELECT o FROM OfficialBreak o WHERE o.fromDate = :fromDate"),
    @NamedQuery(name = "OfficialBreak.findByToDate", query = "SELECT o FROM OfficialBreak o WHERE o.toDate = :toDate"),
    @NamedQuery(name = "OfficialBreak.findByReason", query = "SELECT o FROM OfficialBreak o WHERE o.reason = :reason"),
    @NamedQuery(name = "OfficialBreak.findByAppliedDate", query = "SELECT o FROM OfficialBreak o WHERE o.appliedDate = :appliedDate"),
    @NamedQuery(name = "OfficialBreak.findByAdminComment", query = "SELECT o FROM OfficialBreak o WHERE o.adminComment = :adminComment"),
    @NamedQuery(name = "OfficialBreak.findByApprovalStatus", query = "SELECT o FROM OfficialBreak o WHERE o.approvalStatus = :approvalStatus"),
    @NamedQuery(name = "OfficialBreak.findByOfficialBreakId", query = "SELECT o FROM OfficialBreak o WHERE o.officialBreakId = :officialBreakId"),
    @NamedQuery(name = "OfficialBreak.findByAppliedStatus", query = "SELECT o FROM OfficialBreak o WHERE o.appliedStatus = :appliedStatus"),
    @NamedQuery(name = "OfficialBreak.findByIsArchive", query = "SELECT o FROM OfficialBreak o WHERE o.isArchive = :isArchive"),
    @NamedQuery(name = "OfficialBreak.findByUserId", query = "SELECT o FROM OfficialBreak o WHERE o.userId = :userId"),
    @NamedQuery(name = "OfficialBreak.findByResponseBy", query = "SELECT o FROM OfficialBreak o WHERE o.responseBy = :responseBy"),
    @NamedQuery(name = "OfficialBreak.findByIsNotificationShow", query = "SELECT o FROM OfficialBreak o WHERE o.isNotificationShow = :isNotificationShow"),
    @NamedQuery(name = "OfficialBreak.findByResponseDate", query = "SELECT o FROM OfficialBreak o WHERE o.responseDate = :responseDate")})
public class OfficialBreak implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Basic(optional = false)
    @Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "applied_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedDate;
    @Column(name = "admin_comment")
    private String adminComment;
    @Column(name = "approval_status")
    private String approvalStatus;
    @Id
    @Basic(optional = false)
    @Column(name = "official_break_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long officialBreakId;
    @Column(name = "applied_status")
    private String appliedStatus;
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "response_by")
    private Long responseBy;
    @Basic(optional = false)
    @Column(name = "is_notification_show")
    private boolean isNotificationShow;
    @Column(name = "response_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;

    public OfficialBreak() {
    }

    public OfficialBreak(Long officialBreakId) {
        this.officialBreakId = officialBreakId;
    }

    public OfficialBreak(Long officialBreakId, String type, Date fromDate, Date toDate, String reason, Date appliedDate, String approvalStatus, long userId, boolean isNotificationShow) {
        this.officialBreakId = officialBreakId;
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.appliedDate = appliedDate;
        this.approvalStatus = approvalStatus;
        this.userId = userId;
        this.isNotificationShow = isNotificationShow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public Long getOfficialBreakId() {
        return officialBreakId;
    }

    public void setOfficialBreakId(Long officialBreakId) {
        this.officialBreakId = officialBreakId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getResponseBy() {
        return responseBy;
    }

    public void setResponseBy(Long responseBy) {
        this.responseBy = responseBy;
    }

    public boolean getIsNotificationShow() {
        return isNotificationShow;
    }

    public void setIsNotificationShow(boolean isNotificationShow) {
        this.isNotificationShow = isNotificationShow;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (officialBreakId != null ? officialBreakId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfficialBreak)) {
            return false;
        }
        OfficialBreak other = (OfficialBreak) object;
        if ((this.officialBreakId == null && other.officialBreakId != null) || (this.officialBreakId != null && !this.officialBreakId.equals(other.officialBreakId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.OfficialBreak[ officialBreakId=" + officialBreakId + " ]";
    }
    
}
