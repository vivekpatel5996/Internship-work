<%-- 
    Document   : LOGIN
    Created on : 28 Feb, 2018, 12:45:05 PM
    Author     : vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <font color="red">${errorMessage}</font>

            <form method="post" action="loginsubmit">

        Name : <input type="text" name="name" />

        Password : <input type="password" name="password" /> 

        <input type="submit" />

    </form>
    </body>
</html>
