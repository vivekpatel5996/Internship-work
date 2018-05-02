/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.Designation;
import java.util.List;

/**
 *This Class is used to perform various operations on Designation and related entities.
 * @author Harshit
 */
public interface DesignationService {

    /**
     * createDesignation method creates an Object of Class
     * Designation, by calling saveEntity method of its parent.
     * @param designation Takes an Object of Class Designation,
     * which is to be created.
     */
    void createDesignation(Designation designation);

    /**
     * updateDesignation method updates an Object of Class
     * Designation, by calling updateEntity method of its parent.
     * @param designation Takes an Object of Class Designation,
     * which is to be updated.
     */
    public void updateDesignation(Designation designation);

    /**
     * retrieveDesignationByKey method retrieves the Designation
     * Object, by calling getEntityById method of its parent.
     * @param desgId Takes PK of the Designation
     * @return Returns the Object of Designation.
     */
    public Designation retrieveDesignationById(Long desgId);

    /**
     * retrieveAllDesignations method retrieves all Designation
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<Designation> retrieveAllDesignation();
    
   /**
     * isDesgNameAvailable method check Designation Name is available or not
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param desgName for search criteria.
     * @return Returns the boolean True or False.
     */
    public boolean isDesgNameAvailable(String desgName);
}