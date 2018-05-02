/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mpritmani
 */
@ManagedBean(name = "messageDataBean")
@RequestScoped
public class MessageDataBean {

    private Long id;
    private String pageId;
    private String message;
    private Boolean isSuccess;
    private Boolean hasRefreshBtn;
    private Boolean hasCancelBtn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Boolean getHasCancelBtn() {
        return hasCancelBtn;
    }

    public void setHasCancelBtn(Boolean hasCancelBtn) {
        this.hasCancelBtn = hasCancelBtn;
    }

    public Boolean getHasRefreshBtn() {
        return hasRefreshBtn;
    }

    public void setHasRefreshBtn(Boolean hasRefreshBtn) {
        this.hasRefreshBtn = hasRefreshBtn;
    }
}
