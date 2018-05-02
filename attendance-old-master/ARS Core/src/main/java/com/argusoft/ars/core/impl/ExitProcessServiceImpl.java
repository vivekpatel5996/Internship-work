/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.ExitProcessService;
import com.argusoft.ars.database.ExitProcessDao;
import com.argusoft.ars.model.ExitProcess;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("exitProcessService")
public class ExitProcessServiceImpl implements ExitProcessService {

    @Autowired
    private ExitProcessDao exitProcessDao;

    @Override
    public Long createExitProcess(ExitProcess exitProcess) {
        return exitProcessDao.create(exitProcess);
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#exitProcess.exitId")
    public void updateExitProcess(ExitProcess exitProcess) {
        exitProcessDao.update(exitProcess);
    }

    @Override
    public ExitProcess retrieveExitProcessById(Long exitProcessId) {
        return exitProcessDao.retrieveById(exitProcessId);
    }

    @Override
    public ExitProcess retrieveExitProcessByUserId(Long id) {
        return exitProcessDao.retrieveExitProcessByUserId(id);
    }

    @Override
    public List<ExitProcess> retrievePandingResignation() {
        return exitProcessDao.retrievePandingResignationList();
    }

    @Override
    public List<ExitProcess> retrieveNotArchiveResignationList(Long id) {
        return exitProcessDao.retrieveNotArchiveResignation(id);
    }

    @Override
    public List<ExitProcess> retrieveCancelResignationList() {
        return exitProcessDao.retrieveCancelResignationList();
    }
}