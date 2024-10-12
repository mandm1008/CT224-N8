<%-- 
    Document   : index
    Created on : Oct 5, 2024, 9:06:30 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! <%
            User user = (User) request.getSession().getAttribute("user");
            if(user != null) out.println(user.username + "!!!");
        %></h1>
    </body>
</html>
