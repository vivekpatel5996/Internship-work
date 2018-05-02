/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.department;

import com.mycompany.companymanagement.employee.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vivek
 */
@Repository

public class DepartmentDao {
    @Autowired
    HibernateTemplate hibernateTemplate;

    public void setTemplate(HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    JdbcTemplate template;

    @Autowired
    DataSource ds;
    
    
    @Autowired
    SessionFactory sessionFactory;

    public int save(Department d) {
   
       
        // Using Hibernate Template
       
        hibernateTemplate.save(d);
   
      
        return 1;

//        Using JDBCtemplate
//        template = new JdbcTemplate(ds);
//        String sql = "insert into department(dept_name)values('" + d.getDept_name() + "')";
//        return template.update(sql);
    }

    public List<Department> getDepartments() {
//        template = new JdbcTemplate(ds);
//        return template.query("select * from department", new RowMapper<Department>() {
//            public Department mapRow(ResultSet rs, int row) throws SQLException {
//                Department d = new Department();
//                d.setId(rs.getInt(1));
//                d.setDept_name(rs.getString(2));
//
//                return d;
//            }
//        });

        //using hibernate template
        //hibernateTemplate.loadAll(Department.class);
        //using hibernate session
        List<Department> list= sessionFactory.openSession().createCriteria(Department.class).list();
        return list;
    }

   @Transactional(readOnly = false)
    public int delete(int id) {
        // Using Hibernate template
        
       // System.out.println("Here in delete:"+id);
        
//       Department d=(Department)hibernateTemplate.load(Department.class, id);
//        int x=id;
//        hibernateTemplate.delete(d);
//       
//        hibernateTemplate.flush();
//       
        
        
        sessionFactory.openSession().createQuery("DELETE FROM Department WHERE id = "+id).executeUpdate();
        
        return 1;
        
        //Using JDBCtemplate

//        template = new JdbcTemplate(ds);
//        String sql = "delete from department where id=" + id + "";
//        return template.update(sql);
    }

    public int edit(Department d) {
//        template = new JdbcTemplate(ds);
//        System.out.println("idxcv:" + d.getId());
//        String sql = "update department set dept_name='" + d.getDept_name() + "'where id=" + d.getId() + "";
//
//        return template.update(sql);

//using hibernate sessionfactory
        Session s=sessionFactory.openSession();
        s.beginTransaction();
         
        s.update(d);
        s.getTransaction().commit();
        return 1;
    }

    public Department getDepartmentByID(int id) {
//        System.out.println("Here in method");
//        template = new JdbcTemplate(ds);
//        String sql = "select * from department where id=" + id;
//        System.out.println("Here insadasdasda method");
//
//        //return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Department>(Department.class));
//        //return d;
//        return template.query(sql, new ResultSetExtractor<Department>() {
//            public Department extractData(ResultSet rs) throws SQLException,
//                    DataAccessException {
//                if (rs.next()) {
//                    Department d = new Department();
//                    d.setId(rs.getInt(1));
//                    d.setDept_name(rs.getString(2));
//
//                    return d;
//                }
//                return null;
//            }
//        });
    Department d =(Department)sessionFactory.openSession().load(Department.class, id);
    return d;
    }

}
