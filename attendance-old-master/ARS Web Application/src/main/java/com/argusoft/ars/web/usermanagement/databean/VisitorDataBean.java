/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "visitorDataBean")
@ViewScoped
public class VisitorDataBean {

    private Long visitorId;
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\s.]*)?$", message = "Please enter valid visitor name")
    private String visitorName;
    private Long cardIdPk;
    private String address;
    private String city;
    private String state;
    private String pincode;
    @Pattern(regexp = "[0-9]*$", message = "Enter Valid Mobile No")
    private String mobileNo;
    @Email(message = "Please enter valid emailId")
    private String email;
    private Date fromDate;
    private Date toDate;
    private String reasonToVisit;
    private Long cardId;
    private Long tempId;
    private Date visitEndDate;

    public Date getVisitEndDate() {
        return visitEndDate;
    }

    public void setVisitEndDate(Date visitEndDate) {
        this.visitEndDate = visitEndDate;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCardIdPk() {
        return cardIdPk;
    }

    public void setcardIdPk(Long cardIdPk) {
        this.cardIdPk = cardIdPk;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getReasonToVisit() {
        return reasonToVisit;
    }

    public void setReasonToVisit(String reasonToVisit) {
        this.reasonToVisit = reasonToVisit;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public void setNull() {
        this.visitorId = null;
        this.visitorName = null;
        this.cardIdPk = null;
        this.address = null;
        this.city = null;
        this.state = null;
        this.pincode = null;
        this.mobileNo = null;
        this.email = null;
        this.fromDate = null;
        this.toDate = null;
        this.reasonToVisit = null;
    }

    public void setVisitorDataBean(VisitorDataBean visitorDataBean) {
        this.visitorId = visitorDataBean.visitorId;
        this.visitorName = visitorDataBean.visitorName;
        this.cardIdPk = visitorDataBean.cardIdPk;
        this.address = visitorDataBean.address;
        this.city = visitorDataBean.city;
        this.state = visitorDataBean.state;
        this.pincode = visitorDataBean.pincode;
        this.mobileNo = visitorDataBean.mobileNo;
        this.email = visitorDataBean.email;
        this.fromDate = visitorDataBean.fromDate;
        this.toDate = visitorDataBean.toDate;
        this.reasonToVisit = visitorDataBean.reasonToVisit;
        this.cardId = visitorDataBean.cardId;
        this.tempId = visitorDataBean.cardIdPk;
        this.visitEndDate=visitorDataBean.visitEndDate;
    }
}
