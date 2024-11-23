<%@page import="jakarta.servlet.RequestDispatcher"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="DAO.Bang_Playlist"%>
<%@page import="DAO.Bang"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Playlist Page</title>
        <link rel="stylesheet" href="css/playlist_style.css">
    </head>
    <body>
        <script src="js/playlist_script.js"></script>
        <h1>PlayList</h1>
        <div id="table_container_playlist">
        <button type="button" id ="btn_them" onclick="them_playlist()">Thêm bài hát</button><br>
        <select name="tim_op_playlist" id="tim_op_playlist">
        <option value="tim_op_select">Select</option>
        <option value="playlist_id">Playlist ID</option>
        <option value="name">Name</option>
        </select>
        <input type="text" name="search_playlist" id="search_playlist">
        <button type="button" id ="btn_tim_playlist" onclick="tim_playlist_f()">Tìm Kiếm</button><br>
        <button type="button" id ="btn_reload_playlist" onclick="">Reload</button><br>
        <table id = "song_table_id" class = "song_table_class">
            <tr>
                <th>PlayList_ID</th>
                <th>Name</th>
                <th colspan="2"></th> 
            </tr>
        <%
        Bang_Playlist kq = (Bang_Playlist)request.getAttribute("kq");
        int i, si = kq.playlist_id.size();
        for(i = 0;i<si;i++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(kq.playlist_id.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.name.get(i)); out.println("</td>");
          out.println("<td>"); out.println("<button onclick=''> Edit </button>"); out.println("</td>");
          out.println("</tr>");
          }
        %>
        </table>
        </div>>
        <br>
        <br>
        <br>
        <div id="table_container">
            <div id="top_table">
        <select name="tim_op" id="tim_op">
          <option value="tim_op_select">Select</option>
          <option value="song_id">Song ID</option>
          <option value="title">Title</option>
        </select>
        <input type="text" name="search_song" id="search_song">
        <button type="button" id ="btn_song_tim" onclick="tim_song()">Tìm Kiếm</button><br>
        <button type="button" id ="btn_reload" onclick="reload()">Reload</button><br>
        </div>
        <table id = "song_table_id" class = "song_table_class">
            <tr>
                <th>Song ID</th>
                <th>Title</th>
                <th colspan="2"></th> 
            </tr>
        <%
        Bang kq_2 = (Bang)request.getAttribute("kq_2");
        int j, sj = kq_2.song_id.size();
        for(j = 0;j<sj;j++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(kq_2.song_id.get(j)); out.println("</td>");
          out.println("<td>"); out.println(kq_2.title.get(j)); out.println("</td>");
          out.println("<td>"); out.println("<input type='checkbox' name='selectedSongs' class = 'selectedSongs' value='" + kq_2.song_id.get(j) + "'>"); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='sua()'> Edit </button>"); out.println("</td>");
          out.println("</tr>");
          }
        %>
        </table>
        </div>
        
        
        <form action = "./Find_Songs">
            <button type="submit">Submit</button>
        </form>
        
    <form action = "./tim_playlist" name="tim_playlist" id="tim_playlist" style="display: none">
            <input type="hidden" id="tim_w" name="tim_w">
            <input type="hidden" id="tim_v" name="tim_v">
            <input type="hidden" id="sop" name="sop">
            <button type="submit">submit</button>
    </form>   
        
        
    </body>
</html>
