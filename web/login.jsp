<%-- 
    Document   : login
    Created on : Oct 5, 2024, 8:37:39 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enjoy Music - Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="music-login-container">
        <div class="music-logo">
            <img src="images/music-logo.png" alt="Music Logo" onclick="window.location = '<%=request.getContextPath()%>'">
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
        
        <form action="api/login" method="post" id="loginForm" onsubmit="handleLoginSubmit(event)">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required placeholder="Enter your username">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Enter your password">
            </div>
            <div class="form-group">
                <button type="submit" id="loginBtn">Login</button>
            </div>
        </form>
        <div class="signup-link">
            <p>Don't have an account? <a href="signup.jsp">Sign up here</a></p>
        </div>
    </div>
    <script src="js/account.js"></script>
</body>
</html>

