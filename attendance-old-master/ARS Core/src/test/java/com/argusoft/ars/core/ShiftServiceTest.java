package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.Shift;
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
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={CoreApplicationConfig.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ShiftServiceTest {
    
    @Resource
    ShiftService instance;
    
    public ShiftServiceTest() {
    }

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
     * Test of createShift method, of class ShiftService.
     */
    @Test
    public void testCreateShift() {
        System.out.println("createShift");
        Shift shift = new Shift();
        shift.setShiftName("Morning");
        shift.setCreatedDate(new Date());
        shift.setIsArchive(true);
        shift.setIsActive(true);
        shift.setShiftEndTime(new Date()); 
        shift.setShiftStartTime(new Date());
        shift.setCreatedBy(1L);
        shift.setShiftId(1L);
        instance.createShift(shift);        
    }

    /**
     * Test of updateSystemConfiguration method, of class SystemConfigurationService.
     */
    @Test
   public void testUpdateShift() {
        System.out.println("updateShift");
        Shift shift = new Shift();
        shift.setShiftName("Morning");
        shift.setCreatedDate(new Date());
        shift.setIsArchive(true);
        shift.setIsActive(true);
        shift.setShiftEndTime(new Date());
        shift.setShiftStartTime(new Date());
        shift.setCreatedBy(1L);
        shift.setShiftId(1L);
        instance.createShift(shift);
        shift = instance.retrieveShiftById(1L);
        shift.setShiftName("Morning12");
        instance.updateShift(shift);       
    }

  /**
     * Test of retrieveSystemConfigurationByKey method, of class SystemConfigurationService.
     */
//    @Test
  public void testRetrieveShiftById() {
        System.out.println("retrieveShiftByKey");        
        Shift shift = new Shift();
        shift.setShiftName("Morning1");
        shift.setCreatedDate(new Date());
        shift.setIsArchive(true);
        shift.setIsActive(true);
        shift.setShiftEndTime(new Date());
        shift.setShiftStartTime(new Date());
        shift.setCreatedBy(1L);
//        shift.setShiftId(3L);
        instance.createShift(shift);
        shift = instance.retrieveShiftById(3L);                
        assertNotNull(shift);
  }

    /**
     * Test of retrieveAllSystemConfigurations method, of class SystemConfigurationService.
     */
//      @Test
       public void testRetrieveAllSystemConfigurations() {
//        System.out.println("retrieveAllSystemConfigurations");
//        Shift shift = new Shift();
//        UsermanagementSystemUser us = new UsermanagementSystemUser();
//        us.setId(1L);
//        shift.setShiftName("Morning1");
//        shift.setCreatedDate(new Date());
//        shift.setIsArchive(true);
//        shift.setIsActive(true);
//        shift.setShiftEndTime(new Date());
//        shift.setShiftStartTime(new Date());
//        shift.setCreatedBy(us);
//        shift.setShiftId(3L);
//        instance.createShift(shift);   
//        Map<String, String> result = instance.retrieveAllShift();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);        
    }

    /**
     * Test of retrieveSystemConfigurationsBasedOnLikeKeySearch method, of class SystemConfigurationService.
     */
//    @Test
  public void testRetrieveSystemConfigurationsBasedOnLikeKeySearch() {
//        System.out.println("retrieveSystemConfigurationsBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";        
//        
//        SystemConfiguration systemConfiguration = new SystemConfiguration();
//        systemConfiguration.setSystemKey("PROJECT_NAME");
//        systemConfiguration.setKeyValue("ARS");
//        systemConfiguration.setIsActive(true);
//        instance.createSystemConfiguration(systemConfiguration); 
//        
//        List result = instance.retrieveSystemConfigurationsBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);        
   }    
}