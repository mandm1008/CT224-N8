/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.PlaylistModel;

public class DeletePlaylistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this operation.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] playlistIds = request.getParameterValues("playlistIds");
        if (playlistIds != null) {
            PlaylistModel playlistModel = new PlaylistModel();
            for (String id : playlistIds) {
                try {
                    int playlistId = Integer.parseInt(id);
                    playlistModel.deleteById(playlistId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new ServletException("Invalid playlist ID: " + id, e);
                } catch (Exception e) {  // Bắt ngoại lệ từ deleteById
                    e.printStackTrace();
                    throw new ServletException("Error deleting playlist ID: " + id, e);
                }
            }
        }
        response.sendRedirect("playlist.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting playlists";
    }
}
