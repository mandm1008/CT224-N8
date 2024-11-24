package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.LinkedList;
import db.SongModel;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import db.UserMusic;
import db.PlaylistModel;
import DAO.User;
import DAO.Song;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 String contextPath = request.getContextPath();
      out.write('\n');

    User user = (User) session.getAttribute("user");
    LinkedList<SongModel> mostViewSongs = SongModel.getMostViewSongs(9);
    LinkedList<Song> userSongs = null;
    if (user != null) {
        UserMusic um = new UserMusic(user.userId);
        userSongs = um.findByUserId();
    }

    LinkedList<PlaylistModel> newAdminPlaylist = (new PlaylistModel()).getAdminPlaylist();

    Gson gsonHome = new GsonBuilder().setLenient().create();

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Enjoy Music - Home</title>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(contextPath);
      out.write("/css/song.css\">\n");
      out.write("        <script src=\"");
      out.print(contextPath);
      out.write("/js/home.js\" defer></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"home-wrapper\">\n");
      out.write("            <div class=\"home-element\">\n");
      out.write("                <h2>Nhạc nổi bật</h2>\n");
      out.write("\n");
      out.write("                <div class=\"music-box\">\n");
      out.write("                    ");
 for (int i = 0; i < mostViewSongs.size(); i++) {
                            Song song = mostViewSongs.get(i).toSong();
                            String jsonSong = gsonHome.toJson(song).replaceAll("(?<!\\\\)\"", "&quot;");
                    
      out.write("\n");
      out.write("\n");
      out.write("                    <div class=\"");
      out.print((song.href.contains("youtube") ? "music-element music-element-youtube" : "music-element"));
      out.write("\" yt-data=\"");
      out.print((song.href.contains("youtube") ? song.href : ""));
      out.write("\"  >\n");
      out.write("                        <img src=\"");
      out.print((song.image.length() > 0 ? song.image : (contextPath + "/images/demo_music.png")));
      out.write("\" alt=\"");
      out.print(song.title);
      out.write("\" class=\"music-image\"/>\n");
      out.write("\n");
      out.write("                        <div class=\"music-info\">\n");
      out.write("                            <p class=\"music-info--title\" onclick=\"window.location = '");
      out.print(contextPath);
      out.write("/songs.jsp?id=");
      out.print(song.songId);
      out.write("'\">");
      out.print(song.title);
      out.write("</p>\n");
      out.write("\n");
      out.write("                            <p class=\"music-info--artist\">\n");
      out.write("                                <span>");
      out.print(song.artistName);
      out.write("</span>\n");
      out.write("                            </p>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <button class=\"music-menu--icon\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/ellipsis-vertical-solid.png\" alt=\"Menu\" />\n");
      out.write("                        </button>\n");
      out.write("\n");
      out.write("                        <div class=\"music-menu\">\n");
      out.write("                            <div class=\"music-menu--title\" onclick=\"window.location = '");
      out.print(contextPath);
      out.write("/songs.jsp?id=");
      out.print(song.songId);
      out.write("'\">");
      out.print(song.title);
      out.write("</div>\n");
      out.write("                            <div class=\"music-menu--button\" onclick=\"playerManager.playNowSong('");
      out.print(jsonSong);
      out.write("')\">\n");
      out.write("                                <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/play-solid.png\" alt=\"Play\" />\n");
      out.write("                                <span>Phát ngay</span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"music-menu--button\" onclick=\"playerManager.addToPlaylist('");
      out.print(jsonSong);
      out.write("')\">\n");
      out.write("                                <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/square-plus-solid.png\" alt=\"Add\" />\n");
      out.write("                                <span>Thêm vào danh sách phát</span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"music-menu--button\" onclick=\"playerManager.downloadSong(`");
      out.print(song.href);
      out.write("`)\">\n");
      out.write("                                <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/download-solid.png\" alt=\"Download\" />\n");
      out.write("                                <span>Tải xuống</span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"music-menu--button\" onclick=\"playerManager.shareSong(`");
      out.print(song.songId);
      out.write("`)\">\n");
      out.write("                                <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/share-from-square-regular.png\" alt=\"Share\" />\n");
      out.write("                                <span>Chia sẻ</span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
 }
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");
  if (userSongs != null && userSongs.size() > 0) { 
      out.write("\n");
      out.write("            <div class=\"home-element\">\n");
      out.write("                <h2>Đã nghe gần đây</h2>\n");
      out.write("\n");
      out.write("                <div class=\"music-box\">\n");
      out.write("                    ");
 for (int i = 0; i < userSongs.size(); i++) {
                            Song song = userSongs.get(i);
                    
      out.write("\n");
      out.write("\n");
      out.write("                    <div class=\"");
      out.print((song.href.contains("youtube") ? "music-element music-element-youtube" : "music-element"));
      out.write("\" yt-data=\"");
      out.print((song.href.contains("youtube") ? song.href : ""));
      out.write("\"  >\n");
      out.write("                        <img src=\"");
      out.print((song.image.length() > 0 ? song.image : (contextPath + "/images/demo_music.png")));
      out.write("\" alt=\"");
      out.print(song.title);
      out.write("\" class=\"music-image\"/>\n");
      out.write("\n");
      out.write("                        <div class=\"music-info\">\n");
      out.write("                            <p class=\"music-info--title\" onclick=\"window.location = '");
      out.print(contextPath);
      out.write("/songs.jsp?id=");
      out.print(song.songId);
      out.write("'\">");
      out.print(song.title);
      out.write("</p>\n");
      out.write("\n");
      out.write("                            <p class=\"music-info--artist\">\n");
      out.write("                                <span>");
      out.print(song.artistName);
      out.write("</span>\n");
      out.write("                            </p>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
 }
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"home-element\">\n");
      out.write("                <h2>BHX</h2>\n");
      out.write("\n");
      out.write("                <div class=\"slider-container\">\n");
      out.write("                    <div class=\"home-slider\">\n");
      out.write("                        ");
 for (int i = 0; i < mostViewSongs.size() && i < 5; i++) {
                                Song song = mostViewSongs.get(i).toSong();
                        
      out.write("\n");
      out.write("\n");
      out.write("                        <div class=\"");
      out.print((song.href.contains("youtube") ? "music-element music-element-youtube slider-element" : "music-element slider-element"));
      out.write("\" yt-data=\"");
      out.print((song.href.contains("youtube") ? song.href : ""));
      out.write("\"  >\n");
      out.write("\n");
      out.write("                            <div class=\"music-top\">\n");
      out.write("                                <h2>");
      out.print(i + 1);
      out.write("</h2>\n");
      out.write("                            </div>\n");
      out.write("                            <img src=\"");
      out.print((song.image.length() > 0 ? song.image : (contextPath + "/images/demo_music.png")));
      out.write("\" alt=\"");
      out.print(song.title);
      out.write("\" class=\"music-image\"/>\n");
      out.write("\n");
      out.write("                            <div class=\"music-info\">\n");
      out.write("                                <p class=\"music-info--title\" onclick=\"window.location = '");
      out.print(contextPath);
      out.write("/songs.jsp?id=");
      out.print(song.songId);
      out.write("'\">\n");
      out.write("                                    ");
      out.print(song.title);
      out.write("\n");
      out.write("                                </p>\n");
      out.write("                                <p class=\"music-info--artist\">\n");
      out.write("                                    <span>");
      out.print(song.artistName);
      out.write("</span>\n");
      out.write("                                </p>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <button class=\"prev-btn\"><img src=\"");
      out.print(contextPath);
      out.write("/images/icons/backward-solid.png\" alt=\"Previous\"></button>\n");
      out.write("                    <button class=\"next-btn\"><img src=\"");
      out.print(contextPath);
      out.write("/images/icons/forward-solid.png\" alt=\"Next\"></button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");
 if (newAdminPlaylist.size() > 0) {
      out.write("\n");
      out.write("            <div class=\"home-element\">\n");
      out.write("                <h2>Danh sách phát mới cập nhật</h2>\n");
      out.write("\n");
      out.write("                <div class=\"playlist-new\">\n");
      out.write("                    <div class=\"playlist-new-box\">\n");
      out.write("                        ");

                            for (int i = 0; i < newAdminPlaylist.size(); i++) {
                                PlaylistModel playlist = newAdminPlaylist.get(i);

                        
      out.write("\n");
      out.write("                        <div class=\"playlist-new-element\">\n");
      out.write("                            <span>\n");
      out.write("                                ");
      out.print(playlist.getName());
      out.write("\n");
      out.write("                            </span>\n");
      out.write("\n");
      out.write("                            <button class=\"music-menu--icon\" onclick=\"window.location = '");
      out.print(contextPath);
      out.write("/RunPlaylist?id=");
      out.print(playlist.getPlaylistId());
      out.write("'\">\n");
      out.write("                                <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/play-solid.png\" alt=\"Play\">\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            ");
  if (user != null && user.userId > 0) {
      out.write("\n");
      out.write("            <div class=\"playlist-form\">\n");
      out.write("                <h2>Danh Sách Nhạc của tui</h2>\n");
      out.write("                <a href=\"playlist.jsp\" class=\"btn btn-primary\">Xem Danh Sách</a>\n");
      out.write("            </div>\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/player.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <script defer>\n");
      out.write("            document.querySelectorAll(\".music-element-youtube\").forEach(element => {\n");
      out.write("                const href = element.getAttribute(\"yt-data\");\n");
      out.write("                const titleElement = element.querySelector(\".music-info--title\");\n");
      out.write("                const titleMenuElement = element.querySelector(\".music-menu--title\");\n");
      out.write("                const artistElement = element.querySelector(\".music-info--artist span\");\n");
      out.write("                const imageElement = element.querySelector(\".music-image\");\n");
      out.write("                const id = playerManager.youtubeManager.detachId(href);\n");
      out.write("\n");
      out.write("                console.log(href);\n");
      out.write("                imageElement.src = \"https://img.youtube.com/vi/\" + id + \"/hqdefault.jpg\";\n");
      out.write("\n");
      out.write("                playerManager.youtubeManager.fetchYouTubeData(id)\n");
      out.write("                        .then(data => {\n");
      out.write("                            titleElement.innerText = data.title;\n");
      out.write("                            artistElement.innerText = data.channelTitle;\n");
      out.write("                            if (titleMenuElement !== null) {\n");
      out.write("                                titleMenuElement.innerText = data.title;\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
