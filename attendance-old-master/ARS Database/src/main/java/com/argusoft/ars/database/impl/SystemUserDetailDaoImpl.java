/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.SystemUserDetailDao;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Harshit
 */
@Repository
public class SystemUserDetailDaoImpl extends BaseAbstractGenericDao<SystemUserDetail, Long> implements SystemUserDetailDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String IS_ARCHIVE = "isArchive";
    private static final String DEP_ID = "depId.deptId";
    private static final String CARD_ENROLL_NO = "cardEnrollNo.id";
    private static final String DESG_ID = "desgId.desId";
    private static final String SHIFT_ID = "shiftId.shiftId";
    private static final String IS_GIVE_OPINION = "isGiveOpinion";
    private static final String IS_ATTENDANCE_MANAGER = "isAttendanceManager";
    private static final String USER_ID = "userId";
    private static final String EMP_ID = "empId";

    @Override
    public List<SystemUserDetail> retrieveAllActiveSystemUserDetail() {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveSystemUserDetailNotEqualByType(Long id, String type) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        if ("department".equals(type)) {
            criterions.add(Restrictions.or(Restrictions.isNull(DEP_ID), Restrictions.ne(DEP_ID, id)));
        } else if ("designation".equals(type)) {
            criterions.add(Restrictions.or(Restrictions.isNull(DESG_ID), Restrictions.ne(DESG_ID, id)));
        } else {
            criterions.add(Restrictions.or(Restrictions.isNull(SHIFT_ID), Restrictions.ne(SHIFT_ID, id)));
        }
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveUserDetailWithoutCard() {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.isNull(CARD_ENROLL_NO));
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveActiveLeaveOpinionList() {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_GIVE_OPINION, true));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrievenNotEqualUserIdList(Long[] userIdList, boolean isWithLeaveOpinion, boolean isWithAttendanceManager) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        if (isWithLeaveOpinion == true) {
            criterions.add(Restrictions.eq(IS_GIVE_OPINION, true));
        }
        if (isWithAttendanceManager == true) {
            criterions.add(Restrictions.eq(IS_ATTENDANCE_MANAGER, true));
        }
        if (userIdList != null && userIdList.length != 0) {
            criterions.add(Restrictions.not(Restrictions.in(USER_ID, userIdList)));
        }
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveActiveAttendaceManagerList() {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ATTENDANCE_MANAGER, true));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveActiveUserByDepartmentId(Long depId) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(DEP_ID, depId));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveActiveUserByShiftId(Long shiftId) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(SHIFT_ID, shiftId));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveActiveUserByDesignationId(Long desId) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(DESG_ID, desId));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveActiveUser() {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public Integer retriveNoOfActiveEmployeeByEmpId(String empId, Long id) {
        Session session = getSession();
        Criteria c = session.createCriteria(SystemUserDetail.class).add(Restrictions.eq(EMP_ID, empId));
        if (id != null) {
            c.add(Restrictions.ne(USER_ID, id));
        }
        Integer noOfuser = super.findTotalCountforCriteria(c);
        session.close();
        return noOfuser;
    }

    @Override
    public List<SystemUserDetail> retrieveSystemUserDetailByType(Long id, String type) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        if ("department".equals(type)) {
            criterions.add(Restrictions.eq(DEP_ID, id));
        } else if ("designation".equals(type)) {
            criterions.add(Restrictions.eq(DESG_ID, id));
        } else {
            criterions.add(Restrictions.eq(SHIFT_ID, id));
        }
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        return super.findByCriteriaList(criterions);
    }

    @Override
    public List<SystemUserDetail> retrieveSystemUserDetailByType(List<Long> userIds, Boolean isActive) {
        List<Criterion> criterions = new LinkedList<Criterion>();
//        criterions.add(Restrictions.eq(IS_ACTIVE, true));

        if (userIds != null && !userIds.isEmpty()) {
            criterions.add(Restrictions.in(USER_ID, userIds));
        }
        if (isActive != null) {
            criterions.add(Restrictions.eq(IS_ACTIVE, isActive));
        }
        return super.findByCriteriaList(criterions);
    }
}
