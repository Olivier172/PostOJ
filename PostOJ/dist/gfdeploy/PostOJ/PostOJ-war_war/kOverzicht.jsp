<%-- 
    Document   : kOverzicht
    Created on : 17-nov-2022, 9:22:23
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
        <form method="post" action= "Controller.do">
            <p>
                
            </p>
            <input type="submit" value="koverzicht" name="forms">
        </form>
        
    </body>
</html>
