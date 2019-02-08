<%-- 
    Document   : newjsplistaArtistas
    Created on : 12/10/2018, 12:05:07 PM
    Author     : Santiago Medina Peláez
--%>

<%@page import="co.com.soundMusic.Artista.Artista"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre de Artista</th>
            <th scope="col">Foto</th>
            <th scope="col">Contrato</th>
            <th scope="col">Mes y Año</th>
            <th scope="col">Permisos</th>
            <th scope="col">Operaciones</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Artista> lstArtista = (List<Artista>) request.getAttribute("lstArtista");
            for (Artista art : lstArtista) {
                out.print("<tr>");
                out.print("<td  scope='row'>" + art.getIdArtista() + "</td>");
                out.print("<td>" + art.getNombreArtistico() + "</td>");
                out.print("<td><img src='" + art.getRutaImagen() + "'  class='img img-fluid icon-artista'/></td>");
                out.print("<td class='text-success'>");

                List<String> lstNombreEmpresas = art.getNumeroEmpresas();
                for (String nombreempresa : lstNombreEmpresas) {
                    out.print("<span class='badge badge-primary'>" + nombreempresa + "</span>");
                }
                out.print("</td>");

                out.print("<td>" + art.getFechaCreacion() + "</td>");

                if (art.getStatus().equalsIgnoreCase("a")) {
                    out.print("<td><a href='controladorArtista?opcion=borrar&IdArtista="
                            + art.getIdArtista() + "&estado=I' class='badge badge-success btnActivar' activarUsuario='0'>Activo</a></td>");
                } else {
                    out.print("<td><a href='controladorArtista?opcion=borrar&IdArtista="
                            + art.getIdArtista() + "&estado=A' class='badge btnActivar badge-danger' activarUsuario='1'>Inactivo</a></td>");
                }

                out.print("<td><a href='controladorArtista?opcion=editar&IdArtista="
                        + art.getIdArtista() + "' class='btn btn-warning'>Actualizar</a></td>");                
                out.print("</tr>");
            }
        %>
    </tbody>
</table>