<%-- 
    Document   : tracking
    Created on : 17-nov-2022, 15:43:13
    Author     : r0723037
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, th, td {
                border: 1px solid;
            }
        </style>
    </head>
    
    <body>
        <h1>Tracking:</h1>
        <h4>
            Iedereen: Naast bovenstaande afgeschermde pagina’s, 
            is er ook een publieke pagina, 
            die voor de hele wereld toegankelijk is. 
            Hierop kan een gebruiker een identificatienummer ingeven 
            en dan de status van dit pakketje te zien krijgen.
        </h4>
        <p>
            <table><tr>
                <th>PacketID</th>
                <th>Status</th>
            </tr>
            <tr>
                <td> <c:out value = "${sessionScope['packetID']}"/>  </td>
                <td>test</td>
            </tr></table>
            
        </p>
        <form method="post" action= "Controller.do">
            <input type="submit" value="return" name="forms">
        </form>
    </body>
</html>
