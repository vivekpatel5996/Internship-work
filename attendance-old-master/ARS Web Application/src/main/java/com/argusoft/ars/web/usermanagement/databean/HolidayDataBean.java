/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author harshit
 */
@ManagedBean(name = "holidayDataBean")
@RequestScoped
public class HolidayDataBean {

    private Long holidayId;
    @Length(min = 3, max = 55, message = "Please enter holiday name between 3 To 55 Character")
    private String holidayName;
    private Date date;
    private String type;
    private String day;

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long HolidayId) {
        this.holidayId = HolidayId;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String HolidayName) {
        this.holidayName = HolidayName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHolidayDayDataBeanDetail(HolidayDataBean holidayDataBean) {
        this.holidayId = holidayDataBean.getHolidayId();
        this.holidayName = holidayDataBean.getHolidayName();
        this.date = holidayDataBean.getDate();
        this.type = holidayDataBean.getType();
        this.day = holidayDataBean.getDay();
    }
    
}
