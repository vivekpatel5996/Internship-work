<%-- 
    Document   : addemp
    Created on : 14 Feb, 2018, 5:51:33 PM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Employee</h1>
        <form:form method="post" action="/CompanyManagement/addemp">    
        <table> 
            <tr>    
<!--                <td>dept id: </td>   -->
                <td><form:hidden path="dept_id"/></td>  
            </tr><tr>    
<!--                <td> id: </td>   -->
                <td><form:hidden path="id"/></td>  
            </tr>
            
            <tr>    
                <td> name: </td>   
                <td><form:input path="name"/></td>  
            </tr>
            <tr>    
                <td> contact no: </td>   
                <td><form:input path="contactno"/></td>  
            </tr> 
            <tr>    
                <td> </td>    
                <td><input type="submit" value="Save" /></td>    
            </tr>    
        </table>    
    </form:form>    
        
    </body>
</html>
