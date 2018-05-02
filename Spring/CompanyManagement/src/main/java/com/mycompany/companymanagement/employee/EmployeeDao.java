/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.employee;

import com.mycompany.companymanagement.department.Department;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vivek
 */

public class EmployeeDao {

    JdbcTemplate template;

    @Autowired
    DataSource ds;

    @Transactional
    public void save(Employee p) {
        template = new JdbcTemplate(ds);
        String sql = "insert into employee(dept_id,name,contactno) values(" + p.getDept_id() + ",'" + p.getName() + "','" + p.getContactno() + "')";
       
        String sql1="select * from employee where id=";
        int t=template.update(sql);
        Employee e=template.query(sql1, new ResultSetExtractor<Employee>() {
            
            @Override
            public Employee extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt(1));
                    e.setDept_id(rs.getInt(2));
                    e.setName(rs.getString(3));
                    e.setContactno(rs.getString(4));
                    return e;
                }
                return null;
                
            }
        });
        
        System.out.println("eemmpplloo:"+e);
        
       
          
       
    }

    public List<Employee> getEmployees(int dept_id) {
        template = new JdbcTemplate(ds);
        return template.query("select * from employee where dept_id=" + dept_id, new RowMapper<Employee>() {
            @Override
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

    public int delete(int id) {
        template = new JdbcTemplate(ds);
        String sql = "delete from employee where id=" + id;
        return template.update(sql);
    }

    public Employee getEmployeeByID(int id) {
        System.out.println("Here in method");
        template = new JdbcTemplate(ds);
        String sql = "select * from employee where id=" + id;
        System.out.println("Here insadasdasda method");

        //return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Department>(Department.class));
        //return d;
        return template.query(sql, new ResultSetExtractor<Employee>() {
            @Override
            public Employee extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt(1));
                    e.setDept_id(rs.getInt(2));
                    e.setName(rs.getString(3));
                    e.setContactno(rs.getString(4));
                    return e;
                }
                return null;
            }
        });
    }

    public int edit(Employee e) {
        template = new JdbcTemplate(ds);
        System.out.println("idemp:" + e.getId());
        String sql = "update employee set name='" + e.getName() + "',contactno='" + e.getContactno() + "' where id=" + e.getId() + "";

        return template.update(sql);
    }
    
    public List<String> hello()
    {
        List<String> list=new ArrayList<>();
        list.add("vivek");
        list.add("patel");
        return list;
                
    }

//    @Transactional
//    public void test() {
//        
//        temtemplate = new JdbcTemplate(ds);
      //  String sql = "insert into employee(dept_id,name,contactno) values(" + p.getDept_id() + ",'" + p.getName() + "','" + p.getContactno() + "')";plate = new JdbcTemplate(ds);
//        String sql;
//        sql = "insert into employee(dept_id,name,contactno) values('70','employee','148313')";
//        template.update(sql);
//        
//
//    }

//    template = new JdbcTemplate(ds);
//        String sql = "insert into employee(dept_id,name,contactno) values(" + p.getDept_id() + ",'" + p.getName() + "','" + p.getContactno() + "')";
}
