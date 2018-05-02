/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.Designation;
import com.argusoft.ars.model.Designation;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.List;

/**
 * This class is used to perform CRUD operations on Designation Entity.
 *
 * @author Harshit
 */
public interface DesignationDao extends GenericDao<Designation, Long> {

    /**
     * retrieveAllActiveDesignation method retrieves all Active Designation Detail
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class Designation.
     */
    public List<Designation> retrieveAllActiveDesignation();
    
    /**
     * retrieveActiveDesignationByName method retrieve Active Designation Detail by Name.
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the Object of Class Designation.
     */
    public Designation retrieveActiveDesignationByName(String desgName);
    public List<Designation> getActiveDesignationsByUser(SystemUserDetail systemUserDetail);
}