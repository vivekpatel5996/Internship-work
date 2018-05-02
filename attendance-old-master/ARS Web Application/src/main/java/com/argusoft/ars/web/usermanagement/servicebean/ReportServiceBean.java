/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.servicebean;

import com.argusoft.ars.web.leavemanagement.databean.LeaveQuotaDataBean;
import com.argusoft.ars.web.personal.databean.AttendanceRegisterDataBean;
import com.argusoft.ars.web.usermanagement.databean.*;
import com.argusoft.ars.web.usermanagement.transformerbean.ReportTransformerBean;
import com.argusoft.ars.web.usermanagement.transformerbean.UserTransformerBean;
import com.argusoft.ars.web.util.ReportGenerator;
import com.argusoft.ars.web.util.SystemResultViewUtil;
import com.argusoft.usermanagement.common.core.UserService;
import com.argusoft.usermanagement.common.exception.UserManagementException;
import com.argusoft.usermanagement.common.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author sudhir
 */
@ManagedBean(name = "reportServiceBean")
@RequestScoped
public class ReportServiceBean {

    @ManagedProperty(value = "#{cardLogDataBean}")
    private CardLogDataBean cardLogDataBean;
    @ManagedProperty(value = "#{attendanceDataBean}")
    private AttendanceDataBean attendanceDataBean;
    @ManagedProperty(value = "#{messageDataBean}")
    private MessageDataBean messageDataBean;
    @ManagedProperty(value = "#{reportTransformerBean}")
    private ReportTransformerBean reportTransformerBean;
    @ManagedProperty(value = "#{systemResultViewUtil}")
    private SystemResultViewUtil systemResultViewUtil;
    @ManagedProperty(value = "#{loginDataBean}")
    private LoginDataBean loginDataBean;
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    @ManagedProperty(value = "#{consecutiveMailDataBean}")
    private ConsecutiveMailDataBean consecutiveMailDataBean;
    @ManagedProperty(value = "#{userTransformerBean}")
    private UserTransformerBean userTransformerBean;
    private Date fromDate;
    private Date toDate;
    private Long id;
    private Integer totalWorkingDays;

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public UserTransformerBean getUserTransformerBean() {
        return userTransformerBean;
    }

    public void setUserTransformerBean(UserTransformerBean userTransformerBean) {
        this.userTransformerBean = userTransformerBean;
    }

    public LoginDataBean getLoginDataBean() {
        return loginDataBean;
    }

    public void setLoginDataBean(LoginDataBean loginDataBean) {
        this.loginDataBean = loginDataBean;
    }

    public ConsecutiveMailDataBean getConsecutiveMailDataBean() {
        return consecutiveMailDataBean;
    }

    public void setConsecutiveMailDataBean(ConsecutiveMailDataBean consecutiveMailDataBean) {
        this.consecutiveMailDataBean = consecutiveMailDataBean;
    }

    public CardLogDataBean getCardLogDataBean() {
        return cardLogDataBean;
    }

    public void setCardLogDataBean(CardLogDataBean cardLogDataBean) {
        this.cardLogDataBean = cardLogDataBean;
    }

    public AttendanceDataBean getAttendanceDataBean() {
        return attendanceDataBean;
    }

    public void setAttendanceDataBean(AttendanceDataBean attendanceDataBean) {
        this.attendanceDataBean = attendanceDataBean;
    }

    public SystemResultViewUtil getSystemResultViewUtil() {
        return systemResultViewUtil;
    }

    public void setSystemResultViewUtil(SystemResultViewUtil systemResultViewUtil) {
        this.systemResultViewUtil = systemResultViewUtil;
    }

    public MessageDataBean getMessageDataBean() {
        return messageDataBean;
    }

    public void setMessageDataBean(MessageDataBean messageDataBean) {
        this.messageDataBean = messageDataBean;
    }

    public ReportTransformerBean getReportTransformerBean() {
        return reportTransformerBean;
    }

    public void setReportTransformerBean(ReportTransformerBean reportTransformerBean) {
        this.reportTransformerBean = reportTransformerBean;
    }

    public void retrieveCardLog() throws UserManagementException {
        try {
            systemResultViewUtil.setCardLogDataBeanList(reportTransformerBean.retrieveCardLog(cardLogDataBean.getId(), cardLogDataBean.getFromDate(), cardLogDataBean.getToDate()));
            messageDataBean.setIsSuccess(Boolean.TRUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void employeeLastPunchingCardLogDetail() {
        try {
            systemResultViewUtil.setCardLogDataBeanList(reportTransformerBean.retrieveEmployeeLastPunchingCardLogDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieveTodaysAttendance() throws UserManagementException {
        try {
            systemResultViewUtil.setTodaysAttendanceDataBeanList(reportTransformerBean.retrieveTodaysAttendanceCardLog());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieveUserName() throws UserManagementException {
        systemResultViewUtil.setUserDataBeanList(reportTransformerBean.retrieveUserList());

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void retrieveAttendance() throws UserManagementException, ParseException {
        try {
            Integer noOfDays = (int) ((attendanceDataBean.getToDate().getTime() - attendanceDataBean.getFromDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
            totalWorkingDays = reportTransformerBean.totalWorkingDays(attendanceDataBean.getFromDate(), attendanceDataBean.getToDate());
            int allowableLateMinitue = reportTransformerBean.getAllowableLateMinites();
            List<AttendanceDataBean> attendanceOfAllUser = null;
            if (attendanceDataBean.getId() == null) {
//                List<User> user = userService.getAllActiveUsers();
//                for (int i = 0; i < user.size(); i++) {
                attendanceOfAllUser = reportTransformerBean.retrieveAttendance1(null, attendanceDataBean.getFromDate(), attendanceDataBean.getToDate(), noOfDays, allowableLateMinitue);
//                    AttendanceDataBean attendanceDataBean1 = reportTransformerBean.retrieveAttendance(user.get(i).getId(), attendanceDataBean.getFromDate(), attendanceDataBean.getToDate(), noOfDays, allowableLateMinitue);
//                    if (attendanceDataBean1 != null) {
//                        attendanceOfAllUser.add(attendanceDataBean1);
//                    }
//                }
            } else {
//                AttendanceDataBean attendanceDataBean1 = reportTransformerBean.retrieveAttendance(attendanceDataBean.getId(), attendanceDataBean.getFromDate(), attendanceDataBean.getToDate(), noOfDays, allowableLateMinitue);
//                if (attendanceDataBean1 != null) {
                attendanceOfAllUser = reportTransformerBean.retrieveAttendance1(attendanceDataBean.getId(), attendanceDataBean.getFromDate(), attendanceDataBean.getToDate(), noOfDays, allowableLateMinitue);
//                    attendanceOfAllUser.add(attendanceDataBean1);
//                }
            }
            messageDataBean.setIsSuccess(true);
            systemResultViewUtil.setAttendanceDataBeanList(attendanceOfAllUser);
        } catch (Exception e) {
            System.out.println("Exception -----------" + e);
            e.printStackTrace();
        }
    }

    public void retrievePresentDays() throws UserManagementException {
        try {
            systemResultViewUtil.setCardLogDataBeanList(reportTransformerBean.retrievePresentDays(getId(), fromDate, toDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieveLateDays() throws UserManagementException {
        systemResultViewUtil.setCardLogDataBeanList(reportTransformerBean.retrieveLateDays(getId(), fromDate, toDate));
    }

    public void retrieveAbsentDays() throws UserManagementException {
        systemResultViewUtil.setCardLogDataBeanList(reportTransformerBean.retrieveAbsentDays(getId(), fromDate, toDate));

    }

    public void retrieveConsecutivemailList() throws UserManagementException {
        List<ConsecutiveMailDataBean> consecutiveList = new ArrayList<ConsecutiveMailDataBean>();
        List<User> user = userService.getAllActiveUsers();
        for (int i = 0; i < user.size(); i++) {
            consecutiveMailDataBean.setUserId(user.get(i).getId());
            consecutiveList.addAll(reportTransformerBean.retrieveConsecutiveMailList(consecutiveMailDataBean));
        }
        if (consecutiveList != null && !consecutiveList.isEmpty()) {
            reportTransformerBean.sendMailToConsecutiveLateAndOnTime(consecutiveList);
        }
        systemResultViewUtil.setConsecutiveMailList(consecutiveList);

    }

    public void convertCardLogReportToPdf() {
        try {
            String empName = "All";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            byte[] report;
            List<CardLogDataBean> cardLogDataBeans = systemResultViewUtil.getCardLogDataBeanList();
            Collections.sort(cardLogDataBeans, new Comparator<CardLogDataBean>() {

                @Override
                public int compare(CardLogDataBean o1, CardLogDataBean o2) {
                    if (o1.getCardPunchingTime() != null && o2.getCardPunchingTime() != null) {
                        return o2.getCardPunchingTime().compareTo(o1.getCardPunchingTime());
                    } else {
                        return 0;
                    }
                }
            });
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(cardLogDataBeans);
            Map parameters = new HashMap();
            parameters.put("imageLink", facesContext.getExternalContext().getRealPath("/images/header.png").toString());
            parameters.put("fromDate", cardLogDataBean.getFromDate());
            parameters.put("toDate", cardLogDataBean.getToDate());
            if (cardLogDataBean.getId() != null) {
                empName = userTransformerBean.reriveUserNameByUserId(cardLogDataBean.getId());
            }
            parameters.put("empName", empName);
            report = JasperRunManager.runReportToPdf(facesContext.getExternalContext().getRealPath("/jasperReport/cardLogReportForAllEmployee.jasper"), parameters, beanColDataSource);
            final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename= cardLogReport - " + dateFormater(new Date()) + ".pdf"); // or whatever type you're sending back
            response.getOutputStream().write(report); // from the UploadDetails bean
            response.setContentLength(report.length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void convertAttendanceReportToPdf() {
        try {
            String empName = "All";
            String workingDays = "Working Days : " + this.totalWorkingDays;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            byte[] report;
            List<AttendanceDataBean> attendanceDataBeans = systemResultViewUtil.getAttendanceDataBeanList();
            Collections.sort(attendanceDataBeans, new Comparator<AttendanceDataBean>() {

                @Override
                public int compare(AttendanceDataBean o1, AttendanceDataBean o2) {
                    if (o1.getUserName() != null && o2.getUserName() != null) {
                        return o1.getUserName().toLowerCase().compareTo(o2.getUserName().toLowerCase());
                    } else {
                        return 0;
                    }
                }
            });
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(attendanceDataBeans);
            Map parameters = new HashMap();
            parameters.put("imageLink", facesContext.getExternalContext().getRealPath("/images/header.png").toString());
            parameters.put("fromDate", attendanceDataBean.getFromDate());
            parameters.put("toDate", attendanceDataBean.getToDate());
            parameters.put("workingDays", workingDays);
            if (attendanceDataBean.getId() != null) {
                empName = userTransformerBean.reriveUserNameByUserId(attendanceDataBean.getId());
            }
            parameters.put("empName", empName);
            report = JasperRunManager.runReportToPdf(facesContext.getExternalContext().getRealPath("/jasperReport/attendanceReport.jasper"), parameters, beanColDataSource);
            final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename= AttendanceReport - " + dateFormater(new Date()) + ".pdf"); // or whatever type you're sending back
            response.getOutputStream().write(report); // from the UploadDetails bean
            response.setContentLength(report.length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void convertLeaveQuotaBeanToPdf() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            byte[] report;
            List<LeaveQuotaDataBean> leaveQuotaDataBeans = systemResultViewUtil.getLeaveQuotaDataBeansList();
            Collections.sort(leaveQuotaDataBeans, new Comparator<LeaveQuotaDataBean>() {

                @Override
                public int compare(LeaveQuotaDataBean o1, LeaveQuotaDataBean o2) {
                    if (o1.getUserName() != null && o2.getUserName() != null) {
                        return o1.getUserName().toLowerCase().compareTo(o2.getUserName().toLowerCase());
                    } else {
                        return 0;
                    }
                }
            });
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(leaveQuotaDataBeans);
            Map parameters = new HashMap();
            parameters.put("imageLink", facesContext.getExternalContext().getRealPath("/images/header.png").toString());
            report = JasperRunManager.runReportToPdf(facesContext.getExternalContext().getRealPath("/jasperReport/employeeLeaveQuota.jasper"), parameters, beanColDataSource);
            final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename= LeaveQuotaReport - " + dateFormater(new Date()) + ".pdf"); // or whatever type you're sending back
            response.getOutputStream().write(report); // from the UploadDetails bean
            response.setContentLength(report.length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void downloadAttendanceRegister() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM - yyyy");
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTime(fromDate);
            Calendar toCal = Calendar.getInstance();
            toCal.setTime(fromDate);
            toCal.set(Calendar.DAY_OF_MONTH, toCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Integer noOfDays = (int) ((toCal.getTime().getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
            String d[] = new String[31];
            for (int i = 0; i < noOfDays; i++) {
                SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
                d[i] = simpleDateformat.format(fromCal.getTime()).toUpperCase();
                if (reportTransformerBean.isCasualHoliday(fromCal.getTime())) {
                    d[i] += "H";
                }
                fromCal.add(Calendar.DAY_OF_MONTH, 1);

            }
            totalWorkingDays = reportTransformerBean.totalWorkingDays(fromDate, toCal.getTime());
            systemResultViewUtil.setAttendanceRegisterDataBeans(reportTransformerBean.retrieveAttendanceRegisterDetailList1(fromDate));
            Collections.sort(systemResultViewUtil.getAttendanceRegisterDataBeans(), new Comparator() {

                @Override
                public int compare(Object o1, Object o2) {
                    AttendanceRegisterDataBean attendanceRegisterDataBean = (AttendanceRegisterDataBean) o1;
                    AttendanceRegisterDataBean attendanceRegisterDataBeanTemp = (AttendanceRegisterDataBean) o2;
                    return attendanceRegisterDataBean.getEmpName().compareTo(attendanceRegisterDataBeanTemp.getEmpName());
                }
            });
            FacesContext facesContext = FacesContext.getCurrentInstance();
            byte[] report;
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(systemResultViewUtil.getAttendanceRegisterDataBeans());
            Map parameters = new HashMap();
            parameters.put("1", d[0]);
            parameters.put("2", d[1]);
            parameters.put("3", d[2]);
            parameters.put("4", d[3]);
            parameters.put("5", d[4]);
            parameters.put("6", d[5]);
            parameters.put("7", d[6]);
            parameters.put("8", d[7]);
            parameters.put("9", d[8]);
            parameters.put("10", d[9]);
            parameters.put("11", d[10]);
            parameters.put("12", d[11]);
            parameters.put("13", d[12]);
            parameters.put("14", d[13]);
            parameters.put("15", d[14]);
            parameters.put("16", d[15]);
            parameters.put("17", d[16]);
            parameters.put("18", d[17]);
            parameters.put("19", d[18]);
            parameters.put("20", d[19]);
            parameters.put("21", d[20]);
            parameters.put("22", d[21]);
            parameters.put("23", d[22]);
            parameters.put("24", d[23]);
            parameters.put("25", d[24]);
            parameters.put("26", d[25]);
            parameters.put("27", d[26]);
            parameters.put("28", d[27]);
            parameters.put("29", d[28]);
            parameters.put("30", d[29]);
            parameters.put("31", d[30]);
            parameters.put("totalWorkingDay", totalWorkingDays);
            parameters.put("month", sdf.format(fromDate).toUpperCase());
            parameters.put("totmonthdays", noOfDays);
//            report = JasperRunManager.runReportToPdf(facesContext.getExternalContext().getRealPath("/jasperReport/rptAttendanceRegister.jasper"), parameters, beanColDataSource);
            JasperPrint print = JasperFillManager.fillReport(facesContext.getExternalContext().getRealPath("/jasperReport/rptAttendanceRegister.jasper"), parameters, beanColDataSource);
            ReportGenerator rpt = new ReportGenerator();
            System.out.println("== Path :" + facesContext.getExternalContext().getRealPath("/jasperReport/rptAttendanceRegister.pdf"));
            JasperExportManager.exportReportToPdfFile(print, facesContext.getExternalContext().getRealPath("/jasperReport/rptAttendanceRegister.pdf"));
            rpt.showReport(facesContext.getExternalContext().getRealPath("/jasperReport/rptAttendanceRegister.pdf"), "Attendance_Register");
//            final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.setContentType("application/pdf");
//            response.setHeader("Content-Disposition", "attachment;filename= AttendanceRegister - " + sdf.format(fromDate).toUpperCase() + ".pdf"); // or whatever type you're sending back
//            response.getOutputStream().write(report); // from the UploadDetails bean
//            response.setContentLength(report.length);
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("======>Error" + e);
            e.printStackTrace();
        }
    }

    private String dateFormater(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }
}
