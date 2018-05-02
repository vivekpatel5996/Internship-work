/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.handler;

import com.argusoft.ars.web.util.SystemConstantUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 *
 * @author Harshit
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    //  Other properties

    private static final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class);

    /**
     * Method is called when successful authentication done
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("---------------Custom Authentication Success Handler----------------");

        //        Setting Cookies to the user browser if remember me is enabled else removing existing cookies
        Cookie userIdCookie;
        Cookie passwordCookie;
        Cookie rememberMeCookie;

        if (request.getParameter("_spring_security_remember_me") != null) {
            userIdCookie = new Cookie("USERID", authentication.getName());
            passwordCookie = new Cookie("PASSWORD", request.getParameter("j_password"));
            rememberMeCookie = new Cookie("REMEMBERME", "true");

            userIdCookie.setMaxAge(3600);
            passwordCookie.setMaxAge(3600);
            rememberMeCookie.setMaxAge(3600);

            userIdCookie.setPath("/");
            passwordCookie.setPath("/");
            rememberMeCookie.setPath("/");
        } else {
            userIdCookie = new Cookie("USERID", "");
            passwordCookie = new Cookie("PASSWORD", "");
            rememberMeCookie = new Cookie("REMEMBERME", "false");

            userIdCookie.setMaxAge(0);
            passwordCookie.setMaxAge(0);
            rememberMeCookie.setMaxAge(0);

            userIdCookie.setPath("/");
            passwordCookie.setPath("/");
            rememberMeCookie.setPath("/");
        }
        response.addCookie(userIdCookie);
        response.addCookie(passwordCookie);
        response.addCookie(rememberMeCookie);

        SavedRequest savedRequest =
                new HttpSessionRequestCache().getRequest(request, response);

//        Redirection of the page according to the role of user

        if (request.isUserInRole(SystemConstantUtil.ROLE_SUPER_ADMIN)) {
            if (savedRequest != null) {
                String url = savedRequest.getRedirectUrl().split("/")[savedRequest.getRedirectUrl().split("/").length - 1];
                url = url.replaceAll(".jsf", "");
                response.sendRedirect(url);
            } else {
                response.sendRedirect(SystemConstantUtil.ROLE_SUPER_ADMIN_PAGE);
            }
        } else if (request.isUserInRole(SystemConstantUtil.ROLE_ADMIN)) {
            if (savedRequest != null && savedRequest.getRedirectUrl().indexOf("/superadmin/") == -1) {
                response.sendRedirect(savedRequest.getRedirectUrl());
            } else {
                response.sendRedirect(SystemConstantUtil.ROLE_ADMIN_PAGE);
            }
        } else if (request.isUserInRole(SystemConstantUtil.ROLE_MEMBER)) {
            if (savedRequest != null && savedRequest.getRedirectUrl().indexOf("/superadmin/") == -1) {
                String url = savedRequest.getRedirectUrl().split("/")[savedRequest.getRedirectUrl().split("/").length - 1];
                url = url.replaceAll(".jsf", "");
                response.sendRedirect(url);
            } else {
                response.sendRedirect(SystemConstantUtil.ROLE_MEMBER_PAGE);
            }
        }


//        Setting the authenticated session flag
        request.getSession(true).setAttribute(SystemConstantUtil.AUTHENTICATED_SESSION, "true");
    }
}
