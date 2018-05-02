/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.DepartmentService;
import com.argusoft.ars.database.DepartmentDao;
import com.argusoft.ars.model.Department;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public void createDepartment(Department department) {
        departmentDao.update(department);
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#department.deptId")
    public void updateDepartment(Department department) {
        departmentDao.update(department);
    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public Department retrieveDepartmentById(Long depId) {
        Department department = null;
        if (depId != null) {
            department = departmentDao.retrieveById(depId);
        }
        return department;
    }

    @Override
    public List<Department> retrieveAllDepartment() {
        List<Department> departmentsList = departmentDao.retrieveAllActiveDepartment();
        return departmentsList;
    }

    @Override
    public boolean isDepNameAvailable(String depName) {
        Department department = departmentDao.retrieveActiveDepartmentByName(depName);
        if (department == null) {
            return true;
        } else {
            return false;
        }
    }
}
