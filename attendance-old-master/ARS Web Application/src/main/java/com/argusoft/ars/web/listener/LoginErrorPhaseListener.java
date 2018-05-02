/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.listener;

/**
 *
 * @author smodi
 */
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;



public class LoginErrorPhaseListener implements PhaseListener {

    private static final long serialVersionUID = -1216620620302322995L;

    @Override
    public void beforePhase(final PhaseEvent arg0) {
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
                "SPRING_SECURITY_LAST_EXCEPTION");         
        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    "SPRING_SECURITY_LAST_EXCEPTION", null);
             FacesContext.getCurrentInstance().addMessage("errorMsg", new FacesMessage("The Username or Password you entered is incorrect"));
        } else if(e instanceof DisabledException) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    "SPRING_SECURITY_LAST_EXCEPTION", null);
            FacesContext.getCurrentInstance().addMessage("errorMsg", new FacesMessage("Your account is deactivated by Admin. Please contact Admin"));
        }
    }

    @Override
    public void afterPhase(final PhaseEvent arg0) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}