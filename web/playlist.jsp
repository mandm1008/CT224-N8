<%-- 
    Document   : playlist
    Created on : Nov 16, 2024, 7:26:52 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.PlaylistModel"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="db.SongModel"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="db.UserMusic"%>
<%@page import="DAO.User" %>
<%@page import="DAO.Song" %>
<%
    User user = (User) session.getAttribute("user");
    
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int userId = user.userId;

    LinkedList<PlaylistModel> playlists = new PlaylistModel().findByUserId(userId);
    LinkedList<SongModel> mostViewSongs = SongModel.getMostViewSongs(9);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Playlist</title>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/playlist.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/song.css">
</head>
<body>
    <h1>Danh Sách Playlist</h1>
    <a href="index.jsp">Tạo Playlist Mới</a>

    <!-- Form để xóa playlist -->
    <form action="DeletePlaylistServlet" method="POST">
        <ul>
            <% for (PlaylistModel playlist : playlists) { %>
                <li>
                    <!-- Hiển thị tên playlist và liên kết -->
                    <a href="PlaylistDetail.jsp?playlistId=<%= playlist.getPlaylistId() %>">
                        <%= playlist.getName() %>
                    </a>
                    <!-- Checkbox để chọn playlist cần xóa -->
                    <input type="checkbox" name="playlistIds" value="<%= playlist.getPlaylistId() %>"> Xóa
                    
                </li>
            <% } %>
        </ul>

        <!-- Nút gửi danh sách playlist cần xóa -->
        <button type="submit">Xóa Playlist Đã Chọn</button>
    </form>

    <!-- Form tạo playlist mới -->
    <div class="playlist-form">
        <h2>Tạo Danh Sách Phát</h2>
        <form action="AddSongToPlaylistServlet" method="POST">
            <!-- Nhập tên danh sách phát -->
            <div class="form-group">
                <label for="playlistName">Tên danh sách phát:</label>
                <input type="text" id="playlistName" name="playlistName" class="form-control" placeholder="Nhập tên danh sách phát" required>
            </div>
            <br>

            <!-- Chuyển userId từ session -->
            <input type="hidden" name="userId" value="<%= userId %>">
            <input type="hidden" name="playlistId" value="<%= request.getAttribute("playlistId") != null ? request.getAttribute("playlistId") : "" %>">

            <% 
                String playlistId = (String) request.getAttribute("playlistId");
                if (playlistId == null) {
                    playlistId = request.getParameter("playlistId"); // Lấy từ URL nếu cần
                }
                if (playlistId == null) {
                    playlistId = (String) session.getAttribute("playlistId"); // Lấy từ session
                }
                if (playlistId == null) {
                    playlistId = ""; // Đảm bảo không null
                }
            %>

            <!-- Danh sách bài hát -->
            <h2>Chọn Bài Hát</h2>
            <div class="song-list">
                <% if (mostViewSongs != null && mostViewSongs.size() > 0) {
                        for (int i = 0; i < mostViewSongs.size(); i++) {
                            Song song = mostViewSongs.get(i).toSong();
                %>
                <div class="music-element">
                    <!-- Ảnh bài hát -->
                    <img src="<%= song.image %>" alt="<%= song.title %>" class="music-image"/>

                    <div class="music-info">
                        <p class="music-info--title"><%= song.title %></p>
                        <p class="music-info--artist">
                            <span><%= song.artistName %></span>
                        </p>
                    </div>

                    <!-- Checkbox để chọn bài hát -->
                    <label class="music-checkbox">
                        <input type="checkbox" name="songId" value="<%= song.getSongId() %>"> Chọn bài này
                    </label>
                </div>
                <% }
                } else { %>
                <p>Không có bài hát nào để thêm.</p>
                <% } %>
            </div>
            <br>

            <!-- Nút gửi -->
            <button type="submit">Thêm Bài Hát Vào Playlist</button>
        </form>
    </div>
</body>
</html>

