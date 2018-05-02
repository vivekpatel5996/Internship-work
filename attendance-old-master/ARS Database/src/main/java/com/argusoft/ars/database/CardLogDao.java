/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.CardLog;
import com.argusoft.ars.model.Holiday;
import com.argusoft.ars.model.Leave;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smetaliya
 */
public interface CardLogDao extends GenericDao<CardLog, Long> {

    public List<CardLog> retrieveCardLogBetweenDates(Long id, Date fromDate, Date toDate);

    public List<CardLog> retriveLastPunchingTimeCardLogDetail(Long id, Date fromDate, Date toDate);

    public List<CardLog> retrieveJobBreak(Long id, Date fromDate, Date toDate);

    public float retrieveNumberOfPresentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList);

    public int retrieveNumberOfLateDays(Long id, Date fromDate, Date toDate, Date shiftStartTime, List<Holiday> holidayList, List<Leave> leaveList, int allowableLateMinutes);

    public List<CardLog> retrieveEntryCardLogBetweenDates(Long id, Date fromDate, Date toDate);

    public List<CardLog> retriveFirstPunchingTimeCardLogDetail(Long id, Date fromDate, Date toDate, Boolean isFirstInEntry);

    public List<CardLog> retrieveCardLogBetweenDatesByUserIds(List<Long> userIds, Date fromDate, Date toDate);
}
