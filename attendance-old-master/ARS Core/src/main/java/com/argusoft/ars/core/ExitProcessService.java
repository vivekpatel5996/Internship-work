/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.ExitProcess;
import java.util.List;

/**
 *
 * @author smetaliya
 */
public interface ExitProcessService {

    public Long createExitProcess(ExitProcess exitProcess);

    public void updateExitProcess(ExitProcess exitProcess);

    public ExitProcess retrieveExitProcessById(Long exitProcessId);

    public ExitProcess retrieveExitProcessByUserId(Long id);

    public List<ExitProcess> retrievePandingResignation();

    public List<ExitProcess> retrieveNotArchiveResignationList(Long id);

    public List<ExitProcess> retrieveCancelResignationList();
}
