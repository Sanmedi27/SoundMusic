
<%@page import="java.util.List"%>
<%@page import="co.com.soundMusic.EmpresaDifusora.EmpresaDifusora"%>
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

                    <h3>Modulo de Empresa Difusora </h3>
                    <hr>
                    <br>
                    <div class="row">
                        <div class="col">
                            <a href="controladorEmpresaDifusora?opcion=crearEmpresa" class="btn btn-success" id="btnRegistrar">Registrar Empresa</a>
                        </div>
                    </div>

                    <br>

                    <table class="table" id="listaEmpresas">
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
                                    } else {
                                        out.print("<td> </td>");
                                        out.print("</tr>");
                                    }
                                }
                            %>
                        </tbody>
                    </table>

                </div>
            </div>

            <br>
            <br>

            <div class="row">
                <div class="col-md-12">

                    <h3>Empresas,Artistas y Albunes Recientes</h3>
                    <hr>
                    <br> 

                    <div class="row">
                        <div class="col">
                            <h4>Spotify</h4>
                            <hr>
                            <br>
                            <table class="table">
                                <thead>

                                <h5>                                
                                    <img src="img/artistas/axwell&ingrosso/art4.jpg" class="img img-thumbnail icon-artista"/>
                                    Axwell & Ingrosso
                                </h5> 

                                <tr class="thead-dark">
                                    <th></th>
                                    <th scope="col">Nombre del albun</th>
                                    <th scope="col">Ganacias</th>
                                    <th scope="col">Fecha de Produccion</th>
                                </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>More Than You Know</td>
                                        <td class="text-success">$10.000.000 COP</td>
                                        <td>8 de diciembre del 2017</td>
                                    </tr>

                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>More Than You Know</td>
                                        <td class="text-success">$10.000.000 COP</td>
                                        <td>8 de diciembre del 2017</td>
                                    </tr>
                                </tbody>
                            </table>
                            <br>
                            <table class="table">
                                <thead>

                                <h5>                                
                                    <img src="img/artistas/axwell&ingrosso/art4.jpg" class="img img-thumbnail icon-artista"/>
                                    Axwell & Ingrosso
                                </h5> 

                                <tr class="thead-dark">
                                    <th></th>
                                    <th scope="col">Nombre del albun</th>
                                    <th scope="col">Ganacias</th>
                                    <th scope="col">Fecha de Produccion</th>
                                </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>More Than You Know</td>
                                        <td class="text-success">$10.000.000 COP</td>
                                        <td>8 de diciembre del 2017</td>
                                    </tr>

                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>More Than You Know</td>
                                        <td class="text-success">$10.000.000 COP</td>
                                        <td>8 de diciembre del 2017</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>


                    </div>


                </div>

            </div>
        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/navbar.js" type="text/javascript"></script>
    </body>

</html>