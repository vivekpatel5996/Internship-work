package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.ManualCardEntry;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.*;
import static org.junit.Assert.assertNotNull;
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
public class ManualCardEntryServiceTest {
    
    @Resource
    ManualCardEntryService instance;
    
    public ManualCardEntryServiceTest() {
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
     * Test of createManualCardEntry method, of class ManualCardEntryService.
     */
   @Test
    public void testCreateManualCardEntry() {
        System.out.println("createManualCardEntry");
        ManualCardEntry manualCardEntry = new ManualCardEntry();
        manualCardEntry.setDate(new Date());
        manualCardEntry.setAppliedDate(new Date());
        manualCardEntry.setEntryId(1L);
        manualCardEntry.setIsArchive(true);
        manualCardEntry.setInStatus(true);
        manualCardEntry.setOutStatus(true);
        manualCardEntry.setApprovalStatus("pending");
        manualCardEntry.setUserId(1L);
        manualCardEntry.setResponseBy(1L);
        manualCardEntry.setReason("lost card");
        manualCardEntry.setAdminComment("ok");
        manualCardEntry.setAppliedStatus("applied");
        
//        instance.createManualCardEntry(manualCardEntry);  
    }

    /**
     * Test of updateSystemConfiguration method, of class SystemConfigurationService.
     */
  @Test
   public void testUpdateManualCardEntry() {
        System.out.println("updateManualCardEntry");
        ManualCardEntry manualCardEntry = new ManualCardEntry();
        manualCardEntry.setDate(new Date());
        manualCardEntry.setAppliedDate(new Date());
        manualCardEntry.setEntryId(1L);
        manualCardEntry.setIsArchive(true);
        manualCardEntry.setInStatus(true);
        manualCardEntry.setOutStatus(true);
        manualCardEntry.setApprovalStatus("pending");
        manualCardEntry.setUserId(1L);
        manualCardEntry.setResponseBy(1L);
        manualCardEntry.setReason("roobaroo meeting");
        manualCardEntry.setAdminComment("ok");
        manualCardEntry.setAppliedStatus("applied");
        
//        instance.createManualCardEntry(manualCardEntry);  
//        manualCardEntry = instance.retrieveManualCardEntryByKey(1L);
        manualCardEntry.setApprovalStatus("approved");
        instance.updateManualCardEntry(manualCardEntry);       
    }

  /**
     * Test of retrieveSystemConfigurationByKey method, of class SystemConfigurationService.
     */
   @Test
  public void testRetrieveManualCardEntryByKey() {
        System.out.println("retrieveManualCardEntryByKey"); 
        ManualCardEntry manualCardEntry = new ManualCardEntry();
        manualCardEntry.setDate(new Date());
        manualCardEntry.setAppliedDate(new Date());
        manualCardEntry.setEntryId(1L);
        manualCardEntry.setIsArchive(true);
        manualCardEntry.setInStatus(true);
        manualCardEntry.setOutStatus(true);
        manualCardEntry.setApprovalStatus("pending");
        manualCardEntry.setUserId(1L);
        manualCardEntry.setResponseBy(1L);
        manualCardEntry.setReason("roobaroo meeting");
        manualCardEntry.setAdminComment("ok");
        manualCardEntry.setAppliedStatus("applied");
        
//        instance.createManualCardEntry(manualCardEntry);
//        manualCardEntry = instance.retrieveManualCardEntryByKey(1L);                
        assertNotNull(manualCardEntry);
  }

    /**
     * Test of retrieveAllSystemConfigurations method, of class SystemConfigurationService.
     */
//      @Test
       public void testRetrieveAllSystemConfigurations() {
//        System.out.println("retrieveAllSystemConfigurations");
//        ManualCardEntry manualCardEntry = new ManualCardEntry();
//        UsermanagementSystemUser us = new UsermanagementSystemUser();
//        us.setId(1L);
//        manualCardEntry.setManualCardEntryName("Morning1");
//        manualCardEntry.setCreatedDate(new Date());
//        manualCardEntry.setIsArchive(true);
//        manualCardEntry.setIsActive(true);
//        manualCardEntry.setManualCardEntryEndTime(new Date());
//        manualCardEntry.setManualCardEntryStartTime(new Date());
//        manualCardEntry.setCreatedBy(us);
//        manualCardEntry.setManualCardEntryId(3L);
//        instance.createManualCardEntry(manualCardEntry);   
//        Map<String, String> result = instance.retrieveAllManualCardEntry();
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