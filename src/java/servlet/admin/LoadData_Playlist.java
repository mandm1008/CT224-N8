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
import DAO.Bang;
import DAO.Bang_Playlist;
import java.sql.*;
import jakarta.servlet.RequestDispatcher;

/**
 *
 * @author nguye
 */
public class LoadData_Playlist extends HttpServlet {

    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    Statement stm = null;
    Statement stm_2 = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
            {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(db_url,db_user,db_pw);
                stm = conn.createStatement();
                stm_2 = conn.createStatement();
               
                String sql_playlist = "select * from playlists";
                String sql_song = "select * from songs";
                ResultSet rs = stm.executeQuery(sql_playlist);
                ResultSet rs_2 = stm_2.executeQuery(sql_song);
                
                Bang_Playlist kq = new Bang_Playlist();
                Bang kq_2 = new Bang();   
                while(rs.next())
                {
                    String playlist_id = rs.getString("playlist_id");
                    kq.playlist_id.add(playlist_id);
                    String name = rs.getString("name");
                    kq.name.add(name);
                    String user_id = rs.getString("user_id");
                    kq.user_id.add(user_id);
                }
                while(rs_2.next())
                {
                    String song_id = rs_2.getString("song_id");
                    kq_2.song_id.add(song_id);
                    String title = rs_2.getString("title");
                    kq_2.title.add(title);
                } 
                rs.close();
                rs_2.close();
                stm.close();
                stm_2.close();
                conn.close();
                                
                 request.setAttribute("kq", kq);
                 request.setAttribute("kq_2", kq_2);
                 RequestDispatcher dispatcher = request.getRequestDispatcher("./playlist.jsp");
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
