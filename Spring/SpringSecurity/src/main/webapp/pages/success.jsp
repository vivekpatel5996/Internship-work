<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <body>
        <table><tr><td>
Welcome!You are logged 
                    <c:if test="${msg=='true'}">

                        Welcome!You are logged in
                        <a href="j_spring_security_logout">logout </a>
                    </c:if>           
                    <c:if test="${msg=='false'}">
                        <p>Sorry!Try it again</p>
                        <a href="j_spring_security_logout">login </a>
                    </c:if>            
                </td></tr>
            <tr><td>

                </td></tr>
        </table>   
    </body>
</html>