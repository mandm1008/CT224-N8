<%-- 
    Document   : index
    Created on : Oct 5, 2024, 9:06:30 PM
    Author     : ASUS
--%>

<%@page import="db.UserMusic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.User" %>
<%@page import="DAO.Song" %>

<% String contextPath = request.getContextPath();%>
<%
    User user = (User) session.getAttribute("user");
    LinkedList<SongModel> mostViewSongs = SongModel.getMostViewSongs(9);
    LinkedList<Song> userSongs = null;
    if (user != null) {
        UserMusic um = new UserMusic(user.userId);
        userSongs = um.findByUserId();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enjoy Music - Home</title>

        <%@include file="/WEB-INF/views/header-head.jsp" %>        
        <%@include file="/WEB-INF/views/player-head.jsp" %>
        <link rel="stylesheet" href="<%=contextPath%>/css/index.css">
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
                    %>

                    <div class="music-element">
                        <img src="<%=contextPath + song.image%>" alt="<%=song.title%>" class="music-image"/>

                        <div class="music-info">
                            <p class="music-info--title"><%=song.title%></p>

                            <p class="music-info--artist">
                                <span><%=song.artistName%></span>
                            </p>
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

                    <div class="music-element">
                        <img src="<%=contextPath + song.image%>" alt="<%=song.title%>" class="music-image"/>

                        <div class="music-info">
                            <p class="music-info--title"><%=song.title%></p>

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

                        <div class="music-element slider-element">
                            <div class="music-top">
                                <h2><%=i + 1%></h2>
                            </div>
                            <img src="<%=contextPath + song.image%>" alt="<%=song.title%>" class="music-image"/>

                            <div class="music-info"><%=song.title%></p>

                                <p class="music-info--title">
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
        </div>

        <jsp:include page="/WEB-INF/views/player.jsp" />
    </body>
</html>
