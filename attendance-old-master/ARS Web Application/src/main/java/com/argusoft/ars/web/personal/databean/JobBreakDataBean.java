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
@ManagedBean(name = "jobBreakDataBean")
@RequestScoped
public class JobBreakDataBean {

    private Date cardPunchingTime;
    private String totalInTime;
    private Date fromDate;
    private Date toDate;
    private String officialBreak;
    private boolean isValiduseOfCard;
    private String day;
    private long totalTime;
    private long officialBreakTime;
    

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

    public String getTotalInTime() {
        return totalInTime;
    }

    public void setTotalInTime(String totalInTime) {
        this.totalInTime = totalInTime;
    }

    public boolean isIsValiduseOfCard() {
        return isValiduseOfCard;
    }

    public void setIsValiduseOfCard(boolean isValiduseOfCard) {
        this.isValiduseOfCard = isValiduseOfCard;
    }

    public String getOfficialBreak() {
        return officialBreak;
    }

    public void setOfficialBreak(String officialBreak) {
        this.officialBreak = officialBreak;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getOfficialBreakTime() {
        return officialBreakTime;
    }

    public void setOfficialBreakTime(long officialBreakTime) {
        this.officialBreakTime = officialBreakTime;
    }
    
    
}
