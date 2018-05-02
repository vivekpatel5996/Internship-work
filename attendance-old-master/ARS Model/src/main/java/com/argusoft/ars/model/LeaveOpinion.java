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
@Table(name = "leave_opinion")
@NamedQueries({
    @NamedQuery(name = "LeaveOpinion.findAll", query = "SELECT l FROM LeaveOpinion l")})
public class LeaveOpinion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "opinion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opinionDate;
    @Column(name = "opinion")
    private String opinion;
    @Id
    @Basic(optional = false)
    @Column(name = "leave_opinion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveOpinionId;
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "leave_id")
    private Long leaveId;
    @Column(name = "is_notification_show")
    private Boolean isNotificationShow;
    @Column(name = "is_selected_by_user")
    private Boolean isSelectedByUser;

    public LeaveOpinion() {
    }

    public LeaveOpinion(Long leaveOpinionId) {
        this.leaveOpinionId = leaveOpinionId;
    }

    public Boolean getIsSelectedByUser() {
        return isSelectedByUser;
    }

    public void setIsSelectedByUser(Boolean isSelectedByUser) {
        this.isSelectedByUser = isSelectedByUser;
    }

    public Boolean getIsNotificationShow() {
        return isNotificationShow;
    }

    public void setIsNotificationShow(Boolean isNotificationShow) {
        this.isNotificationShow = isNotificationShow;
    }

    public LeaveOpinion(Long leaveOpinionId, Long userId, Long leaveId) {
        this.leaveOpinionId = leaveOpinionId;
        this.userId = userId;
        this.leaveId = leaveId;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Date getOpinionDate() {
        return opinionDate;
    }

    public void setOpinionDate(Date opinionDate) {
        this.opinionDate = opinionDate;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Long getLeaveOpinionId() {
        return leaveOpinionId;
    }

    public void setLeaveOpinionId(Long leaveOpinionId) {
        this.leaveOpinionId = leaveOpinionId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveOpinionId != null ? leaveOpinionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveOpinion)) {
            return false;
        }
        LeaveOpinion other = (LeaveOpinion) object;
        if ((this.leaveOpinionId == null && other.leaveOpinionId != null) || (this.leaveOpinionId != null && !this.leaveOpinionId.equals(other.leaveOpinionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.LeaveOpinion[ leaveOpinionId=" + leaveOpinionId + " ]";
    }
}
