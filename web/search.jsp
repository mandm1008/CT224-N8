<%-- 
    Document   : search
    Created on : Oct 17, 2024, 8:14:24 PM
    Author     : ASUS
--%>

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
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enjoy Music - Home</title>

        <%@include file="/WEB-INF/views/header-head.jsp" %>        
        <%@include file="/WEB-INF/views/player-head.jsp" %>

        <link href="css/search.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <div class="wrapper">
            <h3 class="title">Kết quả tìm kiếm của "<%=searchKey%>" (<%=songs.size()%>)</h3>
            <div class="ctn-songs">
                <% for (int i = 0; i < songs.size(); i++) {
                        Song song = songs.get(i).toSong();
                %>

                <div class="ctn-songs_item" <%=i % 3 != 0 ? "style=\"margin-left: 12px;\"" : ""%>>
                    <img src="<%=contextPath + song.image%>" alt="<%=song.title%>" />

                    <div class="ctn-songs_info">
                        <p><%=song.title%></p>
                        <p>
                            <span><%=song.artistName%></span>
                        </p>
                    </div>
                </div>

                <% }%>
            </div>
        </div>

        <jsp:include page="/WEB-INF/views/player.jsp" />
    </body>
</html>

