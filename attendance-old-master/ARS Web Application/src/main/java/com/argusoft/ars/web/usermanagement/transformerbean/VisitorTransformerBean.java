/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.transformerbean;

import com.argusoft.ars.core.CardInventoryService;
import com.argusoft.ars.core.VisitorService;
import com.argusoft.ars.model.CardInventory;
import com.argusoft.ars.model.Visitor;
import com.argusoft.ars.web.usermanagement.databean.LoginDataBean;
import com.argusoft.ars.web.usermanagement.databean.VisitorDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

/**
 * Transformer for Visitor.
 *
 * @author Harshit
 */
@ManagedBean(name = "visitorTransformerBean")
@RequestScoped
public class VisitorTransformerBean {

    //Login DataBean
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    //  Core properties
    @ManagedProperty(value = "#{visitorService}")
    private VisitorService visitorService;
    @ManagedProperty(value = "#{cardInventoryService}")
    private CardInventoryService cardInventoryService;
    //  Other properties
    private static final Logger log = Logger.getLogger(VisitorTransformerBean.class);

    public CardInventoryService getCardInventoryService() {
        return cardInventoryService;
    }

    public void setCardInventoryService(CardInventoryService cardInventoryService) {
        this.cardInventoryService = cardInventoryService;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public VisitorService getVisitorService() {
        return visitorService;
    }

    public void setVisitorService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    /**
     * Method to convert VisitorDataBean to Visitor Model.
     *
     */
    private Visitor convertVisitorDataBeanToVisitorModel(VisitorDataBean visitorDataBean, Visitor visitor) {

        visitor.setAddress(visitorDataBean.getAddress());
        visitor.setCardId(visitorDataBean.getCardIdPk());
        visitor.setCity(visitorDataBean.getCity());
        visitor.setEmail(visitorDataBean.getEmail());
        visitor.setFromDate(visitorDataBean.getFromDate());
        visitor.setIsArchive(false);
        visitor.setMobileNo(visitorDataBean.getMobileNo());
        visitor.setPincode(visitorDataBean.getPincode());
        visitor.setReasonToVisit(visitorDataBean.getReasonToVisit());
        visitor.setState(visitorDataBean.getState());
        visitor.setToDate(visitorDataBean.getToDate());
        visitor.setVisitorName(visitorDataBean.getVisitorName());
        return visitor;
    }

    /**
     * Method to convert Visitor Model to VisitorDataBean.
     */
    private VisitorDataBean convertVisitorModelToVisitorDataBean(Visitor visitor, VisitorDataBean visitorDataBean) {
        visitorDataBean.setAddress(visitor.getAddress());
        visitorDataBean.setcardIdPk(visitor.getCardId());
        visitorDataBean.setCity(visitor.getCity());
        visitorDataBean.setEmail(visitor.getEmail());
        visitorDataBean.setFromDate(visitor.getFromDate());
        visitorDataBean.setMobileNo(visitor.getMobileNo());
        visitorDataBean.setPincode(visitor.getPincode());
        visitorDataBean.setReasonToVisit(visitor.getReasonToVisit());
        visitorDataBean.setState(visitor.getState());
        visitorDataBean.setToDate(visitor.getToDate());
        visitorDataBean.setVisitorId(visitor.getVisitorId());
        visitorDataBean.setVisitorName(visitor.getVisitorName());
        visitorDataBean.setVisitEndDate(visitor.getVisitEndDate());
        return visitorDataBean;
    }

    /**
     * Method to Retrive all active Visitor
     */
    public List<VisitorDataBean> retrieveAllVisitor() {
        List<Visitor> visitorList = visitorService.retrieveAllVisitor();
        List<VisitorDataBean> visitorDataBeansList = new ArrayList<VisitorDataBean>();
        for (Visitor visitor : visitorList) {
            VisitorDataBean visitorDataBean = convertVisitorModelToVisitorDataBean(visitor, new VisitorDataBean());
            if (visitorDataBean.getCardIdPk() != null) {
                CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(visitorDataBean.getCardIdPk());
                if (cardInventory != null) {
                    visitorDataBean.setCardId(cardInventory.getCardId());
                }
            }
            visitorDataBeansList.add(visitorDataBean);
        }
        return visitorDataBeansList;
    }

    /**
     * Method to Retrive Visitor Detail by Visitor Id
     */
    public VisitorDataBean retrieveVisitorDetail(Long visitorId) {
        Visitor visitor = visitorService.retrieveVisitorById(visitorId);
        VisitorDataBean visitorDataBean = convertVisitorModelToVisitorDataBean(visitor, new VisitorDataBean());
        if (visitor.getVisitEndDate() != null) {
            visitorDataBean.setToDate(visitor.getVisitEndDate());
        }
        if (visitorDataBean.getCardIdPk() != null) {
            CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(visitorDataBean.getCardIdPk());
            visitorDataBean.setCardId(cardInventory.getCardId());
        }
        return visitorDataBean;
    }

    /**
     * Method to Add Visitor object.
     *
     */
    public String createVisitor(VisitorDataBean visitorDataBean) {
        try {
            Boolean isCardIdAlreadyUsedBeetweenThisDate = false;
            if (visitorDataBean.getCardIdPk() != null) {
                isCardIdAlreadyUsedBeetweenThisDate = visitorService.isCardIdAlreadyUsedBeetweenThisDate(visitorDataBean.getCardIdPk(), visitorDataBean.getFromDate(), visitorDataBean.getToDate());
            }
            if (!isCardIdAlreadyUsedBeetweenThisDate) {
                Visitor visitor = convertVisitorDataBeanToVisitorModel(visitorDataBean, new Visitor());
                visitor.setCreatedDate(new Date());
                visitor.setCreatedUser(loginDataBean.getId());
                Long visitorId = visitorService.createVisitor(visitor);
                if (visitorDataBean.getCardIdPk() != null) {
                    CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(visitorDataBean.getCardIdPk());
                    cardInventory.setVisitorId(visitorId);
                    cardInventory.setIsAssigned(Boolean.TRUE);
                    cardInventoryService.updateCardInventory(cardInventory);
                }
            } else {
                return "Card Id already in Use Beetween this Days";
            }
            return SystemConstantUtil.SUCCESS;
        } catch (Exception e) {
            System.out.println("Add Visitor Catch");
            return e.toString();
        }
    }

    /**
     * Method to Update Visitor Detail
     */
    public String updateVisitor(VisitorDataBean visitorDataBean) {
        try {
            Visitor visitor;
            visitor = visitorService.retrieveVisitorById(visitorDataBean.getVisitorId());
            if (visitor.getCardId() != visitorDataBean.getCardIdPk()) {
                if (visitorDataBean.getCardIdPk() != null) {
                    Boolean isCardIdAlreadyUsedBeetweenThisDate = visitorService.isCardIdAlreadyUsedBeetweenThisDate(visitorDataBean.getCardIdPk(), visitorDataBean.getFromDate(), visitorDataBean.getToDate());
                    if (!isCardIdAlreadyUsedBeetweenThisDate) {
                        if (visitor.getCardId() != null) {
                            CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(visitor.getCardId());
                            cardInventory.setVisitorId(null);
                            cardInventory.setIsAssigned(false);
                            cardInventoryService.updateCardInventory(cardInventory);
                        }
                        CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(visitorDataBean.getCardIdPk());
                        cardInventory.setVisitorId(visitor.getVisitorId());
                        cardInventory.setIsAssigned(Boolean.TRUE);
                        cardInventoryService.updateCardInventory(cardInventory);

                    } else {
                        return "Card Id already in Use Beetween this Days";
                    }
                }
            }

            Date todate = visitor.getToDate();
            visitor = convertVisitorDataBeanToVisitorModel(visitorDataBean, visitor);
            if (visitor.getVisitEndDate() != null) {
                visitor.setToDate(todate);
            }
            visitorService.updateVisitor(visitor);
            return SystemConstantUtil.SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            System.out.println(ex.getStackTrace().toString());

            return ex.toString();

        }
    }

    /**
     * Method to Inactivate Visitor.
     */
    public void deleteVisitor(VisitorDataBean visitorDataBean) {
        try {
            Visitor visitor = visitorService.retrieveVisitorById(visitorDataBean.getVisitorId());
            if (visitor != null) {
                visitor.setIsArchive(true);
                visitorService.updateVisitor(visitor);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public String endVisit(Long visitorId, Date visitEndDate) {
        Visitor visitor = visitorService.retrieveVisitorById(visitorId);
        if (!visitEndDate.before(visitor.getFromDate())) {
            CardInventory cardInventory = cardInventoryService.retrieveCardInventoryById(visitor.getCardId());
            if (cardInventory != null) {
                cardInventory.setVisitorId(null);
                cardInventory.setIsAssigned(false);
                cardInventoryService.updateCardInventory(cardInventory);
            }
            visitor.setVisitEndDate(visitEndDate);
            visitorService.updateVisitor(visitor);
            return SystemConstantUtil.SUCCESS;
        } else {
            return "Visit end date should not be less than visit from date";
        }
    }
}
