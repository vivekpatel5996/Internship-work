/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.CardInventory;
import java.util.List;

/**
 *This Class is used to perform various operations on CardInventory and related entities.
 * @author Harshit
 */
public interface CardInventoryService {

    /**
     * createCardInventory method creates an Object of Class
     * CardInventory, by calling saveEntity method of its parent.
     * @param cardInventory Takes an Object of Class CardInventory,
     * which is to be created.
     */
    Long createCardInventory(CardInventory cardInventory);

    /**
     * updateCardInventory method updates an Object of Class
     * CardInventory, by calling updateEntity method of its parent.
     * @param cardInventory Takes an Object of Class CardInventory,
     * which is to be updated.
     */
    public void updateCardInventory(CardInventory cardInventory);

    /**
     * retrieveCardInventoryByKey method retrieves the CardInventory
     * Object, by calling getEntityById method of its parent.
     * @param cardInventoryId Takes PK of the CardInventory
     * @return Returns the Object of CardInventory.
     */
    public CardInventory retrieveCardInventoryById(Long cardEnrollNo);

    /**
     * retrieveAllCardInventorys method retrieves all CardInventory
     * Objects, by calling findByCriteriaList method of its parent class.
     * @param isActive isActive for search criteria.
     * @return Returns the Map of Key Value pairs.
     */
    public List<CardInventory> retrieveAllActiveCardInventory();

    public boolean isCardEnrollNoAvailable(String cardEnrollNo);
    
    public boolean isCardIdAvailable(Long cardId);

    public List<CardInventory> retrieveUnusedCardInventory();

    
}