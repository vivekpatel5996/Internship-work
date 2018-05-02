/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.EmailFormatService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.model.*;
import com.argusoft.ars.web.usermanagement.databean.*;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.email.common.core.EmailService;
import com.argusoft.email.common.model.Attachment;
import com.argusoft.email.common.model.Email;
import com.argusoft.usermanagement.common.core.FeatureService;
import com.argusoft.usermanagement.common.core.RoleService;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.Feature;
import com.argusoft.usermanagement.common.model.Role;
import com.argusoft.usermanagement.common.model.User;
import com.argusoft.usermanagement.common.model.UserContact;
import com.argusoft.usermanagement.common.model.UserRole;
import com.argusoft.usermanagement.common.model.UserRolePK;
import com.argusoft.usermanagement.common.util.RandomPasswordGenerator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author mmodi
 */
@ManagedBean(name = "userTransformerBean")
@RequestScoped
public class UserTransformerBean {

    @ManagedProperty(value = "#{basicPasswordEncryptor}")
    private BasicPasswordEncryptor basicPasswordEncryptor;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{roleService}")
    private RoleService roleService;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{featureService}")
    private FeatureService featureService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{emailService}")
    private EmailService emailService;
    @ManagedProperty(value = "#{cardInventoryTransformerBean}")
    private CardInventoryTransformerBean cardInventoryTransformerBean;
    @ManagedProperty(value = "#{emailFormatService}")
    private EmailFormatService emailFormatService;

    public EmailFormatService getEmailFormatService() {
        return emailFormatService;
    }

    public void setEmailFormatService(EmailFormatService emailFormatService) {
        this.emailFormatService = emailFormatService;
    }

    public CardInventoryTransformerBean getCardInventoryTransformerBean() {
        return cardInventoryTransformerBean;
    }

    public void setCardInventoryTransformerBean(CardInventoryTransformerBean cardInventoryTransformerBean) {
        this.cardInventoryTransformerBean = cardInventoryTransformerBean;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
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

    public BasicPasswordEncryptor getBasicPasswordEncryptor() {
        return basicPasswordEncryptor;
    }

    public void setBasicPasswordEncryptor(BasicPasswordEncryptor basicPasswordEncryptor) {
        this.basicPasswordEncryptor = basicPasswordEncryptor;
    }

    public byte[] retrieveUserImage(Long id) {
        return null;
    }

    public byte[] retrieveUserProfile(Long id) {
        return null;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public boolean isUserExist(String userId, Long id) {
        boolean isUserExists = false;
        if (userId != null) {
            try {
                if (id != null) {
                    User user = userService.getUserbyId(id, false, false, false, false);
                    if (user.getUserId().equals(userId)) {
                        return false;
                    }
                }
                isUserExists = userService.isUserExist(userId.toLowerCase());
            } catch (Exception ex) {
                System.out.println("Exception in UserTransformer:checkUserAvalibility is " + ex);
            }
        }
        return isUserExists;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public FeatureService getFeatureService() {
        return featureService;
    }

    public void setFeatureService(FeatureService featureService) {
        this.featureService = featureService;
    }

    public String createUser(UserDataBean userDataBean) {
        String response = SystemConstantUtil.SUCCESS;
        try {
            String password = userDataBean.getPassword();
            if (password == null || password.isEmpty()) {
                password = RandomPasswordGenerator.getRamdomPassword();
                password = password.toLowerCase();
            }
            String encrypted = basicPasswordEncryptor.encryptPassword(password);
            userDataBean.setPassword(encrypted);
            if (userDataBean.getEmpType().equals(SystemConstantUtil.CONTRACTUAL_EMPLOYEE)) {
//                userDataBean.setDepId(null);
//                userDataBean.setDesgId(null);
//                userDataBean.setShiftId(null);
                userDataBean.setDoc(null);
            } else if (userDataBean.getEmpType().equals(SystemConstantUtil.TRAINEE)) {
                userDataBean.setDoc(null);
            }
            Long userId;
            User user = new User();
            user = convertUserDataBeanToUserModel(userDataBean, user, SystemConstantUtil.CREATE_OPERATION);
            UserContact userContact = this.convertUserDataBeanToUserContactModel(userDataBean, new UserContact(), SystemConstantUtil.CREATE_OPERATION);
            //   Setting up user contact to user
            user.setContact(userContact);
            //   Inserting user to system user
            userId = userService.createUser(user);
            userDataBean.setId(userId);
            SystemUserDetail systemUserDetail = this.convertUserDataBeanToSystemUserDetail(userDataBean, new SystemUserDetail());
            systemUserDetailService.createsystemUserDetail(systemUserDetail);
            this.sendUserDetailMail(userDataBean.getUserId(), password, userDataBean.getFirstName() + " " + userDataBean.getLastName(), userDataBean.getEmail(), SystemConstantUtil.EMAIL_FORMAT_NEW_USER_CREATED);
            if (userDataBean.getCardIdPk() != null) {
                cardInventoryTransformerBean.assignCard(userDataBean.getCardIdPk(), userDataBean.getId());
            }
//            if (userId != null) {
//                userDataBean.setPassword(password);
//                // Setting user-role mappings
//                if (userDataBean.getUserRoleList() != null && userDataBean.getUserRoleList().size() > 0) {
//                    for (RoleDataBean roleData : userDataBean.getUserRoleList()) {
//                        UserRole userRole = this.convertRoleDataBeanToUserRoleModel(userId, roleData.getRoleId(), new UserRole());
//                        userService.createUserRole(userRole);
//                    }
//                }
//            } else {
//                response = SystemConstantUtil.FAILURE;
//            }
        } catch (Exception e) {
            response = SystemConstantUtil.FAILURE;
            e.printStackTrace();
        }
        return response;
    }

    private User convertUserDataBeanToUserModel(UserDataBean userDataBean, User userModel, String operation) {
        // action field
        if (operation.equalsIgnoreCase(SystemConstantUtil.CREATE_OPERATION)) {
            userModel.setCreatedBy(loginDataBean.getUserId());
            userModel.setCreatedOn(Calendar.getInstance().getTime());
            userModel.setPassword(userDataBean.getPassword());
            userModel.setType(SystemConstantUtil.ROLE_MEMBER);
            userModel.setIsActive(Boolean.TRUE);
            userModel.setIsArchive(Boolean.FALSE);
        } else if (operation.equalsIgnoreCase(SystemConstantUtil.UPDATE_OPERATION)) {
            userModel.setModifiedBy(loginDataBean.getUserId());
            userModel.setModifiedOn(Calendar.getInstance().getTime());
        }
        userModel.setUserId(userDataBean.getUserId().toLowerCase());
        userModel.setCustom2(userDataBean.getPreferredLanguage());

        userModel.setExpiredOn(null);
        return userModel;
    }

    private UserContact convertUserDataBeanToUserContactModel(UserDataBean userDataBean, UserContact userContactModel, String operation) {
        // set UserContact
        if (userDataBean.getContactReference() != null) {
            try {
                userContactModel = userService.getUserContactById(userDataBean.getContactReference());
            } catch (UserManagementException ex) {
                Logger.getLogger(UserTransformerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // action field
        if (operation.equalsIgnoreCase(SystemConstantUtil.CREATE_OPERATION)) {
            userContactModel.setCreatedBy(loginDataBean.getUserId());
            userContactModel.setCreatedOn(Calendar.getInstance().getTime());
        } else if (operation.equalsIgnoreCase(SystemConstantUtil.UPDATE_OPERATION)) {
            userContactModel.setModifiedBy(loginDataBean.getUserId());
            userContactModel.setModifiedOn(Calendar.getInstance().getTime());
        }
        userContactModel.setFirstName(userDataBean.getFirstName().trim());
        if (userDataBean.getMiddleName() != null) {
            userContactModel.setMiddleName(userDataBean.getMiddleName().trim());
        } else {
            userContactModel.setMiddleName(null);
        }
        userContactModel.setLastName(userDataBean.getLastName().trim());
        userContactModel.setEmailAddress(userDataBean.getEmail());
        userContactModel.setGender(userDataBean.getGender());
        if (userDataBean.getDob() != null) {
            userContactModel.setDateOfBirth(userDataBean.getDob());
        } else {
            userContactModel.setDateOfBirth(null);
        }
        userContactModel.setMobileNumber(userDataBean.getPhoneNo());
        if (userDataBean.getAlternatePhoneNo() != null) {
            userContactModel.setAlternateContactNumber(userDataBean.getAlternatePhoneNo());
        } else {
            userContactModel.setAlternateContactNumber(null);
        }
        userContactModel.setDisplayName(userDataBean.getFirstName() + " " + userDataBean.getLastName());
        userContactModel.setAddress(userDataBean.getAddress());
        return userContactModel;
    }

    private UserRole convertRoleDataBeanToUserRoleModel(Long userId, Long roleId, UserRole userRole) {
        UserRolePK userRolePK = new UserRolePK(userId, roleId);
        userRole.setUserRolePK(userRolePK);
        userRole.setUser(new User(userId));
        userRole.setRole(new Role(roleId));
        return userRole;
    }

    public List<FeatureDataBean> retrieveAllFeatures(Boolean isActive) {
        List<FeatureDataBean> featureDataBeans = null;
        List<Feature> features = featureService.getAllFeatures(isActive);
        if (features != null && features.size() > 0) {
            featureDataBeans = new LinkedList<FeatureDataBean>();
            for (Feature feature : features) {
                FeatureDataBean featureDataBean = this.convertFeatureModelToFeatureDataBean(feature, new FeatureDataBean());
                featureDataBeans.add(featureDataBean);
            }
        }
        return featureDataBeans;
    }

    private FeatureDataBean convertFeatureModelToFeatureDataBean(Feature featureModel, FeatureDataBean featureDataBean) {
        featureDataBean.setFeatureId(featureModel.getId());
        featureDataBean.setFeatureName(featureModel.getName());
        featureDataBean.setDescription(featureModel.getDescription());
        featureDataBean.setIsCrud(featureModel.getIsCrud());
        featureDataBean.setIsActive(featureModel.getIsActive());
        return featureDataBean;
    }

    public List<SystemUserDetailDataBean> reriveUserList() throws UserManagementException {
        List<User> activeUserList = userService.getAllActiveUsers();
        if (!activeUserList.isEmpty()) {
            List<SystemUserDetailDataBean> systemUserDetailDataBeansList = new ArrayList<SystemUserDetailDataBean>();
            for (User user : activeUserList) {
                SystemUserDetailDataBean systemUserDetailDataBean = new SystemUserDetailDataBean();
                systemUserDetailDataBean.setUserId(user.getId());
                systemUserDetailDataBean.setName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                systemUserDetailDataBeansList.add(systemUserDetailDataBean);
            }

            Collections.sort(systemUserDetailDataBeansList, new Comparator<SystemUserDetailDataBean>() {
                @Override
                public int compare(SystemUserDetailDataBean o1, SystemUserDetailDataBean o2) {
                    if (o1.getName() != null && o2.getName() != null) {
                        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                    } else {
                        return 0;
                    }
                }
            });

            return systemUserDetailDataBeansList;
        }
        return null;
    }

    public Long[] retrieveUserLeaderList() {
        return systemUserDetailService.retrievePersonalUserLeaderList(loginDataBean.getId());
    }

    public void submitUserLeaderList(Long[] leaderList) {
        systemUserDetailService.submitUserLeaderList(loginDataBean.getId(), leaderList);
    }

    public List<SystemUserDetailDataBean> reriveUserListWithoutItself() throws UserManagementException {
        List<User> activeUserList = userService.getAllActiveUsers();
        if (!activeUserList.isEmpty()) {
            List<SystemUserDetailDataBean> systemUserDetailDataBeansList = new ArrayList<SystemUserDetailDataBean>();

            for (User user : activeUserList) {
                if (user.getId() != loginDataBean.getId()) {
                    SystemUserDetailDataBean systemUserDetailDataBean = new SystemUserDetailDataBean();
                    systemUserDetailDataBean.setUserId(user.getId());
                    systemUserDetailDataBean.setName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                    systemUserDetailDataBeansList.add(systemUserDetailDataBean);
                }
            }
            Collections.sort(systemUserDetailDataBeansList, new Comparator<SystemUserDetailDataBean>() {
                @Override
                public int compare(SystemUserDetailDataBean o1, SystemUserDetailDataBean o2) {
                    if (o1.getName() != null && o2.getName() != null) {
                        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                    } else {
                        return 0;
                    }
                }
            });
            return systemUserDetailDataBeansList;
        }
        return null;
    }

    @Async
    public void sendMail(String subject, String message, String[] to, boolean addMsgOfEnsuredDelivery, Attachment[] attachments) {
        Email email = new Email();
        email.setSubject(subject);
        email.setAttachment(attachments);

        //  create body for email which included header and footer
        StringBuilder msg = new StringBuilder();
        msg.append(this.setHeader());
        msg.append(message);
        msg.append(this.setFooter());
        email.setMessageBody(msg.toString());
        email.setTo(to);
        email.setIsSecure(Boolean.TRUE);

        email.setConnectionType(SystemConstantUtil.CONNECTION_TYPE_DEFAULT); // this value is "Default", set this in your constant file.
        emailService.sendMail(email);

    }

    private String setHeader() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("Context Root:" + httpServletRequest.getContextPath());
        return "<img src=\"http:" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/images/header.png\"><br/><hr/>";
    }

    private String setFooter() {
        return "<br/><hr/> Argusoft India Ltd";
    }

    public String reriveUserNameByUserId(Long userId) throws UserManagementException {
        User user = userService.getUserbyId(userId, false, false, true, false);
        return user.getContact().getFirstName() + " " + user.getContact().getLastName();
    }

    private SystemUserDetail convertUserDataBeanToSystemUserDetail(UserDataBean userDataBean, SystemUserDetail systemUserDetail) {
        if (userDataBean.getCardIdPk() != null) {
            systemUserDetail.setCardEnrollNo(new CardInventory(userDataBean.getCardIdPk()));
        } else {
            systemUserDetail.setCardEnrollNo(null);
        }
        if (userDataBean.getShiftId() != null) {
            systemUserDetail.setShiftId(new Shift(userDataBean.getShiftId()));
        } else {
            systemUserDetail.setShiftId(null);
        }
        if (userDataBean.getDepId() != null) {
            systemUserDetail.setDepId(new Department(userDataBean.getDepId()));
        } else {
            systemUserDetail.setDepId(null);
        }
        if (userDataBean.getDesgId() != null) {
            systemUserDetail.setDesgId(new Designation(userDataBean.getDesgId()));
        } else {
            systemUserDetail.setDesgId(null);
        }
        systemUserDetail.setIsAttendanceManager(Boolean.FALSE);
        systemUserDetail.setIsActive(true);
        systemUserDetail.setIsArchive(false);
        systemUserDetail.setIsGiveOpinion(false);
        systemUserDetail.setEmpId(userDataBean.getEmpId());
        systemUserDetail.setJoinDate(userDataBean.getDoj());
        systemUserDetail.setConformationDate(userDataBean.getDoc());
        systemUserDetail.setUserId(userDataBean.getId());
        systemUserDetail.setEmpType(userDataBean.getEmpType());
        return systemUserDetail;
    }

    public List<UserDataBean> reriveUserDetailList() throws UserManagementException {
        List<User> users = userService.getAllActiveUsers();
        if (users != null && users.size() > 0) {
            List<UserDataBean> userDataBeans = new ArrayList<UserDataBean>();
            for (User user : users) {
                UserDataBean userDataBean = this.convertUserModelToUserDetail(user, new UserDataBean());
                userDataBeans.add(userDataBean);
            }
            return userDataBeans;
        }
        return null;
    }

    public UserDataBean retriveUserDetailByUserId(Long userId) throws UserManagementException {
        User user = userService.getUserbyId(userId, false, false, false, false);
        if (user != null) {
            return this.convertUserModelToUserDetail(user, new UserDataBean());
        }
        return null;
    }

    public Boolean isEmpIdExits(String empId, Long id) {
        return systemUserDetailService.isEmpIdExits(empId, id);
    }

    private UserDataBean convertUserModelToUserDetail(User user, UserDataBean userDataBean) {
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(user.getId());
        userDataBean.setAddress(user.getContact().getAddress());
        userDataBean.setAlternatePhoneNo(user.getContact().getAlternateContactNumber());
        if (systemUserDetail.getCardEnrollNo() != null) {
            userDataBean.setCardId(systemUserDetail.getCardEnrollNo().getCardId());
            userDataBean.setCardIdPk(systemUserDetail.getCardEnrollNo().getId());
            userDataBean.setTempCardIdPk(systemUserDetail.getCardEnrollNo().getId());
        }
        if (systemUserDetail.getDepId() != null) {
            userDataBean.setDepartmnetName(systemUserDetail.getDepId().getDeptName());
            userDataBean.setDepId(systemUserDetail.getDepId().getDeptId());
        }
        if (systemUserDetail.getDesgId() != null) {
            userDataBean.setDesignationName(systemUserDetail.getDesgId().getDesName());
            userDataBean.setDesgId(systemUserDetail.getDesgId().getDesId());
        }
        userDataBean.setDob(user.getContact().getDateOfBirth());
        userDataBean.setDoj(systemUserDetail.getJoinDate());
        userDataBean.setDoc(systemUserDetail.getConformationDate());
        userDataBean.setEmail(user.getContact().getEmailAddress());
        userDataBean.setEmpType(systemUserDetail.getEmpType());
        userDataBean.setFirstName(user.getContact().getFirstName());
        userDataBean.setGender(user.getContact().getGender());
        userDataBean.setMiddleName(user.getContact().getMiddleName());
        if (systemUserDetail.getShiftId() != null) {
            userDataBean.setShiftName(systemUserDetail.getShiftId().getShiftName());
            userDataBean.setShiftId(systemUserDetail.getShiftId().getShiftId());
        }
        userDataBean.setLastName(user.getContact().getLastName());
        userDataBean.setPhoneNo(user.getContact().getMobileNumber());
        userDataBean.setIsAdmin(user.getType().equals(SystemConstantUtil.ROLE_SUPER_ADMIN));
        userDataBean.setId(user.getId());
        userDataBean.setEmpId(systemUserDetail.getEmpId());
        userDataBean.setUserId(user.getUserId());
        userDataBean.setIsActive(user.getIsActive());
        return userDataBean;

    }

    public String assignAsAdmin(Long userId) throws UserManagementException {
        User user = userService.getUserbyId(userId, false, false, true, false);
        String msg;
        if (user != null) {
            if (user.getType().equals(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
                user.setType(SystemConstantUtil.ROLE_MEMBER);
                msg = user.getContact().getFirstName() + " " + user.getContact().getLastName() + " is not admin now";
            } else {
                user.setType(SystemConstantUtil.ROLE_SUPER_ADMIN);
                msg = user.getContact().getFirstName() + " " + user.getContact().getLastName() + " is admin now";
            }
            userService.updateUser(user, false, false, false, false);
            return msg;
        }
        return null;
    }

    public String editUser(UserDataBean userDataBean) {
        String response = SystemConstantUtil.SUCCESS;
        try {
            User user = userService.getUserbyId(userDataBean.getId(), false, false, false, false);
            if (user != null && (user.getPassword() == null || user.getPassword().isEmpty())) {
                String password = RandomPasswordGenerator.getRamdomPassword().toLowerCase();
                String encrypted = basicPasswordEncryptor.encryptPassword(password);
                user.setPassword(encrypted);
                this.sendUserDetailMail(userDataBean.getUserId(), password, userDataBean.getFirstName() + " " + userDataBean.getLastName(), userDataBean.getEmail(), SystemConstantUtil.EMAIL_FORMAT_ACTIVATE_USER);
            }
            SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userDataBean.getId());
            Boolean isUserIdExits = false;
            Boolean isEmpIdExits = false;
            if (!userDataBean.getUserId().equals(user.getUserId())) {
                isUserIdExits = this.isUserExist(userDataBean.getUserId(), userDataBean.getId());
            }
            if (!systemUserDetail.getEmpId().equals(userDataBean.getEmpId())) {
                this.isEmpIdExits(userDataBean.getEmpId(), userDataBean.getId());
            }
            if (!isUserIdExits && !isEmpIdExits) {
                if (systemUserDetail.getCardEnrollNo() == null && userDataBean.getCardIdPk() != null) {
                    cardInventoryTransformerBean.assignCard(userDataBean.getCardIdPk(), userDataBean.getId());
                } else if (systemUserDetail.getCardEnrollNo() != null && userDataBean.getCardIdPk() == null) {
                    cardInventoryTransformerBean.unAssignCard(systemUserDetail.getCardEnrollNo().getId());
                } else if (systemUserDetail.getCardEnrollNo() != null && systemUserDetail.getCardEnrollNo().getId() != userDataBean.getCardIdPk()) {
                    cardInventoryTransformerBean.unAssignCard(systemUserDetail.getCardEnrollNo().getId());
                    cardInventoryTransformerBean.assignCard(userDataBean.getCardIdPk(), userDataBean.getId());
                }
                if (userDataBean.getEmpType().equals(SystemConstantUtil.CONTRACTUAL_EMPLOYEE)) {
                    userDataBean.setDepId(null);
                    userDataBean.setDesgId(null);
                    userDataBean.setShiftId(null);
                    userDataBean.setDoc(null);
                } else if (userDataBean.getEmpType().equals(SystemConstantUtil.TRAINEE)) {
                    userDataBean.setDoc(null);
                }

//                System.out.println("In update user transformer======================");
//            String password = userDataBean.getPassword();
//            if (password == null || password.isEmpty()) {
//                password = RandomPasswordGenerator.getRamdomPassword();
//                userDataBean.setPassword(password);
//            }
//            String encrypted = basicPasswordEncryptor.encryptPassword(userDataBean.getPassword().toLowerCase());
//            userDataBean.setPassword(encrypted);
                this.convertUserDataBeanToUserModel(userDataBean, user, SystemConstantUtil.UPDATE_OPERATION);
                this.convertUserDataBeanToUserContactModel(userDataBean, user.getContact(), SystemConstantUtil.UPDATE_OPERATION);
                //   Inserting user to system user
                this.userService.updateUser(user, false, false, false, false);

//                user = convertUserDataBeanToUserModel(userDataBean, user, SystemConstantUtil.UPDATE_OPERATION);
//                UserContact userContact = this.convertUserDataBeanToUserContactModel(userDataBean, user.getContact(), SystemConstantUtil.UPDATE_OPERATION);
//                //   Setting up user contact to user
//                user.setContact(userContact);
//                //   Inserting user to system user
//                userService.updateUser(user, false, false, false, false);
                systemUserDetail = this.convertUserDataBeanToSystemUserDetail(userDataBean, systemUserDetail);
                systemUserDetailService.updatesystemUserDetail(systemUserDetail);

            } else {
                if (isUserIdExits) {
                    return "User Id is not available";
                } else if (isEmpIdExits) {
                    return "Employee Id is not available";
                } else {
                    return "User Id and Employee Id both not available";
                }
            }
        } catch (Exception e) {
            response = SystemConstantUtil.FAILURE;
            e.printStackTrace();
        }
        return response;
    }

    public List<UserDataBean> retriveAllUserDetail() throws UserManagementException {
        List<User> users = userService.getAllUsers();
        if (users != null && users.size() > 0) {
            List<UserDataBean> userDataBeans = new ArrayList<UserDataBean>();
            for (User user : users) {
                UserDataBean userDataBean = this.convertUserModelToUserDetail(user, new UserDataBean());
                userDataBeans.add(userDataBean);
            }
            return userDataBeans;
        }
        return null;
    }

    public String activateUser(UserDataBean userDataBean) {
        String response = SystemConstantUtil.SUCCESS;
        try {
            User user = userService.getUserbyId(userDataBean.getId(), false, false, false, false);
            String password = RandomPasswordGenerator.getRamdomPassword().toLowerCase();
            String encrypted = basicPasswordEncryptor.encryptPassword(password);
            user.setPassword(encrypted);
            SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(userDataBean.getId());
            Boolean isUserIdExits = false;
            Boolean isEmpIdExits = false;
            if (!userDataBean.getUserId().equals(user.getUserId())) {
                isUserIdExits = this.isUserExist(userDataBean.getUserId(), userDataBean.getId());
            }
            if (!systemUserDetail.getEmpId().equals(userDataBean.getEmpId())) {
                this.isEmpIdExits(userDataBean.getEmpId(), userDataBean.getId());
            }
            if (!isUserIdExits && !isEmpIdExits) {
                if (systemUserDetail.getCardEnrollNo() == null && userDataBean.getCardIdPk() != null) {
                    cardInventoryTransformerBean.assignCard(userDataBean.getCardIdPk(), userDataBean.getId());
                } else if (systemUserDetail.getCardEnrollNo() != null && userDataBean.getCardIdPk() == null) {
                    cardInventoryTransformerBean.unAssignCard(systemUserDetail.getCardEnrollNo().getId());
                } else if (systemUserDetail.getCardEnrollNo() != null && systemUserDetail.getCardEnrollNo().getId() != userDataBean.getCardIdPk()) {
                    cardInventoryTransformerBean.unAssignCard(systemUserDetail.getCardEnrollNo().getId());
                    cardInventoryTransformerBean.assignCard(userDataBean.getCardIdPk(), userDataBean.getId());
                }
                if (userDataBean.getEmpType().equals(SystemConstantUtil.CONTRACTUAL_EMPLOYEE)) {
                    userDataBean.setDepId(null);
                    userDataBean.setDesgId(null);
                    userDataBean.setShiftId(null);
                    userDataBean.setDoc(null);
                } else if (userDataBean.getEmpType().equals(SystemConstantUtil.TRAINEE)) {
                    userDataBean.setDoc(null);
                }

                System.out.println("In update user transformer======================");
//            String password = userDataBean.getPassword();
//            if (password == null || password.isEmpty()) {
//                password = RandomPasswordGenerator.getRamdomPassword();
//                userDataBean.setPassword(password);
//            }
//            String encrypted = basicPasswordEncryptor.encryptPassword(userDataBean.getPassword().toLowerCase());
//            userDataBean.setPassword(encrypted);

                this.convertUserDataBeanToUserModel(userDataBean, user, SystemConstantUtil.UPDATE_OPERATION);
                this.convertUserDataBeanToUserContactModel(userDataBean, user.getContact(), SystemConstantUtil.UPDATE_OPERATION);
                //   Inserting user to system user
                user.setIsActive(Boolean.TRUE);
                user.setIsArchive(Boolean.FALSE);
                user.getContact().setIsActive(Boolean.TRUE);
                user.getContact().setIsArchive(Boolean.FALSE);

                this.userService.updateUser(user, false, false, false, false);

//                user = convertUserDataBeanToUserModel(userDataBean, user, SystemConstantUtil.UPDATE_OPERATION);
//                UserContact userContact = this.convertUserDataBeanToUserContactModel(userDataBean, user.getContact(), SystemConstantUtil.UPDATE_OPERATION);
//                //   Setting up user contact to user
//                user.setContact(userContact);
//                //   Inserting user to system user
//                userService.updateUser(user, false, false, false, false);
                systemUserDetail = this.convertUserDataBeanToSystemUserDetail(userDataBean, systemUserDetail);
                systemUserDetail.setIsActive(Boolean.TRUE);
                systemUserDetail.setIsArchive(Boolean.FALSE);
                systemUserDetailService.updatesystemUserDetail(systemUserDetail);
                this.sendUserDetailMail(userDataBean.getUserId(), password, userDataBean.getFirstName() + " " + userDataBean.getLastName(), userDataBean.getEmail(), SystemConstantUtil.EMAIL_FORMAT_ACTIVATE_USER);

            } else {
                if (isUserIdExits) {
                    return "User Id is not available";
                } else if (isEmpIdExits) {
                    return "Employee Id is not available";
                } else {
                    return "User Id and Employee Id both not available";
                }
            }
        } catch (Exception e) {
            response = SystemConstantUtil.FAILURE;
            e.printStackTrace();
        }
        return response;
    }

    public String deActivateUser(String userId, Long id) throws UserManagementException {
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(id);
        if (systemUserDetail.getCardEnrollNo() != null) {
            cardInventoryTransformerBean.unAssignCard(systemUserDetail.getCardEnrollNo().getId());
            systemUserDetail.setCardEnrollNo(null);
        }
        systemUserDetail.setIsActive(Boolean.FALSE);
        systemUserDetailService.updatesystemUserDetail(systemUserDetail);
        userService.deActiveUser(userId);
        User user = userService.getUserbyId(id, false, false, false, false);
        user.setExpiredOn(new Date());
        userService.updateUser(user, false, false, false, false);
        return SystemConstantUtil.SUCCESS;
    }

    private void sendUserDetailMail(String userId, String password, String empName, String emailId, String mailFormate) {
        EmailFormat emailFormat = emailFormatService.retriveEmailByFormateName(mailFormate);
        if (emailFormat != null && emailId != null) {
            String[] to = new String[1];
            to[0] = emailId;
            String userIdPasswordInfo = this.retriveUserIdPasswordInfo(userId, password);
            String emailBody = emailFormat.getBody();
            String employeeName = empName;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, employeeName);
            emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_CURRENT_DATE, sdf.format(new Date()).toString());
            emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_INFO, userIdPasswordInfo);
            emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_NEW_PASSWORD, password);
            emailBody = emailBody.replaceAll(SystemConstantUtil.EMAIL_PASSWORD, password);
            emailBody = SystemFunctionUtil.replaceTextForEmail(emailBody, employeeName, null, null, null, null, userIdPasswordInfo, null, null, null, null, null, null, password, password);
            String subject = emailFormat.getSubject();
            subject = SystemFunctionUtil.replaceTextForEmail(subject, employeeName, null, null, null, null, userIdPasswordInfo, null, null, null, null, null, null, password, password);
            this.sendMail(subject, emailBody, to, false, null);
        }
    }

    private String retriveUserIdPasswordInfo(String userId, String password) {
        StringBuilder message = new StringBuilder();
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String link = "http://" + httpServletRequest.getServerName().toString() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
        message.append("<table width='100%' border='0' cellpadding='7px' cellspacing='0'>");
        message.append("<tr><th align='right'> <strong>User Id : &nbsp;</strong> </th><td>").append(userId).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Password : &nbsp;</strong> </th><td>").append(password).append("</td></tr>");
        message.append("<tr><th align='right'> <strong>Visit Link : &nbsp;</strong> </th><td>").append(link).append("</td></tr>");
        message.append("</table>");
        return message.toString();
    }

    public void updateProfile(UserDataBean userDataBean) throws UserManagementException {
        UserContact userContact = userService.getUserContactById(userDataBean.getId());
        if (userContact != null) {
            userContact.setAddress(userDataBean.getAddress());
            userContact.setDateOfBirth(userDataBean.getDob());
            userContact.setFirstName(userDataBean.getFirstName());
            userContact.setMiddleName(userDataBean.getMiddleName());
            userContact.setLastName(userDataBean.getLastName());
            userContact.setMobileNumber(userDataBean.getPhoneNo());
            userContact.setAlternateContactNumber(userDataBean.getAlternatePhoneNo());
            userContact.setAlternateEmailAddress(userDataBean.getAlternateEmail());
            System.out.println("Dob:" + userContact.getDateOfBirth());
            userService.updateUserContact(userContact);
        }
    }

    public String changePassword(ChangePasswordDataBean changePasswordDataBean) throws UserManagementException {
        if (!loginDataBean.getPassword().equals(changePasswordDataBean.getOldPassword())) {
            return "Currunt password not match";
        } else {
            String encrypted = basicPasswordEncryptor.encryptPassword(changePasswordDataBean.getPassword());
            User user = userService.getUserbyId(loginDataBean.getId(), false, false, false, false);
            user.setPassword(encrypted);
            userService.updateUser(user, false, false, false, false);
            return SystemConstantUtil.SUCCESS;
        }
    }

    public String forgotPassword(String userId) {
        String response = SystemConstantUtil.FAILURE;
        try {
            User systemUser = null;
            String password = RandomPasswordGenerator.getRamdomPassword().toLowerCase();
            String encrypted = basicPasswordEncryptor.encryptPassword(password);
            userId = userId.trim().toLowerCase();
            response = userService.resetPassword(userId, encrypted);

            if (response != null) {
                response = SystemConstantUtil.SUCCESS;
                systemUser = userService.getUserbyUserId(userId, false, false, false, false);

                this.sendUserDetailMail(userId, password, systemUser.getContact().getFirstName() + " " + systemUser.getContact().getLastName(), systemUser.getContact().getEmailAddress(), SystemConstantUtil.EMAIL_FORMAT_FORGOT_PASSWORD);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserTransformerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

   
}
