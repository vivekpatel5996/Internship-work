/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.transformerbean;

import com.argusoft.ars.core.EmailFormatService;
import com.argusoft.ars.core.HolidayService;
import com.argusoft.ars.core.LeaveOpinionService;
import com.argusoft.ars.core.LeaveService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.*;
import com.argusoft.ars.web.leavemanagement.databean.LeaveDataBean;
import com.argusoft.ars.web.leavemanagement.databean.LeaveOpinionDataBean;
import com.argusoft.ars.web.leavemanagement.databean.LeaveQuotaDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.UserTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.usermanagement.common.core.SystemConfigurationService;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.SystemConfiguration;
import com.argusoft.usermanagement.common.model.User;
import com.argusoft.usermanagement.common.model.UserContact;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "leaveTransformerBean")
@RequestScoped
public class LeaveTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    @ManagedProperty(value = "#{holidayService}")
    private HolidayService holidayService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{leaveOpinionService}")
    private LeaveOpinionService leaveOpinionService;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    @ManagedProperty(value = "#{emailFormatService}")
    private EmailFormatService emailFormatService;
    @ManagedProperty(value = "#{systemConfigurationService}")
    private SystemConfigurationService systemConfigurationService;
    private Boolean isCasaulLeaveTaken;
    private Boolean isEarnLeaveTaken;
    //  Other properties
    private static final Logger log = Logger.getLogger(LeaveTransformerBean.class);

    public Boolean getIsCasaulLeaveTaken() {
        return isCasaulLeaveTaken;
    }

    public void setIsCasaulLeaveTaken(Boolean isCasaulLeaveTaken) {
        this.isCasaulLeaveTaken = isCasaulLeaveTaken;
    }

    public Boolean getIsEarnLeaveTaken() {
        return isEarnLeaveTaken;
    }

    public void setIsEarnLeaveTaken(Boolean isEarnLeaveTaken) {
        this.isEarnLeaveTaken = isEarnLeaveTaken;
    }

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public EmailFormatService getEmailFormatService() {
        return emailFormatService;
    }

    public void setEmailFormatService(EmailFormatService emailFormatService) {
        this.emailFormatService = emailFormatService;
    }

    public UserTransformerBean getUserTransformerBean() {
        return userTransformerBean;
    }

    public void setUserTransformerBean(UserTransformerBean userTransformerBean) {
        this.userTransformerBean = userTransformerBean;
    }

    public SystemConfigurationService getSystemConfigurationService() {
        return systemConfigurationService;
    }

    public void setSystemConfigurationService(SystemConfigurationService systemConfigurationService) {
        this.systemConfigurationService = systemConfigurationService;
    }

    public LeaveOpinionService getLeaveOpinionService() {
        return leaveOpinionService;
    }

    public void setLeaveOpinionService(LeaveOpinionService leaveOpinionService) {
        this.leaveOpinionService = leaveOpinionService;
    }

    public SystemUserDetailService getSystemUserDetail() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public LeaveService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to convert LeaveDataBean to Leave Model.
     *
     */
    private Leave convertLeaveDataBeanToLeaveModel(LeaveDataBean leaveDataBean, Leave leave) {
        leave.setAppliedDate(new Date());
        if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
            leave.setApprovalStatus(SystemConstantUtil.APPROVE);
            leave.setIsNotificationShow(false);
            leave.setResponseBy(loginDataBean.getId());
            leave.setResponseDate(new Date());
            leave.setIsArchive(false);
        } else {
            leave.setApprovalStatus(SystemConstantUtil.PENDING);
            leave.setIsNotificationShow(true);
            leave.setIsArchive(true);
        }
        leave.setFromDate(leaveDataBean.getFromDate());
        leave.setFromDateLeaveType(leaveDataBean.getFromDateLeaveType());
        leave.setLeaveSubject(leaveDataBean.getLeaveSubject());
        leave.setLeaveType(leaveDataBean.getLeaveType());
        leave.setReason(leaveDataBean.getReason());
        leave.setToDate(leaveDataBean.getToDate());
        leave.setToDateLeaveType(leaveDataBean.getToDateLeaveType());
        leave.setUserId(leaveDataBean.getUserId());
        leave.setHolidayId(leaveDataBean.getHolidayId());
        return leave;
    }

    /**
     * Method to convert Leave Model to LeaveDataBean.
     */
    private LeaveDataBean convertLeaveModelToLeaveDataBean(Leave leave, LeaveDataBean leaveDataBean) {
        if (leave.getLeaveType().equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
            leaveDataBean.setHolidayId(leave.getHolidayId());
            leaveDataBean.setLeaveSubject("Holiday-" + leave.getLeaveSubject());
        } else {
            leaveDataBean.setLeaveSubject(leave.getLeaveSubject());
        }
        leaveDataBean.setToDate(leave.getToDate());
        leaveDataBean.setToDateLeaveType(leave.getToDateLeaveType());
        leaveDataBean.setAdminComment(leave.getAdminComment());
        leaveDataBean.setAppliedDate(leave.getAppliedDate());
        leaveDataBean.setAppliedStatus(leave.getAppliedStatus());
        leaveDataBean.setApprovalStatus(leave.getApprovalStatus());
        leaveDataBean.setFromDate(leave.getFromDate());
        leaveDataBean.setFromDateLeaveType(leave.getFromDateLeaveType());
        leaveDataBean.setLeaveId(leave.getLeaveId());
        leaveDataBean.setLeaveType(leave.getLeaveType());
        leaveDataBean.setReason(leave.getReason());
        leaveDataBean.setResponseBy(leave.getResponseBy());
        leaveDataBean.setResponseDate(leave.getResponseDate());
        leaveDataBean.setUserId(leave.getUserId());
        return leaveDataBean;
    }

    private LeaveOpinionDataBean convertLeaveOpinionModelToLeaveOpinionDataBean(LeaveOpinion leaveOpinion, LeaveOpinionDataBean leaveOpinionDataBean) throws UserManagementException {
        leaveOpinionDataBean.setLevaeOpinionId(leaveOpinion.getLeaveOpinionId());
        leaveOpinionDataBean.setOpinion(leaveOpinion.getOpinion());
        leaveOpinionDataBean.setUserId(leaveOpinion.getUserId());
        User user = userService.getUserbyId(leaveOpinion.getUserId(), false, false, true, false);
        leaveOpinionDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
        return leaveOpinionDataBean;
    }

    /**
     * Method to Retrive all active Leave
     */
    public List<LeaveDataBean> retrieveAllLeave() {
        try {
            System.out.println("User Id" + loginDataBean.getId());
            List<Leave> leaveList = leaveService.retrieveLeaveByUserId(loginDataBean.getId());
            List<LeaveDataBean> leaveDataBeansList = new ArrayList<LeaveDataBean>();
            if (!leaveList.isEmpty()) {
                for (Leave leave : leaveList) {
                    Float leaveDays = retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
                    LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                    leaveDataBean.setNoOfDays(leaveDays);
                    leaveDataBeansList.add(leaveDataBean);
                }
                return leaveDataBeansList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive Leave Detail by Leave Id
     */
    public LeaveDataBean retrieveLeaveDetail(Long leaveId, String btnClick) throws UserManagementException {
        System.out.println("*********** Retrive Leave Detail *************");
        Leave leave = leaveService.retrieveLeaveById(leaveId);
        if (leave != null) {
            Float leaveDays = retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
            LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
            leaveDataBean.setNoOfDays(leaveDays);
            if ("edit".equals(btnClick)) {
                if (leaveDataBean.getLeaveType().equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
                    leaveDataBean.setFromDate(null);
                    leaveDataBean.setToDate(null);
                    leaveDataBean.setFromDateLeaveType(null);
                    leaveDataBean.setToDateLeaveType(null);
                }
            } else {
                if (leaveDataBean.getLeaveType().equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
                    leaveDataBean.setHolidayName(leave.getLeaveSubject());
                }
                if (leaveDataBean.getResponseBy() != null) {
                    User user = userService.getUserbyId(leave.getResponseBy(), false, false, true, false);
                    leaveDataBean.setResposeByUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                }
            }
            if ("notificationLink".equals(btnClick)) {
                leaveDataBean.setAvailableCasualLeave(this.getAvailableCasualLeave(leaveDataBean.getUserId(), false));
                leaveDataBean.setAvailableEarnLeave(this.getAvailableEarnLeave(leaveDataBean.getUserId()));
                leaveDataBean.setAvailableRestrictedHolidayLeave(this.getAvailableRestrictedDay(leaveDataBean.getUserId(), false));
                leaveDataBean.setLossOfpayLeave(this.getNoOfLossOfPay(leaveDataBean.getUserId()));
                leaveDataBean.setPreviosAdminComment(leaveDataBean.getAdminComment());
                leaveDataBean.setAdminComment(null);
            }
            return leaveDataBean;
        }
        return null;
    }

    /**
     * Method to Add Leave object.
     *
     */
    public String applyLeave(LeaveDataBean leaveDataBean) {
        try {
            leaveDataBean = this.setLeaveDataBean(leaveDataBean);
            if (SystemConstantUtil.RISTRICTED_HOLIDAY.equals(leaveDataBean.getLeaveType())) {
                Holiday holiday = holidayService.retrieveHolidayById(leaveDataBean.getHolidayId());
                if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    Integer availableRestrictedHoliday = this.getAvailableRestrictedDay(leaveDataBean.getUserId(), true).intValue();
                    if (availableRestrictedHoliday <= 0) {
                        return "Your restricted leave quota is over";
                    }
                }
                leaveDataBean.setFromDate(holiday.getHolidayDate());
                leaveDataBean.setFromDateLeaveType("FD");
                leaveDataBean.setToDate(holiday.getHolidayDate());
                leaveDataBean.setToDateLeaveType("FD");
                leaveDataBean.setLeaveSubject(holiday.getHolidayName());
            }
            if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                if (SystemConstantUtil.CASUL_LEAVE.equals(leaveDataBean.getLeaveType())) {
                    Float avilableCasulLeave = getAvailableCasualLeave(leaveDataBean.getUserId(), true);
                    Float noOfdaysLeaves = this.retrieveLeaveDays(leaveDataBean.getFromDate(), leaveDataBean.getFromDateLeaveType(), leaveDataBean.getToDate(), leaveDataBean.getToDateLeaveType());
                    if (noOfdaysLeaves > avilableCasulLeave) {
                        return "Your 'Available Casual Leave' quota is inadequate.Try applying Earned Leave.";
                    }
                }
            }
            Float leaveDays = this.retrieveLeaveDays(leaveDataBean.getFromDate(), leaveDataBean.getFromDateLeaveType(), leaveDataBean.getToDate(), leaveDataBean.getToDateLeaveType());
            if (leaveDays == 0F) {
                return "You are trying to apply leave for weekend.";
            }
            boolean isLeaveAlreadyApply = leaveService.isLeaveAlreadyApply(leaveDataBean.getUserId(), leaveDataBean.getFromDate(), leaveDataBean.getToDate());
            if (!isLeaveAlreadyApply) {
                Leave leave = convertLeaveDataBeanToLeaveModel(leaveDataBean, new Leave());
                leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
                Long leaveId = this.leaveService.createLeave(leave);
                if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    Long[] leaveOpinionList = systemUserDetailService.retrieveLeaveOpinionUserListByUserId(leaveDataBean.getUserId());
//                    List<User> adminUserList=userService.getUsersByType(null, isLeaveAlreadyApply, isLeaveAlreadyApply, isLeaveAlreadyApply, isLeaveAlreadyApply, isLeaveAlreadyApply)
                    List<Long> listOfUserIdForSendEmail = leaveOpinionService.sendLeaveOpinionNotification(leaveDataBean.getUserId(), leaveId, leaveOpinionList);
                    if (listOfUserIdForSendEmail != null && listOfUserIdForSendEmail.size() > 0) {
                        System.out.println("leave opinion size:" + listOfUserIdForSendEmail.size());
                        this.sendMail(listOfUserIdForSendEmail, leave, null, SystemConstantUtil.EMAIL_FORMAT_LEAVE_OPINION);
                    }
                    this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_APPLY_LEAVE);
                }
                return SystemConstantUtil.SUCCESS;
            } else {
                return "You are already on leave within this duration.";
            }
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }

    /**
     * Method to Inactivate Leave.
     */
    public String cancelLeave(LeaveDataBean leaveDataBean) {
        try {
            Leave leave = leaveService.retrieveLeaveById(leaveDataBean.getLeaveId());
            if (leave != null) {
                if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    leave.setApprovalStatus(null);
                    leave.setAppliedStatus(SystemConstantUtil.APPLY_CANCEL);
                } else if (leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                    leave.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL);
                    leave.setApprovalStatus(SystemConstantUtil.PENDING);
                    leave.setIsNotificationShow(true);
                } else if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_MODIFIED)) {
                    leave.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL);
                    leave.setIsNotificationShow(true);
                    leave.setApprovalStatus(SystemConstantUtil.PENDING);
                } else {
                    leave.setAppliedStatus(SystemConstantUtil.APPLY_CANCEL);
                    leave.setApprovalStatus(null);
                    leave.setIsNotificationShow(true);
                }
                leaveService.updateLeave(leave);
                if (leave.getApprovalStatus() == null) {
                    List<LeaveOpinion> intrestedUserList = leaveOpinionService.retrtiveIntrestedUserLeaveOpinionListByLeaveId(leaveDataBean.getLeaveId());
                    if (intrestedUserList != null) {
                        List<Long> userIdList = new ArrayList<Long>();
                        for (LeaveOpinion leaveOpinion : intrestedUserList) {
                            leaveOpinion.setIsArchive(true);
                            leaveOpinion.setIsNotificationShow(true);
                            leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                            userIdList.add(leaveOpinion.getUserId());
                        }
                        if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                            this.sendMail(userIdList, null, leave, SystemConstantUtil.EMAIL_FORMAT_CANCEL_LEAVE);
                        }
                    }
                    List<LeaveOpinion> notIntrestedUserList = leaveOpinionService.retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(leaveDataBean.getLeaveId());
                    if (notIntrestedUserList != null) {
                        for (LeaveOpinion leaveOpinion : notIntrestedUserList) {
                            leaveOpinion.setIsArchive(true);
                            leaveOpinion.setIsNotificationShow(false);
                            leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                        }
                    }
                    if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                        this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_CANCEL_LEAVE);
                    }
                } else {
                    if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                        this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_LEAVE_CANCELLATION);
                    }
                }
                return SystemConstantUtil.SUCCESS;
            }
        } catch (Exception ex) {
            log.error(ex);
            System.out.println(ex);
            return ex.toString();
        }
        return SystemConstantUtil.SUCCESS;
    }

    /**
     * Method to Update Leave Detail
     */
    public String updateLeave(LeaveDataBean leaveDataBean) {
        try {
            leaveDataBean = this.setLeaveDataBean(leaveDataBean);
            Leave leave = leaveService.retrieveLeaveById(leaveDataBean.getLeaveId());
            Leave oldLeave = leaveService.retrieveLeaveById(leaveDataBean.getLeaveId());
            if ((SystemConstantUtil.RISTRICTED_HOLIDAY).equals(leaveDataBean.getLeaveType())) {
                Holiday holiday = holidayService.retrieveHolidayById(leaveDataBean.getHolidayId());
                leaveDataBean.setFromDate(holiday.getHolidayDate());
                leaveDataBean.setFromDateLeaveType("FD");
                leaveDataBean.setToDate(holiday.getHolidayDate());
                leaveDataBean.setToDateLeaveType("FD");
                leaveDataBean.setLeaveSubject(holiday.getHolidayName());
                if (!leave.getLeaveType().equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
                    if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                        Integer avilableRestrictedHoliday = this.getAvailableRestrictedDay(loginDataBean.getId(), true).intValue();
                        if (avilableRestrictedHoliday <= 0) {
                            return "Ristriction Leave Quota is over";
                        }
                    }
                }
            }
            if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                if (SystemConstantUtil.CASUL_LEAVE.equals(leaveDataBean.getLeaveType())) {
                    Float avilableCasulLeave = getAvailableCasualLeave(loginDataBean.getId(), true);
                    avilableCasulLeave += retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
                    Float noOfdaysLeaves = this.retrieveLeaveDays(leaveDataBean.getFromDate(), leaveDataBean.getFromDateLeaveType(), leaveDataBean.getToDate(), leaveDataBean.getToDateLeaveType());
                    if (noOfdaysLeaves > avilableCasulLeave) {
                        return "Your 'Available Casual Leave' quota is inadequate.Try applying Earned Leave.";
                    }
                }
            }
            Float leaveDays = retrieveLeaveDays(leaveDataBean.getFromDate(), leaveDataBean.getFromDateLeaveType(), leaveDataBean.getToDate(), leaveDataBean.getToDateLeaveType());
            if (leaveDays == 0F) {
                return "You are trying to apply leave for weekend.";
            }
            if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
            } else {
                if (leave.getApprovalStatus() != null) {
                    if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_NEW) && leave.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
                        leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW_MODIFIED);
                    } else if (leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                        leave.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_MODIFIED);
                    } else if (leave.getApprovalStatus().equals(SystemConstantUtil.DISAPPROVE)) {
                        leave.setAppliedStatus(SystemConstantUtil.APPLY_DISAPPROVE_MODIFIED);
                    } else if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                        leave.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_MODIFIED);
                    } else if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_CANCEL)) {
                        leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW_MODIFIED);
                    } else {
                        leave.setAppliedStatus(leave.getAppliedStatus());
                    }
                } else {
                    leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW_MODIFIED);
                }
            }
            List<LeaveDataBean> leaveListBeetweenDate = this.retrieveLeaveBetweenDates(loginDataBean.getId(), leaveDataBean.getFromDate(), leaveDataBean.getToDate());
            boolean isLeaveAlreadyApplied = true;
            if (leaveListBeetweenDate == null) {
                isLeaveAlreadyApplied = false;
            } else if (leaveListBeetweenDate.size() == 1) {
                if (leaveListBeetweenDate.get(0).getLeaveId().equals(leave.getLeaveId())) {
                    isLeaveAlreadyApplied = false;
                }
            }
            if (!isLeaveAlreadyApplied) {
                leave = convertLeaveDataBeanToLeaveModel(leaveDataBean, leave);
                leaveService.updateLeave(leave);
                if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    leaveOpinionService.sendLeaveOpinionNotificationForModifyLeave(leaveDataBean.getLeaveId(), leaveDataBean.getUserIdList());
                }
                List<LeaveOpinion> intrestedUserList = leaveOpinionService.retrtiveIntrestedUserLeaveOpinionListByLeaveId(leaveDataBean.getLeaveId());
                if (intrestedUserList != null) {
                    List<Long> userIdList = new ArrayList<Long>();
                    for (LeaveOpinion leaveOpinion : intrestedUserList) {
                        leaveOpinion.setIsArchive(false);
                        leaveOpinion.setIsNotificationShow(true);
                        leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                        userIdList.add(leaveOpinion.getUserId());
                    }
                    this.sendMail(userIdList, leave, oldLeave, SystemConstantUtil.EMAIL_FORMAT_LEAVE_OPINION);
                }
                if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    this.sendMailToAdmin(leave, oldLeave, SystemConstantUtil.EMAIL_FORMAT_UPDATE_LEAVE);
                }
                return SystemConstantUtil.SUCCESS;
            } else {
                return "You are already on leave within this duration.";
            }
        } catch (Exception ex) {
            log.error(ex);
            ex.printStackTrace();
            return ex.toString();
        }
    }

    public Integer monthsBetween(Date date) {
        Calendar calDate = new GregorianCalendar();
        calDate.setTime(date);
        Calendar curruntDate = new GregorianCalendar();
        curruntDate.setTime(new Date());
        double monthsBetween = 0;
        //difference in month for years
        monthsBetween = (curruntDate.get(Calendar.YEAR) - calDate.get(Calendar.YEAR)) * 12;
        //difference in month for months
        monthsBetween += curruntDate.get(Calendar.MONTH) - calDate.get(Calendar.MONTH);
        //difference in month for days
        if (curruntDate.get(Calendar.DAY_OF_MONTH) != curruntDate.getActualMaximum(Calendar.DAY_OF_MONTH)
                && curruntDate.get(Calendar.DAY_OF_MONTH) != curruntDate.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            monthsBetween += ((curruntDate.get(Calendar.DAY_OF_MONTH) - calDate.get(Calendar.DAY_OF_MONTH)) / 31d);
        }
        return (int) monthsBetween;
    }

    public Float getAvailableCasualLeave(Long userId, Boolean isWithPandingLeave) {
        System.out.println("************* get Available Casual Leave ****************");
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userId);
        SystemConfiguration casualLeaveValue = null;
        try {
            casualLeaveValue = systemConfigurationService.retrieveSystemConfigurationByKey("LeavePolicyCasualLeave");
        } catch (Exception e) {
            System.out.println("Exception in retrive number of casual leave from database" + e);
        }
        float casualLeave = 10f;
        if (casualLeaveValue != null) {
            casualLeave = Float.valueOf(casualLeaveValue.getKeyValue());
        }
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        Integer monthsBetween;
        Calendar calToDate = Calendar.getInstance();
        calToDate.set(Calendar.DATE, 31);
        calToDate.set(Calendar.MONTH, 11);

        Float noOfLeave;
        if (systemUserDetail.getConformationDate() != null && simpleDateformat.format(systemUserDetail.getConformationDate()).equals(simpleDateformat.format(new Date()))) {
            monthsBetween = this.monthsBetween(systemUserDetail.getConformationDate());
            noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, systemUserDetail.getConformationDate(), calToDate.getTime(), SystemConstantUtil.CASUL_LEAVE, isWithPandingLeave);
        } else if (simpleDateformat.format(systemUserDetail.getJoinDate()).equals(simpleDateformat.format(new Date()))) {
            monthsBetween = 0;
            noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, systemUserDetail.getJoinDate(), calToDate.getTime(), SystemConstantUtil.CASUL_LEAVE, isWithPandingLeave);
        } else {
            Calendar calFromDate = Calendar.getInstance();
            calFromDate.set(Calendar.DATE, 01);
            calFromDate.set(Calendar.MONTH, 00);
            monthsBetween = this.monthsBetween(calFromDate.getTime());
            noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, calFromDate.getTime(), calToDate.getTime(), SystemConstantUtil.CASUL_LEAVE, isWithPandingLeave);

        }

        Float AvailablecasualLeave = (monthsBetween * (casualLeave / 12)) - noOfLeave;
        return AvailablecasualLeave;
    }

    public Float getAvailableEarnLeave(Long userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date systemStartDate = null;
        try {
            systemStartDate = sdf.parse("01/01/2010");
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(LeaveTransformerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("************  Get Avalable Earn Leave  ***********");
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userId);
        SystemConfiguration earnedLeaveValue = null;
        try {
            earnedLeaveValue = systemConfigurationService.retrieveSystemConfigurationByKey("LeavePolicyEarnedLeave");
        } catch (Exception e) {
            System.out.println("Exception in retrive number of earned leave from database" + e);
        }
        float earnedLeave = 10f;
        if (earnedLeaveValue != null) {
            earnedLeave = Float.valueOf(earnedLeaveValue.getKeyValue());
        }
        Integer monthsBetween;
        Float noOfLeave;
        Float AvailableEarnLeave = 0f;
        if (systemUserDetail.getConformationDate() != null) {
            if (systemUserDetail.getConformationDate().after(systemStartDate)) {
                monthsBetween = this.monthsBetween(systemUserDetail.getConformationDate());
            } else {
                monthsBetween = this.monthsBetween(systemStartDate);
            }
            noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, systemUserDetail.getConformationDate(), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.EARN_LEAVE, false);
            AvailableEarnLeave = (monthsBetween * (earnedLeave / 12)) - noOfLeave;
        } else {
            noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, systemUserDetail.getJoinDate(), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.EARN_LEAVE, false);
            AvailableEarnLeave = 0f - noOfLeave;
        }
        return AvailableEarnLeave;
    }

    public Float getAvailableRestrictedDay(Long userId, Boolean isWithPandingLeave) {
        System.out.println("***********get Available Restricted Holiday ******************");
        Float noOfLeave;
        SystemConfiguration restrictedHolidayValue = null;
        try {
            restrictedHolidayValue = systemConfigurationService.retrieveSystemConfigurationByKey("LeavePolicyRestrictedHoliday");
        } catch (Exception e) {
            System.out.println("Exception in retrive number of restricted holiday from database" + e);
        }
        int restrictedHoliday = 2;
        if (restrictedHolidayValue != null) {
            restrictedHoliday = Integer.valueOf(restrictedHolidayValue.getKeyValue());
        }

        noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, new Date(new Date().getYear(), 0, 1), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.RISTRICTED_HOLIDAY, isWithPandingLeave);
        Float AvailableRestrictedLeave = restrictedHoliday - noOfLeave;
        return AvailableRestrictedLeave;
    }

    public Float getNoOfLossOfPay(Long userId) {
        System.out.println("**************** get No of Loss Pay ***********************");
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userId);
        Float noOfLeave = leaveService.retrieveNoOfLeavesBetweenDate(userId, systemUserDetail.getJoinDate(), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.LOSS_OF_PAY, false);
        return noOfLeave;
    }

    public Integer daysBetween(Date fromDate, Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
    }

    public List<LeaveDataBean> retrieveLeaveNotificationList() {
        try {
            List<Leave> leaveList = leaveService.retrievePandingLeave();
            List<LeaveDataBean> leaveDataBeansList = new ArrayList<LeaveDataBean>();
            if (!leaveList.isEmpty()) {
                for (Leave leave : leaveList) {
                    LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                    User user = userService.getUserbyId(leave.getUserId(), false, false, true, false);
                    leaveDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    System.out.println(leaveDataBean.getAppliedStatus());
                    leaveDataBeansList.add(leaveDataBean);
                }
                return leaveDataBeansList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private LeaveDataBean setLeaveDataBean(LeaveDataBean leaveDataBean) {
        if ((SystemConstantUtil.RISTRICTED_HOLIDAY).equals(leaveDataBean.getLeaveType())) {
            leaveDataBean.setLeaveSubject(null);
            leaveDataBean.setToDate(null);
            leaveDataBean.setToDateLeaveType(null);
            leaveDataBean.setFromDate(null);
            leaveDataBean.setFromDateLeaveType(null);
        } else {
            leaveDataBean.setHolidayId(null);
        }
        return leaveDataBean;
    }

    public String approveLeave(Long leaveId, String adminComment) throws UserManagementException {
        System.out.println("************ TransformerBean Leave Approve **************");
        Leave leave = leaveService.retrieveLeaveById(leaveId);
        if (leave.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
            if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                leave.setApprovalStatus(null);
                leave.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL_APPROVE);

            } else {
                leave.setApprovalStatus(SystemConstantUtil.APPROVE);
                leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
            }
            leave.setIsArchive(false);
            leave.setResponseBy(loginDataBean.getId());
            leave.setResponseDate(new Date());
            leave.setIsNotificationShow(false);
            leave.setAdminComment(adminComment);
            leaveService.updateLeave(leave);
            List<LeaveOpinion> intrestedUserList = leaveOpinionService.retrtiveIntrestedUserLeaveOpinionListByLeaveId(leave.getLeaveId());
            List<Long> userIdList = new ArrayList<Long>();
            if (intrestedUserList != null) {
                for (LeaveOpinion leaveOpinion : intrestedUserList) {
                    leaveOpinion.setIsArchive(true);
                    leaveOpinion.setIsNotificationShow(true);
                    leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                    userIdList.add(leaveOpinion.getUserId());
                }
            }
            userIdList.add(leave.getUserId());
            if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                this.sendMail(userIdList, leave, null, SystemConstantUtil.EMAIL_FORMAT_CANCELATION_LEAVE_APPROVE);
                this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_CANCELATION_LEAVE_APPROVE);
            } else {
                this.sendMail(userIdList, leave, null, SystemConstantUtil.EMAIL_FORMAT_APPROVE_LEAVE);
                this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_APPROVE_LEAVE);
            }
            List<LeaveOpinion> notIntrestedUserList = leaveOpinionService.retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(leave.getLeaveId());
            if (notIntrestedUserList != null) {
                for (LeaveOpinion leaveOpinion : notIntrestedUserList) {
                    leaveOpinion.setIsArchive(true);
                    leaveOpinion.setIsNotificationShow(false);
                    leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                }
            }
            return "Leave approve successfully";
        } else {
            return "Leave already responded";
        }
    }

    public String disApproveLeave(Long leaveId, String adminComment) throws UserManagementException {
        Leave leave = leaveService.retrieveLeaveById(leaveId);
        if (leave.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
            if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                leave.setApprovalStatus(SystemConstantUtil.APPROVE);
                leave.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL_DISAPPROVE);
            } else {
                leave.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
                leave.setApprovalStatus(SystemConstantUtil.DISAPPROVE);
            }
            leave.setIsArchive(false);
            leave.setResponseBy(loginDataBean.getId());
            leave.setResponseDate(new Date());
            leave.setIsNotificationShow(false);
            leave.setAdminComment(adminComment);
            leaveService.updateLeave(leave);
            List<LeaveOpinion> intrestedUserList = leaveOpinionService.retrtiveIntrestedUserLeaveOpinionListByLeaveId(leave.getLeaveId());
            List<Long> userIdList = new ArrayList<Long>();
            if (intrestedUserList != null) {
                for (LeaveOpinion leaveOpinion : intrestedUserList) {
                    leaveOpinion.setIsArchive(true);
                    leaveOpinion.setIsNotificationShow(true);
                    leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                    userIdList.add(leaveOpinion.getUserId());
                }
            }
            userIdList.add(leave.getUserId());
            if (leave.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                this.sendMail(userIdList, leave, null, SystemConstantUtil.EMAIL_FORMAT_CANCELATION_LEAVE_DISAPPROVE);
                this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_CANCELATION_LEAVE_DISAPPROVE);
            } else {
                this.sendMail(userIdList, leave, null, SystemConstantUtil.EMAIL_FORMAT_DISAPPROVE_LEAVE);
                this.sendMailToAdmin(leave, null, SystemConstantUtil.EMAIL_FORMAT_DISAPPROVE_LEAVE);
            }
            List<LeaveOpinion> notIntrestedUserList = leaveOpinionService.retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(leave.getLeaveId());
            if (notIntrestedUserList != null) {
                for (LeaveOpinion leaveOpinion : notIntrestedUserList) {
                    leaveOpinion.setIsArchive(true);
                    leaveOpinion.setIsNotificationShow(false);
                    leaveOpinionService.updateLeaveOpinion(leaveOpinion);
                }
            }
            return "Leave successfullly disapprove.";
        } else {
            return "Leave already responded";
        }
    }

    public String cancelLeaveArchive(Long leaveId) {
        System.out.println("************ TransformerBean Leave CancelLeaveArchive **************");
        Leave leave = leaveService.retrieveLeaveById(leaveId);
        leave.setResponseBy(loginDataBean.getId());
        leave.setResponseDate(new Date());
        leave.setIsNotificationShow(false);
        leaveService.updateLeave(leave);
        return SystemConstantUtil.SUCCESS;
    }

    public List<LeaveDataBean> retrievePersonalLeaveNotificationList() {
        System.out.println("************ Personal Leave Notificaction List *********************");
        try {
            System.out.println("User Id" + loginDataBean.getId());
            List<Leave> leaveList = leaveService.retrieveNotArchiveLeave(loginDataBean.getId());
            List<LeaveDataBean> leaveDataBeansList = new ArrayList<LeaveDataBean>();
            if (!leaveList.isEmpty()) {
                for (Leave leave : leaveList) {
                    Float leaveDays = retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
                    LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                    leaveDataBean.setNoOfDays(leaveDays);
                    User user = userService.getUserbyId(leave.getResponseBy(), false, false, true, false);
                    leaveDataBean.setResposeByUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    leaveDataBeansList.add(leaveDataBean);
                }
                return leaveDataBeansList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void archiveLeave(Long leaveId) {
        Leave leave = leaveService.retrieveLeaveById(leaveId);
        leave.setIsArchive(true);
        leaveService.updateLeave(leave);
    }

    private Float retrieveLeaveDays(Date fromDate, String fromDateLeaveType, Date toDate, String toDateLeaveType) {
        Float noOfLeaves = 0F;
        Integer noOfDaysBeetween = daysBetween(fromDate, toDate);
        Integer noOfWeekEnds = getWeekEndsDaysBetweenTwoDates(fromDate, toDate);
        if (noOfDaysBeetween == 1) {
            if (noOfWeekEnds > 0) {
                return 0F;
            } else {
                if (!"FD".equals(toDateLeaveType)) {
                    noOfLeaves += 0.5F;
                } else {
                    noOfLeaves += 1F;
                }
            }
        } else {
            noOfLeaves += noOfDaysBeetween;
            noOfLeaves -= noOfWeekEnds;
            if (!"FD".equals(toDateLeaveType)) {
                if (toDate.getDay() == 0 || toDate.getDay() == 6) {
                    noOfLeaves += 0.5F;
                }
                noOfLeaves -= 0.5F;
            }
            if (!"FD".equals(fromDateLeaveType)) {
                if (fromDate.getDay() == 0 || fromDate.getDay() == 6) {
                    noOfLeaves += 0.5F;
                }
                noOfLeaves -= 0.5F;
            }
        }
        return noOfLeaves;
    }

    public static int getWeekEndsDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int weekEndDays = 0;
        if (startCal.getTimeInMillis() >= endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }
        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                ++weekEndDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        return weekEndDays;
    }

    public LeaveQuotaDataBean retrieveLeaveQuotaDetailByUserId(Long userId) throws UserManagementException {
        if (userId == null) {
            userId = loginDataBean.getId();
        }
        LeaveQuotaDataBean leaveQuotaDataBean = new LeaveQuotaDataBean();
        leaveQuotaDataBean.setAvailableCasualLeave(this.getAvailableCasualLeave(userId, false));
        leaveQuotaDataBean.setAvailableEarnLeave(this.getAvailableEarnLeave(userId));
        leaveQuotaDataBean.setAvailableRestrictedHoliday(this.getAvailableRestrictedDay(userId, false).intValue());
        leaveQuotaDataBean.setLossOfPayLeaves(this.getNoOfLossOfPay(userId));
        leaveQuotaDataBean.setUserId(userId);
        User user = userService.getUserbyId(userId, false, false, true, false);
        leaveQuotaDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
        return leaveQuotaDataBean;
    }

    public List<LeaveDataBean> retrieveLeaveListByLeaveType(Long userId, String leaveType) {
        System.out.println("************ RetriveLeave By Leave Type List *********************");
        try {
            List<Leave> leaveList = null;
            if (leaveType.equals(SystemConstantUtil.CASUL_LEAVE)) {
                System.out.println("************* get Available Casual Leave List ****************");
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userId);
                SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
                if (simpleDateformat.format(systemUserDetail.getJoinDate()).equals(simpleDateformat.format(new Date()))) {
                    leaveList = leaveService.retrieveLeavesBetweenDateWithApprovalStatus(userId, systemUserDetail.getJoinDate(), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.CASUL_LEAVE, false);
                } else {
                    leaveList = leaveService.retrieveLeavesBetweenDateWithApprovalStatus(userId, new Date(new Date().getYear(), 0, 01), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.CASUL_LEAVE, false);
                }
            }
            if (leaveType.equals(SystemConstantUtil.EARN_LEAVE)) {
                System.out.println("************* get Available Earn Leave List ****************");
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userId);
                leaveList = leaveService.retrieveLeavesBetweenDateWithApprovalStatus(userId, systemUserDetail.getJoinDate(), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.EARN_LEAVE, false);
            }
            if (leaveType.equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
                System.out.println("************* get Available Restricted Leave List ****************");
                leaveList = leaveService.retrieveLeavesBetweenDateWithApprovalStatus(userId, new Date(new Date().getYear(), 0, 1), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.RISTRICTED_HOLIDAY, false);
            }
            if (leaveType.equals(SystemConstantUtil.LOSS_OF_PAY)) {
                System.out.println("************* get Loss Of Pay Leave List ****************");
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userId);
                leaveList = leaveService.retrieveLeavesBetweenDateWithApprovalStatus(userId, systemUserDetail.getJoinDate(), new Date(new Date().getYear(), 11, 31), SystemConstantUtil.LOSS_OF_PAY, false);
            }
            List<LeaveDataBean> leaveDataBeansList = new ArrayList<LeaveDataBean>();
            if (!leaveList.isEmpty()) {
                for (Leave leave : leaveList) {
                    Float leaveDays = retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
                    LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                    leaveDataBean.setNoOfDays(leaveDays);
                    leaveDataBeansList.add(leaveDataBean);
                    if (leaveDataBean.getLeaveType().equals(SystemConstantUtil.RISTRICTED_HOLIDAY)) {
                        Holiday holiday = holidayService.retrieveHolidayById(leaveDataBean.getHolidayId());
                        leaveDataBean.setHolidayName(holiday.getHolidayName());
                    }
                }
                return leaveDataBeansList;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<LeaveQuotaDataBean> retrieveLeaveQuotaBeanList() throws UserManagementException {
        List<User> users = userService.getAllActiveUsers();
        List<LeaveQuotaDataBean> leaveQuotaDataBeans = new ArrayList<LeaveQuotaDataBean>();
        if (!users.isEmpty()) {
            for (User user : users) {
                LeaveQuotaDataBean leaveQuotaDataBean = this.retrieveLeaveQuotaDetailByUserId(user.getId());
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(user.getId());
                leaveQuotaDataBean.setEmpId(systemUserDetail.getEmpId());
                leaveQuotaDataBeans.add(leaveQuotaDataBean);
            }
        }
        return leaveQuotaDataBeans;
    }

    public List<LeaveDataBean> retrieveLeaveBetweenDates(Long userId, Date fromDate, Date toDate) throws UserManagementException {
        List<Leave> leaveListBeetweenDate = leaveService.retrieveLeavesBetweenDate(userId, fromDate, toDate, null);
        if (!leaveListBeetweenDate.isEmpty()) {
            List<LeaveDataBean> leaveDataBeanList = new ArrayList<LeaveDataBean>();
            for (Leave leave : leaveListBeetweenDate) {
                LeaveDataBean leaveDataBean;
                leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                UserContact userContect = userService.getUserContactById(leave.getUserId());
                leaveDataBean.setUserName(userContect.getFirstName() + " " + userContect.getLastName());
                leaveDataBean.setNoOfDays(retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType()));
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(leave.getUserId());
                leaveDataBean.setEmpId(systemUserDetail.getEmpId());
                leaveDataBeanList.add(leaveDataBean);
            }
            return leaveDataBeanList;
        }
        return null;
    }

    public Long[] retrieveLeaveOpinionList() {
        return systemUserDetailService.retrieveLeaveOpinionList();
    }

    public void submitLeaveOpinionList(Long[] userIdList) {
        systemUserDetailService.submitLeaveOpinionList(userIdList);
    }

    public Long[] retrieveAttendanceManagerList() {
        return systemUserDetailService.retrieveAttendanceManagerList();
    }

    public void submitAttendanceManagerList(Long[] userIdList) {
        systemUserDetailService.submitAttendanceManagerList(userIdList);
    }

    public List<LeaveDataBean> retrievePersonalLeaveOpinionNotificationDetail() throws UserManagementException {
        List<Leave> leaveListOfOpinionNotification = leaveService.retrieveLeaveNotificationDetailByUserId(loginDataBean.getId());
        if (leaveListOfOpinionNotification != null) {
            List<LeaveDataBean> leaveDataBeanList = new ArrayList<LeaveDataBean>();
            for (Leave leave : leaveListOfOpinionNotification) {
                LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                User user = userService.getUserbyId(leave.getUserId(), false, false, true, false);
                leaveDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                leaveDataBean.setLeaveOpinionId(leaveOpinionService.retrieveLeaveOpinionIdByLeaveIdAndUserId(loginDataBean.getId(), leaveDataBean.getLeaveId()));
                leaveDataBeanList.add(leaveDataBean);
            }
            return leaveDataBeanList;
        }
        return null;
    }

    public String submitLeaveOpinion(Long levaeOpinionId, String opinion) {
        LeaveOpinion leaveOpinion = leaveOpinionService.retrieveLeaveOpinionById(levaeOpinionId);
        if (leaveOpinion.getIsArchive() != true) {
            leaveOpinion.setOpinion(opinion);
            leaveOpinion.setIsArchive(true);
            leaveOpinionService.updateLeaveOpinion(leaveOpinion);
            return "Leave opinion submitted successfully";
        } else {
            if (leaveOpinion.getOpinion() == null || leaveOpinion.getOpinion().equals("")) {
                Leave leave = leaveService.retrieveLeaveById(leaveOpinion.getLeaveId());
                if (leave != null && leave.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
                    return "You already archive leave.";
                } else {
                    return "Leave already responded.";
                }
            } else {
                return "Already opinion is given by you.";
            }
        }
    }

    public void archiveLeaveOpinion(Long levaeOpinionId) {
        LeaveOpinion leaveOpinion = leaveOpinionService.retrieveLeaveOpinionById(levaeOpinionId);
        leaveOpinion.setIsArchive(true);
        leaveOpinionService.updateLeaveOpinion(leaveOpinion);
    }

    public List<LeaveOpinionDataBean> retrieveLeaveOpinionByLeaveId(Long leaveId) throws UserManagementException {
        List<LeaveOpinion> leaveOpinionsList = leaveOpinionService.retrieveLeaveOpinionListByLeaveId(leaveId);
        List<LeaveOpinionDataBean> leaveOpinionDataBeanslist = new ArrayList<LeaveOpinionDataBean>();
        if (leaveOpinionsList != null) {
            for (LeaveOpinion leaveOpinion : leaveOpinionsList) {
                if (leaveOpinion.getOpinion() != null && !"".equals(leaveOpinion.getOpinion().trim())) {
                    LeaveOpinionDataBean leaveOpinionDataBean = convertLeaveOpinionModelToLeaveOpinionDataBean(leaveOpinion, new LeaveOpinionDataBean());
                    leaveOpinionDataBeanslist.add(leaveOpinionDataBean);
                }
            }
        }
        return leaveOpinionDataBeanslist;
    }

    public List<LeaveDataBean> retrieveCancelLeaveNotification() throws UserManagementException {
        if (loginDataBean.getRole() == null ? SystemConstantUtil.ROLE_SUPER_ADMIN == null : loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
            List<Leave> cancelLeaveList = leaveService.retrieveCancelLeaveNotification();
            if (cancelLeaveList != null) {
                List<LeaveDataBean> leaveDataBeanList = new ArrayList<LeaveDataBean>();
                for (Leave leave : cancelLeaveList) {
                    LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                    User user = userService.getUserbyId(leave.getUserId(), false, false, true, false);
                    leaveDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    System.out.println(leaveDataBean.getAppliedStatus());
                    leaveDataBeanList.add(leaveDataBean);
                }
                return leaveDataBeanList;
            }
        }
        return null;
    }

    public List<LeaveDataBean> retrieveOpinionLeaveResponseList() throws UserManagementException {
        List<LeaveOpinion> leaveOpinionsList = leaveOpinionService.retrieveOpinionLeaveResponseList(loginDataBean.getId());
        if (leaveOpinionsList != null) {
            List<LeaveDataBean> leaveDataBeansList = new ArrayList<LeaveDataBean>();
            for (LeaveOpinion leaveOpinion : leaveOpinionsList) {
                Leave leave = leaveService.retrieveLeaveById(leaveOpinion.getLeaveId());
                LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                User user = userService.getUserbyId(leave.getUserId(), false, false, true, false);
                leaveDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                leaveDataBean.setLeaveOpinionId(leaveOpinionService.retrieveLeaveOpinionIdByLeaveIdAndUserId(loginDataBean.getId(), leaveDataBean.getLeaveId()));
                System.out.println(leaveDataBean.getAppliedStatus());
                leaveDataBeansList.add(leaveDataBean);
            }
            return leaveDataBeansList;
        }
        return null;
    }

    public LeaveOpinionDataBean retrieveLeaveOpinionByLeaveIdAndUserId(Long leaveId) throws UserManagementException {
        LeaveOpinion leaveOpinion = leaveOpinionService.retrieveLeaveOpinionByLeaveIdAndUserId(leaveId, loginDataBean.getId());
        return convertLeaveOpinionModelToLeaveOpinionDataBean(leaveOpinion, new LeaveOpinionDataBean());
    }

    public void archiveLeaveOpinionResponseNotification(Long leaveOpinionId) {
        LeaveOpinion leaveOpinion = leaveOpinionService.retrieveLeaveOpinionById(leaveOpinionId);
        leaveOpinion.setIsNotificationShow(false);
        leaveOpinionService.updateLeaveOpinion(leaveOpinion);
    }

    public List<LeaveDataBean> retrieveApproveLeaveBetweenDates(Long id, Date leaveFromDate, Date leaveToDate) {
        if (id == null) {
            id = loginDataBean.getId();
        }
        List<Leave> leaveList;
        List<LeaveDataBean> leaveDataBeansList = new ArrayList<LeaveDataBean>();
        leaveList = leaveService.retrieveLeavesBetweenDateWithApprovalStatus(id, leaveFromDate, leaveToDate, null, false);
        if (!leaveList.isEmpty()) {
            for (Leave leave : leaveList) {
                Float leaveDays = retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
                LeaveDataBean leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                leaveDataBean.setNoOfDays(leaveDays);
                leaveDataBeansList.add(leaveDataBean);
            }
            return leaveDataBeansList;
        } else {
            return null;
        }
    }

    public List<LeaveDataBean> retrieveLeaveHistory(Long leaveId, Long userId, Date fromDate, Date toDate) {
        List<Leave> leaveListBetweenDate = leaveService.retrieveLeavesBetweenDateListWithoutSameLeaveDetail(userId, fromDate, toDate, leaveId, null);
        if (leaveListBetweenDate != null && !leaveListBetweenDate.isEmpty()) {
            List<LeaveDataBean> leaveDataBeanList = new ArrayList<LeaveDataBean>();
            for (Leave leave : leaveListBetweenDate) {
                LeaveDataBean leaveDataBean;
                leaveDataBean = convertLeaveModelToLeaveDataBean(leave, new LeaveDataBean());
                leaveDataBean.setNoOfDays(retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType()));
                leaveDataBeanList.add(leaveDataBean);
            }
            return leaveDataBeanList;
        }
        return new ArrayList<LeaveDataBean>();
    }

    public Long[] retrieveUserSelectedLeaveOpinion(Long leaveId) {
        return leaveOpinionService.retrieveUserSelectedLeaveOpinionUserIdList(leaveId);
    }

    private void sendMail(List<Long> listOfUserIdForSendEmail, Leave leave, Leave oldLeave, String mailFormat) throws UserManagementException {
        if (listOfUserIdForSendEmail != null && listOfUserIdForSendEmail.size() > 0) {
            List<User> usersList = userService.retrieveUsersByUserList(listOfUserIdForSendEmail, null, null, null, true);
            if (usersList != null && usersList.size() > 0) {
                String[] to = new String[usersList.size()];
                int i = 0;
                for (User user : usersList) {
                    to[i++] = user.getContact().getEmailAddress();
                }
                EmailFormat emailFormat = emailFormatService.retriveEmailByFormateName(mailFormat);
                if (emailFormat != null) {
                    String emailBody = emailFormat.getBody();
                    User user = userService.getUserbyId(leave.getUserId(), false, false, true, false);
                    String employeeName = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String approver = "";
                    if (oldLeave != null) {
                        emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_OLD_FROM_DATE, sdf.format(oldLeave.getFromDate()).toString());
                        emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_OLD_TO_DATE, sdf.format(oldLeave.getToDate()).toString());
                        if (oldLeave.getResponseBy() != null) {
                            user = userService.getUserbyId(oldLeave.getResponseBy(), false, false, true, false);
                            approver = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                        }
                    } else if (leave.getResponseBy() != null) {
                        user = userService.getUserbyId(leave.getResponseBy(), false, false, true, false);
                        approver = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    }

                    String leaveInfo = this.setLeaveInfo(leave, oldLeave, employeeName, approver);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_FROM_DATE, sdf.format(leave.getFromDate()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_CURRENT_DATE, sdf.format(new Date()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_TO_DATE, sdf.format(leave.getToDate()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_END_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_START_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_LATE_DURATION, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_INFO, leaveInfo);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_APPROVER, approver);
                    String oldLeaveFromDate = null;
                    String oldLeaveToDate = null;
                    if (oldLeave != null && oldLeave.getToDate() != null) {
                        oldLeaveToDate = sdf.format(oldLeave.getToDate());
                    }
                    if (oldLeave != null && oldLeave.getFromDate() != null) {
                        oldLeaveFromDate = sdf.format(oldLeave.getFromDate());
                    }
                    emailBody = SystemFunctionUtil.replaceTextForEmail(emailBody, employeeName, null, null, sdf.format(leave.getFromDate()), sdf.format(leave.getToDate()), leaveInfo, approver, null, null, null, oldLeaveFromDate, oldLeaveToDate, null, null);

                    if (mailFormat.equals(SystemConstantUtil.EMAIL_FORMAT_APPLY_LEAVE) || mailFormat.equals(SystemConstantUtil.EMAIL_FORMAT_LEAVE_CANCELLATION) || mailFormat.equals(SystemConstantUtil.EMAIL_FORMAT_UPDATE_LEAVE)) {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        emailBody += "<br/><form action=\"http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/leaveResponse\" method=\"post\"><input type=\"hidden\" name=\"leaveId\" value=\"" + leave.getLeaveId() + "\" /><table><tr><td valign='top'>Comment: <td><td><textarea name=\"comment\" ></textarea></td></tr><td colspan='2' align='center'><input type=\"submit\" value=\"Approve\" name=\"type\"/> <input type=\"submit\" value=\"Disapprove\" name=\"type\"/></td></tr></form>";
                    } else if (mailFormat.equals(SystemConstantUtil.EMAIL_FORMAT_LEAVE_OPINION)) {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//                         emailBody +=
//                                "<head><script type = 'text/javascript'> function validateForm(){"
//                                + "var x = document.forms['leaveOpinionForm']['comment'].value;"
//                                + "if (x == null || x == '') {alert('Opinion must be filled out'); return false; }}</ script> < / head >";
                        emailBody += "<br/><form name='leaveOpinionForm' action=\"http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/leaveOpinionResponse\" method=\"post\" onsubmit='return validateForm()'><table><input type=\"hidden\" name=\"leaveId\" value=\"" + leave.getLeaveId() + "\" /><tr><td valign='top'>Opinion:</td><td> <textarea name=\"comment\" ></textarea> </td><tr><td colspan='2'><input type=\"submit\" value=\"Give opinion\" name=\"type\"/></td></tr> </form>";
                    }

                    String subject = emailFormat.getSubject();
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_FROM_DATE, sdf.format(leave.getFromDate()));
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_TO_DATE, sdf.format(leave.getToDate()));
                    subject = SystemFunctionUtil.replaceTextForEmail(subject, employeeName, null, null, sdf.format(leave.getFromDate()), sdf.format(leave.getToDate()), leaveInfo, approver, null, null, null, oldLeaveFromDate, oldLeaveToDate, null, null);
                    userTransformerBean.sendMail(subject, emailBody, to, false, null);
                }
            }
        }
    }

    private void sendMailToAdmin(Leave leave, Leave oldLeave, String emailFormat) throws UserManagementException {
        List<User> adminUserDetailList = userService.getUsersByType(SystemConstantUtil.ROLE_SUPER_ADMIN, true, false, false, false, false);
        if (adminUserDetailList != null && !adminUserDetailList.isEmpty()) {
            List<Long> adminList = new ArrayList<Long>();
            for (User user : adminUserDetailList) {
                if (user.getId() != loginDataBean.getId()) {
                    adminList.add(user.getId());
                }
            }
            System.out.println("Admin List size:" + adminList.size());
            this.sendMail(adminList, leave, oldLeave, emailFormat);
        }
    }

    private String setLeaveInfo(Leave leave, Leave oldLeave, String empName, String responseEmpName) {
        StringBuilder message = new StringBuilder();
        Float leaveDays = retrieveLeaveDays(leave.getFromDate(), leave.getFromDateLeaveType(), leave.getToDate(), leave.getToDateLeaveType());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (oldLeave != null) {
            Float oldLeaveDays = retrieveLeaveDays(oldLeave.getFromDate(), oldLeave.getFromDateLeaveType(), oldLeave.getToDate(), oldLeave.getToDateLeaveType());
            message.append("<p><center><b>Old Leave Detail:</b></center></p><br/><hr/>");
            message.append("<table width='100%' border='0' cellpadding='7px' cellspacing='0'>");
            message.append("<tr><th width='25%' align='right'> <strong>Employee Name : &nbsp;</strong> </th><td width='75%'>").append(empName).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Leave Type : &nbsp;</strong> </th><td>").append(oldLeave.getLeaveType()).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Subject : &nbsp;</strong> </th><td>").append(oldLeave.getLeaveSubject()).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>From Date : &nbsp;</strong> </th><td>").append(sdf.format(oldLeave.getFromDate()).toString()).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>From Date Type: &nbsp;</strong> </th><td>").append("FD".equals(oldLeave.getFromDateLeaveType()) ? "Full Day" : "HDM".equals(oldLeave.getFromDateLeaveType()) ? "Half Day Morning" : "Half Day Evening").append("</td></tr>");
            message.append("<tr><th align='right'> <strong>To Date : &nbsp;</strong> </th><td>").append(sdf.format(oldLeave.getToDate()).toString()).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>To Date Type: &nbsp;</strong> </th><td>").append("FD".equals(oldLeave.getToDateLeaveType()) ? "Full Day" : "HDM".equals(oldLeave.getToDateLeaveType()) ? "Half Day Morning" : "Half Day Evening").append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Leave Days : &nbsp;</strong> </th><td>").append(oldLeaveDays).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Reason : &nbsp;</strong> </th><td>").append(oldLeave.getReason()).append("</td></tr>");
            if (oldLeave.getResponseBy() != null) {
                message.append("<tr><th align='right'> <strong>Response By : &nbsp;</strong> </th><td>").append(responseEmpName).append("</td></tr>");
                message.append("<tr><th align='right'> <strong>Comment : &nbsp;</strong> </th><td>").append(oldLeave.getAdminComment()).append("</td></tr>");
            }
            message.append("</table><br/><hr/> <p><center><b>New Leave Detail:</b></center></p><br/><hr/>");
        }
        message.append("<table width='100%' border='0' cellpadding='7px' cellspacing='0'>");
        message.append("<tr><th width='25%' align='right'> <strong>Employee Name : &nbsp;</strong> </th><td width='75%'>").append(empName).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Leave Type : &nbsp;</strong> </th><td>").append(leave.getLeaveType()).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Subject : &nbsp;</strong> </th><td>").append(leave.getLeaveSubject()).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>From Date : &nbsp;</strong> </th><td>").append(sdf.format(leave.getFromDate()).toString()).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>From Date Type: &nbsp;</strong> </th><td>").append("FD".equals(leave.getFromDateLeaveType()) ? "Full Day" : "HDM".equals(leave.getFromDateLeaveType()) ? "Half Day Morning" : "Half Day Evening").append("</td></tr>");
        message.append("<tr><th align='right'> <strong>To Date : &nbsp;</strong> </th><td>").append(sdf.format(leave.getToDate()).toString()).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>To Date Type: &nbsp;</strong> </th><td>").append("FD".equals(leave.getToDateLeaveType()) ? "Full Day" : "HDM".equals(leave.getToDateLeaveType()) ? "Half Day Morning" : "Half Day Evening").append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Leave Days : &nbsp;</strong> </th><td>").append(leaveDays).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Reason : &nbsp;</strong> </th><td>").append(leave.getReason()).append("</td></tr>");
        message.append("</table>");
        return message.toString();
    }

    public Long retrieveOpinionIdByleaveIdAndUserId(Long leaveId) {
        return leaveOpinionService.retrieveLeaveOpinionIdByLeaveIdAndUserId(loginDataBean.getId(), leaveId);
    }

    public Long[] retrieveUserUnderUserList(Long leaderId) {
        return systemUserDetailService.retrieveUsersByLeaderid(leaderId);
//        if (userIds != null) {
//            List<User> users = userService.retrieveUsersBySearchQriteria(userIds, null);
//            List<SystemUserDetailDataBean> systemUserDetailDataBeans = new ArrayList<SystemUserDetailDataBean>();
//            for (User user : users) {
//                SystemUserDetailDataBean systemUserDetailDataBean = new SystemUserDetailDataBean();
//                systemUserDetailDataBean.setUserId(user.getId());
//                String name = "";
//                if (user.getContact().getFirstName() != null) {
//                    name += user.getContact().getFirstName();
//                }
//                if (user.getContact().getLastName() != null) {
//                    name += user.getContact().getLastName();
//                }
//                systemUserDetailDataBean.setName(name);
//                systemUserDetailDataBeans.add(systemUserDetailDataBean);
//            }
//            return systemUserDetailDataBeans;
//        }
//        return null;
    }

    public void submitLeaderUsersList(Long leaderId, Long[] userIds) {
        System.out.println("Leader Id : " + leaderId);
        System.out.println("Selected UserId :");
        for (Long long1 : userIds) {
            System.out.println("Long 1");
        }
        systemUserDetailService.submitUsersAndLeaderid(leaderId, userIds);
    }

}
