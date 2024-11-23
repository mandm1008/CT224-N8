package servlet.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.File;

public class xoa extends HttpServlet {
    
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
       try
            {
              Class.forName(jdbc_driver);
              conn = DriverManager.getConnection(db_url,db_user,db_pw);
          
              String song_id = (String)request.getParameter("song_id");
              String old_href = (String)request.getParameter("old_href");
              String old_img = (String)request.getParameter("old_img");
              
                      //Delete Old File 
                      if(!old_href.isEmpty() || old_href != null)
                      {
              File oldFile = new File(old_href);
              if (oldFile.exists() && oldFile.isFile() || old_href.isEmpty()) {
                boolean deleted_old_file = oldFile.delete();                
              }
                      }
                //Delete Old Img
                if(!old_img.isEmpty() || old_img != null)
                {
                  File oldImg = new File(old_img);
               if (oldImg.exists() && oldImg.isFile()  || old_img.isEmpty()) {
                boolean deleted_old_img = oldImg.delete();                
              }
                }
              String sql = "DELETE FROM songs WHERE song_id = ?;";   
              PreparedStatement pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, song_id);
              pstmt.executeUpdate();
              
              pstmt.close();
              conn.close();
              response.sendRedirect("LoadData");
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            }
    }

}
