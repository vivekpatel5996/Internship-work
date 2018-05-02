/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.usermanagement.databean.DepartmentDataBean;
import com.argusoft.ars.web.usermanagement.databean.MessageDataBean;
import com.argusoft.ars.web.usermanagement.databean.SystemUserDetailDataBean;
import com.argusoft.ars.web.usermanagement.transformerbean.DepartmentTransformerBean;
import com.argusoft.ars.web.util.SystemConstantUtil;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Harshit ServiceBean for Department
 */
@ManagedBean(name = "departmentServiceBean")
@RequestScoped
public class DepartmentServiceBean {

    @ManagedProperty(value = "#{departmentDataBean}")
    private DepartmentDataBean departmentDataBean;
    //  transformer injection
    @ManagedProperty(value = "#{departmentTransformerBean}")
    private DepartmentTransformerBean departmentTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    private Long depId;
    private String depName;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public DepartmentDataBean getDepartmentDataBean() {
        return departmentDataBean;
    }

    public void setDepartmentDataBean(DepartmentDataBean departmentDataBean) {
        this.departmentDataBean = departmentDataBean;
    }

    public DepartmentTransformerBean getDepartmentTransformerBean() {
        return departmentTransformerBean;
    }

    public void setDepartmentTransformerBean(DepartmentTransformerBean departmentTransformerBean) {
        this.departmentTransformerBean = departmentTransformerBean;
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
     * Method for Retrive Department List for dataTable
     */
    public void retrieveDepartmentList() {
        try {
            systemResultViewUtil.setDepartmentDataBeansList(departmentTransformerBean.retrieveAllDepartment());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveAddToThisDepartmentList() {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList(departmentTransformerBean.retrieveAddToThisDepartmentDetail(depId));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Method for get department detail.
     */
    public void fillDepartmentDetail() {
        DepartmentDataBean dept = departmentTransformerBean.retrieveDepartmentDetail(depId);
        departmentDataBean.setDepId(dept.getDepId());
        departmentDataBean.setDepName(dept.getDepName());
    }
    /*
     * Method for Add Department
     */

    public void createDepartment() {
        try {
            if (departmentDataBean != null) {
                String response = departmentTransformerBean.createDepartment(departmentDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Department added successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    departmentDataBean.setDepName(null);
                    this.retrieveDepartmentList();
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    messageDataBean.setMessage("Department Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
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
     * Method for Update Department
     */
    public void updateDepartment() {
        try {
            if (departmentDataBean != null) {
                String response = departmentTransformerBean.updateDepartment(departmentDataBean);
                if (SystemConstantUtil.SUCCESS.equals(response)) {
                    messageDataBean.setMessage("Department updated successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    departmentDataBean.setDepName(null);
                    this.retrieveDepartmentList();
//                } else if (SystemConstantUtil.SAME.equals(response)) {
//                    messageDataBean.setMessage("Try to update department without any changes...");
//                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else if (SystemConstantUtil.REPEAT.equals(response)) {
                    messageDataBean.setMessage("Department Name already exists.");
                    messageDataBean.setIsSuccess(Boolean.FALSE);
                } else {
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
     * Method for Delete Department
     */
    public void deleteDepartment() {
        try {
            System.out.println("********************ServiceBean=>deleteDepartment********");
            if (departmentDataBean != null) {
                String response = departmentTransformerBean.deleteDepartment(departmentDataBean);
                if (response.equals(SystemConstantUtil.SUCCESS)) {
                    messageDataBean.setMessage("Department deleted successfully.");
                    messageDataBean.setIsSuccess(Boolean.TRUE);
                    this.retrieveDepartmentList();
//                } else if (response.equals(SystemConstantUtil.NOT_EMPTY)) {
//                    messageDataBean.setMessage("Some employee is related to this department so you can't delete this department");
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

    public void addEmployeeToThisDepartment() {
        try {
            boolean flag = false;
            for (SystemUserDetailDataBean systemUserDetailDataBean : systemResultViewUtil.getSystemUserDetailDataBeansList()) {
                if (systemUserDetailDataBean.isChecked() == true) {
                    departmentTransformerBean.addEmployeeToThisDepartment(systemUserDetailDataBean, depId);
                    flag = true;
                }
            }
            if (flag) {
                messageDataBean.setMessage("Employee added to " + depName + " department successfully.");
                messageDataBean.setIsSuccess(true);
                this.retrieveDepartmentList();
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

    public void retrieveEmployeeByDepartment() {
        try {
            systemResultViewUtil.setSystemUserDetailDataBeansList(departmentTransformerBean.retrieveEmployeeByDepartment(depId));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("==> Error :" + e);
        }
    }
}
