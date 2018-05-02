/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.CardLog;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class CardLogServiceTest {
    
    @Resource
    CardLogService instance;
    
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
     * Test of createCardLog method, of class CardLogServiceTest.
     */
//    @Test
    public void testCreateCardLog() {
        System.out.println("createCardLog");
        CardLog cardLog = new CardLog();
//        cardLog.setCardLogId(1L);
        cardLog.setCardEnrollNumber(10L);
        cardLog.setCardEntryReason("exit");
        cardLog.setCardPunchingTime(new Date());
        cardLog.setUserId(1L);
        instance.createCardLog(cardLog);
    }

    /**
     * Test of updateCardLog method, of class CardLogServiceTest.
     */
//    @Test
    public void testUpdateCardLog() {
        System.out.println("updateCardLog");
        CardLog cardLog = new CardLog();
        cardLog.setCardLogId(1L);
//        cardLog.setCardEnrollNumber(10);
        cardLog.setCardEntryReason("exit");
        cardLog.setCardPunchingTime(new Date());
        cardLog.setUserId(1L);
        instance.createCardLog(cardLog);
        cardLog = instance.retrieveCardLogByKey(1L);
        cardLog.setCardLogId(1L);
        instance.updateCardLog(cardLog);
    }

    /**
     * Test of retrieveCardLogByKey method, of class CardLogServiceTest.
     */
//   @Test
    public void testRetrieveCardLogByKey() {
        System.out.println("retrieveCardLogByKey");
        CardLog cardLog = new CardLog();
        cardLog.setCardLogId(1L);
//        cardLog.setCardEnrollNumber(10);
        cardLog.setCardEntryReason("exit");
        cardLog.setCardPunchingTime(new Date());
        cardLog.setUserId(1L);
        instance.createCardLog(cardLog);
        cardLog = instance.retrieveCardLogByKey(1L);
        assertNotNull(cardLog);
    }

    /**
     * Test of retrieveAllCardLogs method, of class CardLogServiceTest.
     */
//    @Test
    public void testRetrieveAllCardLogs() {
//        System.out.println("retrieveAllCardLogs");
//
//        CardLog cardLog = new CardLog();
//        cardLog.setSystemKey("PROJECT_NAME");
//        cardLog.setKeyValue("ARS");
//        cardLog.setIsActive(true);
//        instance.createCardLog(cardLog);
//        Map<String, String> result = instance.retrieveAllCardLogs();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveCardLogsBasedOnLikeKeySearch method, of class CardLogServiceTest.
     */
//    @Test
    public void testRetrieveCardLogsBasedOnLikeKeySearch() {
//        System.out.println("retrieveCardLogsBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        CardLog cardLog = new CardLog();
//        cardLog.setSystemKey("PROJECT_NAME");
//        cardLog.setKeyValue("ARS");
//        cardLog.setIsActive(true);
//        instance.createCardLog(cardLog);
//
//        List result = instance.retrieveCardLogsBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
    @Test
    public void testRetrieveFistDayOfThePunching() {
        Long id = 10L;
        Calendar c = Calendar.getInstance();
        Date toDate = c.getTime();
        
        List<CardLog> cardLogs = instance.retrieveFirstPunchingTimeBetweenDate(id, null, null, Boolean.TRUE);
        for (CardLog cardLog : cardLogs) {
            System.out.println(cardLog);
        }
        System.out.println("=================================End ===================");
        
    }
}
