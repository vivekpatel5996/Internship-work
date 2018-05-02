/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.ManualCardEntry;
import java.util.Date;
import java.util.List;

/**
 * This Class is used to perform various operations on ManualCardEntry and
 * related entities.
 *
 * @author Harshit
 */
public interface ManualCardEntryService {

    /**
     * createManualCardEntry method creates an Object of Class ManualCardEntry,
     * by calling saveEntity method of its parent.
     *
     * @param manualCardEntry Takes an Object of Class ManualCardEntry, which is
     * to be created.
     */
    Long applyManualCardEntry(ManualCardEntry manualCardEntry);

    /**
     * updateManualCardEntry method updates an Object of Class ManualCardEntry,
     * by calling updateEntity method of its parent.
     *
     * @param manualCardEntry Takes an Object of Class ManualCardEntry, which is
     * to be updated.
     */
    public void updateManualCardEntry(ManualCardEntry manualCardEntry);

    /**
     * retrieveManualCardEntryByKey method retrieves the ManualCardEntry Object,
     * by calling getEntityById method of its parent.
     *
     * @param manualCardEntryId Takes PK of the ManualCardEntry
     * @return Returns the Object of ManualCardEntry.
     */
    public ManualCardEntry retrieveManualCardEntryById(Long manualCardEntryId);

    /**
     * retrieveAllManualCardEntrys method retrieves all ManualCardEntry Objects,
     * by calling findByCriteriaList method of its parent class.
     *
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<ManualCardEntry> retrieveNotArchiveManualCardEntry(Long id);

    public List<ManualCardEntry> retrieveManualCardEntryByUserId(Long id);

    public List<ManualCardEntry> retrievePandingManualCardEntry();

    public boolean isManualCardEntryAlreadyApply(Long userId, Date date, Boolean inStatus, Boolean outStatus);
}