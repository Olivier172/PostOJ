<%-- 
    Document   : inlog
    Created on : 8-jan-2023, 14:00:46
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inloggen</title>
    </head>
    <body>
        <h1>Loginscreen</h1>
        <p> Log je hier in voor de les Applicatie-Architecuren:
        <form method="post" action="j_security_check">
            <table>
                <tr> <td> Username (wnaam): </td> <td> <input type="text" name="j_username" /></td></tr>
                <tr> <td> Password (wnaam):</td><td><input type="password" name="j_password" /></td></tr>
            </table>
            
            <input type="submit" />
        </form>
    </body>
</html>
