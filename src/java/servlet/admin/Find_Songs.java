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
import java.sql.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class Find_Songs extends HttpServlet {

    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    Statement stm = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try
            {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(db_url,db_user,db_pw);
                stm = conn.createStatement();
               
                String sql = "select * from songs where song_id = 1";
                ResultSet rs = stm.executeQuery(sql);
                
                Bang kq = new Bang();          
                while(rs.next())
                {
                    String song_id = rs.getString("song_id");
                    kq.song_id.add(song_id);
                    String title = rs.getString("title");
                    kq.title.add(title);
                    String artis_id = rs.getString("artist_id");
                    kq.artist_id.add(artis_id);
                    String href = rs.getString("href");
                    kq.href.add(href);
                    String image = rs.getString("image");
                    kq.image.add(image);
                    String view = rs.getString("view");
                    kq.view.add(view);
                }               
                rs.close();
                stm.close();
                conn.close();
                             
                HttpSession ss= request.getSession(true);
                ss.setAttribute("kq_2", kq);
                response.sendRedirect("./playlist.jsp");
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
