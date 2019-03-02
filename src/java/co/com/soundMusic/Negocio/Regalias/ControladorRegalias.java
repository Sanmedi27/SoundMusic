/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soundMusic.Negocio.Regalias;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.Artista.ArtistaDaoImpl;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusora;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusoraDaoImpl;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividad;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividadDaoImpl;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresa;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresaDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class ControladorRegalias extends HttpServlet {

    List<Regalia> lstRegaliasp;
    List<Artista> lstArtistap;
    List<EmpresaDifusora> lstEmpresaDifusorap;

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
            out.println("<title>Servlet ControladorRegalias</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRegalias at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher vista;
        RegaliaDaoImpl daoRegalia= new RegaliaDaoImpl(true);
        switch (opcion) {
            case "listarRegalias":
                mostrarPaginaRegalias(request, response);
                break;
            case "borrar":
                int idRegalia = Integer.parseInt((String) request.getParameter("idRegalia"));
                String status = request.getParameter("estado");
                daoRegalia.pagarRegalia(status, idRegalia);
                for (int i = 0; i < lstRegaliasp.size(); i++) {
                    if (lstRegaliasp.get(i).getIdRegalia()== idRegalia) {
                        lstRegaliasp.get(i).setStatus(status);
                    }
                }
                request.setAttribute("lstRegalias", lstRegaliasp);
                request.setAttribute("lstArtistap", lstArtistap);
                request.setAttribute("lstEmpresaDifusorap", lstEmpresaDifusorap);                
                request.getRequestDispatcher("/regalias.jsp").forward(request, response);
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
                crearRegalias(request, response);
                //Ingresar al log de auditoria
                mostrarPaginaRegalias(request, response);
                break;
            case "pagar":
                editarRegalias(request, response);
                mostrarPaginaRegalias(request, response);
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

    private void mostrarPaginaRegalias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        lstArtistap = daoArtista.obtenerArtistas();
        request.setAttribute("lstArtistap", lstArtistap);

        EmpresaDifusoraDaoImpl daoEmpresaDifusora = new EmpresaDifusoraDaoImpl(true);
        lstEmpresaDifusorap = daoEmpresaDifusora.obtenerEmpresasDifusoras();
        request.setAttribute("lstEmpresaDifusorap", lstEmpresaDifusorap);

        RegaliaDaoImpl daoRegalias = new RegaliaDaoImpl(true);
        lstRegaliasp = daoRegalias.obtenerRegaliasSimple();

        procesarListaRegalias();
        request.setAttribute("lstRegalias", lstRegaliasp);
        request.getRequestDispatcher("/regalias.jsp").forward(request, response);
    }

    private void procesarListaRegalias() {
        for (Regalia regalia : lstRegaliasp) {
            boolean auxiliar = false;
            boolean auxiliar2 = false;
            for (Artista artista : lstArtistap) {
                if (Objects.equals(regalia.getArtistaEmpresa().getArtista().getIdArtista(), artista.getIdArtista())) {
                    regalia.getArtistaEmpresa().setArtista(artista);
                    break;
                }
            }
            for (EmpresaDifusora empresaDifusora : lstEmpresaDifusorap) {
                if (Objects.equals(regalia.getArtistaEmpresa().getEmpresaDifusora().getIdEmpresaDifusora(),
                        empresaDifusora.getIdEmpresaDifusora())) {
                    regalia.getArtistaEmpresa().setEmpresaDifusora(empresaDifusora);
                    auxiliar = true;
                }
                if (regalia.getCosto().getIdCostoActividad() == empresaDifusora.getCostoOperacion().getIdCostoActividad()) {
                    regalia.setCosto(empresaDifusora.getCostoOperacion());
                    auxiliar2 = true;
                }
                if (auxiliar && auxiliar2) {
                    break;
                }
            }
        }
    }

    private void crearRegalias(HttpServletRequest request, HttpServletResponse response) {
        Regalia regalia = new Regalia();
        ArtistaEmpresa artistaEmpresa = crearArtistaEmpresa(request);
        CostoActividad costo = crearCosto(request);

        regalia.setNumeroOperaciones(Integer.parseInt(request.getParameter("numOp")));
        regalia.setFecha(Timestamp.from(Instant.now()));
        regalia.setArtistaEmpresa(artistaEmpresa);
        regalia.setCosto(costo);
        regalia.calcularRegalias();

        RegaliaDaoImpl regaliaDao = new RegaliaDaoImpl(true);
        regalia.setIdRegalia(regaliaDao.crearRegalia(regalia));
    }

    private ArtistaEmpresa crearArtistaEmpresa(HttpServletRequest request) {
        ArtistaEmpresaDaoImpl artistaEmpresaDao = new ArtistaEmpresaDaoImpl(true);
        ArtistaDaoImpl artistaDao = new ArtistaDaoImpl(true);
        EmpresaDifusoraDaoImpl empresaDao = new EmpresaDifusoraDaoImpl(true);

        ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();

        artistaEmpresa.setArtista(artistaDao.obtenerArtista(Integer.parseInt(request.getParameter("nomArtista"))));
        artistaEmpresa.setEmpresaDifusora(empresaDao.obtenerEmpresaDifusora(Integer.parseInt(request.getParameter("nomEmpresa"))));

        artistaEmpresa.setIdArtistaEmpresa(artistaEmpresaDao.
                getIdArtistaEmpresaPorArtistayEmpresa(
                        artistaEmpresa.getArtista().getIdArtista(),
                        artistaEmpresa.getEmpresaDifusora().getIdEmpresaDifusora()));
        return artistaEmpresa;
    }

    private CostoActividad crearCosto(HttpServletRequest request) {
        CostoActividadDaoImpl costoDao = new CostoActividadDaoImpl(true);
        CostoActividad costo = costoDao.getCostoPorIdEmpresa(
                Integer.parseInt(request.getParameter("nomEmpresa")));
        return costo;
    }

    private void editarRegalias(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
