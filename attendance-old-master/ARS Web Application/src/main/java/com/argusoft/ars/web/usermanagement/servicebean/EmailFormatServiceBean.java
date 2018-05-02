/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.EmailDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.EmailFormatTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "emailFormatServiceBean")
@RequestScoped
public class EmailFormatServiceBean {

    @ManagedProperty(value = "#{emailDataBean}")
    private EmailDataBean emailDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{emailFormatTransformerBean}")
    private EmailFormatTransformerBean emailFormatTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    private Long emailId;
    private String formatName;

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public EmailDataBean getEmailDataBean() {
        return emailDataBean;
    }

    public void setEmailDataBean(EmailDataBean emailDataBean) {
        this.emailDataBean = emailDataBean;
    }

    public EmailFormatTransformerBean getEmailFormatTransformerBean() {
        return emailFormatTransformerBean;
    }

    public void setEmailFormatTransformerBean(EmailFormatTransformerBean emailFormatTransformerBean) {
        this.emailFormatTransformerBean = emailFormatTransformerBean;
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

    /*
     * Method for Retrive EmailFormat List for dataTable
     */
    public void retrieveEmailFormatList() {
        try {
            systemResultViewUtil.setEmailFormatDataBeansList(emailFormatTransformerBean.retrieveAllEmailFormat());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
     * Method for Add EmailFormat
     */

    public void createEmailFormat() {
        System.out.println("in servicebean");
        try {
            if (emailDataBean != null) {
                String response = emailFormatTransformerBean.createEmailFormat(emailDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    System.out.println("EmailFormat added successfully.");
                    messageDataBean.setMessage("EmailFormat added successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    this.retrieveEmailFormatList();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    messageDataBean.setMessage("EmailFormat Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
            System.out.println(e);
        }

    }

    /*
     * Method for get email format detail.fillEmailFormatDetail
     */
    public void fillEmailFormatDetail() {
        EmailDataBean email = emailFormatTransformerBean.retrieveEmailFormatDetail(emailId);
        emailDataBean.setBcc(email.getBcc());
        emailDataBean.setBody(email.getBody());
        emailDataBean.setCc(email.getCc());
        emailDataBean.setEmailId(emailId);
        emailDataBean.setFooter(email.getFooter());
        emailDataBean.setFormatName(email.getFormatName());
        emailDataBean.setSubject(email.getSubject());
    }

    /*
     * Method for Delete EmailFormat
     */
    public void deleteEmailFormat() {
        try {
            System.out.println("********************ServiceBean=>deleteEmail Format********");
            if (emailDataBean != null) {
                String response = emailFormatTransformerBean.deleteEmailFormat(emailDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setMessage("Email Format deleted successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    this.retrieveEmailFormatList();
                } else {
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    /*
     * Method for updateEmailFormat
     */
    public void updateEmailFormat() {
        try {
            if (emailDataBean != null) {
                String response = emailFormatTransformerBean.updateEmailFormat(emailDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Email Format updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    this.retrieveEmailFormatList();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    messageDataBean.setMessage("Email Format Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    messageDataBean.setMessage("Error =" + response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error =>" + e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }

    }
}
