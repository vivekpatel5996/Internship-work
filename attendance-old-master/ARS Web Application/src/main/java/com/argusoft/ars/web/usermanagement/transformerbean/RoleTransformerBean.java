/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.web.usermanagement.databean.FeatureDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.MasterDataBean;
import com.argusoft.ars.web.usermanagement.databean.RoleDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.usermanagement.common.core.RoleService;
import com.argusoft.usermanagement.common.model.Feature;
import com.argusoft.usermanagement.common.model.Role;
import com.argusoft.usermanagement.common.model.RoleFeature;
import com.argusoft.usermanagement.common.model.RoleFeaturePK;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author npadharia
 */
@ManagedBean(name = "roleTransformerBean")
@RequestScoped
public class RoleTransformerBean {

    @ManagedProperty(value = "#{roleService}")
    private RoleService roleService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{masterDataBean}")
    private MasterDataBean masterDataBean;

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public MasterDataBean getMasterDataBean() {
        return masterDataBean;
    }

    public void setMasterDataBean(MasterDataBean masterDataBean) {
        this.masterDataBean = masterDataBean;
    }

//    public List<RoleDataBean> retrieveRoles(String languageCode, Boolean isActive, boolean retrieveRoleFeatures, boolean retrieveUsers) {
//        List<RoleDataBean> roleDataBeans = null;
//        List<Role> roles = null;
//        roles = roleService.getAllRole(isActive, null, null, null, retrieveRoleFeatures, retrieveUsers);
//        if (roles != null && roles.size() > 0) {
//            roleDataBeans = new LinkedList<RoleDataBean>();
//            for (Role roleModel : roles) {
//                //if (roleModel.getIsActive() && !roleModel.getIsArchive()) {
//                RoleDataBean roleDataBean = new RoleDataBean();
//                roleDataBean = convertRoleModelToRoleDataBean(roleDataBean, roleModel);
//                roleDataBeans.add(roleDataBean);
//            }
//            //}
//        }
//        return roleDataBeans;
//    }

    private RoleDataBean convertRoleModelToRoleDataBean(RoleDataBean roleDataBean, Role roleModel) {
        roleDataBean.setRoleId(roleModel.getId());
        roleDataBean.setRoleName(roleModel.getName());
        // Default role provided
        roleDataBean.setRoleDescription(roleModel.getDescription());
        roleDataBean.setIsActive(roleModel.getIsActive());
        return roleDataBean;
    }

    public boolean isRoleExist(String roleName) {
        boolean isUserExists = false;
        isUserExists = roleService.isRoleExist(roleName, null, null, null);
        return isUserExists;
    }

    public Long createRole(RoleDataBean roleDataBean) {
        //        Adding role info to role model
        Role role = this.convertRoleDataBeanToRoleModel(roleDataBean, new Role(), "CREATE");
        //        Adding RoleFeatures to role model
        Set<RoleFeature> roleFeatureSet = null;
        List<FeatureDataBean> featureDataBeanList = roleDataBean.getPermissionList();
        if (featureDataBeanList != null && featureDataBeanList.size() > 0) {
            roleFeatureSet = new LinkedHashSet<RoleFeature>();
            for (FeatureDataBean featureDataBean : featureDataBeanList) {
                RoleFeature roleFeature = this.convertFeatureDataBeanToRoleFeatureModel(featureDataBean, new RoleFeature(), roleDataBean);
                roleFeatureSet.add(roleFeature);
            }
        }
        role.setRoleFeatureCollection(roleFeatureSet);
        //        Creating the role 
        Long roleId = roleService.createRole(role);
        return roleId;
    }

    private Role convertRoleDataBeanToRoleModel(RoleDataBean roleDataBean, Role roleModel, String operation) {
        if (operation != null && operation.equalsIgnoreCase("CREATE")) {
            roleModel.setCreatedBy(loginDataBean.getUserId());
            roleModel.setCreatedOn(new Date());
        } else if (operation != null && operation.equalsIgnoreCase("UPDATE")) {
            //roleModel = roleService.getRoleByRoleId(roleDataBean.getRoleId(), true, false);
            roleModel.setModifiedBy(loginDataBean.getUserId());
            roleModel.setModifiedOn(new Date());
        }
        if (roleDataBean.getRoleDescription() != null) {
            roleModel.setDescription(roleDataBean.getRoleDescription());
        }
        if (roleDataBean.getRoleId() != null) {
            roleModel.setId(roleDataBean.getRoleId());
        }
        if (roleDataBean.getRoleName() != null) {
            roleModel.setName(roleDataBean.getRoleName().trim());
        }
        if (roleDataBean.getStatus().equalsIgnoreCase(SystemConstantUtil.ACTIVE)) {
            roleModel.setIsActive(Boolean.TRUE);
        } else {
            roleModel.setIsActive(Boolean.FALSE);
        }
        return roleModel;
    }

    private RoleFeature convertFeatureDataBeanToRoleFeatureModel(FeatureDataBean featureDataBean, RoleFeature roleFeatureModel, RoleDataBean roleDataBean) {
        RoleFeaturePK roleFeaturePK = new RoleFeaturePK();
        if (roleDataBean.getRoleId() != null) {
            roleFeaturePK.setRole(roleDataBean.getRoleId());
        }
        if (featureDataBean.getFeatureId() != null) {
            roleFeaturePK.setFeature(featureDataBean.getFeatureId());
        }
        roleFeatureModel.setRoleFeaturePK(roleFeaturePK);
        roleFeatureModel.setFeature(new Feature(featureDataBean.getFeatureId()));
        return roleFeatureModel;
    }

    public List<RoleDataBean> retrieveAllRoles(String languageCode, Boolean isActive, boolean retrieveRoleFeatures, boolean retrieveUsers) {
        List<RoleDataBean> roleDataBeans = null;
        List<Role> roles = null;
        roles = roleService.getAllRole(isActive, null, null, null, retrieveRoleFeatures, retrieveUsers);
        if (roles != null && roles.size() > 0) {
            roleDataBeans = new LinkedList<RoleDataBean>();
            for (Role roleModel : roles) {
                RoleDataBean roleDataBean = new RoleDataBean();
                roleDataBean = convertRoleModelToRoleDataBean(roleDataBean, roleModel);
                if (languageCode == null || languageCode.trim().equalsIgnoreCase("")) {
                    roleDataBean.setRoleName(roleDataBean.getRoleName());
                } else {
                    roleDataBean.setRoleName(roleDataBean.getRoleName());
                }
                roleDataBeans.add(roleDataBean);
            }
        }
        return roleDataBeans;
    }

    public RoleDataBean retrieveRoleById(Long id, boolean retrieveRoleFeatures, boolean retrieveUsers, RoleDataBean roleDataBean) {
        if (id != null && !id.equals(new Long(0))) {
            Role roleModel = roleService.getRoleByRoleId(id, retrieveRoleFeatures, retrieveUsers);
            if (roleModel != null) {
                roleDataBean = convertRoleModelToRoleDataBean(roleDataBean, roleModel);
            }
        }
        return roleDataBean;
    }

    public Boolean isRoleNameExistForUpdateRole(String roleName, Long roletId) {
        Boolean isExist = Boolean.FALSE;
        Role role = roleService.getRoleByRoleName(roleName, false, false);
        if (role != null) {
            if (role.getId().equals(roletId)) {
                isExist = Boolean.FALSE;
            } else {
                isExist = Boolean.TRUE;
            }
        }
        return isExist;
    }

    public String updateRole(RoleDataBean roleDataBean) {
        String response = SystemConstantUtil.FAILURE;
        Role role = roleService.getRoleByRoleId(masterDataBean.getRoleId(), true, false);
        if (role != null) {
            role = this.convertRoleDataBeanToRoleModel(roleDataBean, role, "UPDATE");
            //        Adding RoleFeatures to role model
            Set<RoleFeature> roleFeatureSet = null;
            List<FeatureDataBean> featureDataBeanList = roleDataBean.getPermissionList();
            if (featureDataBeanList != null && featureDataBeanList.size() > 0) {
                roleFeatureSet = new LinkedHashSet<RoleFeature>();
                for (FeatureDataBean featureDataBean : featureDataBeanList) {
                    RoleFeature roleFeature = this.convertFeatureDataBeanToRoleFeatureModel(featureDataBean, new RoleFeature(), roleDataBean);
                    roleFeatureSet.add(roleFeature);
                }
            }
            role.setRoleFeatureCollection(roleFeatureSet);
            //   Updating role
            roleService.updateRole(role);
            response = SystemConstantUtil.SUCCESS;
        }
        return response;
    }

    public void changeRoleStatus() {
        if (masterDataBean.getRoleId() != null) {
            Role role = this.roleService.getRoleByRoleId(masterDataBean.getRoleId(), true, true);
            if (role != null) {
                role.setIsActive(!masterDataBean.isRoleStatus());
                this.roleService.updateRole(role);
            }
        }
    }
}
