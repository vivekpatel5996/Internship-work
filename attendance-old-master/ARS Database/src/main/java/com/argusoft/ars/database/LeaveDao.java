/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.Leave;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to perform CRUD operations on Leave Entity.
 *
 * @author Harshit
 */
public interface LeaveDao extends GenericDao<Leave, Long> {

    /**
     * retrieveAllActiveLeave method retrieves all Active Leave Detail Objects,
     * by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class Leave.
     */
    public List<Leave> retrieveNotArchiveLeave(Long id);

    /**
     * retrieveActiveLeaveByName method retrieve Active Leave Detail by Name.
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the Object of Class Leave.
     */
    public List<Leave> retrieveLeaveByUserId(Long id);

    public List<Leave> retrieveNotificationLeaveList();

    public List<Leave> retrieveApplyLeaveBetweenDates(Long id, Date fromDate, Date toDate);

    public List<Leave> retrieveLeaveTypeBetweenDateWithApprovalStatus(Long id, Date fromDate, Date toDate, String leaveType, Boolean isWithPandingLeave);

    public List<Leave> retrieveLeaveBetweenDate(Long id, Date fromDate, Date toDate,Long leaveId, String leaveType);

    public List<Leave> retrieveCancelLeaveNotificationList();
    
    public Leave retrieveLastLeaveDetail(Long id, Date toDate);
}