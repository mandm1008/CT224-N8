<%-- 
    Document   : search
    Created on : Oct 17, 2024, 8:14:24 PM
    Author     : ASUS
--%>

<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.List"%>
<%@page import="db.PlaylistModel"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="db.SongModel" %>
<%@page import="DAO.Song" %>
<%@page import="java.util.LinkedList" %>

<% String contextPath = request.getContextPath();%>
<%
    String searchKey = request.getParameter("searchkey");
    LinkedList<SongModel> songs = SongModel.searchSongs(searchKey);
    List<PlaylistModel> newAdminPlaylist = new PlaylistModel().searchAdminPlaylist(searchKey);
    
    Gson gsonHome = new GsonBuilder().setLenient().create();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enjoy Music - Home</title>
        <link href="css/song.css" rel="stylesheet" />
        <link href="css/search.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <div class="home-wrapper">
            <div class="home-element">
                <h2>Kết quả tìm kiếm của "<%=searchKey%>" (<%=songs.size()%>)</h2>
                
                <div class="music-box">
                <% for (int i = 0; i < songs.size(); i++) {
                        Song song = songs.get(i).toSong();
                        String jsonSong = gsonHome.toJson(song).replaceAll("(?<!\\\\)\"", "&quot;");
                %>

                <div class="<%=(song.href.contains("youtube") ? "music-element music-element-youtube" : "music-element")%>" yt-data="<%=(song.href.contains("youtube") ? song.href : "")%>"  >
                    <img src="<%=(song.image.length() > 0 ? song.image : (contextPath + "/images/demo_music.png"))%>" alt="<%=song.title%>" class="music-image"/>

                    <div class="music-info">
                        <p class="music-info--title" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=song.songId%>'"><%=song.title%></p>

                        <p class="music-info--artist">
                            <span><%=song.artistName%></span>
                        </p>
                    </div>

                    <button class="music-menu--icon">
                        <img src="<%=contextPath%>/images/icons/ellipsis-vertical-solid.png" alt="Menu" />
                    </button>

                    <div class="music-menu">
                        <div class="music-menu--title" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=song.songId%>'"><%=song.title%></div>
                        <div class="music-menu--button" onclick="playerManager.playNowSong('<%=jsonSong%>')">
                            <img src="<%=contextPath%>/images/icons/play-solid.png" alt="Play" />
                            <span>Phát ngay</span>
                        </div>
                        <div class="music-menu--button" onclick="playerManager.addToPlaylist('<%=jsonSong%>')">
                            <img src="<%=contextPath%>/images/icons/square-plus-solid.png" alt="Add" />
                            <span>Thêm vào danh sách phát</span>
                        </div>
                        <div class="music-menu--button" onclick="playerManager.downloadSong(`<%=song.href%>`)">
                            <img src="<%=contextPath%>/images/icons/download-solid.png" alt="Download" />
                            <span>Tải xuống</span>
                        </div>
                        <div class="music-menu--button" onclick="playerManager.shareSong(`<%=song.songId%>`)">
                            <img src="<%=contextPath%>/images/icons/share-from-square-regular.png" alt="Share" />
                            <span>Chia sẻ</span>
                        </div>
                    </div>
                </div>

                <% }%>
                </div>
            </div>
            
            <div class="home-element">
                <h2>Danh sách phát liên quan (<%=newAdminPlaylist.size()%>)</h2>

                <div class="playlist-new">
                    <div class="playlist-new-box">
                        <% 
                            for (int i = 0; i < newAdminPlaylist.size(); i++) {
                                PlaylistModel playlist = newAdminPlaylist.get(i);
                        %>
                        <div class="playlist-new-element">
                            <span>
                                <%=playlist.getName()%>
                            </span>
                            
                            <button class="music-menu--icon" onclick="window.location = '<%=contextPath%>/RunPlaylist?id=<%=playlist.getPlaylistId()%>'">
                                <img src="<%=contextPath%>/images/icons/play-solid.png" alt="Play">
                            </button>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/views/player.jsp" />
    </body>
</html>

