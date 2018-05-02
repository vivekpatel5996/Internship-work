/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.companymanagement.department;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author vivek
 */
@Controller

public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/")
    public ModelAndView displayDepartment() throws FileNotFoundException {
        List<Department> depats = departmentDao.getDepartments();
        System.out.println("in department controller");
       // this.store(depats);
        return new ModelAndView("/Department/showdept", "departments", depats);
    }

    @RequestMapping("/showdept")
    public ModelAndView showDepartment() {
        List<Department> depats = departmentDao.getDepartments();

        return new ModelAndView("/Department/showdept", "departments", depats);
    }

    @RequestMapping("/adddeptform")
    public ModelAndView addDeptForm() {
        //departmentDao.save(dept);
        return new ModelAndView("/Department/adddept", "command", new Department());
    }

    @RequestMapping("/adddept")
    public ModelAndView addDepartment(@ModelAttribute("dept") Department dept) {
        departmentDao.save(dept);
        return new ModelAndView("redirect:/");

    }

    @RequestMapping(value = "/deletedept/{id}")
    public ModelAndView deleteDepartment(@ModelAttribute("dept") Department dept,@PathVariable int id) {
        departmentDao.delete(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editdept/{id}")
    public ModelAndView editDeptForm(@PathVariable int id) {
        System.out.println("Here");
        Department d = departmentDao.getDepartmentByID(id);
        return new ModelAndView("/Department/editdept", "command", d);
    }

    @RequestMapping("/edit")
    public ModelAndView update(@ModelAttribute("dept") Department dept) {
        departmentDao.edit(dept);
        return new ModelAndView("redirect:/");
    }

//    public void store(List<Department> depats) throws FileNotFoundException {
//        JsonObjectBuilder empBuilder = Json.createObjectBuilder();
//        for (Department d : depats) {
//            System.out.println("Here in store");
//            empBuilder.add("id", d.getId());
//            empBuilder.add("dept_name", d.getDept_name());
//            JsonObject empJsonObject = empBuilder.build();
//
//            OutputStream os = new FileOutputStream("/home/vivek/NetBeansProjects/CompanyManagement/src/main/resources/departments.json", true);
//            try (JsonWriter jsonWriter = Json.createWriter(os)) {
//                jsonWriter.writeObject(empJsonObject);
//            }
//
//        }
//
//    }

}
