<%-- 
    Document   : menulogout
    Created on : 4 Jul, 2018, 9:47:00 AM
    Author     : amruth
--%>

<%@page contentType="pdf" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
     <link rel='shortcut icon' href='images/exza.png'>
    </head>
    <body>
        <%
            session=request.getSession();
        
        session.removeAttribute("user");
        session.invalidate();
        
        response.sendRedirect("login.jsp");
            %>
    </body>
</html>
