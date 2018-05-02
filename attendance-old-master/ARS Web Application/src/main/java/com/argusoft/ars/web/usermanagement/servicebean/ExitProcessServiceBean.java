/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.ExitProcessDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.ExitProcessTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import java.io.IOException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "exitProcessServiceBean")
@RequestScoped
public class ExitProcessServiceBean {

    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{exitProcessDataBean}")
    private ExitProcessDataBean exitProcessDataBean;
    @ManagedProperty(value = "#{exitProcessTransformerBean}")
    private ExitProcessTransformerBean exitProcessTransformerBean;

    public ExitProcessDataBean getExitProcessDataBean() {
        return exitProcessDataBean;
    }

    public void setExitProcessDataBean(ExitProcessDataBean exitProcessDataBean) {
        this.exitProcessDataBean = exitProcessDataBean;
    }

    public ExitProcessTransformerBean getExitProcessTransformerBean() {
        return exitProcessTransformerBean;
    }

    public void setExitProcessTransformerBean(ExitProcessTransformerBean exitProcessTransformerBean) {
        this.exitProcessTransformerBean = exitProcessTransformerBean;
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

    public void retrievePersonalExitProcessDetail() {
        try {
            System.out.println("****************** Retrive Personal ExitProcessDetail *********");
            exitProcessDataBean.setExitProcessDataBean(exitProcessTransformerBean.retrieveExitprocessDetail());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveExitProcressDetailByExitId() {
        try {
            System.out.println("************************** Retrive Exit Process Detail by Exit Id **************");
            exitProcessDataBean.setExitProcessDataBean(exitProcessTransformerBean.retrieveExitprocessDetailByExitId(exitProcessDataBean.getExitId()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void submitResign() {
        try {
            System.out.println("************************Submit Resignation**************************");
            System.out.println("Discription:" + exitProcessDataBean.getFileData());
            System.out.println("Discription:" + exitProcessDataBean.getDiscription());
            System.out.println("Discription:" + exitProcessDataBean.getExpectedReliveDate());

            String status = exitProcessTransformerBean.submitResignation(exitProcessDataBean);
            if (status.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Ressign apply successfully !!!!!!!!");
                messageDataBean.setIsSuccess(Boolean.TRUE);
            } else {
                messageDataBean.setMessage(status);
                messageDataBean.setIsSuccess(Boolean.FALSE);
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void cancelResign() {
        try {
            System.out.println("************************Cancel Resignation**************************");
            System.out.println(exitProcessDataBean.getExitId());
            String status = exitProcessTransformerBean.cancelResignation(exitProcessDataBean.getExitId());
            messageDataBean.setMessage(status);
            messageDataBean.setIsSuccess(Boolean.TRUE);
            exitProcessDataBean.setNull();
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void setFlag() {
        messageDataBean.setIsSuccess(true);
    }

    public void updateResign() {
        try {
            System.out.println("************************Update Resignation**************************");
            String status = exitProcessTransformerBean.updateResignation(exitProcessDataBean);
            if (status.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Ressign updated successfully !!!!!!!!");
                messageDataBean.setIsSuccess(Boolean.TRUE);
            } else {
                messageDataBean.setMessage(status);
                messageDataBean.setIsSuccess(Boolean.FALSE);
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void retrievePandingResignNotification() {
        System.out.println("************************ Retrive Panding Resignation List *****************");
        try {
            systemResultViewUtil.setExitProcessDataBeansList(exitProcessTransformerBean.retrievePandingResignNotification());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void approveResign() {
        System.out.println("*********************** Approve Resign *****************************");
        try {
            System.out.println("Admin Comment:" + exitProcessDataBean.getAdminComment());
            String response = exitProcessTransformerBean.aprroveResign(exitProcessDataBean.getExitId(), exitProcessDataBean.getAdminComment(),exitProcessDataBean.getActualReliveDate());
            messageDataBean.setMessage(response);
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void disApproveResign() {
        System.out.println("*********************** Disapprove Resign *****************************");
        try {
            String response = exitProcessTransformerBean.disAprroveResign(exitProcessDataBean.getExitId(), exitProcessDataBean.getAdminComment());
            messageDataBean.setMessage(response);
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void retrieveResignResponseNotification() {
        System.out.println("*********************retrieveResignResponseNotification************************");
        try {
            systemResultViewUtil.setResignationResponseNotificationList(exitProcessTransformerBean.retrieveResignationResponseNotification());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void cancelResignNotification() {
        System.out.println("*********************retrieveResignResponseNotification************************");
        try {
            systemResultViewUtil.setCancelResignationNotificationList(exitProcessTransformerBean.retrieveCancelResignationNotification());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void archiveResponseResignation() {
        System.out.println("************************Archive Response Resignation **************************");
        try {
            String status = exitProcessTransformerBean.archiveResponseResignation(exitProcessDataBean.getExitId());
            if (status.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Ressign response archive successfully !!!!!!!!");
                messageDataBean.setIsSuccess(Boolean.TRUE);
            } else {
                messageDataBean.setMessage(status);
                messageDataBean.setIsSuccess(Boolean.FALSE);
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void archiveCancelResignationNotification() {
        System.out.println("************************Archive Response Resignation **************************");
        try {
            String status = exitProcessTransformerBean.archiveCancelResignationNotification(exitProcessDataBean.getExitId());
            if (status.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Cancel Resignation notification archive successfully !!!!!!!!");
                messageDataBean.setIsSuccess(Boolean.TRUE);
            } else {
                messageDataBean.setMessage(status);
                messageDataBean.setIsSuccess(Boolean.FALSE);
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        exitProcessDataBean.setFileName(item.getName());
        exitProcessDataBean.setFileData(item.getData());
    }

    public void clearFile() {
        exitProcessDataBean.setFileData(null);
        exitProcessDataBean.setFileName(null);
    }

    public void downloadFile() {
        final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=" + exitProcessDataBean.getFileName()); // or whatever type you're sending back
        try {
            response.getOutputStream().write(exitProcessDataBean.getFileData()); // from the UploadDetails bean
            response.setContentLength(exitProcessDataBean.getFileData().length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void responseExitProcess() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (facesContext.getExternalContext().getRequestParameterMap().get("exitProcessId") != null) {
                String responseType = (String) facesContext.getExternalContext().getRequestParameterMap().get("type");
                Long exitProcessId = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("exitProcessId"));
                if (responseType.equals(SystemConstantUtil.APPROVE)) {
//                    String response = exitProcessTransformerBean.aprroveResign(exitProcessId, (String) facesContext.getExternalContext().getRequestParameterMap().get("comment"),(Date)facesContext.getExternalContext().getRequestParameterMap().get("actual_relieve_date"));
//                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    exitProcessDataBean.setExitProcessDataBean(exitProcessTransformerBean.retrieveExitprocessDetailByExitId(exitProcessId));
                } else if (responseType.equals(SystemConstantUtil.DISAPPROVE)) {
                    String response = exitProcessTransformerBean.disAprroveResign(exitProcessId, (String) facesContext.getExternalContext().getRequestParameterMap().get("comment"));
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    exitProcessDataBean.setExitProcessDataBean(exitProcessTransformerBean.retrieveExitprocessDetailByExitId(exitProcessId));
                    
                }
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect(SystemConstantUtil.ROLE_SUPER_ADMIN_PAGE + ".jsf");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
        }
    }
}
