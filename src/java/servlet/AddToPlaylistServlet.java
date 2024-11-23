/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.PlaylistModel;
import DAO.User;

/**
 * Servlet để thêm bài hát vào danh sách phát.
 *
 * @author DELL
 */
@WebServlet(name = "AddToPlaylistServlet", urlPatterns = {"/AddToPlaylistServlet"})
public class AddToPlaylistServlet extends HttpServlet {

    /**
     * Xử lý cả GET và POST yêu cầu.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException nếu xảy ra lỗi Servlet
     * @throws IOException nếu xảy ra lỗi I/O
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Lấy thông tin từ request
            String playlistName = request.getParameter("playlistName");
            User user = (User) request.getSession().getAttribute("user");

            // Kiểm tra trạng thái đăng nhập
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Kiểm tra tham số playlistName
            if (playlistName == null || playlistName.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tên danh sách phát không được để trống.");
                return;
            }

            // Logic tạo danh sách phát
            PlaylistModel playlist = new PlaylistModel(playlistName, user.userId);
            if (!playlist.checkAccess()) { // Kiểm tra trùng lặp
                response.getWriter().println("Danh sách phát đã tồn tại.");
                return;
            }
            
            playlist.insert(); // Thêm danh sách phát vào database
            response.sendRedirect("PlaylistList.jsp"); // Chuyển hướng đến trang danh sách phát
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Xử lý yêu cầu HTTP GET.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException nếu xảy ra lỗi Servlet
     * @throws IOException nếu xảy ra lỗi I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Xử lý yêu cầu HTTP POST.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException nếu xảy ra lỗi Servlet
     * @throws IOException nếu xảy ra lỗi I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Trả về mô tả ngắn gọn về servlet.
     *
     * @return Chuỗi mô tả servlet
     */
    @Override
    public String getServletInfo() {
        return "Servlet để thêm bài hát vào danh sách phát";
    }
}
