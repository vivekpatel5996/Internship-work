/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.OfficialBreak;
import java.util.Date;
import java.util.List;


/**
 *
 * @author smetaliya
 */
public interface OfficialBreakDao extends GenericDao<OfficialBreak, Long>{

    public List<OfficialBreak> retrieveOfficialBreakByUserId(Long id);

    public List<OfficialBreak> retrieveApplyOfficialBreakBetweenDates(Long id, Date fromDate, Date toDate);

    public List<OfficialBreak> retrieveOfficialBreakBetweenDate(Long id, Date fromDate, Date toDate);

    public List<OfficialBreak> retrieveNotArchiveOfficialBreak(Long id);

    public List<OfficialBreak> retrieveNotificationOfficialBreakList();

    public List<OfficialBreak> retrieveOfficialBreakByUserIdBetweenDates(Long userId, Date fromDate, Date toDate);
}
