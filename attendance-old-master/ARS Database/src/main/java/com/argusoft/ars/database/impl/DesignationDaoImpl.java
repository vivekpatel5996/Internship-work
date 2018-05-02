/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.DesignationDao;
import com.argusoft.ars.model.Designation;
import com.argusoft.ars.model.Designation;
import com.argusoft.ars.model.Designation;
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
public class DesignationDaoImpl extends BaseAbstractGenericDao<Designation, Long> implements DesignationDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String DESG_NAME = "desName";
    private static final String DESG_ID = "systemUserDetail";

    @Override
    public List<Designation> retrieveAllActiveDesignation() {
        List<Designation> designationList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        designationList = super.findByCriteriaList(criterions);
        return designationList;
    }

    @Override
    public Designation retrieveActiveDesignationByName(String desgName) {
        try {
            System.out.println("desgName" + desgName);
            List<Designation> designationList;
            List<Criterion> criterions = new LinkedList<Criterion>();
            criterions.add(Restrictions.eq(IS_ACTIVE, true));
            criterions.add(Restrictions.eq(DESG_NAME, desgName));
            designationList = super.findByCriteriaList(criterions);
            System.out.println("==>list size" + designationList.size());
            if (designationList.isEmpty()) {
                System.out.println("null");
                return null;
            } else {
                System.out.println("desg_name return" + designationList.get(0).getDesName());
                return designationList.get(0);
            }
        } catch (Exception ex) {
            System.out.println("In designationDao retrieveActiveDesignationByName method error");
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<Designation> getActiveDesignationsByUser(SystemUserDetail systemUserDetail) {


        List<Designation> designations = null;

        if (!(systemUserDetail == null)) {
            List<Criterion> criterions = new LinkedList<Criterion>();
            if (systemUserDetail != null) {
                criterions.add(Restrictions.eq(DESG_ID, systemUserDetail));
            }

            designations = super.findByCriteriaList(criterions);
        }

        return designations;
    }
}
