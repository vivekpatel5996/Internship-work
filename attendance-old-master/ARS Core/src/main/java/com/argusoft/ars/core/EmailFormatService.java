/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.EmailFormat;
import java.util.List;

/**
 *
 * @author smetaliya
 */
public interface EmailFormatService {

    public EmailFormat retrieveEmailFormatById(Long emailId);

    public void createEmailFormat(EmailFormat emailFormat);

    public void updateEmailFormat(EmailFormat emailFormat);

    public EmailFormat retrieveEmailFormatByKey(Long key);

    public List<EmailFormat> retrieveAllEmailFormat();

    public EmailFormat retriveEmailByFormateName(String formateName);

    public boolean isFormatNameAvailable(String formatName);
}