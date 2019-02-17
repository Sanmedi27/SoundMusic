package co.com.soundMusic.Login.Usuario;

import co.com.soundMusic.LogAuditoria.LogAuditoria;
import co.com.soundMusic.LogAuditoria.LogAuditoriaDaoImpl;
import co.com.soundMusic.Login.CuentaUsuario.UsuarioLogin;
import co.com.soundMusic.Login.CuentaUsuario.UsuarioLoginDaoImpl;
import co.com.soundMusic.Seguridad.Perfiles.Perfil;
import co.com.soundMusic.Seguridad.Perfiles.PerfilDaoImpl;
import co.com.soundMusic.Seguridad.Permisos.Permisos;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class controladorUsuario extends HttpServlet {

    List<Usuario> lstUsuariop;
    int identificacion;

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

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
        RequestDispatcher vista;
        switch (opcion) {
            case "listarUsuarios":
                mostrarPaginaUsuario(request, response);
                break;
            case "editarEstado":
                int idUsuario = Integer.parseInt((String) request.getParameter("IdUsuario"));
                String status = request.getParameter("estado");
                UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
                daoUsuario.eliminarUsuario(status, idUsuario);
                mostrarPaginaUsuario(request, response);
                break;
            case "crearUsuario":
                actualizarDatosFormulario(request);
                vista = request.getRequestDispatcher("/registrarUsuario.jsp");
                vista.forward(request, response);
                break;
            case "editar":
                actualizarDatosFormulario(request);
                identificacion = Integer.parseInt((String) request.getParameter("IdUsuario"));
                for (Usuario usuario : lstUsuariop) {
                    if (usuario.getIdUsuario() == identificacion) {
                        request.setAttribute("usuarioEditar", usuario);
                        break;
                    }
                }
                request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
                break;
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
        String operacion = request.getParameter("operacion");

        switch (operacion) {
            case "crear":
                crearUsuario(request);
                ingresarLogAuditoria(UsuarioId(request, response), 5);
                mostrarPaginaUsuario(request, response);
                request.setAttribute("lstUsuario", lstUsuariop);
                request.getRequestDispatcher("/usuario.jsp").forward(request, response);
                break;
            case "editar":
                editarUsuario(request, response, identificacion);
                ingresarLogAuditoria(UsuarioId(request, response), 4);
                mostrarPaginaUsuario(request, response);
                break;
        }
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

    private void mostrarPaginaUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
        lstUsuariop = daoUsuario.obtenerUsuarios();
        request.setAttribute("lstUsuario", lstUsuariop);
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }

    private void crearUsuario(HttpServletRequest request)
            throws ServletException, IOException {
        UsuarioLogin usuarioLogin = crearUsuarioLogin(request);
        Perfil perfil = obtenerPerfil(request);
        Usuario usuario = new Usuario();

        usuario.setPrimerNombre(request.getParameter("nombre1"));
        usuario.setSegundoNombre(request.getParameter("nombre2"));
        usuario.setPrimerApellido(request.getParameter("apellido1"));
        usuario.setSegundoApellido(request.getParameter("apellido2"));
        usuario.setFechaCreacion(Date.valueOf(LocalDate.now()));
        usuario.setStatus("A");
        usuario.setGenero(request.getParameter("sexo"));
        usuario.setUsuarioLogin(usuarioLogin);
        usuario.setPerfil(perfil);

        UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
        usuario.setIdUsuario(daoUsuario.crearUsuario(usuario));
        lstUsuariop.add(usuario);
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response, int idUsuario)
            throws IOException, NumberFormatException, ServletException {
        UsuarioLogin usuarioLogin = editarUsuarioLogin(request);
        Perfil perfil = obtenerPerfil(request);
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(idUsuario);
        usuario.setPrimerNombre(request.getParameter("nombre1"));
        usuario.setSegundoNombre(request.getParameter("nombre2"));
        usuario.setPrimerApellido(request.getParameter("apellido1"));
        usuario.setSegundoApellido(request.getParameter("apellido2"));
        usuario.setStatus("A");
        usuario.setGenero(request.getParameter("sexo"));
        usuario.setUsuarioLogin(usuarioLogin);
        usuario.setPerfil(perfil);

        UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
        daoUsuario.actualizarUsuario(usuario);
    }

    private void actualizarDatosFormulario(HttpServletRequest request) {
        PerfilDaoImpl daoPerfil = new PerfilDaoImpl(true);
        List<Perfil> lstPerfil = daoPerfil.obtenerPerfiles();
        request.setAttribute("lstPerfil", lstPerfil);
    }

    private void ingresarLogAuditoria(int idUsuario, int idPermisos) {
        LogAuditoriaDaoImpl daoLogAuditoria = new LogAuditoriaDaoImpl(true);
        daoLogAuditoria.crearLog(new LogAuditoria(0, idUsuario, idPermisos));
    }

    //Metodo para tener el id del usuario en la sesion
    private int UsuarioId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idUsuario = 0;
        response.setContentType("text/html");
        request.getRequestDispatcher("navbar.jsp").include(request, response);

        HttpSession session = request.getSession(false);
        if (session != null) {
            idUsuario = (int) session.getAttribute("usuarioId");
            return idUsuario;
        }
        return idUsuario;
    }

    private Usuario obtenerUsuarioAEditar(List<Usuario> lstUsuario, int idUsuario) {
        for (Usuario usuario : lstUsuario) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    private UsuarioLogin crearUsuarioLogin(HttpServletRequest request) {
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin.setNombreUsuario(request.getParameter("nomUsuario"));
        usuarioLogin.setContrasena(request.getParameter("pass1"));

        UsuarioLoginDaoImpl daoUsuarioLogin = new UsuarioLoginDaoImpl(true);
        int idUsLog = daoUsuarioLogin.crearUsuarioLogin(usuarioLogin);

        usuarioLogin.setIdUsuarioLogin(idUsLog);

        return usuarioLogin;
    }

    private Perfil obtenerPerfil(HttpServletRequest request) {
        PerfilDaoImpl daoPerfil = new PerfilDaoImpl(true);
        Perfil perfil = daoPerfil.obtenerPerfil(Integer.parseInt((String) request.getParameter("perfil")));
        return perfil;
    }

    private UsuarioLogin editarUsuarioLogin(HttpServletRequest request) {
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin.setIdUsuarioLogin(Integer.parseInt(request.getParameter("idUsuarioLogin")));
        usuarioLogin.setNombreUsuario(request.getParameter("nomUsuario"));
        usuarioLogin.setContrasena(request.getParameter("pass1"));

        UsuarioLoginDaoImpl daoUsuarioLogin = new UsuarioLoginDaoImpl(true);
        daoUsuarioLogin.actualizarUsuarioLogin(usuarioLogin);

        return usuarioLogin;
    }
}
