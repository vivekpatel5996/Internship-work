/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemConfigurationDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.SystemConfigurationTransformerBean;
import com.argusoft.ars.web.util.SystemResultSessionUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.springframework.util.StringUtils;

/**
 *
 * @author mpritmani
 */
@ManagedBean(name = "systemConfigurationServiceBean")
@RequestScoped
public class SystemConfigurationServiceBean {

    @ManagedProperty(value = "#{systemConfigurationDataBean}")
    private SystemConfigurationDataBean systemConfigurationDataBean;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;

    @ManagedProperty(value = "#{systemConfigurationTransformerBean}")
    private SystemConfigurationTransformerBean systemConfigurationTransformerBean;
    
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{systemResultSessionUtil}")
    private SystemResultSessionUtil systemResultSessionUtil;

    

    public SystemConfigurationDataBean getSystemConfigurationDataBean() {
        return systemConfigurationDataBean;
    }

    public void setSystemConfigurationDataBean(SystemConfigurationDataBean systemConfigurationDataBean) {
        this.systemConfigurationDataBean = systemConfigurationDataBean;
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

    public SystemResultSessionUtil getSystemResultSessionUtil() {
        return systemResultSessionUtil;
    }

    public void setSystemResultSessionUtil(SystemResultSessionUtil systemResultSessionUtil) {
        this.systemResultSessionUtil = systemResultSessionUtil;
    }    

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public void createSystemConfiguration() {
        try {
            if (systemConfigurationDataBean != null) {
                systemConfigurationTransformerBean.createSystemConfiguration(systemConfigurationDataBean);
            }
            messageDataBean.setMessage("SystemConfiguration Added Successfully !!!!");
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            messageDataBean.setMessage("SystemConfiguration already Exists or An Error occured !!!!");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
        systemConfigurationDataBean.setKeyValue(null);
        systemConfigurationDataBean.setSystemKey(null);
    }

    public void retrieveSystemConfiguration() {
        try {
            systemResultViewUtil.setSystemConfigurationDataBeanList(systemConfigurationTransformerBean.retrieveSystemConfigurations());
            System.out.println("-------in retrieve method");
        } catch (Exception e) {
        }
    }
    
    public void retrieveType(String type) {
        try {
            systemResultViewUtil.setSystemConfigurationDataBeanList(systemConfigurationTransformerBean.retrieveType(type));
//            System.out.println("-------in retrieve method");
        } catch (Exception e) {
        }
    }
    
    

    public void deleteSystemConfiguration() {
        try {
            systemConfigurationTransformerBean.deleteSystemConfiguration(systemResultSessionUtil.getKey());
            messageDataBean.setMessage("SystemConfiguration Deleted Successfully !!!!");
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {            
            messageDataBean.setMessage("Error in Deleting SystemConfiguration !!!!");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void updateSystemConfiguration() {
        try {
            if (!StringUtils.hasText(systemResultSessionUtil.getValue())) {
                messageDataBean.setMessage("Property Value Required !!!!");
                messageDataBean.setIsSuccess(Boolean.FALSE);
                return;
            }
            systemConfigurationTransformerBean.updateSystemConfiguration(systemResultSessionUtil.getKey(), systemResultSessionUtil.getValue());
            messageDataBean.setMessage("SystemConfiguration Updated Successfully !!!!");
            messageDataBean.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            messageDataBean.setMessage("Error in Updating SystemConfiguration !!!!");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }
}
