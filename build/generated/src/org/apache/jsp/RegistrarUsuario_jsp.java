package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class registrarUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/navbar.jsp");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>SoundMusic</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"icon\" type=\"img/png\" href=\"icon/musica.png\"/>\n");
      out.write("        <link href=\"style/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"bootstrap/CSS/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n");
      out.write("  <a class=\"navbar-brand\" href=\"#\">Music Sound</a>\n");
      out.write("  <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\" aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("    <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("  </button>\n");
      out.write("  <div class=\"collapse navbar-collapse nav\" id=\"navbarNavDropdown\">\n");
      out.write("    <ul class=\"navbar-nav\">\n");
      out.write("      <li class=\"nav-item\">\n");
      out.write("          <a class=\"nav-link \" href=\"home.jsp\">Home</a>\n");
      out.write("      </li>\n");
      out.write("      <li class=\"nav-item\">\n");
      out.write("          <a class=\"nav-link\" href=\"usuario.jsp\">Usuarios</a>\n");
      out.write("      </li>\n");
      out.write("      <li class=\"nav-item\">\n");
      out.write("          <a class=\"nav-link\" href=\"empresa.jsp\">Empresas Difunsoras</a>\n");
      out.write("      </li>\n");
      out.write("      <li class=\"nav-item \">\n");
      out.write("          <a class=\"nav-link\" href=\"artista.jsp\">Artistas</a>\n");
      out.write("      </li>\n");
      out.write("\n");
      out.write("    </ul>\n");
      out.write("      <div class=\"row\">\n");
      out.write("          <div class=\"col display-flex\">\n");
      out.write("              <a href =\"login.jsp\" class=\"btn btn-danger\" type=\"button\" click=\"cerrarSesion()\">Salir</a>\n");
      out.write("          </div>\n");
      out.write("      </div>\n");
      out.write("  </div>\n");
      out.write("</nav>");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"container main-container\">\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-2\"></div>\n");
      out.write("\n");
      out.write("                <div class=\"col-md-8\">\n");
      out.write("\n");
      out.write("                    <h3>Registro de Usuario</h3>\n");
      out.write("                    <hr>\n");
      out.write("                    <br>\n");
      out.write("\n");
      out.write("                    <form>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Identificacion</label>\n");
      out.write("                            <input type=\"number\" class=\"form-control\" id=\"textId\" placeholder=\"Ingrese el numero Idenficacion\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-row\">\n");
      out.write("                            <div class=\"col\">\n");
      out.write("                                <label>Nombre</label>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"textName\" placeholder=\"Ingresar el nombre del operador\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col\">\n");
      out.write("                                <label>Apellido</label>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"textLastname\" placeholder=\"Ingresar el apellido del operador\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <br>\n");
      out.write("                            <label>Genero</label>\n");
      out.write("                            <br>\n");
      out.write("                            <div class=\"form-check-inline\">\n");
      out.write("                                <input class=\"form-check-input\" type=\"radio\" name=\"sexo\" id=\"textSex1\" value=\"Masculino\">\n");
      out.write("                                <label class=\"form-check-label\" for=\"textSex1\">Masculino</label>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\" form-check-inline\">\n");
      out.write("                                <input class=\"form-check-input\" type=\"radio\" name=\"sexo\" id=\"textSex2\" value=\"Femenino\">\n");
      out.write("                                <label class=\"form-check-label\" for=\"textSex2\">Femenino</label>\n");
      out.write("                            </div>                         \n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Fecha de Nacimiento</label>\n");
      out.write("                            <input type=\"date\" name=\"fechaNac\" id=\"birthDate\" class=\"form-control\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Nombre del Usuario</label>\n");
      out.write("                            <input type=\"text\" class=\"form-control\" id=\"textNameUser\" placeholder=\"Ingresar el nuevo usuario del operador\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-row\">\n");
      out.write("                            <div class=\"col\">\n");
      out.write("                                <label>Contraseña</label>\n");
      out.write("                                <input type=\"password\" class=\"form-control\" id=\"textPass\" placeholder=\"Password\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col\">\n");
      out.write("                                <label>Repita Contraseña</label>\n");
      out.write("                                <input type=\"password\" class=\"form-control\" id=\"textRepeatPass\" placeholder=\"Password\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <br>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label >Numero del Celular</label>\n");
      out.write("                            <input type=\"number\" class=\"form-control\" name=\"numCelular\" id=\"numPhone\"placeholder=\"Ingrese el numero de celular\"\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"form-check\">\n");
      out.write("                                <input class=\"form-check-input\" type=\"checkbox\" id=\"check\">\n");
      out.write("                                <label class=\"form-check-label\" for=\"check\">\n");
      out.write("                                    Terminos y condiciones\n");
      out.write("                                </label>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary\" id=\"btnGuardar\">Registrar Usuario</button>\n");
      out.write(" \n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-2\"></div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <!--SCRIPT-->\n");
      out.write("    <script src=\"bootstrap/JS/popper.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script src=\"bootstrap/JS/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script src=\"bootstrap/JS/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
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
