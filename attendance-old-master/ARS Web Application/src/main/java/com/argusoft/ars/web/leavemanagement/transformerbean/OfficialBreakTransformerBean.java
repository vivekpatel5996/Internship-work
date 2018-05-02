/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.transformerbean;

import com.argusoft.ars.core.*;
import com.argusoft.ars.model.EmailFormat;
import com.argusoft.ars.model.OfficialBreak;
import com.argusoft.ars.model.Shift;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.web.leavemanagement.databean.OfficialBreakDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.UserTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.usermanagement.common.core.SystemConfigurationService;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "officialBreakTransformerBean")
@RequestScoped
public class OfficialBreakTransformerBean {

    //  Core properties
    @ManagedProperty(value = "#{officialBreakService}")
    private OfficialBreakService officialBreakService;
    @ManagedProperty(value = "#{shiftService}")
    private ShiftService shiftService;
    @ManagedProperty(value = "#{officialBreakDataBean}")
    private OfficialBreakDataBean officialBreakDataBean;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    @ManagedProperty(value = "#{emailFormatService}")
    private EmailFormatService emailFormatService;
    @ManagedProperty(value = "#{systemConfigurationService}")
    private SystemConfigurationService systemConfigurationService;
    //  Other properties
    private static final Logger log = Logger.getLogger(OfficialBreakTransformerBean.class);

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public OfficialBreakDataBean getOfficialBreakDataBean() {
        return officialBreakDataBean;
    }

    public void setOfficialBreakDataBean(OfficialBreakDataBean officialBreakDataBean) {
        this.officialBreakDataBean = officialBreakDataBean;
    }

    public OfficialBreakService getOfficialBreakService() {
        return officialBreakService;
    }

    public void setOfficialBreakService(OfficialBreakService officialBreakService) {
        this.officialBreakService = officialBreakService;
    }

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ShiftService getShiftService() {
        return shiftService;
    }

    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    public EmailFormatService getEmailFormatService() {
        return emailFormatService;
    }

    public void setEmailFormatService(EmailFormatService emailFormatService) {
        this.emailFormatService = emailFormatService;
    }

    public SystemConfigurationService getSystemConfigurationService() {
        return systemConfigurationService;
    }

    public void setSystemConfigurationService(SystemConfigurationService systemConfigurationService) {
        this.systemConfigurationService = systemConfigurationService;
    }

    public UserTransformerBean getUserTransformerBean() {
        return userTransformerBean;
    }

    public void setUserTransformerBean(UserTransformerBean userTransformerBean) {
        this.userTransformerBean = userTransformerBean;
    }

    public List<OfficialBreakDataBean> retrieveAllOfficialBreak() {

        try {
            System.out.println("User Id" + loginDataBean.getId());
            List<OfficialBreak> officialBreakList = officialBreakService.retrieveOfficialBreakByUserId(loginDataBean.getId());
            List<OfficialBreakDataBean> officialBreakDataBeansList = new ArrayList<OfficialBreakDataBean>();
            if (!officialBreakList.isEmpty()) {
                for (OfficialBreak officialBreak : officialBreakList) {
                    OfficialBreakDataBean officialBreakDataBean1 = convertOfficialBreakModelToOfficialBreakDataBean(officialBreak, new OfficialBreakDataBean());
                    officialBreakDataBeansList.add(officialBreakDataBean1);
                }
                return officialBreakDataBeansList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    /**
     * Method to Add OfficialBreak object.
     *
     */
    public String applyOfficialBreak(OfficialBreakDataBean officialBreakDataBean) {
        try {
            Date fromDate;
            Date toDate;
            if (officialBreakDataBean.getFromDate() != null) {
                fromDate = officialBreakDataBean.getFromDate();
                toDate = officialBreakDataBean.getToDate();
            } else {
                DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fromDate = (Date) formatter.parse(officialBreakDataBean.getCardLogFromDate());
                if (officialBreakDataBean.getCardLogToDate() == null) {
                    SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(officialBreakDataBean.getUserId());
                    System.out.println("-------------------shiftId---------------" + systemUserDetail.getShiftId().getShiftId());
                    System.out.println("--------------------1");
                    Shift shift = shiftService.retrieveShiftById(systemUserDetail.getShiftId().getShiftId());
                    System.out.println("--------shift------------2" + shift);
                    Date shiftEndTime = shift.getShiftEndTime();
                    System.out.println("----------------name----------" + shift.getShiftName());
                    System.out.println("----------shiftEndTime" + shiftEndTime);
                    System.out.println("--------hour------------" + shiftEndTime.getHours());
                    System.out.println("--------minutes---------" + shiftEndTime.getMinutes());
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(shiftEndTime);
                    cal.set(Calendar.DATE, fromDate.getDate());
                    cal.set(Calendar.MONTH, fromDate.getMonth());
                    cal.set(Calendar.YEAR, fromDate.getYear() + 1900);
                    cal.set(Calendar.HOUR_OF_DAY, shiftEndTime.getHours());
                    cal.set(Calendar.MINUTE, shiftEndTime.getMinutes());
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    toDate = cal.getTime();
                } else {
                    toDate = (Date) formatter.parse(officialBreakDataBean.getCardLogToDate());
                }
            }
            boolean isOfficialBreakAlreadyApply = officialBreakService.isOfficialBreakAlreadyApply(loginDataBean.getId(), fromDate, toDate);
            if (officialBreakDataBean.getOfficialBreakType().equals("officialBreak")) {
                DateFormat formatter;
                Date fromDateTime, toDateTime;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fromDateTime = (Date) formatter.parse(officialBreakDataBean.getCardLogFromDate());
                if (officialBreakDataBean.getCardLogToDate() == null) {
                    SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(officialBreakDataBean.getUserId());
                    Shift shift = shiftService.retrieveShiftById(systemUserDetail.getShiftId().getShiftId());
                    Date shiftEndTime = shift.getShiftEndTime();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(shiftEndTime);
                    cal.set(Calendar.DATE, fromDateTime.getDate());
                    cal.set(Calendar.MONTH, fromDateTime.getMonth());
                    cal.set(Calendar.YEAR, fromDate.getYear() + 1900);
                    cal.set(Calendar.HOUR_OF_DAY, shiftEndTime.getHours());
                    cal.set(Calendar.MINUTE, shiftEndTime.getMinutes());
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    toDateTime = cal.getTime();
                } else {
                    toDateTime = (Date) formatter.parse(officialBreakDataBean.getCardLogToDate());

                }
                if (fromDateTime.getTime() >= toDateTime.getTime()) {
                    return "Please Select Valid Time Period";
                }
            }
            if (!isOfficialBreakAlreadyApply) {
                OfficialBreak officialBreak = convertOfficialBreakDataBeanToOfficialBreakModel(officialBreakDataBean, new OfficialBreak());
                officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW);
                this.officialBreakService.createOfficialBreak(officialBreak);
                if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    this.sendMailToAdmin(officialBreak, SystemConstantUtil.EMAIL_FORMAT_APPLY_OFFICIALBREAK);
                }

                return SystemConstantUtil.SUCCESS;
            } else {
                return "officialBreak already applied for this date";
            }
        } catch (Exception e) {
            System.out.println("Add OfficialBreak Catch");
            System.out.println(e);
            if (e.toString().equals("java.lang.NullPointerException")) {
                return "Please check that any of field is remain to fill..";
            }
            return e.toString();
        }
    }

    private OfficialBreak convertOfficialBreakDataBeanToOfficialBreakModel(OfficialBreakDataBean officialBreakDataBean, OfficialBreak officialBreak) throws ParseException {
        officialBreak.setAppliedDate(new Date());
        if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
            officialBreak.setApprovalStatus(SystemConstantUtil.APPROVE);
            officialBreak.setIsNotificationShow(false);
            officialBreak.setIsArchive(true);
        } else {
            officialBreak.setApprovalStatus(SystemConstantUtil.PENDING);
            officialBreak.setIsNotificationShow(true);
            officialBreak.setIsArchive(false);
        }
        if ((officialBreakDataBean.getOfficialBreakType()).equals(SystemConstantUtil.BUSINESS_TRIP)) {
            officialBreak.setFromDate(officialBreakDataBean.getFromDate());
            officialBreak.setToDate(officialBreakDataBean.getToDate());
        }
        if ((officialBreakDataBean.getOfficialBreakType()).equals(SystemConstantUtil.OFFICIAL_BREAK)) {
            DateFormat formatter;
            Date fromDate;
            Date toDate;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fromDate = (Date) formatter.parse(officialBreakDataBean.getCardLogFromDate());
            officialBreak.setFromDate(fromDate);
            if (officialBreakDataBean.getCardLogToDate() == null) {
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(officialBreakDataBean.getUserId());
                Shift shift = shiftService.retrieveShiftById(systemUserDetail.getShiftId().getShiftId());
                Date shiftEndTime = shift.getShiftEndTime();
                Calendar cal = Calendar.getInstance();
                cal.setTime(shiftEndTime);
                cal.set(Calendar.DATE, fromDate.getDate());
                cal.set(Calendar.MONTH, fromDate.getMonth());
                cal.set(Calendar.YEAR, fromDate.getYear() + 1900);
                cal.set(Calendar.HOUR_OF_DAY, shiftEndTime.getHours());
                cal.set(Calendar.MINUTE, shiftEndTime.getMinutes());
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);
                toDate = cal.getTime();
            } else {
                toDate = (Date) formatter.parse(officialBreakDataBean.getCardLogToDate());
            }
            officialBreak.setToDate(toDate);
        }
        officialBreak.setType(officialBreakDataBean.getOfficialBreakType());
        officialBreak.setReason(officialBreakDataBean.getReason());
        officialBreak.setUserId(loginDataBean.getId());
        officialBreak.setAdminComment(null);
        return officialBreak;
    }

    private String officialBreakDays(OfficialBreak officialBreak) {
        String noOfDays = null;
        if (officialBreak.getToDate() != null) {
            noOfDays = ((int) (((officialBreak.getToDate().getTime()) - (officialBreak.getFromDate().getTime())) / (1000 * 60 * 60 * 24)) + 1) + " Days";
        }
        return noOfDays;
    }

    private OfficialBreakDataBean convertOfficialBreakModelToOfficialBreakDataBean(OfficialBreak officialBreak, OfficialBreakDataBean officialBreakDataBean) throws UserManagementException {

        if (officialBreak.getType().equals(SystemConstantUtil.OFFICIAL_BREAK)) {
            long breakDurationInMilliseconds = ((officialBreak.getToDate().getTime()) - (officialBreak.getFromDate().getTime()));
            officialBreakDataBean.setOfficialBreakDuration(breakDurationInMilliseconds);
            long hours = breakDurationInMilliseconds / (60 * 60 * 1000);
            breakDurationInMilliseconds -= (hours * 60 * 60 * 1000);
            long minutes = breakDurationInMilliseconds / (1000 * 60);
            String breakDuration = hours + " Hours and " + minutes + " Minutes";
            officialBreakDataBean.setBreakDuration(breakDuration);
            officialBreakDataBean.setFromDate(officialBreak.getFromDate());
            officialBreakDataBean.setToDate(officialBreak.getToDate());
            officialBreakDataBean.setCardLogFromDate(officialBreak.getFromDate().toString());
            officialBreakDataBean.setCardLogToDate(officialBreak.getToDate().toString());
        } else {
            officialBreakDataBean.setFromDate(officialBreak.getFromDate());
            officialBreakDataBean.setToDate(officialBreak.getToDate());
            String officialBreakDays = officialBreakDays(officialBreak);
            officialBreakDataBean.setBreakDuration(officialBreakDays);
        }
        officialBreakDataBean.setAdminComment(officialBreak.getAdminComment());
        officialBreakDataBean.setAppliedDate(officialBreak.getAppliedDate());
        officialBreakDataBean.setAppliedStatus(officialBreak.getAppliedStatus());
        officialBreakDataBean.setApprovalStatus(officialBreak.getApprovalStatus());
        officialBreakDataBean.setOfficialBreakId(officialBreak.getOfficialBreakId());
        officialBreakDataBean.setOfficialBreakType(officialBreak.getType());
        officialBreakDataBean.setReason(officialBreak.getReason());
        officialBreakDataBean.setResponseBy(officialBreak.getResponseBy());
        officialBreakDataBean.setResponseDate(officialBreak.getResponseDate());
        officialBreakDataBean.setUserId(officialBreak.getUserId());
        return officialBreakDataBean;
    }

    /**
     * Method to Inactivate OfficialBreak.
     */
    public String cancelOfficialBreak(OfficialBreakDataBean officialBreakDataBean) {
        try {
            OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakDataBean.getOfficialBreakId());
            if (officialBreak != null) {
                if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                    officialBreak.setApprovalStatus(null);
                    officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_CANCEL);
                    System.out.println("role is super admin");
                } else if (officialBreak.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                    officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL);
                    officialBreak.setApprovalStatus(SystemConstantUtil.PENDING);
                    officialBreak.setIsNotificationShow(true);
                } else if (officialBreak.getAppliedStatus().equals(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_MODIFIED)) {
                    officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL);
                    officialBreak.setIsNotificationShow(true);
                    officialBreak.setApprovalStatus(SystemConstantUtil.PENDING);
                } else {
                    officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_CANCEL);
                    officialBreak.setApprovalStatus(null);
                    officialBreak.setIsNotificationShow(true);
                }
                try {

                    officialBreakService.updateOfficialBreak(officialBreak);
                } catch (Exception e) {
                    System.out.println("catch of updating oficialBreak--------------------");
                    System.out.println("Exception ------" + e);
                }
                return SystemConstantUtil.SUCCESS;
            }
        } catch (Exception ex) {
            log.error(ex);
            System.out.println(ex);
            return ex.toString();
        }
        return null;
    }

    public OfficialBreakDataBean retrieveOfficialBreakDetail(Long officialBreakId, String btnClick) throws UserManagementException {
        System.out.println("*********** Retrive officialBreak Detail *************");
        OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakId);
        if (officialBreak != null) {
            String officialBreakDays = officialBreakDays(officialBreak);
            OfficialBreakDataBean officialBreakDataBean1 = convertOfficialBreakModelToOfficialBreakDataBean(officialBreak, new OfficialBreakDataBean());
            officialBreakDataBean1.setBreakDuration(officialBreakDays);
            if (officialBreakDataBean1.getResponseBy() != null) {
                User user = userService.getUserbyId(officialBreak.getResponseBy(), false, false, true, false);
                officialBreakDataBean1.setResponseByUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            }
            return officialBreakDataBean1;
        }
        return null;
    }

    /**
     * Method to Update officialBreak Detail
     */
    public String updateOfficialBreak(OfficialBreakDataBean officialBreakDataBean) {
        try {
            System.out.println("in update officialBreak----------------");
            if (officialBreakDataBean.getOfficialBreakType().equals(SystemConstantUtil.OFFICIAL_BREAK)) {
                DateFormat formatter;
                Date fromDateTime, toDateTime;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("fromdate as String officialBreak--------------" + officialBreakDataBean.getCardLogFromDate());
                fromDateTime = (Date) formatter.parse(officialBreakDataBean.getCardLogFromDate());
                if (officialBreakDataBean.getCardLogToDate() == null) {
                    SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(officialBreakDataBean.getUserId());
                    Shift shift = shiftService.retrieveShiftById(systemUserDetail.getShiftId().getShiftId());
                    Date shiftEndTime = shift.getShiftEndTime();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(shiftEndTime);
                    cal.set(Calendar.DATE, fromDateTime.getDate());
                    cal.set(Calendar.MONTH, fromDateTime.getMonth());
                    cal.set(Calendar.YEAR, fromDateTime.getYear() + 1900);
                    cal.set(Calendar.HOUR_OF_DAY, shiftEndTime.getHours());
                    cal.set(Calendar.MINUTE, shiftEndTime.getMinutes());
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    toDateTime = cal.getTime();
                    System.out.println("-------------toDate " + toDateTime);

                } else {
                    toDateTime = (Date) formatter.parse(officialBreakDataBean.getCardLogToDate());

                }
                if (fromDateTime.getTime() >= toDateTime.getTime()) {
                    return "Exit Time should be less than Entry Time";
                }
            }
            OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakDataBean.getOfficialBreakId());
            List<OfficialBreakDataBean> officialBreakListBeetweenDate = this.retrieveOfficialBreakBetweenDates(loginDataBean.getId(), officialBreakDataBean.getFromDate(), officialBreakDataBean.getToDate());
            if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW);
            } else {
                if (officialBreak.getApprovalStatus() != null) {
                    if (officialBreak.getAppliedStatus().equals(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW) && officialBreak.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
                        officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW_MODIFIED);
                    } else if (officialBreak.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                        officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_MODIFIED);
                    } else if (officialBreak.getApprovalStatus().equals(SystemConstantUtil.DISAPPROVE)) {
                        officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_DISAPPROVE_MODIFIED);
                    } else if (officialBreak.getAppliedStatus().equals(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL)) {
                        officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_MODIFIED);
                    } else if (officialBreak.getAppliedStatus().equals(SystemConstantUtil.OFFICIALBREAK_APPLY_CANCEL)) {
                        officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW_MODIFIED);
                    } else {
                        officialBreak.setAppliedStatus(officialBreak.getAppliedStatus());
                    }
                } else {
                    officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW_MODIFIED);
                }
            }
            boolean isOfficialBreakAlreadyApply = true;
            if (officialBreakListBeetweenDate == null) {
                isOfficialBreakAlreadyApply = false;
            } else if (officialBreakListBeetweenDate.size() == 1) {
                if (officialBreakListBeetweenDate.get(0).getOfficialBreakId().equals(officialBreak.getOfficialBreakId())) {
                    isOfficialBreakAlreadyApply = false;
                }
            }
            if (!isOfficialBreakAlreadyApply) {
                officialBreak = convertOfficialBreakDataBeanToOfficialBreakModel(officialBreakDataBean, officialBreak);
                officialBreakService.updateOfficialBreak(officialBreak);
                return SystemConstantUtil.SUCCESS;
            } else {
                return "OfficialBreak already applied..";
            }
        } catch (Exception ex) {
            log.error(ex);
            System.out.println(ex.getStackTrace().toString());
            return ex.toString();
        }
    }

    private List<OfficialBreakDataBean> retrieveOfficialBreakBetweenDates(Long id, Date fromDate, Date toDate) throws UserManagementException {
        List<OfficialBreak> officialBreakListBetweenDate = officialBreakService.retrieveOfficialBreaksBetweenDate(id, fromDate, toDate, null);
        if (!officialBreakListBetweenDate.isEmpty()) {
            List<OfficialBreakDataBean> officialbreakDataBeanList = new ArrayList<OfficialBreakDataBean>();
            for (OfficialBreak officialBreak : officialBreakListBetweenDate) {
                OfficialBreakDataBean officialBreakDataBean1;
                officialBreakDataBean1 = convertOfficialBreakModelToOfficialBreakDataBean(officialBreak, new OfficialBreakDataBean());
                User user = userService.getUserbyId(officialBreak.getUserId(), false, false, true, false);
                officialBreakDataBean1.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                officialBreakDataBean1.setBreakDuration(officialBreakDays(officialBreak));
                officialbreakDataBeanList.add(officialBreakDataBean1);
            }
            return officialbreakDataBeanList;
        }
        return null;
    }

    public String approveOfficialBreak(Long officialBreakId, String adminComment) {
        System.out.println("************ TransformerBean OfficialBreak Approve **************");
        OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakId);
        if (officialBreak.getAppliedStatus().equals(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL)) {
            officialBreak.setApprovalStatus(null);
            officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL_APPROVE);
        } else {
            officialBreak.setApprovalStatus(SystemConstantUtil.APPROVE);
            officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW);
        }
        officialBreak.setIsArchive(false);
        officialBreak.setResponseBy(loginDataBean.getId());
        officialBreak.setResponseDate(new Date());
        officialBreak.setIsNotificationShow(false);
        officialBreak.setAdminComment(adminComment);
        officialBreakService.updateOfficialBreak(officialBreak);
        return SystemConstantUtil.SUCCESS;
    }

    public List<OfficialBreakDataBean> retrieveOfficialBreakNotificationList() {
        System.out.println("***********OfficialBreak Notificaction List *********************");
        try {
            System.out.println("User Id" + loginDataBean.getId());
            List<OfficialBreak> officialBreakList = officialBreakService.retrievePendingOfficialBreak(loginDataBean.getId());
            List<OfficialBreakDataBean> officialBreakDataBeansList = new ArrayList<OfficialBreakDataBean>();
            if (!officialBreakList.isEmpty()) {
                for (OfficialBreak officialBreak : officialBreakList) {
                    String officialBreakDays = officialBreakDays(officialBreak);
                    OfficialBreakDataBean officialBreakDataBean1 = convertOfficialBreakModelToOfficialBreakDataBean(officialBreak, new OfficialBreakDataBean());
                    officialBreakDataBean1.setBreakDuration(officialBreakDays);
                    User user = userService.getUserbyId(officialBreak.getUserId(), false, false, true, false);
                    officialBreakDataBean1.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    officialBreakDataBeansList.add(officialBreakDataBean1);
                }
                return officialBreakDataBeansList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String disApproveOfficialBreak(Long officialBreakId, String adminComment) {
        System.out.println("************ TransformerBean OfficialBreak Disapprove **************");
        OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakId);
        if (officialBreak.getAppliedStatus().equals(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL)) {
            officialBreak.setApprovalStatus(SystemConstantUtil.APPROVE);
            officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_APPROVE_CANCEL_DISAPPROVE);
        } else {
            officialBreak.setAppliedStatus(SystemConstantUtil.OFFICIALBREAK_APPLY_NEW);
            officialBreak.setApprovalStatus(SystemConstantUtil.DISAPPROVE);
        }
        officialBreak.setIsArchive(false);
        officialBreak.setResponseBy(loginDataBean.getId());
        officialBreak.setResponseDate(new Date());
        officialBreak.setIsNotificationShow(false);
        officialBreak.setAdminComment(adminComment);
        officialBreakService.updateOfficialBreak(officialBreak);
        return SystemConstantUtil.SUCCESS;
    }

    public List<OfficialBreakDataBean> retrievePersonalOfficialBreakNotificationList() {
        System.out.println("************ Personal OfficialBreak Notificaction List *********************");
        try {
            System.out.println("User Id" + loginDataBean.getId());
            List<OfficialBreak> officialBreakList = officialBreakService.retrieveNotArchiveOfficialBreak(loginDataBean.getId());
            List<OfficialBreakDataBean> officialBreakDataBeansList = new ArrayList<OfficialBreakDataBean>();
            if (!officialBreakList.isEmpty()) {
                System.out.println("officialBreak list--------------------" + officialBreakList);
                for (OfficialBreak officialBreak : officialBreakList) {
                    String officialBreakDays = officialBreakDays(officialBreak);
                    OfficialBreakDataBean officialBreakDataBean1 = convertOfficialBreakModelToOfficialBreakDataBean(officialBreak, new OfficialBreakDataBean());
                    officialBreakDataBean1.setBreakDuration(officialBreakDays);
                    System.out.println("officialBreak list for loop--------------------");
                    System.out.println("-----officialBreak.getResponseBy()-------------" + officialBreak.getResponseBy());
                    User user = userService.getUserbyId(officialBreak.getResponseBy(), false, false, true, false);
                    officialBreakDataBean1.setResponseByUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    System.out.println("--------------------retrieve user" + officialBreakDataBean1.getResponseByUserName());
                    officialBreakDataBeansList.add(officialBreakDataBean1);
                }
                return officialBreakDataBeansList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void archiveOfficialBreak(Long officialBreakId) {
        OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakId);
        officialBreak.setIsArchive(true);
        officialBreakService.updateOfficialBreak(officialBreak);
    }

    public String cancelOfficialBreakArchive(Long officialBreakId) {
        System.out.println("************ TransformerBean OfficialBreak CancelLeaveArchive **************");
        OfficialBreak officialBreak = officialBreakService.retrieveOfficialBreakById(officialBreakId);
        officialBreak.setResponseBy(loginDataBean.getId());
        officialBreak.setResponseDate(new Date());
        officialBreak.setIsNotificationShow(false);
        try {
            officialBreakService.updateOfficialBreak(officialBreak);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return SystemConstantUtil.SUCCESS;
    }

    public List<OfficialBreakDataBean> retrieveOfficialBreak(List<OfficialBreak> officialBreakList) throws UserManagementException {
        List<OfficialBreakDataBean> officialBreakDataBeansList = new ArrayList<OfficialBreakDataBean>();
        if (!officialBreakList.isEmpty()) {
            System.out.println("officialBreak list--------------------" + officialBreakList);
            for (OfficialBreak officialBreak : officialBreakList) {
                OfficialBreakDataBean officialBreakDataBean1 = convertOfficialBreakModelToOfficialBreakDataBean(officialBreak, new OfficialBreakDataBean());
                officialBreakDataBeansList.add(officialBreakDataBean1);
            }
            return officialBreakDataBeansList;
        }
        return null;
    }

    private void sendMailToAdmin(OfficialBreak officialBreak, String emailFormat) throws UserManagementException {
        List<User> adminUserDetailList = userService.getUsersByType(SystemConstantUtil.ROLE_SUPER_ADMIN, true, false, false, false, false);
        if (adminUserDetailList != null && !adminUserDetailList.isEmpty()) {
            List<Long> adminList = new ArrayList<Long>();
            for (User user : adminUserDetailList) {
                if (user.getId() != loginDataBean.getId()) {
                    adminList.add(user.getId());
                }
            }
            this.sendMail(adminList, officialBreak, emailFormat);
        }
    }

    private void sendMail(List<Long> listOfUserIdForSendEmail, OfficialBreak officialBreak, String mailFormat) throws UserManagementException {
        if (listOfUserIdForSendEmail != null && !listOfUserIdForSendEmail.isEmpty()) {
            List<User> usersList = userService.retrieveUsersByUserList(listOfUserIdForSendEmail, null, null, null, true);
            if (usersList != null && !usersList.isEmpty()) {
                String[] to = new String[usersList.size()];
                int i = 0;
                for (User user : usersList) {
                    to[i++] = user.getContact().getEmailAddress();
                }
                EmailFormat emailFormat = emailFormatService.retriveEmailByFormateName(mailFormat);
                if (emailFormat != null) {
                    String emailBody = emailFormat.getBody();
                    User user = userService.getUserbyId(officialBreak.getUserId(), false, false, true, false);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_FROM_DATE, sdf.format(officialBreak.getFromDate()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_CURRENT_DATE, sdf.format(new Date()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_TO_DATE, sdf.format(officialBreak.getToDate()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_END_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_START_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_LATE_DURATION, "");
            
                    if (mailFormat.equals(SystemConstantUtil.EMAIL_FORMAT_APPLY_LEAVE)) {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        String approveButton = "<br/> <a href=\"http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort()+ httpServletRequest.getContextPath() + "/officialBreakResponse?officialBreakId=" + officialBreak.getOfficialBreakId() + "&type=" + SystemConstantUtil.APPROVE + "\"><button> Approve OfficialBreak</button></a>";
                        String disapproveButton = " <a href=\"http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort()+ httpServletRequest.getContextPath() + "/officialBreakResponse?officialBreakId=" + officialBreak.getOfficialBreakId() + "&type=" + SystemConstantUtil.DISAPPROVE + "\"> Disapprove OfficialBreak</a>";
                        emailBody += approveButton + " " + disapproveButton;

                    }
                    userTransformerBean.sendMail(emailFormat.getSubject(), emailBody, to, true, null);
                }
            }
        }
    }
}