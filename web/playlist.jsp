<%-- 
    Document   : playlist
    Created on : Nov 16, 2024, 7:26:52 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.PlaylistModel"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Playlist</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/playlist.css">
</head>
<body>
    <h1>Danh Sách Playlist</h1>
    <a href="create_playlist.jsp">Tạo Playlist Mới</a>
    <ul>
        <%
            int userId = (int) session.getAttribute("userId"); // Lấy userId từ session
            LinkedList<PlaylistModel> playlists = new PlaylistModel().findByUserId(userId);

            for (PlaylistModel playlist : playlists) {
        %>
            <li>
                <a href="PlaylistDetail.jsp?playlistId=<%= playlist.getPlaylistId() %>">
                    <%= playlist.getName() %>
                </a>
            </li>
        <%
            }
        %>
    </ul>
</body>
</html>
