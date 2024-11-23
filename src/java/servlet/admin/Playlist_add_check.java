package servlet.admin;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;


public class Playlist_add_check extends HttpServlet {

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
        String song_id = request.getParameter("songId");
        boolean isChecked = Boolean.parseBoolean(request.getParameter("checked"));
        boolean isAllowed = false;
   
        try
        {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(db_url,db_user,db_pw);
            String sql_check = "select song_id from playlist_songs where song_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql_check);
            pstmt.setString(1, song_id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Record exists
                isAllowed = false;
            } else {
                // Record does not exist
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
