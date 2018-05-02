<%-- 
    Document   : addattendance
    Created on : 22 Feb, 2018, 5:28:02 PM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.mycompany.companymanagement.employee.EmployeeDao"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add attendance</title>
    </head>
    <script type="text/javascript" >
       
          function myFunc() {
    var selectBox = document.getElementById("dept");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    alert(selectedValue);
    
   }
    </script>
    <body>
        <h1>Attendance</h1>
        <form:form action="" method="post">
            <table>
                <tr>
                    <td>Department:</td>
                    <td>
                        <form:select path="dept" onchange="myFunc()" id="dept">
                            <form:options items="${departments}" itemValue="id" itemLabel="dept_name"/>
                        </form:select>
                    </td>
                </tr>
                
              
                <tr>
                    <td>Employee:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Select option:</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add attendance"/> </td>
                </tr>
            </table>
        </form:form>
        
    </body>
</html>
