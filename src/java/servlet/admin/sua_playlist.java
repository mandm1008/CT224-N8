package servlet.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author nguye
 */
public class sua_playlist extends HttpServlet {

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
              
              String playlist_id = (String)request.getParameter("playlist_id_v_update");
              String name = (String)request.getParameter("name_update");

              
              String sql = "update playlists set name = ? where playlist_id = ?";
              PreparedStatement pstmt = conn.prepareStatement(sql);
              
              pstmt.setString(1, name);
              pstmt.setString(2, playlist_id);

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
