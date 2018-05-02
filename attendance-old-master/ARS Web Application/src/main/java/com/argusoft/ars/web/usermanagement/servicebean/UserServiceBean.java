/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.*;
import com.argusoft.ars.web.usermanagement.transformerbean.RoleTransformerBean;
import com.argusoft.ars.web.usermanagement.transformerbean.UserTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.ars.web.util.SystemResultSessionUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author mmodi
 */
@ManagedBean(name = "userServiceBean")
@RequestScoped
public class UserServiceBean {

    @ManagedProperty(value = "#{userDataBean}")
    private UserDataBean userDataBean;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    @ManagedProperty(value = "#{roleTransformerBean}")
    private RoleTransformerBean roleTransformerBean;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{systemResultSessionUtil}")
    private SystemResultSessionUtil systemResultSessionUtil;
    @ManagedProperty(value = "#{systemUserDetailDataBean}")
    private SystemUserDetailDataBean systemUserDetailDataBean;
    @ManagedProperty(value = "#{changePasswordDataBean}")
    private ChangePasswordDataBean changePasswordDataBean;
    private List<RoleDataBean> roleDataBeanList;
    private boolean isUserExist;
    private String isUserAvailable;
    private boolean isEmpIdExist;
    private String isEmpIdAvailable;
    private List<Long> roleIdList = new ArrayList<Long>();
    private List<SelectItem> sourceRoleList;
    private Long id;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ChangePasswordDataBean getChangePasswordDataBean() {
        return changePasswordDataBean;
    }

    public void setChangePasswordDataBean(ChangePasswordDataBean changePasswordDataBean) {
        this.changePasswordDataBean = changePasswordDataBean;
    }

    public SystemResultSessionUtil getSystemResultSessionUtil() {
        return systemResultSessionUtil;
    }

    public void setSystemResultSessionUtil(SystemResultSessionUtil systemResultSessionUtil) {
        this.systemResultSessionUtil = systemResultSessionUtil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsEmpIdAvailable() {
        return isEmpIdAvailable;
    }

    public void setIsEmpIdAvailable(String isEmpIdAvailable) {
        this.isEmpIdAvailable = isEmpIdAvailable;
    }

    public boolean isIsEmpIdExist() {
        return isEmpIdExist;
    }

    public void setIsEmpIdExist(boolean isEmpIdExist) {
        this.isEmpIdExist = isEmpIdExist;
    }

    public SystemUserDetailDataBean getSystemUserDetailDataBean() {
        return systemUserDetailDataBean;
    }

    public void setSystemUserDetailDataBean(SystemUserDetailDataBean systemUserDetailDataBean) {
        this.systemUserDetailDataBean = systemUserDetailDataBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
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

    public List<RoleDataBean> getRoleDataBeanList() {
        return roleDataBeanList;
    }

    public void setRoleDataBeanList(List<RoleDataBean> roleDataBeanList) {
        this.roleDataBeanList = roleDataBeanList;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<SelectItem> getSourceRoleList() {
        roleDataBeanList = this.retrieveAllRoles();
        sourceRoleList = new LinkedList<SelectItem>();
        if (roleDataBeanList != null && roleDataBeanList.size() > 0) {
            for (RoleDataBean roleData : roleDataBeanList) {
                sourceRoleList.add(new SelectItem(new Long(roleData.getRoleId()), roleData.getRoleName()));
            }
        }
        return sourceRoleList;
    }

    public void setSourceRoleList(List<SelectItem> sourceRoleList) {
        this.sourceRoleList = sourceRoleList;
    }

    public void forgotPassword() {
        try {
            String response = null;
            response = this.userTransformerBean.forgotPassword(this.loginDataBean.getUserId());
            if (response.equalsIgnoreCase(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setIsSuccess(Boolean.TRUE);
                messageDataBean.setMessage("Password change sucessfully.");
            } else if (response.equalsIgnoreCase(SystemConstantUtil.FAILURE)) {
                if (!this.userTransformerBean.isUserExist(this.loginDataBean.getUserId(), null)) {
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                    messageDataBean.setMessage("User does not exist");
                } else {
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                    messageDataBean.setMessage("Failed to change password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e);
        }

    }

    public boolean isIsUserExist() {
        return isUserExist;
    }

    public void setIsUserExist(boolean isUserExist) {
        this.isUserExist = isUserExist;
    }

    public String getIsUserAvailable() {
        return isUserAvailable;
    }

    public void setIsUserAvailable(String isUserAvailable) {
        this.isUserAvailable = isUserAvailable;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public RoleTransformerBean getRoleTransformerBean() {
        return roleTransformerBean;
    }

    public void setRoleTransformerBean(RoleTransformerBean roleTransformerBean) {
        this.roleTransformerBean = roleTransformerBean;
    }

    public UserDataBean getUserDataBean() {
        return userDataBean;
    }

    public void setUserDataBean(UserDataBean userDataBean) {
        this.userDataBean = userDataBean;
    }

    public UserTransformerBean getUserTransformerBean() {
        return userTransformerBean;
    }

    public void setUserTransformerBean(UserTransformerBean userTransformerBean) {
        this.userTransformerBean = userTransformerBean;
    }

    public String navigateToCreateUser() {
        this.roleDataBeanList = roleTransformerBean.retrieveAllRoles(null, Boolean.TRUE, false, false);
        String resultantView = null;
        if (this.roleDataBeanList != null && this.roleDataBeanList.size() > 0) {
            resultantView = "createUser";
        } else {
            resultantView = "createUser";
        }
        return resultantView;
    }

    public String isUserExist() {
        boolean response = false;
        if (userDataBean.getUserId() != null && !userDataBean.getUserId().equalsIgnoreCase("")) {
            response = userTransformerBean.isUserExist(userDataBean.getUserId(), systemResultSessionUtil.getUserId());
            if (response) {
                this.setIsUserAvailable(" " + userDataBean.getUserId() + " is not available");
                this.isUserExist = false;
            } else {
                this.setIsUserAvailable(" " + userDataBean.getUserId() + " is available");
                this.isUserExist = true;
            }
        }
        return null;
    }

    public String changePassword() {
        try {
            if (changePasswordDataBean.getPassword().equals(changePasswordDataBean.getRePassword())) {
//                if (!changePasswordDataBean.getOldPassword().equals(loginDataBean.getPassword())) {
                    String status = userTransformerBean.changePassword(changePasswordDataBean);
                    if (status.equals(SystemConstantUtil.SUCCESS)) {
                        messageDataBean.setMessage("Password changed successfully");
                        messageDataBean.setIsSuccess(Boolean.TRUE);
                        SystemFunctionUtil.getFlashScope().put("messageDataBean", messageDataBean);
                        return "managePersonDetail?faces-redirect=true";
                    } else {
                        messageDataBean.setMessage("Current password is not match");
                        messageDataBean.setIsSuccess(false);
                    }
//                }else{
//                     messageDataBean.setMessage("Current password should not be same as new password");
//                        messageDataBean.setIsSuccess(false);
//                }
            } else {
                messageDataBean.setMessage("New password and confirm password not match");
                messageDataBean.setIsSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void isEmpIdExist() {
        boolean response = false;
        if (userDataBean.getEmpId() != null && !userDataBean.getEmpId().equalsIgnoreCase("")) {
            response = userTransformerBean.isEmpIdExits(userDataBean.getEmpId(), userDataBean.getId());
            if (response) {
                this.setIsEmpIdAvailable(" " + userDataBean.getEmpId() + " is not available");
                this.isEmpIdExist = false;
            } else {
                this.setIsEmpIdAvailable(" " + userDataBean.getEmpId() + " is  available");
                this.isEmpIdExist = true;

            }
        }
    }

    public void retrieveUserList() throws UserManagementException {
        systemResultViewUtil.setSystemUserDetailDataBeansList(userTransformerBean.reriveUserList());
    }

    public List<RoleDataBean> retrieveAllRoles() {
        List<RoleDataBean> roleDataBeanList = roleTransformerBean.retrieveAllRoles(null, Boolean.TRUE, true, false);
        return roleDataBeanList;
    }

    public String createUser() {
        String resultantView = null;
        String response;
        boolean isExist = userTransformerBean.isUserExist(userDataBean.getUserId(), null);
        isEmpIdExist = userTransformerBean.isEmpIdExits(userDataBean.getEmpId(), userDataBean.getId());
        if (isExist && isEmpIdExist) {
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage("User with same UserId and EmpId already exist");
        }
        if (!isExist) {
            if (!isEmpIdExist) {
                if (userDataBean.getEmpType().equals(SystemConstantUtil.EMPLOYEE)) {
                    if (!this.isDOJLessThanDOC(userDataBean.getDoc(), userDataBean.getDoj())) {
                        messageDataBean.setIsSuccess(Boolean.FALSE);
                        messageDataBean.setMessage("Conformation date is not valid");
                        return null;
                    }
                }
                response = userTransformerBean.createUser(userDataBean);
                if (response.equalsIgnoreCase(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    messageDataBean.setMessage("User created successfully.");
                    SystemFunctionUtil.getFlashScope().put("messageDataBean", messageDataBean);
                    resultantView = "manageUserDetail?faces-redirect=true";
                } else {
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                    messageDataBean.setMessage("User could not be created.");
                }
            } else {
                messageDataBean.setIsSuccess(Boolean.FALSE);
                messageDataBean.setMessage("User with same EmpId already exist");
            }
        } else {
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage("User with same UserId already exist");
        }
        return resultantView;
    }

    public void retrieveUserLeaderList() {
        systemUserDetailDataBean.setUserList(userTransformerBean.retrieveUserLeaderList());
    }

    public void retrieveUserListWithoutItself() {
        try {
            System.out.println("***************************retrieveUserListWithoutItself********************");
            systemResultViewUtil.setSystemUserDetailDataBeansList(userTransformerBean.reriveUserListWithoutItself());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void submituserLeaderList() {
        try {
            userTransformerBean.submitUserLeaderList(systemUserDetailDataBean.getUserList());
            messageDataBean.setMessage("your personal leader list submited successfully!!!");
            messageDataBean.setIsSuccess(true);
        } catch (Exception e) {
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
            System.out.println(e);
        }
    }

    public String retrieveUserNameByUserId(Long userId) {
        try {
            if (userId != null) {
                return userTransformerBean.reriveUserNameByUserId(userId);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void retriveActiveUserDetailList() {
        try {
            systemResultViewUtil.setUserDataBeans(userTransformerBean.reriveUserDetailList());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void retriveUserDetail() {
        try {
            System.out.println("================ retrive user Detail ==============");
            if (systemResultSessionUtil.getUserId() != null || id != null) {
                if (id == null) {
                    id = systemResultSessionUtil.getUserId();
                }
                UserDataBean dataBean;
                dataBean = userTransformerBean.retriveUserDetailByUserId(id);
                if (userDataBean == null || userDataBean.getId() == null || !userDataBean.getId().equals(id)) {
                    userDataBean.setDataBean(dataBean);
                }
                if (systemResultViewUtil.getCardInventoryDataBeansList() != null && dataBean.getCardIdPk() != null && dataBean.getCardId() != null) {
                    CardInventoryDataBean cardInventoryDataBean = new CardInventoryDataBean();
                    cardInventoryDataBean.setId(dataBean.getCardIdPk());
                    cardInventoryDataBean.setCardId(dataBean.getCardId());
                    systemResultViewUtil.getCardInventoryDataBeansList().add(cardInventoryDataBean);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void retrievePersonalInformation() {
        try {
            if (loginDataBean.getId() != null) {
                userDataBean.setDataBean(userTransformerBean.retriveUserDetailByUserId(loginDataBean.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void assignAsAdmin() {
        try {
            String response = userTransformerBean.assignAsAdmin(id);
            if (response != null) {
                messageDataBean.setMessage(response);
                messageDataBean.setIsSuccess(true);
            } else {
                messageDataBean.setMessage("User not found");
                messageDataBean.setIsSuccess(false);
            }
        } catch (Exception ex) {
            messageDataBean.setMessage(ex.toString());
            messageDataBean.setIsSuccess(false);
            Logger.getLogger(UserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean isDOJLessThanDOC(Date doc, Date doj) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        if (!fmt.format(doc).equals(fmt.format(doj))) {
            return doc.after(doj);
        }
        return true;
    }

    public String editUser() {
        String resultantView = null;
        String response;
        boolean isExist = userTransformerBean.isUserExist(userDataBean.getUserId(), systemResultSessionUtil.getUserId());
        isEmpIdExist = userTransformerBean.isEmpIdExits(userDataBean.getEmpId(), userDataBean.getId());

        if (isExist && isEmpIdExist) {
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage("User with same UserId and EmpId already exist");
        }
        if (!isExist) {
            if (!isEmpIdExist) {
                if (userDataBean.getEmpType().equals(SystemConstantUtil.EMPLOYEE)) {
                    if (!this.isDOJLessThanDOC(userDataBean.getDoc(), userDataBean.getDoj())) {
                        messageDataBean.setIsSuccess(Boolean.FALSE);
                        messageDataBean.setMessage("Conformation date is not valid");
                        return null;
                    }
                }
                response = userTransformerBean.editUser(userDataBean);

                if (response.equalsIgnoreCase(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    messageDataBean.setMessage("User updated successfully.");
                    SystemFunctionUtil.getFlashScope().put("messageDataBean", messageDataBean);
                    resultantView = "manageUserDetail?faces-redirect=true";
                } else {
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                    messageDataBean.setMessage(response);
                }
            } else {
                messageDataBean.setIsSuccess(Boolean.FALSE);
                messageDataBean.setMessage("User with same EmpId already exist");
            }
        } else {
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage("User with same UserId already exist");
        }
        return resultantView;
    }

    public void updateProfile() {
        try {
            userTransformerBean.updateProfile(userDataBean);
            messageDataBean.setIsSuccess(Boolean.TRUE);
            messageDataBean.setMessage("Profile updated successfully.");
        } catch (Exception e) {
            System.out.println("Error =>" + e);
            e.printStackTrace();
        }


    }

    public void deActivateUser() {
        try {
            String response = userTransformerBean.deActivateUser(userDataBean.getUserId(), userDataBean.getId());
            messageDataBean.setIsSuccess(Boolean.TRUE);
            messageDataBean.setMessage(userDataBean.getFirstName() + " " + userDataBean.getLastName() + " has been successfully deactivated.");
            this.showUserDetail();
        } catch (Exception e) {
            e.printStackTrace();
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage(e.toString());
        }
    }

    public void showAllUser() {
        try {
            systemResultViewUtil.setUserDataBeans(userTransformerBean.retriveAllUserDetail());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public String activateUser() {
        try {
//            System.out.println("userId:" + userDataBean.getUserId());
            boolean isExist = userTransformerBean.isUserExist(userDataBean.getUserId(), systemResultSessionUtil.getUserId());
            isEmpIdExist = userTransformerBean.isEmpIdExits(userDataBean.getEmpId(), userDataBean.getId());

            if (isExist && isEmpIdExist) {
                messageDataBean.setIsSuccess(Boolean.FALSE);
                messageDataBean.setMessage("User with same UserId and EmpId already exist");
            }
            if (!isExist) {
                if (!isEmpIdExist) {
                    if (userDataBean.getEmpType().equals(SystemConstantUtil.EMPLOYEE)) {
                        if (!this.isDOJLessThanDOC(userDataBean.getDoc(), userDataBean.getDoj())) {
                            messageDataBean.setIsSuccess(Boolean.FALSE);
                            messageDataBean.setMessage("Conformation date is not valid");
                            return null;
                        }
                    }
                    userTransformerBean.activateUser(userDataBean);
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    messageDataBean.setMessage("User activate successfully.");
                    SystemFunctionUtil.getFlashScope().put("messageDataBean", messageDataBean);
                    return "manageUserDetail?faces-redirect=true";
                } else {
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                    messageDataBean.setMessage("User with same EmpId already exist");
                }
            } else {
                messageDataBean.setIsSuccess(Boolean.FALSE);
                messageDataBean.setMessage("User with same UserId already exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            messageDataBean.setIsSuccess(Boolean.FALSE);
            messageDataBean.setMessage(e.toString());
            return null;
        }
        return null;
    }

    public void showUserDetail() {
        if (systemResultViewUtil.getShowAll() != null && systemResultViewUtil.getShowAll()) {
            this.showAllUser();
        } else {
            this.retriveActiveUserDetailList();
        }
    }
    
    
}