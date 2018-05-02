/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sudhir
 */

@ManagedBean(name = "emailDataBean")
@ViewScoped
public class EmailDataBean {

   private String formatName;
   private String footer;
   private String subject;
   private String cc;
   private String bcc;
   private String body;
   private Long emailId;

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formateName) {
        this.formatName = formateName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void databeanNull()
    {
        this.formatName = null;
        this.footer = null;
        this.subject = null;
        this.cc = null;
        this.bcc = null;
        this.body = null;
        this.emailId = null;
    }
   
    
}
