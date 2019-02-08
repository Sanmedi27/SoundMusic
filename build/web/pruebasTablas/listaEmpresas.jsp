<%-- 
    Document   : listaEmpresas
    Created on : 10/10/2018, 10:54:52 AM
    Author     : Santiago Medina Peláez
--%>

<%@page import="co.com.soundMusic.EmpresaDifusora.EmpresaDifusora"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre de Empresa</th>
            <th scope="col">Total Artista</th>
            <th scope="col">Mes y Año</th>
            <th scope="col"> </th>
            <th scope="col"> </th>
        </tr>
    </thead>
    <tbody>
        <%
            List<EmpresaDifusora> lstEmpresas = (List<EmpresaDifusora>) request.getAttribute("lstEmpresas");
            int[] numeroArtistas = (int[]) request.getAttribute("numeroArtistas");
            int cantidadArtistas = (int) request.getAttribute("cantidadArtistas");
            for (EmpresaDifusora emDif : lstEmpresas) {
                out.print("<tr>");
                out.print("<th  scope='row'>" + emDif.getIdEmpresaDifusora() + "</th>");
                out.print("<td>" + emDif.getNombre() + "</td>");
                out.print("<td>" + numeroArtistas[lstEmpresas.indexOf(emDif)] + "</td>");
                out.print("<td>" + emDif.getFechaCreacion() + "</td>");
                out.print("<td><a href='controladorEmpresaDifusora?opcion=editar&IdEmpresa="
                        + emDif.getIdEmpresaDifusora() + "' class='btn btn-warning'>Actualizar</a></td>");
                if (cantidadArtistas != numeroArtistas[lstEmpresas.indexOf(emDif)]) {
                    out.print("<td><a href='controladorArtistaEmpresa?opcion=agregarAEmpresa&IdEmpresa="
                            + emDif.getIdEmpresaDifusora() + "' class='btn btn-warning'>Agregar Artista</a></td>");
                    out.print("</tr>");
                }else{
                    out.print("<td> </td>");
                    out.print("</tr>");
                }
            }
        %>
    </tbody>
</table>