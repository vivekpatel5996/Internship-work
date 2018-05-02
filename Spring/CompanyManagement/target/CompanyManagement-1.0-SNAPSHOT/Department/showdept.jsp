<%-- 
    Document   : showdept
    Created on : 13 Feb, 2018, 7:06:02 PM
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
        <h1>Departments</h1>
        <TABLE border="2">
            <% int count=1;%>
            <c:forEach var="dept" items="${departments}">
                <tr>  
                    <td><%=count%></td>
                    <td>${dept.dept_name}</td>  
                    <td><a href="deletedept/${dept.id}">Delete</a></td>
                    <td><a href="editdept/${dept.id}">Edit</a></td>
                    <td><a href="addempform/${dept.id}">Add Employee</a></td>
                    <td><a href="allemployee/${dept.id}">View Employee</a></td>
                </tr>     
                <% count++;%>
            </c:forEach> 
        </TABLE>
        <br><br><br><br><br>
        <a href="adddeptform">Add Department</a>
    </body>
</html>
