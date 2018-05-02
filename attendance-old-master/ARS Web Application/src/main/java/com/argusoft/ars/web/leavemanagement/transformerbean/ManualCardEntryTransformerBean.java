/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.transformerbean;

import com.argusoft.ars.core.CardLogService;
import com.argusoft.ars.core.EmailFormatService;
import com.argusoft.ars.core.ManualCardEntryService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.EmailFormat;
import com.argusoft.ars.model.ManualCardEntry;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.web.leavemanagement.databean.ManualCardEntryDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.UserTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "manualCardEntryTransformerBean")
@RequestScoped
public class ManualCardEntryTransformerBean {
    //Login DataBean

    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{manualCardEntryService}")
    private ManualCardEntryService manualCardEntryService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{cardLogService}")
    private CardLogService cardLogService;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    @ManagedProperty(value = "#{emailFormatService}")
    private EmailFormatService emailFormatService;

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

    public CardLogService getCardLogService() {
        return cardLogService;
    }

    public void setCardLogService(CardLogService cardLogService) {
        this.cardLogService = cardLogService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public ManualCardEntryService getManualCardEntryService() {
        return manualCardEntryService;
    }

    public void setManualCardEntryService(ManualCardEntryService manualCardEntryService) {
        this.manualCardEntryService = manualCardEntryService;
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

    public String approveMannualCardEntryRequest(Long manualCardEntryId, String adminComment, Boolean isLateEntry) throws UserManagementException {
        try {
            Long cardId = 0L;
            ManualCardEntry manualCardEntry = manualCardEntryService.retrieveManualCardEntryById(manualCardEntryId);
            if (manualCardEntry != null && manualCardEntry.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
                SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(manualCardEntry.getUserId());
                if (systemUserDetail == null) {
                    return "no user found";
                } else {
                    if (systemUserDetail.getCardEnrollNo() != null) {
                        cardId = systemUserDetail.getCardEnrollNo().getCardId();
                    }
                    if (systemUserDetail.getShiftId() == null) {
                        return "Right now user has no shift allocate so you can't approve request";
                    } else {
                        Boolean issuccessfullyDone = cardLogService.doManualCardEntry(systemUserDetail.getUserId(), manualCardEntry.getDate(), manualCardEntry.getInStatus(), manualCardEntry.getOutStatus(), cardId, systemUserDetail.getShiftId().getShiftStartTime(), systemUserDetail.getShiftId().getShiftEndTime(), manualCardEntry.getReason(), isLateEntry);
                        if (issuccessfullyDone) {
                            if (adminComment != null) {
                                manualCardEntry.setAdminComment(adminComment);
                            } else {
                                manualCardEntry.setAdminComment("N/A");
                            }
                            manualCardEntry.setResponseBy(loginDataBean.getId());
                            manualCardEntry.setResponseDate(new Date());
                            manualCardEntry.setApprovalStatus(SystemConstantUtil.APPROVE);
                            manualCardEntry.setIsNotificationShow(false);
                            manualCardEntryService.updateManualCardEntry(manualCardEntry);
                            List<Long> userId = new ArrayList<Long>();
                            userId.add(manualCardEntry.getUserId());
                            if (!loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                                this.sendMail(userId, manualCardEntry, SystemConstantUtil.EMAIL_FORMAT_RESPONSE_MANUAL_CARD_ENTRY);
                            }
                            return SystemConstantUtil.SUCCESS;
                        } else {
                            return "some connection issue";
                        }
                    }
                }
            } else {
                return "Already responded.";
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
            e.printStackTrace();
        }
        return null;
    }

    public String disApproveMannualCardEntryRequest(Long manualCardEntryId, String adminComment) throws UserManagementException {
        ManualCardEntry manualCardEntry = manualCardEntryService.retrieveManualCardEntryById(manualCardEntryId);
        if (manualCardEntry != null && manualCardEntry.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
            manualCardEntry.setAdminComment(adminComment);
            manualCardEntry.setResponseBy(loginDataBean.getId());
            manualCardEntry.setResponseDate(new Date());
            manualCardEntry.setApprovalStatus(SystemConstantUtil.DISAPPROVE);
            manualCardEntry.setIsNotificationShow(false);
            manualCardEntryService.updateManualCardEntry(manualCardEntry);
            List<Long> userId = new ArrayList<Long>();
            userId.add(manualCardEntry.getUserId());
            this.sendMail(userId, manualCardEntry, SystemConstantUtil.EMAIL_FORMAT_RESPONSE_MANUAL_CARD_ENTRY);
            return SystemConstantUtil.SUCCESS;
        } else {
            return "Already responded";
        }
    }

    public List<ManualCardEntryDataBean> retrievePersonalManualCardEntryList() {
        List<ManualCardEntryDataBean> manualCardEntryDataBeansList = new ArrayList<ManualCardEntryDataBean>();
        List<ManualCardEntry> manualCardEntryList = manualCardEntryService.retrieveManualCardEntryByUserId(loginDataBean.getId());
        if (!manualCardEntryList.isEmpty()) {
            for (ManualCardEntry manualCardEntry : manualCardEntryList) {
                ManualCardEntryDataBean manualCardEntryDataBean = convertManualCardEntryModelToManualCardEntryDataBean(manualCardEntry, new ManualCardEntryDataBean());
                manualCardEntryDataBeansList.add(manualCardEntryDataBean);
            }
        }
        return manualCardEntryDataBeansList;
    }

    public List<ManualCardEntryDataBean> retrieveManualcardEntryPandingRequestList() throws UserManagementException {
        List<ManualCardEntryDataBean> manualCardEntryDataBeansList = new ArrayList<ManualCardEntryDataBean>();
        List<ManualCardEntry> manualCardEntryList = manualCardEntryService.retrievePandingManualCardEntry();
        for (ManualCardEntry manualCardEntry : manualCardEntryList) {
            ManualCardEntryDataBean manualCardEntryDataBean = new ManualCardEntryDataBean();
            manualCardEntryDataBean = convertManualCardEntryModelToManualCardEntryDataBean(manualCardEntry, manualCardEntryDataBean);
            User user = userService.getUserbyId(manualCardEntryDataBean.getUserId(), false, false, true, false);
            manualCardEntryDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            manualCardEntryDataBeansList.add(manualCardEntryDataBean);
        }
        return manualCardEntryDataBeansList;
    }

    public List<ManualCardEntryDataBean> retrieveNotArchiveManualcardEntryList() throws UserManagementException {
        List<ManualCardEntryDataBean> manualCardEntryDataBeansList = new ArrayList<ManualCardEntryDataBean>();
        List<ManualCardEntry> manualCardEntryList = manualCardEntryService.retrieveNotArchiveManualCardEntry(loginDataBean.getId());
        for (ManualCardEntry manualCardEntry : manualCardEntryList) {
            ManualCardEntryDataBean manualCardEntryDataBean = new ManualCardEntryDataBean();
            manualCardEntryDataBean = convertManualCardEntryModelToManualCardEntryDataBean(manualCardEntry, manualCardEntryDataBean);
            User user = userService.getUserbyId(manualCardEntry.getResponseBy(), false, false, true, false);
            manualCardEntryDataBean.setResponseUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            manualCardEntryDataBeansList.add(manualCardEntryDataBean);
        }
        return manualCardEntryDataBeansList;
    }

    public ManualCardEntryDataBean retrieveManualCardEntryRequestDetail(Long manualCardEntryId) throws UserManagementException {
        ManualCardEntry manualCardEntry = manualCardEntryService.retrieveManualCardEntryById(manualCardEntryId);
        ManualCardEntryDataBean manualCardEntryDataBean = new ManualCardEntryDataBean();
        manualCardEntryDataBean = convertManualCardEntryModelToManualCardEntryDataBean(manualCardEntry, manualCardEntryDataBean);
        if (manualCardEntry.getResponseBy() != null) {
            User user = userService.getUserbyId(manualCardEntry.getResponseBy(), false, false, false, false);
            if (user != null) {
                manualCardEntryDataBean.setResponseUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            }
        }
        return manualCardEntryDataBean;
    }

    public String requestForManualCardEntry(ManualCardEntryDataBean manualCardEntryDataBean) throws UserManagementException {
//        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(manualCardEntryDataBean.getUserId());
//        if (systemUserDetail.getCardEnrollNo() == null) {
//            return "no card allocate so you can't request for manual card entry";
//        } else if (systemUserDetail.getShiftId() == null) {
//            return "no shift allocate so you can't request for manual card entry";
//        } else if (systemUserDetail.getShiftId() == null || systemUserDetail.getCardEnrollNo() == null) {
//            return " no shift and card allocate so you can't request for manual card entry";
//        }
        boolean isAlreadyManualCardEnteryApply = manualCardEntryService.isManualCardEntryAlreadyApply(manualCardEntryDataBean.getUserId(), manualCardEntryDataBean.getDate(), manualCardEntryDataBean.getInStatus(), manualCardEntryDataBean.getOutStatus());
//        System.out.println("============> Already aply :" + isAlreadyManualCardEnteryApply);
        if (!isAlreadyManualCardEnteryApply) {
            ManualCardEntry manualCardEntry = convertManualCardEntryDataBeanToManualCardEntryModel(manualCardEntryDataBean, new ManualCardEntry());
            Long manualcardId = manualCardEntryService.applyManualCardEntry(manualCardEntry);
            if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                approveMannualCardEntryRequest(manualcardId, null, false);
            } else {
                this.sendMailToAdmin(manualCardEntry, SystemConstantUtil.EMAIL_FORMAT_APPLY_MANUAL_CARD_ENTRY);
            }
            return SystemConstantUtil.SUCCESS;
        } else {
            return "Reqest for manual card entry is alreay done on this date";
        }
    }

    private ManualCardEntry convertManualCardEntryDataBeanToManualCardEntryModel(ManualCardEntryDataBean manualCardEntryDataBean, ManualCardEntry manualCardEntry) {
        if (loginDataBean.getRole().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
            manualCardEntry.setApprovalStatus(SystemConstantUtil.PENDING);
            manualCardEntry.setIsNotificationShow(true);
            manualCardEntry.setResponseBy(loginDataBean.getId());
            manualCardEntry.setResponseDate(new Date());
            manualCardEntry.setIsNotificationShow(false);
        } else {
            manualCardEntry.setApprovalStatus(SystemConstantUtil.PENDING);
            manualCardEntry.setIsNotificationShow(true);
        }
        manualCardEntry.setAppliedDate(new Date());
        manualCardEntry.setDate(manualCardEntryDataBean.getDate());
        manualCardEntry.setInStatus(manualCardEntryDataBean.getInStatus());
        manualCardEntry.setIsArchive(false);
        manualCardEntry.setOutStatus(manualCardEntryDataBean.getOutStatus());
        manualCardEntry.setReason(manualCardEntryDataBean.getReason());
        manualCardEntry.setUserId(manualCardEntryDataBean.getUserId());
        return manualCardEntry;
    }

    /**
     * Method to convert ManualCardEntry Model to ManualCardEntryDataBean.
     */
    private ManualCardEntryDataBean convertManualCardEntryModelToManualCardEntryDataBean(ManualCardEntry manualCardEntry, ManualCardEntryDataBean manualCardEntryDataBean) {
        manualCardEntryDataBean.setAdminComment(manualCardEntry.getAdminComment());
        manualCardEntryDataBean.setDate(manualCardEntry.getDate());
        manualCardEntryDataBean.setInStatus(manualCardEntry.getInStatus());
        manualCardEntryDataBean.setManualCardEntryId(manualCardEntry.getEntryId());
        manualCardEntryDataBean.setOutStatus(manualCardEntry.getOutStatus());
        manualCardEntryDataBean.setReason(manualCardEntry.getReason());
        manualCardEntryDataBean.setStatus(manualCardEntry.getApprovalStatus());
        manualCardEntryDataBean.setUserId(manualCardEntry.getUserId());
        return manualCardEntryDataBean;
    }

    public void archiveManualCardEntry(Long manualCardEntryId) {
        ManualCardEntry manualCardEntry = manualCardEntryService.retrieveManualCardEntryById(manualCardEntryId);
        manualCardEntry.setIsArchive(true);
        manualCardEntryService.updateManualCardEntry(manualCardEntry);
    }

    private void sendMail(List<Long> listOfUserIdForSendEmail, ManualCardEntry manualCardEntry, String mailFormatName) throws UserManagementException {
        if (listOfUserIdForSendEmail != null && listOfUserIdForSendEmail.size() > 0) {
            List<User> usersList = userService.retrieveUsersByUserList(listOfUserIdForSendEmail, null, null, null, true);
            if (usersList != null && usersList.size() > 0) {
                String[] to = new String[usersList.size()];
                int i = 0;
                for (User user : usersList) {
                    to[i++] = user.getContact().getEmailAddress();
                    System.out.println("To:" + i + ":" + user.getContact().getEmailAddress());
                }
                EmailFormat emailFormat = emailFormatService.retriveEmailByFormateName(mailFormatName);
                if (emailFormat != null) {
                    String emailBody = emailFormat.getBody();
                    User user = userService.getUserbyId(manualCardEntry.getUserId(), false, false, true, false);
                    String employeeName = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    String approver = "";
                    if (manualCardEntry.getResponseBy() != null) {
                        user = userService.getUserbyId(manualCardEntry.getResponseBy(), false, false, true, false);
                        approver = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String manualCardEntryInfo = this.setManualCardEntryInfo(manualCardEntry, employeeName, approver);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_FROM_DATE, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_CURRENT_DATE, sdf.format(new Date()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_TO_DATE, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_END_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_START_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_LATE_DURATION, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_RESPONSE, manualCardEntry.getApprovalStatus());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_INFO, manualCardEntryInfo);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_APPROVER, approver);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_DATE, sdf.format(manualCardEntry.getDate()));
                    emailBody = SystemFunctionUtil.replaceTextForEmail(emailBody, employeeName, null, null, null , null, manualCardEntryInfo, approver, sdf.format(manualCardEntry.getDate()),manualCardEntry.getApprovalStatus() ,null, null, null, null, null);
            
                    if (mailFormatName.equals(SystemConstantUtil.EMAIL_FORMAT_APPLY_MANUAL_CARD_ENTRY)) {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                        emailBody += "<br/><form  action=\"http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/manualCardEntryResponse\" method=\"post\"><table>";
                        if (manualCardEntry.getInStatus() == true) {
                            emailBody += "<tr><td>Penallty one hour: </td><td><input type=\"checkbox\" name=\"isLateEntry\" value=\"true\"/></td></tr>";
                        }
                        emailBody += "<input type=\"hidden\" name=\"manualCardEntryId\" value=\"" + manualCardEntry.getEntryId() + "\" /><tr><td>Comment: </td><td><textarea name=\"comment\"></textarea></td></tr><tr><td colsapan='2'><input type=\"submit\" value=\"Approve\" name=\"type\"/> <input type=\"submit\" value=\"Disapprove\" name=\"type\"/></td></form>";
                    }
                    String subject = emailFormat.getSubject();
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_DATE, sdf.format(manualCardEntry.getDate()));
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_APPROVER, approver);
                    subject = SystemFunctionUtil.replaceTextForEmail(subject, employeeName, null, null, null , null, manualCardEntryInfo, approver, sdf.format(manualCardEntry.getDate()),manualCardEntry.getApprovalStatus() ,null, null, null, null, null);
            
                    userTransformerBean.sendMail(subject, emailBody, to, false, null);
                }
            }
        }
    }

    private void sendMailToAdmin(ManualCardEntry manualCardEntry, String emailFormat) throws UserManagementException {
        List<User> adminUserDetailList = userService.getUsersByType(SystemConstantUtil.ROLE_SUPER_ADMIN, true, false, false, false, false);
        if (adminUserDetailList != null && !adminUserDetailList.isEmpty()) {
            List<Long> adminList = new ArrayList<Long>();
            for (User user : adminUserDetailList) {
                if (user.getId() != loginDataBean.getId()) {
                    adminList.add(user.getId());
                }
            }
            System.out.println("Admin List size:" + adminList.size());
            this.sendMail(adminList, manualCardEntry, emailFormat);
        }
    }

    private String setManualCardEntryInfo(ManualCardEntry manualCardEntry, String empName, String responseEmpName) {
        StringBuilder message = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        message.append("<table width='100%' border='0' cellpadding='7px' cellspacing='0'>");
        message.append("<tr><th width='25%' align='right'> <strong>Employee Name : &nbsp;</strong> </th><td width='75%'>").append(empName).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Date : &nbsp;</strong> </th><td>").append(sdf.format(manualCardEntry.getDate()).toString()).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Entry: &nbsp;</strong> </th><td>").append(manualCardEntry.getInStatus() && manualCardEntry.getOutStatus() ? "In and Out" : manualCardEntry.getInStatus() ? "In" : "Out").append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Reason : &nbsp;</strong> </th><td>").append(manualCardEntry.getReason()).append("</td></tr>");
        if (manualCardEntry.getResponseBy() != null) {
            message.append("<tr><th colspan=2><hr/></th></tr>");
            message.append("<tr><th align='right'> <strong>Response By : &nbsp;</strong> </th><td>").append(responseEmpName).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Comment : &nbsp;</strong> </th><td>").append(manualCardEntry.getAdminComment()).append("</td></tr>");
        }
        message.append("</table>");
        return message.toString();
    }
}
