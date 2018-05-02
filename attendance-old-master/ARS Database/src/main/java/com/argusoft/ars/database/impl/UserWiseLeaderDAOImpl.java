/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database.impl;

import com.argusoft.ars.common.database.impl.BaseAbstractGenericDao;
import com.argusoft.ars.database.UserWiseLeaderDAO;
import com.argusoft.ars.model.UserWiseLeader;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author harshit
 */
@Repository
public class UserWiseLeaderDAOImpl extends BaseAbstractGenericDao<UserWiseLeader, Long> implements UserWiseLeaderDAO {

    private static final String USER_ID = "userId";
    private static final String LEADER_ID = "leaderUserId";
    

    @Override
    public List<UserWiseLeader> retrieveUserLeaderList(Long id) {
        List<UserWiseLeader> userWiseLeaders;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(USER_ID, id));
        userWiseLeaders = super.findByCriteriaList(criterions);
        return userWiseLeaders;
    }
    
    @Override
    public List<UserWiseLeader> retrieveUsersByLeaderid(Long id) {
        List<UserWiseLeader> userWiseLeaders;
        List<Criterion> criterions = new LinkedList<Criterion>();
        criterions.add(Restrictions.eq(LEADER_ID, id));
        userWiseLeaders = super.findByCriteriaList(criterions);
        return userWiseLeaders;
    }
    
}
