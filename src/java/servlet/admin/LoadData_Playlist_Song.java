package servlet.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.Bang_Playlist_song;
import java.sql.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

public class LoadData_Playlist_Song extends HttpServlet {

    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        
        try
            {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(db_url,db_user,db_pw);
               
                HttpSession ss = request.getSession();
                
                
                String playlist_id_send = request.getParameter("playlist_id_detail_v");
                String name_send = request.getParameter("name_detail_v");
                if(playlist_id_send == null || name_send == null)
                {
                    playlist_id_send = (String)ss.getAttribute("playlist_id_ss");
                    name_send = (String)ss.getAttribute("name_ss");
                }
                
                String sql_playlist_song = "SELECT ps.playlist_id, s.song_id, s.title, pl.name " +
                               "FROM playlist_songs AS ps " +
                               "INNER JOIN songs AS s ON ps.song_id = s.song_id " +
                               "INNER JOIN playlists AS pl ON ps.playlist_id = pl.playlist_id " +
                               "WHERE ps.playlist_id = " + playlist_id_send + ";";

    PreparedStatement pstmt = conn.prepareStatement(sql_playlist_song);
                
                ResultSet rs_3 = pstmt.executeQuery(sql_playlist_song);
                
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
                    String name = rs_3.getString("name");
                    playlist_song.name.add(name);
                }               
                rs_3.close();
                conn.close();
                                      
                request.setAttribute("playlist_song", playlist_song);
                request.setAttribute("play_list_name", name_send);
                RequestDispatcher dispatcher = request.getRequestDispatcher("./playlist_song.jsp");
                dispatcher.forward(request, response);
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            } 
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
