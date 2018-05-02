 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.util;

import com.argusoft.ars.eproctortoars.EproctorDBToARSDBServiceIpl;
import com.argusoft.usermanagement.common.core.SystemConfigurationService;
import com.argusoft.usermanagement.common.model.SystemConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author hshah
 */
@Service("croneService")
public class CroneService {

    @Autowired
    SystemConfigurationService systemConfigurationService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void method() throws ParseException {
//        System.out.println("Crone Call");
//        SystemConfiguration systemConfiguration = systemConfigurationService.retrieveSystemConfigurationByKey("LAST_CARDLOG_DONE");
//        EproctorDBToARSDBServiceIpl eproctorDBToARSDBServiceIpl = new EproctorDBToARSDBServiceIpl();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date fromDate = simpleDateFormat.parse(systemConfiguration.getKeyValue());
//        fromDate = getDateAfterNoOfMiniutes(fromDate, -1);
//
//        Date toDate = new Date();
//        System.out.println("From Date :" + fromDate);
//        System.out.println("To Date :" + toDate);
//        toDate = eproctorDBToARSDBServiceIpl.transferCardLogData(fromDate);
////        if (systemConfiguration == null) {
////            systemConfiguration = new SystemConfiguration();
////            systemConfiguration.setSystemKey("LAST_CARDLOG_DONE");
////            systemConfiguration.setIsActive(Boolean.TRUE);
////        }
//        if (toDate != null) {
//            System.out.println("===== To date :" + toDate);
//            systemConfiguration.setKeyValue(simpleDateFormat.format(toDate));
//            systemConfigurationService.updateSystemConfiguration(systemConfiguration);
//        }
    }

    public static Date getDateAfterNoOfMiniutes(Date date, int noOfMiniute) {
        Calendar dateCal = Calendar.getInstance();
        if (date != null) {
            dateCal.setTime(date);
            dateCal.set(Calendar.MINUTE, dateCal.get(Calendar.MINUTE) + noOfMiniute);
        }
        return dateCal.getTime();
    }
}
