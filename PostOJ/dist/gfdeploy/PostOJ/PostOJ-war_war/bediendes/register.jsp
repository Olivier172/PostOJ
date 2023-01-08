<%-- 
    Document   : register
    Created on : 17-nov-2022, 9:19:42
    Author     : r0723037
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registreren</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <h1>Register:</h1>
        <h4>
            Bij de registratie van een nieuw pakket, 
            krijgt dit pakket automatisch een nieuw uniek identificatienummer toegewezen.
            De bediende voert dan volgende gegevens in: het leveringsadres (bestaande uit naam, straat+nummer, postcode en gemeente),
            het gewicht van het pakket en eventuele commentaar over het pakket. 
            Tot slot moet de bediende het nieuwe pakket ook toewijzen aan een specifieke koerier 
            (bv. door middel van een drop-down menuutje). 
            Naast deze gegevens die manueel worden ingevuld, 
            moet er ook onthouden worden op welk moment het pakket werd toegevoegd.
        </h4>
        <form method="post" action= <%=response.encodeRedirectURL(request.getContextPath() + "/Controller.do")%>> 
            <p>
                <table>
                    <tr>
                        <td>Naam:</td>
                        <td><input type="text" name="naam"/></td>
                    </tr>
                    <tr>
                        <td>Adres:</td>
                        <td><input type="text" name="adres" placeholder="bv Abdijstraat 69"/></td>
                    </tr>
                    <tr>
                        <td>Postcode:</td>
                        <td><input type="number" name="postcode" placeholder="bv. 2000"/></td>
                    </tr>
                    <tr>
                        <td>Gemeente:</td>
                        <td><input type="text" name="gemeente" placeholder="bv. Antwerpen"/></td>
                    </tr>
                    <tr>
                        <td>Gewicht:</td>
                        <td><input type="number" name="gewicht"/> [kg]</td>
                    </tr>
                    <tr>
                        <td>Commentaar:</td>
                        <td><input type="text" name="commentaar"/></td>
                    </tr>
                    <tr>
                        <td>Kourier:</td>
                        <td>
                            <select name="kourierKeuze">
                            <c:forEach var="itr" items="${sessionScope.koeriers}">
                                <option value="${itr.getWid()}"> ${itr.getWnaam()} </option> 
                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </p>
            <input type="hidden" name="actie" value="nieuwpakket">
            <input type="hidden" name="naarWaar" value="bOverzicht">
            <input type="submit" value="registreer" name="forms">
        </form>
        <form method="post" action= <%=response.encodeRedirectURL(request.getContextPath() + "/Controller.do")%>> 
            <!--hier is geen actie hiddenfield omdat we niets willen registreren als we terug gaan -->
            <input type="hidden" name="naarWaar" value="bOverzicht">
            <input type="submit" value="terug" name="forms">
        </form>
        
    </body>
</html>
