/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.ShiftDao;
import com.argusoft.ars.model.Shift;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Harshit
 */
@Repository
public class ShiftDaoImpl extends BaseAbstractGenericDao<Shift, Long> implements ShiftDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String SHIFT_NAME = "shiftName";
    private static final String SHIFT_ID = "systemUserDetail";

    @Override
    public List<Shift> retrieveAllActiveShift() {
        List<Shift> ShiftList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        ShiftList = super.findByCriteriaList(criterions);
        return ShiftList;
    }

    @Override
    public Shift retrieveActiveShift(String shiftName) {
        System.out.println("shiftName" + shiftName);
        List<Shift> shiftList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(SHIFT_NAME, shiftName));
        shiftList = super.findByCriteriaList(criterions);
        System.out.println("==>list size" + shiftList.size());
        if (shiftList.isEmpty()) {
            return null;
        } else {
//            System.out.println("shift_name return" + shiftList.get(0).getShiftName());
            return shiftList.get(0);
        }
    }

    @Override
    public List<Shift> getActiveShiftsByUser(SystemUserDetail systemUserDetail) {


        List<Shift> shifts = null;

        if (!(systemUserDetail == null)) {
            List<Criterion> criterions = new LinkedList<Criterion>();
            if (systemUserDetail != null) {
                criterions.add(Restrictions.eq(SHIFT_ID, systemUserDetail));
            }

            shifts = super.findByCriteriaList(criterions);
        }

        return shifts;
    }
}
