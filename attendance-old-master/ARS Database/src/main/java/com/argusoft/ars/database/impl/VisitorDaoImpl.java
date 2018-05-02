/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.VisitorDao;
import com.argusoft.ars.model.Visitor;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author harshit
 */
@Repository
public class VisitorDaoImpl extends BaseAbstractGenericDao<Visitor, Long> implements VisitorDao {

    public static final String IS_ARCHIVE = "isArchive";
    public static final String CARD_ID = "cardId";
    public static final String FROM_DATE = "fromDate";
    public static final String TO_DATE = "toDate";

    @Override
    public List<Visitor> retrieveAllActiveVisitor() {
        List<Visitor> visitorList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        visitorList = super.findByCriteriaList(criterions);
        return visitorList;
    }

    @Override
    public List<Visitor> retrieveVisitorDetailByCardIdBetweenDates(Long cardId, Date fromDate, Date toDate) {
        List<Visitor> visitorList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ARCHIVE, false));
        criterions.add(Restrictions.or(Restrictions.or(Restrictions.and(Restrictions.ge(FROM_DATE, fromDate), Restrictions.le(TO_DATE, fromDate)), Restrictions.and(Restrictions.ge(FROM_DATE, toDate), Restrictions.le(TO_DATE, toDate))), Restrictions.or(Restrictions.between(FROM_DATE, fromDate, toDate), Restrictions.between(TO_DATE, fromDate, toDate))));
        criterions.add(Restrictions.eq(CARD_ID, cardId));
        visitorList = super.findByCriteriaList(criterions);
        return visitorList;
    }
}
