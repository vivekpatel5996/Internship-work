/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core;

import com.argusoft.ars.model.SystemUserDetail;
import java.util.List;

/**
 * This Class is used to perform various operations on SystemUserDetail and
 * related entities.
 *
 * @author Harshit
 */
public interface SystemUserDetailService {

    /**
     * createsystemUserDetail method creates an Object of Class
     * SystemUserDetail, by calling saveEntity method of its parent.
     *
     * @param systemUserDetail Takes an Object of Class SystemUserDetail, which
     * is to be created.
     */
    void createsystemUserDetail(SystemUserDetail systemUserDetail);

    /**
     * updatesystemUserDetail method updates an Object of Class
     * SystemUserDetail, by calling updateEntity method of its parent.
     *
     * @param systemUserDetail Takes an Object of Class SystemUserDetail, which
     * is to be updated.
     */
    public void updatesystemUserDetail(SystemUserDetail systemUserDetail);

    /**
     * retrieveSystemUserDetailByKey method retrieves the SystemUserDetail
     * Object, by calling getEntityById method of its parent.
     *
     * @param systemUserDetailId Takes PK of the SystemUserDetail
     * @return Returns the Object of SystemUserDetail.
     */
    public SystemUserDetail retrieveSystemUserDetailById(Long id);

    public List<SystemUserDetail> retrieveSystemUserDetailNotEqualByType(Long id, String type);

    public List<SystemUserDetail> retrieveUserDetailWithoutCard();

    public Long[] retrieveLeaveOpinionList();

    public void submitLeaveOpinionList(Long[] userIdList);

    public Long[] retrieveAttendanceManagerList();

    public void submitAttendanceManagerList(Long[] userIdList);

    public Long[] retrievePersonalUserLeaderList(Long id);

    public Long[] retrieveUsersByLeaderid(Long id);

    public void submitUsersAndLeaderid(Long leaderId, Long[] userIds);

    public void submitUserLeaderList(Long id, Long[] userIdList);

    public Long[] retrieveLeaveOpinionUserListByUserId(Long userId);

    public Integer noOfActiveUserInDepartment(Long depId);

    public Integer noOfActiveUserInDesignation(Long desgId);

    public Integer noOfActiveUserInShift(Long shiftId);

    public Integer noOfActiveUser();

    public Long[] retrieveLeaveOpinionUserListByUserId(Long[] leaveOpinionUserIdList);

    public Boolean isEmpIdExits(String empId, Long id);

    public List<SystemUserDetail> retrieveSystemUserDetailByType(Long id, String type);

    public List<SystemUserDetail> retrieveSystemUserDetailByUserIds(List<Long> userIds, Boolean isActive);
}
