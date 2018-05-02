<%-- 
    Document   : editdept
    Created on : 14 Feb, 2018, 4:53:57 PM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit department</title>
    </head>
    <body>
        <h1>Edit Department</h1>
        <form:form method="post" action="/CompanyManagement/edit">    
        <table>    
            <tr>    
                <td>Department Name : </td>   
                <td><form:input path="dept_name"/></td>  
            </tr>  
            <tr>    
                   
                <td><form:hidden path="id"/></td>  
            </tr> 
            <tr>    
                <td> </td>    
                <td><input type="submit" value="Update"/></td>    
            </tr>    
        </table>    
    </form:form>    
        
    </body>
</html>
