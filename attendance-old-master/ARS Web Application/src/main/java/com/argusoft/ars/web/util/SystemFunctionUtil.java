/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.exolab.castor.types.DateTime;

/**
 *
 * @author mpritmani
 */
public class SystemFunctionUtil {

//    
    public static List<Date> getWeekEndsDaysBetweenTwoDates(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int weekEndDays = 0;
        if (startCal.getTimeInMillis() >= endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }
        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                dates.add(startCal.getTime());
                ++weekEndDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        return dates;
    }

    public static Date convertDateTimeToUserTimezone(Date inputDate, String userTimeZone) {
        try {
            if (userTimeZone.equalsIgnoreCase(SystemConstantUtil.TIMEZONE_PDT)) {
                userTimeZone = "GMT-7:00";
            }
            inputDate.setTime(inputDate.getTime() - TimeZone.getTimeZone(SystemConstantUtil.SERVER_TIMEZONE).getRawOffset() + TimeZone.getTimeZone(userTimeZone).getRawOffset());
        } catch (Exception e) {
            inputDate = null;
        }
        return inputDate;
    }

    public static Date convertDateTimeToServerTimeZone(Date inputDate, String userTimeZone) {
        try {
            if (userTimeZone.equalsIgnoreCase(SystemConstantUtil.TIMEZONE_PDT)) {
                userTimeZone = "GMT-7:00";
            }
            inputDate.setTime(inputDate.getTime() - TimeZone.getTimeZone(userTimeZone).getRawOffset() + TimeZone.getTimeZone(SystemConstantUtil.SERVER_TIMEZONE).getRawOffset());
        } catch (Exception e) {
            inputDate = null;
        }
        return inputDate;
    }

    public static Date convertDateTime(Date inputDate, String sourceTimeZone, String destinationTimeZone) {
        try {
            if (sourceTimeZone.equalsIgnoreCase(SystemConstantUtil.TIMEZONE_PDT)) {
                sourceTimeZone = "GMT-7:00";
            }
            if (destinationTimeZone.equalsIgnoreCase(SystemConstantUtil.TIMEZONE_PDT)) {
                destinationTimeZone = "GMT-7:00";
            }
            if (!sourceTimeZone.equalsIgnoreCase(destinationTimeZone)) {
                inputDate.setTime(inputDate.getTime() - TimeZone.getTimeZone(sourceTimeZone).getRawOffset() + TimeZone.getTimeZone(destinationTimeZone).getRawOffset());
            }
        } catch (Exception e) {
            inputDate = null;
        }
        return inputDate;
    }

    public static Flash getFlashScope() {
        return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }

    public static String retrieveHoursAndMinite(long time) {
        long secs = time / 1000;
        int hours = (int) secs / 3600;
        secs = secs % 3600;
        int mins = (int) secs / 60;
        String hour, min;
        if (hours >= 0 && hours < 10) {
            hour = "0" + hours;
        } else {
            hour = String.valueOf(hours);
        }
        if (mins >= 0 && mins < 10) {
            min = "0" + mins;
        } else {
            min = String.valueOf(mins);
        }
        return hour + " hrs " + min + " mins";
    }

    public static String replaceTextForEmail(String emailText, String empName, String shiftStartTime, String shiftEndTime, String fromDate, String toDate, String info, String approver, String date, String response, String oldDate, String oldFromDate, String oldToDate, String password, String newPassword) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");

        if (approver == null) {
            approver = "";
        }
        if (empName == null) {
            empName = "";
        }
        if (shiftStartTime == null) {
            shiftStartTime = "";
        }
        if (shiftEndTime == null) {
            shiftEndTime = "";
        }
        if (fromDate == null) {
            fromDate = "";
        }
        if (info == null) {
            info = "";
        }
        if (toDate == null) {
            toDate = "";
        }
        if (response == null) {
            response = "";
        }
        if (oldDate == null) {
            oldDate = "";
        }
        if (oldFromDate == null) {
            oldFromDate = "";
        }
        if (oldToDate == null) {
            oldToDate = "";
        }
        if (password == null) {
            password = "";
        }
        if (newPassword == null) {
            newPassword = "";
        }
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_APPROVER, approver);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_CURRENT_DATE, sdf.format(new Date()).toString());
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_CURRENT_TIME, sdf1.format(new Date()).toString());
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_DATE, date);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_EMPLOYEE_NAME, empName);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_FROM_DATE, fromDate);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_INFO, info);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_LATE_DURATION, "");
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_OLD_DATE, oldDate);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_OLD_FROM_DATE, oldFromDate);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_OLD_TO_DATE, oldToDate);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_RESPONSE, response);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_SHIFT_END_TIME, shiftEndTime);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_SHIFT_START_TIME, shiftStartTime);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_TO_DATE, toDate);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_PASSWORD, password);
        emailText = emailText.replaceAll(SystemConstantUtil.EMAIL_NEW_PASSWORD, newPassword);
        return emailText;
    }

    public static Date convertToFirstDayOfTheMonth(Date date) {
        Calendar calDate = Calendar.getInstance();
        if (date != null) {
            calDate.setTime(date);
        }
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MILLISECOND, 0);
        calDate.set(Calendar.DAY_OF_MONTH, 1);

        return calDate.getTime();
    }

    public static Date convertToLastDayOfTheMonth(Date date) {
        Calendar calDate = Calendar.getInstance();
        if (date != null) {
            calDate.setTime(date);
        }
        calDate.set(Calendar.HOUR_OF_DAY, 23);
        calDate.set(Calendar.MINUTE, 59);
        calDate.set(Calendar.SECOND, 59);
        calDate.set(Calendar.MILLISECOND, 59);
        calDate.set(Calendar.DAY_OF_MONTH, calDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calDate.getTime();
    }

    public static Date getDateAfterNoOfDays(Date date, int noOfDays) {
        Calendar dateCal = Calendar.getInstance();
        if (date != null) {
            dateCal.setTime(date);
            dateCal.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH) + noOfDays);
        }
        return dateCal.getTime();
    }
    
    public static Date convertToStartDate(Date date) {
        Calendar calDate = Calendar.getInstance();
        if (date != null) {
            calDate.setTime(date);
        }
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MILLISECOND, 0);
        return calDate.getTime();
    }

    public static Date convertToEndDate(Date date) {
        Calendar calDate = Calendar.getInstance();
        if (date != null) {
            calDate.setTime(date);
        }
        calDate.set(Calendar.HOUR_OF_DAY, 23);
        calDate.set(Calendar.MINUTE, 59);
        calDate.set(Calendar.SECOND, 59);
        calDate.set(Calendar.MILLISECOND, 999);
        return calDate.getTime();
    }
    
    
}
