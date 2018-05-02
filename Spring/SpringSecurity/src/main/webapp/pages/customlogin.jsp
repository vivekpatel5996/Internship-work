<%-- 
    Document   : newjsp
    Created on : 17 Feb, 2018, 12:46:50 PM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        ${msg}
        
            

        <form name='form'   action="<c:url value='/login ' />" method='POST'>
            
            Enter UserName: <input type="text" name="username"/><br/><br/>
            Enter Password: <input type="password" name="password"/> <br/><br/>			
            <input type="submit" value="Login"/>
            <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

        </form>

    </body>
</html>
