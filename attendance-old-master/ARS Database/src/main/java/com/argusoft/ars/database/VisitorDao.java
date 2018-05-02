/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.Visitor;
import java.util.Date;
import java.util.List;

/**
 *
 * @author harshit
 */
/**
 * This class is used to perform CRUD operations on Visitor Entity.
 *
 * @author Harshit
 */
public interface VisitorDao extends GenericDao<Visitor, Long> {

    /**
     * retrieveAllActiveVisitor method retrieves all Active Visitor Detail
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class Visitor.
     */
    public List<Visitor> retrieveAllActiveVisitor();

    public List<Visitor> retrieveVisitorDetailByCardIdBetweenDates(Long cardId, Date fromDate, Date toDate);
}