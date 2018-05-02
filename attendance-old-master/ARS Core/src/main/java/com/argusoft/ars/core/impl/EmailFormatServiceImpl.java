/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.EmailFormatService;
import com.argusoft.ars.database.EmailFormatDao;
import com.argusoft.ars.model.EmailFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("emailFormatService")
public class EmailFormatServiceImpl implements EmailFormatService {

    @Autowired
    private EmailFormatDao emailFormatDao;

    @Override
    public void createEmailFormat(EmailFormat emailFormat) {
        emailFormatDao.update(emailFormat);
    }
    @Override
    public void updateEmailFormat(EmailFormat emailFormat) {
        emailFormatDao.update(emailFormat);
    }

    @Override
    public EmailFormat retrieveEmailFormatByKey(Long key) {
        EmailFormat emailFormat = null;
        if (key != null) {
            emailFormat = emailFormatDao.retrieveById(key);
        }
        return emailFormat;
    }

//    @Override
//    public Map<String, String> retrieveAllEmailFormat() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
    @Override
    public List<EmailFormat> retrieveAllEmailFormat() {
        return emailFormatDao.retrieveAllEmailFormat();
    }

    @Override
    public EmailFormat retriveEmailByFormateName(String formateName) {
        List<EmailFormat> emailFormatList = emailFormatDao.retrieveEmailFormatByFormateName(formateName);
        if (emailFormatList != null && !emailFormatList.isEmpty()) {
            return emailFormatList.get(0);
        }
        return null;
    }

    @Override
    public boolean isFormatNameAvailable(String formatName) {
        if (emailFormatDao.retrieveEmailFormatByFormateName(formatName) != null && emailFormatDao.retrieveEmailFormatByFormateName(formatName).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EmailFormat retrieveEmailFormatById(Long emailId) {
        EmailFormat emailFormat = null;
        if (emailId != null) {
            emailFormat = emailFormatDao.retrieveById(emailId);
        }
        return emailFormat;
    }
}