<%-- 
    Document   : bOverzicht
    Created on : 17-nov-2022, 9:21:40
    Author     : Olivier en Jorn
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overzicht bediendes</title>
        <style>
            table, th, td {
                border: 1px solid;
                 border-collapse: collapse;
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
        <form method="post" action= "<c:url value='Controller.do'/>">
           
            <table>
                <tr>
                    <th>PacketID</th>
                    <th>Status</th>
                </tr>       
                <c:forEach var="itr" items="${sessionScope.pakketjes}">
                <jsp:useBean id="now" class="java.util.Date"/> <!-- huidig tijd-->
                <c:choose>
                    <c:when test="${itr.getStatus().equals('geleverd')}">
                        <tr style="background-color: green"> 
                            <td><input type="submit" value="${itr.getPid()}" name="forms"></td>
                            <td>${itr.getStatus()}</td>
                        </tr> 
                    </c:when>
                    <c:when test="${itr.getStatus().equals('probleem')}">
                        <tr style="background-color: red"> 
                            <td><input type="submit" value="${itr.getPid()}" name="forms"></td>
                            <td>${itr.getStatus()}</td>
                        </tr> 
                    </c:when>
                    <c:when test="${( now.time - itr.getDatum().getTime() > 48*60*60*1000) && (itr.getStatus().equals('transit')) }"><!-- //zijn we al 48 uur verder? , time wordt gerekent in millis sinds 1970-->
                        <tr style="background-color: orange"> 
                            <td><input type="submit" value="${itr.getPid()}" name="forms"></td>
                            <td>${itr.getStatus()}</td>
                        </tr>  
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td><input type="submit" value="${itr.getPid()}" name="forms"></td>
                            <td>${itr.getStatus()}</td>
                        </tr> 
                    </c:otherwise>
                </c:choose>      
                </c:forEach>
            </table>
            <p>
                Legende kleuren: <br/>
                <span style="color:green">Groen</span> = geleverd <br/>
                <span style="color:orange">oranje</span> = het pakketje is al 48u in transit <br/>
                <span style="color:red">rood</span> = er is een probleem met het pakketje <br/>
                Geen kleur= pakketje nog niet geleverd maar het is nog geen 48h in transit.
            </p>
            
        <input type="hidden" name="naarWaar" value="bDetails">
        </form>
        
        <form method="post" action= "<c:url value='Controller.do'/>">
            <input type="hidden" name="naarWaar" value="register">
            <input type="submit" value="Een nieuw pakket registreren" name="forms">
        </form>
            
        <form method="post" action= "<c:url value='Controller.do'/>">
            <input type="hidden" name="naarWaar" value="index">
            <input type="submit" value="log uit" name="forms">
        </form>
        
    </body>
</html>
