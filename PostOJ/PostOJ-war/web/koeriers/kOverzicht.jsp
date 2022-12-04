<%-- 
    Document   : kOverzicht
    Created on : 17-nov-2022, 9:22:23
    Author     : Olivier en Jorn
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koerier overzicht</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
                margin: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Koeriers overzicht:</h1>
        <h4>
            Als een koerier inlogt, krijgt hij onmiddellijk een overzicht te zien van alle pakketjes die aan hem zijn toegewezen. 
            Voor elk pakket moet hij het identificatienummer en de status zien.
            Pakketten die al langer dan 48 de status “transit” hebben, 
            moeten speciaal worden aangeduid op deze pagina. 
            Door op een identificatienummer te klikken, 
            moet een koerier de detailgegevens van het pakket kunnen raadplegen. 
            Op deze detail-pagina moeten er ook knoppen aanwezig zijn waarmee de koerier kan aangeven 
            dat hij het pakket geleverd heeft of dat er een probleem was.
            Door op één van deze knoppen te klikken, wijzigt de status van het pakket. 
            Eénmaal een pakket “geleverd” is, kan zijn status niet meer veranderd worden. 
            De status van een “probleem” pakket kan wel nog veranderen naar “geleverd”.
        </h4>
        
        <h1>Welkom koerier met wid ${sessionScope.wid} </h1>
        <form method="post" action= "<c:url value='Controller.do'/>">
            <table>
                <tr>
                    <th>PacketID</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="itr" items="${sessionScope.pakketjes_koerier}">
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
        <input type="hidden" name="naarWaar" value="kDetails">
        </form>
            
        <form method="post" action= "<c:url value='Controller.do'/>">
            <input type="hidden" name="naarWaar" value="index">
            <input type="submit" value="log uit" name="forms">
        </form>
        
    </body>
</html>
