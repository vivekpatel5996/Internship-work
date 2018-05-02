/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.leavemanagement.servicebean;

import com.argusoft.ars.web.leavemanagement.databean.OfficialBreakDataBean;
import com.argusoft.ars.web.leavemanagement.transformerbean.OfficialBreakTransformerBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "officialBreakServiceBean")
@RequestScoped
public class OfficialBreakServiceBean {

    @ManagedProperty(value = "#{officialBreakDataBean}")
    private OfficialBreakDataBean officialBreakDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{officialBreakTransformerBean}")
    private OfficialBreakTransformerBean officialBreakTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
//    @ManagedProperty(value = "#{systemConfigurationTransformerBean}")
//    private SystemConfigurationTransformerBean systemConfigurationTransformerBean;
    private Long officialBreakId;
    private String userName;
    private Long userId;
    private String officialBreakType;

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public OfficialBreakDataBean getOfficialBreakDataBean() {
        return officialBreakDataBean;
    }

    public void setOfficialBreakDataBean(OfficialBreakDataBean officialBreakDataBean) {
        this.officialBreakDataBean = officialBreakDataBean;
    }

    public Long getOfficialBreakId() {
        return officialBreakId;
    }

    public void setOfficialBreakId(Long officialBreakId) {
        this.officialBreakId = officialBreakId;
    }

    public OfficialBreakTransformerBean getOfficialBreakTransformerBean() {
        return officialBreakTransformerBean;
    }

    public void setOfficialBreakTransformerBean(OfficialBreakTransformerBean officialBreakTransformerBean) {
        this.officialBreakTransformerBean = officialBreakTransformerBean;
    }

    public String getOfficialBreakType() {
        return officialBreakType;
    }
    public void setOfficialBreakType(String officialBreakType) {
        this.officialBreakType = officialBreakType;
    }
    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }
    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void applyOfficialBreak() {
        Boolean isInformationCorrect = isOfficialBreakInformationCorrect();
        System.out.println("Service Bean Appply Official Break--------------------------------------");
        if (isInformationCorrect) {
            String response = officialBreakTransformerBean.applyOfficialBreak(officialBreakDataBean);
            if (response.equals(SystemConstantUtil.SUCCESS)) {
                messageDataBean.setMessage("Official Break Applied Successfully..");
                messageDataBean.setIsSuccess(true);
                this.retrieveOfficialBreakList();
            } else {
                messageDataBean.setIsSuccess(false);
                messageDataBean.setMessage(response);
            }
        }
    }
    public void fillOfficialBreakDetail(String btnClick) {
        try {
            System.out.println(btnClick);
            System.out.println("******************fill OfficialBreak Detail ****************");
            System.out.println("officialBreak Id:" + officialBreakId);
            OfficialBreakDataBean officialBreakDetail = officialBreakTransformerBean.retrieveOfficialBreakDetail(officialBreakId, btnClick);
            fillOfficialBreakDataBean(officialBreakDetail);
            officialBreakDataBean.setUserName(userName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void retrieveOfficialBreakList() {
        try {
            System.out.println("Retrive Official break list");
            systemResultViewUtil.setOfficialBreakDataBeans(officialBreakTransformerBean.retrieveAllOfficialBreak());
        } catch (Exception e) {
            System.out.println("Error in Retriving official break List");
            System.out.println(e);
        }
    }
    public void cancelOfficialBreak() {
        String response = officialBreakTransformerBean.cancelOfficialBreak(officialBreakDataBean);
        if (response.equals(SystemConstantUtil.SUCCESS)) {
            messageDataBean.setMessage("OfficialBreak Cancelled Succesfully..");
            messageDataBean.setIsSuccess(true);
            this.retrieveOfficialBreakList();
        } else {
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(response);
        }
    }
    public void modifyOfficialBreak() {
        try {
            System.out.println("ServiceBean Modify officialBreak");
            Boolean isInformationCorrect = isOfficialBreakInformationCorrect();
            if (isInformationCorrect) {
                String response = officialBreakTransformerBean.updateOfficialBreak(officialBreakDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setMessage("OfficialBreak modified successfully..");
                    messageDataBean.setIsSuccess(true);
                    this.retrieveOfficialBreakList();
                } else {
                    System.out.println("not modified successfully");
                    messageDataBean.setIsSuccess(false);
                    messageDataBean.setMessage(response);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in modify officialBreak in servicebean-------" + e);
        }
    }

    private void fillOfficialBreakDataBean(OfficialBreakDataBean officialBreakDetail) {
        try {
            if (officialBreakDetail.getOfficialBreakType().equals("officialBreak")) {
                officialBreakDataBean.setCardLogFromDate(officialBreakDetail.getFromDate().toString());
                officialBreakDataBean.setCardLogToDate(officialBreakDetail.getToDate().toString());
            } else {
                officialBreakDataBean.setFromDate(officialBreakDetail.getFromDate());
                officialBreakDataBean.setToDate(officialBreakDetail.getToDate());
            }
            officialBreakDataBean.setAdminComment(officialBreakDetail.getAdminComment());
            officialBreakDataBean.setAppliedDate(officialBreakDetail.getAppliedDate());
            officialBreakDataBean.setApprovalStatus(officialBreakDetail.getApprovalStatus());
            officialBreakDataBean.setAppliedStatus(officialBreakDetail.getAppliedStatus());
            officialBreakDataBean.setOfficialBreakId(officialBreakDetail.getOfficialBreakId());
            officialBreakDataBean.setOfficialBreakType(officialBreakDetail.getOfficialBreakType());
            officialBreakDataBean.setReason(officialBreakDetail.getReason());
            officialBreakDataBean.setResponseBy(officialBreakDetail.getResponseBy());
            officialBreakDataBean.setResponseDate(officialBreakDetail.getResponseDate());
            officialBreakDataBean.setUserId(officialBreakDetail.getUserId());
            officialBreakDataBean.setBreakDuration(officialBreakDetail.getBreakDuration());
            officialBreakDataBean.setResponseByUserName(officialBreakDetail.getResponseByUserName());
            System.out.println("after fillofficialbreakdetail-----------------");
        } catch (Exception e) {
            System.out.println("exception--------" + e);
        }
    }
    private Boolean isOfficialBreakInformationCorrect() {
        Boolean flag = true;
        if (officialBreakDataBean.getOfficialBreakType().equals(SystemConstantUtil.BUSINESS_TRIP)) {
            if (officialBreakDataBean.getFromDate() == null) {
                officialBreakDataBean.setFromDateValidationMessage("Please enter the from date!!");
                flag = false;
            }
            if (officialBreakDataBean.getReason() == null || "".equals(officialBreakDataBean.getReason().trim())) {
                officialBreakDataBean.setReasonValidationMessage("Please enter the reason");
                flag = false;
            }
            if (officialBreakDataBean.getToDate() == null) {
                officialBreakDataBean.setToDateValidationMessage("Please enter the todate");
                flag = false;
            }
        }
        if (officialBreakDataBean.getOfficialBreakType().equals(SystemConstantUtil.OFFICIAL_BREAK)) {
            if (officialBreakDataBean.getCardLogFromDate() == null) {
                officialBreakDataBean.setFromDateValidationMessage("Please select the from date time period");
                flag = false;
            }
            if (officialBreakDataBean.getReason() == null || "".equals(officialBreakDataBean.getReason().trim())) {
                officialBreakDataBean.setReasonValidationMessage("Please enter the reason");
                flag = false;
            }
        }
        return flag;
    }
     public void approveOfficialBreak() {
        try {
            String response = officialBreakTransformerBean.approveOfficialBreak(officialBreakDataBean.getOfficialBreakId(), officialBreakDataBean.getAdminComment());
            messageDataBean.setMessage("OfficialBreak Approved successfully..");
            messageDataBean.setIsSuccess(true);
            this.retrieveOfficialBreakNotificationList();
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }
    public void disApproveOfficialBreak() {
        try {
            String response = officialBreakTransformerBean.disApproveOfficialBreak(officialBreakDataBean.getOfficialBreakId(), officialBreakDataBean.getAdminComment());
            messageDataBean.setMessage("OfficialBreak Disapprove successfully..");
            messageDataBean.setIsSuccess(true);
            this.retrievePersonalOfficialBreakNotificationList();
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }
    public void archiveOfficialBreak() {
        try {
            officialBreakTransformerBean.archiveOfficialBreak(officialBreakDataBean.getOfficialBreakId());
            messageDataBean.setMessage("OfficialBreak Archive successfully !!!!");
            messageDataBean.setIsSuccess(true);
            this.retrievePersonalOfficialBreakNotificationList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void cancelOfficialBreakArchive() {
        try {
            String response = officialBreakTransformerBean.cancelOfficialBreakArchive(officialBreakDataBean.getOfficialBreakId());
            messageDataBean.setMessage("OfficialBreak Archive successfully..");
            messageDataBean.setIsSuccess(true);
            this.retrieveOfficialBreakList();
        } catch (Exception ex) {
            System.out.println(ex);
            messageDataBean.setIsSuccess(false);
            messageDataBean.setMessage(ex.toString());
        }
    }
    public void retrieveOfficialBreakNotificationList() {
        try {
            System.out.println("Retrive list");
            systemResultViewUtil.setOfficialBreakDataBeans(officialBreakTransformerBean.retrieveOfficialBreakNotificationList());
        } catch (Exception e) {
            System.out.println("Error in Retrive List");
            System.out.println(e);
        }
    }
    public void retrievePersonalOfficialBreakNotificationList() {
         try {
            System.out.println("**************** retrieve Personal officialBreak List ********************");
            systemResultViewUtil.setOfficialBreakDataBeans(officialBreakTransformerBean.retrievePersonalOfficialBreakNotificationList());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
