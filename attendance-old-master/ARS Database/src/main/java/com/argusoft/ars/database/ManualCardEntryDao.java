/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.ManualCardEntry;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to perform CRUD operations on ManualCardEntry Entity.
 *
 * @author Harshit
 */
public interface ManualCardEntryDao extends GenericDao<ManualCardEntry, Long> {

    /**
     * retrieveAllActiveManualCardEntry method retrieves all Active
     * ManualCardEntry Detail Objects, by calling findByCriteriaList method of
     * its parent class.
     *
     * @return Returns the List of Object of Class ManualCardEntry.
     */
    public List<ManualCardEntry> retrieveNotArchiveManualCardEntry(Long id);

    /**
     * retrieveActiveManualCardEntryByName method retrieve Active
     * ManualCardEntry Detail by Name. Objects, by calling findByCriteriaList
     * method of its parent class.
     *
     * @return Returns the Object of Class ManualCardEntry.
     */
    public List<ManualCardEntry> retrieveManualCardEntryByUserId(Long id);

    public List<ManualCardEntry> retrieveNotificationManualCardEntryList();

    public List<ManualCardEntry> retrieveManualCardEntryTypeBetweenDate(Long id, Date fromDate, Date toDate, String manualCardEntryType);

    public List<ManualCardEntry> retrieveApplyManualCardEntryBetweenDates(Long userId, Date date, Boolean inStatus, Boolean outStatus);
}