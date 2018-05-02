/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.Leave;
import java.util.Date;
import java.util.List;

/**
 * This Class is used to perform various operations on Leave and related
 * entities.
 *
 * @author Harshit
 */
public interface LeaveService {

    /**
     * createLeave method creates an Object of Class Leave, by calling
     * saveEntity method of its parent.
     *
     * @param leave Takes an Object of Class Leave, which is to be created.
     * @return 
     */
    Long createLeave(Leave leave);

    /**
     * updateLeave method updates an Object of Class Leave, by calling
     * updateEntity method of its parent.
     *
     * @param leave Takes an Object of Class Leave, which is to be updated.
     */
    public void updateLeave(Leave leave);

    /**
     * retrieveLeaveByKey method retrieves the Leave Object, by calling
     * getEntityById method of its parent.
     *
     * @param leaveId Takes PK of the Leave
     * @return Returns the Object of Leave.
     */
    public Leave retrieveLeaveById(Long leaveId);

    /**
     * retrieveAllLeaves method retrieves all Leave Objects, by calling
     * findByCriteriaList method of its parent class.
     *
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<Leave> retrieveNotArchiveLeave(Long id);

    public List<Leave> retrieveLeaveByUserId(Long id);

    public List<Leave> retrievePandingLeave();

    public boolean isLeaveAlreadyApply(Long id, Date fromDate, Date toDate);

    public Float retrieveNoOfLeavesBetweenDate(Long id, Date fromDate, Date toDate, String leaveType, Boolean isWithPandingLeave);

    public List<Leave> retrieveLeavesBetweenDateWithApprovalStatus(Long id, Date fromDate, Date toDate, String leaveType, Boolean isWithPandingLeave);

    public List<Leave> retrieveLeavesBetweenDate(Long id, Date fromDate, Date toDate, String leaveType);

    public List<Leave> retrieveLeavesBetweenDateListWithoutSameLeaveDetail(Long id, Date fromDate, Date toDate, Long leaveId, String leaveType);

    public List<Leave> retrieveLeaveNotificationDetailByUserId(Long id);

    public List<Leave> retrieveCancelLeaveNotification();

    public Leave retrieveLeaveByDate(Long id,Date time);
}