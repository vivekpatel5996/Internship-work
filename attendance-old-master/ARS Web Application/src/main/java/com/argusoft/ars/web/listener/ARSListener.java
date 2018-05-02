/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.DefaultResourceLoader;
/**
 *
 * @author Harshit
 */
public class ARSListener implements ServletContextListener {
     private static final Logger log = Logger.getLogger(ARSListener.class);

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        configureLog4j("classpath:log4j_default.properties");
        log.info("---------------------------------------------------------\n");
        log.info("............Telemed app deployed successfully ...........\n");
        log.info("---------------------------------------------------------\n");

    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //TODO  perform infra stuff
    }

    public static void configureLog4j(String filePath) {
        try {
            if (filePath.contains("classpath")) {
                DefaultResourceLoader drl = new DefaultResourceLoader();
                PropertyConfigurator.configure(drl.getResource(filePath).getURL());
            } else {
                PropertyConfigurator.configureAndWatch(filePath, 1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to configure logging properly.",e);
        }
    }
}
