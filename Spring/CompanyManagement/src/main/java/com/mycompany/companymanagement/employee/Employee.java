/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.employee;


/**
 *
 * @author vivek
 */

public class Employee {

    int id;
    int dept_id;

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", dept_id=" + dept_id + ", name=" + name + ", contactno=" + contactno + '}';
    }
    String name;
    String contactno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

}
