t<%-- 
    Document   : list-todos
    Created on : 28 Feb, 2018, 1:00:15 PM
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
        Here are the list of your todos:

        ${todos} 

        <BR/>

        Your Name is : ${name}
    </body>
</html>
