<%@page import="java.util.List"%>
<%@page import="co.com.soundMusic.Artista.Artista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>SoundMusic</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="img/png" href="icon/musica.png" />
        <link href="style/style.css" rel="stylesheet" type="text/css" />
        <link href="bootstrap/CSS/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="sweetarlert/sweetalert2.min.css" rel="stylesheet" type="text/css" />
    </head>

    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Music Sound</a>
        </nav>
    </header>

    <body>

        <div class="container main-container">

            <div class="row">
                <div class="col-md-12">

                    <h3>Registro de Artista</h3>
                    <hr>
                    <%
                        Artista artista = (Artista) request.getAttribute("artistaEditar");
                    %>
                    <div class="row">
                        <div class="col">
                            <a href="controladorArtista?opcion=listarArtistas" class="btn btn-danger" id="btn-regresar">Regresar</a>
                        </div>
                    </div>

                    <br>

                    <form name="formulario_artista" action="controladorArtista" method="post">
                        <input type="hidden" name="operacion" id="operacion" value="editar">
                        <%
                            out.println("<input type='hidden' name='IdArtista' id='IdArtista' readonly value='" + artista.getIdArtista() + "'/>");
                        %>
                        <div class="form-row">
                            <div class="col">
                                <label>Primer Nombre*</label>
                                <% out.println("<input type='text' class='form-control' name='nom1' id='nom1' value='" + artista.getPrimerNombre() + "'>");%>
                                <div class="invalid-feedback ">El primer nombre es un campo obligatorio</div>

                            </div>
                            <div class="col">
                                <label>Segundo Nombre</label>
                                <% out.println("<input type='text' class='form-control' name='nom2' id='nom2' value='" + artista.getSegundoNombre() + "'>");%>
                            </div>
                        </div>

                        <br>

                        <div class="form-row">
                            <div class="col">
                                <label>Primer Apellido*</label>
                                <% out.println("<input type='text' class='form-control' name='apellido1' id='apellido1' value='" + artista.getPrimerApellido() + "'>");%>
                                <div class="invalid-feedback ">El primer apellido es un campo obligatorio</div>

                            </div>
                            <div class="col">
                                <label>Segundo Apellido*</label>
                                <% out.println("<input type='text' class='form-control' name='apellido2' id='apellido2' placeholder='" + artista.getSegundoApellido() + "'>");%>
                                <div class="invalid-feedback ">El segundo apellido es un campo obligatorio</div>
                            </div>
                        </div>

                        <div class="form-group radio">
                            <br>
                            <h8>Genero*</h8>
                                <%if (artista.getGenero().equalsIgnoreCase("M")) {
                                        out.println("<input class='form-check-input ' type='radio' name='sexo' id='textSex1' value='M' checked>");%>
                            <label class="form-check-label " for="textSex1">Masculino</label>
                            <%out.println("<input class='form-check-input ' type='radio' name='sexo' id='textSex2' value='F'>");%>
                            <label class="form-check-label" for="textSex2">Femenino</label>
                            <%} else {
                                out.println("<input class='form-check-input ' type='radio' name='sexo' id='textSex1' value='M'>");%>
                            <label class="form-check-label " for="textSex1">Masculino</label>
                            <%out.println("<input class='form-check-input ' type='radio' name='sexo' id='textSex2' value='F' checked>");%>
                            <label class="form-check-label" for="textSex2">Femenino</label>
                            <%}%>
                            <br>
                            <div class="invalid-feedback ">Debe seleccionar un campo</div>
                            <br>
                        </div>
                        <div class="form-group">
                            <label>Nombre de Artista*</label>
                            <% out.println("<input type='text' class='form-control' id='textNomArtista' name='nomArtista' value='" + artista.getNombreArtistico() + "'>");%>
                            <div class="invalid-feedback">El campo nombre artista es obligatorio</div>
                        </div>  
                        <div class="form-group">
                            <div class="panel">Subir Foto Del Artista</div>
                            <input type="file" class="nuevaFoto" name="nuevaFoto">

                            <p class="help-block">Peso maximo de la foto 2MB</p>

                            <img src="img/artistas/default/anonymous.png" class="img-thumbnail previsualizar" width="100px" />

                        </div>

                        <input type="submit" value="Modificar Artista" class="btn btn-primary btn-block" id="btnGuardar">
                    </form>
                </div>
            </div>
        </div>

        <!--SCRIPT-->
        <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="sweetarlert/sweetalert2.min.js" type="text/javascript"></script>
        <script src="js/validacionArtista.js" type="text/javascript"></script>
    </body>

</html>