/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.Department;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.List;

/**
 * This class is used to perform CRUD operations on Department Entity.
 *
 * @author Harshit
 */
public interface DepartmentDao extends GenericDao<Department, Long> {

    /**
     * retrieveAllActiveDepartment method retrieves all Active Department Detail
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class Department.
     */
    public List<Department> retrieveAllActiveDepartment();
    
    /**
     * retrieveActiveDepartmentByName method retrieve Active Department Detail by Name.
     * Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the Object of Class Department.
     */
    public Department retrieveActiveDepartmentByName(String depName);
    
   
    public List<Department> getActiveDepartmentsByUser(SystemUserDetail systemUserDetail);

    

}