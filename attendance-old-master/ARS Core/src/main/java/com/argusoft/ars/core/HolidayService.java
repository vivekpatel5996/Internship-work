/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.Holiday;
import java.util.Date;
import java.util.List;

/**
 *This Class is used to perform various operations on Holiday and related entities.
 * @author Harshit
 */
public interface HolidayService {

    /**
     * createHoliday method creates an Object of Class
     * Holiday, by calling saveEntity method of its parent.
     * @param holiday Takes an Object of Class Holiday,
     * which is to be created.
     */
    void createHoliday(Holiday holiday);

    /**
     * updateHoliday method updates an Object of Class
     * Holiday, by calling updateEntity method of its parent.
     * @param holiday Takes an Object of Class Holiday,
     * which is to be updated.
     */
    public void updateHoliday(Holiday holiday);

    /**
     * retrieveHolidayByKey method retrieves the Holiday
     * Object, by calling getEntityById method of its parent.
     * @param holidayId Takes PK of the Holiday
     * @return Returns the Object of Holiday.
     */
    public Holiday retrieveHolidayById(Long holidayId);

    /**
     * retrieveAllHolidays method retrieves all Holiday
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<Holiday> retrieveAllHoliday();

    public List<Holiday> retrieveHolidayBetweenDates(Date fromDate, Date toDate);

    public boolean isHolidayAvailable(Date date, boolean isCasualHolidayOnly, boolean isRestrictedHolidayRequierd);

    public List<Holiday> retrieveHolidayByTypeAndBetweenDate(String type, Date fromDate, Date toDate);

    public List<Holiday> retrieveHolidayBetweenDates(Date fromDate, Date toDate, boolean isCasualHolidayOnly, boolean isRestrictedHolidayRequire, List<Date> weekDaysList);
}