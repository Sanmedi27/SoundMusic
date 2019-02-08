<%-- 
    Document   : listaLogAuditoria
    Created on : 10/10/2018, 08:35:53 PM
    Author     : Santiago Medina Pelaez
--%>

<%@page import="co.com.soundMusic.LogAuditoria.LogAuditoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="height:100px;overflow:auto;">
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