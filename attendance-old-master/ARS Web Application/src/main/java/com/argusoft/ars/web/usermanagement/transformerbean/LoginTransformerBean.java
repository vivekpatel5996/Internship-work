/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.util.SystemFunctionUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.model.User;
import com.argusoft.usermanagement.common.model.UserContact;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * Transformer for Login.
 *
 * @author mpritmani
 */
@ManagedBean(name = "loginTransformerBean")
@RequestScoped
public class LoginTransformerBean {

    //  DataBean properties
    @ManagedProperty("#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty("#{userService}")
    private UserService userService;
    //  Other properties
    private static final Logger logger = Logger.getLogger(LoginTransformerBean.class);

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

    /**
     * Method to change login information.
     *
     * @param userId login id of the user
     */
    public void updateLoginData(String userId) {
        System.out.println("userid in updatelogin method v got is " + userId);
        User systemUser = this.retrieveSystemUser(userId);
        System.out.println("system user v got is " + systemUser);
        if (systemUser != null) {
            this.loginDataBean = this.convertUserModelToLoginDataBean(systemUser);
            Calendar currentdate = Calendar.getInstance();
            currentdate.setTime(SystemFunctionUtil.convertDateTimeToServerTimeZone(currentdate.getTime(), currentdate.getTimeZone().getID()));
            systemUser.setLastLoginOn(currentdate.getTime());
            this.updateSystemUser(systemUser);
        }
    }

    /**
     * Conversion method for LoginDataBean
     *
     * @param systemUser User obejct to be converted
     * @return Converted LoginDataBean object is returned
     */
    private LoginDataBean convertUserModelToLoginDataBean(User systemUser) {
        this.loginDataBean.setId(systemUser.getId());
        this.loginDataBean.setUserId(systemUser.getUserId());
        this.loginDataBean.setRole(systemUser.getType());
        UserContact contact = systemUser.getContact();
        if (contact != null) {
            StringBuilder name = new StringBuilder();
            if (StringUtils.hasText(contact.getFirstName())) {
                name.append(contact.getFirstName());
                name.append(" ");
            }
            if (StringUtils.hasText(contact.getLastName())) {
                name.append(contact.getLastName());
            }
            if (StringUtils.hasText(name)) {
                this.loginDataBean.setName(name.toString());
            }
            this.loginDataBean.setTimezone(systemUser.getContact().getTimeZone());
            if (systemUser.getLastLoginOn() != null && contact.getTimeZone() != null) {
                this.loginDataBean.setLastLoginDateTime(SystemFunctionUtil.convertDateTimeToUserTimezone(systemUser.getLastLoginOn(), contact.getTimeZone()));
            }
            this.loginDataBean.setPreferredLanguage(systemUser.getCustom2());
        }

        return loginDataBean;
    }

    /**
     * Method to update user object
     *
     * @param systemUser Object which is to be updated
     */
    private void updateSystemUser(User systemUser) {
        try {
            userService.updateUser(systemUser, false, false, false, false);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * Method to retrieve User by id
     *
     * @param userId Id of the user
     * @return The user object if exist, otherwise null returned
     */
    private User retrieveSystemUser(String userId) {
        User systemUser = null;
        try {
            systemUser = userService.getUserbyUserId(userId, false, false, false, false);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return systemUser;
    }
}
