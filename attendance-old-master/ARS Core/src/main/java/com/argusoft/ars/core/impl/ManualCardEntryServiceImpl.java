/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.ManualCardEntryService;
import com.argusoft.ars.database.ManualCardEntryDao;
import com.argusoft.ars.model.ManualCardEntry;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("manualCardEntryService")
public class ManualCardEntryServiceImpl implements ManualCardEntryService {

    @Autowired
    private ManualCardEntryDao manualCardEntryDao;

    @Override
    public Long applyManualCardEntry(ManualCardEntry manualCardEntry) {
        return manualCardEntryDao.create(manualCardEntry);
    }

    @Override
    public void updateManualCardEntry(ManualCardEntry manualCardEntry) {
        manualCardEntryDao.update(manualCardEntry);
    }

    @Override
    public ManualCardEntry retrieveManualCardEntryById(Long manualCardEntryId) {
        ManualCardEntry manualCardEntry = null;
        if (manualCardEntryId != null) {
            manualCardEntry = manualCardEntryDao.retrieveById(manualCardEntryId);
        }
        return manualCardEntry;
    }

    @Override
    public List<ManualCardEntry> retrieveNotArchiveManualCardEntry(Long id) {
        return manualCardEntryDao.retrieveNotArchiveManualCardEntry(id);

    }

    @Override
    public List<ManualCardEntry> retrieveManualCardEntryByUserId(Long id) {
        return manualCardEntryDao.retrieveManualCardEntryByUserId(id);
    }

    @Override
    public List<ManualCardEntry> retrievePandingManualCardEntry() {
        return manualCardEntryDao.retrieveNotificationManualCardEntryList();
    }

    @Override
    public boolean isManualCardEntryAlreadyApply(Long userId, Date date, Boolean inStatus, Boolean outStatus) {
        if (inStatus) {
            System.out.println("-------> in Status");
            List<ManualCardEntry> manualCardEntrys = manualCardEntryDao.retrieveApplyManualCardEntryBetweenDates(userId, date, true, null);
            if (manualCardEntrys != null && manualCardEntrys.size() > 0) {
                System.out.println("------> in Status True");
                return true;
            }
        }
        if (outStatus) {
            System.out.println("-------> out Status");
            List<ManualCardEntry> manualCardEntrys = manualCardEntryDao.retrieveApplyManualCardEntryBetweenDates(userId, date, null, true);
            if (manualCardEntrys != null && manualCardEntrys.size() > 0) {
                System.out.println("-------> out Status true");
                return true;
            }
        }
        System.out.println("----------> out Side");
        return false;
    }
}
