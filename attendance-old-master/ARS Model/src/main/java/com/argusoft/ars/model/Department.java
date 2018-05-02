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
@Table(name = "department")
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")})
public class Department implements Serializable {

    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private static final Long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    @Basic(optional = false)
    @Column(name = "created_by")
    private Long createdBy;
    @Basic(optional = false)
    @Column(name = "dept_name")
    private String deptName;
    @Basic(optional = false)
    @Column(name = "is_active")
    private Boolean isActive;
    @Basic(optional = false)
    @Column(name = "is_archive")
    private Boolean isArchive;
    @OneToMany(mappedBy = "depId", fetch = FetchType.LAZY)
    private List<SystemUserDetail> systemUserDetailList;

    public Department() {
    }

    public Department(Long deptId) {
        this.deptId = deptId;
    }

    public Department(Long deptId, Long createdBy, Date createdDate, String deptName, Boolean isActive, Boolean isArchive) {
        this.deptId = deptId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.deptName = deptName;
        this.isActive = isActive;
        this.isArchive = isArchive;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public List<SystemUserDetail> getSystemUserDetailList() {
        return systemUserDetailList;
    }

    public void setSystemUserDetailList(List<SystemUserDetail> systemUserDetailList) {
        this.systemUserDetailList = systemUserDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptId != null ? deptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.Department[ deptId=" + deptId + " ]";
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }
}
