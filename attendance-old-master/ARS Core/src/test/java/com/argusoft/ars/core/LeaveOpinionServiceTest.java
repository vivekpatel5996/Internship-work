/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.Leave;
import com.argusoft.ars.model.LeaveOpinion;
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
public class LeaveOpinionServiceTest {

    @Resource
    LeaveOpinionService instance;

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
     * Test of createLeaveOpinion method, of class LeaveOpinionServiceTest.
     */
    @Test
    public void testCreateLeaveOpinion() {
        System.out.println("createLeaveOpinion");
        Leave l=new Leave();
        l.setLeaveId(1L);
        LeaveOpinion leaveOpinion = new LeaveOpinion();
        leaveOpinion.setIsArchive(true);
        leaveOpinion.setLeaveOpinionId(1L);
//        leaveOpinion.setLeaveId(l);
        leaveOpinion.setUserId(1L);
        leaveOpinion.setOpinion("opinion");
        leaveOpinion.setOpinionDate(new Date());
        
        instance.createLeaveOpinion(leaveOpinion);
    }

    /**
     * Test of updateLeaveOpinion method, of class LeaveOpinionServiceTest.
     */
    @Test
    public void testUpdateLeaveOpinion() {
        System.out.println("updateLeaveOpinion");
        Leave l=new Leave();
        l.setLeaveId(1L);
        LeaveOpinion leaveOpinion = new LeaveOpinion();
        leaveOpinion.setIsArchive(true);
        leaveOpinion.setLeaveOpinionId(1L);
//        leaveOpinion.setLeaveId(l);
        leaveOpinion.setUserId(1L);
        leaveOpinion.setOpinion("opinion");
        leaveOpinion.setOpinionDate(new Date());
        instance.createLeaveOpinion(leaveOpinion);
//        leaveOpinion = instance.retrieveLeaveOpinionByKey(1L);
        leaveOpinion.setLeaveOpinionId(3L);
        instance.updateLeaveOpinion(leaveOpinion);
    }

    /**
     * Test of retrieveLeaveOpinionByKey method, of class LeaveOpinionServiceTest.
     */
   @Test
    public void testRetrieveLeaveOpinionByKey() {
        Leave l=new Leave();
        l.setLeaveId(1L);
        LeaveOpinion leaveOpinion = new LeaveOpinion();
        leaveOpinion.setIsArchive(true);
        leaveOpinion.setLeaveOpinionId(1L);
//        leaveOpinion.setLeaveId(l);
        leaveOpinion.setUserId(1L);
        leaveOpinion.setOpinion("opinion");
        leaveOpinion.setOpinionDate(new Date());
        instance.createLeaveOpinion(leaveOpinion);
//        leaveOpinion = instance.retrieveLeaveOpinionByKey(1L);
        assertNotNull(leaveOpinion);
    }

    /**
     * Test of retrieveAllLeaveOpinions method, of class LeaveOpinionServiceTest.
     */
//    @Test
    public void testRetrieveAllLeaveOpinions() {
//        System.out.println("retrieveAllLeaveOpinions");
//
//        LeaveOpinion leaveOpinion = new LeaveOpinion();
//        leaveOpinion.setSystemKey("PROJECT_NAME");
//        leaveOpinion.setKeyValue("ARS");
//        leaveOpinion.setIsActive(true);
//        instance.createLeaveOpinion(leaveOpinion);
//        Map<String, String> result = instance.retrieveAllLeaveOpinions();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveLeaveOpinionsBasedOnLikeKeySearch method, of class LeaveOpinionServiceTest.
     */
//    @Test
    public void testRetrieveLeaveOpinionsBasedOnLikeKeySearch() {
//        System.out.println("retrieveLeaveOpinionsBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        LeaveOpinion leaveOpinion = new LeaveOpinion();
//        leaveOpinion.setSystemKey("PROJECT_NAME");
//        leaveOpinion.setKeyValue("ARS");
//        leaveOpinion.setIsActive(true);
//        instance.createLeaveOpinion(leaveOpinion);
//
//        List result = instance.retrieveLeaveOpinionsBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
