/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.employee;

import com.mycompany.companymanagement.department.Department;
import com.mycompany.companymanagement.department.DepartmentDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author vivek
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    DepartmentDao departmentDao;


    @RequestMapping(value = "/addempform/{deptid}")
    public ModelAndView addDeptForm(@PathVariable int deptid) {

        Employee e = new Employee();
        e.setDept_id(deptid);

        return new ModelAndView("/Employee/addemp", "command", e);
    }

    @RequestMapping(value = "/addemp")
    public ModelAndView addEmployee(@ModelAttribute("emp") Employee emp) {

        employeeDao.save(emp);
        return new ModelAndView("redirect:/showdept");
    }
    
    
    @RequestMapping(value = "/allemployee/{id}")
    public ModelAndView getAllEmployee(@PathVariable int id) {

        List<Employee> emps=employeeDao.getEmployees(id);
        Department dept=departmentDao.getDepartmentByID(id);
        String name=dept.getDept_name();
        ModelAndView m=new ModelAndView("/Employee/showemp");
        m.addObject("employees", emps);
        m.addObject("deptname", name);
        return m;
//        return new ModelAndView("/Employee/showemp","employees",emps);
    }
    
    
     @RequestMapping(value="deleteemp/{e_id}/{d_id}")
    public ModelAndView deleteEmployee(@PathVariable int e_id,@PathVariable int d_id)
    {
        employeeDao.delete(e_id);
        System.out.println("d_id"+d_id);
        return new ModelAndView("redirect:/allemployee/"+d_id);
    }
    
    @RequestMapping(value="/editemp/{id}")
    public ModelAndView editDeptForm(@PathVariable int id)
    {
        System.out.println("Here");
        Employee e=employeeDao.getEmployeeByID(id);
        return new ModelAndView("/Employee/editemp","command",e);
    }
    
    @RequestMapping("/updateemp")
    public ModelAndView update(@ModelAttribute("emp") Employee emp)
    {
        employeeDao.edit(emp);
        return new ModelAndView("redirect:/allemployee/"+emp.dept_id);
    }
    
    @RequestMapping("/addattendance")
    public ModelAndView addAttendance(@ModelAttribute("emp") Employee emp)
    {
       
        return new ModelAndView("");
    }
    
    @RequestMapping(value="/attendanceform")
    public ModelAndView showAttendanceform()
    {
        List<Department> depats=departmentDao.getDepartments();
         List<String> names=new ArrayList<>();
        for(Department d:depats)
            names.add(d.getDept_name());
           
        //List<Employee> employees=employeeDao.getEmployees();
        ModelAndView modelAndView=new ModelAndView("/Attendance/addattendance");
        modelAndView.addObject("departments",depats );
        modelAndView.addObject("command",new Attendance());
        //modelAndView.addObject("departments",depats );
        //modelAndView.addObject("employees",employees);
          return modelAndView;      
        //return new ModelAndView("/Attendance/addattendance","command",);
    }

}
