/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.CardLogDao;
import com.argusoft.ars.model.CardLog;
import com.argusoft.ars.model.Holiday;
import com.argusoft.ars.model.Leave;
import java.lang.reflect.Type;
import java.util.*;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author smetaliya
 */
@Repository
public class CardLogDaoImpl extends BaseAbstractGenericDao<CardLog, Long> implements CardLogDao {

    public static final String CARD_PUNCHING_TIME = "cardPunchingTime";
    public static final String USER_ID = "userId";
    public static final String ENTRY_TYPE = "cardEntryType";

    /**
     * ***
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<CardLog> retrieveCardLogBetweenDates(Long id, Date fromDate, Date toDate) {
        Session session = getSession();
        Criteria cr = session.createCriteria(CardLog.class);
        if (fromDate != null) {
            fromDate = setFromDate(fromDate);
            cr.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        }
        if (toDate != null) {
            toDate = setToDate(toDate);
            cr.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        }
        if (id != null) {
            cr.add(Restrictions.eq(USER_ID, id));
        }
        cr.addOrder(Order.asc(CARD_PUNCHING_TIME));
        List<CardLog> cardLogs = cr.list();
        session.close();
        return cardLogs;
    }

    /**
     * *****
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<CardLog> retrieveJobBreak(Long id, Date fromDate, Date toDate) {
        Session session = getSession();
        Criteria cr = session.createCriteria(CardLog.class);
        if (fromDate != null) {
            cr.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        }
        if (toDate != null) {
            cr.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        }
        if (id != null) {
            cr.add(Restrictions.eq(USER_ID, id));
        }
        cr.addOrder(Order.asc(CARD_PUNCHING_TIME));
        List<CardLog> cardLogs = cr.list();
        session.close();
        return cardLogs;
    }

    /**
     * ********
     *
     * @param fromDate
     * @return
     */
    private Date setFromDate(Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 01);
        fromDate = cal.getTime();
        return fromDate;
    }

    /**
     * ********
     *
     * @param toDate
     * @return
     */
    private Date setToDate(Date toDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(toDate);
        cal1.set(Calendar.HOUR_OF_DAY, 23);
        cal1.set(Calendar.MINUTE, 59);
        cal1.set(Calendar.SECOND, 59);
        cal1.set(Calendar.MILLISECOND, 00);
        toDate = cal1.getTime();
        return toDate;

    }

    @Override
    public float retrieveNumberOfPresentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList) {
        float numberOfPresentDays = 0;
        List<CardLog> cardLogList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        List<Date> cardLogsList = new ArrayList<Date>();
        fromDate = setFromDate(fromDate);
        toDate = setToDate(toDate);
        criterions.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        criterions.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        criterions.add(Restrictions.eq(USER_ID, id));
        cardLogList = super.findByCriteriaList(criterions);
        Collections.sort(cardLogList, new Comparator<CardLog>() {

            @Override
            public int compare(CardLog cardLog1, CardLog cardLog2) {
                return cardLog1.getCardPunchingTime().compareTo(cardLog2.getCardPunchingTime());
            }
        });
        List<Date> weekEndList = getWeekEndsDaysBetweenTwoDates(fromDate, toDate);
        for (int i = 0; i < holidayList.size(); i++) {
            Date date = holidayList.get(i).getHolidayDate();
            weekEndList.add(date);
        }
        boolean flag = true;
        Date prevDate = null;
        for (int i = 0; i < cardLogList.size(); i++) {
            Date d = cardLogList.get(i).getCardPunchingTime();
            if (flag == true) {
                prevDate = setToDate(d);
                cardLogsList.add(d);
            }
            if (setFromDate(d).getTime() < prevDate.getTime()) {
                flag = false;
                continue;
            } else {
                flag = true;
            }
            if (flag == true) {
                prevDate = setToDate(d);
                cardLogsList.add(d);
                flag = false;
            }
        }
        for (int i = 0; i < leaveList.size(); i++) {
            System.out.println("in leavelist");
            if ((leaveList.get(i).getFromDateLeaveType()).equals("HDM") && (leaveList.get(i).getToDateLeaveType()).equals("HDM")) {
                numberOfPresentDays += 0.5;
                System.out.println("in fromDate HD");
            }
            if ((leaveList.get(i).getFromDateLeaveType()).equals("HDE") && (leaveList.get(i).getToDateLeaveType()).equals("HDE")) {
                numberOfPresentDays += 0.5;
                System.out.println("in fromDate HD");
            }
            if ((leaveList.get(i).getFromDateLeaveType()).equals("HDE") && (leaveList.get(i).getToDateLeaveType()).equals("HDM")) {
                numberOfPresentDays++;
                System.out.println("in toDate HD");
            }
            Date date = leaveList.get(i).getFromDate();
            Date date1 = leaveList.get(i).getToDate();
            Calendar startCal;
            Calendar endCal;
            startCal = Calendar.getInstance();
            startCal.setTime(date);
            endCal = Calendar.getInstance();
            endCal.setTime(date1);
            do {
                weekEndList.add(startCal.getTime());
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

        }
        for (int i = 0; i < cardLogsList.size(); i++) {
            boolean flag1 = false;
            Date date = cardLogsList.get(i);
            for (int j = 0; j < weekEndList.size(); j++) {
                Date date1 = weekEndList.get(j);
                System.out.println("week end list" + setFromDate(date1));
                if (setFromDate(date).getTime() == setFromDate(date1).getTime()) {
                    flag1 = true;
                    break;
                }
            }
            if (flag1 == false) {
                System.out.println("Late Day....." + setFromDate(date));
                numberOfPresentDays++;
            }
        }
        return numberOfPresentDays;
    }

    @Override
    public int retrieveNumberOfLateDays(Long id, Date fromDate, Date toDate, Date shiftStartTime, List<Holiday> holidayList, List<Leave> leaveList, int allowableLateMinutes) {
        int numberOfLateDays = 0;
        short a = 0;
        List<CardLog> cardLogList;
        //systemConfigurationServiceBean.retrieveType('allowableLateMinutes');

        List<Criterion> criterions = new LinkedList<Criterion>();
        fromDate = setFromDate(fromDate);
        toDate = setToDate(toDate);
        criterions.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        criterions.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        criterions.add(Restrictions.eq(USER_ID, id));
        criterions.add(Restrictions.eq(ENTRY_TYPE, a));
        cardLogList = super.findByCriteriaList(criterions);
        Collections.sort(cardLogList, new Comparator<CardLog>() {

            @Override
            public int compare(CardLog cardLog1, CardLog cardLog2) {
                return cardLog1.getCardPunchingTime().compareTo(cardLog2.getCardPunchingTime());
            }
        });
        boolean flag = true;
        Date prevDate = null;
        List<Date> cardLogsList = new ArrayList<Date>();
        List<Date> weekEndList = getWeekEndsDaysBetweenTwoDates(fromDate, toDate);
        for (int i = 0; i < holidayList.size(); i++) {
            Date date = holidayList.get(i).getHolidayDate();
            weekEndList.add(date);
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
                weekEndList.add(startCal.getTime());
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        }
        for (int i = 0; i < cardLogList.size(); i++) {
            Date d = cardLogList.get(i).getCardPunchingTime();
            if (flag == true) {
                prevDate = setToDate(d);
                if (d.getHours() > shiftStartTime.getHours() || (d.getHours() == shiftStartTime.getHours() && d.getMinutes() > (shiftStartTime.getMinutes() + allowableLateMinutes))) {
                    cardLogsList.add(d);
                }
            }
            if (setFromDate(d).getTime() < prevDate.getTime()) {
                flag = false;
                continue;
            } else {
                flag = true;
            }
            if (flag == true) {
                prevDate = setToDate(d);
                if (d.getHours() > shiftStartTime.getHours() || (d.getHours() == shiftStartTime.getHours() && d.getMinutes() > (shiftStartTime.getMinutes() + allowableLateMinutes))) {
                    System.out.println("all late days" + d);
                    cardLogsList.add(d);
                }
            }
            flag = false;
        }
        for (int i = 0; i < cardLogsList.size(); i++) {
            boolean flag1 = false;
            Date date = cardLogsList.get(i);
            for (int j = 0; j < weekEndList.size(); j++) {
                Date date1 = weekEndList.get(j);
                System.out.println("week end list" + setFromDate(date1));
                if (setFromDate(date).getTime() == setFromDate(date1).getTime()) {
                    flag1 = true;
                    break;
                }
            }
            if (flag1 == false) {
                System.out.println("Late Day....." + setFromDate(date));
                numberOfLateDays++;
            }
        }
        return numberOfLateDays;
    }

    @Override
    public List<CardLog> retrieveEntryCardLogBetweenDates(Long id, Date fromDate, Date toDate) {
        short a = 0;
        List<CardLog> cardLogList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        fromDate = setFromDate(fromDate);
        toDate = setToDate(toDate);
        criterions.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        criterions.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        criterions.add(Restrictions.eq(USER_ID, id));
        criterions.add(Restrictions.eq(ENTRY_TYPE, a));
        cardLogList = super.findByCriteriaList(criterions);
        Collections.sort(cardLogList, new Comparator<CardLog>() {

            @Override
            public int compare(CardLog cardLog1, CardLog cardLog2) {
                return cardLog1.getCardPunchingTime().compareTo(cardLog2.getCardPunchingTime());
            }
        });
        return cardLogList;
    }

    private List getWeekEndsDaysBetweenTwoDates(Date startDate, Date endDate) {
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
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        return weekEndList;
    }

    @Override
    public List<CardLog> retriveLastPunchingTimeCardLogDetail(Long id, Date fromDate, Date toDate) {
        Session session = getSession();
//        List l = session.createCriteria(CardLog.class).add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate)).add(Restrictions.le(CARD_PUNCHING_TIME, toDate)).setProjection(Projections.projectionList().add(Projections.max(CARD_PUNCHING_TIME)).add(Projections.groupProperty(USER_ID))).list();
        Criteria cr = session.createCriteria(CardLog.class);
        cr.setProjection(Projections.projectionList().add(Projections.max(CARD_PUNCHING_TIME)).add(Projections.groupProperty(USER_ID)));
        if (fromDate != null) {
            cr.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        }
        if (toDate != null) {
            cr.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        }
        if (id != null) {
            cr.add(Restrictions.eq(USER_ID, id));
        }
        List l = cr.list();
        session.close();
        if (l != null && !l.isEmpty()) {
            List<CardLog> cardLogs1 = new ArrayList<CardLog>();
            List<CardLog> cardLogs = new ArrayList<CardLog>();
            Iterator it = l.iterator();
            while (it.hasNext()) {
                cardLogs.clear();
                Object[] obj = (Object[]) it.next();
                List<Criterion> criterions = new LinkedList<Criterion>();
                criterions.add(Restrictions.eq(USER_ID, obj[1]));
                criterions.add(Restrictions.eq(CARD_PUNCHING_TIME, obj[0]));
                cardLogs = super.findByCriteriaList(criterions);
                if (cardLogs != null && !cardLogs.isEmpty());
                {
                    cardLogs1.add(cardLogs.get(0));
                }
            }
            return cardLogs1;
        }
        return null;
    }

    @Override
    public List<CardLog> retriveFirstPunchingTimeCardLogDetail(Long id, Date fromDate, Date toDate, Boolean isFirstInEntry) {
        Session session = getSession();
//        List l = session.createCriteria(CardLog.class).add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate)).add(Restrictions.le(CARD_PUNCHING_TIME, toDate)).setProjection(Projections.projectionList().add(Projections.max(CARD_PUNCHING_TIME)).add(Projections.groupProperty(USER_ID))).list();
        Criteria cr = session.createCriteria(CardLog.class);
        cr.setProjection(Projections.projectionList().add(Projections.min(CARD_PUNCHING_TIME)).add(Projections.groupProperty(USER_ID)));
        if (fromDate != null) {
            cr.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        }
        if (toDate != null) {
            cr.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        }
        if (id != null) {
            cr.add(Restrictions.eq(USER_ID, id));
        }
        if (isFirstInEntry == true) {
            cr.add(Restrictions.eq(ENTRY_TYPE, (short) 0));
        }
        List l = cr.list();
        session.close();
        if (l != null && l.size() > 0) {
            Iterator it = l.iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                List<Criterion> criterions = new LinkedList<Criterion>();
                criterions.add(Restrictions.eq(USER_ID, obj[1]));
                criterions.add(Restrictions.eq(CARD_PUNCHING_TIME, obj[0]));
                return super.findByCriteriaList(criterions);
            }
        }
        return null;
    }

    @Override
    public List<CardLog> retrieveCardLogBetweenDatesByUserIds(List<Long> userIds, Date fromDate, Date toDate) {
        Session session = getSession();
        Criteria cr = session.createCriteria(CardLog.class);
        if (fromDate != null) {
            cr.add(Restrictions.ge(CARD_PUNCHING_TIME, fromDate));
        }
        if (toDate != null) {
            cr.add(Restrictions.le(CARD_PUNCHING_TIME, toDate));
        }
        if (userIds != null && !userIds.isEmpty()) {
            cr.add(Restrictions.in(USER_ID, userIds));
        }
        cr.addOrder(Order.asc(CARD_PUNCHING_TIME));
        List<CardLog> cardLogs = cr.list();
        session.close();
        return cardLogs;
    }
}
