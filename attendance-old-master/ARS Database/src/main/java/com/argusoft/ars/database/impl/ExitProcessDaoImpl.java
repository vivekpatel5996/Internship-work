/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.ExitProcessDao;
import com.argusoft.ars.model.ExitProcess;
import com.argusoft.ars.model.Leave;
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
public class ExitProcessDaoImpl extends BaseAbstractGenericDao<ExitProcess, Long> implements ExitProcessDao {

    private static final String USER_ID = "userId";
    private static final String IS_ACTIVE = "isActive";
    private static final String APPROVAL_STATUS = "approvalStatus";
    private static final String IS_NOTIFICATION_SHOW = "isNotificationShow";
    private static final String IS_ARCHIVE = "isArchive";

    @Override
    public ExitProcess retrieveExitProcessByUserId(Long id) {
        List<ExitProcess> exitProcessesList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(USER_ID, id));
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        exitProcessesList = super.findByCriteriaList(criterions);
        if (exitProcessesList != null) {
            return exitProcessesList.get(0);
        }
        return null;
    }

    @Override
    public List<ExitProcess> retrievePandingResignationList() {
        List<ExitProcess> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        criterions.add(Restrictions.isNotNull(APPROVAL_STATUS));
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        leaveList = super.findByCriteriaList(criterions);
        return leaveList;
    }

    @Override
    public List<ExitProcess> retrieveCancelResignationNotification() {
        List<ExitProcess> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        criterions.add(Restrictions.isNull(APPROVAL_STATUS));
        criterions.add(Restrictions.eq(IS_ACTIVE, false));
        leaveList = super.findByCriteriaList(criterions);
        return leaveList;
    }

    @Override
    public List<ExitProcess> retrieveNotArchiveResignation(Long id) {
        List<ExitProcess> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        criterions.add(Restrictions.eq(USER_ID, id));
        leaveList = super.findByCriteriaList(criterions);
        return leaveList;
    }

    @Override
    public List<ExitProcess> retrieveCancelResignationList() {
        List<ExitProcess> leaveList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        criterions.add(Restrictions.isNull(APPROVAL_STATUS));
        leaveList = super.findByCriteriaList(criterions);
        return leaveList;
    }
}
