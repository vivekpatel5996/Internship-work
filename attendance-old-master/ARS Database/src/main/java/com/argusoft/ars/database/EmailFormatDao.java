/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.EmailFormat;
import java.util.List;


/**
 *
 * @author smetaliya
 */
public interface EmailFormatDao extends GenericDao<EmailFormat, Long>{
    
    public List<EmailFormat> retrieveAllEmailFormat();

    public List<EmailFormat> retrieveEmailFormatByFormateName(String formateName);
}