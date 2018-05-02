/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.FeatureDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.MasterDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.RoleDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.RoleTransformerBean;
import com.argusoft.ars.web.usermanagement.transformerbean.UserTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author dmoteria
 */
@ManagedBean(name = "roleServiceBean")
@RequestScoped
public class RoleServiceBean {

    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{roleDataBean}")
    private RoleDataBean roleDataBean;
    @ManagedProperty(value = "#{roleTransformerBean}")
    private RoleTransformerBean roleTransformerBean;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    @ManagedProperty(value = "#{masterDataBean}")
    private MasterDataBean masterDataBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    private List<Long> featureIdList;
    private List<SelectItem> sourceFeatureLists;

    public MasterDataBean getMasterDataBean() {
        return masterDataBean;
    }

    public void setMasterDataBean(MasterDataBean masterDataBean) {
        this.masterDataBean = masterDataBean;
    }

    public UserTransformerBean getUserTransformerBean() {
        return userTransformerBean;
    }

    public void setUserTransformerBean(UserTransformerBean userTransformerBean) {
        this.userTransformerBean = userTransformerBean;
    }

    public List<Long> getFeatureIdList() {
        return featureIdList;
    }

    public void setFeatureIdList(List<Long> featureIdList) {
        this.featureIdList = featureIdList;
    }

    public RoleTransformerBean getRoleTransformerBean() {
        return roleTransformerBean;
    }

    public void setRoleTransformerBean(RoleTransformerBean roleTransformerBean) {
        this.roleTransformerBean = roleTransformerBean;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public RoleDataBean getRoleDataBean() {
        return roleDataBean;
    }

    public void setRoleDataBean(RoleDataBean roleDataBean) {
        this.roleDataBean = roleDataBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    public void retrieveAllRoles() {
        systemResultViewUtil.setRoleDataBeanList(roleTransformerBean.retrieveAllRoles(null, null, false, false));
        if (systemResultViewUtil.getRoleDataBeanList() == null || systemResultViewUtil.getRoleDataBeanList().isEmpty()) {
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage("No roles exist.");
        }
    }

    public List<SelectItem> getSourceFeatureLists() {
        List<FeatureDataBean> featureDataBeans = this.userTransformerBean.retrieveAllFeatures(Boolean.TRUE);
        FeatureDataBeanSort featureDataBeanSort = new FeatureDataBeanSort();
        Collections.sort(featureDataBeans, featureDataBeanSort);
        if (featureDataBeans != null && featureDataBeans.size() > 0) {
            sourceFeatureLists = new LinkedList<SelectItem>();
            for (FeatureDataBean featureData : featureDataBeans) {
                sourceFeatureLists.add(new SelectItem(featureData.getFeatureId(), featureData.getFeatureName()));
            }
        }
        return sourceFeatureLists;
    }

    public void setSourceFeatureLists(List<SelectItem> sourceFeatureLists) {
        this.sourceFeatureLists = sourceFeatureLists;
    }

    public String navigateToCreateRole() {
        return "createRole?faces-redirect=true";
    }

    public void retrieveRoleInfo() {
        if (this.masterDataBean.getRoleId() != null) {
            this.roleDataBean = roleTransformerBean.retrieveRoleById(masterDataBean.getRoleId(), true, false, this.roleDataBean);
            List<FeatureDataBean> targetFeatureList = this.roleDataBean.getPermissionList();
            List<FeatureDataBean> featureDataBeanList = userTransformerBean.retrieveAllFeatures(Boolean.TRUE);
            this.sourceFeatureLists = new LinkedList<SelectItem>();
            this.featureIdList = new LinkedList<Long>();
            if (featureDataBeanList != null && featureDataBeanList.size() > 0 && targetFeatureList != null && targetFeatureList.size() > 0) {
                boolean isExist;
                for (FeatureDataBean featureData : featureDataBeanList) {
                    isExist = false;
                    for (FeatureDataBean fdb : targetFeatureList) {
                        if (featureData.getFeatureId().equals(fdb.getFeatureId())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        this.sourceFeatureLists.add(new SelectItem(featureData.getFeatureId(), featureData.getFeatureName()));
                    } else {
                        this.featureIdList.add(featureData.getFeatureId());
                    }
                }
            }
        }
    }

    public String createRole() {
        String result = null;
        boolean isExist = this.isRoleExist();
        if (!isExist) {
            List<FeatureDataBean> targetFeatureList = new LinkedList<FeatureDataBean>();
            for (Long featureId : this.featureIdList) {
                FeatureDataBean featureDataBean = new FeatureDataBean();
                featureDataBean.setFeatureId(featureId);
                if (featureDataBean != null) {
                    targetFeatureList.add(featureDataBean);
                }
            }
            this.roleDataBean.setPermissionList(targetFeatureList);
            Long roleId = roleTransformerBean.createRole(roleDataBean);
            if (roleId != null) {
                messageDataBean.setIsSuccess(Boolean.TRUE);
                messageDataBean.setMessage("Role created successfully");
                SystemFunctionUtil.getFlashScope().put("messageDataBean", messageDataBean);
                result = "searchRole?faces-redirect=true";
            } else {
                messageDataBean.setIsSuccess(Boolean.FALSE);
                messageDataBean.setMessage("Role could not be created");
            }
        } else {
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage("Role with same name already exist");
        }
        return result;
    }

    public String updateRole() {
        String result = null;
        if (masterDataBean.getRoleId() != null && this.roleDataBean != null) {
            boolean isRoleNameExist = false;
            if (!masterDataBean.getRoleName().equalsIgnoreCase(this.roleDataBean.getRoleName())) {
                isRoleNameExist = this.roleTransformerBean.isRoleNameExistForUpdateRole(this.roleDataBean.getRoleName(), masterDataBean.getRoleId());
            }
            if (!isRoleNameExist) {
                RoleDataBean oldRoleDataBean = roleTransformerBean.retrieveRoleById(masterDataBean.getRoleId(), true, false, new RoleDataBean());
                List<FeatureDataBean> targetFeatureList = new LinkedList<FeatureDataBean>();
                for (Long featureId : this.featureIdList) {
                    FeatureDataBean featureDataBean = new FeatureDataBean();
                    featureDataBean.setFeatureId(featureId);
                    if (featureDataBean != null) {
                        targetFeatureList.add(featureDataBean);
                    }
                }
                this.roleDataBean.setPermissionList(targetFeatureList);
                String response = roleTransformerBean.updateRole(this.roleDataBean);
                if (response.equalsIgnoreCase(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    messageDataBean.setMessage("Role modified successfully.");
                    SystemFunctionUtil.getFlashScope().put("messageDataBean", messageDataBean);
                    result = "searchRole?faces-redirect=true";
                } else {
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                    messageDataBean.setMessage("Role could not be modified.");
                }
            } else {
                messageDataBean.setIsSuccess(Boolean.FALSE);
                messageDataBean.setMessage("Role with same name already exist.");
            }
        }
        return result;
    }

    private Boolean isRoleExist() {
        boolean isExist = false;
        if (this.roleDataBean.getRoleName() != null || !this.roleDataBean.getRoleName().equalsIgnoreCase("")) {
            isExist = roleTransformerBean.isRoleExist(this.roleDataBean.getRoleName().trim());
        }
        return isExist;
    }

    class FeatureDataBeanSort implements Comparator<FeatureDataBean> {

        @Override
        public int compare(FeatureDataBean featureDataBeanONE, FeatureDataBean featureDataBeanTWO) {
            return featureDataBeanONE.getFeatureName().compareTo(featureDataBeanTWO.getFeatureName());
        }
    }

    public String navigateToUpdateRole() {
        return "updateRole?faces-redirect=true";
    }

    public void changeRoleStatus() {
        this.roleTransformerBean.changeRoleStatus();
    }
}
