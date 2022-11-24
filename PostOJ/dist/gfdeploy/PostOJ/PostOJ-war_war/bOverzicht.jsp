<%-- 
    Document   : bOverzicht
    Created on : 17-nov-2022, 9:21:40
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
        <h1>Bediende overzicht:</h1>
        <h4>
            Op de overzichtspagina moet de bediende een lijst zien van alle identificatienummers van pakketjes en hun status. 
            Er zijn drie mogelijke statussen: “transit”, “probleem” en “geleverd”.
            Een nieuw pakket begint altijd met de status “transit”. 
            Door op het identificatienummer van een pakket te klikken, 
            moet de bediende de detailgegevens van dit pakket kunnen raadplegen en eventueel wijzigen.
            De status van het pakket kan de bediende niet wijzigen.
        </h4>
        <form method="post" action= "Controller.do">
            <p>
                <table>
                    <tr>
                        <th>PacketID</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <td>test</td>
                        <td>test</td>
                    </tr>
                    <tr>
                        <td>test</td>
                        <td>test</td>
                    </tr>
                </table>
            </p>
            <input type="submit" value="toRegister" name="forms">
        </form>
        
    </body>
</html>
