/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.employee;

import com.mycompany.companymanagement.department.Department;
import com.mycompany.companymanagement.department.DepartmentDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vivek
 */
@RestController
public class EmployeeRestAPI {

    @Autowired
    EmployeeDao employeeDao;

    JdbcTemplate template;
    @Autowired
    DataSource ds;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("allemp/{dept_id}")
    public List<Employee> getEmployees(@PathVariable int dept_id) {
        template = new JdbcTemplate(ds);
        return template.query("select * from employee where dept_id=" + dept_id, new RowMapper<Employee>() {
            public Employee mapRow(ResultSet rs, int row) throws SQLException {
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setDept_id(rs.getInt(2));
                e.setName(rs.getString(3));
                e.setContactno(rs.getString(4));
                return e;
            }
        });

    }

    @RequestMapping("delete/{id}")
    public int delete(@PathVariable int id) {
        template = new JdbcTemplate(ds);
        String sql = "delete from employee where id=" + id;
        return template.update(sql);
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public int save(Employee p) {
//        int t = employeeDao.save(p);
//        return t;
//
//    }
    
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public int update(Employee p,@PathVariable int id) {
        int t = employeeDao.edit(p);
        return t;

    }
    
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
     public Employee getEmployeeByID(@PathVariable int id) {
        Employee e=employeeDao.getEmployeeByID(id);
        return e;
     }
    
    
}
