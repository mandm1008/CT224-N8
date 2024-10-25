<%-- 
    Document   : player
    Created on : Oct 12, 2024, 6:44:18 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% String contextPath = request.getContextPath();%>

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
        
</div>
