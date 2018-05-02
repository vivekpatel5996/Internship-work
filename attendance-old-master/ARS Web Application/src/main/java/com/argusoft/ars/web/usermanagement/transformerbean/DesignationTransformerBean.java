/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.DesignationService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.Designation;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.web.usermanagement.databean.DesignationDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import com.argusoft.usermanagement.common.model.UserContact;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for Designation.
 *
 * @author Harshit
 */
@ManagedBean(name = "designationTransformerBean")
@RequestScoped
public class DesignationTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{designationService}")
    private DesignationService designationService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    //  Other properties
    private static final Logger log = Logger.getLogger(DesignationTransformerBean.class);

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public DesignationService getDesignationService() {
        return designationService;
    }

    public void setDesignationService(DesignationService designationService) {
        this.designationService = designationService;
    }

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to convert DesignationDataBean to Designation Model.
     *
     */
    private Designation convertDesignationDataBeanToDesignationModel(DesignationDataBean designationDataBean, Designation designation) throws UserManagementException {
        designation.setDesName(designationDataBean.getDesgName().trim());
        designation.setCreatedBy(loginDataBean.getId());
        designation.setIsActive(true);
        designation.setIsArchive(true);
        designation.setCreatedDate(new Date());
        return designation;
    }

    /**
     * Method to convert Designation Model to DesignationDataBean.
     */
    private DesignationDataBean convertDesignationModelToDesignationDataBean(Designation designation) {
        try {
            DesignationDataBean designationDataBean = new DesignationDataBean();
            designationDataBean.setDesgName(designation.getDesName());
            designationDataBean.setDesgId(designation.getDesId());
            return designationDataBean;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive all active Designation
     */
    public List<DesignationDataBean> retrieveAllDesignation() {
        List<Designation> designationList = designationService.retrieveAllDesignation();
        if (designationList != null) {
            List<DesignationDataBean> designationDataBeansList = new ArrayList<DesignationDataBean>();
//            flag use for check whether all emplyee are in same designation;
            Boolean flag = true;
            Integer noOfActiveUser = systemUserDetailService.noOfActiveUser();
            for (Designation designation : designationList) {
                DesignationDataBean designationDataBean = convertDesignationModelToDesignationDataBean(designation);
                if (flag) {
                    Integer noOfUserInDesignation = systemUserDetailService.noOfActiveUserInDesignation(designation.getDesId());
                    if (noOfActiveUser == noOfUserInDesignation) {
                        designationDataBean.setAddToThisButtonDisable(true);
                        flag = false;
                    } else {
                        designationDataBean.setAddToThisButtonDisable(false);
                    }
                    if (noOfUserInDesignation > 0) {
                        designationDataBean.setDeleteButtonDisable(true);
                    } else {
                        designationDataBean.setDeleteButtonDisable(false);
                    }
                } else {
                    designationDataBean.setAddToThisButtonDisable(false);
                    designationDataBean.setDeleteButtonDisable(false);
                }
                designationDataBeansList.add(designationDataBean);
            }
            return designationDataBeansList;
        }
        return null;
    }

    /**
     * Method to Retrive Designation Detail by Designation Id
     */
    public DesignationDataBean retrieveDesignationDetail(Long desgId) {
        Designation designation = designationService.retrieveDesignationById(desgId);
        DesignationDataBean designationDataBean = convertDesignationModelToDesignationDataBean(designation);
        return designationDataBean;
    }

    /**
     * Method to Add Designation object.
     *
     */
    public String createDesignation(DesignationDataBean designationDataBean) {
        try {
            boolean isDesgNameAvailable = isDesgNameAvailable(designationDataBean.getDesgName().trim());
            if (isDesgNameAvailable) {
                Designation designation = convertDesignationDataBeanToDesignationModel(designationDataBean, new Designation());
                this.designationService.createDesignation(designation);
                return SystemConstantUtil.SUCCESS;
            } else {
                return SystemConstantUtil.REPEAT;
            }
        } catch (Exception e) {
            System.out.println("Add Designation Catch");
            System.out.println(e);
            return SystemConstantUtil.FAILURE;
        }
    }

    /**
     * Method to Update Designation Detail
     */
    public String updateDesignation(DesignationDataBean designationDataBean) {
        try {
            Designation designation = designationService.retrieveDesignationById(designationDataBean.getDesgId());
            if (!designation.getDesName().equals(designationDataBean.getDesgName())) {
                boolean isDesgNameAvailable = isDesgNameAvailable(designationDataBean.getDesgName().trim());
                if (isDesgNameAvailable) {
                    designation.setDesName(designationDataBean.getDesgName());
                    designationService.updateDesignation(designation);
                    return SystemConstantUtil.SUCCESS;
                } else {
                    return SystemConstantUtil.REPEAT;
                }
            }
            return SystemConstantUtil.SUCCESS;
//            else {
//                return SystemConstantUtil.SAME;
//            }
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to Inactivate Designation.
     */
    public String deleteDesignation(DesignationDataBean designationDataBean) {
        try {
            System.out.println("**************designationTranformerBean=>deleteDesignation************");
            Designation designation = designationService.retrieveDesignationById(designationDataBean.getDesgId());
            designation.setIsActive(false);
            designationService.updateDesignation(designation);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to check whether Designation Name is already added in active or
     * not.
     */
    private boolean isDesgNameAvailable(String desgName) {
        System.out.println("in isDesgaNameAvalable Function....");
        return designationService.isDesgNameAvailable(desgName);
    }

    public List<SystemUserDetailDataBean> retrieveAddToThisDesignationDetail(Long desgId) throws UserManagementException {
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailNotEqualByType(desgId, "designation");
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
        if (systemUserDetail.getDesgId() != null) {
            systemUserDetailDataBean.setDepId(systemUserDetail.getDesgId().getDesId());
            systemUserDetailDataBean.setDesgName(systemUserDetail.getDesgId().getDesName());
        }
        systemUserDetailDataBean.setUserId(systemUserDetail.getUserId());
        systemUserDetailDataBean.setChecked(false);
        return systemUserDetailDataBean;
    }

    public void addEmployeeToThisDesignation(SystemUserDetailDataBean systemUserDetailDataBean, Long desgId) {
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(systemUserDetailDataBean.getUserId());
        Designation designation = designationService.retrieveDesignationById(desgId);
        System.out.println(designation);
        systemUserDetail.setDesgId(designation);
        systemUserDetailService.updatesystemUserDetail(systemUserDetail);
    }

    public List<SystemUserDetailDataBean> retrieveEmployeeByDesignation(Long desgId) throws UserManagementException {
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailByType(desgId, "designation");
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
