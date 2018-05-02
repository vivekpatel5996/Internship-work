/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "cardLogDataBean")
@RequestScoped
public class CardLogDataBean {

    private Long cardId;
    private Date cardPunchingTime;
    private String cardEntryReason;
    private String cardEntryType;
    private String attendanceStatus;
    private String userId;
    private Date fromDate;
    private Date toDate;
    private Long id;
    private String userName;
    private String empId;
    private String duration;
    private Date entryTime;
    private String mobileNo;
    private String weekend;

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }
}
