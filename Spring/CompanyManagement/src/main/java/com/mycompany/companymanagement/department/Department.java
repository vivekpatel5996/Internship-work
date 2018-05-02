/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.department;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vivek
 */
@Entity
@Table(name="department")
public class Department implements Serializable 
{

     @Id
     @Column(name="id")
     @GeneratedValue(strategy=GenerationType.IDENTITY)        
     int id;
     
     @Column(name="dept_name")
     String dept_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
     @Override
    public String toString() {
        return "Department{" + "id=" + id + ", dept_name=" + dept_name + '}';
    }
}
