<%-- 
    Document   : inlogError
    Created on : 8-jan-2023, 14:01:30
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error bij inloggen</title>
    </head>
    <body>
        Is dit de correcte pagina voor jouw rechten, ik dacht het niet he
        <form method="post" action= <%=response.encodeRedirectURL(request.getContextPath() + "/Controller.do")%>> 
            <!--hier is geen actie hiddenfield omdat we niets willen registreren als we terug gaan -->
            <input type="hidden" name="naarWaar" value="index">
            <input type="submit" value="terug" name="forms">
        </form>
        
    </body>
</html>
