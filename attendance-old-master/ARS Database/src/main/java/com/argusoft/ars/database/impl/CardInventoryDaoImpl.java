/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.CardInventoryDao;
import com.argusoft.ars.model.CardInventory;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Harshit
 */
@Repository
public class CardInventoryDaoImpl extends BaseAbstractGenericDao<CardInventory, Long> implements CardInventoryDao {

    private static final String IS_ACTIVE = "isActive";
    private static final String CARD_ID = "cardId";
    private static final String CARD_ENROLL_NO = "cardEnrollNo";
    private static final String IS_ASSIGNED = "isAssigned";

    @Override
    public List<CardInventory> retrieveAllActiveCardInventory() {
        List<CardInventory> cardInventoryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        cardInventoryList = super.findByCriteriaList(criterions);
        return cardInventoryList;
    }

    @Override
    public CardInventory retrieveByCardId(Long cardId) {
        List<CardInventory> cardInventoryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(CARD_ID, cardId));
        cardInventoryList = super.findByCriteriaList(criterions);
        if (cardInventoryList.isEmpty()) {
            return null;
        }
        return cardInventoryList.get(0);
    }

    @Override
    public List<CardInventory> getActiveCardInventorysByUser(SystemUserDetail systemUserDetail) {
        List<CardInventory> cardInventorys = null;
        if (!(systemUserDetail == null)) {
            List<Criterion> criterions = new LinkedList<Criterion>();
            if (systemUserDetail != null) {
                criterions.add(Restrictions.eq("systemUserDetail", systemUserDetail));
            }
            cardInventorys = super.findByCriteriaList(criterions);
        }
        return cardInventorys;
    }

    @Override
    public CardInventory retrieveByCardEnrollno(String cardEnrollNo) {
        List<CardInventory> cardInventoryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.ilike(CARD_ENROLL_NO, cardEnrollNo));
        cardInventoryList = super.findByCriteriaList(criterions);
        if (cardInventoryList.isEmpty()) {
            return null;
        }
        return cardInventoryList.get(0);
    }

    @Override
    public List<CardInventory> retrieveUnusedCard() {
        List<CardInventory> cardInventoryList;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(IS_ACTIVE, true));
        criterions.add(Restrictions.eq(IS_ASSIGNED, false));
        cardInventoryList = super.findByCriteriaList(criterions);
        return cardInventoryList;
    }
}
