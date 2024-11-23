
<%@page import="jakarta.servlet.RequestDispatcher"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="DAO.Bang_user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin_User</title>
        <link rel="stylesheet" href="css/user_style.css">
    </head>
    <body>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/user_script.js"></script>
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
        <h1 id="title_page">Quản lý User</h1>
        
        
        <div id="table_container">
            <div id="top_table">
        <button type="button" id ="btn_them" onclick="add_user()">Thêm User</button><br>
        <input type="text" name="search_song" id="search_song">
        <button type="button" id ="btn_them" onclick="testa()">Tìm Kiếm</button><br>
        </div>
            <article class="content">
        <table id = "song_table_id" class = "song_table_class">
             <thead>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th colspan="2"></th> 
            </tr>
            </thead>
            <tbody id="tableBody">
        <%
        Bang_user kq = (Bang_user) session.getAttribute("kq");
        int i, si = kq.user_id.size();
        for(i = 0;i<si;i++)
          {
          out.println("<tr>");
          out.println("<td>"); out.println(kq.user_id.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.username.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.password.get(i)); out.println("</td>");
          out.println("<td>"); out.println(kq.email.get(i)); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='edit_user()'> Edit </button>"); out.println("</td>");
          out.println("<td>"); out.println("<button onclick='delete_user()'> Delete </button>"); out.println("</td>");
          out.println("</tr>");
          }
        %>
             </tbody>
        </table>
             </article>
      
    <div class="pagination" id="paginationControls">
       
    </div>
        
        </div>
        <div id="them_user_container">
        <form name = "them_user" id ="them_user" action = "./them_user" method="post" enctype="multipart/form-data" style="display:none">
        <label for="user_id">User ID</label><br>
        <input type="text" id="user_id" name="user_id"><br>
        <label for="username">Username</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password</label><br>
        <input type="text" id="password" name="password"><br>
        <label for="email">Email</label><br>
        <input type="text" id="email" name="email"><br>
        <button type="submit">Submit</button>
        </form>
        
        </div>
        <br><button onclick="them_hide()" id="them_hide_button" style="display:none">Cancel</button>
        
        <form name = "xoa_user" id ="xoa_user" action = "./xoa_user" method="post">
        <input type="hidden" id="user_id" name="user_id">
        <button type="submit" style = "display: none">Delete</button>
        </form>
        
        
        
        
        
    </body>
    
</html>
