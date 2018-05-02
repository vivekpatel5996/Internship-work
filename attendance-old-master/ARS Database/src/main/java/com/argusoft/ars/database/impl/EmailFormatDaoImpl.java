/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.EmailFormatDao;
import com.argusoft.ars.model.EmailFormat;
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
public class EmailFormatDaoImpl extends BaseAbstractGenericDao<EmailFormat, Long> implements EmailFormatDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String EMAIL_FORMAT_NAME = "emailFormatName";

    @Override
    public List<EmailFormat> retrieveAllEmailFormat() {
        List<EmailFormat> emailFormatList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        emailFormatList = super.findByCriteriaList(criterions);
        return emailFormatList;
    }

    @Override
    public List<EmailFormat> retrieveEmailFormatByFormateName(String formateName) {
        List<EmailFormat> emailFormatList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.ilike(EMAIL_FORMAT_NAME, formateName + "%"));
        emailFormatList = super.findByCriteriaList(criterions);
        return emailFormatList;
    }
}
