<%-- 
    Document   : kRedirect
    Created on : 8-jan-2023, 21:11:48
    Author     : MM
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:redirect url="/Controller.do?naarWaar=kOverzicht"/>
    </body>
</html>



