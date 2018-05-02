/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author harshit
 */
@Entity
@Table(name = "shift")
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")})
public class Shift implements Serializable {
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "shift_end_time")
    @Temporal(TemporalType.TIME)
    private Date shiftEndTime;
    @Basic(optional = false)
    @Column(name = "shift_start_time")
    @Temporal(TemporalType.TIME)
    private Date shiftStartTime;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "shift_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long shiftId;
    @Basic(optional = false)
    @Column(name = "created_by")
    private Long createdBy;
    @Basic(optional = false)
    @Column(name = "is_active")
    private Boolean isActive;
    @Basic(optional = false)
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Basic(optional = false)
    @Column(name = "shift_name")
    private String shiftName;
    @OneToMany(mappedBy = "shiftId",fetch= FetchType.LAZY)
    private List<SystemUserDetail> systemUserDetailList;

    public Shift() {
    }

    public Shift(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Shift(Long shiftId, Long createdBy, Date createdDate, Boolean isActive, Boolean isArchive, Date shiftEndTime, String shiftName, Date shiftStartTime) {
        this.shiftId = shiftId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.isArchive = isArchive;
        this.shiftEndTime = shiftEndTime;
        this.shiftName = shiftName;
        this.shiftStartTime = shiftStartTime;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Date getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Date shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public List<SystemUserDetail> getSystemUserDetailList() {
        return systemUserDetailList;
    }

    public void setSystemUserDetailList(List<SystemUserDetail> systemUserDetailList) {
        this.systemUserDetailList = systemUserDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftId != null ? shiftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftId == null && other.shiftId != null) || (this.shiftId != null && !this.shiftId.equals(other.shiftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.Shift[ shiftId=" + shiftId + " ]";
    }

    
}
