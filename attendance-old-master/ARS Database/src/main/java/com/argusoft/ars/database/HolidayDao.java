/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.Holiday;
import java.util.Date;
import java.util.List;

/**
 * This class is used to perform CRUD operations on Holiday Entity.
 *
 * @author Harshit
 */
public interface HolidayDao extends GenericDao<Holiday, Long> {

    /**
     * retrieveAllActiveHoliday method retrieves all Active Holiday Detail
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class Holiday.
     */
    public List<Holiday> retrieveAllActiveHoliday();
    
    public List<Holiday> retrieveActiveHolidayBetweenDates(Date fromDate,Date toDate);

    public Holiday retrieveActiveHolidayByDate(Date date,boolean isCasualHolidayOnly,boolean isRestrictedHolidayRequierd);

    public List<Holiday> retrieveHolidayByTypeAndBetweenDates(String type, Date fromDate, Date toDate);


    public List<Holiday> retrieveHolidayBetweenDates(Date fromDate, Date toDate, boolean casualHolidayOnly, boolean restrictedHolidayRequire, List<Date> weekDaysList);
}