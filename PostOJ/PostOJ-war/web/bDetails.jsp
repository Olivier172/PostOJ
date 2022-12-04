<%-- 
    Document   : bDetails
    Created on : 17-nov-2022, 9:21:54
    Author     : r0723037
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details pakket ${sessionScope.pid}</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
                margin: 20px;
            }
            table {
                float : left;
            }
        </style>
    </head>
    
        <form method="post" action= "<c:url value='Controller.do'/>">
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
                        <td><input type="number" name="gewicht" value="${sessionScope.gewicht}"/> [kg]</td>
                    </tr>
                    <tr>
                        <td>commentaar</td>
                        <td><input type="text" name="commentaar" value="${sessionScope.commentaar}"/></td>
                    </tr>
                <table>
                    <caption>Bestemming</caption>
                    <tr>
                        <td>aid</td>
                        <td>${sessionScope.aid}</td>
                    </tr>
                    <tr>
                        <td>naam</td>
                        <td><input type="text" name="naam" value="${sessionScope.naam}"/></td>
                    </tr>
                    <tr>
                        <td>straatennr</td>
                        <td><input type="text" name="straatennr" value="${sessionScope.straatennr}"/></td>
                    </tr>
                    <tr>
                        <td>postcode</td>
                        <td><input type="number" name="postcode" value="${sessionScope.postcode}"/></td>
                    </tr>
                    <tr>
                        <td>gemeente</td>
                        <td><input type="text" name="gemeente" value="${sessionScope.gemeente}"/></td>
                    </tr>
                </table>
                <table>
                    <caption>Koerier</caption>
                    <tr>
                        <td>wid</td>
                        <td>${sessionScope.wid}</td>
                    </tr>
                    <tr>
                        <td>wnaam</td>
                        <td>${sessionScope.wnaam}</td>
                    </tr>
                    <tr>
                        <td>wfunctie</td>
                        <td>${sessionScope.wfunctie}</td>
                    </tr>
                    <tr>
                        <td style="border-top-width: 4px">Andere koerier toewijzen?<input type="checkbox" name="wijzigKoerier"/></td>
                        <td style="border-top-width: 4px">
                            <select name="kourierKeuze">
                            <c:forEach var="itr" items="${sessionScope.koeriers}">
                                <option value="${itr.getWid()}"> ${itr.getWnaam()} </option> 
                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            <input type="hidden" name="wid" value="${sessionScope.wid}">
            <input type="hidden" name="actie" value="updatepakket">
            <input type="hidden" name="naarWaar" value="bOverzicht">
            <c:if test="${ !(sessionScope.status.equals(\"geleverd\"))}">
                <input type="submit" value="update" name="forms">
            </c:if>
        </form>
        
        <form method="post" action= "<c:url value='Controller.do'/>">
            <!--hier is geen actie hiddenfield omdat we niets aanpassen als we teruggaan -->
            <input type="hidden" name="naarWaar" value="bOverzicht">
            <input type="submit" value="terug" name="forms">
        </form>
    

