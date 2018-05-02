/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.Holiday;
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
public class HolidayServiceTest {

    @Resource
    HolidayService instance;

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
     * Test of createHoliday method, of class HolidayServiceTest.
     */
    @Test
    public void testCreateHoliday() {
        System.out.println("createHoliday");
        Holiday holiday = new Holiday();
        holiday.setCreatedBy(1L);
        holiday.setHolidayName("Formate name");
        holiday.setCreatedDate(new Date());
        holiday.setType("Casual");
        holiday.setHolidayDate(new Date());
        holiday.setIsActive(true);
        holiday.setIsArchive(false);
        instance.createHoliday(holiday);
    }

    /**
     * Test of updateHoliday method, of class HolidayServiceTest.
     */
//    @Test
    public void testUpdateHoliday() {
        System.out.println("updateHoliday");
        Holiday holiday = new Holiday();
        holiday.setCreatedBy(1L);
        holiday.setHolidayId(1L);
        holiday.setHolidayName("Formate name");
        holiday.setCreatedDate(new Date());
        holiday.setType("Casual");
        holiday.setHolidayDate(new Date());
        instance.createHoliday(holiday);
        holiday = instance.retrieveHolidayById(1L);
        holiday.setHolidayId(3L);
        instance.updateHoliday(holiday);
    }

    /**
     * Test of retrieveHolidayByKey method, of class HolidayServiceTest.
     */
//   @Test
    public void testRetrieveHolidayByKey() {
        System.out.println("retrieveHolidayByKey");
        Holiday holiday = new Holiday();
        holiday.setCreatedBy(1L);
        holiday.setHolidayId(1L);
        holiday.setHolidayName("Formate name");
        holiday.setCreatedDate(new Date());
        holiday.setType("Casual");
        holiday.setHolidayDate(new Date());
        instance.createHoliday(holiday);
        holiday = instance.retrieveHolidayById(1L);
        assertNotNull(holiday);
    }

    /**
     * Test of retrieveAllHolidays method, of class HolidayServiceTest.
     */
//    @Test
    public void testRetrieveAllHolidays() {
//        System.out.println("retrieveAllHolidays");
//
//        Holiday holiday = new Holiday();
//        holiday.setSystemKey("PROJECT_NAME");
//        holiday.setKeyValue("ARS");
//        holiday.setIsActive(true);
//        instance.createHoliday(holiday);
//        Map<String, String> result = instance.retrieveAllHolidays();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveHolidaysBasedOnLikeKeySearch method, of class HolidayServiceTest.
     */
//    @Test
    public void testRetrieveHolidaysBasedOnLikeKeySearch() {
//        System.out.println("retrieveHolidaysBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        Holiday holiday = new Holiday();
//        holiday.setSystemKey("PROJECT_NAME");
//        holiday.setKeyValue("ARS");
//        holiday.setIsActive(true);
//        instance.createHoliday(holiday);
//
//        List result = instance.retrieveHolidaysBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
