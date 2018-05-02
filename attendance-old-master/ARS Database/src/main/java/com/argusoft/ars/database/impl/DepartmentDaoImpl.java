/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.DepartmentDao;
import com.argusoft.ars.model.Department;
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
public class DepartmentDaoImpl extends BaseAbstractGenericDao<Department, Long> implements DepartmentDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String DEP_NAME = "deptName";
    private static final String DEP_ID = "systemUserDetail";

    @Override
    public List<Department> retrieveAllActiveDepartment() {
        List<Department> departmentList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        departmentList = super.findByCriteriaList(criterions);
        return departmentList;
    }

    @Override
    public Department retrieveActiveDepartmentByName(String depName) {
        List<Department> departmentList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.ilike(DEP_NAME, depName.trim()));
        departmentList = super.findByCriteriaList(criterions);
        System.out.println("==>list size" + departmentList.size());
        if (departmentList.isEmpty()) {
            return null;
        } else {
            return departmentList.get(0);
        }
    }

    @Override
    public List<Department> getActiveDepartmentsByUser(SystemUserDetail systemUserDetail) {
        if (systemUserDetail != null) {
            List<Criterion> criterions = new LinkedList<Criterion>();
            if (systemUserDetail != null) {
                criterions.add(Restrictions.eq(DEP_ID, systemUserDetail));
            }
            return super.findByCriteriaList(criterions);
        }
        return null;
    }
}
