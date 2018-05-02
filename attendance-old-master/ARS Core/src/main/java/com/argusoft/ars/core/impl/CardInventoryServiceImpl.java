/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.CardInventoryService;
import com.argusoft.ars.database.CardInventoryDao;
import com.argusoft.ars.database.VisitorDao;
import com.argusoft.ars.model.CardInventory;
import com.argusoft.ars.model.Visitor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("cardInventoryService")
public class CardInventoryServiceImpl implements CardInventoryService {

    @Autowired
    private CardInventoryDao cardInventoryDao;
    @Autowired
    private VisitorDao visitorDao;

    @Override
    public Long createCardInventory(CardInventory cardInventory) {
        try {
            return cardInventoryDao.create(cardInventory);
        } catch (Exception e) {
            System.out.println("Exception in Core createCardInventory method");
            System.out.println("Exception:" + e);
            return null;
        }
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#cardInventory.cardInventorytId")
    public void updateCardInventory(CardInventory cardInventory) {
        cardInventoryDao.update(cardInventory);
    }

    @Override
    @Cacheable(value = "systemCache", key = "#key")
    public CardInventory retrieveCardInventoryById(Long cardInventoryId) {
        CardInventory cardInventory = null;
        if (cardInventoryId != null) {
            cardInventory = cardInventoryDao.retrieveById(cardInventoryId);
        }
        return cardInventory;
    }

    @Override
    public List<CardInventory> retrieveAllActiveCardInventory() {
//        List<CardInventory> cardInventoryList =
        return cardInventoryDao.retrieveAllActiveCardInventory();
//        if (!cardInventoryList.isEmpty()) {
//            List<CardInventory> cardInventorys = new ArrayList<CardInventory>();
//            for (CardInventory cardInventory : cardInventoryList) {
//                if (cardInventory.getVisitorId() != null) {
//                    Visitor visitor = cardUsedByVisitor(cardInventory.getVisitorId());
//                    if (visitor == null) {
//                        cardInventory.setVisitorId(null);
//                        updateCardInventory(cardInventory);
//                    }
//
//                }
//                cardInventorys.add(cardInventory);
//            }
//            return cardInventorys;
//        } else {
//            return null;
//        }
    }

    @Override
    public boolean isCardEnrollNoAvailable(String cardEnrollNo) {
        CardInventory cardInventory = cardInventoryDao.retrieveByCardEnrollno(cardEnrollNo);
        if (cardInventory == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCardIdAvailable(Long cardId) {
        CardInventory cardInventory = cardInventoryDao.retrieveByCardId(cardId);
        if (cardInventory == null) {
            return true;
        }
        return false;
    }

    @Override
    public List<CardInventory> retrieveUnusedCardInventory() {
//        List<CardInventory> unUsedCardInventorys = 
        return cardInventoryDao.retrieveUnusedCard();
//        if (!unUsedCardInventorys.isEmpty()) {
//            List<CardInventory> cardInventorys = new ArrayList<CardInventory>();
//            for (CardInventory cardInventory : unUsedCardInventorys) {
//                if (cardInventory.getVisitorId() != null) {
//                    Visitor visitor = cardUsedByVisitor(cardInventory.getVisitorId());
//                    if (visitor == null) {
//                        cardInventory.setVisitorId(null);
//                        cardInventorys.add(cardInventory);
//                        updateCardInventory(cardInventory);
//                    }
//                } else {
//                    cardInventorys.add(cardInventory);
//                }
//            }
//
//            return cardInventorys;
//        } else {
//            return null;
//        }
    }

    private Visitor cardUsedByVisitor(Long visitorId) {
        Visitor visitor = visitorDao.retrieveById(visitorId);
        if (visitor.getIsArchive() == false) {
            if (visitor.getVisitEndDate() != null) {
                return null;
            } else {
                return visitor;
            }
        }
        return null;
    }
//    private Integer isBeetweenDate(Date fromDate, Date toDate) {
//        System.out.println("FromDate:" + fromDate);
//        System.out.println("Todate:" + toDate);
//        Date curuntDate = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0);
//        if ((curuntDate.after(fromDate) && curuntDate.before(toDate)) || curuntDate.equals(fromDate) || curuntDate.equals(toDate)) {
//            return 0;
//        }
//        if (toDate.after(curuntDate)) {
//            return 1;
//        } else {
//            return 2;
//        }
//    }
}
