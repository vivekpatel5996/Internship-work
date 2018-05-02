/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.util;

import com.argusoft.ars.web.leavemanagement.databean.*;
import com.argusoft.ars.web.personal.databean.AttendanceRegisterDataBean;
import com.argusoft.ars.web.personal.databean.CardLogTransactionDataBean;
import com.argusoft.ars.web.personal.databean.JobBreakDataBean;
import com.argusoft.ars.web.usermanagement.databean.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author mmodi
 */
@ManagedBean(name = "systemResultViewUtil")
@ViewScoped
public class SystemResultViewUtil {

    private List<SystemConfigurationDataBean> systemConfigurationDataBeanList;
//    List for the purpose of retriving and displaying the roles on searchRole page of admin
    private List<CardLogDataBean> cardLogDataBeanList;
    private List<CardLogTransactionDataBean> cardLogTransactionDataBeanList;
    private List<JobBreakDataBean> jobBreakDataBeanList;
    private List<AttendanceDataBean> attendanceDataBeanList;
    private List<RoleDataBean> roleDataBeanList;
    private List<UserDataBean> userDataBeanList;
    private List<DepartmentDataBean> departmentDataBeansList;
    private List<DesignationDataBean> designationDataBeansList;
    private List<HolidayDataBean> holidayDataBeansList;
    private List<ShiftDataBean> shiftDataBeansList;
    private List<VisitorDataBean> visitorDataBeansList;
    private List<CardInventoryDataBean> cardInventoryDataBeansList;
    private List<SystemUserDetailDataBean> systemUserDetailDataBeansList;
    private List<SystemUserDetailDataBean> systemUserDetailDataBeansList1;
    private List<LeaveDataBean> leaveDataBeans;
    private List<LeaveQuotaDataBean> leaveQuotaDataBeansList;
    private List<ManualCardEntryDataBean> manualCardEntryDataBeansList;
    private List<SystemConfigurationDataBean> leavePolicyDataBeansList;
    private List<CardLogDataBean> todaysAttendanceDataBeanList;
    private List<OfficialBreakDataBean> officialBreakDataBeans;
    private List<CardLogTransactionDataBean> cardLogTransactionExitDataBeanList;
    private List<CardLogTransactionDataBean> cardLogTransactionEntryDataBeanList;
    private List<LeaveOpinionDataBean> leaveOpinionDataBeans;
    private List<LeaveDataBean> leaveDetailOfLeaveOpininList;
    private List<LeaveDataBean> leaveCancelNotificationList;
    private List<LeaveDataBean> opinionLeaveResponseList;
    private List<LeaveDataBean> leaveHistoryList;
    private List<ExitProcessDataBean> exitProcessDataBeansList;
    private List<ExitProcessDataBean> cancelResignationNotificationList;
    private List<ExitProcessDataBean> resignationResponseNotificationList;
    private List<ConsecutiveMailDataBean> consecutiveMailList;
    private List<EmailDataBean> emailFormatDataBeansList;
    private List<HolidayDataBean> addNewHolidayList;
    private List<UserDataBean> userDataBeans;
    private List<AttendanceRegisterDataBean> attendanceRegisterDataBeans;
    private List<SelectItem> holidayList;
    private Long depId;
    private Boolean showAll;
    private Long empId;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public List<SelectItem> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<SelectItem> holidayList) {
        this.holidayList = holidayList;
    }

    public Boolean getShowAll() {
        return showAll;
    }

    public void setShowAll(Boolean showAll) {
        this.showAll = showAll;
    }

    public List<UserDataBean> getUserDataBeans() {
        return userDataBeans;
    }

    public void setUserDataBeans(List<UserDataBean> userDataBeans) {
        this.userDataBeans = userDataBeans;
    }

    public List<AttendanceRegisterDataBean> getAttendanceRegisterDataBeans() {
        return attendanceRegisterDataBeans;
    }

    public void setAttendanceRegisterDataBeans(List<AttendanceRegisterDataBean> attendanceRegisterDataBeans) {
        this.attendanceRegisterDataBeans = attendanceRegisterDataBeans;
    }

    public List<HolidayDataBean> getAddNewHolidayList() {
        return addNewHolidayList;
    }

    public void setAddNewHolidayList(List<HolidayDataBean> addNewHolidayList) {
        this.addNewHolidayList = addNewHolidayList;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public List<LeaveDataBean> getLeaveHistoryList() {
        return leaveHistoryList;
    }

    public void setLeaveHistoryList(List<LeaveDataBean> leaveHistoryList) {
        this.leaveHistoryList = leaveHistoryList;
    }

    public List<ExitProcessDataBean> getResignationResponseNotificationList() {
        return resignationResponseNotificationList;
    }

    public void setResignationResponseNotificationList(List<ExitProcessDataBean> resignationResponseNotificationList) {
        this.resignationResponseNotificationList = resignationResponseNotificationList;
    }

    public List<ExitProcessDataBean> getCancelResignationNotificationList() {
        return cancelResignationNotificationList;
    }

    public void setCancelResignationNotificationList(List<ExitProcessDataBean> cancelResignationNotificationList) {
        this.cancelResignationNotificationList = cancelResignationNotificationList;
    }

    public List<ExitProcessDataBean> getExitProcessDataBeansList() {
        return exitProcessDataBeansList;
    }

    public void setExitProcessDataBeansList(List<ExitProcessDataBean> exitProcessDataBeansList) {
        this.exitProcessDataBeansList = exitProcessDataBeansList;
    }

    public List<LeaveDataBean> getOpinionLeaveResponseList() {
        return opinionLeaveResponseList;
    }

    public void setOpinionLeaveResponseList(List<LeaveDataBean> opinionLeaveResponseList) {
        this.opinionLeaveResponseList = opinionLeaveResponseList;
    }

    public List<LeaveDataBean> getLeaveCancelNotificationList() {
        return leaveCancelNotificationList;
    }

    public void setLeaveCancelNotificationList(List<LeaveDataBean> leaveCancelNotificationList) {
        this.leaveCancelNotificationList = leaveCancelNotificationList;
    }

    public List<LeaveDataBean> getLeaveDetailOfLeaveOpininList() {
        return leaveDetailOfLeaveOpininList;
    }

    public void setLeaveDetailOfLeaveOpininList(List<LeaveDataBean> leaveDetailOfLeaveOpininList) {
        this.leaveDetailOfLeaveOpininList = leaveDetailOfLeaveOpininList;
    }

    public List<LeaveOpinionDataBean> getLeaveOpinionDataBeans() {
        return leaveOpinionDataBeans;
    }

    public void setLeaveOpinionDataBeans(List<LeaveOpinionDataBean> leaveOpinionDataBeans) {
        this.leaveOpinionDataBeans = leaveOpinionDataBeans;
    }

    public List<ConsecutiveMailDataBean> getConsecutiveMailList() {
        return consecutiveMailList;
    }

    public void setConsecutiveMailList(List<ConsecutiveMailDataBean> consecutiveMailList) {
        this.consecutiveMailList = consecutiveMailList;
    }

    public List<ManualCardEntryDataBean> getManualCardEntryDataBeansList() {
        return manualCardEntryDataBeansList;
    }

    public void setManualCardEntryDataBeansList(List<ManualCardEntryDataBean> manualCardEntryDataBeansList) {
        this.manualCardEntryDataBeansList = manualCardEntryDataBeansList;
    }

    public List<EmailDataBean> getEmailFormatDataBeansList() {
        return emailFormatDataBeansList;
    }

    public void setEmailFormatDataBeansList(List<EmailDataBean> emailFormatDataBeansList) {
        this.emailFormatDataBeansList = emailFormatDataBeansList;
    }

    public List<LeaveQuotaDataBean> getLeaveQuotaDataBeansList() {
        return leaveQuotaDataBeansList;
    }

    public void setLeaveQuotaDataBeansList(List<LeaveQuotaDataBean> leaveQuotaDataBeansList) {
        this.leaveQuotaDataBeansList = leaveQuotaDataBeansList;
    }

    public List<RoleDataBean> getRoleDataBeanList() {
        if (this.roleDataBeanList != null && this.roleDataBeanList.size() > 0) {
            RoleDataBeanSort roleDataBeanSort = new RoleDataBeanSort();
            Collections.sort(this.roleDataBeanList, roleDataBeanSort);
        }
        return roleDataBeanList;
    }

    public List<LeaveDataBean> getLeaveDataBeans() {
        return leaveDataBeans;
    }

    public void setLeaveDataBeans(List<LeaveDataBean> leaveDataBeans) {
        this.leaveDataBeans = leaveDataBeans;
    }

    public void setSystemUserDetailDataBeansList1(List<SystemUserDetailDataBean> systemUserDetailDataBeansList1) {
        this.systemUserDetailDataBeansList1 = systemUserDetailDataBeansList1;
    }

    public List<SystemConfigurationDataBean> getSystemConfigurationDataBeanList() {
        return systemConfigurationDataBeanList;
    }

    public void setSystemConfigurationDataBeanList(List<SystemConfigurationDataBean> systemConfigurationDataBeanList) {
        this.systemConfigurationDataBeanList = systemConfigurationDataBeanList;
    }

    public List<CardLogDataBean> getCardLogDataBeanList() {
        return cardLogDataBeanList;
    }

    public void setCardLogDataBeanList(List<CardLogDataBean> cardLogDataBeanList) {
        this.cardLogDataBeanList = cardLogDataBeanList;
    }

    public List<CardLogTransactionDataBean> getCardLogTransactionDataBeanList() {
        return cardLogTransactionDataBeanList;
    }

    public void setCardLogTransactionDataBeanList(List<CardLogTransactionDataBean> cardLogTransactionDataBeanList) {
        this.cardLogTransactionDataBeanList = cardLogTransactionDataBeanList;
    }

    public List<VisitorDataBean> getVisitorDataBeansList() {
        return visitorDataBeansList;
    }

    public void setVisitorDataBeansList(List<VisitorDataBean> visitorDataBeansList) {
        this.visitorDataBeansList = visitorDataBeansList;
    }

//    Class to sort the roleDataBeanList
    class RoleDataBeanSort implements Comparator<RoleDataBean> {

        @Override
        public int compare(RoleDataBean roleDataBeanONE, RoleDataBean roleDataBeanTWO) {
            return roleDataBeanONE.getRoleName().compareTo(roleDataBeanTWO.getRoleName());
        }
    }

    public List<CardInventoryDataBean> getCardInventoryDataBeansList() {
        return cardInventoryDataBeansList;
    }

    public void setCardInventoryDataBeansList(List<CardInventoryDataBean> cardInventoryDataBeansList) {
        this.cardInventoryDataBeansList = cardInventoryDataBeansList;
    }

    public List<DepartmentDataBean> getDepartmentDataBeansList() {
        return departmentDataBeansList;
    }

    public void setDepartmentDataBeansList(List<DepartmentDataBean> departmentDataBeansList) {
        this.departmentDataBeansList = departmentDataBeansList;
    }

    public List<DesignationDataBean> getDesignationDataBeansList() {
        return designationDataBeansList;
    }

    public void setDesignationDataBeansList(List<DesignationDataBean> designationDataBeansList) {
        this.designationDataBeansList = designationDataBeansList;
    }

    public List<HolidayDataBean> getHolidayDataBeansList() {
        return holidayDataBeansList;
    }

    public void setHolidayDataBeansList(List<HolidayDataBean> holidayDataBeansList) {
        this.holidayDataBeansList = holidayDataBeansList;
    }

    public List<ShiftDataBean> getShiftDataBeansList() {
        return shiftDataBeansList;
    }

    public void setShiftDataBeansList(List<ShiftDataBean> shiftDataBeansList) {
        this.shiftDataBeansList = shiftDataBeansList;
    }

    public List<SystemUserDetailDataBean> getSystemUserDetailDataBeansList() {
        return systemUserDetailDataBeansList;
    }

    public void setSystemUserDetailDataBeansList(List<SystemUserDetailDataBean> systemUserDetailDataBeansList) {
        this.systemUserDetailDataBeansList = systemUserDetailDataBeansList;
    }

    public List<SystemUserDetailDataBean> getSystemUserDetailDataBeansList1() {
        return systemUserDetailDataBeansList1;
    }

    public void setRoleDataBeanList(List<RoleDataBean> roleDataBeanList) {
        this.roleDataBeanList = roleDataBeanList;
    }

    public List<UserDataBean> getUserDataBeanList() {
        return userDataBeanList;
    }

    public void setUserDataBeanList(List<UserDataBean> userDataBeanList) {
        this.userDataBeanList = userDataBeanList;
    }

    public List<AttendanceDataBean> getAttendanceDataBeanList() {
        return attendanceDataBeanList;
    }

    public void setAttendanceDataBeanList(List<AttendanceDataBean> attendanceDataBeanList) {
        this.attendanceDataBeanList = attendanceDataBeanList;
    }

    public List<JobBreakDataBean> getJobBreakDataBeanList() {
        return jobBreakDataBeanList;
    }

    public void setJobBreakDataBeanList(List<JobBreakDataBean> jobBreakDataBeanList) {
        this.jobBreakDataBeanList = jobBreakDataBeanList;
    }

    public List<SystemConfigurationDataBean> getLeavePolicyDataBeansList() {
        return leavePolicyDataBeansList;
    }

    public void setLeavePolicyDataBeansList(List<SystemConfigurationDataBean> leavePolicyDataBeansList) {
        this.leavePolicyDataBeansList = leavePolicyDataBeansList;
    }

    public List<CardLogDataBean> getTodaysAttendanceDataBeanList() {
        return todaysAttendanceDataBeanList;
    }

    public void setTodaysAttendanceDataBeanList(List<CardLogDataBean> todaysAttendanceDataBeanList) {
        this.todaysAttendanceDataBeanList = todaysAttendanceDataBeanList;
    }

    public List<OfficialBreakDataBean> getOfficialBreakDataBeans() {
        return officialBreakDataBeans;
    }

    public void setOfficialBreakDataBeans(List<OfficialBreakDataBean> officialBreakDataBeans) {
        this.officialBreakDataBeans = officialBreakDataBeans;
    }

    public List<CardLogTransactionDataBean> getCardLogTransactionEntryDataBeanList() {
        return cardLogTransactionEntryDataBeanList;
    }

    public void setCardLogTransactionEntryDataBeanList(List<CardLogTransactionDataBean> cardLogTransactionEntryDataBeanList) {
        this.cardLogTransactionEntryDataBeanList = cardLogTransactionEntryDataBeanList;
    }

    public List<CardLogTransactionDataBean> getCardLogTransactionExitDataBeanList() {
        return cardLogTransactionExitDataBeanList;
    }

    public void setCardLogTransactionExitDataBeanList(List<CardLogTransactionDataBean> cardLogTransactionExitDataBeanList) {
        this.cardLogTransactionExitDataBeanList = cardLogTransactionExitDataBeanList;
    }
}
