package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.HashMap;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import db.UserMusic;
import DAO.User;
import java.util.LinkedList;
import db.SongModel;
import DAO.Song;

public final class songs_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    String id_str = (String) request.getParameter("id");
    System.out.println(id_str);
    int id = 0;
    try {
        id = Integer.parseInt(id_str);
    } catch (NumberFormatException e) {
        response.sendRedirect(contextPath);
    }

    SongModel songModel = new SongModel(id);
    songModel.findData();
    songModel.setArtistName(songModel.getArtist().getName());
    Song song = songModel.toSong();

    User user = (User) session.getAttribute("user");
    LinkedList<SongModel> mostViewSongs = SongModel.getMostViewSongs(9);
    LinkedList<Song> userSongs = null;
    if (user != null) {
        UserMusic um = new UserMusic(user.userId);
        userSongs = um.findByUserId();
    }

    HashMap<String, Object> data = new HashMap<>();
    LinkedList<Song> list = new LinkedList<>();
    list.add(song);

    data.put("songs", list);
    data.put("name", "Danh sách phát");
    data.put("userId", -1);

    Gson gsonHome = new GsonBuilder().setLenient().create();

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Enjoy Music - Song</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(contextPath);
      out.write("/css/song.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <input type=\"text\" style=\"display: none\" disabled id=\"void\" />\n");
      out.write("        <div class=\"home-element\">\n");
      out.write("            <h2 class=\"song-title--page\">\n");
      out.write("                <span class=\"song-title--value\">");
      out.print(song.title);
      out.write("</span> - <span class=\"song-artist--value\">");
      out.print(song.artistName);
      out.write("</span>\n");
      out.write("            </h2>\n");
      out.write("\n");
      out.write("            <div class=\"song-box\">\n");
      out.write("                <div class=\"song-thumbnail\">\n");
      out.write("                    <img src=\"");
      out.print(song.image);
      out.write("\" alt=\"");
      out.print(song.title);
      out.write("\"/>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"song-timeline\">\n");
      out.write("                    <span id=\"pageCurrentTime\">00:00</span>\n");
      out.write("                    <span>/</span>\n");
      out.write("                    <span id=\"pageDurationTime\">00:00</span>\n");
      out.write("                    <input type=\"range\" min=\"0\" max=\"100\" value=\"0\" id=\"pageProcessBar\" />\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"song-control-box\">\n");
      out.write("                    <div class=\"song-control song-control--left\">\n");
      out.write("                        <button id=\"playPageButton\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/play-solid.png\" alt=\"Play\">\n");
      out.write("                        </button>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"song-control song-control--right\">\n");
      out.write("                        <input type=\"range\" min=\"0\" max=\"100\" value=\"100\" id=\"volumeInput\" />\n");
      out.write("                        <button id=\"volumeButton\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/volume-high-solid.png\" alt=\"Volume\">\n");
      out.write("                        </button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <audio src=\"");
      out.print(song.href);
      out.write("\" style=\"display: none\"></audio>\n");
      out.write("\n");
      out.write("            <div class=\"song-tools\">\n");
      out.write("                <button> \n");
      out.write("                    <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/square-plus-solid.png\" alt=\"Add\">\n");
      out.write("                    Thêm vào\n");
      out.write("                </button>\n");
      out.write("\n");
      out.write("                <button> \n");
      out.write("                    <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/share-from-square-regular.png\" alt=\"Share\">\n");
      out.write("                    Chia sẻ\n");
      out.write("                </button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"home-element\">\n");
      out.write("            <h2>Danh sách phát</h2>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"home-element\">\n");
      out.write("            <h2>Đề cử</h2>\n");
      out.write("\n");
      out.write("            <div class=\"music-box\">\n");
      out.write("                ");
 for (int i = 0; i < mostViewSongs.size(); i++) {
                        Song so = mostViewSongs.get(i).toSong();
                        String jsonSong = gsonHome.toJson(so).replaceAll("(?<!\\\\)\"", "&quot;");
                
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"");
      out.print((so.href.contains("youtube") ? "music-element music-element-youtube" : "music-element"));
      out.write("\" yt-data=\"");
      out.print((so.href.contains("youtube") ? so.href : ""));
      out.write("\"  >\n");
      out.write("                    <img src=\"");
      out.print((so.image.length() > 0 ? so.image : (contextPath + "/images/demo_music.png")));
      out.write("\" alt=\"");
      out.print(so.title);
      out.write("\" class=\"music-image\"/>\n");
      out.write("\n");
      out.write("                    <div class=\"music-info\">\n");
      out.write("                        <p class=\"music-info--title\">");
      out.print(so.title);
      out.write("</p>\n");
      out.write("\n");
      out.write("                        <p class=\"music-info--artist\">\n");
      out.write("                            <span>");
      out.print(so.artistName);
      out.write("</span>\n");
      out.write("                        </p>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <button class=\"music-menu--icon\">\n");
      out.write("                        <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/ellipsis-vertical-solid.png\" alt=\"Menu\" />\n");
      out.write("                    </button>\n");
      out.write("\n");
      out.write("                    <div class=\"music-menu\">\n");
      out.write("                        <div class=\"music-menu--title\">");
      out.print(so.title);
      out.write("</div>\n");
      out.write("                        <div class=\"music-menu--button\" onclick=\"playerManager.playNowSong('");
      out.print(jsonSong);
      out.write("')\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/play-solid.png\" alt=\"Play\" />\n");
      out.write("                            <span>Phát ngay</span>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"music-menu--button\" onclick=\"playerManager.addToPlaylist('");
      out.print(jsonSong);
      out.write("')\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/square-plus-solid.png\" alt=\"Add\" />\n");
      out.write("                            <span>Thêm vào danh sách phát</span>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"music-menu--button\" onclick=\"playerManager.downloadSong(`");
      out.print(so.href);
      out.write("`)\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/download-solid.png\" alt=\"Download\" />\n");
      out.write("                            <span>Tải xuống</span>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"music-menu--button\" onclick=\"playerManager.shareSong(`");
      out.print(so.songId);
      out.write("`)\">\n");
      out.write("                            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/share-from-square-regular.png\" alt=\"Share\" />\n");
      out.write("                            <span>Chia sẻ</span>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");
  if (userSongs != null && userSongs.size() > 0) { 
      out.write("\n");
      out.write("        <div class=\"home-element\">\n");
      out.write("            <h2>Đã nghe gần đây</h2>\n");
      out.write("\n");
      out.write("            <div class=\"music-box\">\n");
      out.write("                ");
 for (int i = 0; i < userSongs.size(); i++) {
                        Song s = userSongs.get(i);
                
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"music-element\">\n");
      out.write("                    <img src=\"");
      out.print(s.image);
      out.write("\" alt=\"");
      out.print(s.title);
      out.write("\" class=\"music-image\"/>\n");
      out.write("\n");
      out.write("                    <div class=\"music-info\">\n");
      out.write("                        <p class=\"music-info--title\">");
      out.print(s.title);
      out.write("</p>\n");
      out.write("\n");
      out.write("                        <p class=\"music-info--artist\">\n");
      out.write("                            <span>");
      out.print(s.artistName);
      out.write("</span>\n");
      out.write("                        </p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        ");
 }
      out.write("\n");
      out.write("\n");
      out.write("        <div style=\"display: none\" id=\"youtube-iframe\"></div>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            const musicElements = document.querySelectorAll(\".music-element\");\n");
      out.write("\n");
      out.write("            musicElements.forEach((musicElement) => {\n");
      out.write("                const menuButton = musicElement.querySelector(\".music-menu--icon\");\n");
      out.write("                const menuMusic = musicElement.querySelector(\".music-menu\");\n");
      out.write("\n");
      out.write("                menuButton.onclick = (e) => {\n");
      out.write("                    menuMusic.classList.toggle(\"active\");\n");
      out.write("                    e.stopPropagation();\n");
      out.write("                };\n");
      out.write("\n");
      out.write("                menuMusic.onclick = (e) => {\n");
      out.write("                    e.stopPropagation();\n");
      out.write("                };\n");
      out.write("\n");
      out.write("                window.addEventListener(\"click\", (e) => {\n");
      out.write("                    menuMusic.classList.remove(\"active\");\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("        <script src=\"https://www.youtube.com/iframe_api\"></script>\n");
      out.write("        <script src=\"js/player.js\"></script>\n");
      out.write("        <script defer>\n");
      out.write("            const pageMetadata = {\n");
      out.write("                contextPath: '");
      out.print(contextPath);
      out.write("',\n");
      out.write("                jsonSongs: `");
      out.print(gsonHome.toJson(data));
      out.write("`\n");
      out.write("            };\n");
      out.write("            const pagePlayerManager = new PlayerManager(\n");
      out.write("                    \".song-title--value\",\n");
      out.write("                    \".song-thumbnail img\",\n");
      out.write("                    \".song-artist--value\",\n");
      out.write("                    \"#playPageButton\",\n");
      out.write("                    \"#void\",\n");
      out.write("                    \"#void\",\n");
      out.write("                    \"#void\",\n");
      out.write("                    \"#void\",\n");
      out.write("                    \"#pageProcessBar\",\n");
      out.write("                    \"#pageCurrentTime\",\n");
      out.write("                    \"#pageDurationTime\");\n");
      out.write("\n");
      out.write("            pagePlayerManager.setContextPath(pageMetadata.contextPath);\n");
      out.write("            pagePlayerManager.getDataFromJson(pageMetadata.jsonSongs);\n");
      out.write("\n");
      out.write("            console.log(\"run\");\n");
      out.write("            document.querySelectorAll(\".music-element-youtube\").forEach(element => {\n");
      out.write("                const href = element.getAttribute(\"yt-data\");\n");
      out.write("                const titleElement = element.querySelector(\".music-info--title\");\n");
      out.write("                const artistElement = element.querySelector(\".music-info--artist span\");\n");
      out.write("                const imageElement = element.querySelector(\".music-image\");\n");
      out.write("                const id = pagePlayerManager.youtubeManager.detachId(href);\n");
      out.write("\n");
      out.write("                console.log(href);\n");
      out.write("                imageElement.src = \"https://img.youtube.com/vi/\" + id + \"/hqdefault.jpg\";\n");
      out.write("\n");
      out.write("                pagePlayerManager.youtubeManager.fetchYouTubeData(id)\n");
      out.write("                        .then(data => {\n");
      out.write("                            titleElement.innerText = data.title;\n");
      out.write("                            artistElement.innerText = data.channelTitle;\n");
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
