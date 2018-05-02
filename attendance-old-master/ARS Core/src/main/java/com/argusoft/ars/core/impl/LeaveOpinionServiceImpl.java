/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.LeaveOpinionService;
import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.database.LeaveOpinionDao;
import com.argusoft.ars.model.LeaveOpinion;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("leaveOpinionService")
public class LeaveOpinionServiceImpl implements LeaveOpinionService {

    @Autowired
    private LeaveOpinionDao leaveOpinionDao;
    @Autowired
    private SystemUserDetailService systemUserDetailService;

    @Override
    public void createLeaveOpinion(LeaveOpinion leaveOpinion) {
        leaveOpinionDao.update(leaveOpinion);
    }

    @Override
    @CacheEvict(value = "systemCache", key = "#leaveOpinion.leaveOpinionId")
    public void updateLeaveOpinion(LeaveOpinion leaveOpinion) {
        leaveOpinionDao.update(leaveOpinion);
    }

    @Override
    public LeaveOpinion retrieveLeaveOpinionById(Long id) {
        LeaveOpinion leaveOpinion = null;
        if (id != null) {
            leaveOpinion = leaveOpinionDao.retrieveById(id);
        }
        return leaveOpinion;
    }

    @Override
    public List<Long> sendLeaveOpinionNotification(Long userId, Long leaveId, Long[] leaveOpinionUserIdList) {
        List<Long> listOfUserIdForSendMail = new ArrayList<Long>();
        if (leaveOpinionUserIdList != null && leaveOpinionUserIdList.length > 0) {
            for (Long leaveOpinionUserId : leaveOpinionUserIdList) {
                LeaveOpinion leaveOpinion = new LeaveOpinion();
                leaveOpinion.setUserId(leaveOpinionUserId);
                leaveOpinion.setLeaveId(leaveId);
                leaveOpinion.setIsArchive(false);
                leaveOpinion.setIsNotificationShow(false);
                leaveOpinion.setIsSelectedByUser(true);
                this.createLeaveOpinion(leaveOpinion);
                listOfUserIdForSendMail.add(leaveOpinionUserId);
            }
        }
        Long[] otherleaveOpinionList = systemUserDetailService.retrieveLeaveOpinionUserListByUserId(leaveOpinionUserIdList);
        if (otherleaveOpinionList != null && otherleaveOpinionList.length != 0) {
            for (Long leaveOpinionUserId : otherleaveOpinionList) {
                if (leaveOpinionUserId != userId) {
                    LeaveOpinion leaveOpinion = new LeaveOpinion();
                    leaveOpinion.setUserId(leaveOpinionUserId);
                    leaveOpinion.setLeaveId(leaveId);
                    leaveOpinion.setIsArchive(false);
                    leaveOpinion.setIsNotificationShow(false);
                    this.createLeaveOpinion(leaveOpinion);
                    leaveOpinion.setIsSelectedByUser(false);
                    listOfUserIdForSendMail.add(leaveOpinionUserId);
                }
            }
        }
        return listOfUserIdForSendMail;
    }

    @Override
    public List<LeaveOpinion> retrieveLeaveOpinionListByLeaveId(Long leaveId) {
        return leaveOpinionDao.retrieveLeaveOpinionListByLeaveId(leaveId);
    }

    @Override
    public Long retrieveLeaveOpinionIdByLeaveIdAndUserId(Long id, Long leaveId) {
        LeaveOpinion leaveOpinion = leaveOpinionDao.retrieveLeaveOpinionIdByLeaveIdAndUserId(id, leaveId);
        if (leaveOpinion != null) {
            return leaveOpinion.getLeaveOpinionId();
        }
        return null;
    }

    @Override
    public List<LeaveOpinion> retrtiveIntrestedUserLeaveOpinionListByLeaveId(Long leaveId) {
        return leaveOpinionDao.retrtiveIntrestedUserLeaveOpinionListByLeaveId(leaveId);
    }

    @Override
    public List<LeaveOpinion> retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(Long leaveId) {
        return leaveOpinionDao.retrtiveNotIntrestedUserLeaveOpinionListByLeaveId(leaveId);
    }

    @Override
    public List<LeaveOpinion> retrieveOpinionLeaveResponseList(Long id) {
        return leaveOpinionDao.retrieveLeaveOpinionListOfNotificationShow(id);
    }

    @Override
    public LeaveOpinion retrieveLeaveOpinionByLeaveIdAndUserId(Long leaveId, Long id) {
        return leaveOpinionDao.retrieveLeaveOpinionIdByLeaveIdAndUserId(id, leaveId);
    }

    @Override
    public void sendLeaveOpinionNotificationForModifyLeave(Long leaveId, Long[] userIdList) {
        if (userIdList != null && userIdList.length > 0) {
            List<LeaveOpinion> deletedleaveOpinionsList = leaveOpinionDao.retriveDeleteUserLeaveOpinionList(leaveId, userIdList);
            if (deletedleaveOpinionsList != null && !deletedleaveOpinionsList.isEmpty()) {
                for (LeaveOpinion leaveOpinion : deletedleaveOpinionsList) {
                    SystemUserDetail systemUserDetail = systemUserDetailService.retrieveSystemUserDetailById(leaveOpinion.getUserId());
                    if (systemUserDetail != null && systemUserDetail.getIsGiveOpinion() == true) {
                        leaveOpinion.setIsSelectedByUser(false);
                        leaveOpinionDao.update(leaveOpinion);
                    } else {
                        leaveOpinionDao.delete(leaveOpinion);
                    }
                }
            }
        }
        List<LeaveOpinion> userSelectedLeaveOpinion = leaveOpinionDao.retrieveUserSelectedLeaveOpinionDetailByLeaveId(leaveId);
        if (userSelectedLeaveOpinion != null && !userSelectedLeaveOpinion.isEmpty()) {
            for (Long userId : userIdList) {
                boolean flag = true;
                for (LeaveOpinion leaveOpinion : userSelectedLeaveOpinion) {
                    if (leaveOpinion.getUserId() == userId) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    LeaveOpinion leaveOpinion = leaveOpinionDao.retrieveLeaveOpinionIdByLeaveIdAndUserId(userId, leaveId);
                    if (leaveOpinion != null) {
                        leaveOpinion.setIsSelectedByUser(true);
                        leaveOpinionDao.update(leaveOpinion);
                    } else {
                        leaveOpinion = new LeaveOpinion();
                        leaveOpinion.setUserId(userId);
                        leaveOpinion.setLeaveId(leaveId);
                        leaveOpinion.setIsArchive(false);
                        leaveOpinion.setIsNotificationShow(false);
                        leaveOpinion.setIsSelectedByUser(true);
                        this.createLeaveOpinion(leaveOpinion);
                    }
                }
            }
        }
    }

    @Override
    public Long[] retrieveUserSelectedLeaveOpinionUserIdList(Long leaveId) {
        List<LeaveOpinion> leaveOpinionList = leaveOpinionDao.retrieveUserSelectedLeaveOpinionDetailByLeaveId(leaveId);
        if (leaveOpinionList != null && !leaveOpinionList.isEmpty()) {
            Long[] userIdlist = new Long[leaveOpinionList.size()];
            int i = 0;
            for (LeaveOpinion leaveOpinion : leaveOpinionList) {
                userIdlist[i++] = leaveOpinion.getUserId();
            }
            return userIdlist;
        }
        return null;
    }
}
