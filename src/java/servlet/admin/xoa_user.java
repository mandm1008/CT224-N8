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
public class xoa_user extends HttpServlet {

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
              
              String user_id = (String)request.getParameter("user_id");
              
              String sql = "DELETE FROM users WHERE user_id = ?;";
              PreparedStatement pstmt = conn.prepareStatement(sql);
              
              pstmt.setString(1, user_id);
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
