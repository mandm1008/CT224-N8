<%-- 
    Document   : index
    Created on : Oct 5, 2024, 9:06:30 PM
    Author     : ASUS
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="db.SongModel"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="db.UserMusic"%>
<%@page import="db.PlaylistModel"%>
<%@page import="DAO.User" %>
<%@page import="DAO.Song" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% String contextPath = request.getContextPath();%>
<%
    User user = (User) session.getAttribute("user");
    LinkedList<SongModel> mostViewSongs = SongModel.getMostViewSongs(9);
    LinkedList<Song> userSongs = null;
    if (user != null) {
        UserMusic um = new UserMusic(user.userId);
        userSongs = um.findByUserId();
    }

    LinkedList<PlaylistModel> newAdminPlaylist = (new PlaylistModel()).getAdminPlaylist();

    Gson gsonHome = new GsonBuilder().setLenient().create();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enjoy Music - Home</title>

        <link rel="stylesheet" href="<%=contextPath%>/css/song.css">
        <script src="<%=contextPath%>/js/home.js" defer></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <div class="home-wrapper">
            <div class="home-element">
                <h2>Nhạc nổi bật</h2>

                <div class="music-box">
                    <% for (int i = 0; i < mostViewSongs.size(); i++) {
                            Song song = mostViewSongs.get(i).toSong();
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

            <%  if (userSongs != null && userSongs.size() > 0) { %>
            <div class="home-element">
                <h2>Đã nghe gần đây</h2>

                <div class="music-box">
                    <% for (int i = 0; i < userSongs.size(); i++) {
                            Song song = userSongs.get(i);
                    %>

                    <div class="<%=(song.href.contains("youtube") ? "music-element music-element-youtube" : "music-element")%>" yt-data="<%=(song.href.contains("youtube") ? song.href : "")%>"  >
                        <img src="<%=(song.image.length() > 0 ? song.image : (contextPath + "/images/demo_music.png"))%>" alt="<%=song.title%>" class="music-image"/>

                        <div class="music-info">
                            <p class="music-info--title" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=song.songId%>'"><%=song.title%></p>

                            <p class="music-info--artist">
                                <span><%=song.artistName%></span>
                            </p>
                        </div>
                    </div>

                    <% }%>
                </div>
            </div>
            <% } %>

            <div class="home-element">
                <h2>BHX</h2>

                <div class="slider-container">
                    <div class="home-slider">
                        <% for (int i = 0; i < mostViewSongs.size() && i < 5; i++) {
                                Song song = mostViewSongs.get(i).toSong();
                        %>

                        <div class="<%=(song.href.contains("youtube") ? "music-element music-element-youtube slider-element" : "music-element slider-element")%>" yt-data="<%=(song.href.contains("youtube") ? song.href : "")%>"  >

                            <div class="music-top">
                                <h2><%=i + 1%></h2>
                            </div>
                            <img src="<%=(song.image.length() > 0 ? song.image : (contextPath + "/images/demo_music.png"))%>" alt="<%=song.title%>" class="music-image"/>

                            <div class="music-info">
                                <p class="music-info--title" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=song.songId%>'">
                                    <%=song.title%>
                                </p>
                                <p class="music-info--artist">
                                    <span><%=song.artistName%></span>
                                </p>
                            </div>
                        </div>

                        <% }%>
                    </div>

                    <button class="prev-btn"><img src="<%=contextPath%>/images/icons/backward-solid.png" alt="Previous"></button>
                    <button class="next-btn"><img src="<%=contextPath%>/images/icons/forward-solid.png" alt="Next"></button>
                </div>
            </div>

            <% if (newAdminPlaylist.size() > 0) {%>
            <div class="home-element">
                <h2>Danh sách phát mới cập nhật</h2>

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
            <% } %>

            <%  if (user != null && user.userId > 0) {%>
            <div class="playlist-form">
                <h2>Danh Sách Nhạc của tui</h2>
                <a href="playlist.jsp" class="btn btn-primary">Xem Danh Sách</a>
            </div>
            <% }%>
        </div>

        <jsp:include page="/WEB-INF/views/player.jsp" />

        <script defer>
            document.querySelectorAll(".music-element-youtube").forEach(element => {
                const href = element.getAttribute("yt-data");
                const titleElement = element.querySelector(".music-info--title");
                const titleMenuElement = element.querySelector(".music-menu--title");
                const artistElement = element.querySelector(".music-info--artist span");
                const imageElement = element.querySelector(".music-image");
                const id = playerManager.youtubeManager.detachId(href);

                console.log(href);
                imageElement.src = "https://img.youtube.com/vi/" + id + "/hqdefault.jpg";

                playerManager.youtubeManager.fetchYouTubeData(id)
                        .then(data => {
                            titleElement.innerText = data.title;
                            artistElement.innerText = data.channelTitle;
                            if (titleMenuElement !== null) {
                                titleMenuElement.innerText = data.title;
                            }
                        });
            });
        </script>
    </body>
</html>
