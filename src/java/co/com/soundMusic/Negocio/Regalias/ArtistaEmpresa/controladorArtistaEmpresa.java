package co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.Artista.ArtistaDaoImpl;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusora;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusoraDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class controladorArtistaEmpresa extends HttpServlet {

    List<Artista> lstArtistasp;
    List<ArtistaEmpresa> lstArtistasDeEmpresa;
    int idEmpresa;

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
            out.println("<title>Servlet controladorArtistaEmpresa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorArtistaEmpresa at " + request.getContextPath() + "</h1>");
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
        switch (opcion) {
            case "agregarAEmpresa":
                idEmpresa = Integer.parseInt((String) request.getParameter("IdEmpresa"));
                ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
                ArtistaEmpresaDaoImpl daoArtistaEmpresa = new ArtistaEmpresaDaoImpl(true);
                lstArtistasp = daoArtista.obtenerArtistas();
                lstArtistasDeEmpresa = daoArtistaEmpresa.obtenerArtistaDeEmpresa(idEmpresa);
                request.setAttribute("lstArtista", lstArtistasp);
                request.setAttribute("lstArtistasDeEmpresa", lstArtistasDeEmpresa);
                request.getRequestDispatcher("/pruebasTablas/agregarArtistas.jsp").forward(request, response);
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
            case "agregar":
                ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
                artistaEmpresa.getEmpresaDifusora().setIdEmpresaDifusora(idEmpresa);
                String[] idArtistasAgregar = request.getParameterValues("selected");
                ArtistaEmpresaDaoImpl daoArtistaEmpresa = new ArtistaEmpresaDaoImpl(true);
                for (String idArtista : idArtistasAgregar) {
                    artistaEmpresa.getArtista().setIdArtista(Integer.parseInt(idArtista));
                    daoArtistaEmpresa.insertarArtistaEmpresa(artistaEmpresa);
                }
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
        List<EmpresaDifusora> lstEmpresasDifusorasp = daoEmpresa.obtenerEmpresasDifusoras();
        int[] numeroArtistas = new EmpresaDifusora().getNumeroArtistas(lstEmpresasDifusorasp);
        int cantidadArtistas = new Artista().getNumeroArtistas();
        request.setAttribute("lstEmpresas", lstEmpresasDifusorasp);
        request.setAttribute("numeroArtistas", numeroArtistas);
        request.setAttribute("cantidadArtistas", cantidadArtistas);
        request.getRequestDispatcher("/empresa.jsp").forward(request, response);
    }
}
