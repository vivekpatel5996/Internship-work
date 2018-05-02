/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.ShiftService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.Shift;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.ShiftDataBean;
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
 * Transformer for Shift.
 *
 * @author Harshit
 */
@ManagedBean(name = "shiftTransformerBean")
@RequestScoped
public class ShiftTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{shiftService}")
    private ShiftService shiftService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    //  Other properties
    private static final Logger log = Logger.getLogger(ShiftTransformerBean.class);

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

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public ShiftService getShiftService() {
        return shiftService;
    }

    public void setShiftService(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /**
     * Method to convert ShiftDataBean to Shift Model.
     *
     */
    private Shift convertShiftDataBeanToShiftModel(ShiftDataBean shiftDataBean, Shift shift) {

        shift.setCreatedBy(loginDataBean.getId());
        shift.setCreatedDate(new Date());
        shift.setShiftId(shiftDataBean.getShiftId());
        shift.setShiftName(shiftDataBean.getShiftName());
        shift.setShiftStartTime(shiftDataBean.getShiftStratTime());
        shift.setShiftEndTime(shiftDataBean.getShiftEndTime());
        shift.setIsActive(true);
        shift.setIsArchive(false);
        return shift;
    }

    /**
     * Method to convert Shift Model to ShiftDataBean.
     */
    private ShiftDataBean convertShiftModelToShiftDataBean(Shift shift) {
        try {
            ShiftDataBean shiftDataBean = new ShiftDataBean();
            shiftDataBean.setShiftId(shift.getShiftId());
            shiftDataBean.setShiftName(shift.getShiftName());
            shiftDataBean.setShiftStratTime(shift.getShiftStartTime());
            shiftDataBean.setShiftEndTime(shift.getShiftEndTime());
            return shiftDataBean;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive all active Shift
     */
    public List<ShiftDataBean> retrieveAllShift() {
        List<Shift> shiftList = shiftService.retrieveAllShift();
        if (shiftList != null) {
            List<ShiftDataBean> shiftDataBeansList = new ArrayList<ShiftDataBean>();
//            flag use for check whether all emplyee are in same shift;
            Boolean flag = true;
            Integer noOfActiveUser = systemUserDetailService.noOfActiveUser();
            for (Shift shift : shiftList) {
                ShiftDataBean shiftDataBean = convertShiftModelToShiftDataBean(shift);
                if (flag) {
                    Integer noOfUserInShift = systemUserDetailService.noOfActiveUserInShift(shift.getShiftId());
                    if (noOfActiveUser == noOfUserInShift) {
                        shiftDataBean.setAddToThisButtonDisable(true);
                        flag = false;
                    } else {
                        shiftDataBean.setAddToThisButtonDisable(false);
                    }
                    if (noOfUserInShift > 0) {
                        shiftDataBean.setDeleteButtonDisable(true);
                    } else {
                        shiftDataBean.setDeleteButtonDisable(false);
                    }
                } else {
                    shiftDataBean.setAddToThisButtonDisable(false);
                    shiftDataBean.setDeleteButtonDisable(false);
                }
                shiftDataBeansList.add(shiftDataBean);
            }
            return shiftDataBeansList;
        }
        return null;
    }

    /**
     * Method to Retrive Shift Detail by Shift Id
     */
    public ShiftDataBean retrieveShiftDetail(Long shiftId) {
        Shift shift = shiftService.retrieveShiftById(shiftId);
        ShiftDataBean shiftDataBean = convertShiftModelToShiftDataBean(shift);
        return shiftDataBean;
    }

    /**
     * Method to Add Shift object.
     *
     */
    public String createShift(ShiftDataBean shiftDataBean) {
        try {
            boolean isShiftNameAvailable = isShiftNameAvailable(shiftDataBean.getShiftName());
            if (isShiftNameAvailable) {
                Shift shift = convertShiftDataBeanToShiftModel(shiftDataBean, new Shift());
                this.shiftService.createShift(shift);
                return SystemConstantUtil.SUCCESS;
            } else {
                return SystemConstantUtil.REPEAT;
            }
        } catch (Exception e) {
            System.out.println("Add Shift Catch");
            return e.toString();
        }
    }

    /**
     * Method to Update Shift Detail
     */
    public String updateShift(ShiftDataBean shiftDataBean) {
        try {
            Shift shift = shiftService.retrieveShiftById(shiftDataBean.getShiftId());
            boolean isShiftNameAvailable;
            if (!shift.getShiftName().equals(shiftDataBean.getShiftName())) {
                isShiftNameAvailable = isShiftNameAvailable(shiftDataBean.getShiftName());
            } else {
                isShiftNameAvailable = true;
            }

            if (isShiftNameAvailable) {
                shift.setShiftName(shiftDataBean.getShiftName());
                shift.setShiftStartTime(shiftDataBean.getShiftStratTime());
                shift.setShiftEndTime(shiftDataBean.getShiftEndTime());
                shiftService.updateShift(shift);
                return SystemConstantUtil.SUCCESS;
            } else {
                return SystemConstantUtil.REPEAT;
            }
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to Inactivate Shift.
     */
    /**
     * Method to Inactivate Shift.
     */
    public String deleteShift(ShiftDataBean shiftDataBean) {
        try {
            System.out.println("**************shiftTranformerBean=>deleteShift************");
            Shift shift = shiftService.retrieveShiftById(shiftDataBean.getShiftId());
            shift.setIsActive(false);
            shiftService.updateShift(shift);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to check whether Shift Name is already added in active or not.
     */
    private boolean isShiftNameAvailable(String shiftName) {
        System.out.println("in isShiftaNameAvalable Function....");
        return shiftService.isShiftNameAvailable(shiftName);
    }

    public List<SystemUserDetailDataBean> retrieveAddToThisShiftDetail(Long shiftId) throws UserManagementException {
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailNotEqualByType(shiftId, "shift");
        List<SystemUserDetailDataBean> systemUserDetailDataBeans = new ArrayList<SystemUserDetailDataBean>();
        for (SystemUserDetail systemUserDetail : systemUserDetails) {
            SystemUserDetailDataBean systemUserDetailDataBean = convertSystemUserDetailModeltoSystemUserDetailDataBean(systemUserDetail, new SystemUserDetailDataBean());
            UserContact userContact = userService.getUserContactById(systemUserDetail.getUserId());
            systemUserDetailDataBean.setName(userContact.getFirstName() + " " + userContact.getLastName());
            systemUserDetailDataBean.setEmpId(systemUserDetail.getEmpId());
            systemUserDetailDataBeans.add(systemUserDetailDataBean);
        }
        return systemUserDetailDataBeans;
    }

    private SystemUserDetailDataBean convertSystemUserDetailModeltoSystemUserDetailDataBean(SystemUserDetail systemUserDetail, SystemUserDetailDataBean systemUserDetailDataBean) {
        if (systemUserDetail.getShiftId() != null) {
            systemUserDetailDataBean.setShiftId(systemUserDetail.getShiftId().getShiftId());
            systemUserDetailDataBean.setShiftName(systemUserDetail.getShiftId().getShiftName());
        }
        systemUserDetailDataBean.setUserId(systemUserDetail.getUserId());
        systemUserDetailDataBean.setChecked(false);
        return systemUserDetailDataBean;
    }

    public void addEmployeeToThisShift(SystemUserDetailDataBean systemUserDetailDataBean, Long shiftId) {
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(systemUserDetailDataBean.getUserId());
        Shift shift = shiftService.retrieveShiftById(shiftId);
        System.out.println(shift);
        systemUserDetail.setShiftId(shift);
        systemUserDetailService.updatesystemUserDetail(systemUserDetail);
    }

    public List<SystemUserDetailDataBean> retrieveEmployeeByDesignation(Long shiftId) throws UserManagementException {
        List<SystemUserDetail> systemUserDetails = systemUserDetailService.retrieveSystemUserDetailByType(shiftId, "shift");
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
