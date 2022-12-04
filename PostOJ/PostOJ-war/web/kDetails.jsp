<%-- 
    Document   : kDetails
    Created on : 17-nov-2022, 9:22:37
    Author     : Olivier en Jorn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details pakket</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
                margin: 20px;
            }
            table {
                float : left;
            }
            form {
                margin :10px;
            }
        </style>
    </head>
    <body>
        <h1>Details pakket met pid ${sessionScope.pid}</h1>
        <table>
            <caption>Pakketinfo</caption>
            <tr>
                <td>pid</td>
                <td>${sessionScope.pid}</td>
            </tr>
            <tr>
                <td>Status</td>
                <td>${sessionScope.status}</td>
            </tr>
            <tr>
                <td>Datum</td>
                <td>${sessionScope.datum}</td>
            </tr>
            <tr>
                <td>Tijd</td>
                <td>${sessionScope.tijd}</td>
            </tr>
            <tr>
                <td>gewicht</td>
                <td>${sessionScope.gewicht} [kg]</td>
            </tr>
            <tr>
                <td>commentaar</td>
                <td>${sessionScope.commentaar}</td>
            </tr>
        <table>
            <caption>Bestemming</caption>
            <tr>
                <td>aid</td>
                <td>${sessionScope.aid}</td>
            </tr>
            <tr>
                <td>naam</td>
                <td>${sessionScope.naam}</td>
            </tr>
            <tr>
                <td>straatennr</td>
                <td>${sessionScope.straatennr}</td>
            </tr>
            <tr>
                <td>postcode</td>
                <td>${sessionScope.postcode}</td>
            </tr>
            <tr>
                <td>gemeente</td>
                <td>${sessionScope.gemeente}</td>
            </tr>
        </table>
        <form method="post" action= "<c:url value='Controller.do'/>">
            <input type="hidden" name="actie" value="updatestatus">
            <input type="hidden" name="naarWaar" value="kOverzicht">
            <input type="hidden" name="pid" value="${sessionScope.pid}">
            <input type="submit" name="forms" value="geleverd">
            <input type="submit" name="forms" value="probleem">
        </form>
            
        <form method="post" action= "<c:url value='Controller.do'/>">
            <!--hier is geen actie hiddenfield omdat we niets aanpassen als we teruggaan -->
            <input type="hidden" name="naarWaar" value="kOverzicht">
            <input type="submit" value="terug" name="forms">
        </form>
    </body>
</html>
