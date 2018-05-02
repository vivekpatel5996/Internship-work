/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.DepartmentService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.Department;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.web.usermanagement.databean.DepartmentDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.usermanagement.databean.UserDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import com.argusoft.usermanagement.common.model.UserContact;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for Department.
 *
 * @author Harshit
 */
@ManagedBean(name = "departmentTransformerBean")
@RequestScoped
public class DepartmentTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    //  Other properties
    private static final Logger log = Logger.getLogger(DepartmentTransformerBean.class);

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Method to convert DepartmentDataBean to Department Model.
     *
     */
    private Department convertDepartmentDataBeanToDepartmentModel(DepartmentDataBean departmentDataBean, Department department) throws UserManagementException {
        department.setDeptName(departmentDataBean.getDepName().trim());
        department.setCreatedBy(loginDataBean.getId());
        department.setIsActive(true);
        department.setIsArchive(true);
        department.setCreatedDate(new Date());
        return department;
    }

    /**
     * Method to convert Department Model to DepartmentDataBean.
     */
    private DepartmentDataBean convertDepartmentModelToDepartmentDataBean(Department department) {
        try {
            DepartmentDataBean departmentDataBean = new DepartmentDataBean();
            departmentDataBean.setDepName(department.getDeptName());
            departmentDataBean.setDepId(department.getDeptId());
            return departmentDataBean;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive all active Department
     */
    public List<DepartmentDataBean> retrieveAllDepartment() {
        List<Department> departmentList = departmentService.retrieveAllDepartment();
        if (departmentList != null) {
            List<DepartmentDataBean> departmentDataBeansList = new ArrayList<DepartmentDataBean>();
//            flag use for check whether all emplyee are in same department;
            Boolean flag = true;
            Integer noOfActiveUser = systemUserDetailService.noOfActiveUser();
            for (Department department : departmentList) {
                DepartmentDataBean departmentDataBean = convertDepartmentModelToDepartmentDataBean(department);
                if (flag) {
                    Integer noOfUserInDepartment = systemUserDetailService.noOfActiveUserInDepartment(department.getDeptId());
                    if (noOfActiveUser == noOfUserInDepartment) {
                        departmentDataBean.setAddToThisDisable(true);
                        flag = false;
                    } else {
                        departmentDataBean.setAddToThisDisable(false);
                    }
                    if (noOfUserInDepartment > 0) {
                        departmentDataBean.setChecked(true);
                    } else {
                        departmentDataBean.setChecked(false);
                    }
                } else {
                    departmentDataBean.setChecked(false);
                    departmentDataBean.setAddToThisDisable(false);
                }
                departmentDataBeansList.add(departmentDataBean);
            }
            return departmentDataBeansList;
        }
        return null;
    }

    /**
     * Method to Retrive Department Detail by Department Id
     */
    public DepartmentDataBean retrieveDepartmentDetail(Long depId) {
        Department department = departmentService.retrieveDepartmentById(depId);
        DepartmentDataBean departmentDataBean = convertDepartmentModelToDepartmentDataBean(department);
        return departmentDataBean;
    }

    /**
     * Method to Add Department object.
     *
     */
    public String createDepartment(DepartmentDataBean departmentDataBean) {
        try {
            boolean isDepNameAvailable = isDepNameAvailable(departmentDataBean.getDepName().trim());
            if (isDepNameAvailable) {
                Department department = convertDepartmentDataBeanToDepartmentModel(departmentDataBean, new Department());
                this.departmentService.createDepartment(department);
                return SystemConstantUtil.SUCCESS;
            } else {
                return SystemConstantUtil.REPEAT;
            }
        } catch (Exception e) {
            System.out.println("Add Department Catch");
            System.out.println(e);
            return e.toString();
        }
    }

    /**
     * Method to Update Department Detail
     */
    public String updateDepartment(DepartmentDataBean departmentDataBean) {
        try {
            Department department = departmentService.retrieveDepartmentById(departmentDataBean.getDepId());
            if (!department.getDeptName().equals(departmentDataBean.getDepName())) {
                boolean isDepNameAvailable = isDepNameAvailable(departmentDataBean.getDepName().trim());
                if (isDepNameAvailable) {
                    department.setDeptName(departmentDataBean.getDepName().trim());
                    departmentService.updateDepartment(department);
                    return SystemConstantUtil.SUCCESS;
                } else {
                    return SystemConstantUtil.REPEAT;
                }
            }
            return SystemConstantUtil.SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to Inactivate Department.
     */
    public String deleteDepartment(DepartmentDataBean departmentDataBean) {
        try {
            System.out.println("**************departmentTranformerBean=>deleteDepartment************");
            Department department = departmentService.retrieveDepartmentById(departmentDataBean.getDepId());
            department.setIsActive(false);
            departmentService.updateDepartment(department);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to check whether Department Name is already added in active or
     * not.
     */
    private boolean isDepNameAvailable(String depName) {
        return departmentService.isDepNameAvailable(depName);
    }

    public List<SystemUserDetailDataBean> retrieveAddToThisDepartmentDetail(Long depId) throws UserManagementException {
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailNotEqualByType(depId, "department");
        List<SystemUserDetailDataBean> systemUserDetailDataBeans = new ArrayList<SystemUserDetailDataBean>();
        if (systemUserDetails != null) {
            for (SystemUserDetail systemUserDetail : systemUserDetails) {
                SystemUserDetailDataBean systemUserDetailDataBean = convertSystemUserDetailModeltoSystemUserDetailDataBean(systemUserDetail, new SystemUserDetailDataBean());
                UserContact userContact = userService.getUserContactById(systemUserDetail.getUserId());
                systemUserDetailDataBean.setName(userContact.getFirstName() + " " + userContact.getLastName());
                systemUserDetailDataBean.setEmpId(systemUserDetail.getEmpId());
                systemUserDetailDataBeans.add(systemUserDetailDataBean);
            }
        }
        return systemUserDetailDataBeans;
    }

    private SystemUserDetailDataBean convertSystemUserDetailModeltoSystemUserDetailDataBean(SystemUserDetail systemUserDetail, SystemUserDetailDataBean systemUserDetailDataBean) {
        if (systemUserDetail.getDepId() != null) {
            systemUserDetailDataBean.setDepId(systemUserDetail.getDepId().getDeptId());
            systemUserDetailDataBean.setDepName(systemUserDetail.getDepId().getDeptName());
        }
        systemUserDetailDataBean.setUserId(systemUserDetail.getUserId());
        systemUserDetailDataBean.setChecked(false);
        return systemUserDetailDataBean;
    }

    public void addEmployeeToThisDepartment(SystemUserDetailDataBean systemUserDetailDataBean, Long depId) {
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(systemUserDetailDataBean.getUserId());
        Department department = departmentService.retrieveDepartmentById(depId);
        systemUserDetail.setDepId(department);
        systemUserDetailService.updatesystemUserDetail(systemUserDetail);
    }

    public List<SystemUserDetailDataBean> retrieveEmployeeByDepartment(Long depId) throws UserManagementException {
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailByType(depId, "department");
        List<SystemUserDetailDataBean> systemUserDetailDataBeans = new ArrayList<SystemUserDetailDataBean>();
        if (systemUserDetails != null) {
            for (SystemUserDetail systemUserDetail : systemUserDetails) {
                SystemUserDetailDataBean systemUserDetailDataBean = convertSystemUserDetailModeltoSystemUserDetailDataBean(systemUserDetail, new SystemUserDetailDataBean());
                UserContact userContact = userService.getUserContactById(systemUserDetail.getUserId());
                systemUserDetailDataBean.setName(userContact.getFirstName() + " " + userContact.getLastName());
                systemUserDetailDataBean.setEmpId(systemUserDetail.getEmpId());
                systemUserDetailDataBeans.add(systemUserDetailDataBean);
            }
        }
        return systemUserDetailDataBeans;
    }
}
