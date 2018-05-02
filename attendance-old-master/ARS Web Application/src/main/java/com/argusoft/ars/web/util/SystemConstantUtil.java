/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.util;

/**
 * This class specifies the static final constants to be used in application.
 *
 * @author mpritmani
 */
public class SystemConstantUtil {

    //  Roles
    public static final String ROLE_SUPER_ADMIN = "ROLE_SADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MEMBER = "ROLE_MEMBER";
    //  default pages
    public static final String ROLE_SUPER_ADMIN_PAGE = "adminHome";
    public static final String ROLE_ADMIN_PAGE = "manageAdmin";
    public static final String ROLE_MEMBER_PAGE = "memberDashboard";
    public static final String LOGOUT_URL = "login";
    //  general constants
    public final static String SUCCESS = "SUCCESS";
    public final static String FAILURE = "FAILURE";
    public final static String AUTHENTICATED_SESSION = "AUTHENTICATED SESSION";
    public final static String LABEL_UPDATE_REQUIRED = "LABEL_UPDATE_REQUIRED";
    public final static String REPEAT = "REPEAT";
    public final static String SAME = "SAME";
    public static final String NOT_EMPTY = "NOTEMPLTY";
    //  timezone constants
    public static final String TIMEZONE_PDT = "PDT";
    public static final String SERVER_TIMEZONE = "GMT";
    // operation constants
    public static final String CREATE_OPERATION = "C";
    public static final String UPDATE_OPERATION = "U";
    // Active and IsActive status
    public static final String ACTIVE = "A";
    public static final String IN_ACTIVE = "IA";
    //Leave Status
    public static final String PENDING = "Pending";
    public static final String APPROVE = "Approve";
    public static final String DISAPPROVE = "Disapprove";
    //Applied Status
    public static final String APPLY_NEW = "New";
    public static final String APPLY_MODIFIED = "Modified";
    public static final String APPLY_CANCEL = "Cancel";
    public static final String APPLY_NEW_MODIFIED = "New-Modified";
    public static final String APPLY_APPROVE_MODIFIED = "Approve-Modified";
    public static final String APPLY_DISAPPROVE_MODIFIED = "Disapprove-Modified";
    public static final String APPLY_APPROVE_CANCEL = "Approve-Cancel";
    public static final String APPLY_APPROVE_CANCEL_APPROVE = "Approve-Cancel-Approve";
    public static final String APPLY_APPROVE_CANCEL_DISAPPROVE = "Approve-Cancel-Disapprove";
    //Leave type
    public static final String EARN_LEAVE = "Earn";
    public static final String CASUL_LEAVE = "Casual";
    public static final String LOSS_OF_PAY = "Loss Of Pay";
    public static final String RISTRICTED_HOLIDAY = "Restricted Holiday";
    //OfficialBreak Type
    public static final String OFFICIAL_BREAK = "officialBreak";
    public static final String BUSINESS_TRIP = "businessTrip";
    //OfficialBreak Applied Status
    public static final String OFFICIALBREAK_APPLY_NEW = "New";
    public static final String OFFICIALBREAK_APPLY_MODIFIED = "Modified";
    public static final String OFFICIALBREAK_APPLY_CANCEL = "Cancel";
    public static final String OFFICIALBREAK_APPLY_NEW_MODIFIED = "New-Modified";
    public static final String OFFICIALBREAK_APPLY_APPROVE_MODIFIED = "Approve-Modified";
    public static final String OFFICIALBREAK_APPLY_DISAPPROVE_MODIFIED = "Disapprove-Modified";
    public static final String OFFICIALBREAK_APPLY_APPROVE_CANCEL = "Approve-Cancel";
    public static final String OFFICIALBREAK_APPLY_APPROVE_CANCEL_APPROVE = "Approve-Cancel-Approve";
    public static final String OFFICIALBREAK_APPLY_APPROVE_CANCEL_DISAPPROVE = "Approve-Cancel-Disapprove";
    //  in - out constants
    public static final int PUNCH_IN = 0;
    public static final int PUNCH_OUT = 1;
    public static String CONNECTION_TYPE_DEFAULT = "Default";
    //email template
    public static final String EMAIL_EMPLOYEE_NAME = "#EMPNAME#";
    public static final String EMAIL_SHIFT_START_TIME = "#SHIFTSTARTTIME#";
    public static final String EMAIL_SHIFT_END_TIME = "#SHIFTENDTIME#";
    public static final String EMAIL_CURRENT_TIME = "#CURTIME#";
    public static final String EMAIL_APPROVAL_STATUS = "#APROVALSTATUS#";
    public static final String EMAIL_FROM_DATE = "#FROMDATE#";
    public static final String EMAIL_TO_DATE = "#TODATE#";
    public static final String EMAIL_CURRENT_DATE = "#CURDATE#";
    public static final String EMAIL_LATE_DURATION = "#LATEDURATION#";
    public static final String EMAIL_INFO = "#INFO#";
    public static final String EMAIL_APPROVER = "#APPROVER#";
    public static final String EMAIL_DATE = "#DATE#";
    public static final String EMAIL_RESPONSE = "#RESPONSE#";
    public static final String EMAIL_OLD_DATE = "#OLDDATE#";
    public static final String EMAIL_OLD_FROM_DATE = "#OLDFROMDATE#";
    public static final String EMAIL_OLD_TO_DATE = "#OLDTODATE#";
    public static final String EMAIL_PASSWORD = "#PASSWORD#";
    public static final String EMAIL_NEW_PASSWORD = "#NEWPASSWORD#";
    //email formate name
    public static final String EMAIL_FORMAT_LEAVE_OPINION = "LeaveOpinion";
    public static final String EMAIL_FORMAT_CANCEL_LEAVE = "CancelLeave";
    public static final String EMAIL_FORMAT_APPLY_LEAVE = "ApplyLeave";
    public static final String EMAIL_FORMAT_LEAVE_CANCELLATION = "LevaeCancellation";
    public static final String EMAIL_FORMAT_UPDATE_LEAVE = "UpdateLeave";
    public static final String EMAIL_FORMAT_APPROVE_LEAVE = "ApproveLeave";
    public static final String EMAIL_FORMAT_CANCELATION_LEAVE_APPROVE = "CancelationLeaveApprove";
    public static final String EMAIL_FORMAT_DISAPPROVE_LEAVE = "DisapproveLeave";
    public static final String EMAIL_FORMAT_CANCELATION_LEAVE_DISAPPROVE = "CancelationLeaveDisapprove";
    public static final String EMAIL_FORMAT_APPLY_MANUAL_CARD_ENTRY = "Apply MannualCardEntry";
    public static final String EMAIL_FORMAT_RESPONSE_MANUAL_CARD_ENTRY = "ResponseManualCardEntry";
    public static final String EMAIL_FORMAT_OFFICIALBREAK_OPINION = "OfficialBreakOpinion";
    public static final String EMAIL_FORMAT_CANCEL_OFFICIALBREAK = "CancelOfficialBreak";
    public static final String EMAIL_FORMAT_APPLY_OFFICIALBREAK = "ApplyOfficialBreak";
    public static final String EMAIL_FORMAT_OFFICIALBREAK_CANCELLATION = "OfficialBreakCancellation";
    public static final String EMAIL_FORMAT_UPDATE_OFFICIALBREAK = "UpdateOfficialBreak";
    public static final String EMAIL_FORMAT_APPROVE_OFFICIALBREAK = "ApproveOfficialBreak";
    public static final String EMAIL_FORMAT_CANCELATION_OFFICIALBREAK_APPROVE = "CancelationOfficialBreakApprove";
    public static final String EMAIL_FORMAT_DISAPPROVE_OFFICIALBREAK = "DisapproveOfficialBreak";
    public static final String EMAIL_FORMAT_CANCELATION_OFFICIALBREAK_DISAPPROVE = "CancelationOfficialBreakDisapprove";
    public static final String EMAIL_FORMAT_CANCELATION_RESIGNATION_DISAPPROVE = "Cancelation Resignation Disapprove";
    public static final String EMAIL_FORMAT_RESIGNATION_DISAPPROVE = "Resignation Disapprove";
    public static final String EMAIL_FORMAT_RESIGNATION_APPROVE = "Resignation Approve";
    public static final String EMAIL_FORMAT_CANCELATION_RESIGNATION_APPROVE = "Cancelation Resignation Approve";
    public static final String EMAIL_FORMAT_UPDATE_EXIT_PROCESS = "Update Resignation";
    public static final String EMAIL_FORMAT_CANCEL_RESIGNATION = "Cancel Resignation";
    public static final String EMAIL_FORMAT_APPLY_RESIGNATION = "Apply Resignation";
    public static final String EMAIL_FORMAT_FORGOT_PASSWORD = "Forgot Password";
    public static final String EMAIL_FORMAT_LATE_CONSECUTIVE="Consecutive Late";
    public static final String EMAIL_FORMAT_ON_TIME_CONSECUTIVE="Consecutive On Time";
    
    
    public static final String CONTRACTUAL_EMPLOYEE = "Contractual";
    public static final String EMAIL_FORMAT_NEW_USER_CREATED = "New User Created";
    public static final String TRAINEE = "Trainee";
    public static final String EMAIL_FORMAT_ACTIVATE_USER = "Activate User";
    public static final String EMPLOYEE = "Employee";
    public static final String ENTRY_IN = "In";
    public static final String ENTRY_OUT = "Out";
    public static final String ALLOWABLE_LATE_MINIUTE = "allowableLateMinutes";
    public static final String MAIL_FOR_LATE="Late";
    public static final String MAIL_FOR_ON_TIME="OnTime";
    
}
