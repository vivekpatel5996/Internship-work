/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;

/**
 *
 * @author npadharia
 */
@ManagedBean(name = "roleDataBean")
@RequestScoped
public class RoleDataBean {

    @Pattern(regexp = "([a-zA-Z]+(([\\sa-zA-Z0-9])*))$", message = "{Entervalidrolename}")
    private String roleName;
    private String roleDescription;
    private Long roleId;
    private String status;
    private Boolean isActive;
    private Boolean isDefaultRole;
    private List<FeatureDataBean> permissionList;
    private List<Long> featureIdList;

    public List<Long> getFeatureIdList() {
        return featureIdList;
    }

    public void setFeatureIdList(List<Long> featureIdList) {
        this.featureIdList = featureIdList;
    }

    public List<FeatureDataBean> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<FeatureDataBean> permissionList) {
        this.permissionList = permissionList;
    }

    public RoleDataBean(Long roleId, String roleName) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoleDataBean() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDefaultRole() {
        return isDefaultRole;
    }

    public void setIsDefaultRole(Boolean isDefaultRole) {
        this.isDefaultRole = isDefaultRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoleDataBean other = (RoleDataBean) obj;
        if ((this.roleName == null) ? (other.roleName != null) : !this.roleName.equals(other.roleName)) {
            return false;
        }
        if (this.roleId != other.roleId && (this.roleId == null || !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.roleName != null ? this.roleName.hashCode() : 0);
        hash = 41 * hash + (this.roleId != null ? this.roleId.hashCode() : 0);
        return hash;
    }
}

