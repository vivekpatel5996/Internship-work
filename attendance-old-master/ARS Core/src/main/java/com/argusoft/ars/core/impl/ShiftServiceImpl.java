/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.ShiftService;
import com.argusoft.ars.database.ShiftDao;
import com.argusoft.ars.model.Shift;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("shiftService")
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftDao shiftDao;

    @Override
    public void createShift(Shift shift) {
        shiftDao.update(shift);
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#shift.deptId")
    public void updateShift(Shift shift) {
        shiftDao.update(shift);
    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public Shift retrieveShiftById(Long shiftId) {
        Shift shift = null;
        if (shiftId != null) {
            shift = shiftDao.retrieveById(shiftId);
        }
        return shift;
    }

    @Override
    public List<Shift> retrieveAllShift() {
        List<Shift> shiftsList = shiftDao.retrieveAllActiveShift();
        return shiftsList;
    }

    @Override
    public boolean isShiftNameAvailable(String shiftName) {
        try {
            Shift shift = shiftDao.retrieveActiveShift(shiftName);
            if (shift == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("In Shift Service Error");
            System.out.println(ex);
            return false;
        }

    }
}
