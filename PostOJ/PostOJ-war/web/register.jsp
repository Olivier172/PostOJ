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
        <title>JSP Page</title>
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
        <form method="post" action= "Controller.do">
            <p>
                
            </p>
            <input type="submit" value="register" name="forms">
        </form>
        
    </body>
</html>