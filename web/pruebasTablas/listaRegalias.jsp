<%-- 
    Document   : listaRegalias
    Created on : 4/11/2018, 10:47:58 PM
    Author     : Santiago Medina Pelaez
--%>

<%@page import="co.com.soundMusic.Negocio.Regalias.Regalia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th>Nombre Artista</th>
            <th>Empresa Difunsora</th>                  
            <th>Fecha</th>
            <th>Operaciones</th>
            <th>Costo Operaión</th>
            <th>Regalia</th>
            <th>Bonificacion</th>     
            <th>Pago</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Regalia> lstRegalias = (List<Regalia>) request.getAttribute("lstRegalias");
            for (Regalia reg : lstRegalias) {
                out.print("<tr>");
                out.print("<td>" + reg.getArtistaEmpresa().getArtista().getNombreArtistico() + "</td>");
                out.print("<td>" + reg.getArtistaEmpresa().getEmpresaDifusora().getNombre() + "</td>");
                out.print("<td>" + reg.getFecha() + "</td>");
                out.print("<td>" + reg.getNumeroOperaciones() + "</td>");
                out.print("<td>" + "$ " + reg.getCosto().getCostoPorOperacion() + "</td>");
                out.print("<td class='text-success'>" + "$ " + reg.getTotalGanado() + "</td>");
                out.print("<td>" + "Pendiente" + "</td>");
                out.print("<td><a href='#' class='badge badge-danger btnActivar' activarRegalias=''>" + "Pendiente" + "</a></td>");
                out.print("</tr>");
            }
        %>       
    </tbody>
</table>
