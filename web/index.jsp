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
        <title>Enjoy Music - Home</title>

        <%@include file="/WEB-INF/views/header-head.jsp" %>        
        <%@include file="/WEB-INF/views/player-head.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />
        
        
        <h1>
            Danh sach nhac
            <%
                User user = (User) request.getSession().getAttribute("user");
                if (user != null)
                    out.println(user.username + "!!!");
            %>
        </h1>
        <<h1>Bang xep hang</h1>
        <table id="ranking-table">
            <thead>
                <tr>
                    <th>Thu hang</th>
                    <th>Bai hat</th>
                    <th>Nghe si</th>
                    <th>Luot nghe</th>
                </tr>
            </thead>
            <tbody>
            
            </tbody>
        </table>

        <jsp:include page="/WEB-INF/views/player.jsp" />
    </body>
</html>
