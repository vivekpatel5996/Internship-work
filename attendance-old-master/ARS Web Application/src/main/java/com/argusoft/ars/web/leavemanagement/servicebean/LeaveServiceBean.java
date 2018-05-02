/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.servicebean;

import com.argusoft.ars.model.Holiday;
import com.argusoft.ars.web.leavemanagement.databean.LeaveDataBean;
import com.argusoft.ars.web.leavemanagement.databean.LeaveOpinionDataBean;
import com.argusoft.ars.web.leavemanagement.databean.LeaveQuotaDataBean;
import com.argusoft.ars.web.leavemanagement.transformerbean.LeaveTransformerBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.servicebean.UserServiceBean;
import com.argusoft.ars.web.usermanagement.transformerbean.HolidayTransformerBean;
import com.argusoft.ars.web.usermanagement.transformerbean.SystemConfigurationTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "leaveServiceBean")
@RequestScoped
public class LeaveServiceBean {

    @ManagedProperty(value = "#{leaveDataBean}")
    private LeaveDataBean leaveDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{leaveTransformerBean}")
    private LeaveTransformerBean leaveTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{holidayTransformerBean}")
    private HolidayTransformerBean holidayTransformerBean;
    @ManagedProperty(value = "#{systemConfigurationTransformerBean}")
    private SystemConfigurationTransformerBean systemConfigurationTransformerBean;
    @ManagedProperty(value = "#{leaveQuotaDataBean}")
    private LeaveQuotaDataBean leaveQuotaDataBean;
    @ManagedProperty(value = "#{leaveOpinionDataBean}")
    private LeaveOpinionDataBean leaveOpinionDataBean;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{userServiceBean}")
    private UserServiceBean userServiceBean;
    private Long leaveId;
    private String userName;
    private Long userId;
    private String leaveType;
    private Long leaveOpinionId;
    private Date leaveFromDate;
    private Date leaveToDate;
    private List<Integer> leaveHistoryYearList;
    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setLeaveHistoryYearList(List<Integer> leaveHistoryYearList) {
        this.leaveHistoryYearList = leaveHistoryYearList;
    }

    public UserServiceBean getUserServiceBean() {
        return userServiceBean;
    }

    public void setUserServiceBean(UserServiceBean userServiceBean) {
        this.userServiceBean = userServiceBean;
    }

    public List<Integer> getLeaveHistoryYearList() {
        leaveHistoryYearList = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
        Integer curYear = cal.get(Calendar.YEAR);
        leaveHistoryYearList.add(curYear + 1);
        for (int i = 0; i < 10; i++) {
            leaveHistoryYearList.add(curYear - i);
        }
        return leaveHistoryYearList;
    }

    public Long getLeaveOpinionId() {
        return leaveOpinionId;
    }

    public void setLeaveOpinionId(Long leaveOpinionId) {
        this.leaveOpinionId = leaveOpinionId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LeaveQuotaDataBean getLeaveQuotaDataBean() {
        return leaveQuotaDataBean;
    }

    public void setLeaveQuotaDataBean(LeaveQuotaDataBean leaveQuotaDataBean) {
        this.leaveQuotaDataBean = leaveQuotaDataBean;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Date getLeaveFromDate() {
        return leaveFromDate;
    }

    public void setLeaveFromDate(Date leaveFromDate) {
        this.leaveFromDate = leaveFromDate;
    }

    public Date getLeaveToDate() {
        return leaveToDate;
    }

    public void setLeaveToDate(Date leaveToDate) {
        this.leaveToDate = leaveToDate;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public LeaveOpinionDataBean getLeaveOpinionDataBean() {
        return leaveOpinionDataBean;
    }

    public void setLeaveOpinionDataBean(LeaveOpinionDataBean leaveOpinionDataBean) {
        this.leaveOpinionDataBean = leaveOpinionDataBean;
    }

    public SystemConfigurationTransformerBean getSystemConfigurationTransformerBean() {
        return systemConfigurationTransformerBean;
    }

    public void setSystemConfigurationTransformerBean(SystemConfigurationTransformerBean systemConfigurationTransformerBean) {
        this.systemConfigurationTransformerBean = systemConfigurationTransformerBean;
    }

    public HolidayTransformerBean getHolidayTransformerBean() {
        return holidayTransformerBean;
    }

    public void setHolidayTransformerBean(HolidayTransformerBean holidayTransformerBean) {
        this.holidayTransformerBean = holidayTransformerBean;
    }

    public LeaveDataBean getLeaveDataBean() {
        return leaveDataBean;
    }

    public void setLeaveDataBean(LeaveDataBean leaveDataBean) {
        this.leaveDataBean = leaveDataBean;
    }

    public LeaveTransformerBean getLeaveTransformerBean() {
        return leaveTransformerBean;
    }

    public void setLeaveTransformerBean(LeaveTransformerBean leaveTransformerBean) {
        this.leaveTransformerBean = leaveTransformerBean;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    public void applyLeave() {
        System.out.println("Service Bean Appply Leave");
        Boolean isInformationCorrect = isLeaveInformationCorrect();
        if (isInformationCorrect) {
            String response = leaveTransformerBean.applyLeave(leaveDataBean);
            if (response.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Leave applied successfully.");
                messageDataBean.setIsSuccess(true);
                this.retrieveleaveList();
                leaveDataBean.setLeaveDataBeanNull();
            } else {
                messageDataBean.setIsSuccess(false);
                messageDataBean.setMessage(response);
            }
        } else {
            messageDataBean.setIsSuccess(false);
        }
    }

    public void modifyLeave() {
        try {
            System.out.println("ServiceBean Modify Leave");
            Boolean isInformationCorrect = isLeaveInformationCorrect();
            if (isInformationCorrect) {
                String response = leaveTransformerBean.updateLeave(leaveDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setMessage("Leave modified successfully.");
                    messageDataBean.setIsSuccess(true);
                    this.retrieveleaveList();
                } else {
                    messageDataBean.setIsSuccess(false);
                    messageDataBean.setMessage(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelLeave() {
        System.out.println("ServiceBean cancel Leave");
        String response = leaveTransformerBean.cancelLeave(leaveDataBean);
        if (response.equals(SystemConstantUtil.SUCCESS)) {
            messageDataBean.setMessage("Leave cancelled successfully.");
            messageDataBean.setIsSuccess(true);
            this.retrieveleaveList();
        } else {
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(response);
        }
    }

    public void retrieveleaveList() {
        try {
            System.out.println("Retrive list");
            systemResultViewUtil.setLeaveDataBeans(leaveTransformerBean.retrieveAllLeave());
        } catch (Exception e) {
            System.out.println("Error in Retrive List");
            System.out.println(e);
        }

    }

    public void retrieveleaveNotificationList() {
        try {
            System.out.println("Retrieve list");
            systemResultViewUtil.setLeaveDataBeans(leaveTransformerBean.retrieveLeaveNotificationList());
        } catch (Exception e) {
            System.out.println("Error in Retrieve List");
            System.out.println(e);
        }

    }

    public void retrieveNotification() {
        try {
            systemResultViewUtil.setLeaveCancelNotificationList(leaveTransformerBean.retrieveCancelLeaveNotification());
            systemResultViewUtil.setOpinionLeaveResponseList(leaveTransformerBean.retrieveOpinionLeaveResponseList());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Float availableCasualLeaves() {
        try {
            return leaveTransformerBean.getAvailableCasualLeave(leaveId, false);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void retrieveRestrictedHolidayList() {
        Calendar fromDateCal = Calendar.getInstance();
        fromDateCal.add(Calendar.MONTH, -6);
        Calendar toDateCal = Calendar.getInstance();
        toDateCal.add(Calendar.MONTH, 6);
        List<Holiday> holidays = holidayTransformerBean.retrieveHolidayByTypeAndBetweenDates("Restricted", fromDateCal.getTime(), toDateCal.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (holidays != null && !holidays.isEmpty()) {
            Collections.sort(holidays, new Comparator<Holiday>() {

                @Override
                public int compare(Holiday o1, Holiday o2) {
                    if (o1.getHolidayDate() != null && o2.getHolidayDate() != null) {
                        return o1.getHolidayDate().compareTo(o2.getHolidayDate());
                    } else {
                        return 0;
                    }
                }
            });
            List<SelectItem> selectItems = new ArrayList<SelectItem>();
            for (Holiday holiday : holidays) {
                SelectItem selectItem = new SelectItem();
                String holidayName = holiday.getHolidayName();
                holidayName += " - " + sdf.format(holiday.getHolidayDate());
                selectItem.setLabel(holidayName);
                selectItem.setValue(holiday.getHolidayId());
                selectItems.add(selectItem);
            }
            systemResultViewUtil.setHolidayList(selectItems);
        }

    }

    public void retrieveLeaveSubjectList() {
        systemResultViewUtil.setSystemConfigurationDataBeanList(systemConfigurationTransformerBean.retrieveType("LeaveSubject"));
    }

    public void retrievePersonalLeaveNotificationList() {
        try {
            System.out.println("**************** retrieve Personal Leave List ********************");
            systemResultViewUtil.setLeaveDataBeans(leaveTransformerBean.retrievePersonalLeaveNotificationList());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void fillLeaveDetail(String btnClick) {
        try {
            System.out.println("******************fill Leave Detail ****************");
            LeaveDataBean leaveDetail = leaveTransformerBean.retrieveLeaveDetail(leaveId, btnClick);
            systemResultViewUtil.setLeaveOpinionDataBeans(leaveTransformerBean.retrieveLeaveOpinionByLeaveId(leaveId));
            fillLeaveDataBean(leaveDetail);
            leaveDataBean.setUserName(userName);
            if ("edit".equals(btnClick)) {
                userServiceBean.retrieveUserListWithoutItself();
                leaveDataBean.setUserIdList(leaveTransformerBean.retrieveUserSelectedLeaveOpinion(leaveId));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void approveLeave() {
        try {
            String response = leaveTransformerBean.approveLeave(leaveDataBean.getLeaveId(), leaveDataBean.getAdminComment());
            messageDataBean.setMessage(response);
            messageDataBean.setIsSuccess(true);
//            this.retrieveleaveNotificationList();
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }

    public void disApproveLeave() {
        try {
            String response = leaveTransformerBean.disApproveLeave(leaveDataBean.getLeaveId(), leaveDataBean.getAdminComment());
            messageDataBean.setMessage(response);
            messageDataBean.setIsSuccess(true);
//            this.retrievePersonalLeaveNotificationList();
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }

    public void archiveLeave() {
        try {
            leaveTransformerBean.archiveLeave(leaveDataBean.getLeaveId());
            messageDataBean.setMessage("Leave archived successfully.");
            messageDataBean.setIsSuccess(true);
//            this.retrievePersonalLeaveNotificationList();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void cancelLeaveArchive() {
        try {
            String response = leaveTransformerBean.cancelLeaveArchive(leaveDataBean.getLeaveId());
            messageDataBean.setMessage("Leave archived successfully.");
            messageDataBean.setIsSuccess(true);
//            this.retrieveleaveList();
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }

    public void fillLeaveQuotaDataBean() throws UserManagementException {
        LeaveQuotaDataBean lqdb = leaveTransformerBean.retrieveLeaveQuotaDetailByUserId(leaveDataBean.getUserId());
        leaveQuotaDataBean.setAvailableCasualLeave(lqdb.getAvailableCasualLeave());
        leaveQuotaDataBean.setAvailableEarnLeave(lqdb.getAvailableEarnLeave());
        leaveQuotaDataBean.setAvailableRestrictedHoliday(lqdb.getAvailableRestrictedHoliday());
        leaveQuotaDataBean.setLossOfPayLeaves(lqdb.getLossOfPayLeaves());
        leaveQuotaDataBean.setUserId(lqdb.getUserId());
        leaveQuotaDataBean.setUserName(lqdb.getUserName());
    }

    public void retrieveLeaveListByQuotaType() {
        try {
            systemResultViewUtil.setLeaveDataBeans(leaveTransformerBean.retrieveLeaveListByLeaveType(userId, leaveType));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void retrieveLeaveOpinionList() {
        leaveDataBean.setUserIdList(leaveTransformerBean.retrieveLeaveOpinionList());
    }

    public void submitLeaderUsersList() {
        leaveTransformerBean.submitLeaderUsersList(systemResultViewUtil.getEmpId(), leaveDataBean.getUserIdList());
        messageDataBean.setMessage("Leave opinion list submitted successfully.");
        messageDataBean.setIsSuccess(Boolean.TRUE);
    }

    public void submitLeaveOpinionList() {
        try {
            leaveTransformerBean.submitLeaveOpinionList(leaveDataBean.getUserIdList());
            messageDataBean.setMessage("Leave opinion list submitted successfully.");
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

//    public void retrieveOpinionList(){
//        try {
//            systemResultViewUtil.setSystemUserDetailDataBeansList(leaveTransformerBean.retrieveLeaveOpinionList(systemResultViewUtil.getEmpId()));
//            messageDataBean.setMessage("Leave opinion list submitted successfully.");
//            messageDataBean.setIsSuccess(Boolean.TRUE);
//        } catch (Exception e) {
//            System.out.println(e);
//            messageDataBean.setMessage(e.toString());
//            messageDataBean.setIsSuccess(Boolean.FALSE);
//        }
//    }
    public void retrieveOpinionList() {
        leaveDataBean.setUserIdList(leaveTransformerBean.retrieveUserUnderUserList(systemResultViewUtil.getEmpId()));

    }

    public void retrieveAttendanceManagerList() {
        try {
            leaveDataBean.setUserIdList(leaveTransformerBean.retrieveAttendanceManagerList());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void submitAttendanceManagerList() {
        try {
            leaveTransformerBean.submitAttendanceManagerList(leaveDataBean.getUserIdList());
            messageDataBean.setMessage("Attendance manager list submitted successfully.");
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void retrieveLeaveQuotaBeanList() throws UserManagementException {
        if (systemResultViewUtil.getLeaveQuotaDataBeansList() == null) {
            systemResultViewUtil.setLeaveQuotaDataBeansList(leaveTransformerBean.retrieveLeaveQuotaBeanList());
        }
    }

    public void retrievePersonalLeaveOpinionNotificationDetail() {
        try {
            systemResultViewUtil.setLeaveDetailOfLeaveOpininList(leaveTransformerBean.retrievePersonalLeaveOpinionNotificationDetail());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillLeaveDetailWithLeaveOpinion() {
        try {
            fillLeaveDataBean(leaveTransformerBean.retrieveLeaveDetail(leaveId, "opinion"));
            leaveOpinionDataBean.setLeaveOpinionDataBean(leaveTransformerBean.retrieveLeaveOpinionByLeaveIdAndUserId(leaveId));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void archiveLeaveOpinionResponseNotification() {
        try {
            System.out.println("******************archiveLeaveOpinionResponseNotification*********************");
            leaveTransformerBean.archiveLeaveOpinionResponseNotification(leaveOpinionId);
            messageDataBean.setMessage("Leave opinion response archived successfully.");
            messageDataBean.setIsSuccess(true);
        } catch (Exception e) {
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
            System.out.println(e);
        }
    }

    public void submitLeaveOpinion() {
        try {
            if (!"".equals(leaveOpinionDataBean.getOpinion().trim())) {
                leaveTransformerBean.submitLeaveOpinion(leaveOpinionId, leaveOpinionDataBean.getOpinion());
                messageDataBean.setMessage("Leave opinion submitted successfully.");
                messageDataBean.setIsSuccess(true);
            } else {
                messageDataBean.setMessage("Please enter the opinion");
                messageDataBean.setIsSuccess(false);
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
        }
    }

    public void archiveLeaveOpinion() {
        try {
            leaveTransformerBean.archiveLeaveOpinion(leaveOpinionId);
            messageDataBean.setMessage("Leave opinion archived successfully.");
            messageDataBean.setIsSuccess(true);
        } catch (Exception e) {
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
            System.out.println(e);
        }
    }

    private void fillLeaveDataBean(LeaveDataBean leaveDetail) {
        leaveDataBean.setAdminComment(leaveDetail.getAdminComment());
        leaveDataBean.setAppliedDate(leaveDetail.getAppliedDate());
        leaveDataBean.setAppliedStatus(leaveDetail.getAppliedStatus());
        leaveDataBean.setApprovalStatus(leaveDetail.getApprovalStatus());
        leaveDataBean.setFromDate(leaveDetail.getFromDate());
        leaveDataBean.setFromDateLeaveType(leaveDetail.getFromDateLeaveType());
        leaveDataBean.setLeaveId(leaveDetail.getLeaveId());
        leaveDataBean.setLeaveSubject(leaveDetail.getLeaveSubject());
        leaveDataBean.setLeaveType(leaveDetail.getLeaveType());
        leaveDataBean.setReason(leaveDetail.getReason());
        leaveDataBean.setResponseBy(leaveDetail.getResponseBy());
        leaveDataBean.setResponseDate(leaveDetail.getResponseDate());
        leaveDataBean.setToDate(leaveDetail.getToDate());
        leaveDataBean.setToDateLeaveType(leaveDetail.getToDateLeaveType());
        leaveDataBean.setUserId(leaveDetail.getUserId());
        leaveDataBean.setHolidayId(leaveDetail.getHolidayId());
        leaveDataBean.setHolidayName(leaveDetail.getHolidayName());
        leaveDataBean.setNoOfDays(leaveDetail.getNoOfDays());
        leaveDataBean.setAvailableCasualLeave(leaveDetail.getAvailableCasualLeave());
        leaveDataBean.setAvailableEarnLeave(leaveDetail.getAvailableEarnLeave());
        leaveDataBean.setAvailableRestrictedHolidayLeave(leaveDetail.getAvailableRestrictedHolidayLeave());
        leaveDataBean.setLossOfpayLeave(leaveDetail.getLossOfpayLeave());
        leaveDataBean.setResposeByUserName(leaveDetail.getResposeByUserName());
        leaveDataBean.setPreviosAdminComment(leaveDetail.getPreviosAdminComment());
    }

    private Boolean isLeaveInformationCorrect() {
        Boolean flag = true;
        if (leaveDataBean.getLeaveType().equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
            if (leaveDataBean.getHolidayId() == null) {
                leaveDataBean.setRistricedHolidayValidationMessage("Please select the restricted holiday");
                flag = false;
            }
            if (leaveDataBean.getReason() == null || "".equals(leaveDataBean.getReason().trim())) {
                leaveDataBean.setReasonValidationMessage("Please enter the reason");
                flag = false;
            }
        } else {
            System.out.println("In other type");
            if (leaveDataBean.getFromDate() == null) {
                leaveDataBean.setFromDateValidationMessage("Please enter the from date");
                flag = false;
            }
            if (leaveDataBean.getLeaveSubject() == null) {
                leaveDataBean.setLeaveSubjectValidationMessage("Please select a subject");
                flag = false;
            }
            if (leaveDataBean.getFromDateLeaveType() == null) {
                leaveDataBean.setFromDateTypeValidationMessage("Please select the leave type for from-date");
                flag = false;
            }
            if (leaveDataBean.getToDate() == null) {
                leaveDataBean.setToDateValidationMessage("Please enter the todate");
                flag = false;
            }
            if (leaveDataBean.getToDateLeaveType() == null) {
                leaveDataBean.setToDateTypeValidationMessage("Please select the leave type for to-date");
                flag = false;
            }
            if (leaveDataBean.getReason() == null || "".equals(leaveDataBean.getReason().trim())) {
                leaveDataBean.setReasonValidationMessage("Please enter the reason");
                flag = false;
            }
            if (flag) {
                leaveDataBean.setFromDate(setDate(leaveDataBean.getFromDate()));
                leaveDataBean.setToDate(setDate(leaveDataBean.getToDate()));
                System.out.println(leaveDataBean.getFromDate().compareTo(leaveDataBean.getToDate()));
                if (leaveDataBean.getFromDate().compareTo(leaveDataBean.getToDate()) == 0) {
                    if (!leaveDataBean.getToDateLeaveType().equals(leaveDataBean.getFromDateLeaveType())) {
                        flag = false;
                        messageDataBean.setMessage("Type for from-date and to-date type must match.");
                    }
                } else {
                    String msg = "";
                    if ("HDM".equals(leaveDataBean.getFromDateLeaveType())) {
                        flag = false;
                        msg += "Your from-date leave type cannot be Half-day Morning";
                    }
                    if ("HDE".equals(leaveDataBean.getToDateLeaveType())) {
                        if (!"".equals(msg)) {
                            msg += " , neither your to-date leave type can be Half-day Evening";
                        } else {
                            msg += "Your to-date leave type cannot be Half-day Evening";
                        }
                        flag = false;
                    }
                    messageDataBean.setMessage(msg);
                }
            }
        }
        return flag;
    }

    public void retrieveLeaveBetweenDates() {
        try {
            systemResultViewUtil.setLeaveDataBeans(leaveTransformerBean.retrieveLeaveBetweenDates(leaveDataBean.getUserId(), leaveDataBean.getFromDate(), leaveDataBean.getToDate()));
            if (systemResultViewUtil.getLeaveDataBeans() == null) {
                messageDataBean.setMessage("No record found..");
            } else {
                messageDataBean.setMessage(null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveApproveLeaveBetweenDates() {
        try {
            systemResultViewUtil.setLeaveDataBeans(leaveTransformerBean.retrieveApproveLeaveBetweenDates(getUserId(), leaveFromDate, leaveToDate));
            if (systemResultViewUtil.getLeaveDataBeans() == null) {
                messageDataBean.setMessage("No record found..");
            } else {
                messageDataBean.setMessage(null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveLeaveHistoryDetail() {
        try {
            Calendar fromDateCal, toDateCal;
            fromDateCal = Calendar.getInstance();
            toDateCal = Calendar.getInstance();
            fromDateCal.set(Calendar.DATE, 01);
            fromDateCal.set(Calendar.MONTH, 0);
            toDateCal.set(Calendar.DATE, 31);
            toDateCal.set(Calendar.MONTH, 11);
            Date fromDate, toDate;
            if (year == null) {
                fromDateCal.set(Calendar.YEAR, fromDateCal.get(Calendar.YEAR) - 1);
                toDateCal.set(Calendar.YEAR, toDateCal.get(Calendar.YEAR) + 1);
            } else {
                fromDateCal.set(Calendar.YEAR, year);
                toDateCal.set(Calendar.YEAR, year);
            }
            fromDate = fromDateCal.getTime();
            toDate = toDateCal.getTime();
            systemResultViewUtil.setLeaveHistoryList(leaveTransformerBean.retrieveLeaveHistory(leaveId, userId, fromDate, toDate));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private Date setDate(Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        fromDate = cal.getTime();
        return fromDate;
    }

    public void responseLeave() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (facesContext.getExternalContext().getRequestParameterMap().get("leaveId") != null) {
                leaveId = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("leaveId"));
                String responseType = (String) facesContext.getExternalContext().getRequestParameterMap().get("type");
                String adminComment = (String) facesContext.getExternalContext().getRequestParameterMap().get("comment");
                if (responseType.equals(SystemConstantUtil.APPROVE)) {
                    String response = leaveTransformerBean.approveLeave(leaveId, adminComment);
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(true);
                    this.fillLeaveDetail("withResponseBy");
                } else if (responseType.equals(SystemConstantUtil.DISAPPROVE)) {
                    String response = leaveTransformerBean.disApproveLeave(leaveId, adminComment);
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(true);
                    this.fillLeaveDetail("withResponseBy");
                }
            } else {
//                FacesContext.getCurrentInstance().getExternalContext().dispatch(SystemConstantUtil.ROLE_MEMBER_PAGE);
                FacesContext.getCurrentInstance().getExternalContext().redirect(SystemConstantUtil.ROLE_SUPER_ADMIN_PAGE + ".jsf");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
        }
    }

    public void responseLeaveOpinion() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (facesContext.getExternalContext().getRequestParameterMap().get("leaveId") != null) {
                leaveId = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("leaveId"));
                leaveOpinionId = leaveTransformerBean.retrieveOpinionIdByleaveIdAndUserId(leaveId);
                if (leaveOpinionId != null) {
                    String comment = (String) facesContext.getExternalContext().getRequestParameterMap().get("comment");
                    String response = leaveTransformerBean.submitLeaveOpinion(leaveOpinionId, comment);
                    this.fillLeaveDetailWithLeaveOpinion();
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(true);
                    this.fillLeaveDetail("withResponseBy");
                    leaveDataBean.setUserName(userServiceBean.retrieveUserNameByUserId(leaveDataBean.getLeaveId()));
                } else {
                    messageDataBean.setMessage("don't have permission for give opinion");
                    messageDataBean.setIsSuccess(true);
                }
            } else {
//                FacesContext.getCurrentInstance().getExternalContext().dispatch(SystemConstantUtil.ROLE_MEMBER_PAGE);
                if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(SystemConstantUtil.ROLE_SUPER_ADMIN_PAGE + ".jsf");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(SystemConstantUtil.ROLE_ADMIN_PAGE + ".jsf");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
        }
    }
}
