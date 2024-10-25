package org.apache.jsp.WEB_002dINF.views;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import DAO.User;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 String contextPath = request.getContextPath(); 
      out.write('\n');
 User user = (User) request.getSession().getAttribute("user"); 
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    var contextPath = \"");
      out.print(contextPath);
      out.write("\";\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<header class=\"header\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"logo\">\n");
      out.write("            <a href=\"");
      out.print(contextPath);
      out.write("\">Enjoy Music</a>\n");
      out.write("        </div>\n");
      out.write("        <nav class=\"nav-links\">\n");
      out.write("            <a href=\"");
      out.print(contextPath);
      out.write("\">Trang chủ</a>\n");
      out.write("            <a href=\"");
      out.print(contextPath);
      out.write("\">BXH</a>\n");
      out.write("            <a href=\"");
      out.print(contextPath);
      out.write("\">Thể loại</a>\n");
      out.write("        </nav>\n");
      out.write("        <div class=\"search-bar\">\n");
      out.write("            <input id=\"searchInput\" type=\"text\" placeholder=\"Tìm kiếm bài hát, ca sĩ...\">\n");
      out.write("            <button id=\"searchButton\">Tìm kiếm</button>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        ");
 if (user == null) { 
      out.write("\n");
      out.write("            <div class=\"auth-buttons\">\n");
      out.write("                <a href=\"");
      out.print(contextPath);
      out.write("/login.jsp\">Đăng nhập</a>\n");
      out.write("                <a href=\"");
      out.print(contextPath);
      out.write("/signup.jsp\">Đăng ký</a>\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        \n");
      out.write("        ");
 if (user != null) { 
      out.write("\n");
      out.write("            <div class=\"user-info\">\n");
      out.write("                <img class=\"avatar\" src=\"");
      out.print(contextPath);
      out.write("/images/avatar.jpg\" alt=\"");
      out.print(user.username);
      out.write("\">\n");
      out.write("                <span class=\"username\">");
      out.print(user.username);
      out.write("</span>\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </div>\n");
      out.write("</header>\n");
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
