package servlet.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import jakarta.servlet.http.HttpSession;

public class Delete_Song_Playlist extends HttpServlet {

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
        
        String playlist_id = request.getParameter("playlist_id_v");
        String song_id = request.getParameter("song_id_v");
        String name = request.getParameter("name_v");
          
        try
            {
              Class.forName(jdbc_driver);
              conn = DriverManager.getConnection(db_url,db_user,db_pw);
                           
              String sql = "DELETE FROM playlist_songs WHERE playlist_id = ? and song_id = ?;";
              PreparedStatement pstmt = conn.prepareStatement(sql);
              
              pstmt.setString(1, playlist_id);
              pstmt.setString(2, song_id);
              pstmt.executeUpdate();
              
              pstmt.close();
              conn.close();
              HttpSession ss = request.getSession();
              ss.setAttribute("playlist_id_ss", playlist_id);
              ss.setAttribute("name_ss", name);
              response.sendRedirect("LoadData_Playlist_Song");
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            }
    }



}
