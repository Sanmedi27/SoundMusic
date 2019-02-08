<%@page import="co.com.soundMusic.Artista.Artista"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SoundMusic</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="img/png" href="icon/musica.png"/>
        <link href="style/style.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/CSS/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/JS/popper.min.js" type="text/javascript"></script>

    </head>

    <body>

        <%@include file="navbar.jsp" %>

        <div class="container main-container">
            <div class="row animated fadeIn fast">
                <div class="col-md-12">

                    <h3>Modulo de Artistas </h3>
                    <hr>
                    <br>
                    <div class="row">
                        <div class="col">
                            <a href="controladorArtista?opcion=crearArtista" class="btn btn-success" id="btnRegistrar">Registrar Artista</a>
                        </div>
                        <div class="col">
                            <label>Listar solo activos</label>                            
                            <input type="checkbox" id="modoListar" onclick="cambiarLista()">
                        </div>
                    </div>

                    <br>

                    <table class="table" id="listaArtistas">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre de Artista</th>
                                <th scope="col">Foto</th>
                                <th scope="col">Contrato</th>
                                <th scope="col">Mes y Año</th>
                                <th scope="col">Estado</th>
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
                </div>
            </div>
            <br>
        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/artista.js" type="text/javascript"></script>
        <script src="js/navbar.js" type="text/javascript"></script>
    </body>

</html>