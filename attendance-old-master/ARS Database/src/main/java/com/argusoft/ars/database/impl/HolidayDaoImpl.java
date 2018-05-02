/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.HolidayDao;
import com.argusoft.ars.model.Holiday;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Harshit
 */
@Repository
public class HolidayDaoImpl extends BaseAbstractGenericDao<Holiday, Long> implements HolidayDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String HOLIDAY_NAME = "holidayName";
    private static final String HOLIDAY_DATE = "holidayDate";
    private static final String IS_ARCHIVE = "isArchive";
    private static final String TYPE = "type";
    private static final String HOLIDAY_TYPE = "type";

    @Override
    public List<Holiday> retrieveAllActiveHoliday() {
        Date yearStartDate = new Date(new Date().getYear(), 00, 01, 0, 0, 0);
        List<Holiday> holidayList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        criterions.add(Restrictions.ge(HOLIDAY_DATE, yearStartDate));
        holidayList = super.findByCriteriaList(criterions);
        return holidayList;
    }

    @Override
    public Holiday retrieveActiveHolidayByDate(Date date, boolean isCasualHolidaySearch, boolean isRestrictedHolidaySearch) {
        List<Holiday> holidayList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(HOLIDAY_DATE, date));
        if (isCasualHolidaySearch == true && isRestrictedHolidaySearch == false) {
            criterions.add(Restrictions.eq(HOLIDAY_TYPE, "Official"));
        }
        if (isRestrictedHolidaySearch == true && isCasualHolidaySearch == false) {
            criterions.add(Restrictions.eq(HOLIDAY_TYPE, "Restricted"));
        }
        holidayList = super.findByCriteriaList(criterions);
        if (holidayList.isEmpty()) {
            return null;
        } else {
            return holidayList.get(0);
        }
    }

    @Override
    public List<Holiday> retrieveHolidayByTypeAndBetweenDates(String type, Date fromDate, Date toDate) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        if (type != null) {
            criterions.add(Restrictions.eq(TYPE, type));
        }
        if (fromDate != null) {
            criterions.add(Restrictions.ge(HOLIDAY_DATE, fromDate));
        }
        if (toDate != null) {
            criterions.add(Restrictions.le(HOLIDAY_DATE, toDate));
        }
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<Holiday> retrieveActiveHolidayBetweenDates(Date fromDate, Date toDate) {
        List<Holiday> holidayList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        ///criterions.add(Restrictions.eq(TYPE,"Official"));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        criterions.add(Restrictions.ge(HOLIDAY_DATE, fromDate));
        criterions.add(Restrictions.le(HOLIDAY_DATE, toDate));
        holidayList = super.findByCriteriaList(criterions);
        return holidayList;
    }

    @Override
    public List<Holiday> retrieveHolidayBetweenDates(Date fromDate, Date toDate, boolean isCasualHolidayRequierd, boolean isRestrictedHolidayRequierd, List<Date> weekDaysList) {
        List<Holiday> holidayList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        ///criterions.add(Restrictions.eq(TYPE,"Official"));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        criterions.add(Restrictions.ge(HOLIDAY_DATE, fromDate));
        criterions.add(Restrictions.le(HOLIDAY_DATE, toDate));
        if (isCasualHolidayRequierd == true && isRestrictedHolidayRequierd == false) {
            criterions.add(Restrictions.eq(HOLIDAY_TYPE, "Official"));
        }
        if (isRestrictedHolidayRequierd == true && isCasualHolidayRequierd == false) {
            criterions.add(Restrictions.eq(HOLIDAY_TYPE, "Restricted"));
        }
        if (weekDaysList != null && weekDaysList.size() > 0) {
            criterions.add(Restrictions.not(Restrictions.in(HOLIDAY_DATE, weekDaysList)));
        }
        holidayList = super.findByCriteriaList(criterions);
        return holidayList;
    }
}
