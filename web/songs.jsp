<%-- 
    Document   : songs
    Created on : Nov 9, 2024, 1:20:41 PM
    Author     : ASUS
--%>

<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="db.UserMusic"%>
<%@page import="DAO.User"%>
<%@page import="java.util.LinkedList"%>
<%@page import="db.SongModel"%>
<%@page import="DAO.Song"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String contextPath = request.getContextPath();
    String id_str = (String) request.getParameter("id");
    System.out.println(id_str);
    int id = 0;
    try {
        id = Integer.parseInt(id_str);
    } catch (NumberFormatException e) {
        response.sendRedirect(contextPath);
    }

    SongModel songModel = new SongModel(id);
    songModel.findData();
    songModel.setArtistName(songModel.getArtist().getName());
    Song song = songModel.toSong();

    User user = (User) session.getAttribute("user");
    LinkedList<SongModel> mostViewSongs = SongModel.getMostViewSongs(9);
    LinkedList<Song> userSongs = null;
    if (user != null) {
        UserMusic um = new UserMusic(user.userId);
        userSongs = um.findByUserId();
    }

    HashMap<String, Object> data = new HashMap<>();
    LinkedList<Song> list = new LinkedList<>();
    list.add(song);

    data.put("songs", list);
    data.put("name", "Danh sách phát");
    data.put("userId", -1);

    Gson gsonHome = new GsonBuilder().setLenient().create();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enjoy Music - Song</title>
        <link rel="stylesheet" href="<%=contextPath%>/css/song.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <input type="text" style="display: none" disabled id="void" />
        <div class="home-element">
            <h2 class="song-title--page">
                <span class="song-title--value"><%=song.title%></span> - <span class="song-artist--value"><%=song.artistName%></span>
            </h2>

            <div class="song-box">
                <div class="song-thumbnail">
                    <img src="<%=song.image%>" alt="<%=song.title%>"/>
                </div>

                <div class="song-timeline">
                    <span id="pageCurrentTime">00:00</span>
                    <span>/</span>
                    <span id="pageDurationTime">00:00</span>
                    <input type="range" min="0" max="100" value="0" id="pageProcessBar" />
                </div>

                <div class="song-control-box">
                    <div class="song-control song-control--left">
                        <button id="playPageButton">
                            <img src="<%=contextPath%>/images/icons/play-solid.png" alt="Play">
                        </button>
                    </div>

                    <div class="song-control song-control--right">
                        <input type="range" min="0" max="100" value="100" id="volumeInput" />
                        <button id="volumeButton">
                            <img src="<%=contextPath%>/images/icons/volume-high-solid.png" alt="Volume">
                        </button>
                    </div>
                </div>
            </div>

            <audio src="<%=song.href%>" style="display: none"></audio>

            <div class="song-tools">
                <!--                <button> 
                                    <img src="<%=contextPath%>/images/icons/square-plus-solid.png" alt="Add">
                                    Thêm vào
                                </button>-->

                <button> 
                    <img src="<%=contextPath%>/images/icons/share-from-square-regular.png" alt="Share">
                    Chia sẻ
                </button>
            </div>
        </div>

        <div class="home-element">
            <h2>Danh sách phát</h2>
        </div>

        <div class="home-element">
            <h2>Đề cử</h2>

            <div class="music-box">
                <% for (int i = 0; i < mostViewSongs.size(); i++) {
                        Song so = mostViewSongs.get(i).toSong();
                        String jsonSong = gsonHome.toJson(so).replaceAll("(?<!\\\\)\"", "&quot;");
                %>

                <div class="<%=(so.href.contains("youtube") ? "music-element music-element-youtube" : "music-element")%>" yt-data="<%=(so.href.contains("youtube") ? so.href : "")%>"  >
                    <img src="<%=(so.image.length() > 0 ? so.image : (contextPath + "/images/demo_music.png"))%>" alt="<%=so.title%>" class="music-image"/>

                    <div class="music-info">
                        <p class="music-info--title" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=so.songId%>'"><%=so.title%></p>

                        <p class="music-info--artist">
                            <span><%=so.artistName%></span>
                        </p>
                    </div>

                    <button class="music-menu--icon">
                        <img src="<%=contextPath%>/images/icons/ellipsis-vertical-solid.png" alt="Menu" />
                    </button>

                    <div class="music-menu">
                        <div class="music-menu--title"><%=so.title%></div>
                        <div class="music-menu--button" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=so.songId%>'">
                            <img src="<%=contextPath%>/images/icons/play-solid.png" alt="Play" />
                            <span>Phát ngay</span>
                        </div>
                        <div class="music-menu--button" onclick="pagePlayerManager.downloadSong(`<%=so.href%>`)">
                            <img src="<%=contextPath%>/images/icons/download-solid.png" alt="Download" />
                            <span>Tải xuống</span>
                        </div>
                        <div class="music-menu--button" onclick="pagePlayerManager.shareSong(`<%=so.songId%>`)">
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
                        Song s = userSongs.get(i);
                %>

                <div class="music-element">
                    <img src="<%=s.image%>" alt="<%=s.title%>" class="music-image"/>

                    <div class="music-info">
                        <p class="music-info--title" onclick="window.location = '<%=contextPath%>/songs.jsp?id=<%=s.songId%>'"><%=s.title%></p>

                        <p class="music-info--artist">
                            <span><%=s.artistName%></span>
                        </p>
                    </div>
                </div>

                <% }%>
            </div>
        </div>
        <% }%>

        <div style="display: none" id="youtube-iframe"></div>

        <script>
            const musicElements = document.querySelectorAll(".music-element");

            musicElements.forEach((musicElement) => {
                const menuButton = musicElement.querySelector(".music-menu--icon");
                const menuMusic = musicElement.querySelector(".music-menu");

                menuButton.onclick = (e) => {
                    menuMusic.classList.toggle("active");

                    const rect = musicElement.getBoundingClientRect();
                    const menuRect = menuMusic.getBoundingClientRect();

                    if (rect.right + menuRect.width > window.innerWidth) {
                        menuMusic.style.left = 'auto';
                        menuMusic.style.right = '50%'; // Hiển thị ở bên trái
                    } else {
                        menuMusic.style.left = '100%';
                        menuMusic.style.right = 'auto';
                    }

                    e.stopPropagation();
                };

                menuMusic.onclick = (e) => {
                    e.stopPropagation();
                };

                window.addEventListener("click", (e) => {
                    menuMusic.classList.remove("active");
                });
            });

        </script>
        <script src="https://www.youtube.com/iframe_api" defer></script>
        <script src="js/player.js"></script>
        <script defer>
            const pageMetadata = {
                contextPath: '<%=contextPath%>',
                jsonSongs: `<%=gsonHome.toJson(data)%>`
            };
            const pagePlayerManager = new PlayerManager(
                    ".song-title--value",
                    ".song-thumbnail img",
                    ".song-artist--value",
                    "#playPageButton",
                    "#void",
                    "#void",
                    "#void",
                    "#void",
                    "#pageProcessBar",
                    "#pageCurrentTime",
                    "#pageDurationTime");

            pagePlayerManager.setContextPath(pageMetadata.contextPath);
            pagePlayerManager.getDataFromJson(pageMetadata.jsonSongs);

            document.querySelectorAll(".music-element-youtube").forEach(element => {
                const href = element.getAttribute("yt-data");
                const titleElement = element.querySelector(".music-info--title");
                const titleMenuElement = element.querySelector(".music-menu--title");
                const artistElement = element.querySelector(".music-info--artist span");
                const imageElement = element.querySelector(".music-image");
                const id = pagePlayerManager.youtubeManager.detachId(href);

                console.log(href);
                imageElement.src = "https://img.youtube.com/vi/" + id + "/hqdefault.jpg";

                pagePlayerManager.youtubeManager.fetchYouTubeData(id)
                        .then(data => {
                            titleElement.innerText = data.title;
                            artistElement.innerText = data.channelTitle;
                            if (typeof titleMenuElement !== "undefined") {
                                titleMenuElement.innerText = data.title;
                            }
                        })
                        .catch(err => {
                            console.log(err);
                        });
            });
        </script>
    </body>
</html>
