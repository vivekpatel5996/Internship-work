/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Harshit
 */

@Configuration
@ComponentScan(basePackages={"com.argusoft.ars.database"})
//@ComponentScan.Filter(type= FilterType.ANNOTATION, value=DatabaseApplicationConfig.class)
@EnableTransactionManagement
@PropertySource({"jdbc.properties"})
public class DatabaseApplicationConfig {

    @Autowired
    Environment env;
    
    @Bean
    public DataSource dataSourceARS() {
        DataSource dataSrc = new DriverManagerDataSource(env.getProperty("jdbc.driverClassName"), env.getProperty("jdbc.url"), env.getProperty("jdbc.username"), env.getProperty("jdbc.password"));
        System.out.println("ds created.........................");
        return dataSrc;
    }
    
    @Bean
    public AnnotationSessionFactoryBean sessionFactoryARS() {
        AnnotationSessionFactoryBean annotationBean = new AnnotationSessionFactoryBean();
        annotationBean.setDataSource(dataSourceARS());
        annotationBean.setSchemaUpdate(true);
        annotationBean.setPackagesToScan(new String[] {"com.argusoft.ars.model"});
        Properties props = new Properties();
        InputStream in = getClass().getResourceAsStream("/hibernate.properties");
        try {
            props.load(in);
        } catch (IOException ex) {
            System.out.println("got error......................");
            Logger.getLogger(DatabaseApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        annotationBean.setHibernateProperties(props);
        System.out.println("sf created........................");
        return annotationBean;
    }
    
    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate ht = new HibernateTemplate(sessionFactoryARS().getObject());
        System.out.println("ht created..........................");
        return ht;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager htm = new HibernateTransactionManager(sessionFactoryARS().getObject());
        System.out.println("htm created...........................");
        return htm;
    }
    
}

