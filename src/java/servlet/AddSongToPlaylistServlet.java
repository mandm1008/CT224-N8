package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.PlaylistModel;
import db.SongModel;
import DAO.User;

/**
 * Servlet để thêm bài hát vào danh sách phát.
 *
 * @author DELL
 */
public class AddSongToPlaylistServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Lấy thông tin từ request
            String playlistIdStr = request.getParameter("playlistId");
            String[] songIds = request.getParameterValues("songId");
            User user = (User) request.getSession().getAttribute("user");

            // Kiểm tra trạng thái đăng nhập
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Kiểm tra tham số
            if (playlistIdStr == null || playlistIdStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số playlistId.");
                return;
            }
            if (songIds == null || songIds.length == 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số songId.");
                return;
            }

            int playlistId;
            try {
                playlistId = Integer.parseInt(playlistIdStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Giá trị playlistId không hợp lệ.");
                return;
            }

            // Kiểm tra tồn tại của playlist và quyền truy cập
            PlaylistModel playlistModel = new PlaylistModel();
            PlaylistModel playlist = playlistModel.findById(playlistId);
            if (playlist == null || playlist.getOwnerId() != user.userId) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Không có quyền truy cập playlist này.");
                return;
            }

            // Xử lý từng bài hát
            for (String songIdStr : songIds) {
                try {
                    int songId = Integer.parseInt(songIdStr);

                    // Kiểm tra tồn tại của bài hát
                    SongModel songModel = new SongModel();
                    SongModel song = songModel.findById(songId);
                    if (song == null) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy bài hát với ID: " + songId);
                        continue; // Bỏ qua bài hát không tồn tại
                    }

                    // Kiểm tra trùng lặp
                    if (playlist.containsSong(songId)) {
                        response.getWriter().println("Bài hát ID: " + songId + " đã có trong danh sách phát.");
                        continue; // Bỏ qua bài hát đã tồn tại
                    }

                    // Thêm bài hát vào playlist
                    playlist.addSong(songId);
                } catch (NumberFormatException e) {
                    response.getWriter().println("ID bài hát không hợp lệ: " + songIdStr);
                }
            }

            // Chuyển hướng sau khi xử lý xong
            response.sendRedirect("PlaylistDetails.jsp?playlistId=" + playlistId);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để thêm bài hát vào danh sách phát";
    }
}
