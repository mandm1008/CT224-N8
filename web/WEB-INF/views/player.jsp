<%-- 
    Document   : player
    Created on : Oct 12, 2024, 6:44:18 PM
    Author     : ASUS
--%>

<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
<%@page import="db.SongModel" %>
<%@page import="db.PlaylistSongModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // check session playlist
    LinkedList<SongModel> list = new LinkedList<>();
    String playlistIdStrSession = (String) session.getAttribute("playlistId");
    int playlistIdSession = -1;
    
    if (playlistIdStrSession != null) {
        try {
            playlistIdSession = Integer.parseInt(playlistIdStrSession);
        } catch (Exception e) {
        }
    }
    
    if (playlistIdSession > 0) {
        list = (new PlaylistSongModel()).getSongsByPlaylistId(playlistIdSession);
    }
    
    // default
    if (list.size() <= 0) list = SongModel.getNewSongs(9);
    HashMap<String, Object> data = new HashMap<>();

    data.put("songs", list);
    data.put("name", "Danh sách phát");
    data.put("userId", -1);

    Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();
    String json = gson.toJson(data);
%>

<% String contextPath = request.getContextPath();%>

<script src="https://www.youtube.com/iframe_api" defer></script>

<div class="music-player">
    <div class="player-info">
        <img src="<%=contextPath%>/images/demo_music.png" alt="Song Thumbnail" class="song-thumbnail">
        <div class="song-details">
            <span class="song-title">Song Name</span>
            <span class="song-artist">Artist Name</span>
        </div>
    </div> 

    <div class="player-controls">
        <button id="shuffleBtn" class="control-btn" style="opacity: 0.4">
            <img src="<%=contextPath%>/images/icons/shuffle-solid.png" alt="Shuffle">
        </button>
        <button id="prevBtn" class="control-btn">
            <img src="<%=contextPath%>/images/icons/backward-step-solid.png" alt="Previous">
        </button>
        <button id="playBtn" class="control-btn play-btn">
            <img src="<%=contextPath%>/images/icons/play-solid.png" alt="Play">
        </button>
        <button id="nextBtn" class="control-btn">
            <img src="<%=contextPath%>/images/icons/forward-step-solid.png" alt="Next">
        </button>
        <button id="repeatBtn" class="control-btn" style="opacity: 0.4">
            <img src="<%=contextPath%>/images/icons/repeat-solid.png" alt="Repeat">
        </button>
    </div>

    <div class="player-timer">
        <span id="currentTime">00:00</span>
        <input type="range" id="progressBar" value="0" max="100">
        <span id="durationTime">00:00</span>
    </div>

    <div id="youtube-iframe" style="display: none;"></div>

</div>

<script src="js/player.js"></script>
<script defer>
    const metadata = {
        contextPath: '<%=request.getContextPath()%>',
        jsonSongs: `<%=json%>`
    };
    const playerManager = new PlayerManager();

    playerManager.setContextPath(metadata.contextPath);
    playerManager.getDataFromJson(metadata.jsonSongs);
</script>