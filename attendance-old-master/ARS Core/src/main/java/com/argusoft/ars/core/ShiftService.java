/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.Shift;
import java.util.List;

/**
 *This Class is used to perform various operations on Shift and related entities.
 * @author Harshit
 */
public interface ShiftService {

    /**
     * createShift method creates an Object of Class
     * Shift, by calling saveEntity method of its parent.
     * @param shift Takes an Object of Class Shift,
     * which is to be created.
     */
    void createShift(Shift shift);

    /**
     * updateShift method updates an Object of Class
     * Shift, by calling updateEntity method of its parent.
     * @param shift Takes an Object of Class Shift,
     * which is to be updated.
     */
    public void updateShift(Shift shift);

    /**
     * retrieveShiftByKey method retrieves the Shift
     * Object, by calling getEntityById method of its parent.
     * @param shiftId Takes PK of the Shift
     * @return Returns the Object of Shift.
     */
    public Shift retrieveShiftById(Long shiftId);

    /**
     * retrieveAllShifts method retrieves all Shift
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<Shift> retrieveAllShift();
    public boolean isShiftNameAvailable(String shiftName);
}