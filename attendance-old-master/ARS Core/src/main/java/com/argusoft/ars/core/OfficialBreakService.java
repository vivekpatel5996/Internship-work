/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.OfficialBreak;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author smetaliya
 */
public interface OfficialBreakService {

    public void createOfficialBreak(OfficialBreak officialBreak);

    public void updateOfficialBreak(OfficialBreak officialBreak);

    public OfficialBreak retrieveOfficialBreakByKey(Long key);

    public Map<String, String> retrieveAllOfficialBreak();

    public List<OfficialBreak> retrieveOfficialBreakBasedOnLikeKeySearch(String startsWithString);

    public List<OfficialBreak> retrieveOfficialBreakByUserId(Long id);

    public OfficialBreak retrieveOfficialBreakById(Long officialBreakId);

    public boolean isOfficialBreakAlreadyApply(Long id, Date fromDate, Date toDate);

    public List<OfficialBreak> retrieveOfficialBreaksBetweenDate(Long id, Date fromDate, Date toDate, Object object);

    public List<OfficialBreak> retrieveNotArchiveOfficialBreak(Long id);

    public List<OfficialBreak> retrievePendingOfficialBreak(Long id);

    public List<OfficialBreak> retrieveOfficialBreakByUserId(Long userId, Date fromDate, Date toDate);
}
