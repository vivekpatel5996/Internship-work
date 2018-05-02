/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.servicebean;

import com.argusoft.ars.web.leavemanagement.databean.ManualCardEntryDataBean;
import com.argusoft.ars.web.leavemanagement.transformerbean.ManualCardEntryTransformerBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.SystemConfigurationTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "manualCardEntryServiceBean")
@RequestScoped
public class ManualCardEntryServiceBean {

    @ManagedProperty(value = "#{manualCardEntryDataBean}")
    private ManualCardEntryDataBean manualCardEntryDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{manualCardEntryTransformerBean}")
    private ManualCardEntryTransformerBean manualCardEntryTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{systemConfigurationTransformerBean}")
    private SystemConfigurationTransformerBean systemConfigurationTransformerBean;
    private Long userId;
    private Long manualCardEntryId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getManualCardEntryId() {
        return manualCardEntryId;
    }

    public void setManualCardEntryId(Long manualCardEntryId) {
        this.manualCardEntryId = manualCardEntryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ManualCardEntryDataBean getManualCardEntryDataBean() {
        return manualCardEntryDataBean;
    }

    public void setManualCardEntryDataBean(ManualCardEntryDataBean manualCardEntryDataBean) {
        this.manualCardEntryDataBean = manualCardEntryDataBean;
    }

    public ManualCardEntryTransformerBean getManualCardEntryTransformerBean() {
        return manualCardEntryTransformerBean;
    }

    public void setManualCardEntryTransformerBean(ManualCardEntryTransformerBean manualCardEntryTransformerBean) {
        this.manualCardEntryTransformerBean = manualCardEntryTransformerBean;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public SystemConfigurationTransformerBean getSystemConfigurationTransformerBean() {
        return systemConfigurationTransformerBean;
    }

    public void setSystemConfigurationTransformerBean(SystemConfigurationTransformerBean systemConfigurationTransformerBean) {
        this.systemConfigurationTransformerBean = systemConfigurationTransformerBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    public void requestForManualCardEntry() {
        try {
            System.out.println("***************************requestForManualCardEntry************************");
            if (manualCardEntryDataBean.getInStatus() == false && manualCardEntryDataBean.getOutStatus() == false) {
                manualCardEntryDataBean.setInOutStatusValidation("Please select in/out state");
                messageDataBean.setIsSuccess(false);
            } else {
                String response = manualCardEntryTransformerBean.requestForManualCardEntry(manualCardEntryDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setIsSuccess(true);
                    messageDataBean.setMessage("Request for manual card entry apply successfully");
                    this.retrievePersonalManualCardEntryList();
                    manualCardEntryDataBean.setNull();
                } else {
                    messageDataBean.setIsSuccess(false);
                    messageDataBean.setMessage(response);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }

    public void approveMannualCardEntryRequest() {
        try {
            System.out.println("*******************************approveMannualCardEntryRequest*********************");
            System.out.println("Is Late Entry" + manualCardEntryDataBean.getIsLateEntry());
            String response = manualCardEntryTransformerBean.approveMannualCardEntryRequest(manualCardEntryDataBean.getManualCardEntryId(), manualCardEntryDataBean.getAdminComment(), manualCardEntryDataBean.getIsLateEntry());
            if (response.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setIsSuccess(true);
                messageDataBean.setMessage("Manual Card entry approve successfully");
                this.retrieveManualcardEntryPandingRequestList();
            } else {
                messageDataBean.setIsSuccess(false);
                messageDataBean.setMessage(response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }

    public void disApproveMannualCardEntryRequest() {
        try {
            System.out.println("*******************************disApproveMannualCardEntryRequest*********************");
            String response = manualCardEntryTransformerBean.disApproveMannualCardEntryRequest(manualCardEntryDataBean.getManualCardEntryId(), manualCardEntryDataBean.getAdminComment());
            if (response.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setIsSuccess(true);
                messageDataBean.setMessage("Manual Card entry disapprove successfully");
                this.retrieveManualcardEntryPandingRequestList();
            } else {
                messageDataBean.setIsSuccess(false);
                messageDataBean.setMessage(response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }

    public void archiveManualCardEntry() {
        try {
            System.out.println("************************************* archive Manual Card Entry ************************************");
            manualCardEntryTransformerBean.archiveManualCardEntry(manualCardEntryDataBean.getManualCardEntryId());
            retrieveNotArchiveManualcardEntryList();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void retrievePersonalManualCardEntryList() {
        try {
            System.out.println("*******************************retrievePersonalManualCardEntryList*********************");
            systemResultViewUtil.setManualCardEntryDataBeansList(manualCardEntryTransformerBean.retrievePersonalManualCardEntryList());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void retrieveManualcardEntryPandingRequestList() {
        try {
            System.out.println("*******************************retrieveManualcardEntryPandingRequestList*********************");
            systemResultViewUtil.setManualCardEntryDataBeansList(manualCardEntryTransformerBean.retrieveManualcardEntryPandingRequestList());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void retrieveNotArchiveManualcardEntryList() {
        try {
            System.out.println("*******************************retrieveNotArchiveManualcardEntryList****************************");
            systemResultViewUtil.setManualCardEntryDataBeansList(manualCardEntryTransformerBean.retrieveNotArchiveManualcardEntryList());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void retrieveManualCardEntryRequestDetail() {
        try {
            System.out.println("*******************************retrieveManualCardEntryRequest****************************");
            ManualCardEntryDataBean cardEntryDataBean = manualCardEntryTransformerBean.retrieveManualCardEntryRequestDetail(manualCardEntryId);
            manualCardEntryDataBean.setManualCardEntryDataBean(cardEntryDataBean);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void responseCardEntryRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (facesContext.getExternalContext().getRequestParameterMap().get("manualCardEntryId") != null) {
                manualCardEntryId = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("manualCardEntryId"));
                String comment = (String) facesContext.getExternalContext().getRequestParameterMap().get("comment");
                String responseType = (String) facesContext.getExternalContext().getRequestParameterMap().get("type");
                System.out.println("Mannual Card Entry:" + manualCardEntryId);
                System.out.println("Comment" + responseType);

                if (responseType.equals(SystemConstantUtil.APPROVE)) {
                    String lateEntry = (String) facesContext.getExternalContext().getRequestParameterMap().get("isLateEntry");
                    Boolean isLateEntry;
                    if ("true".equals(lateEntry)) {
                        isLateEntry = true;
                    } else {
                        isLateEntry = false;
                    }
                    System.out.println("isLate Entry:" + isLateEntry);
                    String response = manualCardEntryTransformerBean.approveMannualCardEntryRequest(manualCardEntryId, comment, isLateEntry);
                    if (response.equals(SystemConstantUtil.SUCCESS)) {
                        messageDataBean.setIsSuccess(true);
                        messageDataBean.setMessage("Manual Card entry approve successfully");
                        this.retrieveManualcardEntryPandingRequestList();
                    } else {
                        messageDataBean.setIsSuccess(false);
                        messageDataBean.setMessage(response);
                    }
                    this.retrieveManualCardEntryRequestDetail();
                } else if (responseType.equals(SystemConstantUtil.DISAPPROVE)) {
                    String response = manualCardEntryTransformerBean.disApproveMannualCardEntryRequest(manualCardEntryId, comment);
                    if (response.equals(SystemConstantUtil.SUCCESS)) {
                        messageDataBean.setIsSuccess(true);
                        messageDataBean.setMessage("Manual Card entry disapprove successfully");
                        this.retrieveManualcardEntryPandingRequestList();
                    } else {
                        messageDataBean.setIsSuccess(false);
                        messageDataBean.setMessage(response);
                    }
                    this.retrieveManualCardEntryRequestDetail();
                }
            } else {
//                FacesContext.getCurrentInstance().getExternalContext().dispatch(SystemConstantUtil.ROLE_MEMBER_PAGE);
                FacesContext.getCurrentInstance().getExternalContext().redirect(SystemConstantUtil.ROLE_SUPER_ADMIN_PAGE + ".jsf");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
