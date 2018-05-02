/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.eproctortoars;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author harshit
 */
public class EproctorDBToARSDBServiceIpl {
    
    private EProctorDatabaseHandler eProctorDatabaseHandler = new EProctorDatabaseHandler();
    private ARSDatabaseHandler aRSDatabaseHandler = new ARSDatabaseHandler();
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    
    public void transferDepartmentData() {
        try {
            eProctorDatabaseHandler.makeconnection();
            String query = "select * from department";
            System.out.println(query);
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            aRSDatabaseHandler.executeUpdate("update usermanagement_system_user set contact = null");
            aRSDatabaseHandler.executeUpdate("update usermanagement_user_contact set userobj = null");
            aRSDatabaseHandler.executeUpdate("delete from usermanagement_user_contact");
            aRSDatabaseHandler.executeUpdate("delete from usermanagement_system_user");
            aRSDatabaseHandler.executeUpdate("delete from system_user_detail");
            aRSDatabaseHandler.executeUpdate("delete from department");
            while (rs != null && rs.next()) {
                Long depId = rs.getLong("dep_id");
                String dep_name = rs.getString("dep_name");
                String query1 = "insert into department(dept_id,dept_name,is_active,is_archive) values (" + depId + ",'" + dep_name + "', true, false)";
                aRSDatabaseHandler.executeUpdate(query1);
            }
            aRSDatabaseHandler.releaseConnection();
            
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public void transferDesignationData() {
        try {
            eProctorDatabaseHandler.makeconnection();
//            

            String query = "select * from designation";
            System.out.println(query);
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            aRSDatabaseHandler.executeUpdate("delete from designation");
            while (rs != null && rs.next()) {
                
                Long desId = rs.getLong("des_id");
                String des_name = rs.getString("des_name");
                String query1 = "insert into designation(des_id,des_name,is_active,is_archive) values (" + desId + ",'" + des_name + "', true, false)";
                aRSDatabaseHandler.executeUpdate(query1);
            }
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public void transferShift() {
        try {
            eProctorDatabaseHandler.makeconnection();
            String query = "select * from shift";
            System.out.println(query);
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            aRSDatabaseHandler.executeUpdate("delete from shift");
            while (rs != null && rs.next()) {
                Long desId = rs.getLong("shift_id");
                String des_name = rs.getString("shift_name");
                String entryTime = rs.getString("entry_time");
                String exitTime = rs.getString("exit_time");
                System.out.println("Entry Time:" + entryTime);
                String query1 = "insert into shift(shift_id,shift_name,shift_start_time,shift_end_time,is_active,is_archive) values (" + desId + ",'" + des_name + "','" + entryTime + "','" + exitTime + "', true, false)";
                aRSDatabaseHandler.executeUpdate(query1);
            }
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public void transferCardData() {
        try {
            eProctorDatabaseHandler.makeconnection();
            String query = "select * from card_mapping";
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            aRSDatabaseHandler.executeUpdate("delete from card_inventory");
            int i = 1;
            while (rs != null && rs.next()) {
                Long cardId = rs.getLong("card_id");
                Integer id = rs.getInt("id");
                String status = rs.getString("status");
                boolean isActive, isArchive, isAssigned = false;
                if (status.equals("Active")) {
                    isActive = true;
                    isArchive = false;
                } else {
                    isActive = false;
                    isArchive = true;
                }
                String query1 = "insert into card_inventory(id,card_id,card_enroll_no,is_active,is_archive,is_assigned) values (" + id + "," + cardId + ",'" + "e" + (i++) + "'," + isActive + "," + isArchive + "," + isAssigned + ")";
                aRSDatabaseHandler.executeUpdate(query1);
            }
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public void transferEmployeeData() {
        try {
            int i = 1;
            eProctorDatabaseHandler.makeconnection();
            String query = "select * from employee";
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            while (rs != null && rs.next()) {
                String empName = rs.getString("emp_name");
                String gender = rs.getString("gender");
                String bDate = rs.getString("bdate");
                String jDate = rs.getString("jdate");
                String address = rs.getString("address");
                String contactNo = rs.getString("contact_no");
                Integer depId = rs.getInt("dep_id");
                Integer desId = rs.getInt("des_id");
                Integer shiftId = rs.getInt("shift_id");
                Integer cardId = rs.getInt("card_id");
                String email = rs.getString("email");
                
                
                String empType = rs.getString("emp_type");
                String empId = rs.getString("emp_id");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String pincode = rs.getString("pincode");
                boolean archive = rs.getBoolean("archive");
                System.out.println("========" + archive);
                String personalEmail = rs.getString("personal_email");
                boolean attendManager = rs.getBoolean("attnd_mngr");
                String cDate = rs.getString("cdate");
                
                String rDate = rs.getString("rDate");
                boolean isAdmin = false;
                String query2 = "select * from authentication where emp_id like '" + empId + "'";
                boolean isActive = true;
                if (archive) {
                    isActive = false;
                }
                
                String type = "ROLE_MEMBER";
                String password = "2ayry230Vq0oIILnwDNmK6HMoCKevv1h";
                String user_id = email;
                email = "hshah@argusoft.com";
                rs1 = eProctorDatabaseHandler.executeQuery(query2);
                while (rs1.next()) {
                    user_id = rs1.getString("user_id");
                    String adminRights = rs1.getString("admin_rights");
//                    password = rs1.getString("password");
                    if (adminRights.equals("true")) {
                        isAdmin = true;
                        type = "ROLE_SADMIN";
                    }
                }
                if (empType.equals("Permanent")) {
                    empType = "Employee";
                }
                
                
                if (bDate == null) {
                    bDate = "12/12/2022";
                }
                if (jDate == null) {
                    jDate = "01/01/2010";
                }
                if (cDate == null) {
                    cDate = "12/12/2022";
                }
                if (rDate == null) {
                    rDate = "12/12/2022";
                }
                if ("F".equals(gender)) {
                    gender = "FM";
                }
                String uMSUserQuery = "insert into usermanagement_system_user(id,created_by,created_on,password,type,user_id,is_active,is_archive,expired_on) values(" + i + ", 'AV Sethu Raman','" + cDate + "','" + password + "','" + type + "','" + user_id + "'," + isActive + "," + archive + ",'" + rDate + "')";
                System.out.println(uMSUserQuery);
                aRSDatabaseHandler.executeUpdate(uMSUserQuery);
                String uMSUserContactQuery = "insert into usermanagement_user_contact(id,address,alternate_email_address,city,country,created_by,created_on,date_of_birth,email_address,first_name,gender,is_active,is_archive,mobile_number,zip_code,userobj,last_name) values(" + i + ",'" + address + "','" + personalEmail + "','" + city + "','India', 'AV Sethu Raman','" + cDate + "','" + bDate + "','" + email + "','" + empName + "','" + gender + "'," + isActive + "," + archive + ",'" + contactNo + "','" + pincode + "'," + i + ",'')";
                System.out.println(uMSUserContactQuery);
                aRSDatabaseHandler.executeUpdate(uMSUserContactQuery);
                String sysUserDetailQuery = "insert into system_user_detail(user_id,is_active,is_archive,dep_id,desg_id,shift_id,join_date,is_attendance_manager,is_give_opinion,conformation_date,emp_id,emp_type) values(" + i + "," + isActive + "," + archive + "," + depId + "," + desId + "," + shiftId + ",'" + jDate + "'," + attendManager + ",false,'" + cDate + "','" + empId + "','" + empType + "')";
                aRSDatabaseHandler.executeUpdate(sysUserDetailQuery);
                String findCardIdQuery = "select * from card_mapping where emp_id like '" + empId + "'";
                String addRefereanceUMUD = "update usermanagement_system_user set contact=" + i + " where id=" + i + "";
                aRSDatabaseHandler.executeUpdate(addRefereanceUMUD);
                rs1 = eProctorDatabaseHandler.executeQuery(findCardIdQuery);
                while (rs1.next()) {
                    Long cardid = rs1.getLong("id");
                    String updateSysUserDeatilOfCard = "update system_user_detail set card_enroll_no = " + cardid + " where user_id= " + i + "";
                    aRSDatabaseHandler.executeUpdate(updateSysUserDeatilOfCard);
                    String updateCardInvQuery = "update card_inventory set assign_user_id=" + i + ",is_assigned = true where id = " + cardid + " ";
                    aRSDatabaseHandler.executeUpdate(updateCardInvQuery);
                }
//                String cDateUpdateQuery = "update usermanagement_user_contact set created_on = null where created_on='12/12/2022'";
//                aRSDatabaseHandler.executeUpdate(cDateUpdateQuery);
//                String cDateUpdateQuery1 = "update usermanagement_system_user set created_on = null where created_onF='12/12/2022'";
//                aRSDatabaseHandler.executeUpdate(cDateUpdateQuery1);
//                String query1 = "insert into shift(shift_id,shift_name,shift_start_time,shift_end_time,is_active,is_archive) values (" + desId + ",'" + des_name + "','" + entryTime + "','" + exitTime + "', true, false)";
//                aRSDatabaseHandler.executeUpdate(query1);
                i++;
            }
            String bDateUpdateQuery = "update usermanagement_user_contact set date_of_birth = null where date_of_birth='12/12/2022'";
            aRSDatabaseHandler.executeUpdate(bDateUpdateQuery);
//            String jDateUpdateQuery = "update system_user_detail set join_date = null where join_date='12/12/2022'";
//            aRSDatabaseHandler.executeUpdate(jDateUpdateQuery);
            String jDateUpdateQuery1 = "update system_user_detail set conformation_date = null where conformation_date='12/12/2022'";
            aRSDatabaseHandler.executeUpdate(jDateUpdateQuery1);
            String rDateUpdateQuery = "update usermanagement_system_user set expired_on = null where expired_on='12/12/2022'";
            aRSDatabaseHandler.executeUpdate(rDateUpdateQuery);
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public void transferLeaveData() {
        try {
            eProctorDatabaseHandler.makeconnection();
            String query = "select * from leave";
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            aRSDatabaseHandler.executeUpdate("delete from leave");
            int i = 1;
            while (rs != null && rs.next()) {
                Long id = null;
                Long holidayId = null;
                Long leaveId = rs.getLong("leave_id");
                String empName = rs.getString("emp_name");
                String userApply = rs.getString("user_apply");
                if (userApply.equals("Applied")) {
                    userApply = "New";
                } else {
                    userApply = "Cancel";
                }
                String adminReply = rs.getString("admin_reply");
                if ("Approved".equals(adminReply)) {
                    adminReply = "Approve";
                } else if ("Disapproved".equals(adminReply)) {
                    adminReply = "Disapprove";
                } else {
                    if (userApply.equals("Cancel")) {
                        adminReply = "";
                    } else {
                        adminReply = "Pending";
                    }
                }
                String reason = rs.getString("reason");
                String adminComment = rs.getString("admin_comment");
                String fromDate = rs.getString("from_date");
                String toDate = rs.getString("to_date");
                String responseBy = rs.getString("response_by");
                
                String empId = rs.getString("emp_id");
                String leaveType = rs.getString("leave_type");
                if (leaveType.equals("Restricted Holiday")) {
                    leaveType = "Restricted Holiday";
                    
                } else if (leaveType.equals("Casual Leave")) {
                    leaveType = "Casual";
                } else if (leaveType.equals("Earn Leave")) {
                    leaveType = "Earn";
                }
                boolean archive = rs.getBoolean("archive");
                String subject = rs.getString("subject");
                String halfDayFrom = rs.getString("halfday_from_date");
                if (halfDayFrom.equals("Full Day Leave")) {
                    halfDayFrom = "FD";
                } else if (halfDayFrom.equals("Afternoon (PM) Half Day Leave")) {
                    halfDayFrom = "HDE";
                } else if (halfDayFrom.equals("Morning (AM) Half Day Leave")) {
                    halfDayFrom = "HDM";
                }
                
                String halfDayTo = rs.getString("halfday_to_date");
                if (halfDayTo.equals("Full Day Leave")) {
                    halfDayTo = "FD";
                } else if (halfDayTo.equals("Afternoon (PM) Half Day Leave")) {
                    halfDayTo = "HDE";
                } else if (halfDayTo.equals("Morning (AM) Half Day Leave")) {
                    halfDayTo = "HDM";
                }
                reason = reason.replace("'", "");
                String findEmpIdQuery = "select * from system_user_detail where emp_id = '" + empId + "'";
                rs1 = aRSDatabaseHandler.executeQuery(findEmpIdQuery);
                System.out.println(findEmpIdQuery);
                while (rs1.next()) {
                    System.out.println("====>In");
                    id = rs1.getLong("user_id");
                }
                Long responseById = null;
                if (!"".equals(responseBy)) {
                    String findResondeByQuery = "select * from usermanagement_user_contact where first_name like '" + responseBy + "'";
                    rs1 = aRSDatabaseHandler.executeQuery(findResondeByQuery);
                    while (rs1.next()) {
                        responseById = rs1.getLong("id");
                    }
                }
                if ("Restricted Holiday".equals(leaveType)) {
                    String findResondeByQuery = "select * from holiday where h_date = '" + fromDate + "'";
                    rs1 = eProctorDatabaseHandler.executeQuery(findResondeByQuery);
                    while (rs1.next()) {
                        holidayId = rs1.getLong("h_id");
                    }
                }
                boolean isNotificationShow = false;
                if (adminReply.equals("Pending")) {
                    isNotificationShow = true;
                }
                if (id != null) {
                    String leaveInstertQuery = "insert into leave(leave_type,leave_subject,from_date,to_date,from_date_leave_type,to_date_leave_type,reason,applied_date,admin_comment,approval_status,leave_id,applied_status,is_archive,user_id,response_by,holiday_id,is_notification_show) values('" + leaveType + "','" + subject + "','" + fromDate + "','" + toDate + "','" + halfDayFrom + "','" + halfDayTo + "','" + reason + "','" + fromDate + "','" + adminComment + "','" + adminReply + "'," + leaveId + ",'" + userApply + "'," + archive + "," + id + "," + responseById + "," + holidayId + "," + isNotificationShow + " )";
                    System.out.println("==Leave:" + leaveInstertQuery);
                    aRSDatabaseHandler.executeUpdate(leaveInstertQuery);
                }
                //                String query1 = "insert into card_inventory(id,card_id,card_enroll_no,is_active,is_archive,is_assigned) values (" + id + "," + cardId + "," + i++ + "," + isActive + "," + isArchive + "," + isAssigned + ")";
//                aRSDatabaseHandler.executeUpdate(query1);

            }
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public void transferHolidayData() {
        try {
            eProctorDatabaseHandler.makeconnection();
            String query = "select * from holiday";
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
            aRSDatabaseHandler.executeUpdate("delete from holiday");
            int i = 1;
            while (rs != null && rs.next()) {
                Long holidayId = rs.getLong("h_id");
                String holidayName = rs.getString("h_name");
                String holidayDate = rs.getString("h_date");
                String holidayStatus = rs.getString("h_state");
                boolean isArchive = rs.getBoolean("archive");
                boolean isActive = true;
                if (isArchive) {
                    isActive = false;
                }
                String query1 = "insert into holiday(holiday_id,holiday_name,type,holiday_date,is_active,is_archive,created_date,created_by) values (" + holidayId + ",'" + holidayName + "','" + holidayStatus + "','" + holidayDate + "'," + isActive + "," + isArchive + ",'10/10/2012',77)";
                aRSDatabaseHandler.executeUpdate(query1);
            }
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    public Date transferCardLogData(Date fromDate) {
        try {
            Date maxDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            eProctorDatabaseHandler.makeconnection();
//            String query = "select * from card_log where date_time > '" + sdf.format(fromDate) + "' and date_time < '" + sdf.format(toDate) + "'";
            String query = "select * from card_log where date_time > '" + sdf.format(fromDate) + "'";
            
            System.out.println(query);
            rs = eProctorDatabaseHandler.executeQuery(query);
            aRSDatabaseHandler.makeconnection();
//            System.out.println("delete from card_log where card_punching_time > '" + sdf.format(fromDate) + "' and card_punching_time<'" + sdf.format(toDate) + "'");
//            aRSDatabaseHandler.executeUpdate("delete from card_log where card_punching_time > '" + sdf.format(fromDate) + "' and card_punching_time<'" + sdf.format(toDate) + "'");
            aRSDatabaseHandler.executeUpdate("delete from card_log where card_punching_time > '" + sdf.format(fromDate) + "'");
//            int i = 1;
            while (rs != null && rs.next()) {
                Long card_Id = rs.getLong("card_id");
                String dateTime = rs.getString("date_time");
                Date logTime = sdf.parse(dateTime);
                if(maxDate == null){
                    maxDate = logTime;
                }else if(maxDate.compareTo(logTime) < 0){
                    maxDate = logTime;
                }
                
                Integer cheeckType = rs.getInt("check_type");
                Long cardLogId = rs.getLong("log_id");
                Integer entryTurn = rs.getInt("entry_turn");
                String empId = rs.getString("emp_id");
                Long log_id = rs.getLong("log_id");
                String entryReason = rs.getString("entry_reason");
                Long cardId = null;
                Long userId = null;
                String findCardId = "select * from card_mapping where card_id= " + card_Id;
                System.out.println("");
                rs1 = eProctorDatabaseHandler.executeQuery(findCardId);
                while (rs1.next()) {
                    System.out.println("====>In");
                    cardId = rs1.getLong("id");
                }
                String findEmpIdQuery = "select * from system_user_detail where emp_id = '" + empId + "'";
                rs1 = aRSDatabaseHandler.executeQuery(findEmpIdQuery);
                while (rs1.next()) {
                    userId = rs1.getLong("user_id");
                }
                if (userId == null) {
                    userId = 0L;
                }
                if (cardId == null) {
                    cardId = 0L;
                }
                String query1 = "insert into card_log(card_enroll_number,card_punching_time,card_entry_reason,card_entry_type,user_id) values (" + cardId + ",'" + dateTime + "','" + entryReason + "'," + cheeckType + "," + userId + ")";
                System.out.println(query1);
                aRSDatabaseHandler.executeUpdate(query1);
            }
            aRSDatabaseHandler.releaseConnection();
            eProctorDatabaseHandler.releaseConnection();
            return maxDate;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }
}
