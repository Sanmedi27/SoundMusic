package co.com.soundMusic.Artista;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class controladorArtista extends HttpServlet {

    List<Artista> lstArtistasp;
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
        /*        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorArtista</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorArtista at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        switch (opcion) {
            case "listarArtistas":
                mostrarPaginaArtista(request, response);
                break;
            case "borrar":
                int idArtista = Integer.parseInt((String) request.getParameter("IdArtista"));
                String status = request.getParameter("estado");
                daoArtista.eliminarArtista(status, idArtista);
                for (int i = 0; i < lstArtistasp.size(); i++) {
                    if (lstArtistasp.get(i).getIdArtista() == idArtista) {
                        lstArtistasp.get(i).setStatus(status);
                    }
                }
                request.setAttribute("lstArtista", lstArtistasp);
                //mostrarPaginaArtista(request, response);
                request.getRequestDispatcher("/artista.jsp").forward(request, response);
                break;
            case "crearArtista":
                vista = request.getRequestDispatcher("/registrarArtista.jsp");
                vista.forward(request, response);
                break;
            case "editar":
                identificacion = Integer.parseInt((String) request.getParameter("IdArtista"));
                for (Artista artista : lstArtistasp) {
                    if (artista.getIdArtista() == identificacion) {
                        request.setAttribute("artistaEditar", artista);
                        break;
                    }
                }

                vista = request.getRequestDispatcher("modificarArtista.jsp");
                vista.forward(request, response);
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
                crearArtista(request);
                mostrarPaginaArtista(request, response);
                break;
            case "editar":
                editarArtista(request, identificacion);
                mostrarPaginaArtista(request, response);
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

    private void mostrarPaginaArtista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        lstArtistasp = daoArtista.obtenerArtistas();
        request.setAttribute("lstArtista", lstArtistasp);
        request.getRequestDispatcher("/artista.jsp").forward(request, response);
    }

    private void crearArtista(HttpServletRequest request)
            throws ServletException, IOException {
        Artista artista = new Artista();

        artista.setPrimerNombre(request.getParameter("nom1"));
        artista.setSegundoNombre(request.getParameter("nom2"));
        artista.setPrimerApellido(request.getParameter("apellido1"));
        artista.setSegundoApellido(request.getParameter("apellido2"));
        artista.setNombreArtistico(request.getParameter("nomArtista"));
        artista.setGenero(request.getParameter("sexo"));        
        artista.setFechaCreacion(Date.valueOf(LocalDate.now()));
        artista.setStatus("A");
        artista.setRutaImagen(request.getParameter("nuevaFoto"));
        if (artista.getRutaImagen().equals("") || artista.getRutaImagen() == null) {
            artista.setRutaImagen("img/artistas/default/anonymous.png");
        }

        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        daoArtista.crearArtista(artista);
    }

    private void editarArtista(HttpServletRequest request, int idArtista)
            throws IOException, NumberFormatException, ServletException {
        Artista artista = new Artista();

        artista.setIdArtista(idArtista);
        artista.setPrimerNombre(request.getParameter("nom1"));
        artista.setSegundoNombre(request.getParameter("nom2"));
        artista.setPrimerApellido(request.getParameter("apellido1"));
        artista.setSegundoApellido(request.getParameter("apellido2"));
        artista.setNombreArtistico(request.getParameter("nomArtista"));
        artista.setGenero(request.getParameter("sexo"));
        artista.setStatus("A");
        artista.setRutaImagen(request.getParameter("nuevaFoto"));
        if (artista.getRutaImagen().equals("") || artista.getRutaImagen() == null) {
            artista.setRutaImagen("img/artistas/default/anonymous.png");
        }

        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        daoArtista.actualizarArtista(artista);
    }
}
