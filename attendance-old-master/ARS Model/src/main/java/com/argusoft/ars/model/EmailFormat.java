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
@Table(name = "email_format")
@NamedQueries({
    @NamedQuery(name = "EmailFormat.findAll", query = "SELECT e FROM EmailFormat e")})
public class EmailFormat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "email_format_name")
    private String emailFormatName;
    @Column(name = "cc")
    private String cc;
    @Column(name = "bcc")
    private String bcc;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @Column(name = "body")
    private String body;
    @Column(name = "footer")
    private String footer;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Id
    @Basic(optional = false)
    @Column(name = "email_format_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long emailFormatId;
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;
    @Basic(optional = false)
    @Column(name = "is_archive")
    private boolean isArchive;
    @Basic(optional = false)
    @Column(name = "created_by")
    private long createdBy;

    public EmailFormat() {
    }

    public EmailFormat(Long emailFormatId) {
        this.emailFormatId = emailFormatId;
    }

    public EmailFormat(Long emailFormatId, String emailFormatName, String subject, String body, Date createdDate, boolean isActive, boolean isArchive, long createdBy) {
        this.emailFormatId = emailFormatId;
        this.emailFormatName = emailFormatName;
        this.subject = subject;
        this.body = body;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.isArchive = isArchive;
        this.createdBy = createdBy;
    }

    public String getEmailFormatName() {
        return emailFormatName;
    }

    public void setEmailFormatName(String emailFormatName) {
        this.emailFormatName = emailFormatName;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getEmailFormatId() {
        return emailFormatId;
    }

    public void setEmailFormatId(Long emailFormatId) {
        this.emailFormatId = emailFormatId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(boolean isArchive) {
        this.isArchive = isArchive;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailFormatId != null ? emailFormatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailFormat)) {
            return false;
        }
        EmailFormat other = (EmailFormat) object;
        if ((this.emailFormatId == null && other.emailFormatId != null) || (this.emailFormatId != null && !this.emailFormatId.equals(other.emailFormatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.EmailFormat[ emailFormatId=" + emailFormatId + " ]";
    }
    
}
