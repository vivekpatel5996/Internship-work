/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.ExitProcess;
import java.util.List;


/**
 *
 * @author smetaliya
 */
public interface ExitProcessDao extends GenericDao<ExitProcess, Long>{

    public ExitProcess retrieveExitProcessByUserId(Long id);

    public List<ExitProcess> retrievePandingResignationList();
    
    public List<ExitProcess> retrieveCancelResignationNotification();

    public List<ExitProcess> retrieveNotArchiveResignation(Long id);

    public List<ExitProcess> retrieveCancelResignationList();
}
