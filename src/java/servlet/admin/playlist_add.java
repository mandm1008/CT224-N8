package servlet.admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

public class playlist_add extends HttpServlet {

    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    Statement stm = null;
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
      request.setCharacterEncoding("UTF-8");
        try
            {
              Class.forName(jdbc_driver);
              conn = DriverManager.getConnection(db_url,db_user,db_pw);
              stm = conn.createStatement();
              
              
              String name = (String)request.getParameter("name");
              String user_id = "2";
              
              String sql = "insert into playlists (name,user_id) values(?,2)";
              PreparedStatement pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, name);
              pstmt.executeUpdate();
              
              pstmt.close();
              conn.close();
              response.sendRedirect("LoadData_Playlist");
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            }
    }

   
}
