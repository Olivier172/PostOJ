<%-- 
    Document   : tracking
    Created on : 17-nov-2022, 9:20:29
    Author     : r0723037
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PostOJ</title>
        <style>
            login {
                text-align:right;
            }
            form {
                margin: 10px
            }
        </style>
    </head>
    <body>
        <login>
            <form method="post" action= "Controller.do">
                <input type="hidden" name="naarWaar" value="bOverzicht">
                <input type="submit" value="login bediende" name="forms">
            </form>
            <form method="post" action= "Controller.do">
                <input type="hidden" name="naarWaar" value="kRedirect">
                <input type="submit" value="login koerier" name="forms">
            </form>
        </login>
        <h1>INDEX:</h1> 
        
        <h4>
            Iedereen: Naast bovenstaande afgeschermde pagina’s, 
            is er ook een publieke pagina, 
            die voor de hele wereld toegankelijk is. 
            Hierop kan een gebruiker een identificatienummer ingeven 
            en dan de status van dit pakketje te zien krijgen.
        </h4>
        <form method="post" action= "<c:url value='Controller.do'/>">
            <p>
                packetID:
                <input type="number" name="pid"/>
            </p>
            <input type="hidden" name="naarWaar" value="tracking">
            <input type="submit" value="Track" name="forms">
        </form>
            
        
        
    </body>
   
        
             
         
        
    
</html>
