package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import db.SongModel;
import DAO.Song;
import java.util.LinkedList;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedList;
import db.SongModel;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/views/header-head.jsp");
    _jspx_dependants.add("/WEB-INF/views/player-head.jsp");
  }

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
 String contextPath = request.getContextPath();
      out.write('\n');

    String searchKey = request.getParameter("searchkey");
    LinkedList<SongModel> songs = SongModel.searchSongs(searchKey);

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Enjoy Music - Home</title>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"css/head.css\">\n");
      out.write("<script src=\"js/head.js\" defer></script>\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    LinkedList<SongModel> list = SongModel.getNewSongs(9);
    HashMap<String, Object> data = new HashMap<>();
    
    data.put("songs", list);
    data.put("name", "Danh sách phát");
    data.put("userId", -1);
    
    String json = new Gson().toJson(data);

      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"css/player.css\">\n");
      out.write("<script src=\"js/player.js\" defer></script>\n");
      out.write("<script>\n");
      out.write("    const metadata = {\n");
      out.write("        contextPath: '");
      out.print(request.getContextPath());
      out.write("',\n");
      out.write("        jsonSongs: '");
      out.print(json);
      out.write("'\n");
      out.write("    }\n");
      out.write("</script>");
      out.write("\n");
      out.write("\n");
      out.write("        <link href=\"css/search.css\" rel=\"stylesheet\" />\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"wrapper\">\n");
      out.write("            <h3 class=\"title\">Danh sách nhạc (");
      out.print(songs.size());
      out.write(")</h3>\n");
      out.write("            <div class=\"ctn-songs\">\n");
      out.write("                ");
 for (int i = 0; i < songs.size(); i++) {
                        Song song = songs.get(i).toSong();
                
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"ctn-songs_item\" ");
      out.print(i % 3 != 0 ? "style=\"margin-left: 12px;\"" : "");
      out.write(">\n");
      out.write("                    <img src=\"");
      out.print(contextPath + song.image);
      out.write("\" alt=\"");
      out.print(song.title);
      out.write("\" />\n");
      out.write("\n");
      out.write("                    <div class=\"ctn-songs_info\">\n");
      out.write("                        <p>");
      out.print(song.title);
      out.write("</p>\n");
      out.write("                        <p>\n");
      out.write("                            <span>");
      out.print(song.artistName);
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
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/player.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
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
