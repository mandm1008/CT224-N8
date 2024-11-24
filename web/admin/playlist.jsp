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
        <title >Playlist Page</title>
        <link rel="stylesheet" href="css/playlist_style.css">
    </head>
    <body>
        <script src="js/playlist_script.js"></script>
        
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
        
        <h1 id="page-title">PLAYLIST</h1>
        <div id="two_table_container">
        <div id="table_container_playlist">
        <div id="top_table_playlist">
            <div id="add_playlist">
        <button type="button" id ="btn_them" onclick="add_to_playlist()">Thêm Playlist</button>
        <input type="text" name="txt_add_playlist" id="txt_add_playlist"><br>
        </div>
            <div id = "select-search">
        <select name="tim_op_playlist" id="tim_op_playlist">
        <option value="tim_op_select">Select</option>
        <option value="playlist_id">Playlist ID</option>
        <option value="name">Name</option>
        </select>
        <input type="text" name="search_playlist" id="search_playlist">
        <button type="button" id ="btn_tim_playlist" onclick="tim_playlist_f()">Tìm Kiếm</button>
        <button type="button" id ="btn_reload_playlist" onclick="reload_playlist_sc()">Reload</button><br>
        </div>
        </div>
            <article class="content">
        <table id = "playlist_table_id" class = "playlist_table_class">
            <thead>
            <tr>
                <th>PlayList_ID</th>
                <th>Name</th>
                <th colspan="2"></th> 
            </tr>
            </thead>
            <tbody id="tableBody">
        <%
        Bang_Playlist kq = (Bang_Playlist)request.getAttribute("kq");
        int i, si = kq.playlist_id.size();
        for(i = 0;i<si;i++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(kq.playlist_id.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.name.get(i)); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='sua_playlist_sc(this)'>Edit</button>"); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='xoa_playlist_sc(this)'>Delete</button>"); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='send_to_pl_detail(this)'>Detail</button>"); out.println("</td>");
          out.println("<td>"); out.println("<input type='checkbox' name='playlist_check' id = 'playlist_check' class = 'playlist_check' value='" + kq.playlist_id.get(i) + "' />"); out.println("</td>");
          out.println("</tr>");
          }
        %>
        </tbody>
        </table>
        </article>
        <div class="pagination" id="paginationControls">
        </div>
        </div>

        <div id="table_container">
            <div id="top_table">
        <select name="tim_op" id="tim_op">
          <option value="tim_op_select">Select</option>
          <option value="song_id">Song ID</option>
          <option value="title">Title</option>
        </select>
        <input type="text" name="search_song" id="search_song">
        <button type="button" id ="btn_song_tim" onclick="tim_song()">Tìm Kiếm</button>
        <button type="button" id ="btn_reload" onclick="reload_song_sc()">Reload</button>
        </div>
            <article class="content_song">
        <table id = "song_table_id" class = "song_table_class">
            <thead>
            <tr>
                <th>Song ID</th>
                <th>Title</th>
                <th colspan="2"></th> 
            </tr>
            </thead>
            <tbody id="tableBody_song">
        <%
        Bang kq_2 = (Bang)request.getAttribute("kq_2");
        int j, sj = kq_2.song_id.size();
        for(j = 0;j<sj;j++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(kq_2.song_id.get(j)); out.println("</td>");
          out.println("<td>"); out.println(kq_2.title.get(j)); out.println("</td>");       
          out.println("<td>"); out.println("<button onclick='checkSongAndAdd(this)'>Add</button>"); out.println("</td>");
          out.println("</tr>");
          }
        %>
        </tbody>
        </table>
        </article>
        </div>
        </div>
        
    <form action = "./tim_playlist" name="tim_playlist" id="tim_playlist" method="post" style="display: none">
            <input type="hidden" id="tim_w" name="tim_w">
            <input type="hidden" id="tim_v" name="tim_v">
            <input type="hidden" id="sop" name="sop">
            <button type="submit" style="display: none">submit</button>
    </form>   
        
    <form action = "./playlist_add" name="add_song_playlist" id="add_song_playlist" method="post" style="display: none">
            <input type="hidden" id="name" name="name">
            <button type="submit" style="display: none">submit</button>
    </form>       
     <input type="hidden" id="playlist_id_v" name="playlist_id_v">
     
     <form action = "./playlist_add" name="sua_xoa_playlist" id="sua_xoa_playlist" method="post" style="display: none">
     <label for="playlist_id_v_update">Playlist ID</label><br>
     <input type="text" id="playlist_id_v_update" name="playlist_id_v_update"><br>
     <label for="name_update">Playlist Name</label><br>
     <input type="text" id="name_update" name="name_update"><br>
     <button type="submit">Xác Nhận</button>
     </form>
     
     <form action = "./LoadData_Playlist_Song" name="Playlist_detail" id="Playlist_detail" method="post" style="display: none">
     <input type="hidden" id="playlist_id_detail_v" name="playlist_id_detail_v">
     <input type="hidden" id="name_detail_v" name="name_detail_v">
     <button type="submit">submit</button>             
     </form>
    </body>
</html>
