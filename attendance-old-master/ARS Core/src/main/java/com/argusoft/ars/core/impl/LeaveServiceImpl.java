/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.core.impl;

import com.argusoft.ars.core.LeaveService;
import com.argusoft.ars.database.LeaveDao;
import com.argusoft.ars.database.LeaveOpinionDao;
import com.argusoft.ars.model.Leave;
import com.argusoft.ars.model.LeaveOpinion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smetaliya
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private LeaveOpinionDao leaveOpinionDao;

    @Override
    public Long createLeave(Leave leave) {
        return leaveDao.create(leave);
    }

    @Override
    public void updateLeave(Leave leave) {
        leaveDao.update(leave);
    }

    @Override
    public Leave retrieveLeaveById(Long leaveId) {
        Leave leave = null;
        if (leaveId != null) {
            leave = leaveDao.retrieveById(leaveId);
        }
        return leave;
    }

    @Override
    public List<Leave> retrieveNotArchiveLeave(Long id) {
        return leaveDao.retrieveNotArchiveLeave(id);

    }

    @Override
    public List<Leave> retrieveLeaveByUserId(Long id) {
        return leaveDao.retrieveLeaveByUserId(id);
    }

    @Override
    public List<Leave> retrievePandingLeave() {
        return leaveDao.retrieveNotificationLeaveList();
    }

    @Override
    public boolean isLeaveAlreadyApply(Long id, Date fromDate, Date toDate) {
        List<Leave> leaves = leaveDao.retrieveApplyLeaveBetweenDates(id, fromDate, toDate);
        if (leaves.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Float retrieveNoOfLeavesBetweenDate(Long id, Date fromDate, Date toDate, String leaveType, Boolean isWithPandingLeave) {
        List<Leave> leavesList = leaveDao.retrieveLeaveTypeBetweenDateWithApprovalStatus(id, fromDate, toDate, leaveType, isWithPandingLeave);
        Float noOfLeaves = 0F;
        if (!leavesList.isEmpty()) {
            for (Leave leave : leavesList) {
//                if (leave.getToDate() != null) {
//                    noOfLeaves += daysBetween(leave.getFromDate(), leave.getToDate());
//                    System.out.println("No OF Leaves Between" + noOfLeaves);
//                    if (!"FD".equals(leave.getToDateLeaveType())) {
//                        System.out.println("In Not eql FD in todate");
//                        noOfLeaves -= 0.5F;
//                    }
//                } else {
//                    System.out.println("In Else Pasrt");
//                    noOfLeaves += 1F;
//                }
//                if (!"FD".equals(leave.getFromDateLeaveType())) {
//                    System.out.println("In FromDate Cheak");
//                    noOfLeaves -= 0.5F;
//                }
                Integer noOfDaysBeetween = daysBetween(leave.getFromDate(), leave.getToDate());
                Integer noOfWeekEnds = getWeekEndsDaysBetweenTwoDates(leave.getFromDate(), leave.getToDate());
                if (noOfDaysBeetween == 1) {
                    if (noOfWeekEnds > 0) {
                        return 0F;
                    } else {
                        if (!"FD".equals(leave.getToDateLeaveType())) {
                            noOfLeaves += 0.5F;
                        } else {
                            noOfLeaves += 1F;
                        }
                    }
                } else {
                    noOfLeaves += noOfDaysBeetween;
                    noOfLeaves -= noOfWeekEnds;
                    if (!"FD".equals(leave.getToDateLeaveType())) {
                        if (leave.getToDate().getDay() == 0 || leave.getToDate().getDay() == 6) {
                            noOfLeaves += 0.5F;
                        }
                        noOfLeaves -= 0.5F;
                    }
                    if (!"FD".equals(leave.getFromDateLeaveType())) {
                        if (leave.getFromDate().getDay() == 0 || leave.getFromDate().getDay() == 6) {
                            noOfLeaves += 0.5F;
                        }
                        noOfLeaves -= 0.5F;
                    }
                }
            }
            return noOfLeaves;
        } else {
            return noOfLeaves;
        }
    }

    public Integer daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)) + 1;
    }

    @Override
    public List<Leave> retrieveLeavesBetweenDateWithApprovalStatus(Long id, Date fromDate, Date toDate, String leaveType, Boolean isWithPandingLeave) {
        return leaveDao.retrieveLeaveTypeBetweenDateWithApprovalStatus(id, fromDate, toDate, leaveType, isWithPandingLeave);
    }

    @Override
    public List<Leave> retrieveLeavesBetweenDate(Long id, Date fromDate, Date toDate, String leaveType) {
        return leaveDao.retrieveLeaveBetweenDate(id, fromDate, toDate, null, leaveType);
    }

    public static int getWeekEndsDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int weekEndDays = 0;
        if (startCal.getTimeInMillis() >= endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }
        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                ++weekEndDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
        return weekEndDays;
    }

    @Override
    public List<Leave> retrieveLeaveNotificationDetailByUserId(Long id) {
        List<LeaveOpinion> leaveOpinions = leaveOpinionDao.retrieveLeaveOpinionListByUserId(id);
        if (leaveOpinions != null) {
            List<Leave> leaveList = new ArrayList<Leave>();
            for (LeaveOpinion leaveOpinion : leaveOpinions) {
                Leave leave = this.retrieveLeaveById(leaveOpinion.getLeaveId());
                leaveList.add(leave);
            }
            return leaveList;
        }
        return null;
    }

    @Override
    public List<Leave> retrieveCancelLeaveNotification() {
        return leaveDao.retrieveCancelLeaveNotificationList();
    }

    @Override
    public List<Leave> retrieveLeavesBetweenDateListWithoutSameLeaveDetail(Long id, Date fromDate, Date toDate, Long leaveId, String leaveType) {
        return leaveDao.retrieveLeaveBetweenDate(id, fromDate, toDate, leaveId, leaveType);
    }

    @Override
    public Leave retrieveLeaveByDate(Long id, Date time) {
        List<Leave> leaves = retrieveLeavesBetweenDate(id, time, time, null);
        if (leaves != null && leaves.size() > 0) {
            return leaves.get(0);
        }
        return null;
    }
}
