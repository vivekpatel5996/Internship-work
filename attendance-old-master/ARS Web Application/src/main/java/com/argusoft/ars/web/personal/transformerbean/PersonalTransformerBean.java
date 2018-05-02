/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.personal.transformerbean;

import com.argusoft.ars.core.CardLogService;
import com.argusoft.ars.core.OfficialBreakService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.CardLog;
import com.argusoft.ars.model.OfficialBreak;
import com.argusoft.ars.web.leavemanagement.transformerbean.OfficialBreakTransformerBean;
import com.argusoft.ars.web.personal.databean.CardLogTransactionDataBean;
import com.argusoft.ars.web.personal.databean.JobBreakDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.UserDataBean;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for Reports
 *
 * @author sudhir
 */
@ManagedBean(name = "personalTransformerBean")
@RequestScoped
public class PersonalTransformerBean {

    //  Core properties
    @ManagedProperty(value = "#{cardLogService}")
    private CardLogService cardLogService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{officialBreakService}")
    private OfficialBreakService officialBreakService;
    @ManagedProperty(value = "#{officialBreakTransformerBean}")
    private OfficialBreakTransformerBean officialBreakTransformerBean;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Other properties
    private static final Logger log = Logger.getLogger(PersonalTransformerBean.class);

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
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

    public CardLogService getCardLogService() {
        return cardLogService;
    }

    public void setCardLogService(CardLogService cardLogService) {
        this.cardLogService = cardLogService;
    }

    public OfficialBreakService getOfficialBreakService() {
        return officialBreakService;
    }

    public void setOfficialBreakService(OfficialBreakService officialBreakService) {
        this.officialBreakService = officialBreakService;
    }

    public OfficialBreakTransformerBean getOfficialBreakTransformerBean() {
        return officialBreakTransformerBean;
    }

    public void setOfficialBreakTransformerBean(OfficialBreakTransformerBean officialBreakTransformerBean) {
        this.officialBreakTransformerBean = officialBreakTransformerBean;
    }

    /**
     * Method to convert CardLog Model to CardLogDataBean.
     *
     * @param cardLog
     * @param cardLogDataBean
     * @return
     */
    private CardLogTransactionDataBean convertCardLogModelTocardLogTransactionDataBean(CardLog cardLog, CardLogTransactionDataBean cardLogTransactionDataBean) throws UserManagementException {
        cardLogTransactionDataBean.setCardPunchingTime(cardLog.getCardPunchingTime());
        cardLogTransactionDataBean.setCardEntryReason(cardLog.getCardEntryReason());


        short a = cardLog.getCardEntryType();
        if (a == 0) {
            cardLogTransactionDataBean.setCardEntryType("Entry");
        } else {
            cardLogTransactionDataBean.setCardEntryType("Exit");
        }
        return cardLogTransactionDataBean;
    }

    /**
     * Method to retrieve all CardLogs
     *
     * @return
     */
    public List<CardLogTransactionDataBean> retrieveCardLogTransaction(CardLogTransactionDataBean cardLogTransactionDataBean, Long userId, Date cardLogDate) throws UserManagementException {
        List<CardLogTransactionDataBean> cardLogTransactionDataBeanList = new ArrayList<CardLogTransactionDataBean>();
        List<CardLog> cardLogsList;
        if (cardLogDate == null) {
            cardLogsList = cardLogService.retrieveCardLogBetweenDates(userId, cardLogTransactionDataBean.getFromDate(), cardLogTransactionDataBean.getToDate());
        } else {
            cardLogsList = cardLogService.retrieveCardLogBetweenDates(userId, cardLogDate, cardLogDate);
        }

        if (cardLogsList != null) {
            User user = userService.getUserbyId(userId, false, false, false, false);
            for (CardLog cardLog : cardLogsList) {
                cardLogTransactionDataBean = convertCardLogModelTocardLogTransactionDataBean(cardLog, new CardLogTransactionDataBean());
                cardLogTransactionDataBean.setUserId(user.getUserId());
                cardLogTransactionDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                cardLogTransactionDataBeanList.add(cardLogTransactionDataBean);

            }
        } else {
            System.out.println("*******cardloglist not found*********");
        }
        return cardLogTransactionDataBeanList;
    }

    /**
     *
     *
     * @return @throws UserManagementException
     */
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
        return userDataBeanList;
    }

    /**
     *
     * @param jobBreakDataBean
     * @param user
     * @return
     * @throws UserManagementException
     * @throws ParseException
     */
    public List<JobBreakDataBean> retrieveJobBreak(Date fromDate, Date toDate, Long userId) throws UserManagementException, ParseException {

        Integer lastPunchingType = null;
        List<JobBreakDataBean> jobBreakDataBeanList = new ArrayList<JobBreakDataBean>();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(fromDate);
        startCal.set(Calendar.HOUR_OF_DAY, 00);
        startCal.set(Calendar.MINUTE, 00);
        startCal.set(Calendar.SECOND, 00);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(toDate);
        Calendar toDateCal = Calendar.getInstance();
        toDateCal.setTime(fromDate);
        toDateCal.set(Calendar.HOUR_OF_DAY, 23);
        toDateCal.set(Calendar.MINUTE, 59);
        toDateCal.set(Calendar.SECOND, 59);
        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            long entryTime = 0, exitTime = 0, totalInTime = 0, totalOfficialBreakTime = 0;
            boolean isValidUseOfCard = true, flag = false;
            int i = 1;
            List<CardLog> cardLogsList = cardLogService.retrieveJobBreakBetweenDates(userId, startCal.getTime(), toDateCal.getTime());
            List<OfficialBreak> officialBreakList = officialBreakService.retrieveOfficialBreakByUserId(userId, startCal.getTime(), toDateCal.getTime());

            if ((cardLogsList != null && !cardLogsList.isEmpty()) || (officialBreakList != null && !officialBreakList.isEmpty())) {
                if (cardLogsList != null && !cardLogsList.isEmpty()) {
                    if (cardLogsList.get(0).getCardEntryType() == 1) {
                        lastPunchingType = 1;
                        if (lastPunchingType == null) {
                            Calendar startTime = Calendar.getInstance();
                            startTime.setTime(startCal.getTime());
                            startTime.add(Calendar.DATE, -1);
                            Calendar endTime = Calendar.getInstance();
                            endTime.setTime(toDateCal.getTime());
                            endTime.add(Calendar.DATE, -1);
                            List<CardLog> cardLogs = cardLogService.retrieveLastPunchingTimeBetweenDate(userId, startTime.getTime(), endTime.getTime());
                            if (cardLogs != null && cardLogs.size() == 1 && cardLogs.get(0).getCardEntryType() == 0) {
                                entryTime = startCal.getTime().getTime();
                                exitTime = cardLogsList.get(0).getCardPunchingTime().getTime();
                                totalInTime += exitTime - entryTime;
                            } else {
                                isValidUseOfCard = false;
                            }
                        } else if (lastPunchingType == 0) {
                            entryTime = startCal.getTime().getTime();
                            exitTime = cardLogsList.get(0).getCardPunchingTime().getTime();
                            totalInTime += exitTime - entryTime;
                        } else {
                            isValidUseOfCard = false;
                        }
                    } else {
                        lastPunchingType = 0;
                        if (cardLogsList.size() == 1) {
                            Calendar startTime = Calendar.getInstance();
                            startTime.setTime(startCal.getTime());
                            startTime.add(Calendar.DATE, +1);
                            Calendar endTime = Calendar.getInstance();
                            endTime.setTime(toDateCal.getTime());
                            endTime.add(Calendar.DATE, +1);
                            List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(userId, startTime.getTime(), endTime.getTime(), false);
                            if (cardLogs != null && cardLogs.size() == 1 && cardLogs.get(0).getCardEntryType() == 1) {
                                entryTime = cardLogsList.get(0).getCardPunchingTime().getTime();
                                exitTime = toDateCal.getTime().getTime();
                                totalInTime += exitTime - entryTime;
                                System.out.println("total Time:" + totalInTime);
                            } else {
                                isValidUseOfCard = false;
                            }
                            lastPunchingType = 0;
                        } else {
                            entryTime = cardLogsList.get(0).getCardPunchingTime().getTime();
                        }
                    }
                    if (cardLogsList.size() > 2) {
                        for (i = 1; i < cardLogsList.size() - 1; i++) {
                            CardLog cardLog = cardLogsList.get(i);
                            if (lastPunchingType != cardLog.getCardEntryType()) {
                                if (lastPunchingType == 1) {
                                    entryTime = cardLog.getCardPunchingTime().getTime();
                                    lastPunchingType = 0;
                                } else if (lastPunchingType == 0) {
                                    exitTime = cardLog.getCardPunchingTime().getTime();
                                    totalInTime += exitTime - entryTime;
                                    lastPunchingType = 1;
                                }
                            } else {
                                if (lastPunchingType == 0) {
                                    entryTime = cardLog.getCardPunchingTime().getTime();
                                }
                                isValidUseOfCard = false;
                            }
                        }
                    }
                    if (cardLogsList.size() > 1) {
                        if (cardLogsList.get(i).getCardEntryType() != lastPunchingType && cardLogsList.get(i).getCardEntryType() == 1) {
                            exitTime = cardLogsList.get(i).getCardPunchingTime().getTime();
                            totalInTime += exitTime - entryTime;
                            lastPunchingType = 1;
                        } else if (cardLogsList.get(i).getCardEntryType() == 0) {
                            if (cardLogsList.get(i).getCardEntryType() != lastPunchingType) {
                                isValidUseOfCard = false;
                            }
                            Calendar startTime = Calendar.getInstance();
                            startTime.setTime(startCal.getTime());
                            startTime.add(Calendar.DATE, +1);
                            Calendar endTime = Calendar.getInstance();
                            endTime.setTime(toDateCal.getTime());
                            endTime.add(Calendar.DATE, +1);
                            List<CardLog> cardLogs = cardLogService.retrieveFirstPunchingTimeBetweenDate(userId, startTime.getTime(), endTime.getTime(), false);
                            if (cardLogs != null && cardLogs.size() == 1 && cardLogs.get(0).getCardEntryType() == 1) {
                                entryTime = cardLogsList.get(i).getCardPunchingTime().getTime();
                                exitTime = toDateCal.getTime().getTime();
                                totalInTime += exitTime - entryTime;
                            } else {
                                isValidUseOfCard = false;
                            }
                            lastPunchingType = 0;
                        } else if (cardLogsList.get(i).getCardEntryType() == lastPunchingType && cardLogsList.get(i).getCardEntryType() == 1) {
                            isValidUseOfCard = false;

                        }
                    }

                }
                if (officialBreakList != null && !officialBreakList.isEmpty()) {
                    for (OfficialBreak officialBreak : officialBreakList) {
                        totalOfficialBreakTime += (officialBreak.getToDate().getTime() - officialBreak.getFromDate().getTime());
                    }
                }
                JobBreakDataBean jobBreakDataBean = new JobBreakDataBean();
                jobBreakDataBean.setCardPunchingTime(startCal.getTime());
                jobBreakDataBean.setOfficialBreak(SystemFunctionUtil.retrieveHoursAndMinite(totalOfficialBreakTime));
                jobBreakDataBean.setTotalInTime(SystemFunctionUtil.retrieveHoursAndMinite(totalInTime));
                jobBreakDataBean.setIsValiduseOfCard(isValidUseOfCard);
                jobBreakDataBean.setDay(setDay(startCal.getTime()));
                jobBreakDataBean.setTotalTime(totalInTime);
                jobBreakDataBean.setOfficialBreakTime(totalOfficialBreakTime);
                //add to jobbreakdatabean to list
                jobBreakDataBeanList.add(jobBreakDataBean);

            } else {
                lastPunchingType = null;
            }
            startCal.add(Calendar.DAY_OF_MONTH, +1);
            toDateCal.add(Calendar.DAY_OF_MONTH, +1);
        }
        return jobBreakDataBeanList;

    }

    /**
     * *
     *
     * @param cardLogTransactionDataBean
     * @return
     * @throws UserManagementException
     */
    public List<CardLogTransactionDataBean> retrieveOfficialBreakExitCardLog(CardLogTransactionDataBean cardLogTransactionDataBean) throws UserManagementException {
        List<CardLogTransactionDataBean> cardLogTransactionExitDataBeanList = new ArrayList<CardLogTransactionDataBean>();
        Long userId = loginDataBean.getId();
        Date fromDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, -5);
        Date toDate = cal.getTime();
        List<CardLog> cardLogsList = cardLogService.retrieveCardLogBetweenDates(userId, toDate, fromDate);
        if (cardLogsList != null) {
            User user = userService.getUserbyId(userId, false, false, false, false);
            for (CardLog cardLog : cardLogsList) {
                cardLogTransactionDataBean = convertCardLogModelTocardLogTransactionDataBean(cardLog, new CardLogTransactionDataBean());
                cardLogTransactionDataBean.setUserId(user.getUserId());
                cardLogTransactionDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                if (cardLogTransactionDataBean.getCardEntryType().equals("Exit")) {
                    cardLogTransactionExitDataBeanList.add(cardLogTransactionDataBean);
                }
            }
        } else {
            System.out.println("cardlogTransactionlist not found...");
        }
        return cardLogTransactionExitDataBeanList;
    }

    /**
     * *
     *
     * @param cardLogTransactionDataBean
     * @return
     * @throws UserManagementException
     */
    public List<CardLogTransactionDataBean> retrieveOfficialBreakEntryCardLog(CardLogTransactionDataBean cardLogTransactionDataBean) throws UserManagementException {
        List<CardLogTransactionDataBean> cardLogTransactionEntryDataBeanList = new ArrayList<CardLogTransactionDataBean>();
        Long userId = loginDataBean.getId();
        Date fromDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, -5);
        Date toDate = cal.getTime();
        List<CardLog> cardLogsList = cardLogService.retrieveCardLogBetweenDates(userId, toDate, fromDate);
        if (cardLogsList != null) {
            User user = userService.getUserbyId(userId, false, false, false, false);
            for (CardLog cardLog : cardLogsList) {
                cardLogTransactionDataBean = convertCardLogModelTocardLogTransactionDataBean(cardLog, new CardLogTransactionDataBean());
                cardLogTransactionDataBean.setUserId(user.getUserId());
                cardLogTransactionDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                if (cardLogTransactionDataBean.getCardEntryType().equals("Entry")) {
                    cardLogTransactionEntryDataBeanList.add(cardLogTransactionDataBean);
                }
            }
        } else {
            System.out.println("cardlogTransactionlist not found...");
        }
        return cardLogTransactionEntryDataBeanList;
    }

    private String setDay(Date time) {
        SimpleDateFormat f = new SimpleDateFormat("EEEE");
        String day = f.format(time);

        if (day.equals("Sunday") || day.equals("Saturday")) {
            return " (" + day + ")";
        } else {
            return null;
        }

    }
}
