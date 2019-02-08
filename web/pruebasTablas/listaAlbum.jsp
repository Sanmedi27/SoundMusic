<%-- 
    Document   : listaAlbum
    Created on : 23/10/2018, 01:44:41 PM
    Author     : Santiago Medina Peláez
--%>

<%@page import="co.com.soundMusic.Artista.Album.Album"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre Artista</th>
            <th scope="col">Nombre del albun</th>
            <th scope="col">Foto Album</th>            
            <th scope="col">No. Canciones</th>            
            <th scope="col">Fecha Lanzamiento</th>
            <th scope="col">Operación</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Album> lstAlbum = (List<Album>) request.getAttribute("lstAlbum");
            for (Album album : lstAlbum) {
                out.print("<tr>");
                out.print("<th  scope='row'>" + album.getIdAlbum() + "</th>");
                out.print("<td>" + album.getArtista().getNombreArtistico() + "</td>");
                out.print("<td>" + album.getNombre() + "</td>");
                out.print("<td><img src='" + album.getRutaImagen() + "'  class='img img-fluid icon-artista'/></td>");
                out.print("<td><span class='badge badge-primary'>" + album.getNumeroCanciones() + "</span></td>");
                if (album.getFechaFinalizacion() != null) {
                    out.print("<td>" + album.getFechaFinalizacion() + "</td>");
                } else {
                    out.print("<td>" + " " + "</td>");
                }
                out.print("<td>"
                        + "<button type='button' href='controladorAlbum?opcion=editar&IdAlbum=" + album.getIdAlbum()
                        + "' class='btn btn-warning'>Actualizar</button></td>");
                out.print("</tr>");
            }
        %>
    </tbody>
</table>


