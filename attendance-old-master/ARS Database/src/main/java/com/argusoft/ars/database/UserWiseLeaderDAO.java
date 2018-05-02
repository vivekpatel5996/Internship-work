/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.UserWiseLeader;
import java.util.List;

/**
 *
 * @author harshit
 */
public interface UserWiseLeaderDAO extends GenericDao<UserWiseLeader, Long>{

    public List<UserWiseLeader> retrieveUserLeaderList(Long id);
    
     public List<UserWiseLeader> retrieveUsersByLeaderid(Long id);
    
}
