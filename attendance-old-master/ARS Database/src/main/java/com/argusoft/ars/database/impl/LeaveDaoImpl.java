/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.LeaveDao;
import com.argusoft.ars.model.Leave;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Harshit
 */
@Repository
public class LeaveDaoImpl extends BaseAbstractGenericDao<Leave, Long> implements LeaveDao {

    private static final String IS_ARCHIVE = "isArchive";
    private static final String ID = "userId";
    private static final String LEAVE_ID = "leaveId";
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";
    private static final String LEAVE_TYPE = "leaveType";
    private static final String APPROVAL_STATUS = "approvalStatus";
    private static final String IS_NOTIFICATION_SHOW = "isNotificationShow";

    /**
     * ****
     *
     * @param id
     * @return
     */
    @Override
    public List<Leave> retrieveNotArchiveLeave(Long id) {

        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        leaveList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + leaveList.size());
        return leaveList;
    }

    /**
     * ***
     *
     * @param id
     * @return
     */
    @Override
    public List<Leave> retrieveLeaveByUserId(Long id) {
        System.out.println("Id:" + id);
        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        leaveList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + leaveList.size());
        return leaveList;
    }

    /**
     * **
     *
     * @return
     */
    @Override
    public List<Leave> retrieveNotificationLeaveList() {
        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        criterions.add(Restrictions.isNotNull(APPROVAL_STATUS));
        leaveList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + leaveList.size());
        return leaveList;
    }

    @Override
    public List<Leave> retrieveCancelLeaveNotificationList() {
        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        criterions.add(Restrictions.isNull(APPROVAL_STATUS));
        leaveList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + leaveList.size());
        return leaveList;
    }

    /**
     * ***
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<Leave> retrieveApplyLeaveBetweenDates(Long id, Date fromDate, Date toDate) {
        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        criterions.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.and(Restrictions.lt(FROM_DATE, fromDate), Restrictions.gt(TO_DATE, fromDate)), Restrictions.and(Restrictions.lt(FROM_DATE, toDate), Restrictions.gt(TO_DATE, toDate)))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        leaveList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + leaveList.size());
        return leaveList;
    }

    /**
     * ****
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @param leaveType
     * @param isWithPandingLeave
     * @return
     */
    @Override
    public List<Leave> retrieveLeaveTypeBetweenDateWithApprovalStatus(Long id, Date fromDate, Date toDate, String leaveType, Boolean isWithPandingLeave) {
        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        if (id != null) {
            criterions.add(Restrictions.eq(ID, id));
        }
        if (leaveType != null) {
            criterions.add(Restrictions.eq(LEAVE_TYPE, leaveType));
        }
//        criterions.add(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        criterions.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.and(Restrictions.lt(FROM_DATE, fromDate), Restrictions.gt(TO_DATE, fromDate)), Restrictions.and(Restrictions.lt(FROM_DATE, toDate), Restrictions.gt(TO_DATE, toDate)))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        if (isWithPandingLeave == true) {
            criterions.add(Restrictions.or(Restrictions.eq(APPROVAL_STATUS, "Pending"), Restrictions.eq(APPROVAL_STATUS, "Approve")));
        } else {
            criterions.add(Restrictions.eq(APPROVAL_STATUS, "Approve"));
        }
        leaveList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + leaveList.size());
        return leaveList;
    }

    /**
     * ***
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @param leaveType
     * @return
     */
    @Override
    public List<Leave> retrieveLeaveBetweenDate(Long id, Date fromDate, Date toDate, Long leaveId, String leaveType) {
        List<Leave> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        if (id != null) {
            criterions.add(Restrictions.eq(ID, id));
        }
        if (leaveId != null) {
            criterions.add(Restrictions.ne(LEAVE_ID, leaveId));
        }
        if (leaveType != null) {
            criterions.add(Restrictions.eq(LEAVE_TYPE, leaveType));
        }
//        criterions.add(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        criterions.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.and(Restrictions.lt(FROM_DATE, fromDate), Restrictions.gt(TO_DATE, fromDate)), Restrictions.and(Restrictions.lt(FROM_DATE, toDate), Restrictions.gt(TO_DATE, toDate)))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        leaveList = super.findByCriteriaList(criterions);
        if (leaveList != null && !leaveList.isEmpty()) {
            System.out.println("----List Size" + leaveList.size());
        }
        return leaveList;
    }

    @Override
    public Leave retrieveLastLeaveDetail(Long id, Date toDate) {
        Session session = getSession();
        List l = session.createCriteria(Leave.class).add(Restrictions.le(TO_DATE, toDate)).add(Restrictions.eq(ID, id)).setProjection(Projections.projectionList().add(Projections.max(TO_DATE)).add(Projections.groupProperty(LEAVE_ID))).list();
        Iterator it = l.iterator();
        while (it.hasNext()) {
            Object[] obj = (Object[]) it.next();
            System.out.println((Long) obj[0]);
            System.out.println((Long) obj[1]);
//            Leave leave = retrieveById((Long) obj[0]);
            return null;
        }
        return null;
    }
}
