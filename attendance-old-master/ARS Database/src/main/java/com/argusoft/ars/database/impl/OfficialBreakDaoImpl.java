/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.OfficialBreakDao;
import com.argusoft.ars.model.OfficialBreak;
import java.util.Calendar;
import java.util.Date;
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
public class OfficialBreakDaoImpl extends BaseAbstractGenericDao<OfficialBreak, Long> implements OfficialBreakDao {

    private static final String ID = "userId";
    private static final String IS_ARCHIVE = "isArchive";
    //private static final String APPLIED_STATUS = "appliedStatus";
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";
    private static final String APPROVAL_STATUS = "approvalStatus";
    private static final String IS_NOTIFICATION_SHOW = "isNotificationShow";

    /**
     * ***
     *
     * @param id
     * @return
     */
    @Override
    public List<OfficialBreak> retrieveOfficialBreakByUserId(Long id) {
        System.out.println("Id:" + id);
        List<OfficialBreak> officialBreakList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        officialBreakList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + officialBreakList.size());
        return officialBreakList;
    }

    /**
     * ****
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<OfficialBreak> retrieveApplyOfficialBreakBetweenDates(Long id, Date fromDate, Date toDate) {
        List<OfficialBreak> officialBreakList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        fromDate = setFromDate(fromDate);
        toDate = setToDate(toDate);
        criterions.add(Restrictions.eq(ID, id));
        //criterions.add(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        criterions.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.and(Restrictions.lt(FROM_DATE, fromDate), Restrictions.gt(TO_DATE, fromDate)), Restrictions.and(Restrictions.lt(FROM_DATE, toDate), Restrictions.gt(TO_DATE, toDate)))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        officialBreakList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + officialBreakList.size());
        return officialBreakList;
    }

    /**
     * ****
     *
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<OfficialBreak> retrieveOfficialBreakBetweenDate(Long id, Date fromDate, Date toDate) {
        List<OfficialBreak> officialBreakList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        if (id != null) {
            criterions.add(Restrictions.eq(ID, id));
        }
        //criterions.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        criterions.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.and(Restrictions.lt(FROM_DATE, fromDate), Restrictions.gt(TO_DATE, fromDate)), Restrictions.and(Restrictions.lt(FROM_DATE, toDate), Restrictions.gt(TO_DATE, toDate)))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        officialBreakList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + officialBreakList.size());
        return officialBreakList;
    }

    /**
     * ***
     *
     * @param id
     * @return
     */
    @Override
    public List<OfficialBreak> retrieveNotArchiveOfficialBreak(Long id) {
        List<OfficialBreak> officialBreakList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, id));
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        officialBreakList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + officialBreakList.size());
        return officialBreakList;
    }

    /**
     * ***
     *
     * @return
     */
    @Override
    public List<OfficialBreak> retrieveNotificationOfficialBreakList() {
        List<OfficialBreak> officialBreakList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_NOTIFICATION_SHOW, true));
        officialBreakList = super.findByCriteriaList(criterions);
        System.out.println("List Size" + officialBreakList.size());
        return officialBreakList;

    }

    /**
     * **
     *
     * @param userId
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<OfficialBreak> retrieveOfficialBreakByUserIdBetweenDates(Long userId, Date fromDate, Date toDate) {
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(ID, userId));
        criterions.add(Restrictions.ge(FROM_DATE, fromDate));
        criterions.add(Restrictions.le(TO_DATE, toDate));
        criterions.add(Restrictions.eq(APPROVAL_STATUS, "Approve"));
        return super.findByCriteriaList(criterions);
    }

    /**
     * ********
     *
     * @param fromDate
     * @return
     */
    private Date setFromDate(Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        fromDate = cal.getTime();
        return fromDate;
    }

    /**
     * ********
     *
     * @param toDate
     * @return
     */
    private Date setToDate(Date toDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(toDate);
        cal1.set(Calendar.HOUR_OF_DAY, 23);
        cal1.set(Calendar.MINUTE, 59);
        cal1.set(Calendar.SECOND, 59);
        cal1.set(Calendar.MILLISECOND, 00);
        toDate = cal1.getTime();
        return toDate;

    }
}
