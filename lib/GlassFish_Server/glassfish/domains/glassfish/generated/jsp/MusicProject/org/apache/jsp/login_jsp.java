package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Enjoy Music - Login</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/login.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"music-login-container\">\n");
      out.write("        <div class=\"music-logo\">\n");
      out.write("            <img src=\"images/music-logo.png\" alt=\"Music Logo\">\n");
      out.write("        </div>\n");
      out.write("        <h2>Login to Enjoy Music</h2>\n");
      out.write("        \n");
      out.write("        ");
 String errorMessage = request.getParameter("err"); 
      out.write("\n");
      out.write("        <div id=\"errorMessage\" class=\"error-message\" style=\"");
      out.print( (errorMessage != null) ? "display: block;" : "display: none;" );
      out.write("\">\n");
      out.write("            ");

                if (errorMessage != null && errorMessage.equals("invalid")) {
            
      out.write("\n");
      out.write("                <p>Username or password is wrong!</p>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <form action=\"api/login\" method=\"post\" id=\"loginForm\" onsubmit=\"handleLoginSubmit(event)\">\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"username\">Username:</label>\n");
      out.write("                <input type=\"text\" id=\"username\" name=\"username\" required placeholder=\"Enter your username\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"password\">Password:</label>\n");
      out.write("                <input type=\"password\" id=\"password\" name=\"password\" required placeholder=\"Enter your password\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <button type=\"submit\" id=\"loginBtn\">Login</button>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("        <div class=\"signup-link\">\n");
      out.write("            <p>Don't have an account? <a href=\"signup.jsp\">Sign up here</a></p>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <script src=\"js/account.js\"></script>\n");
      out.write("</body>\n");
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
