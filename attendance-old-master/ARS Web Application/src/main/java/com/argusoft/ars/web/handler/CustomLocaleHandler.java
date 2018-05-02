/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.handler;

import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.sun.faces.application.view.MultiViewHandler;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Harshit
 */
public class CustomLocaleHandler extends MultiViewHandler{

    /**
     * Method to define locale of the user from the user's preferred language
     * @param context
     * @return 
     */
    @Override
    public Locale calculateLocale(FacesContext context) {
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            LoginDataBean loginDataBean = (LoginDataBean) session.getAttribute("loginDataBean");
            if (loginDataBean != null && loginDataBean.getPreferredLanguage() != null) {
                return new Locale(loginDataBean.getPreferredLanguage());
            }
        }
        return super.calculateLocale(context);
    }
}
