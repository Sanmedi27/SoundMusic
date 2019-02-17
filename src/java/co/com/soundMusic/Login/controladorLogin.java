package co.com.soundMusic.Login;

import co.com.soundMusic.LogAuditoria.LogAuditoria;
import co.com.soundMusic.LogAuditoria.LogAuditoriaDaoImpl;
import co.com.soundMusic.Login.Usuario.Usuario;
import co.com.soundMusic.Login.Usuario.controladorUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Santiago Medina Peláez
 */
public class controladorLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = (String) request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equalsIgnoreCase("iniciarSesion")) {
                try {
                    iniciarSesion(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (opcion.equalsIgnoreCase("cerrarSesion")) {
                cerrarSesion(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("login.jsp").include(request, response);
        String nomUsuario = request.getParameter("loginNombreUsuario");
        String password = request.getParameter("loginPassword");
        HttpSession sessionUsuario = request.getSession(true);
        if (nomUsuario.equalsIgnoreCase("admin") & password.equalsIgnoreCase("admin")) {
            sessionUsuario.setAttribute("nomUsuario", nomUsuario);
            sessionUsuario.setAttribute("usuarioId", 1);
            response.sendRedirect("home.jsp");
        } else {
            Usuario usuario = new Usuario();
            usuario = (Usuario) usuario.obtenerUsuarioLogeado(nomUsuario, password);

            if (usuario.getUsuarioLogin().getNombreUsuario().equalsIgnoreCase(nomUsuario)
                    & usuario.getUsuarioLogin().getContrasena().equalsIgnoreCase(password)) {

                sessionUsuario.setAttribute("nomUsuario", nomUsuario);
                sessionUsuario.setAttribute("usuarioId", usuario.getIdUsuario());

                //ingresarLogAuditoria(usuario.getIdUsuario(), 1); //1 representa fila 1 de tabla permiso= Iniciar Sesion.
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("navbar.jsp").include(request, response);
        HttpSession sessionUsuario = request.getSession(false);
        //ingresarLogAuditoria((int) sessionUsuario.getAttribute("usuarioId"), 2);//2 representa fila 2 de tabla permiso= Cerrar Sesion.
        sessionUsuario.invalidate();
        response.sendRedirect("login.jsp");
    }

    private void ingresarLogAuditoria(int idUsuario, int idPermisos) {
        LogAuditoriaDaoImpl daoLogAuditoria = new LogAuditoriaDaoImpl(true);
        daoLogAuditoria.crearLog(new LogAuditoria(0, idUsuario, idPermisos));
    }
}
