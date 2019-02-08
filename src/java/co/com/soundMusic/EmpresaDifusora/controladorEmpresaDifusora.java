package co.com.soundMusic.EmpresaDifusora;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividad;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividadDaoImpl;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.TipoEmpresaDifusora;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.TipoEmpresaDifusoraDaoImpl;
import co.com.soundMusic.LogAuditoria.LogAuditoria;
import co.com.soundMusic.LogAuditoria.LogAuditoriaDaoImpl;
import co.com.soundMusic.Login.Usuario.Usuario;
import co.com.soundMusic.Seguridad.Permisos.Permisos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class controladorEmpresaDifusora extends HttpServlet {

    List<EmpresaDifusora> lstEmpresasDifusorasp;
    int[] numeroArtistas;
    int identificacion, cantidadArtistas;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorEmpresaDifusora</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorEmpresaDifusora at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        switch (opcion) {
            case "listarEmpresa":
                mostrarPaginaEmpresas(request, response);
                break;
            case "borrar":
                int idEmpresa = Integer.parseInt((String) request.getParameter("idEmpresa"));
                String status = request.getParameter("estado");
                EmpresaDifusoraDaoImpl daoEmpresa = new EmpresaDifusoraDaoImpl(true);
                daoEmpresa.eliminarEmpresaDifusora(status, idEmpresa);
                mostrarPaginaEmpresas(request, response);
                break;
            case "crearEmpresa":
                actualizarDatosFormulario(request);
                request.getRequestDispatcher("/registrarEmpresa.jsp").forward(request, response);
                break;
            case "editar":
                actualizarDatosFormulario(request);
                identificacion = Integer.parseInt((String) request.getParameter("IdEmpresa"));
                for (EmpresaDifusora empresaDifusora : lstEmpresasDifusorasp) {
                    if (empresaDifusora.getIdEmpresaDifusora() == identificacion) {
                        request.setAttribute("empresaDfifusoraAEditar", empresaDifusora);
                    }
                }
                request.getRequestDispatcher("modificarEmpresa.jsp").forward(request, response);
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
                crearEmpresaDifusora(request, response);
                ingresarLogAuditoria(UsuarioId(request, response), 3);
                mostrarPaginaEmpresas(request, response);
                break;
            case "editar":
                editarEmpresaDifusora(request, response, identificacion);
                ingresarLogAuditoria(UsuarioId(request, response), 3);
                mostrarPaginaEmpresas(request, response);
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

    private void mostrarPaginaEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpresaDifusoraDaoImpl daoEmpresa = new EmpresaDifusoraDaoImpl(true);
        lstEmpresasDifusorasp = daoEmpresa.obtenerEmpresasDifusoras();
        numeroArtistas = new EmpresaDifusora().getNumeroArtistas(lstEmpresasDifusorasp);
        cantidadArtistas = new Artista().getNumeroArtistas();
        request.setAttribute("lstEmpresas", lstEmpresasDifusorasp);
        request.setAttribute("numeroArtistas", numeroArtistas);
        request.setAttribute("cantidadArtistas", cantidadArtistas);
        request.getRequestDispatcher("/empresa.jsp").forward(request, response);
    }

    private void crearEmpresaDifusora(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CostoActividad costoOperacion = crearCostoActividad(request);
        TipoEmpresaDifusora tipoEmpresa = obtenerTipoEmpresa(request);
        EmpresaDifusora empresaDifusora = new EmpresaDifusora();

        empresaDifusora.setNombre(request.getParameter("nomEmpresa"));
        empresaDifusora.setFechaCreacion(Date.valueOf(LocalDate.now()));
        empresaDifusora.setStatus("A");
        empresaDifusora.setTipoActividad(tipoEmpresa);
        empresaDifusora.setCostoOperacion(costoOperacion);

        EmpresaDifusoraDaoImpl daoEmpresa = new EmpresaDifusoraDaoImpl(true);
        daoEmpresa.crearEmpresaDifusora(empresaDifusora);
    }

    private void editarEmpresaDifusora(HttpServletRequest request, HttpServletResponse response, int idEmpresaDifusora)
            throws IOException, NumberFormatException, ServletException {
        CostoActividad costoOperacion = editarCostoActividad(request);
        TipoEmpresaDifusora tipoEmpresa = obtenerTipoEmpresa(request);
        EmpresaDifusora empresaDifusora = new EmpresaDifusora();

        empresaDifusora.setIdEmpresaDifusora(idEmpresaDifusora);
        empresaDifusora.setNombre(request.getParameter("nomEmpresa"));
        empresaDifusora.setStatus("A");
        empresaDifusora.setTipoActividad(tipoEmpresa);
        empresaDifusora.setCostoOperacion(costoOperacion);

        EmpresaDifusoraDaoImpl daoEmpresa = new EmpresaDifusoraDaoImpl(true);
        daoEmpresa.actualizarEmpresaDifusora(empresaDifusora);
    }

    private void actualizarDatosFormulario(HttpServletRequest request) {
        TipoEmpresaDifusoraDaoImpl daoTipoEmpresa = new TipoEmpresaDifusoraDaoImpl(true);
        List<TipoEmpresaDifusora> lstTipoEmpresa = daoTipoEmpresa.obtenerTipoEmpresaDifusora();
        request.setAttribute("lstTipoEmpresa", lstTipoEmpresa);
    }

    private void ingresarLogAuditoria(int idUsuario, int idPermisos) {
        LogAuditoriaDaoImpl daoLogAuditoria = new LogAuditoriaDaoImpl(true);
        daoLogAuditoria.crearLog(new LogAuditoria(0, new Usuario(idUsuario), new Permisos(idPermisos)));
    }

    //Metodo para tener el id del usuario en la sesion
    private int UsuarioId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private EmpresaDifusora obtenerEmpresaAEditar(List<EmpresaDifusora> LstEmpresa, int idEmpresa) {
        for (EmpresaDifusora empresaDif : LstEmpresa) {
            if (empresaDif.getIdEmpresaDifusora() == idEmpresa) {
                return empresaDif;
            }
        }
        return null;
    }

    private TipoEmpresaDifusora obtenerTipoEmpresa(HttpServletRequest request) {
        TipoEmpresaDifusoraDaoImpl daoTipoEmpresa = new TipoEmpresaDifusoraDaoImpl(true);
        TipoEmpresaDifusora tipoEmpresa = daoTipoEmpresa.obtenerTipoEmpresaDifusora(
                Integer.parseInt((String) request.getParameter("tipoEmpresa")));
        return tipoEmpresa;
    }

    private CostoActividad crearCostoActividad(HttpServletRequest request) {
        CostoActividad costoActividad = new CostoActividad();
        costoActividad.setCostoPorOperacion(Float.parseFloat((String) request.getParameter("valorOp")));
        costoActividad.setFechaCreacion(Date.valueOf(LocalDate.now()));

        CostoActividadDaoImpl daoCostoActividad = new CostoActividadDaoImpl(true);
        int idCostoActividad = daoCostoActividad.crearCostoActividad(costoActividad);
        costoActividad.setIdCostoActividad(idCostoActividad);

        return costoActividad;
    }

    private CostoActividad editarCostoActividad(HttpServletRequest request) {
        CostoActividad costoActividad = new CostoActividad();
        costoActividad.setIdCostoActividad(Integer.parseInt(request.getParameter("idCosto")));
        costoActividad.setCostoPorOperacion(Float.parseFloat((String) request.getParameter("valorOp")));
        CostoActividadDaoImpl daoCostoActividad = new CostoActividadDaoImpl(true);
        daoCostoActividad.actualizarCostoActividad(costoActividad);
        return costoActividad;
    }
}
