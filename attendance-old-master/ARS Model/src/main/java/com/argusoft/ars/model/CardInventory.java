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
@Table(name = "card_inventory")
@NamedQueries({
    @NamedQuery(name = "CardInventory.findAll", query = "SELECT c FROM CardInventory c")})
public class CardInventory implements Serializable {

    @Basic(optional = false)
    @Column(name = "card_enroll_no")
    private String cardEnrollNo;
    @Column(name = "assign_user_id")
    private Long assignUserId;
    @Column(name = "card_id")
    private Long cardId;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "is_active")
    private Boolean isActive;
    @Basic(optional = false)
    @Column(name = "is_archive")
    private Boolean isArchive;
    @Basic(optional = false)
    @Column(name = "is_assigned")
    private Boolean isAssigned;
    @Column(name = "visitor_id")
    private Long visitorId;
    private static final Long serialVersionUID = 1L;
    @Column(name = "reason_to_delete")
    private String reasonToDelete;
    @Column(name = "vendor")
    private String vendor;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cardEnrollNo")
    private List<SystemUserDetail> systemUserDetailList;

    public CardInventory() {
    }

    public CardInventory(Long id) {
        this.id = id;
    }

    public CardInventory(Long id, String cardEnrollNo, Long cardId, Boolean isActive, Boolean isArchive, Boolean isAssigned) {
        this.id = id;
        this.cardEnrollNo = cardEnrollNo;
        this.isActive = isActive;
        this.isArchive = isArchive;
        this.isAssigned = isAssigned;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public String getCardEnrollNo() {
        return cardEnrollNo;
    }

    public void setCardEnrollNo(String cardEnrollNo) {
        this.cardEnrollNo = cardEnrollNo;
    }

    public Long getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Long assignUserId) {
        this.assignUserId = assignUserId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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

    public Boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getReasonToDelete() {
        return reasonToDelete;
    }

    public void setReasonToDelete(String reasonToDelete) {
        this.reasonToDelete = reasonToDelete;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SystemUserDetail> getSystemUserDetailList() {
        return systemUserDetailList;
    }

    public void setSystemUserDetailList(List<SystemUserDetail> systemUserDetailList) {
        this.systemUserDetailList = systemUserDetailList;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CardInventory)) {
            return false;
        }
        CardInventory other = (CardInventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.CardInventory[ id=" + id + " ]";
    }
}
