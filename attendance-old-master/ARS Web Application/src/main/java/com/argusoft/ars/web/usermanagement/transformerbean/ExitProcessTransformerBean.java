/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.EmailFormatService;
import com.argusoft.ars.core.ExitProcessService;
import com.argusoft.ars.model.EmailFormat;
import com.argusoft.ars.model.ExitProcess;
import com.argusoft.ars.web.usermanagement.databean.ExitProcessDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.email.common.model.Attachment;
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
@ManagedBean(name = "exitProcessTransformerBean")
@RequestScoped
public class ExitProcessTransformerBean {

    @ManagedProperty(value = "#{exitProcessService}")
    private ExitProcessService exitProcessService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ExitProcessService getExitProcessService() {
        return exitProcessService;
    }

    public void setExitProcessService(ExitProcessService exitProcessService) {
        this.exitProcessService = exitProcessService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public ExitProcessDataBean retrieveExitprocessDetail() {
        ExitProcess exitProcess = exitProcessService.retrieveExitProcessByUserId(loginDataBean.getId());
        ExitProcessDataBean exitProcessDataBean = new ExitProcessDataBean();
        if (exitProcess != null) {
            exitProcessDataBean = convertExitProcessModelToExitProcessDataBean(exitProcess, new ExitProcessDataBean());
            if (exitProcess.getDocName() != null) {
                exitProcessDataBean.setFileData(exitProcess.getDocData());
                exitProcessDataBean.setFileName(exitProcess.getDocName());
                return exitProcessDataBean;
            }
        }
        exitProcessDataBean.setFileData(null);
        return exitProcessDataBean;
    }

    private ExitProcessDataBean convertExitProcessModelToExitProcessDataBean(ExitProcess exitProcess, ExitProcessDataBean exitProcessDataBean) {
        if (exitProcess.getAdminComment() != null && !"".equals(exitProcess.getAdminComment())) {
            exitProcessDataBean.setTempAdminComment(exitProcess.getAdminComment());
        }
        exitProcessDataBean.setExitId(exitProcess.getExitId());
        exitProcessDataBean.setExpectedReliveDate(exitProcess.getExpectedRelieveDate());
        exitProcessDataBean.setDiscription(exitProcess.getReason());
        exitProcessDataBean.setAppliedStatus(exitProcess.getAppliedStatus());
        exitProcessDataBean.setApprovalStatus(exitProcess.getApprovalStatus());
        exitProcessDataBean.setIsArchive(exitProcess.getIsArchive());
        return exitProcessDataBean;
    }

    public String submitResignation(ExitProcessDataBean exitProcessDataBean) {
        try {
            ExitProcess exitProcess = convertExitProcessDataBeanToExitProcessModel(exitProcessDataBean, new ExitProcess());
            exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
            exitProcess.setExitId(exitProcessService.createExitProcess(exitProcess));
            this.sendMailToAdmin(exitProcess, null, SystemConstantUtil.EMAIL_FORMAT_APPLY_RESIGNATION);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return e.toString();
        }
    }

    private ExitProcess convertExitProcessDataBeanToExitProcessModel(ExitProcessDataBean exitProcessDataBean, ExitProcess exitProcess) {
        exitProcess.setApprovalStatus("Pending");
        exitProcess.setDocName(exitProcessDataBean.getFileName());
        exitProcess.setExpectedRelieveDate(exitProcessDataBean.getExpectedReliveDate());
        exitProcess.setIsArchive(true);
        exitProcess.setReason(exitProcessDataBean.getDiscription());
        exitProcess.setRequestedDate(new Date());
        exitProcess.setUserId(loginDataBean.getId());
        exitProcess.setDocData(exitProcessDataBean.getFileData());
        exitProcess.setIsActive(true);
        exitProcess.setIsNotificationShow(true);
        return exitProcess;
    }

    public String cancelResignation(Long exitId) {
        try {
            ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitId);
            String status;
            System.out.println("Exit procress:" + exitProcess.getApprovalStatus());
            if (exitProcess.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL);
                exitProcess.setApprovalStatus(SystemConstantUtil.PENDING);
                status = "your resignation canclation send for approval !!!!";
            } else if (exitProcess.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_MODIFIED)) {
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL);
                exitProcess.setApprovalStatus(SystemConstantUtil.PENDING);
                status = "your resignation canclation send for approval !!!!";
            } else {
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_CANCEL);
                exitProcess.setApprovalStatus(null);
                exitProcess.setIsArchive(true);
                exitProcess.setIsActive(false);
                status = "your resignation cancel successfully !!!!";
            }
            exitProcess.setIsNotificationShow(true);
            exitProcessService.updateExitProcess(exitProcess);
            this.sendMailToAdmin(exitProcess, null, SystemConstantUtil.EMAIL_FORMAT_CANCEL_RESIGNATION);
            return status;
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }

    public String updateResignation(ExitProcessDataBean exitProcessDataBean) {
        try {
            ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitProcessDataBean.getExitId());
            ExitProcess oldExitProcessDetail = exitProcessService.retrieveExitProcessById(exitProcessDataBean.getExitId());
            exitProcess = convertExitProcessDataBeanToExitProcessModel(exitProcessDataBean, exitProcess);
            if (exitProcess.getApprovalStatus() != null) {
                if (exitProcess.getAppliedStatus().equals(SystemConstantUtil.APPLY_NEW) && exitProcess.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
                    exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_NEW_MODIFIED);
                } else if (exitProcess.getApprovalStatus().equals(SystemConstantUtil.APPROVE)) {
                    exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_MODIFIED);
                } else if (exitProcess.getApprovalStatus().equals(SystemConstantUtil.DISAPPROVE)) {
                    exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_DISAPPROVE_MODIFIED);
                } else if (exitProcess.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                    exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_MODIFIED);
                } else if (exitProcess.getAppliedStatus().equals(SystemConstantUtil.APPLY_CANCEL)) {
                    exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_NEW_MODIFIED);
                } else {
                    exitProcess.setAppliedStatus(exitProcess.getAppliedStatus());
                }
            } else {
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_NEW_MODIFIED);
            }
            exitProcess.setApprovalStatus(SystemConstantUtil.PENDING);
            exitProcessService.updateExitProcess(exitProcess);
            this.sendMailToAdmin(exitProcess, oldExitProcessDetail, SystemConstantUtil.EMAIL_FORMAT_UPDATE_EXIT_PROCESS);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }

    public List<ExitProcessDataBean> retrievePandingResignNotification() throws UserManagementException {
        List<ExitProcess> pandingResignationList = exitProcessService.retrievePandingResignation();
        if (pandingResignationList != null) {
            List<ExitProcessDataBean> exitProcessDataBeansList = new ArrayList<ExitProcessDataBean>();
            for (ExitProcess exitProcess : pandingResignationList) {
                if (exitProcess.getUserId() != loginDataBean.getId()) {
                    ExitProcessDataBean exitProcessDataBean = convertExitProcessModelToExitProcessDataBean(exitProcess, new ExitProcessDataBean());
                    User user = userService.getUserbyId(exitProcess.getUserId(), false, false, true, false);
                    exitProcessDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    exitProcessDataBeansList.add(exitProcessDataBean);
                }
            }
            return exitProcessDataBeansList;
        }
        return null;
    }

    public String disAprroveResign(Long exitId, String adminComment) throws UserManagementException {
        ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitId);
        if (exitProcess != null && exitProcess.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
            String response = "";
            String sendMailFormat;
            if (exitProcess.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                exitProcess.setApprovalStatus(SystemConstantUtil.APPROVE);
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL_DISAPPROVE);
                exitProcess.setIsActive(true);
                response = "Cancellation resignation response disapprove successfully.";
                sendMailFormat = SystemConstantUtil.EMAIL_FORMAT_CANCELATION_RESIGNATION_DISAPPROVE;
            } else {
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
                exitProcess.setApprovalStatus(SystemConstantUtil.DISAPPROVE);
                exitProcess.setIsActive(false);
                response = "Resignation disapprove successfully.";
                sendMailFormat = SystemConstantUtil.EMAIL_FORMAT_RESIGNATION_DISAPPROVE;
            }
            exitProcess.setIsArchive(false);
            exitProcess.setIsNotificationShow(false);
            exitProcess.setAdminComment(adminComment);
            exitProcess.setResponseBy(loginDataBean.getId());
            exitProcess.setResponseDate(new Date());
            exitProcessService.updateExitProcess(exitProcess);
            this.sendMailToAdmin(exitProcess, null, sendMailFormat);
            return response;
        } else {
            return "resignation is alreay responded";
        }
    }

    public ExitProcessDataBean retrieveExitprocessDetailByExitId(Long exitId) throws UserManagementException {
        ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitId);
        if (exitProcess != null) {
            ExitProcessDataBean exitProcessDataBean = convertExitProcessModelToExitProcessDataBean(exitProcess, new ExitProcessDataBean());
            if (exitProcess.getResponseBy() != null) {
                User user = userService.getUserbyId(exitProcess.getResponseBy(), false, false, true, false);
                exitProcessDataBean.setResponseUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            }
            if (!exitProcess.getUserId().equals(loginDataBean.getId())) {
                User user = userService.getUserbyId(exitProcess.getUserId(), false, false, true, false);
                exitProcessDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
            }
            exitProcessDataBean.setFileData(exitProcess.getDocData());
            exitProcessDataBean.setFileName(exitProcess.getDocName());
            return exitProcessDataBean;
        }
        return null;
    }

    public String aprroveResign(Long exitId, String adminComment,Date actualRelieveDate) throws UserManagementException {
        ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitId);
        if (exitProcess != null && exitProcess.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
            String sendMailFormat, response;
            if (exitProcess.getAppliedStatus().equals(SystemConstantUtil.APPLY_APPROVE_CANCEL)) {
                exitProcess.setApprovalStatus(null);
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_APPROVE_CANCEL_APPROVE);
                exitProcess.setIsActive(false);
                exitProcess.setActualRelieveDate(actualRelieveDate);
                sendMailFormat = SystemConstantUtil.EMAIL_FORMAT_CANCELATION_RESIGNATION_APPROVE;
                response = "Resignation cancellation request approve successfully.";
            } else {
                exitProcess.setApprovalStatus(SystemConstantUtil.APPROVE);
                exitProcess.setAppliedStatus(SystemConstantUtil.APPLY_NEW);
                exitProcess.setIsActive(true);
                exitProcess.setActualRelieveDate(actualRelieveDate);
                sendMailFormat = SystemConstantUtil.EMAIL_FORMAT_RESIGNATION_APPROVE;
                response = "Resignation request approve successfully.";
            }
            exitProcess.setIsNotificationShow(false);
            exitProcess.setIsArchive(false);
            exitProcess.setResponseBy(loginDataBean.getId());
            exitProcess.setResponseDate(new Date());
            exitProcess.setAdminComment(adminComment);
            exitProcessService.updateExitProcess(exitProcess);
            this.sendMailToAdmin(exitProcess, null, sendMailFormat);
            return response;
        } else {
            return "resignation is alreay responded";
        }
    }

    public List<ExitProcessDataBean> retrieveResignationResponseNotification() throws UserManagementException {
        List<ExitProcess> responseResignatioList = exitProcessService.retrieveNotArchiveResignationList(loginDataBean.getId());
        System.out.println("Transformer Bean");
        if (responseResignatioList != null) {
            List<ExitProcessDataBean> exitProcessDataBeansList = new ArrayList<ExitProcessDataBean>();
            for (ExitProcess exitProcess : responseResignatioList) {
                ExitProcessDataBean exitProcessDataBean = convertExitProcessModelToExitProcessDataBean(exitProcess, new ExitProcessDataBean());
                User user = userService.getUserbyId(exitProcess.getResponseBy(), false, false, true, false);
                exitProcessDataBean.setResponseUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                exitProcessDataBeansList.add(exitProcessDataBean);
            }
            return exitProcessDataBeansList;
        }
        return null;
    }

    public List<ExitProcessDataBean> retrieveCancelResignationNotification() throws UserManagementException {
        List<ExitProcess> pandingResignationList = exitProcessService.retrieveCancelResignationList();
        if (pandingResignationList != null) {
            List<ExitProcessDataBean> exitProcessDataBeansList = new ArrayList<ExitProcessDataBean>();
            for (ExitProcess exitProcess : pandingResignationList) {
                if (exitProcess.getUserId() != loginDataBean.getId()) {
                    ExitProcessDataBean exitProcessDataBean = convertExitProcessModelToExitProcessDataBean(exitProcess, new ExitProcessDataBean());
                    User user = userService.getUserbyId(exitProcess.getUserId(), false, false, true, false);
                    exitProcessDataBean.setUserName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    exitProcessDataBeansList.add(exitProcessDataBean);
                }
            }
            return exitProcessDataBeansList;
        }
        return null;
    }

    public String archiveResponseResignation(Long exitId) {
        ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitId);
        exitProcess.setIsArchive(true);
        exitProcessService.updateExitProcess(exitProcess);
        return SystemConstantUtil.SUCCESS;
    }

    public String archiveCancelResignationNotification(Long exitId) {
        ExitProcess exitProcess = exitProcessService.retrieveExitProcessById(exitId);
        exitProcess.setIsNotificationShow(false);
        exitProcessService.updateExitProcess(exitProcess);
        return SystemConstantUtil.SUCCESS;
    }

    private void sendMail(List<Long> listOfUserIdForSendEmail, ExitProcess exitProcess, ExitProcess oldExitProcess, String mailFormatName) throws UserManagementException {
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
                    User user = userService.getUserbyId(exitProcess.getUserId(), false, false, true, false);
                    String employeeName = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    String approver = "";

                    if (exitProcess.getResponseBy() != null) {
                        user = userService.getUserbyId(exitProcess.getResponseBy(), false, false, true, false);
                        approver = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    } else if (oldExitProcess != null && oldExitProcess.getResponseBy() != null) {
                        user = userService.getUserbyId(oldExitProcess.getResponseBy(), false, false, true, false);
                        approver = user.getContact().getFirstName() + " " + user.getContact().getLastName();
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String exitProcessInfo = this.setExitProcessInfo(exitProcess, oldExitProcess, employeeName, approver);

                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_FROM_DATE, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_TO_DATE, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_CURRENT_DATE, sdf.format(new Date()).toString());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_END_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_SHIFT_START_TIME, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_LATE_DURATION, "");
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_RESPONSE, exitProcess.getApprovalStatus());
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_INFO, exitProcessInfo);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_APPROVER, approver);
                    emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_DATE, sdf.format(exitProcess.getExpectedRelieveDate()));
                    String oldDate = null;
                    if (oldExitProcess != null) {
                        oldDate = sdf.format(oldExitProcess.getExpectedRelieveDate());
                        emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_OLD_DATE, sdf.format(oldExitProcess.getExpectedRelieveDate()));
                    } else {
                        emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_OLD_DATE, "");
                    }
                    emailBody = SystemFunctionUtil.replaceTextForEmail(emailBody, employeeName, null, null, null, null, exitProcessInfo, approver, sdf.format(exitProcess.getExpectedRelieveDate()), exitProcess.getApprovalStatus(), oldDate, null, null, null, null);

                    String subject = emailFormat.getSubject();

                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_DATE, sdf.format(exitProcess.getExpectedRelieveDate()));
                    subject = subject.replaceAll(SystemConstantUtil.EMAIL_APPROVER, approver);
                    subject = SystemFunctionUtil.replaceTextForEmail(subject, employeeName, null, null, null, null, exitProcessInfo, approver, sdf.format(exitProcess.getExpectedRelieveDate()), exitProcess.getApprovalStatus(), oldDate, null, null, null, null);

                    if (exitProcess.getApprovalStatus().equals(SystemConstantUtil.PENDING)) {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        emailBody += "<br/><form action=\"http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/exitProcessResponse\" method=\"post\"><table>";
                        emailBody += "<input type=\"hidden\" name=\"exitProcessId\" value=\"" + exitProcess.getExitId() + "\" /><tr><td>Comment: </td><td><textarea name=\"comment\"</td></tr><tr><td colsapan='2'><input type=\"submit\" value=\"Approve\" name=\"type\"/> <input type=\"submit\" value=\"Disapprove\" name=\"type\"/></td></form>";
                    }

                    Attachment[] attachments;
                    if (oldExitProcess != null) {
                        attachments = new Attachment[2];
                        attachments[0] = new Attachment();
                        attachments[1] = new Attachment();
                        attachments[0].setFileName(exitProcess.getDocName());
                        attachments[0].setFileContent(exitProcess.getDocData());
                        attachments[1].setFileName("old-" + oldExitProcess.getDocName());
                        attachments[1].setFileContent(oldExitProcess.getDocData());

                    } else {
                        attachments = new Attachment[1];
                        attachments[0] = new Attachment();
                        attachments[0].setFileName(exitProcess.getDocName());
                        attachments[0].setFileContent(exitProcess.getDocData());
                    }

                    userTransformerBean.sendMail(subject, emailBody, to, false, attachments);
                }
            }
        }
    }

    private void sendMailToAdmin(ExitProcess exitProcess, ExitProcess oldExitProcess, String emailFormat) throws UserManagementException {
        List<User> adminUserDetailList = userService.getUsersByType(SystemConstantUtil.ROLE_SUPER_ADMIN, true, false, false, false, false);
        if (adminUserDetailList != null && !adminUserDetailList.isEmpty()) {
            List<Long> adminList = new ArrayList<Long>();
            for (User user : adminUserDetailList) {
                if (user.getId() != loginDataBean.getId()) {
                    adminList.add(user.getId());
                }
            }
            System.out.println("Admin List size:" + adminList.size());
            this.sendMail(adminList, exitProcess, oldExitProcess, emailFormat);
        }
    }

    private String setExitProcessInfo(ExitProcess exitProcess, ExitProcess oldExitProcess, String empName, String responseEmpName) {
        StringBuilder message = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (oldExitProcess != null) {
            message.append("<br/>Old Exit process detail:<br/>");
            message.append("<table width='100%' border='0' cellpadding='7px' cellspacing='0'>");
            message.append("<tr><th width='25%' align='right'> <strong>Employee Name : &nbsp;</strong> </th><td width='75%'>").append(empName).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Description : &nbsp;</strong> </th><td>").append(oldExitProcess.getReason().toString()).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Expected Relieve Date : &nbsp;</strong> </th><td>").append(sdf.format(oldExitProcess.getExpectedRelieveDate())).append("</td></tr>");
            if (oldExitProcess.getResponseBy() != null) {
                message.append("<tr><th colspan=2><hr/></th></tr>");
                message.append("<tr><th align='right'> <strong>Response By : &nbsp;</strong> </th><td>").append(responseEmpName).append("</td></tr>");
                message.append("<tr><th align='right'> <strong>Comment : &nbsp;</strong> </th><td>").append(oldExitProcess.getAdminComment()).append("</td></tr>");
            }
            message.append("</table>");
        }

        message.append("<table width='100%' border='0' cellpadding='7px' cellspacing='0'>");
        message.append("<tr><th width='25%' align='right'> <strong>Employee Name : &nbsp;</strong> </th><td width='75%'>").append(empName).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Description : &nbsp;</strong> </th><td>").append(exitProcess.getReason().toString()).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Expected Relieve Date : &nbsp;</strong> </th><td>").append(sdf.format(exitProcess.getExpectedRelieveDate())).append("</td></tr>");
        if (exitProcess.getResponseBy() != null) {
            message.append("<tr><th colspan=2><hr/></th></tr>");
            message.append("<tr><th align='right'> <strong>Response By : &nbsp;</strong> </th><td>").append(responseEmpName).append("</td></tr>");
            message.append("<tr><th align='right'> <strong>Comment : &nbsp;</strong> </th><td>").append(exitProcess.getAdminComment()).append("</td></tr>");
        }
        message.append("</table>");
        return message.toString();
    }
}
