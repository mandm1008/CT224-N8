package servlet.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;


public class Check_And_Add extends HttpServlet {

    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    Statement stm = null;
    ResultSet rs = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
        String song_id = request.getParameter("song_id");
        String playlist_id = request.getParameter("playlist_id"); 
        try
        {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(db_url,db_user,db_pw);
            String sql_check = "select song_id from playlist_songs where playlist_id = ? and song_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql_check);
            pstmt.setString(1, playlist_id);
            pstmt.setString(2, song_id);
            Boolean isAllowed = false;
            rs = pstmt.executeQuery();
            String sql_add = "insert into playlist_songs(playlist_id,song_id) values(?,?)";
            PreparedStatement pstmt_2 = conn.prepareStatement(sql_add);
            if(rs.next()) {
                isAllowed = false;
            } else {               
                pstmt_2.setString(1, playlist_id);
                pstmt_2.setString(2, song_id);
                pstmt_2.executeUpdate();
                isAllowed = true;
            }
            
            response.setContentType("text/plain");
            response.getWriter().write(isAllowed ? "allowed" : "not allowed");
        }
        catch(Exception e)
        {
            response.getWriter().println("Error: " + e);
        }
    }


}
