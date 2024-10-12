<%-- 
    Document   : header
    Created on : Oct 12, 2024, 12:13:42 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.User" %>

<% String contextPath = request.getContextPath(); %>
<% User user = (User) request.getSession().getAttribute("user"); %>

<header class="header">
    <div class="container">
        <div class="logo">
            <a href="#">Enjoy Music</a>
        </div>
        <nav class="nav-links">
            <a href="#">Trang chủ</a>
            <a href="#">BXH</a>
            <a href="#">Thể loại</a>
        </nav>
        <div class="search-bar">
            <input type="text" placeholder="Tìm kiếm bài hát, ca sĩ...">
            <button>Tìm kiếm</button>
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
