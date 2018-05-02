/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.CardInventoryDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.CardInventoryTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Harshit ServiceBean for CardInventory
 */
@ManagedBean(name = "cardInventoryServiceBean")
@RequestScoped
public class CardInventoryServiceBean {

    @ManagedProperty(value = "#{cardInventoryDataBean}")
    private CardInventoryDataBean cardInventoryDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{cardInventoryTransformerBean}")
    private CardInventoryTransformerBean cardInventoryTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    private Long id;
    private List<SystemUserDetailDataBean> systemUserDetail;

    public List<SystemUserDetailDataBean> getSystemUserDetail() {
        return systemUserDetail;
    }

    public void setSystemUserDetail(List<SystemUserDetailDataBean> systemUserDetail) {
        this.systemUserDetail = systemUserDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardInventoryDataBean getCardInventoryDataBean() {
        return cardInventoryDataBean;
    }

    public void setCardInventoryDataBean(CardInventoryDataBean cardInventoryDataBean) {
        this.cardInventoryDataBean = cardInventoryDataBean;
    }

    public CardInventoryTransformerBean getCardInventoryTransformerBean() {
        return cardInventoryTransformerBean;
    }

    public void setCardInventoryTransformerBean(CardInventoryTransformerBean cardInventoryTransformerBean) {
        this.cardInventoryTransformerBean = cardInventoryTransformerBean;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    /*
     * Method for Retrive CardInventory List for dataTable
     */
    public void retrieveCardInventoryList() {
        try {
            systemResultViewUtil.setCardInventoryDataBeansList(cardInventoryTransformerBean.retrieveAllCardInventory());
            System.out.println("Retrive CardInventory Successfully");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error in Retrive CardInventory");
        }
    }

    public void retrieveUserDetailWithoutCard() throws UserManagementException {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList1(cardInventoryTransformerBean.retrieveUserDetailWithoutCard());
            if (systemResultViewUtil.getSystemUserDetailDataBeansList1() == null) {
                messageDataBean.setIsSuccess(false);
                messageDataBean.setMessage("Every employee has card");
            } else {
                messageDataBean.setIsSuccess(true);
            }
//            cardInventoryDataBean.setId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
     * Method for get cardInventory detail.
     */

    public void fillCardInventoryDetail() throws UserManagementException {
       
        CardInventoryDataBean cardInventory = cardInventoryTransformerBean.retrieveCardInventoryDetail(id);
        cardInventoryDataBean.setId(cardInventory.getId());
        cardInventoryDataBean.setAssigneUserId(cardInventory.getAssigneUserId());
        cardInventoryDataBean.setCardEnrollNo(cardInventory.getCardEnrollNo());
        cardInventoryDataBean.setCardId(cardInventory.getCardId());
        cardInventoryDataBean.setName(cardInventory.getName());
        cardInventoryDataBean.setReason(cardInventory.getReason());
        cardInventoryDataBean.setVendor(cardInventory.getVendor());
        cardInventoryDataBean.setTempCardEnrollNo(id);

    }
    /*
     * Method for Add CardInventory
     */

    public void createCardInventory() {
        try {
            if (cardInventoryDataBean != null) {
                String response = cardInventoryTransformerBean.createCardInventory(cardInventoryDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    System.out.println("Create CardInventory Successfully");
                    messageDataBean.setMessage("CardInventory added successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    cardInventoryDataBean.setNull();
                    this.retrieveCardInventoryList();
                } else {
                    System.out.println("CardInventory not created");
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
            System.out.println(e);
        }
    }

    /*
     * Method for Update CardInventory
     */
    public void updateCardInventory() {
        try {
            if (cardInventoryDataBean != null) {
                String response = cardInventoryTransformerBean.updateCardInventory(cardInventoryDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("CardInventory updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    retrieveCardInventoryList();
                    cardInventoryDataBean.setNull();
                } else {
                    System.out.println("CardInventory not created");
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error =>" + e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    /*
     * Method for Delete CardInventory
     */
    public void deleteCardInventory() {
        try {
            System.out.println("********************ServiceBean=>deleteCardInventory********");
            if (cardInventoryDataBean != null) {
                cardInventoryTransformerBean.deleteCardInventory(cardInventoryDataBean);
                retrieveCardInventoryList();
                System.out.println("CardInventory Delete Sussesfully");
                messageDataBean.setMessage("CardInventory deleted successfully.");
                messageDataBean.setIsSuccess(Boolean.TRUE);
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error in Deleting CardInventory.");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void assignCard() {
        try {
            if (cardInventoryDataBean.getAssigneUserId() != null) {
                String response = cardInventoryTransformerBean.assignCard(id, cardInventoryDataBean.getAssigneUserId());
                retrieveCardInventoryList();
                messageDataBean.setIsSuccess(true);
                messageDataBean.setMessage("Card assign successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public void retrieveUnusedCardList() {
        try {
            
            systemResultViewUtil.setCardInventoryDataBeansList(cardInventoryTransformerBean.reretrieveUnusedCardList());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveUnusedCardListForModifyUser(Long cardIdPk, Long cardId) throws UserManagementException {
        boolean falag = false;
        List<CardInventoryDataBean> cardInventoryDataBeanList = cardInventoryTransformerBean.reretrieveUnusedCardList();
        if (cardIdPk != null) {
            for (CardInventoryDataBean unUsedCardInventoryDataBean : cardInventoryDataBeanList) {
                if (unUsedCardInventoryDataBean.getId() == cardIdPk) {
                    falag = true;
                    break;
                }
            }
            if (!falag) {
                CardInventoryDataBean cardInventoryDataBeanNew = new CardInventoryDataBean();
                cardInventoryDataBeanNew.setId(cardIdPk);
                cardInventoryDataBeanNew.setCardId(cardId);
                cardInventoryDataBeanList.add(cardInventoryDataBeanNew);
            }
        }
        systemResultViewUtil.setCardInventoryDataBeansList(cardInventoryDataBeanList);
    }

    public void unAssignCard() {
        try {
            System.out.println(cardInventoryDataBean.getAssigneUserId());
            if (cardInventoryDataBean.getAssigneUserId() != null) {
                cardInventoryTransformerBean.unAssignCard(cardInventoryDataBean.getId());
                retrieveCardInventoryList();
                messageDataBean.setMessage("Card unassign successfully.");
                messageDataBean.setIsSuccess(true);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}
