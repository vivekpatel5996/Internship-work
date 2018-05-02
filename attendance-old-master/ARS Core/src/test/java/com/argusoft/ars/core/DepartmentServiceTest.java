/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.Department;
import java.util.Date;
import javax.annotation.Resource;
import static org.junit.Assert.assertNotNull;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Harshit
 */
/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {CoreApplicationConfig.class})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class DepartmentServiceTest {

    @Resource
    DepartmentService instance;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createDepartment method, of class DepartmentServiceTest.
     */
   @Test
    public void testCreateDepartment() {
        System.out.println("createDepartment");
        Department department = new Department();
            department.setCreatedBy(1L);
//        department.setDeptId(2L);
        department.setDeptName("Testing");
        department.setIsActive(true);
        department.setIsArchive(true);
        department.setCreatedDate(new Date());
        instance.createDepartment(department);
    }

    /**
     * Test of updateDepartment method, of class DepartmentServiceTest.
     */
    @Test
    public void testUpdateDepartment() {
        System.out.println("updateDepartment");
           Department department;
        department = instance.retrieveDepartmentById(19L);
        department.setDeptName("GOD");
        instance.updateDepartment(department);
    }

    /**
     * Test of retrieveDepartmentByKey method, of class DepartmentServiceTest.
     */
  // @Test
    public void testRetrieveDepartmentByKey() {
        System.out.println("retrieveDepartmentByKey");
        Department department = new Department();
        department.setCreatedBy(1L);
        department.setDeptName("Testing");
        department.setIsActive(true);
        department.setIsArchive(true);
        department.setCreatedDate(new Date());
        instance.createDepartment(department);
        department = instance.retrieveDepartmentById(1L);
        assertNotNull(department);
    }

    /**
     * Test of retrieveAllDepartments method, of class DepartmentServiceTest.
     */
//    @Test
    public void testRetrieveAllDepartments() {
//        System.out.println("retrieveAllDepartments");
//
//        Department department = new Department();
//        department.setSystemKey("PROJECT_NAME");
//        department.setKeyValue("ARS");
//        department.setIsActive(true);
//        instance.createDepartment(department);
//        Map<String, String> result = instance.retrieveAllDepartments();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveDepartmentsBasedOnLikeKeySearch method, of class DepartmentServiceTest.
     */
//    @Test
    public void testRetrieveDepartmentsBasedOnLikeKeySearch() {
//        System.out.println("retrieveDepartmentsBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        Department department = new Department();
//        department.setSystemKey("PROJECT_NAME");
//        department.setKeyValue("ARS");
//        department.setIsActive(true);
//        instance.createDepartment(department);
//
//        List result = instance.retrieveDepartmentsBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
