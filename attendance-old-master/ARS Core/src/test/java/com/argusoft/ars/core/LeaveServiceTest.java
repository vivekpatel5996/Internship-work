/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.Leave;
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
public class LeaveServiceTest {
    
    @Resource
    LeaveService instance;
    
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
     * Test of createLeave method, of class LeaveServiceTest.
     */
    @Test
    public void testCreateLeave() {
        System.out.println("createLeave");
        Leave leave = new Leave();
        leave.setIsArchive(true);
        leave.setLeaveId(1L);
        leave.setAdminComment("Admin Comment");
        leave.setAppliedDate(new Date());
        leave.setAppliedStatus("status");
        leave.setApprovalStatus("status");
        leave.setFromDate(new Date());
        leave.setFromDateLeaveType("from");
        leave.setLeaveSubject("subject");
        leave.setLeaveType("type");
        leave.setReason("reason");
        leave.setResponseBy(1L);
        leave.setResponseDate(new Date());
        leave.setToDateLeaveType("type");
        leave.setToDate(new Date());
        leave.setUserId(1L);
        leave.setUserId(1L);
        instance.createLeave(leave);
    }

    /**
     * Test of updateLeave method, of class LeaveServiceTest.
     */
    @Test
    public void testUpdateLeave() {
        System.out.println("updateLeave");
        Leave leave = new Leave();
        leave.setIsArchive(true);
        leave.setLeaveId(1L);
        leave.setAdminComment("Admin Comment");
        leave.setAppliedDate(new Date());
        leave.setAppliedStatus("status");
        leave.setApprovalStatus("status");
        leave.setFromDate(new Date());
        leave.setFromDateLeaveType("from");
        leave.setLeaveSubject("subject");
        leave.setLeaveType("type");
        leave.setReason("reason");
        leave.setResponseBy(1L);
        leave.setResponseDate(new Date());
        leave.setToDateLeaveType("type");
        leave.setToDate(new Date());
        leave.setUserId(1L);
        leave.setUserId(1L);
        instance.createLeave(leave);
        leave = instance.retrieveLeaveById(1L);
        leave.setLeaveId(3L);
        instance.updateLeave(leave);
    }

    /**
     * Test of retrieveLeaveByKey method, of class LeaveServiceTest.
     */
    @Test
    public void testRetrieveLeaveByKey() {
        Leave leave = new Leave();
        leave.setIsArchive(true);
        leave.setLeaveId(1L);
        leave.setAdminComment("Admin Comment");
        leave.setAppliedDate(new Date());
        leave.setAppliedStatus("status");
        leave.setApprovalStatus("status");
        leave.setFromDate(new Date());
        leave.setFromDateLeaveType("from");
        leave.setLeaveSubject("subject");
        leave.setLeaveType("type");
        leave.setReason("reason");
        leave.setResponseBy(1L);
        leave.setResponseDate(new Date());
        leave.setToDateLeaveType("type");
        leave.setToDate(new Date());
        leave.setUserId(1L);
        leave.setUserId(1L);
        instance.createLeave(leave);
        leave = instance.retrieveLeaveById(1L);
        assertNotNull(leave);
    }

    /**
     * Test of retrieveAllLeaves method, of class LeaveServiceTest.
     */
//    @Test
    public void testRetrieveAllLeaves() {
//        System.out.println("retrieveAllLeaves");
//
//        Leave leave = new Leave();
//        leave.setSystemKey("PROJECT_NAME");
//        leave.setKeyValue("ARS");
//        leave.setIsActive(true);
//        instance.createLeave(leave);
//        Map<String, String> result = instance.retrieveAllLeaves();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveLeavesBasedOnLikeKeySearch method, of class LeaveServiceTest.
     */
//    @Test
    public void testRetrieveLeavesBasedOnLikeKeySearch() {
//        System.out.println("retrieveLeavesBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        Leave leave = new Leave();
//        leave.setSystemKey("PROJECT_NAME");
//        leave.setKeyValue("ARS");
//        leave.setIsActive(true);
//        instance.createLeave(leave);
//
//        List result = instance.retrieveLeavesBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
