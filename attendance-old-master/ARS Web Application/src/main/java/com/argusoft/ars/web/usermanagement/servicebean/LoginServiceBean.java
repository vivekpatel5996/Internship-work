/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.LoginTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.UserAppUtil;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * This class is service bean for Login which contains the methods for Login functionality.
 * @author mpritmani
 */
@ManagedBean(name = "loginServiceBean")
@RequestScoped
public class LoginServiceBean {

    //  DataBean properties
    @ManagedProperty("#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Transformer properties
    @ManagedProperty("#{loginTransformerBean}")
    private LoginTransformerBean loginTransformerBean;
    //  User defined objects
    @ManagedProperty("#{userAppUtil}")
    private UserAppUtil userAppUtil;
    //  Other properties
    private static final Logger logger = Logger.getLogger(LoginServiceBean.class);

    public UserAppUtil getUserAppUtil() {
        return userAppUtil;
    }

    public void setUserAppUtil(UserAppUtil userAppUtil) {
        this.userAppUtil = userAppUtil;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public LoginTransformerBean getLoginTransformerBean() {
        return loginTransformerBean;
    }

    public void setLoginTransformerBean(LoginTransformerBean loginTransformerBean) {
        this.loginTransformerBean = loginTransformerBean;
    }

    /**
     * Method checks for user authentication. If the user is authenticated, he's redirected to the default page of the role which user owns
     * @throws ServletException
     * @throws IOException 
     */
    public void doLogin() throws ServletException, IOException {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String userId = this.loginDataBean.getUserId();
        if (userId != null && !userId.trim().equalsIgnoreCase("")) {
            RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
            FacesContext.getCurrentInstance().responseComplete();
        }

        //  code for successful authenticated users
        try {
          
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

            String sessionAuthentication = (String) session.getAttribute(SystemConstantUtil.AUTHENTICATED_SESSION);

      
            if (sessionAuthentication != null && sessionAuthentication.trim().equalsIgnoreCase("true")) {

                if (userId == null) {
                    userId = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
                }

                if (userId != null) {
                    userId = userId.trim();
                }
                //  update user's last login information
                System.out.println("lets call updatelogin data maethod...............................");
                this.loginTransformerBean.updateLoginData(userId);

                if (this.userAppUtil.isUserInList(userId) && !this.userAppUtil.isSameUserSession(userId, session.getId())) {
                    this.userAppUtil.removeUser(userId);
                }

                if (!this.userAppUtil.isUserInList(userId)) {
                    this.userAppUtil.addUser(userId, this.loginDataBean.getRole(), session);
                }

            }

        } catch (Exception e) {
            logger.error(e);
        }


    }

    /**
     * Method for logging out the user
     */
    public void doLogout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_logout");
        try {
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        } catch (ServletException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
        FacesContext.getCurrentInstance().responseComplete();

        String userId = this.loginDataBean.getUserId();
        if (userId != null && !userId.trim().equalsIgnoreCase("") && this.userAppUtil.isUserInList(userId)) {
            this.userAppUtil.removeUser(userId);
            logger.info("v removed user----------------------");
        }

    }

    public void checkCookie() {
        this.loginDataBean.setPassword(null);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCookies();

        if (cookie != null && cookie.length > 0) {
            for (int i = 0; i < cookie.length; i++) {
                cookieName = cookie[i].getName();
                if (cookieName.equals("USERID")) {
                    this.loginDataBean.setUserId(cookie[i].getValue());
                } else if (cookieName.equals("PASSWORD")) {
                    this.loginDataBean.setPassword(cookie[i].getValue());
                } else if (cookieName.equals("REMEMBERME")) {
                    this.loginDataBean.setRememberMe(Boolean.parseBoolean(cookie[i].getValue()));
                }
//                this.doLogin();
            }
            logger.info("Cookie Found");
        } else {
            logger.info("Cannot find any cookie");
        }
    }
}
