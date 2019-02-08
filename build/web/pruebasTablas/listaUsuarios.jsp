<%-- 
    Document   : listaUsuarios
    Created on : 6/10/2018, 11:22:09 AM
    Author     : Santiago Medina Peláez
--%>

<%@page import="co.com.soundMusic.Login.Usuario.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre Completo</th>
            <th scope="col">Sexo</th>
            <th scope="col">Correo</th>
            <th scope="col">Tipo de Usuario</th>
            <th scope="col">Nombre Usuario</th>
            <th scope="col">Fecha Entrada</th>
            <th scope="col">Activo</th>
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
                out.print("<td>" + usu.getContacto().getEmail() + "</td>");
                out.print("<td>" + usu.getPerfil().getNombrePerfil() + "</td>");
                out.print("<td>" + usu.getUsuarioLogin().getNombreUsuario() + "</td>");
                out.print("<td>" + usu.getFechaCreacion() + "</td>");
                if (usu.getStatus().equalsIgnoreCase("A")) {
                    out.print(
                            "<td><a href='controladorUsuario?opcion=borrar&IdUsuario="
                            + usu.getIdUsuario() + "&estado=I' class='badge badge-success btnActivar' idUsuario="
                            + usu.getIdUsuario() + " activarUsuario='0'> Activado </a></td>");
                } else {
                    out.print(
                            "<td><a href='controladorUsuario?opcion=borrar&IdUsuario="
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
