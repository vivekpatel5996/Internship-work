/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.OfficialBreakService;
import com.argusoft.ars.database.OfficialBreakDao;
import com.argusoft.ars.model.OfficialBreak;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("officialBreakService")
public class OfficialBreakServiceImpl implements OfficialBreakService{

    @Autowired
    private OfficialBreakDao officialBreakDao;
      
    @Override
    public void createOfficialBreak(OfficialBreak officialBreak) {
         System.out.println("in create officialBreak method-------------------------------------- ");
        officialBreakDao.update(officialBreak);
    }

    @Override
    public void updateOfficialBreak(OfficialBreak officialBreak) {
        officialBreakDao.update(officialBreak);
        
    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public OfficialBreak retrieveOfficialBreakByKey(Long key) {
        OfficialBreak officialBreak = null;
        if (key != null) {
            officialBreak = officialBreakDao.retrieveById(key);
        }
        return officialBreak;
    }

    @Override
    public Map<String, String> retrieveAllOfficialBreak() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OfficialBreak> retrieveOfficialBreakBasedOnLikeKeySearch(String startsWithString) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OfficialBreak> retrieveOfficialBreakByUserId(Long id) {
       return officialBreakDao.retrieveOfficialBreakByUserId(id);
    }

    @Override
    public OfficialBreak retrieveOfficialBreakById(Long officialBreakId) {
         OfficialBreak officialBreak = null;
        if (officialBreakId != null) {
            officialBreak = officialBreakDao.retrieveById(officialBreakId);
        }
        return officialBreak;
    }

    @Override
    public boolean isOfficialBreakAlreadyApply(Long id, Date fromDate, Date toDate) {
        List<OfficialBreak> officialBreaks = officialBreakDao.retrieveApplyOfficialBreakBetweenDates(id, fromDate, toDate);
        System.out.println("officialBreaks------fd-----------"+officialBreaks);
        System.out.println("officialBreaks------vcb-------size"+officialBreaks.size());
        if (officialBreaks.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public List<OfficialBreak> retrieveOfficialBreaksBetweenDate(Long id, Date fromDate, Date toDate, Object object) {
        return officialBreakDao.retrieveOfficialBreakBetweenDate(id, fromDate, toDate);
    }

    @Override
    public List<OfficialBreak> retrieveNotArchiveOfficialBreak(Long id) {
        return officialBreakDao.retrieveNotArchiveOfficialBreak(id);
    }

    @Override
    public List<OfficialBreak> retrievePendingOfficialBreak(Long id) {
         return officialBreakDao.retrieveNotificationOfficialBreakList();
    }

    @Override
    public List<OfficialBreak> retrieveOfficialBreakByUserId(Long userId, Date fromDate, Date toDate) {
        return officialBreakDao.retrieveOfficialBreakByUserIdBetweenDates(userId,fromDate,toDate);
    }
    
}