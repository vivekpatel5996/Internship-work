<%-- 
    Document   : editemp
    Created on : 15 Feb, 2018, 10:37:02 AM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit employee</title>
    </head>
    <body>
        <h1>Edit Department</h1>
        <form:form method="post" action="/CompanyManagement/updateemp/">    
            <table>    
                <tr>    
                    <td><form:hidden path="id"/></td>  
                </tr>  
                <tr>    
                    <td><form:hidden path="dept_id"/></td>  
                </tr>  
                <tr>    
                    <td>employee Name : </td>   
                    <td><form:input path="name"/></td>  
                </tr>  

                <tr>    
                    <td>contact no : </td>      
                    <td><form:input path="contactno"/></td>  
                </tr> 
                <tr>    
                    <td> </td>    
                    <td><input type="submit" value="Update"/></td>    
                </tr>    
            </table>    
        </form:form>    
    </body>
</html>
