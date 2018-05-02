/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.personal.servicebean;

import com.argusoft.ars.web.personal.databean.CardLogTransactionDataBean;
import com.argusoft.ars.web.personal.databean.JobBreakDataBean;
import com.argusoft.ars.web.personal.databean.AttendanceRegisterDataBean;
import com.argusoft.ars.web.personal.transformerbean.PersonalTransformerBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import java.text.ParseException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "personalServiceBean")
@RequestScoped
public class PersonalServiceBean {

    @ManagedProperty(value = "#{cardLogTransactionDataBean}")
    private CardLogTransactionDataBean cardLogTransactionDataBean;
    @ManagedProperty(value = "#{jobBreakDataBean}")
    private JobBreakDataBean jobBreakDataBean;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{attendanceRegisterDataBean}")
    private AttendanceRegisterDataBean attendanceRegisterDataBean;
    @ManagedProperty(value = "#{personalTransformerBean}")
    private PersonalTransformerBean personalTransformerBean;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    private Long user;
    private Date cardLogDate;
    private Date fromDate;
    private Date toDate;

    public AttendanceRegisterDataBean getAttendanceRegisterDataBean() {
        return attendanceRegisterDataBean;
    }

    public void setAttendanceRegisterDataBean(AttendanceRegisterDataBean attendanceRegisterDataBean) {
        this.attendanceRegisterDataBean = attendanceRegisterDataBean;
    }

    public Long getUser() {
        if (user == null) {
            user = loginDataBean.getId();
        }
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
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

    public CardLogTransactionDataBean getCardLogTransactionDataBean() {
        return cardLogTransactionDataBean;
    }

    public void setCardLogTransactionDataBean(CardLogTransactionDataBean cardLogTransactionDataBean) {
        this.cardLogTransactionDataBean = cardLogTransactionDataBean;
    }

    public PersonalTransformerBean getPersonalTransformerBean() {
        return personalTransformerBean;
    }

    public void setPersonalTransformerBean(PersonalTransformerBean personalTransformerBean) {
        this.personalTransformerBean = personalTransformerBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public void retrieveCardLogTransaction() throws UserManagementException {
        if (cardLogDate != null) {
            cardLogTransactionDataBean = null;
        }
        systemResultViewUtil.setCardLogTransactionDataBeanList(personalTransformerBean.retrieveCardLogTransaction(cardLogTransactionDataBean, getUser(), getCardLogDate()));

    }

    public void retrieveJobBreak() throws UserManagementException, ParseException {
        if (fromDate != null) {
            jobBreakDataBean.setFromDate(fromDate);
            jobBreakDataBean.setToDate(toDate);
        }
        systemResultViewUtil.setJobBreakDataBeanList(personalTransformerBean.retrieveJobBreak(jobBreakDataBean.getFromDate(), jobBreakDataBean.getToDate(), getUser()));

    }

    public void retrieveUserName() throws UserManagementException {
        System.out.println("---------reportservicebean-----------------");
        systemResultViewUtil.setUserDataBeanList(personalTransformerBean.retrieveUserList());

    }

    public JobBreakDataBean getJobBreakDataBean() {
        return jobBreakDataBean;
    }

    public void setJobBreakDataBean(JobBreakDataBean jobBreakDataBean) {
        this.jobBreakDataBean = jobBreakDataBean;
    }

    public Date getCardLogDate() {
        return cardLogDate;
    }

    public void setCardLogDate(Date cardLogDate) {
        this.cardLogDate = cardLogDate;
    }

    public void retrieveOfficialBreakExitCardLog() throws UserManagementException {
        systemResultViewUtil.setCardLogTransactionExitDataBeanList(personalTransformerBean.retrieveOfficialBreakExitCardLog(cardLogTransactionDataBean));
    }

    public void retrieveOfficialBreakEntryCardLog() throws UserManagementException {
        systemResultViewUtil.setCardLogTransactionEntryDataBeanList(personalTransformerBean.retrieveOfficialBreakEntryCardLog(cardLogTransactionDataBean));
    }
    
   
}
