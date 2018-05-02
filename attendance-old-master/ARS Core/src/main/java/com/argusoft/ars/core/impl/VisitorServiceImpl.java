/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.VisitorService;
import com.argusoft.ars.database.VisitorDao;
import com.argusoft.ars.model.Visitor;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorDao visitorDao;

    @Override
    public Long createVisitor(Visitor visitor) {
        try {
            return visitorDao.create(visitor);
        } catch (Exception e) {
            System.out.println("Exception in Core createVisitor method");
            System.out.println("Exception:" + e);
        }
        return null;
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#visitor.visitortId")
    public void updateVisitor(Visitor visitor) {
        try {
            visitorDao.update(visitor);
        } catch (Exception ex) {
            System.out.println("Exception in Core UpdateVisitor method");
            System.out.println(ex);
        }

    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public Visitor retrieveVisitorById(Long visitorId) {
        Visitor visitor = null;
        if (visitorId != null) {
            System.out.println("Vis" + visitorId);
            visitor = visitorDao.retrieveById(visitorId);
            System.out.println("Visitor object" + visitor);
        }
        return visitor;
    }

    @Override
    public List<Visitor> retrieveAllVisitor() {
        List<Visitor> visitorsList = visitorDao.retrieveAllActiveVisitor();
        return visitorsList;
    }

    @Override
    public Boolean isCardIdAlreadyUsedBeetweenThisDate(Long cardId, Date fromDate, Date toDate) {
        List<Visitor> visitorsList = visitorDao.retrieveVisitorDetailByCardIdBetweenDates(cardId, fromDate, toDate);
        System.out.println(visitorsList.size());
        if (visitorsList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
