/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import com.argusoft.ars.model.EmailFormat;
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
public class EmailFormatServiceTest {

    @Resource
    EmailFormatService instance;

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
     * Test of createEmailFormat method, of class EmailFormatServiceTest.
     */
    @Test
    public void testCreateEmailFormat() {
        System.out.println("createEmailFormat");
        EmailFormat emailFormat = new EmailFormat();
        emailFormat.setCreatedBy(1L);
        emailFormat.setBody("Email Body");
        emailFormat.setEmailFormatId(1L);
        emailFormat.setEmailFormatName("Formate name");
        emailFormat.setSubject("email");
        emailFormat.setIsActive(true);
        emailFormat.setIsArchive(true);
        emailFormat.setCreatedDate(new Date());
        emailFormat.setBcc("bcc");
        emailFormat.setCc("cc");
        emailFormat.setFooter("footer");
        instance.createEmailFormat(emailFormat);
    }

    /**
     * Test of updateEmailFormat method, of class EmailFormatServiceTest.
     */
//    @Test
    public void testUpdateEmailFormat() {
        System.out.println("updateEmailFormat");
        EmailFormat emailFormat;
        emailFormat = instance.retrieveEmailFormatByKey(1L);
        emailFormat.setEmailFormatId(3L);
        instance.updateEmailFormat(emailFormat);
    }

    /**
     * Test of retrieveEmailFormatByKey method, of class EmailFormatServiceTest.
     */
//   @Test
    public void testRetrieveEmailFormatByKey() {
        System.out.println("retrieveEmailFormatByKey");
        EmailFormat  emailFormat;
        emailFormat = instance.retrieveEmailFormatByKey(1L);
        assertNotNull(emailFormat);
    }

    /**
     * Test of retrieveAllEmailFormats method, of class EmailFormatServiceTest.
     */
//    @Test
    public void testRetrieveAllEmailFormats() {
//        System.out.println("retrieveAllEmailFormats");
//
//        EmailFormat emailFormat = new EmailFormat();
//        emailFormat.setSystemKey("PROJECT_NAME");
//        emailFormat.setKeyValue("ARS");
//        emailFormat.setIsActive(true);
//        instance.createEmailFormat(emailFormat);
//        Map<String, String> result = instance.retrieveAllEmailFormats();
//        for (String str : result.keySet()) {
//            System.out.println("--> " + str);
//        }
//        assertNotNull(result);
    }

    /**
     * Test of retrieveEmailFormatsBasedOnLikeKeySearch method, of class EmailFormatServiceTest.
     */
//    @Test
    public void testRetrieveEmailFormatsBasedOnLikeKeySearch() {
//        System.out.println("retrieveEmailFormatsBasedOnLikeKeySearch");
//        String startsWithString = "PROJECT_NAME";
//
//        EmailFormat emailFormat = new EmailFormat();
//        emailFormat.setSystemKey("PROJECT_NAME");
//        emailFormat.setKeyValue("ARS");
//        emailFormat.setIsActive(true);
//        instance.createEmailFormat(emailFormat);
//
//        List result = instance.retrieveEmailFormatsBasedOnLikeKeySearch(startsWithString);
//        assertNotNull(result);
    }
}
