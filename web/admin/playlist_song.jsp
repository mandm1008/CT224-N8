<%@page import="jakarta.servlet.RequestDispatcher"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="DAO.Bang_Playlist"%>
<%@page import="DAO.Bang"%>
<%@page import="DAO.Bang_Playlist_song"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Playlist_song</title>
        <link rel="stylesheet" href="css/playlist_song_style.css">
    </head>
    <body>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/playlist_song_script.js"></script>
        
        <header class="header">
            
        <div class="logo">
        <img src="icon/nct_icon.png" width="40" height="40" alt="Logo">
        <span>WebName</span>
        </div>
            
        <nav class="nav">
            <a href="./LoadData">Bài hát</a>
            <a href="./LoadData_User">User</a>
            <a href="./LoadData_Playlist">Playlist</a>
        </nav>
            
        <div class="user">
        <img src="icon/admin_icon.png" width="30" height="30" alt="Logo">
        <span>User</span>
        </div>
        
        </header>
        
        
        
        <h1 id="playlist_name" style="text-transform: uppercase">${play_list_name}</h1>
     <div id="table_container_playlist_song">        
            <article class="content">
        <table id = "playlist_song_table" class = "playlist_song_table">
            <thead>
            <tr>
                <th>Playlist ID</th>
                <th>Song Name</th>
                <th style="display: none">Song ID</th>
                <th colspan="2"></th> 
            </tr>
            </thead>
            <tbody id="tableBody_playlist_song">
        <%
        Bang_Playlist_song playlist_song = (Bang_Playlist_song)request.getAttribute("playlist_song");
        int playlist_song_item, playlist_song_size = playlist_song.playlist_id.size();
        for(playlist_song_item = 0; playlist_song_item < playlist_song_size ; playlist_song_item++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(playlist_song.playlist_id.get(playlist_song_item)); out.println("</td>");
          out.println("<td>"); out.println(playlist_song.title.get(playlist_song_item)); out.println("</td>");
          out.println("<td style='display: none'>"); out.println(playlist_song.song_id.get(playlist_song_item)); out.println("</td>");
          out.println("<td>"); out.println("<a href='#' onclick='delete_song(this)'> Delete </a>"); out.println("</td>");
          out.println("</tr>");
          }
        %>
        </tbody>
        </table>
        </article>
        </div>
        
     <form action ="./Delete_Song_Playlist" name="song_delete" id="song_delete" method="post" style="display: none">
     <input type="hidden" id="playlist_id_v" name="playlist_id_v">
     <input type="hidden" id="song_id_v" name="song_id_v">
     <input type="hidden" id="name_v" name="name_v" value="${play_list_name}">
     <button type="submit">submit</button>             
     </form>
        
    </body>
    
    
</html>
