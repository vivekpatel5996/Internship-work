/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.LeaveOpinion;
import java.util.List;

/**
 *
 * @author smetaliya
 */
public interface LeaveOpinionDao extends GenericDao<LeaveOpinion, Long> {

    public List<LeaveOpinion> retrieveLeaveOpinionListByLeaveId(Long leaveId);

    public List<LeaveOpinion> retrieveLeaveOpinionListByUserId(Long userId);

    public LeaveOpinion retrieveLeaveOpinionIdByLeaveIdAndUserId(Long id, Long leaveId);

    public List<LeaveOpinion> retrtiveIntrestedUserLeaveOpinionListByLeaveId(Long leaveId);

    public List<LeaveOpinion> retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(Long leaveId);

    public List<LeaveOpinion> retrieveLeaveOpinionListOfNotificationShow(Long userId);

    public List<LeaveOpinion> retriveDeleteUserLeaveOpinionList(Long leaveId, Long[] userIdList);

    public List<LeaveOpinion> retrieveUserSelectedLeaveOpinionDetailByLeaveId(Long leaveId);
}
