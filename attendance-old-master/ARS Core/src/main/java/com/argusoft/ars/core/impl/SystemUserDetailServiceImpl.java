/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.SystemUserDetailService;
import com.argusoft.ars.database.*;
import com.argusoft.ars.database.SystemUserDetailDao;
import com.argusoft.ars.model.SystemUserDetail;
import com.argusoft.ars.model.UserWiseLeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("systemUserDetailService")
public class SystemUserDetailServiceImpl implements SystemUserDetailService {

    @Autowired
    private SystemUserDetailDao systemUserDetailDao;
    @Autowired
    private UserWiseLeaderDAO userWiseLeaderDAO;

    @Override
    public SystemUserDetail retrieveSystemUserDetailById(Long id) {
        System.out.println("In RetriveSystem User Detail");
        SystemUserDetail systemUserDetail = systemUserDetailDao.retrieveById(id);
        System.out.println("system UserDetail" + systemUserDetail);
        return systemUserDetail;
    }

//    private SystemUserDetail setUserCollections(SystemUserDetail systemUserDetail, boolean departmentDetailRequired, boolean designationDetailReuired, boolean shiftDetailReuired, boolean cardDetailReuired) {
//        if (departmentDetailRequired) {
//            Department department = departmentDao.retrieveById(systemUserDetail.getDepId().getDeptId());
//            if (department != null) {
//                systemUserDetail.setDepId(department);
//            } else {
//                systemUserDetail.setDepId(new Department(systemUserDetail.getDepId().getDeptId()));
//            }
//        } else {
//            systemUserDetail.setDepId(new Department(systemUserDetail.getDepId().getDeptId()));
//        }
//        if (designationDetailReuired) {
//            Designation designation = designationDao.retrieveById(systemUserDetail.getDesgId().getDesId());
//            if (designation != null) {
//                systemUserDetail.setDesgId(designation);
//            } else {
//                systemUserDetail.setDesgId(new Designation(systemUserDetail.getDesgId().getDesId()));
//            }
//        } else {
//            systemUserDetail.setDesgId(new Designation(systemUserDetail.getDesgId().getDesId()));
//        }
////        if (cardDetailReuired) {
////            CardInventory cardInventory = cardInventoryDao.retrieveByCardId(car);
////            if (designation != null) {
////                systemUserDetail.setDesgId(designation);
////            } else {
////                systemUserDetail.setDesgId(new Designation(systemUserDetail.getDesgId().getDesId()));
////            }
////        } else {
////            systemUserDetail.setDesgId(new Designation(systemUserDetail.getDesgId().getDesId()));
////        }
////        if (designationDetailReuired) {
////            Designation designation = designationDao.retrieveById(systemUserDetail.getDesgId().getDesId());
////            if (designation != null) {
////                systemUserDetail.setDesgId(designation);
////            } else {
////                systemUserDetail.setDesgId(new Designation(systemUserDetail.getDesgId().getDesId()));
////            }
////        } else {
////            systemUserDetail.setDesgId(new Designation(systemUserDetail.getDesgId().getDesId()));
////        }
//        return null;
////        
//    }
    @Override
    public List<SystemUserDetail> retrieveSystemUserDetailNotEqualByType(Long id, String type) {
        return systemUserDetailDao.retrieveSystemUserDetailNotEqualByType(id, type);

    }

    @Override
    public void createsystemUserDetail(SystemUserDetail systemUserDetail) {
        systemUserDetailDao.update(systemUserDetail);
    }

    @Override
    public void updatesystemUserDetail(SystemUserDetail systemUserDetail) {
        systemUserDetailDao.update(systemUserDetail);
    }

    @Override
    public List<SystemUserDetail> retrieveUserDetailWithoutCard() {
        List<SystemUserDetail> systemUserDetailsWithoutCard = systemUserDetailDao.retrieveUserDetailWithoutCard();
        if (systemUserDetailsWithoutCard.isEmpty()) {
            return null;
        } else {
            return systemUserDetailsWithoutCard;
        }
    }

    @Override
    public Long[] retrieveLeaveOpinionList() {
        List<SystemUserDetail> leaveOpinionList = systemUserDetailDao.retrieveActiveLeaveOpinionList();
        if (leaveOpinionList != null) {
            Long[] leaveOpinionUserId = new Long[leaveOpinionList.size()];
            int i = 0;
            for (SystemUserDetail systemUserDetail : leaveOpinionList) {
                leaveOpinionUserId[i++] = systemUserDetail.getUserId();
            }
            return leaveOpinionUserId;
        }
        return null;
    }

    @Override
    public void submitLeaveOpinionList(Long[] userIdList) {
        try {
            List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrievenNotEqualUserIdList(userIdList, true, false);
            if (systemUserDetails != null) {
                for (SystemUserDetail systemUserDetail : systemUserDetails) {
                    systemUserDetail.setIsGiveOpinion(false);
                    this.updatesystemUserDetail(systemUserDetail);
                }
            }
            if (userIdList != null) {
                for (Long userId : userIdList) {
                    SystemUserDetail systemUserDetail = retrieveSystemUserDetailById(userId);
                    systemUserDetail.setIsGiveOpinion(true);
                    this.updatesystemUserDetail(systemUserDetail);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Long[] retrieveAttendanceManagerList() {
        List<SystemUserDetail> attendanceManagerList = systemUserDetailDao.retrieveActiveAttendaceManagerList();
        if (attendanceManagerList != null) {
            Long[] leaveOpinionUserId = new Long[attendanceManagerList.size()];
            int i = 0;
            for (SystemUserDetail systemUserDetail : attendanceManagerList) {
                leaveOpinionUserId[i++] = systemUserDetail.getUserId();
            }
            return leaveOpinionUserId;
        }
        return null;
    }

    @Override
    public void submitAttendanceManagerList(Long[] userIdList) {
        try {
            List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrievenNotEqualUserIdList(userIdList, false, true);
            if (systemUserDetails != null) {
                for (SystemUserDetail systemUserDetail : systemUserDetails) {
                    systemUserDetail.setIsAttendanceManager(false);
                    this.updatesystemUserDetail(systemUserDetail);
                }
            }
            if (userIdList != null) {
                for (Long userId : userIdList) {
                    SystemUserDetail systemUserDetail = retrieveSystemUserDetailById(userId);
                    systemUserDetail.setIsAttendanceManager(true);
                    this.updatesystemUserDetail(systemUserDetail);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Long[] retrievePersonalUserLeaderList(Long id) {
        List<UserWiseLeader> userWiseLeaderList = userWiseLeaderDAO.retrieveUserLeaderList(id);
        if (userWiseLeaderList != null) {
            Long[] leaderUserIdList = new Long[userWiseLeaderList.size()];
            int i = 0;
            for (UserWiseLeader userWiseLeader : userWiseLeaderList) {
                leaderUserIdList[i++] = userWiseLeader.getLeaderUserId();
            }
            return leaderUserIdList;
        }
        return null;
    }

    @Override
    public Long[] retrieveUsersByLeaderid(Long id) {
        List<UserWiseLeader> userWiseLeaderList = userWiseLeaderDAO.retrieveUsersByLeaderid(id);
        if (userWiseLeaderList != null) {
            Long[] userIdList = new Long[userWiseLeaderList.size()];
            int i = 0;
            for (UserWiseLeader userWiseLeader : userWiseLeaderList) {
                userIdList[i++] = userWiseLeader.getUserId();
            }
            return userIdList;
        }
        return null;
    }

    @Override
    public void submitUsersAndLeaderid(Long leaderId, Long[] userIds) {
        List<UserWiseLeader> userWiseLeaderList = userWiseLeaderDAO.retrieveUsersByLeaderid(leaderId);
        if (userWiseLeaderList != null) {
            userWiseLeaderDAO.deleteAll(userWiseLeaderList);
        }
        if (userIds != null) {
            for (Long userId : userIds) {
                UserWiseLeader userWiseLeader = new UserWiseLeader();
                userWiseLeader.setLeaderUserId(leaderId);
                userWiseLeader.setUserId(userId);
                userWiseLeaderDAO.create(userWiseLeader);
            }
        }
    }

    @Override
    public void submitUserLeaderList(Long id, Long[] userIdList) {
        try {
            List<UserWiseLeader> userWiseLeaderList = userWiseLeaderDAO.retrieveUserLeaderList(id);
            if (userWiseLeaderList != null) {
                for (UserWiseLeader userWiseLeader : userWiseLeaderList) {
                    userWiseLeaderDAO.deleteById(userWiseLeader.getId());
                }
            }
            if (userIdList != null) {
                for (Long userId : userIdList) {
                    UserWiseLeader userWiseLeader = new UserWiseLeader();
                    userWiseLeader.setLeaderUserId(userId);
                    userWiseLeader.setUserId(id);
                    userWiseLeaderDAO.create(userWiseLeader);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Long[] retrieveLeaveOpinionUserListByUserId(Long userId) {
        List<UserWiseLeader> userWiseLeadersList = userWiseLeaderDAO.retrieveUserLeaderList(userId);
        Long[] leaderIdList = new Long[1], userIdList;

        if (userWiseLeadersList != null) {
            leaderIdList = new Long[userWiseLeadersList.size() + 1];
            int i = 0;
            for (UserWiseLeader userWiseLeader : userWiseLeadersList) {
                leaderIdList[i++] = userWiseLeader.getLeaderUserId();
            }
            leaderIdList[i++] = userId;
        } else {
            leaderIdList[0] = userId;
        }
        List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrievenNotEqualUserIdList(leaderIdList, true, false);
        if (systemUserDetails != null) {
            int i = 0;
            userIdList = new Long[leaderIdList.length + systemUserDetails.size() - 1];
            for (Long leaderId : leaderIdList) {
                if (leaderId != userId) {
                    userIdList[i++] = leaderId;
                }
            }
            for (SystemUserDetail systemUserDetail : systemUserDetails) {
                userIdList[i++] = systemUserDetail.getUserId();
            }
            return userIdList;
        } else {
            if (leaderIdList.length != 1) {
                return leaderIdList;
            } else {
                return null;
            }
        }
    }

    @Override
    public Integer noOfActiveUserInDepartment(Long depId) {
        List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrieveActiveUserByDepartmentId(depId);
        if (systemUserDetails != null) {
            return systemUserDetails.size();
        }
        return 0;
    }

    @Override
    public Integer noOfActiveUserInDesignation(Long desgId) {
        List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrieveActiveUserByDesignationId(desgId);
        if (systemUserDetails != null) {
            return systemUserDetails.size();
        }
        return 0;
    }

    @Override
    public Integer noOfActiveUserInShift(Long shiftId) {
        List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrieveActiveUserByShiftId(shiftId);
        if (systemUserDetails != null) {
            return systemUserDetails.size();
        }
        return 0;
    }

    @Override
    public Integer noOfActiveUser() {
        List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrieveActiveUser();
        if (systemUserDetails != null) {
            return systemUserDetails.size();
        }
        return 0;
    }

    @Override
    public Long[] retrieveLeaveOpinionUserListByUserId(Long[] leaveOpinionUserIdList) {
        List<SystemUserDetail> systemUserDetails = systemUserDetailDao.retrievenNotEqualUserIdList(leaveOpinionUserIdList, true, false);
        if (systemUserDetails != null) {
            int i = 0;
            Long[] userIdList = new Long[systemUserDetails.size()];
            for (SystemUserDetail systemUserDetail : systemUserDetails) {
                userIdList[i++] = systemUserDetail.getUserId();
            }
            return userIdList;
        }
        return null;
    }

    @Override
    public Boolean isEmpIdExits(String empId, Long id) {
        Integer noOfActiveEmployeeByEmpId = systemUserDetailDao.retriveNoOfActiveEmployeeByEmpId(empId, id);
        if (noOfActiveEmployeeByEmpId != null && noOfActiveEmployeeByEmpId == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<SystemUserDetail> retrieveSystemUserDetailByType(Long id, String type) {
        return systemUserDetailDao.retrieveSystemUserDetailByType(id, type);
    }

    @Override
    public List<SystemUserDetail> retrieveSystemUserDetailByUserIds(List<Long> userIds, Boolean isActive) {
        return systemUserDetailDao.retrieveSystemUserDetailByType(userIds, isActive);
    }
}
