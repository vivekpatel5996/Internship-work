/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.DesignationDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.DesignationTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Harshit ServiceBean for Designation
 */
@ManagedBean(name = "designationServiceBean")
@RequestScoped
public class DesignationServiceBean {

    @ManagedProperty(value = "#{designationDataBean}")
    private DesignationDataBean designationDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{designationTransformerBean}")
    private DesignationTransformerBean designationTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    private Long desgId;
    private String desgName;

    public String getDesgName() {
        return desgName;
    }

    public void setDesgName(String desgName) {
        this.desgName = desgName;
    }

    public Long getDesgId() {
        return desgId;
    }

    public void setDesgId(Long desgId) {
        this.desgId = desgId;
    }

    public DesignationDataBean getDesignationDataBean() {
        return designationDataBean;
    }

    public void setDesignationDataBean(DesignationDataBean designationDataBean) {
        this.designationDataBean = designationDataBean;
    }

    public DesignationTransformerBean getDesignationTransformerBean() {
        return designationTransformerBean;
    }

    public void setDesignationTransformerBean(DesignationTransformerBean designationTransformerBean) {
        this.designationTransformerBean = designationTransformerBean;
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
     * Method for Retrive Designation List for dataTable
     */
    public void retrieveDesignationList() {
        try {
            systemResultViewUtil.setDesignationDataBeansList(designationTransformerBean.retrieveAllDesignation());
            System.out.println("Retrive Designation Successfully");
        } catch (Exception e) {
            System.out.println("Error in Retrive Designation");
        }
    }

    /*
     * Method for get designation detail.
     */
    public void fillDesignationDetail() {
        System.out.println("DesgId" + desgId);
        DesignationDataBean desgt = designationTransformerBean.retrieveDesignationDetail(desgId);
        designationDataBean.setDesgId(desgt.getDesgId());
        designationDataBean.setDesgName(desgt.getDesgName());
        System.out.println("desgName=" + designationDataBean.getDesgName() + "desgId" + designationDataBean.getDesgId());
    }
    /*
     * Method for Add Designation
     */

    public void createDesignation() {
        try {
            if (designationDataBean != null) {
                String response = designationTransformerBean.createDesignation(designationDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Designation added successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    designationDataBean.setDesgName(null);
                    this.retrieveDesignationList();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    messageDataBean.setMessage("Designation Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    messageDataBean.setMessage("Designation not added.");
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
     * Method for Update Designation
     */
    public void updateDesignation() {
        try {
            if (designationDataBean != null) {
                String response = designationTransformerBean.updateDesignation(designationDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Designation updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    designationDataBean.setDesgName(null);
                    retrieveDesignationList();
//                } else if (SystemConstantUtil.SAME.equals(response)) {
//                    messageDataBean.setMessage("Try to update Designation with same name");
//                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    messageDataBean.setMessage("Designation Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    System.out.println("Designation not created");
                    messageDataBean.setMessage("Error =" + response);
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
     * Method for Delete Designation
     */
    public void deleteDesignation() {
        try {
            System.out.println("********************ServiceBean=>deleteDesignation********");
            if (designationDataBean != null) {
                String response = designationTransformerBean.deleteDesignation(designationDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setMessage("Designation deleted successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    this.retrieveDesignationList();
//                } else if (response.equals(SystemConstantUtil.NOT_EMPTY)) {
//                    messageDataBean.setMessage("Some employee is related to this designation so you can't delete this designation");
//                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void retrieveAddToThisDesignationList() {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList(designationTransformerBean.retrieveAddToThisDesignationDetail(desgId));
        } catch (Exception e) {
            System.out.println("Error in Retrive Add To This Designation");
            System.out.println(e);
        }
    }

    public void addEmployeeToThisDesignation() {
        try {
            boolean flag = false;
            for (SystemUserDetailDataBean systemUserDetailDataBean : systemResultViewUtil.getSystemUserDetailDataBeansList()) {
                if (systemUserDetailDataBean.isChecked() == true) {
                    designationTransformerBean.addEmployeeToThisDesignation(systemUserDetailDataBean, desgId);
                    flag = true;
                }
            }
            if (flag) {
                messageDataBean.setMessage("Employee add to " + desgName + " designation successfully.");
                messageDataBean.setIsSuccess(true);
                this.retrieveDesignationList();
            } else {
                messageDataBean.setMessage("Please select employees.");
                messageDataBean.setIsSuccess(false);
            }
        } catch (Exception e) {
            messageDataBean.setMessage(e.toString());
            messageDataBean.setIsSuccess(false);
            System.out.println(e);
        }
    }

    public void retrieveEmployeeByDesignation() {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList(designationTransformerBean.retrieveEmployeeByDesignation(desgId));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("==> Error :" + e);
        }
    }
}
