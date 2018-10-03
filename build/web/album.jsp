
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

                    <h3>Modulo de Albumes </h3>
                    <hr>
                    <br>
                    <div class="row">
                        <div class="col">
                            <a href="registrarAlbum.jsp" class="btn btn-success" id="btnRegistrar">Registrar Album</a>
                        </div>
                    </div>

                    <br>

                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre de Artista</th>
                                <th scope="col">Foto</th>
                                <th scope="col">Contrato</th>
                                <th scope="col">Mes y Año</th>
                                <th scope="col">Permisos</th>
                                <th scope="col">Operaciones</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td scope="row">1</td>
                                <td>Avicci</td>
                                <td><img src="img/art1.jpg"  class="img img-fluid icon-artista"/></td>
                                <td class="text-success">
                                    <span class="badge badge-primary">Youtube</span>
                                    <span class="badge badge-primary">Spotyfy</span>
                                </td>
                                <td>Abril 2018</td>
                                <td><a href="#" class="badge badge-success btnActivar" activarUsuario="0">Activo</a></td>
                                <td><a href="modificarArtista.jsp" class="btn btn-warning">Actualizar</a></td>
                            </tr>

                            <tr>
                                <td scope="row">2</td>
                                <td>Bad Bunny</td>
                                <td><img src="img/art2.png"  class="img img-fluid icon-artista"/></td>
                                <td class="text-success">
                                    <span class="badge badge-primary">Youtube</span>
                                    <span class="badge badge-primary">Google Music</span>
                                </td>

                                <td>Agosto 2018</td>
                                <td><a href="#" class="badge badge-danger btnActivar" activarUsuario="0">Inactivo</a></td>
                                <td><a href="modificarArtista.jsp" class="btn btn-warning">Actualizar</a></td>
                            </tr>

                            <tr>
                                <td scope="row">3</td>
                                <td>Guns And Roses</td>
                                <td><img src="img/art3.png"  class="img img-fluid icon-artista"/></td>
                                <td class="text-success">
                                    <span class="badge badge-primary">Youtube</span>
                                    <span class="badge badge-primary">Spotyfy</span>
                                    <span class="badge badge-primary">Itunes Music</span>
                                </td>
                                <td>Abril 2018</td>
                                <td><a href="#" class="badge badge-success btnActivar" activarUsuario="0">Activo</a></td>
                                <td><a href="modificarArtista.jsp" class="btn btn-warning">Actualizar</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <br>
            <br>
            <!--Tablas Recientes de los albunes-->
            <div class="row">
                <div class="col-md-12">

                    <h3>Albunes Recientes</h3>
                    <hr>
                    <br> 

                    <!--LISTA DE ALBUN INICIO-->
                    <div class="row">
                        <div class="col-4">
                            <img src="img/artistas/axwell&ingrosso/art4.jpg" class="img img-thumbnail "/>
                        </div>
                        <div class="col">
                            <h4>Axwell & Ingrosso</h4>
                            <hr>
                            <br>
                            <table class="table">
                                <thead>
                                    <tr class="thead-dark">
                                        <th scope="col">Imagen</th>
                                        <th scope="col">Nombre del Albun</th>
                                        <th scope="col">No. Canciones</th>
                                        <th scope="col">Total</th>
                                        <th scope="col">Mes de Lanzamiento</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>More Than You Know</td>
                                        <td>15</td>
                                        <td class="text-success">COP $500.000</td>
                                        <td>Mayo 2018</td>
                                    </tr>

                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>Renegade</td>
                                        <td>30</td>
                                        <td class="text-success">COP $500.000</td>
                                        <td>Mayo 2018</td>

                                    </tr>
                                </tbody>
                            </table>
                            <br>
                        </div>
                    </div>
                    <!--LISTA DE ALBUN FIN-->

                    <!--LISTA DE ALBUN INICIO-->
                    <div class="row">
                        <div class="col-4">
                            <img src="img/artistas/axwell&ingrosso/art4.jpg" class="img img-thumbnail "/>
                        </div>
                        <div class="col">
                            <h4>Axwell & Ingrosso</h4>
                            <hr>
                            <br>
                            <table class="table">
                                <thead>
                                    <tr class="thead-dark">
                                        <th scope="col">Imagen</th>
                                        <th scope="col">Nombre del Albun</th>
                                        <th scope="col">No. Canciones</th>
                                        <th scope="col">Total</th>
                                        <th scope="col">Mes de Lanzamiento</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>More Than You Know</td>
                                        <td>15</td>
                                        <td class="text-success">COP $500.000</td>
                                        <td>Mayo 2018</td>
                                    </tr>

                                    <tr>
                                        <th> 
                                            <img src="img/artistas/axwell&ingrosso/albun1.jpg" class="img img-thumbnail icon-artista"/>
                                        </th>
                                        <td>Renegade</td>
                                        <td>30</td>
                                        <td class="text-success">COP $500.000</td>
                                        <td>Mayo 2018</td>

                                    </tr>
                                </tbody>
                            </table>
                            <br>
                        </div>
                    </div>
                    <!--LISTA DE ALBUN FIN-->

                </div>

            </div>

        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/artista.js" type="text/javascript"></script>
        <script src="js/navbar.js" type="text/javascript"></script>
    </body>

</html>