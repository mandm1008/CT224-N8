<%-- 
    Document   : register
    Created on : Oct 5, 2024, 10:56:52 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Music Register</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="music-login-container">
        <div class="music-logo">
            <img src="images/music-logo.png" alt="Music Logo">
        </div>
        <h2>Login to Enjoy Music</h2>
        
        <% String errorMessage = request.getParameter("err"); %>
        <div id="errorMessage" class="error-message" style="<%= (errorMessage != null) ? "display: block;" : "display: none;" %>">
            <%
                if (errorMessage != null && errorMessage.equals("invalid")) {
            %>
                <p>Username or password is wrong!</p>
            <%
                }
            %>
        </div>
        
        <form action="api/login" method="post" id="loginForm">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required placeholder="Enter your username">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" required placeholder="Enter your email">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Enter your password">
            </div>
            <div class="form-group">
                <button type="submit" id="loginBtn">Registe</button>
            </div>
        </form>
        <div class="signup-link">
            <p>You have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
    <script src="js/login.js"></script>
</body>
</html>
