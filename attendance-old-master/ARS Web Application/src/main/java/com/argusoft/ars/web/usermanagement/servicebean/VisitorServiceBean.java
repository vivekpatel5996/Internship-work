/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.VisitorDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.VisitorTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Harshit ServiceBean for Visitor
 */
@ManagedBean(name = "visitorServiceBean")
@RequestScoped
public class VisitorServiceBean {

    @ManagedProperty(value = "#{visitorDataBean}")
    private VisitorDataBean visitorDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{visitorTransformerBean}")
    private VisitorTransformerBean visitorTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{cardInventoryServiceBean}")
    private CardInventoryServiceBean cardInventoryServiceBean;
    private Long visitorId;

    public CardInventoryServiceBean getCardInventoryServiceBean() {
        return cardInventoryServiceBean;
    }

    public void setCardInventoryServiceBean(CardInventoryServiceBean cardInventoryServiceBean) {
        this.cardInventoryServiceBean = cardInventoryServiceBean;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public VisitorDataBean getVisitorDataBean() {
        return visitorDataBean;
    }

    public void setVisitorDataBean(VisitorDataBean visitorDataBean) {
        this.visitorDataBean = visitorDataBean;
    }

    public VisitorTransformerBean getVisitorTransformerBean() {
        return visitorTransformerBean;
    }

    public void setVisitorTransformerBean(VisitorTransformerBean visitorTransformerBean) {
        this.visitorTransformerBean = visitorTransformerBean;
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
     * Method for Retrive Visitor List for dataTable
     */
    public void retrieveVisitorList() {
        try {
            systemResultViewUtil.setVisitorDataBeansList(visitorTransformerBean.retrieveAllVisitor());
        } catch (Exception e) {
            System.out.println("Error in Retrive Visitor");
            System.out.println(e);
        }
    }

    /*
     * Method for get visitor detail.
     */
    public void fillVisitorDetail(String type) throws UserManagementException {
        try {
            VisitorDataBean visitor = visitorTransformerBean.retrieveVisitorDetail(visitorId);
            visitorDataBean.setVisitorDataBean(visitor);
            System.out.println(visitorDataBean.getCardIdPk());
            if ("update".equals(type)) {
                cardInventoryServiceBean.retrieveUnusedCardListForModifyUser(visitorDataBean.getCardIdPk(), visitorDataBean.getCardId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
     * Method for Add Visitor
     */

    public void createVisitor() {
        try {
            if (visitorDataBean != null) {
                String response = visitorTransformerBean.createVisitor(visitorDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Visitor created successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    visitorDataBean.setNull();
                    this.retrieveVisitorList();

                } else {
                    System.out.println("Visitor not created");
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
     * Method for Update Visitor
     */
    public void updateVisitor() {
        try {
            if (visitorDataBean != null) {
                String response = visitorTransformerBean.updateVisitor(visitorDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Visitor updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    visitorDataBean.setNull();
                    this.retrieveVisitorList();
                } else {
                    System.out.println("Visitor not created");
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
     * Method for Delete Visitor
     */
    public void deleteVisitor() {
        try {
            System.out.println("********************ServiceBean=>deleteVisitor********");
            if (visitorDataBean != null) {
                visitorTransformerBean.deleteVisitor(visitorDataBean);
                System.out.println("Visitor deleted successfully");
                messageDataBean.setMessage("Visitor deleted successfully.");
                messageDataBean.setIsSuccess(Boolean.TRUE);
                this.retrieveVisitorList();
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error in deleting Visitor.");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }
    /*
     * Method for End Visit
     */

    public void endVisit() {
        try {
            
            String response = visitorTransformerBean.endVisit(visitorDataBean.getVisitorId(), visitorDataBean.getToDate());
            if (response.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Visitor visit-end detail submitted successfully.");
                messageDataBean.setIsSuccess(Boolean.TRUE);
            } else {
                messageDataBean.setMessage(response);
                messageDataBean.setIsSuccess(Boolean.FALSE);
            }

        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
        this.retrieveVisitorList();
    }

    public void doNullVisitorDataBean() {
        visitorDataBean.setNull();
        cardInventoryServiceBean.retrieveUnusedCardList();
    }
}
