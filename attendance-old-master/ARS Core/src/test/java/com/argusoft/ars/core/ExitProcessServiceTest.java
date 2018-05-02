/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.ExitProcess;
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
public class ExitProcessServiceTest {

    @Resource
    ExitProcessService instance;

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
     * Test of createExitProcess method, of class ExitProcessServiceTest.
     */
    @Test
    public void testCreateExitProcess() {
        System.out.println("createExitProcess");
        ExitProcess exitProcess = new ExitProcess();
        exitProcess.setAdminComment("Admin Comment");
        exitProcess.setAppliedStatus("status");
        exitProcess.setApprovalStatus("ststus");
        exitProcess.setDocName("docName");
        exitProcess.setExitId(1L);
        exitProcess.setExpectedRelieveDate(new Date());
        exitProcess.setReason("comment");
        exitProcess.setRequestedDate(new Date());
        exitProcess.setResponseBy(1L);
        exitProcess.setResponseDate(new Date());
        exitProcess.setUserId(1L);
        instance.createExitProcess(exitProcess);
    }

    /**
     * Test of updateExitProcess method, of class ExitProcessServiceTest.
     */
    @Test
    public void testUpdateExitProcess() {
        System.out.println("updateExitProcess");
        ExitProcess exitProcess = new ExitProcess();
        exitProcess.setAdminComment("Admin Comment");
        exitProcess.setAppliedStatus("status");
        exitProcess.setApprovalStatus("ststus");
        exitProcess.setDocName("docName");
        exitProcess.setExitId(1L);
        exitProcess.setExpectedRelieveDate(new Date());
        exitProcess.setReason("comment");
        exitProcess.setRequestedDate(new Date());
        exitProcess.setResponseBy(1L);
        exitProcess.setResponseDate(new Date());
        exitProcess.setUserId(1L);
        instance.createExitProcess(exitProcess);
//        exitProcess = instance.retrieveExitProcessByKey(1L);
        exitProcess.setExitId(1L);
        instance.updateExitProcess(exitProcess);
    }

    /**
     * Test of retrieveExitProcessByKey method, of class ExitProcessServiceTest.
     */
   @Test
    public void testRetrieveExitProcessByKey() {
        ExitProcess exitProcess = new ExitProcess();
        exitProcess.setAdminComment("Admin Comment");
        exitProcess.setAppliedStatus("status");
        exitProcess.setApprovalStatus("ststus");
        exitProcess.setDocName("docName");
        exitProcess.setExitId(1L);
        exitProcess.setExpectedRelieveDate(new Date());
        exitProcess.setReason("comment");
        exitProcess.setRequestedDate(new Date());
        exitProcess.setResponseBy(1L);
        exitProcess.setResponseDate(new Date());
        exitProcess.setUserId(1L);
        instance.createExitProcess(exitProcess);
        instance.createExitProcess(exitProcess);
        assertNotNull(exitProcess);
    }

    /**
     * Test of retrieveAllExitProcesss method, of class ExitProcessServiceTest.
     */
//    @Test
    public void testRetrieveAllExitProcesss() {
//        System.out.println("retrieveAllExitProcesss");
//
//        ExitProcess exitProcess = new ExitProcess();
//        exitProcess.setSystemKey("PROJECT_NAME");
//        exitProcess.setKeyValue("ARS");
//        exitProcess.setIsActive(true);
//        instance.createExitProcess(exitProcess);
//        Map<String, String> result = instance.retrieveAllExitProcesss();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveExitProcesssBasedOnLikeKeySearch method, of class ExitProcessServiceTest.
     */
//    @Test
    public void testRetrieveExitProcesssBasedOnLikeKeySearch() {
//        System.out.println("retrieveExitProcesssBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        ExitProcess exitProcess = new ExitProcess();
//        exitProcess.setSystemKey("PROJECT_NAME");
//        exitProcess.setKeyValue("ARS");
//        exitProcess.setIsActive(true);
//        instance.createExitProcess(exitProcess);
//
//        List result = instance.retrieveExitProcesssBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
