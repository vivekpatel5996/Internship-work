/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.Shift;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.List;

/**
 * This class is used to perform CRUD operations on Shift Entity.
 *
 * @author Harshit
 */
public interface ShiftDao extends GenericDao<Shift, Long> {

    /**
     * retrieveAllActiveShift method retrieves all Active Shift Detail Objects,
     * by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class Shift.
     */
    public List<Shift> retrieveAllActiveShift();

    public Shift retrieveActiveShift(String shiftName);
    public List<Shift> getActiveShiftsByUser(SystemUserDetail systemUserDetail);
}