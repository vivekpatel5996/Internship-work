/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.EmailFormatService;
import com.argusoft.ars.model.EmailFormat;
import com.argusoft.ars.web.usermanagement.databean.EmailDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author argus2
 */
@ManagedBean(name = "emailFormatTransformerBean")
@RequestScoped
public class EmailFormatTransformerBean {

    @ManagedProperty(value = "#{emailFormatService}")
    private EmailFormatService emailFormatService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Other properties
    private static final Logger log = Logger.getLogger(EmailFormatTransformerBean.class);

    public EmailFormatService getEmailFormatService() {
        return emailFormatService;
    }

    public void setEmailFormatService(EmailFormatService emailFormateService) {
        this.emailFormatService = emailFormateService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    /**
     * Method to convert EmailFormatDataBean to EmailFormat Model.
     *
     */
    private EmailFormat convertEmailFormatDataBeanToEmailFormatModel(EmailDataBean emailFormatDataBean, EmailFormat emailFormat) {
        emailFormat.setBcc(emailFormatDataBean.getBcc());
        emailFormat.setCc(emailFormatDataBean.getCc());
        emailFormat.setBody(emailFormatDataBean.getBody());
        emailFormat.setCreatedBy(loginDataBean.getId());
        emailFormat.setCreatedDate(new Date());
        emailFormat.setEmailFormatName(emailFormatDataBean.getFormatName());
        emailFormat.setFooter(emailFormatDataBean.getFooter());
        emailFormat.setSubject(emailFormatDataBean.getSubject());
        emailFormat.setIsActive(true);
        emailFormat.setIsArchive(true);
        emailFormat.setCreatedDate(new Date());
        return emailFormat;
    }

    /**
     * Method to convert EmailFormat Model to EmailFormatDataBean.
     */
    private EmailDataBean convertEmailFormatModelToEmailFormatDataBean(EmailFormat emailFormat) {
        try {
            EmailDataBean emailDataBean = new EmailDataBean();
            emailDataBean.setBcc(emailFormat.getBcc());
            emailDataBean.setBody(emailFormat.getBody());
            emailDataBean.setCc(emailFormat.getCc());
            emailDataBean.setFooter(emailFormat.getFooter());
            emailDataBean.setFormatName(emailFormat.getEmailFormatName());
            emailDataBean.setSubject(emailFormat.getSubject());
            emailDataBean.setEmailId(emailFormat.getEmailFormatId());
            return emailDataBean;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive all active EmailFormat
     */
    public List<EmailDataBean> retrieveAllEmailFormat() {
        List<EmailFormat> emailFormatList = emailFormatService.retrieveAllEmailFormat();
        if (emailFormatList != null) {
            List<EmailDataBean> emailFormatDataBeansList = new ArrayList<EmailDataBean>();

            for (EmailFormat emailFormat : emailFormatList) {
                EmailDataBean emailFormatDataBean = convertEmailFormatModelToEmailFormatDataBean(emailFormat);
                emailFormatDataBeansList.add(emailFormatDataBean);

            }
            return emailFormatDataBeansList;
        }
        return null;
    }

    /**
     * Method to Add Email Format object.
     *
     */
    public String createEmailFormat(EmailDataBean emailDataBean) {
        try {
            boolean isEmailFormatAvailable = isEmailFormatAvailable(emailDataBean.getFormatName().trim());
            if (!isEmailFormatAvailable) {
                EmailFormat emailFormat = convertEmailFormatDataBeanToEmailFormatModel(emailDataBean, new EmailFormat());
                this.emailFormatService.createEmailFormat(emailFormat);
                return SystemConstantUtil.SUCCESS;
            } else {
                return SystemConstantUtil.REPEAT;
            }
        } catch (Exception e) {
            System.out.println("Add Email format Catch");
            System.out.println(e);
            return e.toString();
        }
    }

    private boolean isEmailFormatAvailable(String formatName) {
        return emailFormatService.isFormatNameAvailable(formatName);
    }

    public String deleteEmailFormat(EmailDataBean emailDataBean) {
        try {
            System.out.println("delete emailformat transformerbean");
            EmailFormat emailFormat = emailFormatService.retrieveEmailFormatById(emailDataBean.getEmailId());
            emailFormat.setIsActive(false);
            emailFormatService.updateEmailFormat(emailFormat);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }

    }

    /**
     * Method to Retrive Email format Detail by emailFormatId
     */
    public EmailDataBean retrieveEmailFormatDetail(Long emailId) {
        EmailFormat emailFormat = emailFormatService.retrieveEmailFormatById(emailId);
        EmailDataBean emailDataBean = convertEmailFormatModelToEmailFormatDataBean(emailFormat);
        return emailDataBean;
    }

    /**
     * Method to Update Email Format Detail
     */
    public String updateEmailFormat(EmailDataBean emailDataBean) {
        try {
            System.out.println("EmailFormat transformerbean update emailformat");
            EmailFormat emailFormat = emailFormatService.retrieveEmailFormatById(emailDataBean.getEmailId());
           // boolean isEmailFormatAvailable = isEmailFormatAvailable(emailDataBean.getFormatName().trim());
          //  if (isEmailFormatAvailable) {
                EmailFormat updatedEmailFormat = convertEmailFormatDataBeanToEmailFormatModel(emailDataBean, emailFormat);
                emailFormatService.updateEmailFormat(updatedEmailFormat);
                return SystemConstantUtil.SUCCESS;
          //  } else {
              //  return "Email format name already exists";
           // }
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }
}
