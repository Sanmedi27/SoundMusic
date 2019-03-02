<%@page import="co.com.soundMusic.EmpresaDifusora.EmpresaDifusora"%>
<%@page import="co.com.soundMusic.Artista.Artista"%>
<%@page import="co.com.soundMusic.Negocio.Regalias.Regalia"%>
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
        <link href="style/regalias.style.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/CSS/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="sweetarlert/sweetalert2.min.css" rel="stylesheet" type="text/css"/>

    </head>    
    <body>

        <%@include file="navbar.jsp" %>

        <div class="container main-container">

            <h2>Lista de Pagos</h2>
            <hr>
            <br>
            <div class="row">
                <div class="col-md-12">

                    <div class="row">
                        <div class="col">
                            <!-- <button type="button" href="caluloRegalias.jsp"  class="btn btn-success" data-toggle="modal" data-target="#modalRegalias">Calcular Regalias</button>-->                                                       
                            <a href="caluloRegalias.jsp" class="btn btn-success" data-toggle="modal" data-target="#modalRegalias">Calcular Regalias</a>
                        </div>
                    </div>

                    <br>

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
                                    //out.print("<td><a href='#' class='badge badge-danger btnActivar' activarRegalias=''>" + "Pendiente" + "</a></td>");
                                    if (reg.getStatus().equalsIgnoreCase("a")) {
                                        out.print("<td>" + "Pagado" + "</td>");
                                        out.print("<td><a href='ControladorRegalias?opcion=borrar&idRegalia="
                                                + reg.getIdRegalia() + "&estado=I' class='badge badge-success btnActivar' activarRegalias='0'>Pagado</a></td>");
                                    } else {
                                        out.print("<td>" + "Pendiente" + "</td>");
                                        out.print("<td><a href='ControladorRegalias?opcion=borrar&idRegalia="
                                                + reg.getIdRegalia() + "&estado=A' class='badge btnActivar badge-danger' activarRegalias='1'>Pendiente</a></td>");
                                    }
                                    out.print("</tr>");
                                }
                            %>       
                        </tbody>
                    </table>

                    <br>

                    <!-- Modal -->
                    <div class="modal fade" id="modalRegalias" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Cacular la Regalia</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="ControladorRegalias" method="post">
                                        <input type ="hidden" name="operacion" value="crear" />
                                        <input type="hidden" name="idRegalias" value="0">

                                        <div class="form-group">
                                            <label>Nombre del Artista*</label>
                                            <select name="nomArtista" id="nomArtista" class="form-control">
                                                <option value="">Seleccionar el nombre del artista</option>
                                                <%
                                                    List<Artista> lstArtista = (List<Artista>) request.getAttribute("lstArtistap");
                                                    for (Artista artista : lstArtista) {
                                                        out.print("<option value='" + artista.getIdArtista() + "'>" + artista.getNombreArtistico() + "</option>");
                                                    }
                                                %>                                                
                                            </select>
                                            <div class="invalid-feedback">Debes seleccionar un nombre de artista</div>
                                        </div>

                                        <div class="form-group">
                                            <label>Nombre de la Empresa*</label>
                                            <select name="nomEmpresa"  id="nomEmpresa" class="form-control">
                                                <option value="">Seleccionar la empresa</option>
                                                <%
                                                    List<EmpresaDifusora> lstEmpresas = (List<EmpresaDifusora>) request.getAttribute("lstEmpresaDifusorap");
                                                    for (EmpresaDifusora empresaDif : lstEmpresas) {
                                                        out.print("<option value='" + empresaDif.getIdEmpresaDifusora() + "'>" + empresaDif.getNombre() + "</option>");
                                                    }
                                                %>
                                            </select>
                                            <div class="invalid-feedback">Debes seleccionar una empresa difusora</div>
                                        </div>

                                        <div class="form-group">
                                            <label>No# Operaciones*</label>
                                            <input type="number" name="numOp" id="numOp" class="form-control" placeholder=" numero de operaciones">
                                            <div class="invalid-feedback">El campo numero de operaciones es obligatorio</div>
                                        </div>

                                     <!--   <div class="form-group">
                                            <label>Total Operaciones</label>
                                            <input type="text" name="totalOp" class="form-control" id="totalOp" value="$0.0" disabled>
                                        </div>-->

                                        <div class="form-group">
                                            <img src="icon/discoOro.png"  class="img-thumbnail previsualizar" width="100" alt=""/>
                                        </div>

                                        <input type="submit" id="btnGuardar" class="btn btn-primary btn-block" value="Guardar "/>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/popper.min.js" type="text/javascript"></script>
        <script src="sweetarlert/sweetalert2.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/jquery.bootstrap-growl.min.js" type="text/javascript"></script>
        <script src="js/regalias.js" type="text/javascript"></script>
        <script src="js/navbar.js" type="text/javascript"></script>
    </body>

</html>