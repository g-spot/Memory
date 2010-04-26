package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class table_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
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
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n");
      out.write("\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\">\n");
      out.write("\t<head>\n");
      out.write("\t\t<title>Memory</title>\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/screen.css\" />\n");
      out.write("\t\t<meta http-equiv=\"Content-type\" content='text/html; charset=\"UTF-8\"'/>\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t\t<div id=\"header\">\n");
      out.write("\t\t\t<img src=\"img/header_left_v2.png\" alt=\"Logo der Lehrveranstaltung EWA\" />\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div id=\"navigation_container\">\n");
      out.write("\t\t\t<ul id=\"navigation\">\n");
      out.write("\t\t\t\t<li>|&nbsp;Userdaten &auml;ndern</li>\n");
      out.write("\t\t\t\t<li>&nbsp;|&nbsp;Zur&uuml;ck zur Lounge</li>\n");
      out.write("\t\t\t\t<li>&nbsp;|&nbsp;Ausloggen</li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div id=\"content\">\n");
      out.write("\t\t\t<div id=\"right-box\">\n");
      out.write("\t\t\t\t<h1 class=\"hidden\">Table</h1>\n");
      out.write("\t\t\t\t<h2>Spielstand</h2>\n");
      out.write("\t\t\t\t<table id=\"score\" summary=\"Die folgende Tabelle listet die Spielstaende auf!\">\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th>&nbsp;</th>\n");
      out.write("\t\t\t\t\t\t<th>Du</th>\n");
      out.write("\t\t\t\t\t\t<th>Gegner</th>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th class=\"tablehead\">Paare</th>\n");
      out.write("\t\t\t\t\t\t<td>1</td>\n");
      out.write("\t\t\t\t\t\t<td>0</td></tr>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th class=\"tablehead\">Versuche</th>\n");
      out.write("\t\t\t\t\t\t<td>3</td>\n");
      out.write("\t\t\t\t\t\t<td>2</td>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th class=\"tablehead\">Zeit</th>\n");
      out.write("\t\t\t\t\t\t<td>0:34</td>\n");
      out.write("\t\t\t\t\t\t<td>0:50</td>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</table>\n");
      out.write("\t\t\t\t<h2>Spielinformationen</h2>\n");
      out.write("\t\t\t\t<table id=\"gameinfo\" summary=\"Die folgende Tabelle listet die Spielinformationen auf!\">\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th>Restliche Paare</th>\n");
      out.write("\t\t\t\t\t\t<td>12</td>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th>Thema</th>\n");
      out.write("\t\t\t\t\t\t<td>Alternative Rock</td>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</table>\n");
      out.write("\t\t\t\t<h2>Top-Künstler</h2>\n");
      out.write("\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t<a href=\"#\"><img src=\"img/muse.jpg\" alt=\"Bild der Alternative Band Muse\" />&nbsp;Muse</a>\n");
      out.write("\t\t\t\t</p>\n");
      out.write("\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t<a href=\"#\"><img src=\"img/placebo.jpg\" alt=\"Bild der Alternative Band Placebo\" />&nbsp;Placebo</a>\n");
      out.write("\t\t\t\t</p>\t\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t<a href=\"#\"><img src=\"img/radiohead.png\" alt=\"Bild der Alternative Band Radiohead\" />&nbsp;Radiohead</a>\n");
      out.write("\t\t\t\t</p>\n");
      out.write("\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t<a href=\"#\"><img src=\"img/incubus.jpeg\" alt=\"Bild der Alternative Band Incubus\" />&nbsp;Incubus</a>\n");
      out.write("\t\t\t\t</p>\n");
      out.write("\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t<a href=\"#\"><img src=\"img/foo_fighters.jpeg\" alt=\"Bild der Alternative Band Foo Fighters\" />&nbsp;Foo-Fighters</a>\n");
      out.write("\t\t\t\t</p>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div id=\"card-box\">\n");
      out.write("\t\t\t\t<div>\n");
      out.write("                                    <input type=\"image\" src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\"/>\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div>\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div>\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div>\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t\t<img src=\"img/card_background.png\" alt=\"Eine nicht aufgedeckte Karte\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div id=\"clear-divider\"></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div id=\"footer\">\n");
      out.write("\t\t\t&copy; 2010 EWA Memory.\n");
      out.write("\t\t</div>\n");
      out.write("\t</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
