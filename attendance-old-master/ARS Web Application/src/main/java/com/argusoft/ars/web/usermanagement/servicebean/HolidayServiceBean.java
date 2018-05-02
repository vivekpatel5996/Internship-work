/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.HolidayDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.HolidayTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Harshit ServiceBean for Holiday
 */
@ManagedBean(name = "holidayServiceBean")
@RequestScoped
public class HolidayServiceBean {

    @ManagedProperty(value = "#{holidayDataBean}")
    private HolidayDataBean holidayDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{holidayTransformerBean}")
    private HolidayTransformerBean holidayTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    private Long holidayId;
    private Integer year;
    private List<Integer> holidayYearList;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setHolidayYearList(List<Integer> holidayYearList) {
        this.holidayYearList = holidayYearList;
    }

    public List<Integer> getHolidayYearList() {
        holidayYearList = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
        Integer curYear = cal.get(Calendar.YEAR);
        holidayYearList.add(curYear + 1);
        for (int i = 0; i < 5; i++) {
            holidayYearList.add(curYear - i);
        }
        return holidayYearList;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public HolidayDataBean getHolidayDataBean() {
        return holidayDataBean;
    }

    public void setHolidayDataBean(HolidayDataBean holidayDataBean) {
        this.holidayDataBean = holidayDataBean;
    }

    public HolidayTransformerBean getHolidayTransformerBean() {
        return holidayTransformerBean;
    }

    public void setHolidayTransformerBean(HolidayTransformerBean holidayTransformerBean) {
        this.holidayTransformerBean = holidayTransformerBean;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    /*
     * Method for Retrive Holiday List for dataTable
     */
    public void retrieveHolidayList() {
        try {
            Calendar fromDateCal, toDateCal;
            fromDateCal = Calendar.getInstance();
            toDateCal = Calendar.getInstance();
            fromDateCal.set(Calendar.DATE, 01);
            fromDateCal.set(Calendar.MONTH, 0);
            toDateCal.set(Calendar.DATE, 31);
            toDateCal.set(Calendar.MONTH, 11);
            Date fromDate, toDate;
            if (year == null) {
                fromDateCal.set(Calendar.YEAR, fromDateCal.get(Calendar.YEAR) - 1);
                toDateCal.set(Calendar.YEAR, toDateCal.get(Calendar.YEAR) + 1);
            } else {
                fromDateCal.set(Calendar.YEAR, year);
                toDateCal.set(Calendar.YEAR, year);
            }
            fromDate = fromDateCal.getTime();
            toDate = toDateCal.getTime();
            systemResultViewUtil.setHolidayDataBeansList(holidayTransformerBean.retrieveAllHoliday(fromDate, toDate));
            System.out.println("Retrive Holiday Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   
    /*
     * Method for get holiday detail.
     */

    public void fillHolidayDetail() {
        HolidayDataBean holiday = holidayTransformerBean.retrieveHolidayDetail(holidayId);
        holidayDataBean.setHolidayId(holiday.getHolidayId());
        holidayDataBean.setHolidayName(holiday.getHolidayName());
        holidayDataBean.setDate(holiday.getDate());
        holidayDataBean.setType(holiday.getType());
    }

    /*
     * Method for Update Holiday
     */
    public void updateHoliday() {
        try {
            if (holidayDataBean != null) {
                String response = holidayTransformerBean.updateHoliday(holidayDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Holiday updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    retrieveHolidayList();
                    doDatabeanNull();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    System.out.println("Holiday Name already created");
                    messageDataBean.setMessage("already holiday on this date.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    System.out.println("Holiday not created");
                    messageDataBean.setMessage("Error =" + response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error =>" + e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }

    }

    /*
     * Method for Delete Holiday
     */
    public void deleteHoliday() {
        try {
            System.out.println("********************ServiceBean=>deleteHoliday********");
            if (holidayDataBean != null) {
                holidayTransformerBean.deleteHoliday(holidayDataBean);
                System.out.println("Holiday Delete Sussesfully");
                messageDataBean.setMessage("Holiday deleted successfully.");
                messageDataBean.setIsSuccess(Boolean.TRUE);
                retrieveHolidayList();
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error in Deleting Holiday !!!!");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    private void doDatabeanNull() {
        holidayDataBean.setHolidayName(null);
        holidayDataBean.setDate(null);
        holidayDataBean.setHolidayId(null);
        holidayDataBean.setType(null);
    }

    public void addHolidayToList() {
        try {
            System.out.println("**************holiday list ***************");
            Boolean isHolidayAvailable = holidayTransformerBean.isHolidayAvailable(holidayDataBean.getDate());
            if (isHolidayAvailable) {
                Boolean isHolidayAddedInList = false;
                List<HolidayDataBean> holidayDataBeans = new ArrayList<HolidayDataBean>();
                if (systemResultViewUtil.getAddNewHolidayList() != null) {
                    holidayDataBeans = systemResultViewUtil.getAddNewHolidayList();
                    for (HolidayDataBean holidayDataBeanDetail : holidayDataBeans) {
                        if (holidayDataBeanDetail.getDate().equals(holidayDataBean.getDate())) {
                            isHolidayAddedInList = true;
//                            break;
                        }
                    }
                }
                if (!isHolidayAddedInList) {
                    HolidayDataBean holidayDataBeanDetail = new HolidayDataBean();
                    holidayDataBeanDetail.setHolidayDayDataBeanDetail(holidayDataBean);
                    holidayDataBeans.add(holidayDataBeanDetail);
                    systemResultViewUtil.setAddNewHolidayList(holidayDataBeans);
                    doDatabeanNull();
                    messageDataBean.setIsSuccess(true);
                } else {
                    messageDataBean.setIsSuccess(false);
                    messageDataBean.setMessage("Same holiday date is in the list alreay.");
                }
            } else {
                messageDataBean.setMessage("On this date already holiday.");
                messageDataBean.setIsSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void submitHoliday() {
        try {
            String msg = "";
            System.out.println("*******Submit holiday method call***************");
            for (HolidayDataBean holidayDataBeanDetail : systemResultViewUtil.getAddNewHolidayList()) {
                holidayTransformerBean.createHoliday(holidayDataBeanDetail);
            }
            messageDataBean.setIsSuccess(true);
            messageDataBean.setMessage("Holidays inserted successfully.");
            this.retrieveHolidayList();
            systemResultViewUtil.getAddNewHolidayList().clear();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
