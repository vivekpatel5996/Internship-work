/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.databean;

import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "leaveOpinionDataBean")
@RequestScoped
public class LeaveOpinionDataBean {

    private Long userId;
    private String userName;
    private Long levaeOpinionId;
    private String opinion;

    public Long getLevaeOpinionId() {
        return levaeOpinionId;
    }

    public void setLevaeOpinionId(Long levaeOpinionId) {
        this.levaeOpinionId = levaeOpinionId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setLeaveOpinionDataBean(LeaveOpinionDataBean leaveOpinionDataBean)
    {
        this.userId = leaveOpinionDataBean.getUserId();
        this.userName = leaveOpinionDataBean.getUserName();
        this.levaeOpinionId = leaveOpinionDataBean.getLevaeOpinionId();
        this.opinion = leaveOpinionDataBean.getOpinion();
    }
}
