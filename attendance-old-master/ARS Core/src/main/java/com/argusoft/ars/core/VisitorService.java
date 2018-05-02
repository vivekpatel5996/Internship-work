/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.Visitor;
import java.util.Date;
import java.util.List;

/**
 *This Class is used to perform various operations on Visitor and related entities.
 * @author Harshit
 */
public interface VisitorService {

    /**
     * createVisitor method creates an Object of Class
     * Visitor, by calling saveEntity method of its parent.
     * @param visitor Takes an Object of Class Visitor,
     * which is to be created.
     */
    public Long createVisitor(Visitor visitor);

    /**
     * updateVisitor method updates an Object of Class
     * Visitor, by calling updateEntity method of its parent.
     * @param visitor Takes an Object of Class Visitor,
     * which is to be updated.
     */
    public void updateVisitor(Visitor visitor);

    /**
     * retrieveVisitorByKey method retrieves the Visitor
     * Object, by calling getEntityById method of its parent.
     * @param visitorId Takes PK of the Visitor
     * @return Returns the Object of Visitor.
     */
    public Visitor retrieveVisitorById(Long visitorId);

    /**
     * retrieveAllVisitors method retrieves all Visitor
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<Visitor> retrieveAllVisitor();

    public Boolean isCardIdAlreadyUsedBeetweenThisDate(Long cardId, Date fromDate, Date toDate);


    
}