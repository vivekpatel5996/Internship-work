/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.database;

import com.argusoft.ars.common.database.GenericDao;
import com.argusoft.ars.model.SystemUserDetail;
import java.util.List;

/**
 * This class is used to perform CRUD operations on SystemUserDetail Entity.
 *
 * @author Harshit
 */
public interface SystemUserDetailDao extends GenericDao<SystemUserDetail, Long> {

    /**
     * retrieveAllActiveSystemUserDetail method retrieves all Active
     * SystemUserDetail Detail Objects, by calling findByCriteriaList method of
     * its parent class.
     *
     * @return Returns the List of Object of Class SystemUserDetail.
     */
    public List<SystemUserDetail> retrieveAllActiveSystemUserDetail();

    /**
     * retrieveActiveSystemUserDetailByName method retrieve Active
     * SystemUserDetail Detail by Name. Objects, by calling findByCriteriaList
     * method of its parent class.
     *
     * @return Returns the Object of Class SystemUserDetail.
     */
    public List<SystemUserDetail> retrieveSystemUserDetailNotEqualByType(Long id, String type);

    public List<SystemUserDetail> retrieveUserDetailWithoutCard();

    public List<SystemUserDetail> retrieveActiveLeaveOpinionList();

    public List<SystemUserDetail> retrievenNotEqualUserIdList(Long[] userIdList, boolean isWithLeaveOpinion, boolean isWithAttendanceManager);

    public List<SystemUserDetail> retrieveActiveAttendaceManagerList();

    public List<SystemUserDetail> retrieveActiveUserByDepartmentId(Long depId);

    public List<SystemUserDetail> retrieveActiveUserByShiftId(Long shiftId);

    public List<SystemUserDetail> retrieveActiveUserByDesignationId(Long desId);

    public List<SystemUserDetail> retrieveActiveUser();

    public Integer retriveNoOfActiveEmployeeByEmpId(String empId, Long id);

    public List<SystemUserDetail> retrieveSystemUserDetailByType(Long id, String type);

    public List<SystemUserDetail> retrieveSystemUserDetailByType(List<Long> userIds, Boolean isActive);
}