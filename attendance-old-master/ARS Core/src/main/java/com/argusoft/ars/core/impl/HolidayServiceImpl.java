/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.HolidayService;
import com.argusoft.ars.database.HolidayDao;
import com.argusoft.ars.model.Holiday;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayDao holidayDao;

    @Override
    public void createHoliday(Holiday holiday) {
        try {
            holidayDao.update(holiday);
        } catch (Exception e) {
            System.out.println("Exception in Core createHoliday method");
            System.out.println("Exception:" + e);
        }
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#holiday.holidaytId")
    public void updateHoliday(Holiday holiday) {
        holidayDao.update(holiday);
    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public Holiday retrieveHolidayById(Long holidayId) {
        Holiday holiday = null;
        if (holidayId != null) {
            holiday = holidayDao.retrieveById(holidayId);
        }
        return holiday;
    }

    @Override
    public List<Holiday> retrieveAllHoliday() {
        List<Holiday> holidaysList = holidayDao.retrieveAllActiveHoliday();
        return holidaysList;
    }

    @Override
    public boolean isHolidayAvailable(Date date, boolean isCasualHolidayOnly, boolean isRestrictedHolidayRequierd) {
        Holiday holiday = holidayDao.retrieveActiveHolidayByDate(date, isCasualHolidayOnly, isRestrictedHolidayRequierd);
        if (holiday == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Holiday> retrieveHolidayByTypeAndBetweenDate(String type, Date fromDate, Date toDate) {
        return holidayDao.retrieveHolidayByTypeAndBetweenDates(type, fromDate, toDate);
    }

    @Override
    public List<Holiday> retrieveHolidayBetweenDates(Date fromDate, Date toDate) {
        return holidayDao.retrieveActiveHolidayBetweenDates(fromDate, toDate);
    }

    @Override
    public List<Holiday> retrieveHolidayBetweenDates(Date fromDate, Date toDate, boolean isCasualHolidayOnly, boolean isRestrictedHolidayRequire, List<Date> weekDaysList) {
        return holidayDao.retrieveHolidayBetweenDates(fromDate, toDate, isCasualHolidayOnly, isRestrictedHolidayRequire, weekDaysList);
    }
}
