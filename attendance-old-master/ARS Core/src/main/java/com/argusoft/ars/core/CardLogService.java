/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.CardLog;
import com.argusoft.ars.model.Holiday;
import com.argusoft.ars.model.Leave;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author smetaliya
 */
public interface CardLogService {

    public void createCardLog(CardLog cardLog);

    public void updateCardLog(CardLog cardLog);

    public CardLog retrieveCardLogByKey(Long key);

    public List<CardLog> retrieveAllCardLogs();

    public List<CardLog> retrieveCardLogsBasedOnLikeKeySearch(String startsWithString);

    public List<CardLog> retrieveCardLogBetweenDates(Long id, Date fromDate, Date toDate);

    public List<CardLog> retrieveLastPunchingTimeBetweenDate(Long id, Date fromDate, Date toDate);

    public List<CardLog> retrieveJobBreakBetweenDates(Long userId, Date fromDate, Date toDate);

    public Boolean doManualCardEntry(Long userId, Date date, boolean inStatus, boolean outStatus, long cardEnrollNo, Date shiftStartTime, Date shiftEndTime, String reason, Boolean isLateEntry);

    public int getNumberOfWorkingDays(Date fromDate, Date toDate, List<Holiday> holidayList);

    public float getNumberOfPresentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList);

    public int retrieveLateDays(Long id, Date fromDate, Date toDate, Date shiftStartTime, List<Holiday> holidayList, List<Leave> leaveList, int allowableLateMinutes);

    public List<CardLog> retrieveEntryCardLogBetweenDates(Long id, Date fromDate, Date toDate);

    public int getNumberOfAbsentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList, List<Date> presenDatesList);

    public List<Date> getAbsentDays(Long id, Date fromDate, Date toDate, List<Holiday> holidayList, List<Leave> leaveList, List<Date> presenDatesList);

    public List<CardLog> retrieveFirstPunchingTimeBetweenDate(Long id, Date fromDate, Date toDate, Boolean isFirstInEntry);

    public Map<Long, Map<Date, Date>> retrieveUserIdAndFirstPunchDetailByDateMap(List<Long> userIds,Date fromDate,Date toDate);

    public List<CardLog> retrieveCardLogBetweenDatesByUserIds(List<Long> userIds, Date fromDate, Date toDate);

}
