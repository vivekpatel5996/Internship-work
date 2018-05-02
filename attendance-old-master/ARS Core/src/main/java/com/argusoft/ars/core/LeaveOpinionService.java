/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.LeaveOpinion;
import java.util.List;

/**
 *
 * @author smetaliya
 */
public interface LeaveOpinionService {

    public void createLeaveOpinion(LeaveOpinion leaveOpinion);

    public void updateLeaveOpinion(LeaveOpinion leaveOpinion);

    public LeaveOpinion retrieveLeaveOpinionById(Long id);

    public List<Long> sendLeaveOpinionNotification(Long userId, Long leaveId, Long[] leaveOpinionUserIdList);

    public List<LeaveOpinion> retrieveLeaveOpinionListByLeaveId(Long leaveId);

    public Long retrieveLeaveOpinionIdByLeaveIdAndUserId(Long id, Long leaveId);

    public List<LeaveOpinion> retrtiveIntrestedUserLeaveOpinionListByLeaveId(Long leaveId);

    public List<LeaveOpinion> retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(Long leaveId);

    public List<LeaveOpinion> retrieveOpinionLeaveResponseList(Long id);

    public LeaveOpinion retrieveLeaveOpinionByLeaveIdAndUserId(Long leaveId, Long id);

    public void sendLeaveOpinionNotificationForModifyLeave(Long leaveId, Long[] userIdList);

    public Long[] retrieveUserSelectedLeaveOpinionUserIdList(Long leaveId);
}
