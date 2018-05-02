/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.ManualCardEntryDao;
import com.argusoft.ars.model.ManualCardEntry;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Harshit
 */
@Repository
public class ManualCardEntryDaoImpl extends BaseAbstractGenericDao<ManualCardEntry, Long> implements ManualCardEntryDao {

    private static final String IS_ARCHIVE = "isArchive";
    private static final String ID = "userId";
    private static final String DATE = "date";
    private static final String LEAVE_TYPE = "manualCardEntryType";
    private static final String APPROVAL_STATUS = "approvalStatus";
    private static final String IS_NOTIFICATION_SHOW = "isNotificationShow";
    private static final String IN_STATUS = "inStatus";
    private static final String OUT_STATUS = "outStatus";

    @Override
    public List<ManualCardEntry> retrieveNotArchiveManualCardEntry(Long id) {

        List<ManualCardEntry> manualCardEntryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        criterions.add(Restrictions.or(Restrictions.eq(APPROVAL_STATUS, "Approve"), Restrictions.eq(APPROVAL_STATUS, "Disapprove")));
        manualCardEntryList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + manualCardEntryList.size());
        return manualCardEntryList;
    }

    @Override
    public List<ManualCardEntry> retrieveManualCardEntryByUserId(Long id) {
        System.out.println("Id:" + id);
        List<ManualCardEntry> manualCardEntryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        manualCardEntryList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + manualCardEntryList.size());
        return manualCardEntryList;
    }

    @Override
    public List<ManualCardEntry> retrieveNotificationManualCardEntryList() {
        List<ManualCardEntry> manualCardEntryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        manualCardEntryList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + manualCardEntryList.size());
        return manualCardEntryList;
    }

    @Override
    public List<ManualCardEntry> retrieveManualCardEntryTypeBetweenDate(Long id, Date fromDate, Date toDate, String manualCardEntryType) {
        List<ManualCardEntry> manualCardEntryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
//        criterions.add(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(FROM_DATE, toDate)), Restrictions.and(Restrictions.ge(TO_DATE, fromDate), Restrictions.le(TO_DATE, toDate))));
        criterions.add(Restrictions.and(Restrictions.ge(DATE, fromDate), Restrictions.le(DATE, toDate)));
        criterions.add(Restrictions.eq(LEAVE_TYPE, manualCardEntryType));
        criterions.add(Restrictions.eq(APPROVAL_STATUS, "Approve"));
        manualCardEntryList = super.findByCriteriaList(criterions);
        return manualCardEntryList;
    }

    @Override
    public List<ManualCardEntry> retrieveApplyManualCardEntryBetweenDates(Long userId, Date date, Boolean inStatus, Boolean outStatus) {
        List<ManualCardEntry> manualCardEntryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        if (userId != null) {
            criterions.add(Restrictions.eq(ID, userId));
        }
        if (date != null) {
            criterions.add(Restrictions.eq(DATE, date));
        }
        if (inStatus != null) {
            criterions.add(Restrictions.eq(IN_STATUS, inStatus));
        }
        if (outStatus != null) {
            criterions.add(Restrictions.eq(OUT_STATUS, outStatus));
        }
        manualCardEntryList = super.findByCriteriaList(criterions);
        return manualCardEntryList;
    }
}
