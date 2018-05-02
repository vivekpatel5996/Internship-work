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
@Table(name = "designation")
@NamedQueries({
    @NamedQuery(name = "Designation.findAll", query = "SELECT d FROM Designation d")})
public class Designation implements Serializable {
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "des_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long desId;
    @Basic(optional = false)
    @Column(name = "created_by")
    private Long createdBy;
    @Basic(optional = false)
    @Column(name = "des_name")
    private String desName;
    @Basic(optional = false)
    @Column(name = "is_active")
    private Boolean isActive;
    @Basic(optional = false)
    @Column(name = "is_archive")
    private Boolean isArchive;
    @OneToMany(mappedBy = "desgId",fetch= FetchType.LAZY)
    private List<SystemUserDetail> systemUserDetailList;

    public Designation() {
    }

    public Designation(Long desId) {
        this.desId = desId;
    }

    public Designation(Long desId, Long createdBy, Date createdDate, String desName, Boolean isActive, Boolean isArchive) {
        this.desId = desId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.desName = desName;
        this.isActive = isActive;
        this.isArchive = isArchive;
    }

    public Long getDesId() {
        return desId;
    }

    public void setDesId(Long desId) {
        this.desId = desId;
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

    public String getDesName() {
        return desName;
    }

    public void setDesName(String desName) {
        this.desName = desName;
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
        hash += (desId != null ? desId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Designation)) {
            return false;
        }
        Designation other = (Designation) object;
        if ((this.desId == null && other.desId != null) || (this.desId != null && !this.desId.equals(other.desId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.Designation[ desId=" + desId + " ]";
    }

    
    
}
