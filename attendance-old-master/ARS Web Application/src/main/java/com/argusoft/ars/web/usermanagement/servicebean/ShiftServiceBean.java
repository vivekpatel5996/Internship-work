/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.ShiftDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.ShiftTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Harshit ServiceBean for Shift
 */
@ManagedBean(name = "shiftServiceBean")
@RequestScoped
public class ShiftServiceBean {

    @ManagedProperty(value = "#{shiftDataBean}")
    private ShiftDataBean shiftDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{shiftTransformerBean}")
    private ShiftTransformerBean shiftTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    private Long shiftId;
    private String shiftName;

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public ShiftDataBean getShiftDataBean() {
        return shiftDataBean;
    }

    public void setShiftDataBean(ShiftDataBean shiftDataBean) {
        this.shiftDataBean = shiftDataBean;
    }

    public ShiftTransformerBean getShiftTransformerBean() {
        return shiftTransformerBean;
    }

    public void setShiftTransformerBean(ShiftTransformerBean shiftTransformerBean) {
        this.shiftTransformerBean = shiftTransformerBean;
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
     * Method for Retrive Shift List for dataTable
     */
    public void retrieveShiftList() {
        try {
            systemResultViewUtil.setShiftDataBeansList(shiftTransformerBean.retrieveAllShift());
            System.out.println("Retrive Shift Successfully");
        } catch (Exception e) {
            System.out.println("Error in Retrive Shift");
        }
    }

    /*
     * Method for get shift detail.
     */
    public void fillShiftDetail() {
        ShiftDataBean shift = shiftTransformerBean.retrieveShiftDetail(shiftId);
        shiftDataBean.setShiftId(shift.getShiftId());
        shiftDataBean.setShiftName(shift.getShiftName());
        shiftDataBean.setShiftStratTime(shift.getShiftStratTime());
        shiftDataBean.setShiftEndTime(shift.getShiftEndTime());
    }
    /*
     * Method for Add Shift
     */

    public void createShift() {
        try {
            if (shiftDataBean != null) {
                String response = shiftTransformerBean.createShift(shiftDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    System.out.println("Create Shift Successfully");
                    messageDataBean.setMessage("Shift added successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    doDatabeanNull();
                    this.retrieveShiftList();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    System.out.println("Shift Name already created");
                    messageDataBean.setMessage("Shift Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    System.out.println("Shift not created");
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
     * Method for Update Shift
     */
    public void updateShift() {
        try {
            if (shiftDataBean != null) {
                String response = shiftTransformerBean.updateShift(shiftDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Shift updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    doDatabeanNull();
                    this.retrieveShiftList();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    System.out.println("Shift Name already created");
                    messageDataBean.setMessage("Shift Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    System.out.println("Shift not created");
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

    private void doDatabeanNull() {
        shiftDataBean.setShiftName(null);
        shiftDataBean.setShiftId(null);
        shiftDataBean.setShiftStratTime(null);
        shiftDataBean.setShiftEndTime(null);
    }
    /*
     * Method for Delete Shift
     */

    public void deleteShift() {
        try {
            System.out.println("********************ServiceBean=>deleteShift********");
            if (shiftDataBean != null) {
                String response = shiftTransformerBean.deleteShift(shiftDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setMessage("Shift deleted successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    this.retrieveShiftList();
//                } else if (response.equals(SystemConstantUtil.NOT_EMPTY)) {
//                    messageDataBean.setMessage("Some employee is related to this shift so you can't delete this shift");
//                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
                    messageDataBean.setMessage(response);
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            messageDataBean.setMessage("Error in deleting shift");
            messageDataBean.setIsSuccess(Boolean.FALSE);
        }
    }

    public void retrieveAddToThisShiftList() {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList(shiftTransformerBean.retrieveAddToThisShiftDetail(shiftId));
        } catch (Exception e) {
            System.out.println("Error in Retrive Add To This Shift");
            System.out.println(e);
        }
    }

    public void addEmployeeToThisShift() {
        try {
            boolean flag = false;
            for (SystemUserDetailDataBean systemUserDetailDataBean : systemResultViewUtil.getSystemUserDetailDataBeansList()) {
                if (systemUserDetailDataBean.isChecked() == true) {
                    shiftTransformerBean.addEmployeeToThisShift(systemUserDetailDataBean, shiftId);
                    flag = true;
                }
            }
            if (flag) {
                messageDataBean.setMessage("Employee add to " + shiftName + " shift successfully");
                messageDataBean.setIsSuccess(true);
                this.retrieveShiftList();
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
    
    public void retrieveEmployeeByDesignation()
    {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList(shiftTransformerBean.retrieveEmployeeByDesignation(shiftId));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("==> Error :" + e);
        }
    }
}
