/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.CardInventoryService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.core.VisitorService;
import com.argusoft.ars.model.CardInventory;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.model.Visitor;
import com.argusoft.ars.web.usermanagement.databean.CardInventoryDataBean;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for CardInventory.
 *
 * @author Harshit
 */
@ManagedBean(name = "cardInventoryTransformerBean")
@RequestScoped
public class CardInventoryTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{cardInventoryService}")
    private CardInventoryService cardInventoryService;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{systemUserDetailService}")
    private SystemUserDetailService systemUserDetailService;
    @ManagedProperty(value = "#{visitorService}")
    private VisitorService visitorService;
    //  Other properties
    private static final Logger log = Logger.getLogger(CardInventoryTransformerBean.class);

    public VisitorService getVisitorService() {
        return visitorService;
    }

    public void setVisitorService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    public SystemUserDetailService getSystemUserDetailService() {
        return systemUserDetailService;
    }

    public void setSystemUserDetailService(SystemUserDetailService systemUserDetailService) {
        this.systemUserDetailService = systemUserDetailService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public CardInventoryService getCardInventoryService() {
        return cardInventoryService;
    }

    public void setCardInventoryService(CardInventoryService cardInventoryService) {
        this.cardInventoryService = cardInventoryService;
    }

    /**
     * Method to convert CardInventoryDataBean to CardInventory Model.
     *
     */
    private CardInventory convertCardInventoryDataBeanToCardInventoryModel(CardInventoryDataBean cardInventoryDataBean, CardInventory cardInventory) {
        cardInventory.setCardId(cardInventoryDataBean.getCardId());
        cardInventory.setAssignUserId(cardInventoryDataBean.getAssigneUserId());
        cardInventory.setCardEnrollNo(cardInventoryDataBean.getCardEnrollNo());
        if (cardInventoryDataBean.getAssigneUserId() != null) {
            cardInventory.setIsAssigned(true);
        } else {
            cardInventory.setIsAssigned(false);
        }
        cardInventory.setIsActive(true);
        cardInventory.setIsArchive(false);
        cardInventory.setVendor(cardInventoryDataBean.getVendor());
        return cardInventory;
    }

    /**
     * Method to convert CardInventory Model to CardInventoryDataBean.
     */
    private CardInventoryDataBean convertCardInventoryModelToCardInventoryDataBean(CardInventory cardInventory, CardInventoryDataBean cardInventoryDataBean) throws UserManagementException {
        cardInventoryDataBean.setId(cardInventory.getId());
        cardInventoryDataBean.setAssigneUserId(cardInventory.getAssignUserId());
        cardInventoryDataBean.setCardEnrollNo(cardInventory.getCardEnrollNo());
        cardInventoryDataBean.setCardId(cardInventory.getCardId());
        if (cardInventory.getAssignUserId() != null) {
            User user = userService.getUserbyId(cardInventory.getAssignUserId(), false, false, true, false);
            cardInventoryDataBean.setName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
        }
        if (cardInventory.getVisitorId() != null) {
            Visitor visitor = visitorService.retrieveVisitorById(cardInventory.getVisitorId());
            cardInventoryDataBean.setName("V -" + visitor.getVisitorName());
        }
        cardInventoryDataBean.setReason(cardInventory.getReasonToDelete());
        cardInventoryDataBean.setVendor(cardInventory.getVendor());
        return cardInventoryDataBean;
    }

    /**
     * Method to Retrive all active CardInventory
     */
    public List<CardInventoryDataBean> retrieveAllCardInventory() throws UserManagementException {
        try {
            List<CardInventory> cardInventoryList = cardInventoryService.retrieveAllActiveCardInventory();
            List<CardInventoryDataBean> cardInventoryDataBeansList = new ArrayList<CardInventoryDataBean>();
            for (CardInventory cardInventory : cardInventoryList) {
                CardInventoryDataBean cardInventoryDataBean = convertCardInventoryModelToCardInventoryDataBean(cardInventory, new CardInventoryDataBean());
                cardInventoryDataBeansList.add(cardInventoryDataBean);
            }
            return cardInventoryDataBeansList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method to Retrive CardInventory Detail by CardInventory Id
     */
    public CardInventoryDataBean retrieveCardInventoryDetail(Long id) throws UserManagementException {
        CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(id);
        CardInventoryDataBean cardInventoryDataBean = convertCardInventoryModelToCardInventoryDataBean(cardInventory, new CardInventoryDataBean());
        return cardInventoryDataBean;
    }

    /**
     * Method to Add CardInventory object.
     *
     */
    public String createCardInventory(CardInventoryDataBean cardInventoryDataBean) {
        try {
            if (cardInventoryDataBean.getCardId() == null && cardInventoryDataBean.getAssigneUserId() != null) {
                return "Without card id you can't assign to employee";
            }
            boolean isCardEnrollNoAvailable = cardInventoryService.isCardEnrollNoAvailable(cardInventoryDataBean.getCardEnrollNo());
            boolean isCardIdAvailable = true;
            if (cardInventoryDataBean.getCardId() != null) {
                isCardIdAvailable = cardInventoryService.isCardIdAvailable(cardInventoryDataBean.getCardId());
            }
            if (isCardIdAvailable && isCardEnrollNoAvailable) {
                CardInventory cardInventory = convertCardInventoryDataBeanToCardInventoryModel(cardInventoryDataBean, new CardInventory());
                cardInventory.setCreatedDate(new Date());
                cardInventory.setCreatedBy(loginDataBean.getId());
                Long cardId = cardInventoryService.createCardInventory(cardInventory);
                cardInventory.setId(cardId);
                if (cardInventory.getAssignUserId() != null) {
                    SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(cardInventoryDataBean.getAssigneUserId());
                    systemUserDetail.setCardEnrollNo(cardInventory);
                    systemUserDetailService.updatesystemUserDetail(systemUserDetail);
                }
                return SystemConstantUtil.SUCCESS;
            } else {
                if (!isCardEnrollNoAvailable && !isCardIdAvailable) {
                    return "Card Enrollment and Card Id both are in use currently";
                } else if (!isCardEnrollNoAvailable) {
                    return "Card Enrollment No is already in use";
                } else {
                    return "Card Id is curruntly in use";
                }
            }
        } catch (Exception e) {
            System.out.println("Add CardInventory Catch");
            return e.toString();
        }
    }

    /**
     * Method to Inactivate CardInventory.
     */
    public void deleteCardInventory(CardInventoryDataBean cardInventoryDataBean) {
        try {
            CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(cardInventoryDataBean.getId());
            if (cardInventory != null) {
                cardInventory.setIsArchive(true);
                cardInventory.setIsActive(false);
                cardInventory.setAssignUserId(null);
                cardInventory.setReasonToDelete(cardInventoryDataBean.getReason());
                cardInventoryService.updateCardInventory(cardInventory);
                if (cardInventoryDataBean.getAssigneUserId() != null) {
                    SystemUserDetail retrieveSystemUserDetailById = systemUserDetailService.retrieveSystemUserDetailById(cardInventoryDataBean.getAssigneUserId());
                    retrieveSystemUserDetailById.setCardEnrollNo(null);
                    systemUserDetailService.createsystemUserDetail(retrieveSystemUserDetailById);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public List<SystemUserDetailDataBean> retrieveUserDetailWithoutCard() throws UserManagementException {
        List<SystemUserDetail> systemUserDetailsWithoutCard = systemUserDetailService.retrieveUserDetailWithoutCard();
        List<SystemUserDetailDataBean> systemUserDetailDataBeans = new ArrayList<SystemUserDetailDataBean>();
        if (systemUserDetailsWithoutCard != null) {
            for (SystemUserDetail systemUserDetail : systemUserDetailsWithoutCard) {
                SystemUserDetailDataBean systemUserDetailDataBean = new SystemUserDetailDataBean();
                systemUserDetailDataBean.setUserId(systemUserDetail.getUserId());
                User user = userService.getUserbyId(systemUserDetail.getUserId(), false, false, true, false);
                systemUserDetailDataBean.setName(user.getContact().getFirstName() + " " + user.getContact().getLastName());
                systemUserDetailDataBeans.add(systemUserDetailDataBean);
            }
            return systemUserDetailDataBeans;
        }
        return null;
    }

    /**
     * Method to Update CardInventory Detail
     */
    public String updateCardInventory(CardInventoryDataBean cardInventoryDataBean) {
        try {
            CardInventory cardInventory;
            cardInventory = cardInventoryService.retrieveCardInventoryById(cardInventoryDataBean.getId());
            System.out.println(cardInventoryDataBean.getId());
            if (cardInventory.getIsAssigned() == true && cardInventoryDataBean.getCardId() == null) {
                return "card is curruntly assign to employee so you can't set card id null!!";
            }
            boolean isCardEnrollNoAvailable;
            if (cardInventory.getCardEnrollNo().equals(cardInventoryDataBean.getCardEnrollNo())) {
                isCardEnrollNoAvailable = true;
            } else {
                isCardEnrollNoAvailable = cardInventoryService.isCardEnrollNoAvailable(cardInventoryDataBean.getCardEnrollNo());
            }
            boolean isCardIdAvailable = true;
            if (cardInventoryDataBean.getCardId() != null && cardInventory.getCardId() != cardInventoryDataBean.getCardId()) {
                isCardIdAvailable = cardInventoryService.isCardIdAvailable(cardInventoryDataBean.getCardId());
            }
            if (isCardIdAvailable && isCardEnrollNoAvailable) {
                cardInventory = convertCardInventoryDataBeanToCardInventoryModel(cardInventoryDataBean, new CardInventory());
                cardInventory.setId(cardInventoryDataBean.getId());
                System.out.println(cardInventory.getId());
                cardInventoryService.updateCardInventory(cardInventory);
                return SystemConstantUtil.SUCCESS;
            } else {
                if (!isCardEnrollNoAvailable && !isCardIdAvailable) {
                    return "Card Enrollment and Card Id both are in use currently";
                } else if (!isCardEnrollNoAvailable) {
                    return "Card Enrollment No is already in use";
                } else {
                    return "Card Id is curruntly in use";
                }
            }
        } catch (Exception ex) {
            log.error(ex);
            System.out.println(ex.getStackTrace().toString());
            return ex.toString();
        }
    }

    public String assignCard(Long id, Long assigneUserId) {
        CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(id);
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(assigneUserId);
        if (cardInventory != null && systemUserDetail != null) {
            cardInventory.setAssignUserId(assigneUserId);
            cardInventory.setIsAssigned(true);
            systemUserDetail.setCardEnrollNo(cardInventory);
            cardInventoryService.updateCardInventory(cardInventory);
            systemUserDetailService.updatesystemUserDetail(systemUserDetail);
        }
        return SystemConstantUtil.SUCCESS;
    }

    public List<CardInventoryDataBean> reretrieveUnusedCardList() throws UserManagementException {
        List<CardInventory> unUsedCardInventorysList = cardInventoryService.retrieveUnusedCardInventory();
        if (!unUsedCardInventorysList.isEmpty()) {
            List<CardInventoryDataBean> unUsedCardInventoryDataBeansList = new ArrayList<CardInventoryDataBean>();
            for (CardInventory cardInventory : unUsedCardInventorysList) {
                CardInventoryDataBean cardInventoryDataBean = convertCardInventoryModelToCardInventoryDataBean(cardInventory, new CardInventoryDataBean());
                if (cardInventoryDataBean.getCardId() != null) {
                    unUsedCardInventoryDataBeansList.add(cardInventoryDataBean);
                }
            }
            return unUsedCardInventoryDataBeansList;
        } else {
            return null;
        }
    }

    public void unAssignCard(Long id) {
        CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(id);
        SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(cardInventory.getAssignUserId());
        systemUserDetail.setCardEnrollNo(null);
        systemUserDetailService.updatesystemUserDetail(systemUserDetail);
        cardInventory.setAssignUserId(null);
        cardInventory.setIsAssigned(false);
        cardInventoryService.updateCardInventory(cardInventory);
    }
}
