package servlet.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.Bang_Playlist_song;
import java.sql.*;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Playlist_Song extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        try
            {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(db_url,db_user,db_pw);
                stm = conn.createStatement();
               
                String sql_playlist_song = "SELECT ps.playlist_id, s.song_id, s.title FROM playlist_songs as ps INNER join songs as s where ps.song_id = s.song_id and ps.playlist_id = 1;";
                ResultSet rs_3 = stm.executeQuery(sql_playlist_song);
                
                Bang_Playlist_song playlist_song = new Bang_Playlist_song();          
                while(rs_3.next())
                {
               //     String playlist_song_id = rs.getString("playlist_song_id");
                //    playlist_song.playlist_song_id.add(playlist_song_id);
                    String playlist_id = rs_3.getString("playlist_id");
                    playlist_song.playlist_id.add(playlist_id);
                    String song_id = rs_3.getString("song_id");
                    playlist_song.song_id.add(song_id);
                    String title = rs_3.getString("title");
                    playlist_song.title.add(title);
                }               
                rs_3.close();
                stm.close();
                conn.close();
                                      
                JsonObject jsonResponse = Json.createObjectBuilder()
            .add("playlist_id", Json.createArrayBuilder(playlist_song.playlist_id))
            .add("song_id", Json.createArrayBuilder(playlist_song.song_id))
            .add("title", Json.createArrayBuilder(playlist_song.title))
            .build();

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            } 
    }

}
