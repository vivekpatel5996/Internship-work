/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.Designation;
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
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DesignationServiceTest {

    @Resource
    DesignationService instance;

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
     * Test of createDesignation method, of class DesignationServiceTest.
     */
    @Test
    public void testCreateDesignation() {
        System.out.println("createDesignation");
        Designation designation = new Designation();
//        designation.setDesId(1L);
        designation.setCreatedBy(1L);
        designation.setDesName("Testing");
        designation.setIsActive(true);
        designation.setIsArchive(true);
        designation.setCreatedDate(new Date());
        instance.createDesignation(designation);
    }

    /**
     * Test of updateDesignation method, of class DesignationServiceTest.
     */
    @Test
    public void testUpdateDesignation() {
        System.out.println("updateDesignation");
       
        Designation designation;
        designation = instance.retrieveDesignationById(5L);
        designation.setDesName("agshag");
        instance.updateDesignation(designation);
    }

    /**
     * Test of retrieveDesignationByKey method, of class DesignationServiceTest.
     */
//   @Test
    public void testRetrieveDesignationByKey() {
        System.out.println("retrieveDesignationByKey");
        Designation designation = new Designation();
        instance.createDesignation(designation);
        designation = instance.retrieveDesignationById(6L);
        assertNotNull(designation);
    }

    /**
     * Test of retrieveAllDesignations method, of class DesignationServiceTest.
     */
//    @Test
    public void testRetrieveAllDesignations() {
//        System.out.println("retrieveAllDesignations");
//
//        Designation designation = new Designation();
//        designation.setSystemKey("PROJECT_NAME");
//        designation.setKeyValue("ARS");
//        designation.setIsActive(true);
//        instance.createDesignation(designation);
//        Map<String, String> result = instance.retrieveAllDesignations();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveDesignationsBasedOnLikeKeySearch method, of class DesignationServiceTest.
     */
//    @Test
    public void testRetrieveDesignationsBasedOnLikeKeySearch() {
//        System.out.println("retrieveDesignationsBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        Designation designation = new Designation();
//        designation.setSystemKey("PROJECT_NAME");
//        designation.setKeyValue("ARS");
//        designation.setIsActive(true);
//        instance.createDesignation(designation);
//
//        List result = instance.retrieveDesignationsBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
