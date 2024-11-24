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
        <select name="tim_op" id="tim_op">
          <option value="tim_op_select">Select</option>
          <option value="user_id">User ID</option>
          <option value="username">Username</option>
        </select>
        <input type="text" name="search_user" id="search_user">
        <button type="button" id ="btn_them" onclick="tim_user_sc()">Tìm Kiếm</button><br>
        <button type="button" id ="btn_reload" onclick="reload()">Reload</button><br>
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
        Bang_user kq = (Bang_user)session.getAttribute("kq");
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
       
    </div>
        
        </div>
        <div id="them_user_container">
        <form name = "them_user" id ="them_user" action = "./them_user" method="post" enctype="multipart/form-data" style="display:none">
        <label><strong id ="form_title">THÊM BÀI HÁT</strong></label><br>
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
        
        <form action = "./tim_user" name="tim_user" id="tim_user" style="display: none">
            <input type="hidden" id="tim_w" name="tim_w">
            <input type="hidden" id="tim_v" name="tim_v">
            <button type="submit">submit</button>
        </form>
        
        
        
    </body>
    
</html>
