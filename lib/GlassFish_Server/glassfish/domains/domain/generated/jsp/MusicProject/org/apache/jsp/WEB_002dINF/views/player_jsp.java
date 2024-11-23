package org.apache.jsp.WEB_002dINF.views;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedList;
import db.SongModel;

public final class player_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    LinkedList<SongModel> list = SongModel.getNewSongs(9);
    HashMap<String, Object> data = new HashMap<>();

    data.put("songs", list);
    data.put("name", "Danh sách phát");
    data.put("userId", -1);

    Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();
    String json = gson.toJson(data);

      out.write('\n');
      out.write('\n');
 String contextPath = request.getContextPath();
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"https://www.youtube.com/iframe_api\" defer></script>\n");
      out.write("\n");
      out.write("<div class=\"music-player\">\n");
      out.write("    <div class=\"player-info\">\n");
      out.write("        <img src=\"");
      out.print(contextPath);
      out.write("/images/demo_music.png\" alt=\"Song Thumbnail\" class=\"song-thumbnail\">\n");
      out.write("        <div class=\"song-details\">\n");
      out.write("            <span class=\"song-title\">Song Name</span>\n");
      out.write("            <span class=\"song-artist\">Artist Name</span>\n");
      out.write("        </div>\n");
      out.write("    </div> \n");
      out.write("\n");
      out.write("    <div class=\"player-controls\">\n");
      out.write("        <button id=\"shuffleBtn\" class=\"control-btn\" style=\"opacity: 0.4\">\n");
      out.write("            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/shuffle-solid.png\" alt=\"Shuffle\">\n");
      out.write("        </button>\n");
      out.write("        <button id=\"prevBtn\" class=\"control-btn\">\n");
      out.write("            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/backward-step-solid.png\" alt=\"Previous\">\n");
      out.write("        </button>\n");
      out.write("        <button id=\"playBtn\" class=\"control-btn play-btn\">\n");
      out.write("            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/play-solid.png\" alt=\"Play\">\n");
      out.write("        </button>\n");
      out.write("        <button id=\"nextBtn\" class=\"control-btn\">\n");
      out.write("            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/forward-step-solid.png\" alt=\"Next\">\n");
      out.write("        </button>\n");
      out.write("        <button id=\"repeatBtn\" class=\"control-btn\" style=\"opacity: 0.4\">\n");
      out.write("            <img src=\"");
      out.print(contextPath);
      out.write("/images/icons/repeat-solid.png\" alt=\"Repeat\">\n");
      out.write("        </button>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"player-timer\">\n");
      out.write("        <span id=\"currentTime\">00:00</span>\n");
      out.write("        <input type=\"range\" id=\"progressBar\" value=\"0\" max=\"100\">\n");
      out.write("        <span id=\"durationTime\">00:00</span>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div id=\"youtube-iframe\" style=\"display: none;\"></div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script src=\"js/player.js\"></script>\n");
      out.write("<script defer>\n");
      out.write("    const metadata = {\n");
      out.write("        contextPath: '");
      out.print(request.getContextPath());
      out.write("',\n");
      out.write("        jsonSongs: `");
      out.print(json);
      out.write("`\n");
      out.write("    };\n");
      out.write("    const playerManager = new PlayerManager();\n");
      out.write("\n");
      out.write("    playerManager.setContextPath(metadata.contextPath);\n");
      out.write("    playerManager.getDataFromJson(metadata.jsonSongs);\n");
      out.write("</script>");
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
