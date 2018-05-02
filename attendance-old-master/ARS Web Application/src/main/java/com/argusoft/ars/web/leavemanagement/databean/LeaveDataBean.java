/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.databean;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "leaveDataBean")
@RequestScoped
public class LeaveDataBean {

    private String leaveType;
    private String leaveSubject;
    private Date fromDate;
    private String fromDateLeaveType;
    private Date toDate;
    private String toDateLeaveType;
    private String reason;
    private Date appliedDate;
    private Date responseDate;
    private String adminComment;
    private String approvalStatus;
    private Long leaveId;
    private String appliedStatus;
    private Boolean isArchive;
    private Long userId;
    private Long responseBy;
    private Long holidayId;
    private Float noOfDays;
    private String holidayName;
    private String userName;
    private Float availableCasualLeave;
    private Float availableEarnLeave;
    private Float availableRestrictedHolidayLeave;
    private Float lossOfPayLeave;
    private String ristricedHolidayValidationMessage;
    private String reasonValidationMessage;
    private String fromDateValidationMessage;
    private String toDateValidationMessage;
    private String fromDateTypeValidationMessage;
    private String toDateTypeValidationMessage;
    private String leaveSubjectValidationMessage;
    private String resposeByUserName;
    private String previosAdminComment;
    private Long[] userIdList;
    List<Long> userIds;
    private Long leaveOpinionId;
    private String empId;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Long getLeaveOpinionId() {
        return leaveOpinionId;
    }

    public void setLeaveOpinionId(Long leaveOpinionId) {
        this.leaveOpinionId = leaveOpinionId;
    }

    public Long[] getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(Long[] userIdList) {
        this.userIdList = userIdList;
    }

    public Float getLossOfPayLeave() {
        return lossOfPayLeave;
    }

    public void setLossOfPayLeave(Float lossOfPayLeave) {
        this.lossOfPayLeave = lossOfPayLeave;
    }

    public String getPreviosAdminComment() {
        return previosAdminComment;
    }

    public void setPreviosAdminComment(String previosAdminComment) {
        this.previosAdminComment = previosAdminComment;
    }

    public String getResposeByUserName() {
        return resposeByUserName;
    }

    public void setResposeByUserName(String resposeByUserName) {
        this.resposeByUserName = resposeByUserName;
    }

    public String getFromDateTypeValidationMessage() {
        return fromDateTypeValidationMessage;
    }

    public void setFromDateTypeValidationMessage(String fromDateTypeValidationMessage) {
        this.fromDateTypeValidationMessage = fromDateTypeValidationMessage;
    }

    public String getFromDateValidationMessage() {
        return fromDateValidationMessage;
    }

    public void setFromDateValidationMessage(String fromDateValidationMessage) {
        this.fromDateValidationMessage = fromDateValidationMessage;
    }

    public String getLeaveSubjectValidationMessage() {
        return leaveSubjectValidationMessage;
    }

    public void setLeaveSubjectValidationMessage(String leaveSubjectValidationMessage) {
        this.leaveSubjectValidationMessage = leaveSubjectValidationMessage;
    }

    public String getReasonValidationMessage() {
        return reasonValidationMessage;
    }

    public void setReasonValidationMessage(String reasonValidationMessage) {
        this.reasonValidationMessage = reasonValidationMessage;
    }

    public String getRistricedHolidayValidationMessage() {
        return ristricedHolidayValidationMessage;
    }

    public void setRistricedHolidayValidationMessage(String ristricedHolidayValidationMessage) {
        this.ristricedHolidayValidationMessage = ristricedHolidayValidationMessage;
    }

    public String getToDateTypeValidationMessage() {
        return toDateTypeValidationMessage;
    }

    public void setToDateTypeValidationMessage(String toDateTypeValidationMessage) {
        this.toDateTypeValidationMessage = toDateTypeValidationMessage;
    }

    public String getToDateValidationMessage() {
        return toDateValidationMessage;
    }

    public void setToDateValidationMessage(String toDateValidationMessage) {
        this.toDateValidationMessage = toDateValidationMessage;
    }

    public Float getAvailableCasualLeave() {
        if (availableCasualLeave != null) {
            return Math.round(availableCasualLeave * 100.0F) / 100.0F;
        }
        return availableCasualLeave;
    }

    public void setAvailableCasualLeave(Float availableCasualLeave) {
        this.availableCasualLeave = availableCasualLeave;
    }

    public Float getAvailableEarnLeave() {
        if (availableEarnLeave != null) {
            return Math.round(availableEarnLeave * 100.0F) / 100.0F;
        }
        return availableEarnLeave;
    }

    public void setAvailableEarnLeave(Float availableEarnLeave) {
        this.availableEarnLeave = availableEarnLeave;
    }

    public Float getAvailableRestrictedHolidayLeave() {
        return availableRestrictedHolidayLeave;
    }

    public void setAvailableRestrictedHolidayLeave(Float availableRestrictedHolidayLeave) {
        this.availableRestrictedHolidayLeave = availableRestrictedHolidayLeave;
    }

    public Float getLossOfpayLeave() {
        if (lossOfPayLeave != null) {
            return Math.round(lossOfPayLeave * 100.0F) / 100.0F;
        }
        return lossOfPayLeave;
    }

    public void setLossOfpayLeave(Float lossOfpayLeave) {
        this.lossOfPayLeave = lossOfpayLeave;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Float getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Float noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getAppliedStatus() {
        return appliedStatus;
    }

    public void setAppliedStatus(String appliedStatus) {
        this.appliedStatus = appliedStatus;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDateLeaveType() {
        return fromDateLeaveType;
    }

    public void setFromDateLeaveType(String fromDateLeaveType) {
        this.fromDateLeaveType = fromDateLeaveType;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveSubject() {
        return leaveSubject;
    }

    public void setLeaveSubject(String leaveSubject) {
        this.leaveSubject = leaveSubject;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getResponseBy() {
        return responseBy;
    }

    public void setResponseBy(Long responseBy) {
        this.responseBy = responseBy;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getToDateLeaveType() {
        return toDateLeaveType;
    }

    public void setToDateLeaveType(String toDateLeaveType) {
        this.toDateLeaveType = toDateLeaveType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLeaveDataBeanNull() {
        this.leaveType = null;
        this.leaveSubject = null;
        this.fromDate = null;
        this.fromDateLeaveType = null;
        this.toDate = null;
        this.toDateLeaveType = null;
        this.reason = null;
        this.appliedDate = null;
        this.responseDate = null;
        this.adminComment = null;
        this.approvalStatus = null;
        this.leaveId = null;
        this.appliedStatus = null;
        this.isArchive = null;
        this.userId = null;
        this.responseBy = null;
        this.holidayId = null;
        this.noOfDays = null;
        this.holidayName = null;
        this.userName = null;
        this.availableCasualLeave = null;
        this.availableEarnLeave = null;
        this.availableRestrictedHolidayLeave = null;
        this.lossOfPayLeave = null;
        this.ristricedHolidayValidationMessage = null;
        this.reasonValidationMessage = null;
        this.fromDateValidationMessage = null;
        this.toDateValidationMessage = null;
        this.fromDateTypeValidationMessage = null;
        this.toDateTypeValidationMessage = null;
        this.leaveSubjectValidationMessage = null;
        this.resposeByUserName = null;
    }
}
