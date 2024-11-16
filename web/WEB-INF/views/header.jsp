<%-- 
    Document   : header
    Created on : Oct 12, 2024, 12:13:42 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.User" %>

<% String contextPath = request.getContextPath(); %>
<% User user = (User) request.getSession().getAttribute("user"); %>

<script>
    var contextPath = "<%=contextPath%>";
</script>
<script src="js/head.js" defer></script>

<header class="header">
    <div class="container">
        <div class="logo">
            <a href="<%=contextPath%>">Enjoy Music</a>
        </div>
        <nav class="nav-links">
            <a href="<%=contextPath%>">Trang chủ</a>
            <a href="<%=contextPath%>">BXH</a>
            <a href="<%=contextPath%>">Thể loại</a>
        </nav>
        <div class="search-bar">
            <input id="searchInput" type="text" placeholder="Tìm kiếm bài hát, ca sĩ...">
            <button id="searchButton">Tìm kiếm</button>
        </div>
        
        <% if (user == null) { %>
            <div class="auth-buttons">
                <a href="<%=contextPath%>/login.jsp">Đăng nhập</a>
                <a href="<%=contextPath%>/signup.jsp">Đăng ký</a>
            </div>
        <% } %>
        
        <% if (user != null) { %>
            <div class="user-info">
                <img class="avatar" src="<%=contextPath%>/images/avatar.jpg" alt="<%=user.username%>">
                <span class="username"><%=user.username%></span>
            </div>
        <% } %>
    </div>
</header>
