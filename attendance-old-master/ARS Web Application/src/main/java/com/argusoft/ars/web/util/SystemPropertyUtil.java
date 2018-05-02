/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.util;

import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mmodi
 */
@ManagedBean(name = "systemPropertyUtil")
@SessionScoped
public class SystemPropertyUtil {
    
    private String appVersion; //Variable for storing system version.
    private String serverTimeZone = Calendar.getInstance().getTimeZone().getID();

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getServerTimeZone() {
        return serverTimeZone;
    }

    public void setServerTimeZone(String serverTimeZone) {
        this.serverTimeZone = serverTimeZone;
    }
  
}
