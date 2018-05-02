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
@Table(name = "system_user_detail")
@NamedQueries({
    @NamedQuery(name = "SystemUserDetail.findAll", query = "SELECT s FROM SystemUserDetail s")})
public class SystemUserDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Column(name = "join_date")
    @Temporal(TemporalType.DATE)
    private Date joinDate;
    @Column(name = "conformation_date")
    @Temporal(TemporalType.DATE)
    private Date conformationDate;
    @Column(name = "relieve_date")
    @Temporal(TemporalType.DATE)
    private Date relieveDate;
    @Column(name = "is_attendance_manager")
    private Boolean isAttendanceManager;
    @Column(name = "is_give_opinion")
    private Boolean isGiveOpinion;
    @Column(name = "emp_type")
    private String empType;
    @JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Shift shiftId;
    @JoinColumn(name = "desg_id", referencedColumnName = "des_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Designation desgId;
    @JoinColumn(name = "dep_id", referencedColumnName = "dept_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department depId;
    @JoinColumn(name = "card_enroll_no", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CardInventory cardEnrollNo;

    public SystemUserDetail() {
    }

    public SystemUserDetail(Long userId) {
        this.userId = userId;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public Date getConformationDate() {
        return conformationDate;
    }

    public void setConformationDate(Date conformationDate) {
        this.conformationDate = conformationDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getIsAttendanceManager() {
        return isAttendanceManager;
    }

    public void setIsAttendanceManager(Boolean isAttendanceManager) {
        this.isAttendanceManager = isAttendanceManager;
    }

    public Boolean getIsGiveOpinion() {
        return isGiveOpinion;
    }

    public void setIsGiveOpinion(Boolean isGiveOpinion) {
        this.isGiveOpinion = isGiveOpinion;
    }

    public Shift getShiftId() {
        return shiftId;
    }

    public void setShiftId(Shift shiftId) {
        this.shiftId = shiftId;
    }

    public Designation getDesgId() {
        return desgId;
    }

    public void setDesgId(Designation desgId) {
        this.desgId = desgId;
    }

    public Department getDepId() {
        return depId;
    }

    public void setDepId(Department depId) {
        this.depId = depId;
    }

    public CardInventory getCardEnrollNo() {
        return cardEnrollNo;
    }

    public void setCardEnrollNo(CardInventory cardEnrollNo) {
        this.cardEnrollNo = cardEnrollNo;
    }

    public Date getRelieveDate() {
        return relieveDate;
    }

    public void setRelieveDate(Date relieveDate) {
        this.relieveDate = relieveDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUserDetail)) {
            return false;
        }
        SystemUserDetail other = (SystemUserDetail) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.SystemUserDetail[ userId=" + userId + " ]";
    }
}
