/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.HolidayService;
import com.argusoft.ars.model.Holiday;
import com.argusoft.ars.web.usermanagement.databean.HolidayDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for Holiday.
 *
 * @author Harshit
 */
@ManagedBean(name = "holidayTransformerBean")
@RequestScoped
public class HolidayTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{holidayService}")
    private HolidayService holidayService;
//    @ManagedProperty(value="#{userService}")
//    private UserService userService;
    //  Other properties
    private static final Logger log = Logger.getLogger(HolidayTransformerBean.class);

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * Method to convert HolidayDataBean to Holiday Model.
     *
     */
    private Holiday convertHolidayDataBeanToHolidayModel(HolidayDataBean holidayDataBean, Holiday holiday) {

        holiday.setCreatedBy(loginDataBean.getId());
        holiday.setHolidayName(holidayDataBean.getHolidayName());
        holiday.setCreatedDate(new Date());
        holiday.setType(holidayDataBean.getType());
        holiday.setHolidayDate(holidayDataBean.getDate());
        holiday.setIsActive(true);
        holiday.setIsArchive(false);
        return holiday;
    }

    /**
     * Method to convert Holiday Model to HolidayDataBean.
     */
    private HolidayDataBean convertHolidayModelToHolidayDataBean(Holiday holiday) {
        try {
            HolidayDataBean holidayDataBean = new HolidayDataBean();
            holidayDataBean.setHolidayName(holiday.getHolidayName());
            holidayDataBean.setHolidayId(holiday.getHolidayId());
            holidayDataBean.setType(holiday.getType());
            holidayDataBean.setDate(holiday.getHolidayDate());
            holidayDataBean.setDay(getHolidayWeekDay(holiday.getHolidayDate().getDay()));
            return holidayDataBean;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive all active Holiday
     */
    public List<HolidayDataBean> retrieveAllHoliday(Date fromDate, Date toDate) {
        List<Holiday> holidayList = holidayService.retrieveHolidayBetweenDates(fromDate, toDate);
        List<HolidayDataBean> holidayDataBeansList = new ArrayList<HolidayDataBean>();
        if (holidayList != null) {
            if (!holidayList.isEmpty()) {
                for (Holiday holiday : holidayList) {
                    HolidayDataBean holidayDataBean = convertHolidayModelToHolidayDataBean(holiday);
                    holidayDataBeansList.add(holidayDataBean);
                }
            }
        }
        return holidayDataBeansList;
    }

    /**
     * Method to Retrive Holiday Detail by Holiday Id
     */
    public HolidayDataBean retrieveHolidayDetail(Long holidayId) {
        Holiday holiday = holidayService.retrieveHolidayById(holidayId);
        HolidayDataBean holidayDataBean = convertHolidayModelToHolidayDataBean(holiday);
        return holidayDataBean;
    }

    /**
     * Method to Add Holiday object.
     *
     */
    public String createHoliday(HolidayDataBean holidayDataBean) {
        Holiday holiday = convertHolidayDataBeanToHolidayModel(holidayDataBean, new Holiday());
        this.holidayService.createHoliday(holiday);
        return SystemConstantUtil.SUCCESS;
    }

    /**
     * Method to Update Holiday Detail
     */
    public String updateHoliday(HolidayDataBean holidayDataBean) {
        try {
            Holiday holiday = holidayService.retrieveHolidayById(holidayDataBean.getHolidayId());
            Boolean isHolidayAvailable = true;
            if (!holidayDataBean.getDate().equals(holiday.getHolidayDate())) {
                isHolidayAvailable = isHolidayAvailable(holidayDataBean.getDate());
            }
            if (isHolidayAvailable) {
                holiday.setHolidayName(holidayDataBean.getHolidayName());
                holiday.setHolidayDate(holidayDataBean.getDate());
                holiday.setType(holidayDataBean.getType());
                holidayService.updateHoliday(holiday);
                return SystemConstantUtil.SUCCESS;
            } else {
                return SystemConstantUtil.REPEAT;
            }
        } catch (Exception ex) {
            log.error(ex);
            return ex.toString();
        }
    }

    /**
     * Method to Inactivate Holiday.
     */
    public void deleteHoliday(HolidayDataBean holidayDataBean) {
        try {
            System.out.println("**************holidayTranformerBean=>deleteHoliday************");
            Holiday holiday = holidayService.retrieveHolidayById(holidayDataBean.getHolidayId());
            if (holiday != null) {
                holiday.setIsActive(false);
                holidayService.updateHoliday(holiday);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    /**
     * Method to check whether Holiday Name is already added in active or not.
     */
    public boolean isHolidayAvailable(Date date) {
        return holidayService.isHolidayAvailable(date, true, true);
    }

    public List<Holiday> retrieveHolidayByTypeAndBetweenDates(String type,Date fromDate,Date toDate) {
        
        List<Holiday> holidayList = holidayService.retrieveHolidayByTypeAndBetweenDate(type,fromDate,toDate);
        
        return holidayList;
    }

    /**
     * Method to Retrive WeekDay Holiday.
     */
    private String getHolidayWeekDay(int day) {
        String[] weekdays = new DateFormatSymbols().getWeekdays();
        return weekdays[day + 1];
    }
}
