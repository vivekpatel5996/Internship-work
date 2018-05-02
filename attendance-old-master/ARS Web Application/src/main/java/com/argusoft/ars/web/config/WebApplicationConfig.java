/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.config;

import com.argusoft.ars.core.config.CoreApplicationConfig;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * This is the configuration class for Spring.
 * @author mpritmani
 */
@Configuration
@Import(CoreApplicationConfig.class)
@ImportResource({"classpath:usermanagement-core-context.xml", "/WEB-INF/ars-security-context.xml", "classpath:email-core-context.xml"})
@ComponentScan(basePackages = {"com.argusoft.ars"})
public class WebApplicationConfig {

    //  Core properties
    //  Other properties
    private static final Logger logger = Logger.getLogger(WebApplicationConfig.class);

    /**
     * Method to be called once faces servlet is initialized. It creates the property files for the active languages at deploy time.
     */
//    @PostConstruct
//    public void constructMessageBundle() {
//            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//            String path = servletContext.getRealPath("");
//
//            if (path != null && path.charAt(path.length() - 1) != File.separatorChar) {
//                path = path + File.separator;
//            }
//            path = path + "WEB-INF" + File.separator + "classes" + File.separator;
//
//            String defaultFilename = path + "ValidationMessages.properties";
//            Properties defaultProperties = new Properties();
//            try {
//                File file = new File(defaultFilename);
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//            } catch (IOException ex) {
//                logger.error(ex);
//            }
//
//            try {
//                defaultProperties.store(new FileOutputStream(defaultFilename), null);
//            } catch (IOException ex) {
//                logger.error(ex);
//            }
//
//        
//    }
}
