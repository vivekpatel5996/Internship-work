/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.CardInventory;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.List;

/**
 * This class is used to perform CRUD operations on CardInventory Entity.
 *
 * @author Harshit
 */
public interface CardInventoryDao extends GenericDao<CardInventory, Long> {

    /**
     * retrieveAllActiveCardInventory method retrieves all Active CardInventory
     * Detail Objects, by calling findByCriteriaList method of its parent class.
     *
     * @return Returns the List of Object of Class CardInventory.
     */
    public List<CardInventory> retrieveAllActiveCardInventory();

    public CardInventory retrieveByCardId(Long cardId);

    public List<CardInventory> getActiveCardInventorysByUser(SystemUserDetail systemUserDetail);

    public CardInventory retrieveByCardEnrollno(String cardEnrollNo);

    public List<CardInventory> retrieveUnusedCard();
}
