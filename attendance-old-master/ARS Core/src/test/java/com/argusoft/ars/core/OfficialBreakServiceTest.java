package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.OfficialBreak;
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
public class OfficialBreakServiceTest {
    
    @Resource
    OfficialBreakService instance;
    
    public OfficialBreakServiceTest() {
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
     * Test of createOfficialBreak method, of class OfficialBreakService.
     */
   @Test
    public void testCreateOfficialBreak() {
        System.out.println("createOfficialBreak");
        System.out.println("updateOfficialBreak");
        OfficialBreak officialBreak = new OfficialBreak();
        officialBreak.setType("offical break");
        officialBreak.setFromDate(new Date());
        officialBreak.setIsArchive(true);
        officialBreak.setAppliedDate(new Date());
        officialBreak.setApprovalStatus("pending");
        officialBreak.setOfficialBreakId(1L);
        officialBreak.setUserId(1L);
        //officialBreak.setResponseBy(1L);
        officialBreak.setReason("roobaroo meeting");
        officialBreak.setToDate(new Date());
        officialBreak.setAdminComment("ok");
        officialBreak.setAppliedStatus("applied");
        
        instance.createOfficialBreak(officialBreak);  
    }

    /**
     * Test of updateSystemConfiguration method, of class SystemConfigurationService.
     */
  @Test
   public void testUpdateOfficialBreak() {
        System.out.println("updateOfficialBreak");
        OfficialBreak officialBreak = new OfficialBreak();
        officialBreak.setType("offical break");
        officialBreak.setFromDate(new Date());
        officialBreak.setIsArchive(true);
        officialBreak.setAppliedDate(new Date());
        officialBreak.setApprovalStatus("pending");
        officialBreak.setOfficialBreakId(1L);
        officialBreak.setUserId(1L);
        //officialBreak.setResponseBy(1L);
        officialBreak.setReason("roobaroo meeting");
        officialBreak.setToDate(new Date());
        officialBreak.setAdminComment("ok");
        officialBreak.setAppliedStatus("applied");
        
        instance.createOfficialBreak(officialBreak);
        officialBreak = instance.retrieveOfficialBreakByKey(1L);
        officialBreak.setType("official leave");
        instance.updateOfficialBreak(officialBreak);       
    }

  /**
     * Test of retrieveSystemConfigurationByKey method, of class SystemConfigurationService.
     */
   @Test
  public void testRetrieveOfficialBreakByKey() {
        System.out.println("retrieveOfficialBreakByKey"); 
        OfficialBreak officialBreak = new OfficialBreak();
        officialBreak.setType("offical break");
        officialBreak.setFromDate(new Date());
        officialBreak.setIsArchive(true);
        officialBreak.setAppliedDate(new Date());
        officialBreak.setApprovalStatus("pending");
        officialBreak.setOfficialBreakId(1L);
        officialBreak.setUserId(1L);
        //officialBreak.setResponseBy(1L);
        officialBreak.setReason("roobaroo meeting");
        officialBreak.setToDate(new Date());
        officialBreak.setAdminComment("ok");
        officialBreak.setAppliedStatus("applied");
        instance.createOfficialBreak(officialBreak);
        officialBreak = instance.retrieveOfficialBreakByKey(1L);                
        assertNotNull(officialBreak);
  }

    /**
     * Test of retrieveAllSystemConfigurations method, of class SystemConfigurationService.
     */
//      @Test
       public void testRetrieveAllSystemConfigurations() {
//        System.out.println("retrieveAllSystemConfigurations");
//        OfficialBreak officialBreak = new OfficialBreak();
//        UsermanagementSystemUser us = new UsermanagementSystemUser();
//        us.setId(1L);
//        officialBreak.setOfficialBreakName("Morning1");
//        officialBreak.setCreatedDate(new Date());
//        officialBreak.setIsArchive(true);
//        officialBreak.setIsActive(true);
//        officialBreak.setOfficialBreakEndTime(new Date());
//        officialBreak.setOfficialBreakStartTime(new Date());
//        officialBreak.setCreatedBy(us);
//        officialBreak.setOfficialBreakId(3L);
//        instance.createOfficialBreak(officialBreak);   
//        Map<String, String> result = instance.retrieveAllOfficialBreak();
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