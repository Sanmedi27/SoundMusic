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
        <link href="sweetarlert/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
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

                    <div class="row">
                        <div class="col">
                            <a href="controladorArtista?opcion=listarArtistas" class="btn btn-danger" id="btn-regresar">Regresar</a>
                        </div>
                    </div>

                    <br>

                    <form name="formulario_artista" action="controladorArtista" method="post">
                        <input type="hidden" name="cod" value="0">
                        <input type ="hidden" name="operacion" value="crear" />

                        <div class="form-row">
                            <div class="col">
                                <label>Primer Nombre*</label>
                                <input type="text" class="form-control" name="nom1" id="nom1" placeholder="Ingresar el primer nombre" onkeypress="return validarSoloLetras(event);">
                                <div class="invalid-feedback ">El primer nombre es un campo obligatorio</div>

                            </div>
                            <div class="col">
                                <label>Segundo Nombre</label>
                                <input type="text" class="form-control" name="nom2" id="nom2" placeholder="Ingresar el segundo nombre" onkeypress="return validarSoloLetras(event);">

                            </div>
                        </div>

                        <br>

                        <div class="form-row">
                            <div class="col">
                                <label>Primer Apellido*</label>
                                <input type="text" class="form-control" name="apellido1" id="apellido1" placeholder="Ingresar el primer apellido "  onkeypress="return validarSoloLetras(event);">

                                <div class="invalid-feedback ">El primer apellido es un campo obligatorio</div>

                            </div>
                            <div class="col">
                                <label>Segundo Apellido*</label>
                                <input type="text" class="form-control" name="apellido2" id="apellido2" placeholder="Ingresar el segundo apellido" onkeypress="return validarSoloLetras(event);">

                                <div class="invalid-feedback ">El segundo apellido es un campo obligatorio</div>
                            </div>
                        </div>

                        <div class="form-group radio">
                            <br>
                            <h6>Genero*</h6>
                            <br>
                            <input class="form-check-input " type="radio" name="sexo" id="textSex1" value="M" >
                            <label class="form-check-label " for="textSex1">Masculino</label>

                            <input class="form-check-input " type="radio" name="sexo" id="textSex2" value="F">
                            <label class="form-check-label" for="textSex2">Femenino</label>
                            <br>
                            <div class="invalid-feedback ">Debe seleccionar un sexo</div>

                        </div>
                        <br>
                        <div class="form-group">
                            <label>Nombre de Artista*</label>
                            <input type="text" class="form-control" id="nomArtista" name="nomArtista" placeholder="ingresar el nombre del artista">
                            <div class="invalid-feedback">El campo nombre artista es obligatorio</div>
                        </div>
                        <br>                               
                        <div class="form-group">
                            <div class="panel">Subir Foto Del Artista</div>
                            <input type="file" class="nuevaFoto" name="nuevaFoto">

                            <p class="help-block">Peso maximo de la foto 2MB</p>

                            <img src="img/artistas/default/anonymous.png" class="img-thumbnail previsualizar" width="100px"/>

                        </div>

                        <input type="submit" value="Registrar Artista" class="btn btn-primary btn-block" id="btnGuardar">
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
        <script src="js/validacionLetras.js" type="text/javascript"></script>

    </body>
</html>