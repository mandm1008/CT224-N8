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
    <title>Enjoy Music - Signup</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="music-login-container">
        <div class="music-logo">
            <img src="images/music-logo.png" alt="Music Logo" onclick="window.location = '<%=request.getContextPath()%>'">
        </div>
        <h2>Create an Account</h2>
        
        <% String errorMessage = request.getParameter("err"); %>
        <div id="errorMessage" class="error-message" style="<%= (errorMessage != null) ? "display: block;" : "display: none;" %>">
            <%
                if (errorMessage != null && errorMessage.equals("userexists")) {
            %>
                <p>Username already exists!</p>
            <%
                } else if (errorMessage != null && errorMessage.equals("mismatch")) {
            %>
                <p>Passwords do not match!</p>
            <%
                }
            %>
        </div>
        
        <form action="api/signup" method="post" id="signupForm" onsubmit="handleSignupSubmit(event)">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required placeholder="Choose your username">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required placeholder="Enter your email">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Choose a password">
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Confirm your password">
            </div>
            <div class="form-group">
                <button type="submit" id="signupBtn">Sign Up</button>
            </div>
        </form>
        <div class="login-link">
            <p>Already have an account? <a href="login.jsp">Log in here</a></p>
        </div>
    </div>
    <script src="js/account.js"></script>
</body>
</html>
