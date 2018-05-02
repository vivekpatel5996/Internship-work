/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.employee;

import com.mycompany.companymanagement.department.Department;

/**
 *
 * @author vivek
 */
public class Attendance 
{
    Department dept; 

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
   String department;    

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
   
   
}
