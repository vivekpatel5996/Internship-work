<%-- 
    Document   : showemp
    Created on : 15 Feb, 2018, 9:59:00 AM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Employees</h1>
        <TABLE border="2">
            <% int count=1;%>
            <c:forEach var="emp" items="${employees}">
                <tr>  
                    <td><%=count%></td>
                    <td>${emp.id}</td>
                    <td>${deptname}</td>
                    <td>${emp.name}</td> 
                    <td>${emp.contactno}</td> 
                     <td><a href="/CompanyManagement/deleteemp/${emp.id}/${emp.dept_id}">Delete</a></td>
                    <td><a href="/CompanyManagement/editemp/${emp.id}">Edit</a></td>
                   
                </tr>     
            </c:forEach> 
        </TABLE>
        <br><br><br><br><br>
    </body>
</html>
