/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.personal.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "cardLogTransactionDataBean")
@RequestScoped
public class CardLogTransactionDataBean {

    private Date cardPunchingTime;
    private String cardEntryReason;
    private String cardEntryType;
    private String userId;
    private int serialNumber;
    private Long id;
    private String userName;
    private Date fromDate;
    private Date toDate;
    private Long duration;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardEntryReason() {
        return cardEntryReason;
    }

    public void setCardEntryReason(String cardEntryReason) {
        this.cardEntryReason = cardEntryReason;
    }

    public String getCardEntryType() {
        return cardEntryType;
    }

    public void setCardEntryType(String cardEntryType) {
        this.cardEntryType = cardEntryType;
    }

    public Date getCardPunchingTime() {
        return cardPunchingTime;
    }

    public void setCardPunchingTime(Date cardPunchingTime) {
        this.cardPunchingTime = cardPunchingTime;
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

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    
}
