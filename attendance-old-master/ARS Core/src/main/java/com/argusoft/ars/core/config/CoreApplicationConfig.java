/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.config;

import com.argusoft.ars.database.config.DatabaseApplicationConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 *
 * @author Harshit
 */
@Configuration
@ComponentScan(basePackages={"com.argusoft.ars.core"})
//@ComponentScan.Filter(type= FilterType.ANNOTATION, value=CoreApplicationConfig.class)
@Import(DatabaseApplicationConfig.class)
//@ImportResource("classpath:cache-context.xml")
public class CoreApplicationConfig {
    
}
