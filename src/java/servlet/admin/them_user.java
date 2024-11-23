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
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig
public class them_user extends HttpServlet {

    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    Statement stm = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


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
              
              
              String username = (String)request.getParameter("username");
              String password = (String)request.getParameter("password");
              String email = (String)request.getParameter("email");
              
              String sql = "insert into users (username,password,email,avatar) values(?,?,?,'a')";
              PreparedStatement pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, username); // Nếu 'title' là chuỗi
              pstmt.setString(2, password); // Nếu 'artist_id' là số
              pstmt.setString(3, email); // Nếu 'href' là chuỗi
              pstmt.executeUpdate();
              
              pstmt.close();
              conn.close();
              response.sendRedirect("LoadData_User");
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            }
    }

}
