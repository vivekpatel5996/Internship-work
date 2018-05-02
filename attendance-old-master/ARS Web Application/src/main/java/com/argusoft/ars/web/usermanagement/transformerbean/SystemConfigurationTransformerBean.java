/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.web.usermanagement.databean.SystemConfigurationDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.usermanagement.common.core.SystemConfigurationService;
import com.argusoft.usermanagement.common.model.SystemConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for System Configuration.
 *
 * @author mpritmani
 */
@ManagedBean(name = "systemConfigurationTransformerBean")
@RequestScoped
public class SystemConfigurationTransformerBean {

    //  Core properties
    @ManagedProperty(value = "#{systemConfigurationService}")
    private SystemConfigurationService systemConfigurationService;
    //  Other properties
    private static final Logger log = Logger.getLogger(SystemConfigurationTransformerBean.class);

    public SystemConfigurationService getSystemConfigurationService() {
        return systemConfigurationService;
    }

    public void setSystemConfigurationService(SystemConfigurationService systemConfigurationService) {
        this.systemConfigurationService = systemConfigurationService;
    }

    /**
     * Method to convert SystemConfigurationDataBean to SystemConfiguration
     * Model.
     *
     * @param systemConfigurationDataBean
     * @param systemConfiguration
     * @return
     */
    private SystemConfiguration convertSystemConfigurationDataBeanToSystemConfigurationModel(SystemConfigurationDataBean systemConfigurationDataBean, SystemConfiguration systemConfiguration) {
        System.out.println(systemConfigurationDataBean.getKeyValue() + systemConfigurationDataBean.getSystemKey());
        systemConfiguration.setKeyValue(systemConfigurationDataBean.getKeyValue());
        systemConfiguration.setSystemKey(systemConfigurationDataBean.getSystemKey());

        return systemConfiguration;

    }

    /**
     * Method to convert SystemConfiguration Model to
     * SystemConfigurationDataBean.
     *
     * @param systemConfiguration
     * @param systemConfigurationDataBean
     * @return
     */
    private SystemConfigurationDataBean convertSystemConfigurationModelTosystemConfigurationDataBean(SystemConfiguration systemConfiguration, SystemConfigurationDataBean systemConfigurationDataBean) {
        if ((systemConfiguration.getSystemKey()).contains("LeavePolicy")) {
            systemConfigurationDataBean.setKeyValue(systemConfiguration.getKeyValue());
            String systemKeyValue = systemConfiguration.getSystemKey().substring(11);
            systemConfigurationDataBean.setSystemKey(systemKeyValue);
        } 
        else {
            systemConfigurationDataBean.setKeyValue(systemConfiguration.getKeyValue());
            systemConfigurationDataBean.setSystemKey(systemConfiguration.getSystemKey());
        }
        return systemConfigurationDataBean;
    }

    /**
     * Method to create SystemConfiguration object.
     *
     * @param systemConfigurationDataBean
     * @return
     */
    public String createSystemConfiguration(SystemConfigurationDataBean systemConfigurationDataBean) {
        String response = SystemConstantUtil.FAILURE;
        SystemConfiguration systemConfiguration = convertSystemConfigurationDataBeanToSystemConfigurationModel(systemConfigurationDataBean, new SystemConfiguration());
        log.info("systemConfigurationService object is " + systemConfigurationService);
        this.systemConfigurationService.createSystemConfiguration(systemConfiguration);
        response = SystemConstantUtil.SUCCESS;
        return response;
    }

    /**
     * Method to retrieve all SystemConfigration objects
     *
     * @return
     */
    public List<SystemConfigurationDataBean> retrieveSystemConfigurations() {
        List<SystemConfigurationDataBean> systemConfigurationDataBeanList = new ArrayList<SystemConfigurationDataBean>();

        Map<String, String> systemConfigurationMainMap = systemConfigurationService.retrieveAllSystemConfigurations();

        if (systemConfigurationMainMap != null && !systemConfigurationMainMap.isEmpty()) {
            Map<String, String> systemConfigurationMap = new TreeMap<String, String>(systemConfigurationMainMap);
            for (String key : systemConfigurationMap.keySet()) {
                String value = systemConfigurationMap.get(key);
                SystemConfigurationDataBean systemConfigurationDataBean = new SystemConfigurationDataBean();
                systemConfigurationDataBean.setSystemKey(key);
                systemConfigurationDataBean.setKeyValue(value);
                systemConfigurationDataBeanList.add(systemConfigurationDataBean);
            }
        }
        return systemConfigurationDataBeanList;
    }

    /**
     * Method to delete SystemConfiguration object.
     *
     * @param key
     */
    public void deleteSystemConfiguration(String key) {
        SystemConfiguration systemConfiguration = systemConfigurationService.retrieveSystemConfigurationByKey(key);
        if (systemConfiguration != null) {
            systemConfiguration.setIsActive(Boolean.FALSE);
            systemConfigurationService.updateSystemConfiguration(systemConfiguration);
        }
    }

    /**
     * Method to update SystemConfiguration object.
     *
     * @param key
     * @param newValue
     */
    public void updateSystemConfiguration(String key, String newValue) {
        SystemConfiguration systemConfiguration = systemConfigurationService.retrieveSystemConfigurationByKey(key);
        if (systemConfiguration != null) {
            systemConfiguration.setKeyValue(newValue);
            systemConfiguration.setIsActive(Boolean.TRUE);
            systemConfigurationService.updateSystemConfiguration(systemConfiguration);
        }
    }

    public List<SystemConfigurationDataBean> retrieveType(String type) {
        List<SystemConfigurationDataBean> systemConfigurationDataBeanList = new ArrayList<SystemConfigurationDataBean>();

        List<SystemConfiguration> typeList = systemConfigurationService.retrieveSystemConfigurationsBasedOnLikeKeySearch(type);

        for (SystemConfiguration systemConfiguration : typeList) {
            systemConfigurationDataBeanList.add(convertSystemConfigurationModelTosystemConfigurationDataBean(systemConfiguration, new SystemConfigurationDataBean()));
        }

        return systemConfigurationDataBeanList;
    }
}
