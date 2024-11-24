<%-- 
    Document   : song
    Created on : Nov 4, 2024, 1:53:49 PM
    Author     : nguye
--%>
<%@page import="jakarta.servlet.RequestDispatcher"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="DAO.Bang"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QL Bài hát</title>
        <link rel="stylesheet" href="css/song_style.css">
    </head>
    <body>   
        
        <script src="js/song_script.js"></script>
        
        
        
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
        <hr>
        <h1 id="title_page">Quản lý bài hát</h1>
        
        
        <div id="table_container">
            <div id="top_table">
        <button type="button" id ="btn_them" onclick="them_show()">Thêm bài hát</button><br>
        <select name="tim_op" id="tim_op">
          <option value="tim_op_select">Select</option>
          <option value="song_id">Song ID</option>
          <option value="title">Title</option>
        </select>
        <input type="text" name="search_song" id="search_song">
        <button type="button" id ="btn_them" onclick="tim_song()">Tìm Kiếm</button><br>
        <button type="button" id ="btn_reload" onclick="reload()">Reload</button><br>
        </div>
            <article class="content">
        <table id = "song_table_id" class = "song_table_class">
            <thead>
            <tr>
                <th>Song ID</th>
                <th>Title</th>
                <th>Artist Id</th>
                <th>Href</th>
                <th>Image</th>
                <th>View</th>
                <th>IMG<th>
                <th colspan="2"></th> 
            </tr>
             </thead>
             <tbody id="tableBody">
        <%
        Bang kq = (Bang)session.getAttribute("kq");
        int i, si = kq.song_id.size();
        for(i = 0;i<si;i++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(kq.song_id.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.title.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.artist_id.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.href.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.image.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.view.get(i)); out.println("</td>");
          out.println("<td>"); out.println("<img src='" + kq.image.get(i) +"' alt='song_img' id = 'song_img'>"); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='sua()'> Edit </button>"); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='xoa()'> Delete </button>"); out.println("</td>");
          out.println("</tr>");
          }
        %>
        </tbody>
        </table>
        </article>
        <div class="pagination" id="paginationControls">
        </div>
        <hr>
        
        
        <div class="form-them-container">
        <form name = "them" id ="them" action = "./them" method="post" enctype="multipart/form-data" style="display:none">
            
        <label><strong id ="form_title">THÊM BÀI HÁT</strong></label>
        <label for="song_id" class = "them_song_id">Song Id</label><br>
        <input type="text" id="song_id" name="song_id" class = "them_song_id"><br>  
        
        <label for="title">Title</label><br>
        <input type="text" id="title" name="title"><br>
        
        
        <label for="artist_id">Artist Name</label><br>
        <input type="text" id="artist_id" name="artist_id"><br>
        
        <select id="options_them" name="options">
        <option value="option_select">Select</option>
        <option value="link_them_select">Link</option>
        <option value="file_them_select">File</option>
        </select>
        <br>
        
        <label for="link_them" class = "them_link_hide">Link</label>
        <input type="text" id="link_them" name="link_them" class = "them_link_hide">
        
        <label for="file" class = "them_file_hide">File</label>
        <input type="file" id="file_them" name="file_them" class = "them_file_hide">
        
        <label for="image">Image</label><br>
        <input type="file" id="image" name="image">
        <input type="hidden" id="view" name="view" value="0">
        
        <input type="hidden" id="lof_them" name="lof_them" value="0">
               
        <input type="hidden" id="old_href" name="old_href">
        <input type="hidden" id="old_img" name="old_img">
        <input type="hidden" id="file_change" name="file_change">
        <input type="hidden" id="link_change" name="link_change">
        <input type="hidden" id="img_change" name="img_change"><br>
        <button type="submit">Xác nhận</button>
        </form>
            </div>
        <br><button onclick="them_hide()" id="them_hide_button" style="display:none">Cancel</button>
        
        
        
        <form name = "xoa_song" id ="xoa_song" action = "./xoa" method="post">
        <input type="hidden" id="song_id" name="song_id">   
        <input type="hidden" id="old_href" name="old_href">
        <input type="hidden" id="old_img" name="old_img">
        <button type="submit" style = "display: none">Delete</button>
        </form>
        
        <form action = "./tim_bh" name="tim_bh" id="tim_bh" style="display: none">
            <input type="hidden" id="tim_w" name="tim_w">
            <input type="hidden" id="tim_v" name="tim_v">
            <button type="submit">submit</button>
        </form>
    </body>
</html>
