<%@page import="co.com.soundMusic.Login.Usuario.Usuario"%>
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

    </head>

    <body>

        <%@include file="navbar.jsp" %>

        <div class="container main-container">
            <div class="row animated fadeIn fast">
                <div class="col-md-12">

                    <h3>Modulo de Usuarios</h3>
                    <hr>
                    <br>
                    <div class="row">
                        <div class="col">
                            <a href="controladorUsuario?opcion=crearUsuario" class="btn btn-success" id="registrarUsuario">Registrar Usuario</a>                            
                        </div>
                        <div class="col">
                            <label>Listar solo activos</label>                            
                            <input type="checkbox" id="modoListar" onclick="cambiarLista()">
                        </div>
                    </div>

                    <br>

                    <table class="table" id="listaUsuarios">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre Completo</th>
                                <th scope="col">Sexo</th>
                                <th scope="col">Tipo de Usuario</th>
                                <th scope="col">Nombre Usuario</th>
                                <th scope="col">Fecha Entrada</th>
                                <th scope="col">Estado</th>
                                <th scope="col">Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Usuario> lstUsuario = (List<Usuario>) request.getAttribute("lstUsuario");
                                for (Usuario usu : lstUsuario) {
                                    out.print("<tr>");
                                    out.print("<th  scope='row'>" + usu.getIdUsuario() + "</th>");
                                    out.print("<td>" + usu.getPrimerNombre() + " " + usu.getPrimerApellido() + "</td>");
                                    out.print("<td>" + usu.getGenero() + "</td>");
                                    out.print("<td>" + usu.getPerfil().getNombrePerfil() + "</td>");
                                    out.print("<td>" + usu.getUsuarioLogin().getNombreUsuario() + "</td>");
                                    out.print("<td>" + usu.getFechaCreacion() + "</td>");
                                    if (usu.getStatus().equalsIgnoreCase("A")) {
                                        out.print(
                                                "<td><a href='controladorUsuario?opcion=editarEstado&IdUsuario="
                                                + usu.getIdUsuario() + "&estado=I' class='badge badge-success btnActivar' idUsuario="
                                                + usu.getIdUsuario() + " activarUsuario='0'> Activado </a></td>");
                                    } else {
                                        out.print(
                                                "<td><a href='controladorUsuario?opcion=editarEstado&IdUsuario="
                                                + usu.getIdUsuario() + "&estado=A' class='badge btnActivar badge-danger' idUsuario="
                                                + usu.getIdUsuario() + " activarUsuario='1'>Inactivo</a></td>");
                                    }
                                    out.print(
                                            "<td><a href='controladorUsuario?opcion=editar&IdUsuario="
                                            + usu.getIdUsuario() + "' class='btn btn-warning'>Actualizar</a></td>");
                                    out.print("</tr>");
                                }
                            %>
                        </tbody>
                    </table>



                </div>
            </div>
        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/usuario.js" type="text/javascript"></script>
        <script src="js/navbar.js" type="text/javascript"></script>
    </body>

</html>