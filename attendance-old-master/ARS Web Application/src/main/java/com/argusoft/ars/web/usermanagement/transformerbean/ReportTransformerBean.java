/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.*;
import com.argusoft.ars.model.*;
import com.argusoft.ars.web.leavemanagement.transformerbean.LeaveTransformerBean;
import com.argusoft.ars.web.personal.databean.AttendanceRegisterDataBean;
import com.argusoft.ars.web.personal.databean.JobBreakDataBean;
import com.argusoft.ars.web.personal.transformerbean.PersonalTransformerBean;
import com.argusoft.ars.web.usermanagement.databean.*;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.core.SystemConfigurationService;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.SystemConfiguration;
import com.argusoft.usermanagement.common.model.User;
import com.argusoft.usermanagement.common.model.UserContact;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.CollectionUtils;

/**
 * Transformer for Reports
 *
 * @author sudhir
 */
@ManagedBean(name = "reportTransformerBean")
@RequestScoped
public class ReportTransformerBean {

    //  Core properties
    @ManagedProperty(value = "#{cardLogService}")
    private CardLogService cardLogService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{holidayService}")
    private HolidayService holidayService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{personalTransformerBean}")
    private PersonalTransformerBean personalTransformerBean;
    @ManagedProperty(value = "#{jobBreakDataBean}")
    private JobBreakDataBean jobBreakDataBean;
    @ManagedProperty(value = "#{leaveTransformerBean}")
    private LeaveTransformerBean leaveTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    @ManagedProperty(value = "#{shiftService}")
    private ShiftService shiftService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{systemConfigurationService}")
    private SystemConfigurationService systemConfigurationService;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    @ManagedProperty(value = "#{emailFormatService}")
    private EmailFormatService emailFormatService;
    //  Other properties
    //private static final Logger log = Logger.getLogger(ReportTransformerBean.class);

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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CardLogService getCardLogService() {
        return cardLogService;
    }

    public void setCardLogService(CardLogService cardLogService) {
        this.cardLogService = cardLogService;
    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public JobBreakDataBean getJobBreakDataBean() {
        return jobBreakDataBean;
    }

    public void setJobBreakDataBean(JobBreakDataBean jobBreakDataBean) {
        this.jobBreakDataBean = jobBreakDataBean;
    }

    public PersonalTransformerBean getPersonalTransformerBean() {
        return personalTransformerBean;
    }

    public void setPersonalTransformerBean(PersonalTransformerBean personalTransformerBean) {
        this.personalTransformerBean = personalTransformerBean;
    }

    public LeaveTransformerBean getLeaveTransformerBean() {
        return leaveTransformerBean;
    }

    public void setLeaveTransformerBean(LeaveTransformerBean leaveTransformerBean) {
        this.leaveTransformerBean = leaveTransformerBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    public LeaveService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
    }

    public ShiftService getShiftService() {
        return shiftService;
    }

    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    public SystemConfigurationService getSystemConfigurationService() {
        return systemConfigurationService;
    }

    public void setSystemConfigurationService(SystemConfigurationService systemConfigurationService) {
        this.systemConfigurationService = systemConfigurationService;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    /**
     * Method to convert CardLog Model to CardLogDataBean.
     *
     * @param cardLog
     * @param cardLogDataBean
     * @return
     */
    private CardLogDataBean convertCardLogModelTocardLogDataBean(CardLog cardLog, CardLogDataBean cardLogDataBean, Boolean isGetUserDetail) throws UserManagementException {
        if (isGetUserDetail != null && isGetUserDetail == true) {
            SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(cardLog.getUserId());
            if (systemUserDetail != null) {
                cardLogDataBean.setEmpId(systemUserDetail.getEmpId());
            }

            UserContact userContect = userService.getUserContactById(cardLog.getUserId());
            if (userContect != null) {
                cardLogDataBean.setUserName(userContect.getFirstName() + " " + userContect.getLastName());
                cardLogDataBean.setMobileNo(userContect.getMobileNumber());
            } else {
                cardLogDataBean.setUserName("System");
            }
        }
        cardLogDataBean.setCardEntryReason(cardLog.getCardEntryReason());
        cardLogDataBean.setCardPunchingTime(cardLog.getCardPunchingTime());
        cardLogDataBean.setCardId(cardLog.getCardEnrollNumber());
        short a = cardLog.getCardEntryType();
        if (a == 0) {
            cardLogDataBean.setCardEntryType(SystemConstantUtil.ENTRY_IN);
        } else {
            cardLogDataBean.setCardEntryType(SystemConstantUtil.ENTRY_OUT);
        }
        return cardLogDataBean;
    }

    /**
     * Method to retrieve all CardLogs
     *
     * @return
     */
    public List<CardLogDataBean> retrieveCardLog(Long id, Date fromDate, Date toDate) throws UserManagementException {
        List<CardLogDataBean> cardLogDataBeanList = new ArrayList<CardLogDataBean>();
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(fromDate);
        calDate.set(Calendar.HOUR_OF_DAY, 00);
        calDate.set(Calendar.MINUTE, 00);
        calDate.set(Calendar.SECOND, 00);
        calDate.set(Calendar.MILLISECOND, 00);
        fromDate = calDate.getTime();
        calDate.setTime(toDate);
        calDate.set(Calendar.HOUR_OF_DAY, 23);
        calDate.set(Calendar.MINUTE, 59);
        calDate.set(Calendar.SECOND, 59);
        toDate = calDate.getTime();
        List<CardLog> cardLogsList = cardLogService.retrieveCardLogBetweenDates(id, fromDate, toDate);
        if (cardLogsList != null && !cardLogsList.isEmpty()) {
            Collections.sort(cardLogsList, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    CardLog cardLog = (CardLog) o1;
                    CardLog cardLogTemp = (CardLog) o2;
                    return cardLog.getUserId().compareTo(cardLogTemp.getUserId());
                }
            });
            SystemUserDetail systemUserDetail = null;
            UserContact userContect = null;
            Long tempId = cardLogsList.get(0).getUserId();
            if (tempId != 0) {
                systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(tempId);
                userContect = userService.getUserContactById(tempId);
            }
            for (CardLog cardLog : cardLogsList) {
                if (tempId != cardLog.getUserId()) {
                    if (cardLog.getUserId() != 0) {
                        systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(cardLog.getUserId());
                        userContect = userService.getUserContactById(cardLog.getUserId());
                    }
                    tempId = cardLog.getUserId();
                }
                CardLogDataBean cardLogDataBean = convertCardLogModelTocardLogDataBean(cardLog, new CardLogDataBean(), false);
                if (systemUserDetail != null) {
                    cardLogDataBean.setEmpId(systemUserDetail.getEmpId());
                }
                if (userContect != null) {
                    cardLogDataBean.setUserName(userContect.getFirstName() + " " + userContect.getLastName());
                    cardLogDataBean.setMobileNo(userContect.getMobileNumber());
                } else {
                    cardLogDataBean.setUserName("System");
                }
                cardLogDataBeanList.add(cardLogDataBean);
            }
        } else {
            System.out.println("cardloglist not found...");
        }
        return cardLogDataBeanList;
    }

    public List<UserDataBean> retrieveUserList() throws UserManagementException {
        List<UserDataBean> userDataBeanList = new ArrayList<UserDataBean>();
        List<User> userList = userService.getAllActiveUsers();

        for (User user : userList) {
            UserDataBean userDataBean = new UserDataBean();
            userDataBean.setId(user.getId());
            userDataBean.setFirstName(user.getContact().getFirstName());
            userDataBean.setLastName(user.getContact().getLastName());
            userDataBeanList.add(userDataBean);
        }
        if (!userDataBeanList.isEmpty()) {
            Collections.sort(userDataBeanList, new Comparator<UserDataBean>() {
                @Override
                public int compare(UserDataBean o1, UserDataBean o2) {
                    if (o1.getFirstName() != null && o2.getFirstName() != null) {
                        return o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase());
                    } else {
                        return 0;
                    }
                }
            });
        }
        return userDataBeanList;
    }

    public Integer totalWorkingDays(Date fromDate, Date toDate) {
        if (toDate.getTime() > new Date().getTime()) {
            toDate = new Date();
        }
        List<Date> weekDays = SystemFunctionUtil.getWeekEndsDaysBetweenTwoDates(fromDate, toDate);
        List<Holiday> listOfHoliday = holidayService.retrieveHolidayBetweenDates(fromDate, toDate, true, false, weekDays);
        Integer totalDays = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
        if (weekDays != null && weekDays.size() > 0) {
            totalDays -= weekDays.size();
        }
        if (listOfHoliday != null && listOfHoliday.size() > 0) {
            totalDays -= listOfHoliday.size();
        }
        return totalDays + 1;
    }

    public List<CardLogDataBean> retrieveTodaysAttendanceCardLog() throws UserManagementException {
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.HOUR_OF_DAY, 00);
        calDate.set(Calendar.MINUTE, 00);
        calDate.set(Calendar.SECOND, 00);
        calDate.set(Calendar.MILLISECOND, 00);
        Date fromDate = calDate.getTime();
        Date toDate = new Date();
        List<CardLogDataBean> todaysAttendanceDataBeanList = new ArrayList<CardLogDataBean>();
        List<User> users = userService.getAllActiveUsers();
        for (User user : users) {
            SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(user.getId());

            CardLog cardLog;
            List<CardLog> cardLogs = cardLogService.retrieveLastPunchingTimeBetweenDate(user.getId(), fromDate, toDate);
            if (cardLogs != null && !cardLogs.isEmpty()) {
                cardLog = cardLogs.get(0);
            } else {
                cardLog = null;
            }

            CardLogDataBean cardLogDataBean = new CardLogDataBean();
            cardLogDataBean.setUserId(user.getUserId());
            cardLogDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            cardLogDataBean.setMobileNo(user.getContact().getMobileNumber());
            cardLogDataBean.setEmpId(systemUserDetail.getEmpId());
            if (cardLog != null) {
                cardLogDataBean.setCardPunchingTime(cardLog.getCardPunchingTime());
                cardLogDataBean.setCardEntryType(cardLog.getCardEntryType() == 0 ? SystemConstantUtil.ENTRY_IN : SystemConstantUtil.ENTRY_OUT);
                cardLogDataBean.setAttendanceStatus("Present");
            } else {
                cardLogDataBean.setAttendanceStatus("Absent");
                cardLogDataBean.setCardEntryType("-");
            }
            todaysAttendanceDataBeanList.add(cardLogDataBean);
        }
        return todaysAttendanceDataBeanList;

    }

    /**
     * *
     * Retrieving Present Days of an employee
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     * @throws UserManagementException
     */
    public List<CardLogDataBean> retrievePresentDays(Long id, Date fromDate, Date toDate) throws UserManagementException {
        List<CardLogDataBean> cardLogDataBeans = new ArrayList<CardLogDataBean>();
        Integer noOfDays = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.setTime(fromDate);
        endTime.setTime(fromDate);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        endTime.set(Calendar.SECOND, 59);
        for (int i = 0; i < noOfDays; i++) {
            String weekend = null;
            if (isHoliday(startTime.getTime())) {
                if (isCasualHoliday(startTime.getTime())) {
                    weekend = "(Holiday)";
                } else {
                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
                    weekend = "(" + simpleDateformat.format(startTime.getTime()).toUpperCase() + ")";
                }
            }
            List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(id, startTime.getTime(), endTime.getTime(), true);
            if (cardLogs != null && cardLogs.size() == 1) {
                CardLog cardLog = cardLogs.get(0);
                CardLogDataBean cardLogDataBean = new CardLogDataBean();
                cardLogDataBean.setCardPunchingTime(cardLog.getCardPunchingTime());
                cardLogDataBean.setWeekend(weekend);
                System.out.println("=====>Entry Type" + cardLog.getCardEntryType());
                if (cardLog.getCardEntryType() == 0) {
                    cardLogDataBean.setCardEntryType(SystemConstantUtil.ENTRY_IN);
                } else {
                    cardLogDataBean.setCardEntryType(SystemConstantUtil.ENTRY_OUT);
                }
                cardLogDataBeans.add(cardLogDataBean);
            }
            startTime.add(Calendar.DAY_OF_MONTH, 1);
            endTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        return cardLogDataBeans;
    }

    public List<CardLogDataBean> retrieveLateDays(Long id, Date fromDate, Date toDate) {
        List<CardLogDataBean> cardLogDataBeans = new ArrayList<CardLogDataBean>();
        Integer allowableLateMinutes = this.getAllowableLateMinites();
        Integer noOfDays = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.setTime(fromDate);
        endTime.setTime(fromDate);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        endTime.set(Calendar.SECOND, 59);
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(id);
        DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
        if (systemUserDetail.getShiftId() != null) {
            if (allowableLateMinutes != null) {
                Shift shift = systemUserDetail.getShiftId();
                Calendar shiftStartTimeCal = Calendar.getInstance();
                shiftStartTimeCal.setTime(shift.getShiftStartTime());
                shiftStartTimeCal.set(Calendar.MINUTE, shiftStartTimeCal.get(Calendar.MINUTE) + allowableLateMinutes);
                shift.setShiftStartTime(shiftStartTimeCal.getTime());
                systemUserDetail.setShiftId(shift);
            }
        }
        for (int i = 0; i < noOfDays; i++) {
            Boolean isOnLeave = false;
            if (!isHoliday(startTime.getTime())) {
                List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(id, startTime.getTime(), endTime.getTime(), true);
                if (cardLogs != null && cardLogs.size() == 1) {
                    CardLog cardLog = cardLogs.get(0);
                    if (f.format(cardLog.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) > 0) {
                        Leave leave = leaveService.retrieveLeaveByDate(id, startTime.getTime());
                        if (leave != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            if (leave.getApprovalStatus() != null && leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                                if ((!"FD".equals(leave.getFromDateLeaveType()) && sdf.format(leave.getFromDate()).equals(sdf.format(startTime.getTime()))) || (!"FD".equals(leave.getToDateLeaveType()) && sdf.format(leave.getToDate()).equals(sdf.format(startTime.getTime())))) {
                                    isOnLeave = true;
                                }
                            }
                        }
                        if (!isOnLeave) {
                            CardLogDataBean cardLogDataBean = new CardLogDataBean();
                            cardLogDataBean.setCardPunchingTime(cardLog.getCardPunchingTime());
                            cardLogDataBeans.add(cardLogDataBean);
                        }
                    }
                }
            }
            startTime.add(Calendar.DAY_OF_MONTH, 1);
            endTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        return cardLogDataBeans;
    }

    /**
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     * @throws UserManagementException
     */
    public List<CardLogDataBean> retrieveAbsentDays(Long id, Date fromDate, Date toDate) throws UserManagementException {
        List<CardLogDataBean> cardLogDataBeans = new ArrayList<CardLogDataBean>();
        Integer noOfDays = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.setTime(fromDate);
        endTime.setTime(fromDate);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        endTime.set(Calendar.SECOND, 59);
        for (int i = 0; i < noOfDays; i++) {
            if (!isHoliday(startTime.getTime())) {
                Boolean isOnLeave = false, isOnHalfLeave = false;
                List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(id, startTime.getTime(), endTime.getTime(), true);
                if (cardLogs == null) {
                    Leave leave = leaveService.retrieveLeaveByDate(id, startTime.getTime());
                    if (leave != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        if (leave.getApprovalStatus() != null && leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                            isOnLeave = true;
                            if ((!"FD".equals(leave.getFromDateLeaveType()) && sdf.format(leave.getFromDate()).equals(sdf.format(startTime.getTime()))) || (!"FD".equals(leave.getToDateLeaveType()) && sdf.format(leave.getToDate()).equals(sdf.format(startTime.getTime())))) {
                                isOnHalfLeave = true;
                                isOnLeave = false;
                            }
                        }
                    }
                    if (!isOnLeave) {
                        CardLogDataBean cardLogDataBean = new CardLogDataBean();
                        cardLogDataBean.setCardPunchingTime(startTime.getTime());
                        if (isOnHalfLeave) {
                            cardLogDataBean.setWeekend("(Half Day)");
                        }
                        cardLogDataBeans.add(cardLogDataBean);
                    }
                }
            }
            startTime.add(Calendar.DAY_OF_MONTH, 1);
            endTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        return cardLogDataBeans;
    }

    /**
     *
     * @param consecutiveMailDataBean
     * @return
     * @throws UserManagementException
     */
    public List<ConsecutiveMailDataBean> retrieveConsecutiveMailList(ConsecutiveMailDataBean consecutiveMailDataBean) throws UserManagementException {
        List<ConsecutiveMailDataBean> consecutiveMailDataBeanList = new ArrayList<ConsecutiveMailDataBean>();
        Long id = consecutiveMailDataBean.getUserId();
        Date fromDate = consecutiveMailDataBean.getFromDate();
        Date toDate = consecutiveMailDataBean.getToDate();
        List<CardLogDataBean> presentDayList = retrievePresentDays(id, fromDate, toDate);
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(id);

//        Integer allowableLateMinutes=systemConfigurationService.retrieveSystemConfigurationsBasedOnLikeKeySearch(SystemConstantUtil.ALLOWABLE_LATE_MINIUTE);
        Shift shift = shiftService.retrieveShiftById(systemUserDetail.getShiftId().getShiftId());
//        Date shiftStartTime = shift.getShiftStartTime();
        Boolean isOnTime = false, isLate = false;
        DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
        if (shift != null && shift.getShiftStartTime() != null) {
            if (consecutiveMailDataBean.getId() == 1) {

                if (presentDayList != null && !presentDayList.isEmpty()) {
                    for (CardLogDataBean cardLogDataBean : presentDayList) {
                        if (cardLogDataBean != null) {
                            if (cardLogDataBean.getCardEntryType().equals(SystemConstantUtil.ENTRY_IN) && !(f.format(cardLogDataBean.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) < 0)) {
                                isLate = true;
                            } else {
                                isLate = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (consecutiveMailDataBean.getId() == 2) {
                if (presentDayList != null && !presentDayList.isEmpty()) {
                    for (CardLogDataBean cardLogDataBean : presentDayList) {
                        if (cardLogDataBean != null) {
                            if (cardLogDataBean.getCardEntryType().equals(SystemConstantUtil.ENTRY_IN) && (f.format(cardLogDataBean.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) < 0)) {
                                isOnTime = true;
                            } else {
                                isOnTime = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (consecutiveMailDataBean.getId() == 0) {
                if (presentDayList != null && !presentDayList.isEmpty()) {
                    for (CardLogDataBean cardLogDataBean : presentDayList) {
                        if (cardLogDataBean != null) {
                            if (cardLogDataBean.getCardEntryType().equals(SystemConstantUtil.ENTRY_IN) && (f.format(cardLogDataBean.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) < 0)) {
                                isOnTime = true;
                            } else {
                                isOnTime = false;
                                break;
                            }
                        }
                    }
                    if (!isOnTime) {
                        for (CardLogDataBean cardLogDataBean : presentDayList) {
                            if (cardLogDataBean != null) {
                                if (cardLogDataBean.getCardEntryType().equals(SystemConstantUtil.ENTRY_IN) && !(f.format(cardLogDataBean.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) < 0)) {
                                    isLate = true;
                                } else {
                                    isLate = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (isLate == true || isOnTime == true) {
            ConsecutiveMailDataBean consecutiveMailDataBean1 = new ConsecutiveMailDataBean();
            UserContact userContact = userService.getUserContactById(id);
            consecutiveMailDataBean1.setId(id);
            consecutiveMailDataBean1.setFromDate(fromDate);
            consecutiveMailDataBean1.setToDate(toDate);
            consecutiveMailDataBean1.setEmployeeName(userContact.getFirstName() + " " + userContact.getLastName());
            consecutiveMailDataBean1.setEmailId(userContact.getEmailAddress());
            if (isLate == true) {
                consecutiveMailDataBean1.setMailSendFor(SystemConstantUtil.MAIL_FOR_LATE);
            } else {
                consecutiveMailDataBean1.setMailSendFor(SystemConstantUtil.MAIL_FOR_ON_TIME);
            }
            consecutiveMailDataBeanList.add(consecutiveMailDataBean1);
        }
        if (consecutiveMailDataBeanList != null) {
            return consecutiveMailDataBeanList;
        } else {
            return null;
        }
    }

    public List<CardLogDataBean> retrieveEmployeeLastPunchingCardLogDetail() throws UserManagementException {
        Map<Long, User> userIdAndUserMap = new HashMap<Long, User>();
        Map<Long, SystemUserDetail> userIdAndSystemUserDetail = new HashMap<Long, SystemUserDetail>();
        List<Long> userIds = new ArrayList<Long>();
        List<User> users = userService.getAllActiveUsers();
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                userIds.add(user.getId());
                userIdAndUserMap.put(user.getId(), user);
            }
        }
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailByUserIds(userIds, Boolean.TRUE);
        if (systemUserDetails != null && !systemUserDetails.isEmpty()) {
            for (SystemUserDetail systemUserDetail : systemUserDetails) {
                userIdAndSystemUserDetail.put(systemUserDetail.getUserId(), systemUserDetail);
            }
        }
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH) - 1);
        Date fromDate = calDate.getTime();
        Date toDate = new Date();
        List<CardLog> cardLogs = cardLogService.retrieveCardLogBetweenDates(null, fromDate, toDate);
        
        List<CardLogDataBean> cardLogDataBeans = new ArrayList<CardLogDataBean>();
        Date curTime = new Date();
        if (cardLogs != null && !cardLogs.isEmpty()) {
            Map<Long,CardLog> clsMap = new HashMap<Long, CardLog>();
            for (CardLog cardLog : cardLogs) {
                CardLog cl = clsMap.get(cardLog.getUserId());
                if(cl == null){
                    clsMap.put(cardLog.getUserId(), cardLog);
                }else if(cardLog.getCardPunchingTime().compareTo(cl.getCardPunchingTime())>0){
                    clsMap.put(cardLog.getUserId(), cardLog);
                }
            }
            for (Map.Entry<Long, CardLog> entry : clsMap.entrySet()) {
//                Long long1 = entry.getKey();
                CardLog cardLog = entry.getValue();
                CardLogDataBean cardLogDataBean = this.convertCardLogModelTocardLogDataBean(cardLog, new CardLogDataBean(), false);
                SystemUserDetail systemUserDetail = userIdAndSystemUserDetail.get(cardLog.getUserId());
                if (systemUserDetail != null) {
                    cardLogDataBean.setEmpId(systemUserDetail.getEmpId());
                }

                User u = userIdAndUserMap.get(cardLog.getUserId());
                if (u != null) {
                    UserContact userContect = u.getContact();
                    if (userContect != null) {
                        cardLogDataBean.setUserName(userContect.getFirstName() + " " + userContect.getLastName());
                        cardLogDataBean.setMobileNo(userContect.getMobileNumber());
                    } else {
                        cardLogDataBean.setUserName("System");
                    }
                    if (!cardLogDataBean.getUserName().equals("System")) {
                        cardLogDataBean.setDuration(SystemFunctionUtil.retrieveHoursAndMinite(curTime.getTime() - cardLog.getCardPunchingTime().getTime()));
                        cardLogDataBeans.add(cardLogDataBean);
                    }
                }
            }
//            for (CardLog cardLog : cardLogs) {
//                
//            }
            return cardLogDataBeans;
        }
        return null;
    }

    public Integer getAllowableLateMinites() {
        SystemConfiguration value = systemConfigurationService.retrieveSystemConfigurationByKey("allowableLateMinutes");
        if (value != null) {
            return Integer.parseInt(value.getKeyValue());
        }
        return 0;
    }

    public List<AttendanceRegisterDataBean> retrieveAttendanceRegisterDetailList(Date fromDate) throws UserManagementException {
        List<AttendanceRegisterDataBean> attendanceRegisterDataBeans = new ArrayList<AttendanceRegisterDataBean>();
        Integer allowableLateMinutes = this.getAllowableLateMinites();
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(fromDate);
        toCal.set(Calendar.DAY_OF_MONTH, toCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Integer noOfDays = (int) ((toCal.getTime().getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        List<User> users = userService.getAllUsers();
        {
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
                    int presentDay = 0, absentday = 0, lateDays = 0;
                    Calendar fromCal = Calendar.getInstance();
                    fromCal.setTime(fromDate);
                    fromCal.set(Calendar.HOUR_OF_DAY, 0);
                    fromCal.set(Calendar.MINUTE, 0);
                    fromCal.set(Calendar.SECOND, 0);
                    Calendar endTimeCal = Calendar.getInstance();
                    endTimeCal.setTime(fromDate);
                    endTimeCal.set(Calendar.HOUR_OF_DAY, 23);
                    endTimeCal.set(Calendar.MINUTE, 59);
                    endTimeCal.set(Calendar.SECOND, 59);
                    SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(user.getId());
                    if (systemUserDetail.getShiftId() != null) {
                        if (allowableLateMinutes != null) {
                            Shift shift = systemUserDetail.getShiftId();
                            Calendar shiftStartTimeCal = Calendar.getInstance();
                            shiftStartTimeCal.setTime(shift.getShiftStartTime());
                            shiftStartTimeCal.set(Calendar.MINUTE, shiftStartTimeCal.get(Calendar.MINUTE) + allowableLateMinutes);
                            shift.setShiftStartTime(shiftStartTimeCal.getTime());
                            systemUserDetail.setShiftId(shift);
                        }
                    }
                    String day[] = new String[noOfDays];

                    if (systemUserDetail.getJoinDate() != null && systemUserDetail.getJoinDate().getTime() <= toCal.getTime().getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                        for (int i = 0; i < noOfDays; i++) {
                            if (systemUserDetail.getJoinDate().getTime() <= fromCal.getTime().getTime() && fromCal.getTime().getTime() <= new Date().getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                                Boolean isHoliday = this.isHoliday(fromCal.getTime());
                                List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(user.getId(), fromCal.getTime(), endTimeCal.getTime(), true);
                                if (cardLogs != null && cardLogs.size() == 1) {
                                    CardLog cardLog = cardLogs.get(0);
                                    if (systemUserDetail.getShiftId() != null) {
                                        DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
                                        if (f.format(cardLog.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) < 0) {
                                            day[i] = "P";
                                            if (!isHoliday) {
                                                presentDay++;
                                            }
                                        } else {
                                            day[i] = "P*";
                                            if (!isHoliday) {
                                                lateDays++;
                                                presentDay++;
                                            }
                                        }
                                    }
                                    if (!"System".equalsIgnoreCase(cardLog.getCardEntryReason())) {
                                        day[i] = "M";
                                    }
                                } else if (!isHoliday) {
//                                    Leave leave=leaveService.retrieveLeaveByDate(fromCal.getTime());
                                    day[i] = "a";
                                    absentday++;
                                } else {
                                    day[i] = "";
                                }
                                if (!isHoliday) {
                                    Leave leave = leaveService.retrieveLeaveByDate(user.getId(), fromCal.getTime());
                                    if (leave != null) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                        if (leave.getApprovalStatus() != null && leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                                            if ((!"FD".equals(leave.getFromDateLeaveType()) && sdf.format(leave.getFromDate()).equals(sdf.format(fromCal.getTime()))) || (!"FD".equals(leave.getToDateLeaveType()) && sdf.format(leave.getToDate()).equals(sdf.format(fromCal.getTime())))) {
                                                day[i] = "HL";
                                            } else {
                                                if ("a".equals(day[i])) {
                                                    absentday--;
                                                    day[i] = "L";
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                day[i] = "-";
                            }
                            fromCal.add(Calendar.DAY_OF_MONTH, +1);
                            endTimeCal.add(Calendar.DAY_OF_MONTH, +1);
                        }
                        AttendanceRegisterDataBean attendanceRegisterDataBean = new AttendanceRegisterDataBean();
                        attendanceRegisterDataBean.setEmpId(systemUserDetail.getEmpId());
                        attendanceRegisterDataBean.setEmpName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                        attendanceRegisterDataBean.setAbsentDays(absentday);
                        attendanceRegisterDataBean.setPresentDays(presentDay);
                        attendanceRegisterDataBean.setLateDays(lateDays);
                        attendanceRegisterDataBean.setD1(day[0]);
                        attendanceRegisterDataBean.setD2(day[1]);
                        attendanceRegisterDataBean.setD3(day[2]);
                        attendanceRegisterDataBean.setD4(day[3]);
                        attendanceRegisterDataBean.setD5(day[4]);
                        attendanceRegisterDataBean.setD6(day[5]);
                        attendanceRegisterDataBean.setD7(day[6]);
                        attendanceRegisterDataBean.setD8(day[7]);
                        attendanceRegisterDataBean.setD9(day[8]);
                        attendanceRegisterDataBean.setD10(day[9]);
                        attendanceRegisterDataBean.setD11(day[10]);
                        attendanceRegisterDataBean.setD12(day[11]);
                        attendanceRegisterDataBean.setD13(day[12]);
                        attendanceRegisterDataBean.setD14(day[13]);
                        attendanceRegisterDataBean.setD15(day[14]);
                        attendanceRegisterDataBean.setD16(day[15]);
                        attendanceRegisterDataBean.setD17(day[16]);
                        attendanceRegisterDataBean.setD18(day[17]);
                        attendanceRegisterDataBean.setD19(day[18]);
                        attendanceRegisterDataBean.setD20(day[19]);
                        attendanceRegisterDataBean.setD21(day[20]);
                        attendanceRegisterDataBean.setD22(day[21]);
                        attendanceRegisterDataBean.setD23(day[22]);
                        attendanceRegisterDataBean.setD24(day[23]);
                        attendanceRegisterDataBean.setD25(day[24]);
                        attendanceRegisterDataBean.setD26(day[25]);
                        attendanceRegisterDataBean.setD27(day[26]);
                        attendanceRegisterDataBean.setD28(day[27]);
                        if (noOfDays > 28) {
                            attendanceRegisterDataBean.setD29(day[28]);
                        }
                        if (noOfDays > 29) {
                            attendanceRegisterDataBean.setD30(day[29]);
                        }
                        if (noOfDays > 30) {
                            attendanceRegisterDataBean.setD31(day[30]);
                        }
                        attendanceRegisterDataBeans.add(attendanceRegisterDataBean);
                    }

                }
            }
        }
//        for (AttendanceRegisterDataBean attendanceRegisterDataBean : attendanceRegisterDataBeans) {
//            System.out.println(attendanceRegisterDataBean.getEmpName() + " " + attendanceRegisterDataBean.getD1() + " " + attendanceRegisterDataBean.getD2() + " " + attendanceRegisterDataBean.getD3() + " " + attendanceRegisterDataBean.getD4() + " " + attendanceRegisterDataBean.getD5() + " " + attendanceRegisterDataBean.getD6() + " " + attendanceRegisterDataBean.getD7() + " " + attendanceRegisterDataBean.getD8() + " " + attendanceRegisterDataBean.getD9() + " " + attendanceRegisterDataBean.getD10() + " " + attendanceRegisterDataBean.getD11() + " " + attendanceRegisterDataBean.getD12() + " " + attendanceRegisterDataBean.getD13() + " " + attendanceRegisterDataBean.getD14() + " " + attendanceRegisterDataBean.getD15() + " " + attendanceRegisterDataBean.getD16() + " " + attendanceRegisterDataBean.getD17() + " " + attendanceRegisterDataBean.getD18() + " " + attendanceRegisterDataBean.getD19() + " " + attendanceRegisterDataBean.getD20() + " " + attendanceRegisterDataBean.getD21() + " " + attendanceRegisterDataBean.getD22() + " " + attendanceRegisterDataBean.getD23() + " " + attendanceRegisterDataBean.getD24() + " " + attendanceRegisterDataBean.getD25() + " " + attendanceRegisterDataBean.getD26() + " " + attendanceRegisterDataBean.getD27() + " " + attendanceRegisterDataBean.getD28() + " " + attendanceRegisterDataBean.getD29() + " " + attendanceRegisterDataBean.getD30() + " " + attendanceRegisterDataBean.getD31() + " " + attendanceRegisterDataBean.getPresentDays() + " " + attendanceRegisterDataBean.getLateDays() + " " + attendanceRegisterDataBean.getAbsentDays());
//        }
        return attendanceRegisterDataBeans;
    }

    public List<AttendanceRegisterDataBean> retrieveAttendanceRegisterDetailList1(Date fromDate) throws UserManagementException {
        Date shiftStartTime = null;
        DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat dateMonthFormat = new SimpleDateFormat("dd/MM/yyyy");
        fromDate = SystemFunctionUtil.convertToFirstDayOfTheMonth(fromDate);
        Date toDate = SystemFunctionUtil.convertToLastDayOfTheMonth(fromDate);
        if (toDate.compareTo(new Date()) > 0) {
            toDate = new Date();
        }
        List<AttendanceRegisterDataBean> attendanceRegisterDataBeans = new ArrayList<AttendanceRegisterDataBean>();
        Integer allowableLateMinutes = this.getAllowableLateMinites();
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(fromDate);
        toCal.set(Calendar.DAY_OF_MONTH, toCal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Integer noOfDays = SystemFunctionUtil.get)
        Integer noOfDays = (int) ((toCal.getTime().getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        Integer noOfDays1 = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        Map<String, Boolean> holidayMap = new HashMap<String, Boolean>();
        List<Holiday> holidays = holidayService.retrieveHolidayBetweenDates(fromDate, toDate, true, false, null);
        for (int i = 0; i < noOfDays1; i++) {

            Date holidayCheckDate = SystemFunctionUtil.getDateAfterNoOfDays(fromDate, i);
            String holiDayDateFormate = dateMonthFormat.format(holidayCheckDate);
            holidayMap.put(holiDayDateFormate, Boolean.FALSE);
            Calendar c = Calendar.getInstance();
            c.setTime(holidayCheckDate);
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                holidayMap.put(holiDayDateFormate, Boolean.TRUE);
            } else {
                if (holidays != null && !holidays.isEmpty()) {
                    for (Holiday holiday : holidays) {
                        if (holiDayDateFormate.equals(dateMonthFormat.format(holiday.getHolidayDate()))) {
                            holidayMap.put(holiDayDateFormate, Boolean.TRUE);
                        }
                    }
                }
            }
        }
        List<User> users = userService.getAllUsers();

        {
            if (users != null && !users.isEmpty()) {
                List<Long> userIds = new ArrayList<Long>();
                for (User user : users) {
                    userIds.add(user.getId());
                }
                List<CardLog> cardLogs = cardLogService.retrieveCardLogBetweenDatesByUserIds(userIds, fromDate, toDate);
                Map<Long, Map<Integer, Date>> cardLogMap = new HashMap<Long, Map<Integer, Date>>();
                Map<Long, Map<Integer, String>> cardLogEntryMap = new HashMap<Long, Map<Integer, String>>();

                for (CardLog cardLog : cardLogs) {
                    Map<Integer, Date> dateAndCardPunchMap = cardLogMap.get(cardLog.getUserId());
                    Map<Integer, String> dateAndCardPunchTypeMap = cardLogEntryMap.get(cardLog.getUserId());
                    if (dateAndCardPunchMap == null) {
                        dateAndCardPunchMap = new HashMap<Integer, Date>();
                        dateAndCardPunchTypeMap = new HashMap<Integer, String>();
                        cardLogEntryMap.put(cardLog.getUserId(), dateAndCardPunchTypeMap);
                        cardLogMap.put(cardLog.getUserId(), dateAndCardPunchMap);
                    }
//                   
                    Date firstCardPunchDate = dateAndCardPunchMap.get(Integer.parseInt(dateFormat.format(cardLog.getCardPunchingTime())));
                    if (firstCardPunchDate == null) {
                        dateAndCardPunchTypeMap.put(Integer.parseInt(dateFormat.format(cardLog.getCardPunchingTime())), cardLog.getCardEntryReason());
                        dateAndCardPunchMap.put(Integer.parseInt(dateFormat.format(cardLog.getCardPunchingTime())), cardLog.getCardPunchingTime());
                    }
                }

                List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailByUserIds(userIds, null);
                Map<Long, SystemUserDetail> systemUserDetailAndUserIdMap = new HashMap<Long, SystemUserDetail>();
                if (systemUserDetails != null && !systemUserDetails.isEmpty()) {
                    for (SystemUserDetail systemUserDetail : systemUserDetails) {
                        systemUserDetailAndUserIdMap.put(systemUserDetail.getUserId(), systemUserDetail);
                    }
                }
                for (User user : users) {
                    Map<Integer, Date> dateAndCardPunchMap = cardLogMap.get(user.getId());
                    Map<Integer, String> dateAndCardPunchTypeMap = cardLogEntryMap.get(user.getId());
                    if (dateAndCardPunchMap == null) {
                        dateAndCardPunchTypeMap = new HashMap<Integer, String>();
                        dateAndCardPunchMap = new HashMap<Integer, Date>();
                    }
                    int presentDay = 0, absentday = 0, lateDays = 0;
                    Calendar fromCal = Calendar.getInstance();
                    fromCal.setTime(fromDate);
                    fromCal.set(Calendar.HOUR_OF_DAY, 0);
                    fromCal.set(Calendar.MINUTE, 0);
                    fromCal.set(Calendar.SECOND, 0);
                    Calendar endTimeCal = Calendar.getInstance();
                    endTimeCal.setTime(fromDate);
                    endTimeCal.set(Calendar.HOUR_OF_DAY, 23);
                    endTimeCal.set(Calendar.MINUTE, 59);
                    endTimeCal.set(Calendar.SECOND, 59);
                    SystemUserDetail systemUserDetail = systemUserDetailAndUserIdMap.get(user.getId());
                    if (systemUserDetail.getShiftId() != null && allowableLateMinutes != null) {
                        Shift shift = systemUserDetail.getShiftId();
//                    if (systemUserDetail.getEmpId().equalsIgnoreCase("A155")) {
//                        System.out.println("Shift time:" + shift.getShiftStartTime());
//                    }
                        Calendar shiftStartTimeCal = Calendar.getInstance();
                        shiftStartTimeCal.setTime(shift.getShiftStartTime());
                        shiftStartTimeCal.set(Calendar.MINUTE, shiftStartTimeCal.get(Calendar.MINUTE) + allowableLateMinutes);
                        shiftStartTime = shiftStartTimeCal.getTime();
//                    shift.setShiftStartTime(shiftStartTimeCal.getTime());
//                    if (systemUserDetail.getEmpId().equalsIgnoreCase("A155")) {
//                        System.out.println("Now Shift time:" + shiftStartTime);
//                    }
//                    systemUserDetail.setShiftId(shift);
                    }
                    String day[] = new String[noOfDays];

                    if (systemUserDetail.getJoinDate() != null && systemUserDetail.getJoinDate().getTime() <= toCal.getTime().getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                        for (int i = 0; i < noOfDays; i++) {
                            if (systemUserDetail.getJoinDate().getTime() <= fromCal.getTime().getTime() && fromCal.getTime().getTime() <= new Date().getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                                Boolean isHoliday = holidayMap.get(dateMonthFormat.format(fromCal.getTime()));
                                if (isHoliday == null) {
                                    isHoliday = false;
                                }
                                Date cardPunchingTime = dateAndCardPunchMap.get(Integer.parseInt(dateFormat.format(fromCal.getTime())));
//                                List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(user.getId(), fromCal.getTime(), endTimeCal.getTime(), true);
                                if (cardPunchingTime != null) {

//                                    CardLog cardLog = cardLogs.get(0);
                                    if (systemUserDetail.getShiftId() != null) {

                                        if (f.format(cardPunchingTime).compareTo(f.format(shiftStartTime)) < 0) {
                                            day[i] = "P";
                                            if (!isHoliday) {
                                                presentDay++;
                                            }
                                        } else {
                                            day[i] = "P*";
                                            if (!isHoliday) {
                                                lateDays++;
                                                presentDay++;
                                            }
                                        }
                                    }
                                    if (!"System".equalsIgnoreCase(dateAndCardPunchTypeMap.get(Integer.parseInt(dateFormat.format(fromCal.getTime()))))) {
                                        day[i] = "M";
                                    }
                                } else if (!isHoliday) {
//                                    Leave leave=leaveService.retrieveLeaveByDate(fromCal.getTime());
                                    day[i] = "a";
                                    absentday++;
                                } else {
                                    day[i] = "";
                                }
                                if (!isHoliday) {
                                    Leave leave = leaveService.retrieveLeaveByDate(user.getId(), fromCal.getTime());
                                    if (leave != null) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                        if (leave.getApprovalStatus() != null && leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                                            if ((!"FD".equals(leave.getFromDateLeaveType()) && sdf.format(leave.getFromDate()).equals(sdf.format(fromCal.getTime()))) || (!"FD".equals(leave.getToDateLeaveType()) && sdf.format(leave.getToDate()).equals(sdf.format(fromCal.getTime())))) {
                                                day[i] = "HL";
                                            } else {
                                                if ("a".equals(day[i])) {
                                                    absentday--;
                                                    day[i] = "L";
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                day[i] = "-";
                            }
                            fromCal.add(Calendar.DAY_OF_MONTH, +1);
                            endTimeCal.add(Calendar.DAY_OF_MONTH, +1);
                        }
                        AttendanceRegisterDataBean attendanceRegisterDataBean = new AttendanceRegisterDataBean();
                        attendanceRegisterDataBean.setEmpId(systemUserDetail.getEmpId());
                        attendanceRegisterDataBean.setEmpName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                        attendanceRegisterDataBean.setAbsentDays(absentday);
                        attendanceRegisterDataBean.setPresentDays(presentDay);
                        attendanceRegisterDataBean.setLateDays(lateDays);
                        attendanceRegisterDataBean.setD1(day[0]);
                        attendanceRegisterDataBean.setD2(day[1]);
                        attendanceRegisterDataBean.setD3(day[2]);
                        attendanceRegisterDataBean.setD4(day[3]);
                        attendanceRegisterDataBean.setD5(day[4]);
                        attendanceRegisterDataBean.setD6(day[5]);
                        attendanceRegisterDataBean.setD7(day[6]);
                        attendanceRegisterDataBean.setD8(day[7]);
                        attendanceRegisterDataBean.setD9(day[8]);
                        attendanceRegisterDataBean.setD10(day[9]);
                        attendanceRegisterDataBean.setD11(day[10]);
                        attendanceRegisterDataBean.setD12(day[11]);
                        attendanceRegisterDataBean.setD13(day[12]);
                        attendanceRegisterDataBean.setD14(day[13]);
                        attendanceRegisterDataBean.setD15(day[14]);
                        attendanceRegisterDataBean.setD16(day[15]);
                        attendanceRegisterDataBean.setD17(day[16]);
                        attendanceRegisterDataBean.setD18(day[17]);
                        attendanceRegisterDataBean.setD19(day[18]);
                        attendanceRegisterDataBean.setD20(day[19]);
                        attendanceRegisterDataBean.setD21(day[20]);
                        attendanceRegisterDataBean.setD22(day[21]);
                        attendanceRegisterDataBean.setD23(day[22]);
                        attendanceRegisterDataBean.setD24(day[23]);
                        attendanceRegisterDataBean.setD25(day[24]);
                        attendanceRegisterDataBean.setD26(day[25]);
                        attendanceRegisterDataBean.setD27(day[26]);
                        attendanceRegisterDataBean.setD28(day[27]);
                        if (noOfDays > 28) {
                            attendanceRegisterDataBean.setD29(day[28]);
                        }
                        if (noOfDays > 29) {
                            attendanceRegisterDataBean.setD30(day[29]);
                        }
                        if (noOfDays > 30) {
                            attendanceRegisterDataBean.setD31(day[30]);
                        }
                        attendanceRegisterDataBeans.add(attendanceRegisterDataBean);
                    }

                }
            }
        }
//        for (AttendanceRegisterDataBean attendanceRegisterDataBean : attendanceRegisterDataBeans) {
//            System.out.println(attendanceRegisterDataBean.getEmpName() + " " + attendanceRegisterDataBean.getD1() + " " + attendanceRegisterDataBean.getD2() + " " + attendanceRegisterDataBean.getD3() + " " + attendanceRegisterDataBean.getD4() + " " + attendanceRegisterDataBean.getD5() + " " + attendanceRegisterDataBean.getD6() + " " + attendanceRegisterDataBean.getD7() + " " + attendanceRegisterDataBean.getD8() + " " + attendanceRegisterDataBean.getD9() + " " + attendanceRegisterDataBean.getD10() + " " + attendanceRegisterDataBean.getD11() + " " + attendanceRegisterDataBean.getD12() + " " + attendanceRegisterDataBean.getD13() + " " + attendanceRegisterDataBean.getD14() + " " + attendanceRegisterDataBean.getD15() + " " + attendanceRegisterDataBean.getD16() + " " + attendanceRegisterDataBean.getD17() + " " + attendanceRegisterDataBean.getD18() + " " + attendanceRegisterDataBean.getD19() + " " + attendanceRegisterDataBean.getD20() + " " + attendanceRegisterDataBean.getD21() + " " + attendanceRegisterDataBean.getD22() + " " + attendanceRegisterDataBean.getD23() + " " + attendanceRegisterDataBean.getD24() + " " + attendanceRegisterDataBean.getD25() + " " + attendanceRegisterDataBean.getD26() + " " + attendanceRegisterDataBean.getD27() + " " + attendanceRegisterDataBean.getD28() + " " + attendanceRegisterDataBean.getD29() + " " + attendanceRegisterDataBean.getD30() + " " + attendanceRegisterDataBean.getD31() + " " + attendanceRegisterDataBean.getPresentDays() + " " + attendanceRegisterDataBean.getLateDays() + " " + attendanceRegisterDataBean.getAbsentDays());
//        }
        return attendanceRegisterDataBeans;
    }

    private Boolean isHoliday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return !(holidayService.isHolidayAvailable(date, true, false));
    }

    public Boolean isCasualHoliday(Date date) {
        return !(holidayService.isHolidayAvailable(date, true, false));
    }

    public AttendanceDataBean retrieveAttendance(Long id, Date fromDate, Date toDate, int noOfDays, Integer allowableLateMinutes) throws UserManagementException, ParseException {
        long totalWorkingHours = 0;
        Float presentDay = 0F, absentDay = 0F, leaveDays = 0F;
        Integer lateDays = 0;
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        Calendar endTimeCal = Calendar.getInstance();
        endTimeCal.setTime(fromDate);
        endTimeCal.set(Calendar.HOUR_OF_DAY, 23);
        endTimeCal.set(Calendar.MINUTE, 59);
        endTimeCal.set(Calendar.SECOND, 59);
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(id);
        if (systemUserDetail.getShiftId() != null && allowableLateMinutes != null) {
            Shift shift = systemUserDetail.getShiftId();
            Calendar shiftStartTimeCal = Calendar.getInstance();
            shiftStartTimeCal.setTime(shift.getShiftStartTime());
            shiftStartTimeCal.set(Calendar.MINUTE, shiftStartTimeCal.get(Calendar.MINUTE) + allowableLateMinutes);
            shift.setShiftStartTime(shiftStartTimeCal.getTime());
            systemUserDetail.setShiftId(shift);
        }
        User user = userService.getUserbyId(id, false, false, false, false);
        if (systemUserDetail != null && systemUserDetail.getJoinDate() != null && systemUserDetail.getJoinDate().getTime() <= toDate.getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
            for (int i = 0; i < noOfDays; i++) {
                boolean isAbsent = false, isLateDays = false;
                if (systemUserDetail.getJoinDate().getTime() <= fromCal.getTime().getTime() && fromCal.getTime().getTime() <= new Date().getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                    Boolean isHoliday = this.isHoliday(fromCal.getTime());
                    List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(id, fromCal.getTime(), endTimeCal.getTime(), true);
                    if (cardLogs != null && cardLogs.size() == 1) {
                        CardLog cardLog = cardLogs.get(0);
                        if (systemUserDetail.getShiftId() != null) {
                            DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
                            if (f.format(cardLog.getCardPunchingTime()).compareTo(f.format(systemUserDetail.getShiftId().getShiftStartTime())) < 0) {
                                if (!isHoliday) {
                                    presentDay++;
                                }
                            } else {
                                if (!isHoliday) {
                                    lateDays++;
                                    presentDay++;
                                    isLateDays = true;
                                }
                            }
                        }
                    } else if (!isHoliday) {
                        absentDay++;
                        isAbsent = true;
                    }
                    if (!isHoliday) {
                        Leave leave = leaveService.retrieveLeaveByDate(id, fromCal.getTime());
                        if (leave != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            if (leave.getApprovalStatus() != null && leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                                if ((!"FD".equals(leave.getFromDateLeaveType()) && sdf.format(leave.getFromDate()).equals(sdf.format(fromCal.getTime()))) || (!"FD".equals(leave.getToDateLeaveType()) && sdf.format(leave.getToDate()).equals(sdf.format(fromCal.getTime())))) {
                                    if (isAbsent) {
                                        absentDay -= 0.5F;
                                    } else {
                                        presentDay -= 0.5F;
                                        if (isLateDays) {
                                            lateDays--;
                                        }
                                    }
                                    leaveDays += 0.5F;
                                } else {
                                    if (isAbsent) {
                                        absentDay--;
                                    }
                                    leaveDays++;
                                }
                            }
                        }
                    }
                }
                fromCal.add(Calendar.DAY_OF_MONTH, +1);
                endTimeCal.add(Calendar.DAY_OF_MONTH, +1);
            }
            List<JobBreakDataBean> jobBreakDataBeanList = personalTransformerBean.retrieveJobBreak(fromDate, toDate, id);
            if (jobBreakDataBeanList != null) {
                for (int i = 0; i < jobBreakDataBeanList.size(); i++) {
                    totalWorkingHours += jobBreakDataBeanList.get(i).getTotalTime() + jobBreakDataBeanList.get(i).getOfficialBreakTime();
                }
            }

            String totalWorkingTime = SystemFunctionUtil.retrieveHoursAndMinite(totalWorkingHours);
            totalWorkingHours /= presentDay;
            String totalAvgWorkingTime = SystemFunctionUtil.retrieveHoursAndMinite(totalWorkingHours);

//
//            System.out.println("hours:" + hours + " :Min:" + mins);
//            System.out.println("");
//            System.out.println("=======Total Working hors:" + totalWorkingTime);
////            totalWorkingHours = totalWorkingHours / (60 * 60 * 1000);
//            System.out.println("<=======>Total Working hors:" + totalAvgWorkingTime);
////            totalWorkingHours = Math.round(totalWorkingHours * 100) / 100;
////            if (totalWorkingHours == 0) {
////                avgWorkingHours = 0;
////            } else {
////                avgWorkingHours = (long) (totalWorkingHours / presentDay);
////                avgWorkingHours = Math.round(avgWorkingHours * 100) / 100;
////            }

            AttendanceDataBean attendanceDataBean = new AttendanceDataBean();
            attendanceDataBean.setAbsentDays(absentDay);
            attendanceDataBean.setLeaveDays(leaveDays);
            attendanceDataBean.setPresentDays(presentDay);
            attendanceDataBean.setLateDays(lateDays);
            attendanceDataBean.setEmpId(systemUserDetail.getEmpId());

            attendanceDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            attendanceDataBean.setId(id);
            attendanceDataBean.setTotalWorkingHours(totalWorkingTime);
            attendanceDataBean.setAverageWorkingHours(totalAvgWorkingTime);
            attendanceDataBean.setFromDate(fromDate);
            attendanceDataBean.setToDate(toDate);
            return attendanceDataBean;
        }
        return null;
    }

    public List<AttendanceDataBean> retrieveAttendance1(Long id, Date fromDate, Date toDate, int noOfDays, Integer allowableLateMinutes) throws UserManagementException, ParseException {
        List<User> users;
        Date shiftStartTime = null;
        DateFormat dateMonthFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (id == null) {
            users = userService.getAllActiveUsers();

        } else {
            User u = userService.getUserbyId(id, false, false, false, false);
            users = new ArrayList<User>();
            users.add(u);
        }
        DateFormat dateFormat = new SimpleDateFormat("dd");
        List<Long> userIds = new ArrayList<Long>();
        //Map of user and userId
        Map<Long, User> userAndIdMap = new HashMap<Long, User>();
        DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
        Integer noOfDays1 = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        Map<String, Boolean> holidayMap = new HashMap<String, Boolean>();
        List<Holiday> holidays = holidayService.retrieveHolidayBetweenDates(fromDate, toDate, true, false, null);
        for (int i = 0; i < noOfDays1; i++) {

            Date holidayCheckDate = SystemFunctionUtil.getDateAfterNoOfDays(fromDate, i);
            String holiDayDateFormate = dateMonthFormat.format(holidayCheckDate);
            holidayMap.put(holiDayDateFormate, Boolean.FALSE);
            Calendar c = Calendar.getInstance();
            c.setTime(holidayCheckDate);
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                holidayMap.put(holiDayDateFormate, Boolean.TRUE);
            } else {
                if (holidays != null && !holidays.isEmpty()) {
                    for (Holiday holiday : holidays) {
                        if (holiDayDateFormate.equals(dateMonthFormat.format(holiday.getHolidayDate()))) {
                            holidayMap.put(holiDayDateFormate, Boolean.TRUE);
                        }
                    }
                }
            }
        }
        if (users != null && !users.isEmpty()) {
            System.out.println("==== In" + users.size());
            List<AttendanceDataBean> attendanceDataBeans = new ArrayList<AttendanceDataBean>();
            for (User user : users) {
                userAndIdMap.put(user.getId(), user);
                userIds.add(user.getId());
            }

            List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailByUserIds(userIds, Boolean.TRUE);
            Map<Long, SystemUserDetail> systemUserDetailAndUserIdMap = new HashMap<Long, SystemUserDetail>();
            if (systemUserDetails != null && !systemUserDetails.isEmpty()) {
                for (SystemUserDetail systemUserDetail : systemUserDetails) {
                    systemUserDetailAndUserIdMap.put(systemUserDetail.getUserId(), systemUserDetail);
                }
            }

            Map<Long, Map<Date, Date>> userIdAndFirstPunchDate = cardLogService.retrieveUserIdAndFirstPunchDetailByDateMap(userIds, fromDate, toDate);

            List<CardLog> cardLogs = cardLogService.retrieveCardLogBetweenDatesByUserIds(userIds, fromDate, toDate);
            Map<Long, Map<Integer, Date>> cardLogMap = new HashMap<Long, Map<Integer, Date>>();
            Map<Long, Map<Integer, String>> cardLogEntryMap = new HashMap<Long, Map<Integer, String>>();
            Map<Long, Long> userIdAndJobsBreakTiming = new HashMap<Long, Long>();
            Map<Long, List<CardLog>> userIdAndListOfCardLogs = new HashMap<Long, List<CardLog>>();

            for (CardLog cardLog : cardLogs) {
                Map<Integer, Date> dateAndCardPunchMap = cardLogMap.get(cardLog.getUserId());
                Map<Integer, String> dateAndCardPunchTypeMap = cardLogEntryMap.get(cardLog.getUserId());
                List<CardLog> cls = userIdAndListOfCardLogs.get(cardLog.getUserId());
                if (cls == null) {
                    cls = new ArrayList<CardLog>();
                    userIdAndListOfCardLogs.put(cardLog.getUserId(), cls);
                }
                cls.add(cardLog);
                if (dateAndCardPunchMap == null) {
                    dateAndCardPunchMap = new HashMap<Integer, Date>();
                    dateAndCardPunchTypeMap = new HashMap<Integer, String>();
                    cardLogEntryMap.put(cardLog.getUserId(), dateAndCardPunchTypeMap);
                    cardLogMap.put(cardLog.getUserId(), dateAndCardPunchMap);
                }
//                   
                Date firstCardPunchDate = dateAndCardPunchMap.get(Integer.parseInt(dateFormat.format(cardLog.getCardPunchingTime())));
                if (firstCardPunchDate == null) {
                    dateAndCardPunchTypeMap.put(Integer.parseInt(dateFormat.format(cardLog.getCardPunchingTime())), cardLog.getCardEntryReason());
                    dateAndCardPunchMap.put(Integer.parseInt(dateFormat.format(cardLog.getCardPunchingTime())), cardLog.getCardPunchingTime());
                }
            }

            Boolean isStartFlag;
            for (User user : users) {
                isStartFlag = Boolean.TRUE;
                Long totalTimeIn = 0l;
                List<CardLog> logs = userIdAndListOfCardLogs.get(user.getId());
                if (!CollectionUtils.isEmpty(logs)) {
                    Date entryTime = null;
                    Date exitTime = null;
                    for (CardLog cardLog : logs) {
                        if (isStartFlag == true && cardLog.getCardEntryType() == 1) {
                            entryTime = this.convertToStartDate(cardLog.getCardPunchingTime());
                            exitTime = cardLog.getCardPunchingTime();
                            isStartFlag = Boolean.FALSE;
                        } else if (cardLog.getCardEntryType() == 0) {
                            entryTime = cardLog.getCardPunchingTime();
                            isStartFlag = Boolean.FALSE;
                        } else if (cardLog.getCardEntryType() == 1) {
                            if (entryTime != null && exitTime == null) {
                                exitTime = cardLog.getCardPunchingTime();
                            }
                        }
                        if (entryTime != null && exitTime != null) {
                            System.out.println("@Entry time : " + entryTime);
                            System.out.println("@Exit time : " + exitTime);
                            totalTimeIn += (exitTime.getTime() - entryTime.getTime());
                            entryTime = null;
                            exitTime = null;

                        }
                    }
                    if (entryTime != null && exitTime == null) {
                        if (this.convertToEndDate(entryTime).compareTo(new Date()) > 0) {

                            exitTime = new Date();
                            System.out.println("@@Entry time : " + entryTime);
                            System.out.println("@@Exit time : " + exitTime);
                            totalTimeIn += (exitTime.getTime() - entryTime.getTime());

                        } else {
                            Calendar startTime = Calendar.getInstance();

                            startTime.setTime(this.convertToStartDate(entryTime));
                            startTime.add(Calendar.DATE, +1);
                            Calendar endTime = Calendar.getInstance();
                            endTime.setTime(this.convertToEndDate(entryTime));
                            endTime.add(Calendar.DATE, +1);
                            List<CardLog> cls = cardLogService.retrieveFirstPunchingTimeBetweenDate(user.getId(), startTime.getTime(), endTime.getTime(), false);
                            if (cls != null && cls.size() == 1 && cls.get(0).getCardEntryType() == 1) {
                                exitTime = this.convertToEndDate(entryTime);
                                System.out.println("@@@Entry time : " + entryTime);
                                System.out.println("@@@Exit time : " + exitTime);
                                totalTimeIn += (exitTime.getTime() - entryTime.getTime());
                            }
                        }
                    }
                    entryTime = null;
                    exitTime = null;
                    userIdAndJobsBreakTiming.put(user.getId(), totalTimeIn);
                }
            }

            for (User user : users) {

                Map<Date, Date> dateAndFirstPunchingTime = null;
                if (userIdAndFirstPunchDate != null) {
                    dateAndFirstPunchingTime = userIdAndFirstPunchDate.get(user.getId());
                }
                long totalWorkingHours = 0l;
                if (userIdAndJobsBreakTiming.get(user.getId()) != null) {
                    totalWorkingHours = userIdAndJobsBreakTiming.get(user.getId());
                }
                Float presentDay = 0F, absentDay = 0F, leaveDays = 0F;
                Integer lateDays = 0;
                Calendar fromCal = Calendar.getInstance();
                fromCal.setTime(fromDate);
                fromCal.set(Calendar.HOUR_OF_DAY, 0);
                fromCal.set(Calendar.MINUTE, 0);
                fromCal.set(Calendar.SECOND, 0);
                Calendar endTimeCal = Calendar.getInstance();
                endTimeCal.setTime(fromDate);
                endTimeCal.set(Calendar.HOUR_OF_DAY, 23);
                endTimeCal.set(Calendar.MINUTE, 59);
                endTimeCal.set(Calendar.SECOND, 59);
                SystemUserDetail systemUserDetail = systemUserDetailAndUserIdMap.get(user.getId());
                if (systemUserDetail.getShiftId() != null && allowableLateMinutes != null) {
                    Shift shift = systemUserDetail.getShiftId();
//                    if (systemUserDetail.getEmpId().equalsIgnoreCase("A155")) {
                    System.out.println("Shift time:" + shift.getShiftStartTime());
//                    }
                    Calendar shiftStartTimeCal = Calendar.getInstance();
                    shiftStartTimeCal.setTime(shift.getShiftStartTime());
                    shiftStartTimeCal.set(Calendar.MINUTE, shiftStartTimeCal.get(Calendar.MINUTE) + allowableLateMinutes);
                    shiftStartTime = shiftStartTimeCal.getTime();
//                    shift.setShiftStartTime(shiftStartTimeCal.getTime());
//                    if (systemUserDetail.getEmpId().equalsIgnoreCase("A155")) {
                    System.out.println("Now Shift time:" + shiftStartTime);
//                    }
//                    systemUserDetail.setShiftId(shift);
                }
//                User user = userService.getUserbyId(id, false, false, false, false);
                if (systemUserDetail.getJoinDate() != null && systemUserDetail.getJoinDate().getTime() <= toDate.getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                    for (int i = 0; i < noOfDays; i++) {
                        boolean isAbsent = false, isLateDays = false;
                        if (systemUserDetail.getJoinDate().getTime() <= fromCal.getTime().getTime() && fromCal.getTime().getTime() <= new Date().getTime() && (user.getExpiredOn() == null || (user.getExpiredOn() != null && user.getExpiredOn().getTime() >= fromCal.getTime().getTime()))) {
                            Boolean isHoliday = holidayMap.get(dateMonthFormat.format(fromCal.getTime()));
//                            List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(user.getId(), fromCal.getTime(), endTimeCal.getTime(), true);
                            Date cardPunchingTime = null;
                            if (dateAndFirstPunchingTime != null) {
                                cardPunchingTime = dateAndFirstPunchingTime.get(fromCal.getTime());
                            }
                            if (cardPunchingTime != null) {
                                if (systemUserDetail.getShiftId() != null) {
//                                    if (systemUserDetail.getEmpId().equalsIgnoreCase("A155")) {
//                                        System.out.println("==card punching time :" + cardPunchingTime + " - " + systemUserDetail.getShiftId().getShiftStartTime());
//
//                                    }
                                    if (f.format(cardPunchingTime).compareTo(f.format(shiftStartTime)) < 0) {
                                        if (!isHoliday) {
                                            presentDay++;
                                        }
                                    } else {
                                        if (!isHoliday) {
                                            lateDays++;
                                            presentDay++;
                                            isLateDays = true;
                                        }
                                    }
                                }
                            } else if (!isHoliday) {
                                absentDay++;
                                isAbsent = true;
                            }
                            if (!isHoliday) {
                                Leave leave = leaveService.retrieveLeaveByDate(user.getId(), fromCal.getTime());
                                if (leave != null) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                    if (leave.getApprovalStatus() != null && leave.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                                        if ((!"FD".equals(leave.getFromDateLeaveType()) && sdf.format(leave.getFromDate()).equals(sdf.format(fromCal.getTime()))) || (!"FD".equals(leave.getToDateLeaveType()) && sdf.format(leave.getToDate()).equals(sdf.format(fromCal.getTime())))) {
                                            if (isAbsent) {
                                                absentDay -= 0.5F;
                                            } else {
                                                presentDay -= 0.5F;
                                                if (isLateDays) {
                                                    lateDays--;
                                                }
                                            }
                                            leaveDays += 0.5F;
                                        } else {
                                            if (isAbsent) {
                                                absentDay--;
                                            }
                                            leaveDays++;
                                        }
                                    }
                                }
                            }
                        }
                        fromCal.add(Calendar.DAY_OF_MONTH, +1);
                        endTimeCal.add(Calendar.DAY_OF_MONTH, +1);
                    }
//                    List<JobBreakDataBean> jobBreakDataBeanList = personalTransformerBean.retrieveJobBreak(fromDate, toDate, user.getId());
//                    if (jobBreakDataBeanList != null) {
//                        for (int i = 0; i < jobBreakDataBeanList.size(); i++) {
//                            totalWorkingHours += jobBreakDataBeanList.get(i).getTotalTime() + jobBreakDataBeanList.get(i).getOfficialBreakTime();
//                        }
//                    }

                    String totalWorkingTime = SystemFunctionUtil.retrieveHoursAndMinite(totalWorkingHours);
                    totalWorkingHours /= presentDay;
                    String totalAvgWorkingTime = SystemFunctionUtil.retrieveHoursAndMinite(totalWorkingHours);

//
//            System.out.println("hours:" + hours + " :Min:" + mins);
//            System.out.println("");
//            System.out.println("=======Total Working hors:" + totalWorkingTime);
////            totalWorkingHours = totalWorkingHours / (60 * 60 * 1000);
//            System.out.println("<=======>Total Working hors:" + totalAvgWorkingTime);
////            totalWorkingHours = Math.round(totalWorkingHours * 100) / 100;
////            if (totalWorkingHours == 0) {
////                avgWorkingHours = 0;
////            } else {
////                avgWorkingHours = (long) (totalWorkingHours / presentDay);
////                avgWorkingHours = Math.round(avgWorkingHours * 100) / 100;
////            }

                    AttendanceDataBean attendanceDataBean = new AttendanceDataBean();
                    attendanceDataBean.setAbsentDays(absentDay);
                    attendanceDataBean.setLeaveDays(leaveDays);
                    attendanceDataBean.setPresentDays(presentDay);
                    attendanceDataBean.setLateDays(lateDays);
                    attendanceDataBean.setEmpId(systemUserDetail.getEmpId());

                    attendanceDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    attendanceDataBean.setId(user.getId());
                    attendanceDataBean.setTotalWorkingHours(totalWorkingTime);
                    attendanceDataBean.setAverageWorkingHours(totalAvgWorkingTime);
                    attendanceDataBean.setFromDate(fromDate);
                    attendanceDataBean.setToDate(toDate);
                    attendanceDataBeans.add(attendanceDataBean);
                }

            }
            return attendanceDataBeans;
        }
        return null;
    }

    private Date convertToStartDate(Date date) {
        Calendar calDate = Calendar.getInstance();
        if (date != null) {
            calDate.setTime(date);
        }
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MILLISECOND, 0);
        return calDate.getTime();
    }

    private Date convertToEndDate(Date date) {
        Calendar calDate = Calendar.getInstance();
        if (date != null) {
            calDate.setTime(date);
        }
        calDate.set(Calendar.HOUR_OF_DAY, 23);
        calDate.set(Calendar.MINUTE, 59);
        calDate.set(Calendar.SECOND, 59);
        calDate.set(Calendar.MILLISECOND, 999);
        return calDate.getTime();
    }

    @Async
    public void sendMailToConsecutiveLateAndOnTime(List<ConsecutiveMailDataBean> consecutiveList) throws UserManagementException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        for (ConsecutiveMailDataBean consecutiveMailDataBean : consecutiveList) {
            SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(consecutiveMailDataBean.getId());
            if (consecutiveMailDataBean.getMailSendFor().equals(SystemConstantUtil.MAIL_FOR_LATE)) {
                this.sendMail(consecutiveMailDataBean.getEmailId(), consecutiveMailDataBean.getEmployeeName(), sdf.format(systemUserDetail.getShiftId().getShiftStartTime()), sdf1.format(consecutiveMailDataBean.getFromDate()), sdf1.format(consecutiveMailDataBean.getToDate()), SystemConstantUtil.EMAIL_FORMAT_LATE_CONSECUTIVE);
            } else {
                this.sendMail(consecutiveMailDataBean.getEmailId(), consecutiveMailDataBean.getEmployeeName(), sdf.format(systemUserDetail.getShiftId().getShiftStartTime()), sdf1.format(consecutiveMailDataBean.getFromDate()), sdf1.format(consecutiveMailDataBean.getToDate()), SystemConstantUtil.EMAIL_FORMAT_ON_TIME_CONSECUTIVE);
            }
        }
    }

    private void sendMail(String emailId, String emapName, String shiftTime, String fromDate, String toDate, String mailFormat) throws UserManagementException {
        String[] to = new String[1];
        to[0] = emailId;
        EmailFormat emailFormat = emailFormatService.retriveEmailByFormateName(mailFormat);
        if (emailFormat != null) {
            String emailBody = emailFormat.getBody();
            String subject = emailFormat.getSubject();
            emailBody = SystemFunctionUtil.replaceTextForEmail(emailBody, emapName, shiftTime, null, fromDate, toDate, null, null, null, null, null, null, null, null, null);
            subject = SystemFunctionUtil.replaceTextForEmail(subject, emapName, shiftTime, null, fromDate, toDate, null, null, null, null, null, null, null, null, null);
            userTransformerBean.sendMail(subject, emailBody, to, false, null);
        }
    }
}
