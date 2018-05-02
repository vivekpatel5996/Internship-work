/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.Department;
import java.util.List;

/**
 *This Class is used to perform various operations on Department and related entities.
 * @author Harshit
 */
public interface DepartmentService {

    /**
     * createDepartment method creates an Object of Class
     * Department, by calling saveEntity method of its parent.
     * @param department Takes an Object of Class Department,
     * which is to be created.
     */
    void createDepartment(Department department);

    /**
     * updateDepartment method updates an Object of Class
     * Department, by calling updateEntity method of its parent.
     * @param department Takes an Object of Class Department,
     * which is to be updated.
     */
    public void updateDepartment(Department department);

    /**
     * retrieveDepartmentByKey method retrieves the Department
     * Object, by calling getEntityById method of its parent.
     * @param depId Takes PK of the Department
     * @return Returns the Object of Department.
     */
    public Department retrieveDepartmentById(Long depId);

    /**
     * retrieveAllDepartments method retrieves all Department
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<Department> retrieveAllDepartment();

    /**
     * isDepNameAvailable method check Department Name is available or not
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param depName for search criteria.
     * @return Returns the boolean True or False.
     */
    public boolean isDepNameAvailable(String depName);

}