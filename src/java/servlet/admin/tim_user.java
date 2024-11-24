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
import DAO.Bang_user;
import java.sql.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class tim_user extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try
            {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(db_url,db_user,db_pw);
                stm = conn.createStatement();
                
                String tim_w = request.getParameter("tim_w");
                String tim_v = request.getParameter("tim_v");
                   
                String sql = "select * from users where " + tim_w + " like '%" + tim_v + "%' ;";
                
                PreparedStatement pstmt = conn.prepareStatement(sql);
                               
                ResultSet rs = pstmt.executeQuery(sql);
                
                Bang_user kq = new Bang_user();          
                while(rs.next())
                {
                    String user_id = rs.getString("user_id");
                    kq.user_id.add(user_id);
                    String username = rs.getString("username");
                    kq.username.add(username);
                    String password = rs.getString("password");
                    kq.password.add(password);
                    String email = rs.getString("email");
                    kq.email.add(email);
                }               
                rs.close();
                stm.close();
                conn.close();
                             
                HttpSession ss = request.getSession(true);
                ss.setAttribute("kq", kq);
                response.sendRedirect("./admin_users.jsp");
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
