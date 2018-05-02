/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.CardLogService;
import com.argusoft.ars.database.CardLogDao;
import com.argusoft.ars.model.CardLog;
import com.argusoft.ars.model.Holiday;
import com.argusoft.ars.model.Leave;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("cardLogService")
public class CardLogServiceImpl implements CardLogService {

    public CardLogServiceImpl() {
    }
    @Autowired
    private CardLogDao cardLogDao;

    /**
     * ***
     *
     * @param cardLog
     */
    @Override
    public void createCardLog(CardLog cardLog) {
        cardLogDao.update(cardLog);
    }

    /**
     * ******
     *
     * @param cardLog
     */
    @Override
    @CacheEvict(value = "systemCache", key = "#cardLog.cardLogId")
    public void updateCardLog(CardLog cardLog) {
        cardLogDao.update(cardLog);
    }

    /**
     * *****
     *
     * @param key
     * @return
     */
    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public CardLog retrieveCardLogByKey(Long key) {
        CardLog cardLog = null;
        if (key != null) {
            cardLog = cardLogDao.retrieveById(key);
        }
        return cardLog;
    }

    /**
     * *****
     *
     * @return
     */
    @Override
    public List<CardLog> retrieveAllCardLogs() {

        return cardLogDao.retrieveAll();

    }

    /**
     * ***
     *
     * @param startsWithString
     * @return
     */
    @Override
    public List<CardLog> retrieveCardLogsBasedOnLikeKeySearch(String startsWithString) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * **
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<CardLog> retrieveCardLogBetweenDates(Long id, Date fromDate, Date toDate) {
        return cardLogDao.retrieveCardLogBetweenDates(id, fromDate, toDate);
    }

    /**
     * ***
     *
     * @param date
     * @param shitTime
     * @return
     */
    private Date getPunchingTime(Date date, Date shitTime, Boolean isEntryLate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(shitTime);
        if (isEntryLate == true) {
            cal.set(Calendar.HOUR, timeCal.get(Calendar.HOUR) + 1);
        } else {
            cal.set(Calendar.HOUR, timeCal.get(Calendar.HOUR));
        }
        cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        Date d = cal.getTime();
        return d;
    }

    /**
     * *****
     *
     * @param fromDate
     * @param i
     * @return
     */
    private Date getDate(Date fromDate, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.DATE, i);
        return calendar.getTime();
    }

    /**
     * *******
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<CardLog> retrieveJobBreakBetweenDates(Long id, Date fromDate, Date toDate) {
        return cardLogDao.retrieveJobBreak(id, fromDate, toDate);
    }

    /**
     * *****
     *
     * @param userId
     * @param fromDate
     * @param toDate
     * @param inStatus
     * @param outStatus
     * @param cardEnrollNo
     * @param shiftStartTime
     * @param shiftEndTime
     * @param reason
     * @return
     */
    @Override
    public Boolean doManualCardEntry(Long userId, Date date, boolean inStatus, boolean outStatus, long cardEnrollNo, Date shiftStartTime, Date shiftEndTime, String reason, Boolean isLateEntry) {
        try {
            System.out.println("Shift Start time:" + shiftStartTime);
            System.out.println("Shift End Time:" + shiftEndTime);
            if (inStatus == true) {
                CardLog cardLog = new CardLog();
                cardLog.setCardEnrollNumber(cardEnrollNo);
                cardLog.setCardEntryReason(reason);
                cardLog.setUserId(userId);
                cardLog.setCardEntryType((short) 0);
                cardLog.setCardPunchingTime(getPunchingTime(date, shiftStartTime, isLateEntry));
                System.out.println("in Punching time:" + getPunchingTime(date, shiftStartTime, isLateEntry));
                createCardLog(cardLog);
            }
            if (outStatus == true) {
                CardLog cardLog = new CardLog();
                cardLog.setCardEnrollNumber(cardEnrollNo);
                cardLog.setCardEntryReason(reason);
                cardLog.setUserId(userId);
                cardLog.setCardEntryType((short) 1);
                cardLog.setCardPunchingTime(getPunchingTime(date, shiftEndTime, false));
                createCardLog(cardLog);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * ******
     *
     * @param fromDate
     * @param toDate
     * @param holidayList
     * @return
     */
    @Override
    public int getNumberOfWorkingDays(Date fromDate, Date toDate, List<Holiday> holidayList) {
        toDate = setDate(toDate);
        fromDate = setDate(fromDate);
        int totalDays = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
        int numberOfWeekEndsDays = getWeekEndsDaysBetweenTwoDates(fromDate, toDate);
        int numberOfHolidays = getNumberOfHolidays(fromDate, toDate, holidayList);
        int totalWorkingDays = totalDays - numberOfWeekEndsDays - numberOfHolidays + 1;
        return totalWorkingDays;
    }

    /**
     * ****
     *
     * @param fromDate
     * @param toDate
     * @param holidayList
     * @return
     */
    private int getNumberOfHolidays(Date fromDate, Date toDate, List<Holiday> holidayList) {
        int totalDays = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
        int numberOfHoliDays = 0;
        for (int i = 0; i < totalDays; i++) {
            for (int j = 0; j < holidayList.size(); j++) {
                Holiday holiday = holidayList.get(j);
                if (holiday.getHolidayDate().getTime() < toDate.getTime() && holiday.getHolidayDate().getTime() > fromDate.getTime() && holiday.getType().equals("Restricted Holiday")) {
                    Calendar startCal;
                    startCal = Calendar.getInstance();
                    startCal.setTime(holiday.getHolidayDate());
                    if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                        numberOfHoliDays++;
                    }
                }
            }
        }
        return numberOfHoliDays;
    }

    /**
     * ***
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private int getWeekEndsDaysBetweenTwoDates(Date startDate, Date endDate) {
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
                ++weekEndDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        return weekEndDays;
    }

    @Override
    public float getNumberOfPresentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList) {

        return cardLogDao.retrieveNumberOfPresentDays(id, fromDate, toDate, holidayList, leaveList);
    }

    @Override
    public int retrieveLateDays(Long id, Date fromDate, Date toDate, Date shiftStartTime, List<Holiday> holidayList, List<Leave> leaveList, int allowableLateMinutes) {
        return cardLogDao.retrieveNumberOfLateDays(id, fromDate, toDate, shiftStartTime, holidayList, leaveList, allowableLateMinutes);
    }

    @Override
    public List<CardLog> retrieveEntryCardLogBetweenDates(Long id, Date fromDate, Date toDate) {
        return cardLogDao.retrieveEntryCardLogBetweenDates(id, fromDate, toDate);
    }

    @Override
    public int getNumberOfAbsentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList, List<Date> presentdateList) {
        System.out.println(".......................number of absentDays......................");
        int absentDays = 0;
        List<Date> dateList = new ArrayList<Date>();
        List<Date> allDateList = new ArrayList<Date>();
        List<Date> absentDateList = new ArrayList<Date>();
        for (int i = 0; i < holidayList.size(); i++) {
            Date date = holidayList.get(i).getHolidayDate();
            if (setDate(date).getTime() >= setDate(fromDate).getTime() && setDate(date).getTime() <= setDate(toDate).getTime()) {
                dateList.add(date);
            }
        }
        System.out.println("HolidayList-------------" + holidayList);
        System.out.println("LeaveList................" + leaveList);

        for (int i = 0; i < leaveList.size(); i++) {
            Date date = leaveList.get(i).getFromDate();
            Date date1 = leaveList.get(i).getToDate();
            Calendar startCal;
            Calendar endCal;
            startCal = Calendar.getInstance();
            startCal.setTime(date);
            endCal = Calendar.getInstance();
            endCal.setTime(date1);
            do {
                dateList.add(startCal.getTime());
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        }
        List<Date> weekEndDateList = getWeekEndsDatesBetweenTwoDates(fromDate, toDate);
        for (int i = 0; i < weekEndDateList.size(); i++) {
            Date date = weekEndDateList.get(i);
            dateList.add(date);
        }
        for (int i = 0; i < presentdateList.size(); i++) {
            Date date = presentdateList.get(i);
            dateList.add(date);
        }
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(fromDate);
        endCal = Calendar.getInstance();
        endCal.setTime(toDate);
        do {
            allDateList.add(startCal.getTime());
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

        for (int i = 0; i < allDateList.size(); i++) {
            boolean flag = false;
            Date date = allDateList.get(i);
            for (int j = 0; j < dateList.size(); j++) {
                Date date1 = dateList.get(j);
                if (setDate(date).getTime() == setDate(date1).getTime()) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                absentDateList.add(date);
                absentDays++;
            }
        }
        return absentDays;
    }

    private Date setDate(Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        fromDate = cal.getTime();
        return fromDate;
    }

    private List getWeekEndsDatesBetweenTwoDates(Date startDate, Date endDate) {
        List<Date> weekEndList = new ArrayList<Date>();
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        if (startCal.getTimeInMillis() >= endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }
        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                weekEndList.add(startCal.getTime());
                System.out.println("week End List" + startCal.getTime());
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        return weekEndList;
    }

    @Override
    public List<Date> getAbsentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList, List<Date> presentdateList) {

        List<Date> dateList = new ArrayList<Date>();
        List<Date> allDateList = new ArrayList<Date>();
        List<Date> absentDateList = new ArrayList<Date>();

        for (int i = 0; i < holidayList.size(); i++) {
            Date date = holidayList.get(i).getHolidayDate();
            if (setDate(date).getTime() >= setDate(fromDate).getTime() && setDate(date).getTime() <= setDate(toDate).getTime()) {
                dateList.add(date);
            }
        }
        for (int i = 0; i < leaveList.size(); i++) {
            Date date = leaveList.get(i).getFromDate();
            Date date1 = leaveList.get(i).getToDate();
            Calendar startCal;
            Calendar endCal;
            startCal = Calendar.getInstance();
            startCal.setTime(date);
            endCal = Calendar.getInstance();
            endCal.setTime(date1);
            do {
                dateList.add(startCal.getTime());
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        }
        List<Date> weekEndDateList = getWeekEndsDatesBetweenTwoDates(fromDate, toDate);
        for (int i = 0; i < weekEndDateList.size(); i++) {
            Date date = weekEndDateList.get(i);
            dateList.add(date);
        }
        for (int i = 0; i < presentdateList.size(); i++) {
            Date date = presentdateList.get(i);
            dateList.add(date);
        }
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(fromDate);
        endCal = Calendar.getInstance();
        endCal.setTime(toDate);
        do {
            allDateList.add(startCal.getTime());
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        for (int i = 0; i < allDateList.size(); i++) {
            boolean flag = false;
            Date date = allDateList.get(i);
            for (int j = 0; j < dateList.size(); j++) {
                Date date1 = dateList.get(j);
                if (setDate(date).getTime() == setDate(date1).getTime()) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                absentDateList.add(date);
            }
        }
        return absentDateList;
    }

    @Override
    public List<CardLog> retrieveLastPunchingTimeBetweenDate(Long id, Date fromDate, Date toDate) {
        return cardLogDao.retriveLastPunchingTimeCardLogDetail(id, fromDate, toDate);
    }

    @Override
    public List<CardLog> retrieveFirstPunchingTimeBetweenDate(Long id, Date fromDate, Date toDate, Boolean isFirstInEntry) {
        return cardLogDao.retriveFirstPunchingTimeCardLogDetail(id, fromDate, toDate, isFirstInEntry);
    }

    @Override
    public Map<Long, Map<Date, Date>> retrieveUserIdAndFirstPunchDetailByDateMap(List<Long> userIds, Date fromDate, Date toDate) {
        System.out.println("From Date ===" + fromDate);
        System.out.println("To Date ====" + toDate);
        fromDate = convertToStartDate(fromDate);
        toDate = convertToEndDate(toDate);
        List<CardLog> cardLogs = cardLogDao.retrieveCardLogBetweenDatesByUserIds(userIds, fromDate, toDate);
        if (cardLogs != null && !cardLogs.isEmpty()) {
            Map<Long, Map<Date, Date>> userIdAndFirstPunchDetailByDateMap = new HashMap<Long, Map<Date, Date>>();
//            Collections.sort(cardLogs, new Comparator<CardLog>() {
//
//                @Override
//                public int compare(CardLog cardLog1, CardLog cardLog2) {
//                    return cardLog1.getCardPunchingTime().compareTo(cardLog2.getCardPunchingTime());
//                }
//            });
            for (CardLog cardLog : cardLogs) {
                Map<Date, Date> dateAndFirstPunchingDateMap = userIdAndFirstPunchDetailByDateMap.get(cardLog.getUserId());
                if (dateAndFirstPunchingDateMap == null) {
                    dateAndFirstPunchingDateMap = new HashMap<Date, Date>();
                    userIdAndFirstPunchDetailByDateMap.put(cardLog.getUserId(), dateAndFirstPunchingDateMap);
                }
                Date date = setDate(cardLog.getCardPunchingTime());
                Date firstPunchingTime = dateAndFirstPunchingDateMap.get(date);
                if (firstPunchingTime == null) {
                    dateAndFirstPunchingDateMap.put(date, cardLog.getCardPunchingTime());
                }
            }
            return userIdAndFirstPunchDetailByDateMap;
        }
        return null;
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

    @Override
    public List<CardLog> retrieveCardLogBetweenDatesByUserIds(List<Long> userIds, Date fromDate, Date toDate) {
        return cardLogDao.retrieveCardLogBetweenDatesByUserIds(userIds, fromDate, toDate);
    }
}
