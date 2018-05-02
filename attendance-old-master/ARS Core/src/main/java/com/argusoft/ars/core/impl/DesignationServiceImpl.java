/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.DesignationService;
import com.argusoft.ars.database.DesignationDao;
import com.argusoft.ars.model.Designation;
import com.argusoft.ars.model.Designation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Harshit
 */
@Service("designationService")
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationDao designationDao;

    @Override
    public void createDesignation(Designation designation) {
        designationDao.update(designation);
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#designation.desgtId")
    public void updateDesignation(Designation designation) {
        designationDao.update(designation);
    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public Designation retrieveDesignationById(Long desgId) {
        Designation designation = null;
        if (desgId != null) {
            designation = designationDao.retrieveById(desgId);
        }
        return designation;
    }

    @Override
    public List<Designation> retrieveAllDesignation() {
        List<Designation> designationsList = designationDao.retrieveAllActiveDesignation();
        return designationsList;
    }
    
    @Override
    public boolean isDesgNameAvailable(String desgName) {
        try {
            System.out.println("isDesgNameAvailable........");
            Designation designation = designationDao.retrieveActiveDesignationByName(desgName);
            if (designation == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("In Designation Service Error");
            System.out.println(ex);
            return false;
        }

    }
}
