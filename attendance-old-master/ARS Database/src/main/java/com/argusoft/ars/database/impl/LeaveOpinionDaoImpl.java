/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.LeaveOpinionDao;
import com.argusoft.ars.model.LeaveOpinion;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author smetaliya
 */
@Repository
public class LeaveOpinionDaoImpl extends BaseAbstractGenericDao<LeaveOpinion, Long> implements LeaveOpinionDao {

    private static final String LEAVE_ID = "leaveId";
    private static final String USER_ID = "userId";
    private static final String IS_ARCHIVE = "isArchive";
    private static final String OPINION = "opinion";
    private static final String IS_NOTIFICATION_SHOW = "isNotificationShow";
    private static final String IS_SELECTED_BY_USER = "isSelectedByUser";

    @Override
    public List<LeaveOpinion> retrieveLeaveOpinionListByLeaveId(Long leaveId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(LEAVE_ID, leaveId));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }

    @Override
    public List<LeaveOpinion> retrieveLeaveOpinionListByUserId(Long userId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(USER_ID, userId));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }

    @Override
    public LeaveOpinion retrieveLeaveOpinionIdByLeaveIdAndUserId(Long id, Long leaveId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(USER_ID, id));
        criterions.add(Restrictions.eq(LEAVE_ID, leaveId));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        if(leaveOpinionsList != null && !leaveOpinionsList.isEmpty())
        {
            return leaveOpinionsList.get(0);
        }
        return null;
    }

    @Override
    public List<LeaveOpinion> retrtiveIntrestedUserLeaveOpinionListByLeaveId(Long leaveId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(LEAVE_ID, leaveId));
        criterions.add(Restrictions.or(Restrictions.and(Restrictions.eq(IS_ARCHIVE, true), Restrictions.and(Restrictions.ne(OPINION, ""), Restrictions.isNotNull(OPINION))), Restrictions.and(Restrictions.eq(IS_ARCHIVE, false), Restrictions.or(Restrictions.eq(OPINION, ""), Restrictions.isNull(OPINION)))));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }

    @Override
    public List<LeaveOpinion> retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(Long leaveId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(LEAVE_ID, leaveId));
        criterions.add(Restrictions.eq(IS_ARCHIVE, true));
        criterions.add(Restrictions.or(Restrictions.eq(OPINION, ""), Restrictions.isNull(OPINION)));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }

    @Override
    public List<LeaveOpinion> retrieveLeaveOpinionListOfNotificationShow(Long userId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(USER_ID, userId));
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }

    @Override
    public List<LeaveOpinion> retriveDeleteUserLeaveOpinionList(Long leaveId, Long[] userIdList) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(LEAVE_ID, leaveId));
        
        criterions.add(Restrictions.not(Restrictions.in(USER_ID, userIdList)));
        criterions.add(Restrictions.eq(IS_SELECTED_BY_USER, true));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }

    @Override
    public List<LeaveOpinion> retrieveUserSelectedLeaveOpinionDetailByLeaveId(Long leaveId) {
        List<LeaveOpinion> leaveOpinionsList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(LEAVE_ID, leaveId));
        criterions.add(Restrictions.eq(IS_SELECTED_BY_USER, true));
        leaveOpinionsList = super.findByCriteriaList(criterions);
        return leaveOpinionsList;
    }
}
