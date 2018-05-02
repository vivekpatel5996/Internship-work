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
@ManagedBean(name = "attendanceDataBean")
@RequestScoped
public class AttendanceDataBean {

    private Long id;
    private String employeeName;
    private String empId;
    private Float presentDays;
    private Float absentDays;
    private Float leaveDays;
    private String totalWorkingHours;
    private String averageWorkingHours;
    private Integer lateDays;
    private Date fromDate;
    private Date toDate;
    private Integer totalDays;
    private Integer totalWorkingDays;
    private String userName;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Float getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(Float absentDays) {
        this.absentDays = absentDays;
    }

    public String getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(String totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public String getAverageWorkingHours() {
        return averageWorkingHours;
    }

    public void setAverageWorkingHours(String averageWorkingHours) {
        this.averageWorkingHours = averageWorkingHours;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLateDays() {
        return lateDays;
    }

    public void setLateDays(Integer lateDays) {
        this.lateDays = lateDays;
    }

    public Float getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Float leaveDays) {
        this.leaveDays = leaveDays;
    }

    public Float getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(Float presentDays) {
        this.presentDays = presentDays;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
