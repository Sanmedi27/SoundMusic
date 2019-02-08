<%@page import="co.com.soundMusic.LogAuditoria.LogAuditoria"%>
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
        <link href="style/auditoria.style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <%@include file="navbar.jsp" %>

        <div class="container main-container">

            <h2>Log de Auditoria</h2>
            <hr>
            <br>


            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre de Usuario</th>
                                <th>Nombre y Apellido</th>            
                                <th>Tipo Usuario</th>
                                <th>Fecha Modificacion</th>
                                <th>Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<LogAuditoria> lstLogAuditoria = (List<LogAuditoria>) request.getAttribute("lstLogAuditoria");
                                for (LogAuditoria logAud : lstLogAuditoria) {
                                    out.print("<tr>");
                                    out.print("<th  scope='row'>" + logAud.getIdLogAuditoria() + "</th>");
                                    out.print("<td>" + logAud.getUsuario().getUsuarioLogin().getNombreUsuario() + "</td>");
                                    out.print("<td>" + logAud.getUsuario().getPrimerNombre() + " " + logAud.getUsuario().getPrimerApellido() + "</td>");
                                    out.print("<td>" + logAud.getUsuario().getPerfil().getNombrePerfil() + "</td>");
                                    out.print("<td>" + logAud.getFecha() + "</td>");
                                    out.print("<td><span class='badge badge-primary'>" + logAud.getOperaciones().getNombrePermiso() + "</span></td>");
                                    out.print("</tr>");
                                }
                            %>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/navbar.js" type="text/javascript"></script>
    </body>

</html>