package co.com.soundMusic.Artista;

import co.com.soundMusic.Contacto.Ciudad.Ciudad;
import co.com.soundMusic.Contacto.Contacto;
import co.com.soundMusic.Contacto.ContactoDaoImpl;
import co.com.soundMusic.Contacto.Ciudad.CiudadDaoImpl;
import co.com.soundMusic.Contacto.Pais.Pais;
import co.com.soundMusic.Contacto.Pais.PaisDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
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
        response.setContentType("text/html;charset=UTF-8");
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
        RequestDispatcher vista;
        switch (opcion) {
            case "listarArtistas":
                mostrarPaginaArtista(request, response);
                break;
            case "borrar":
                int idArtista = Integer.parseInt((String) request.getParameter("idArtista"));
                String status = request.getParameter("estado");
                ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
                daoArtista.eliminarArtista(status, idArtista);
                mostrarPaginaArtista(request, response);
                break;
            case "crearArtista":
                actualizarDatosFormulario(request);
                vista = request.getRequestDispatcher("/registrarArtista.jsp");
                vista.forward(request, response);
                break;
            case "editar":
                actualizarDatosFormulario(request);
                identificacion = Integer.parseInt((String) request.getParameter("idArtista"));
                for (Artista artista : lstArtistasp) {
                    if (artista.getIdArtista() == identificacion) {
                        request.setAttribute("artista", artista);
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
                editarArtista(request, response, identificacion);
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
        Contacto contacto = crearContacto(request);
        Artista artista = new Artista();
        
        artista.setPrimerNombre(request.getParameter("nombre1"));
        artista.setSegundoNombre(request.getParameter("nombre2"));
        artista.setPrimerApellido(request.getParameter("apellido1"));
        artista.setSegundoApellido(request.getParameter("apellido2"));
        artista.setNombreArtistico(request.getParameter("nomArtista"));
        artista.setGenero(request.getParameter("sexo"));
        artista.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNac")));
        artista.setFechaCreacion(Date.valueOf(LocalDate.now()));        
        artista.setStatus("A");
        artista.setContacto(contacto);
        
        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        daoArtista.crearArtista(artista);
    }
    
    private void editarArtista(HttpServletRequest request, HttpServletResponse response, int idArtista) throws IOException, NumberFormatException, ServletException {
        Contacto contacto = editarContacto(request);        
        Artista artista = new Artista();
        
        artista.setIdArtista(idArtista);
        artista.setPrimerNombre(request.getParameter("nombre1"));
        artista.setSegundoNombre(request.getParameter("nombre2"));
        artista.setPrimerApellido(request.getParameter("apellido1"));
        artista.setSegundoApellido(request.getParameter("apellido2"));
        artista.setNombreArtistico(request.getParameter("nomArtista"));
        artista.setGenero(request.getParameter("sexo"));
        artista.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNac")));        
        artista.setStatus("A");
        artista.setContacto(contacto);
        
        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        daoArtista.actualizarArtista(artista);
        
        mostrarPaginaArtista(request, response);
    }
    
    private Pais obtenerPais(int idPais, String nombrePais) {
        PaisDaoImpl paisDao = new PaisDaoImpl(true);
        
        if (idPais == 0) {
            paisDao.crearPais(new Pais(idPais, nombrePais));
        }
        return paisDao.obtenerPais(idPais);
        
    }
    
    private Ciudad obtenerCiudad(int idCiudad, String nombreCiudad, Pais pais) {
        CiudadDaoImpl ciudadDao = new CiudadDaoImpl(true);
        
        if (idCiudad == 0) {
            ciudadDao.crearCiudad(new Ciudad(idCiudad, nombreCiudad, pais.getIdPais()));
        }
        return ciudadDao.obtenerCiudad(idCiudad);
    }
    
    private void actualizarDatosFormulario(HttpServletRequest request) {
        PaisDaoImpl daoPais = new PaisDaoImpl(true);
        List<Pais> lstPais = daoPais.obtenerPaises();
        request.setAttribute("lstPais", lstPais);
        
        CiudadDaoImpl ciudadDao = new CiudadDaoImpl(true);
        List<Ciudad> lstCiudad = ciudadDao.obtenerCiudades();
        request.setAttribute("lstCiudad", lstCiudad);
    }
    
    private Contacto crearContacto(HttpServletRequest request) {
        Contacto contacto = new Contacto();
        contacto.setCelular(request.getParameter("numCel"));
        contacto.setTelefono(request.getParameter("numTel"));
        contacto.setDireccion(request.getParameter("direccion"));
        contacto.setBarrio(request.getParameter("barrio"));
        contacto.setEmail(request.getParameter("email"));
        contacto.getCiudad().setIdCiudad(Integer.parseInt(request.getParameter("ciudad")));
        
        ContactoDaoImpl daoContacto = new ContactoDaoImpl(true);
        int idContacto = daoContacto.crearContacto(contacto);
        contacto.setIdContacto(idContacto);
        
        return contacto;
    }
    
    private Contacto editarContacto(HttpServletRequest request) {
        Contacto contacto = new Contacto();
        contacto.setIdContacto(Integer.parseInt(request.getParameter("idContacto")));
        contacto.setCelular(request.getParameter("numCel"));
        contacto.setTelefono(request.getParameter("numTel"));
        contacto.setDireccion(request.getParameter("direccion"));
        contacto.setBarrio(request.getParameter("barrio"));
        contacto.setEmail(request.getParameter("email"));
        contacto.getCiudad().setIdCiudad(Integer.parseInt(request.getParameter("ciudad")));
        
        ContactoDaoImpl daoContacto = new ContactoDaoImpl(true);
        daoContacto.actualizarContacto(contacto);
        
        return contacto;
    }
}
