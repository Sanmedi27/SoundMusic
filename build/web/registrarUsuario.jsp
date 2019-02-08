<%@page import="co.com.soundMusic.Seguridad.Perfiles.Perfil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sound Music</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="img/png" href="icon/musica.png"/>
        <link href="style/style.css" rel="stylesheet" type="text/css"/>
        <link href="style/usuario.sytle.css" rel="stylesheet" type="text/css"/>
        <link href="style/usuario.sytle.css" rel="stylesheet" type="text/css"/>
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
                <div class="col-md-2"></div>

                <div class="col-md-8">

                    <h3>Registro de Usuario</h3>
                    <hr>
                    <br>

                    <div class="row">
                        <div class="col">
                            <a href="controladorUsuario?opcion=listarUsuarios" class="btn btn-danger" id="btn-regresar">Regresar</a>
                            <button type="button" class="btn btn-success" id="btn-nuevoRegistro">Agregar Nuevo Usuario</button>
                        </div>
                    </div>

                    <br>

                    <form name="formulario_usuario" action="controladorUsuario" method="post">
                        <input type="hidden" name="cod" value="0">
                        <input type ="hidden" name="operacion" value="crear" />


                        <div class="form-row">
                            <div class="col">
                                <label>Primer Nombre*</label>
                                <input type="text" class="form-control " name="nombre1" id="nom1" placeholder="Ingresar el primer nombre" onkeypress="return validarSoloLetras(event);">
                                <div class="invalid-feedback ">El primer nombre es un campo obligatorio</div>

                            </div>
                            <div class="col">
                                <label>Segundo Nombre</label>
                                <input type="text" class="form-control" name="nombre2" id="nom2" placeholder="Ingresar el segundo nombre" onkeypress="return validarSoloLetras(event);">
                            </div>
                        </div>

                        <br>

                        <div class="form-row">
                            <div class="col">
                                <label>Primer Apellido*</label>
                                <input type="text" class="form-control " name="apellido1" id="apellido1" placeholder="Ingresar el primer apellido " onkeypress="return validarSoloLetras(event);">
                                <div class="invalid-feedback ">El primer apellido es un campo obligatorio</div>

                            </div>
                            <div class="col">
                                <label>Segundo Apellido*</label>
                                <input type="text" class="form-control " name="apellido2" id="apellido2" placeholder="Ingresar el segundo apellido" onkeypress="return validarSoloLetras(event);">
                                <div class="invalid-feedback ">El segundo apellido es un campo obligatorio</div>
                            </div>
                        </div>

                        <div class="form-group radio">
                            <br>
                            <h8>Genero*</h8>
                            <br>
                            <input class="form-check-input" type="radio" name="sexo" id="textSex1" value="M" >
                            <label class="form-check-label " for="textSex1">Masculino</label>

                            <input class="form-check-input" type="radio" name="sexo" id="textSex2" value="F">
                            <label class="form-check-label" for="textSex2">Femenino</label>
                            <br>
                            <div class="invalid-feedback ">Debe seleccionar un sexo</div>

                        </div>
                        <div class="form-group">
                            <label>Nombre del Usuario*</label>
                            <input type="text" class="form-control" name="nomUsuario" id="nomUsuario" placeholder="Ingresar el nuevo usuario del operador"  >
                            <span id="error1"></span>
                            <div class="invalid-feedback ">El nombre de usuario es un campo obligatorio</div>
                            <br>
                            <ul class="ul-usuario">
                                <li id="mayu">1 Mayuscula</li>
                                <li id="min">4 Minusculas</li>
                                <li id="digitos">2 Digitos.</li>
                                <li id="especial">1 Caracter especiual.</li>
                                <li id="caracteres">Minimo 8 caracteres.</li>
                            </ul>

                        </div>

                        <div class="form-row">
                            <div class="col">
                                <label>Contraseña*</label>
                                <input type="password" class="form-control" name="pass1" id="pass1" placeholder="Password" >
                                <span id="error2"></span>
                                <div class="invalid-feedback ">La contraseña debe ser obligatorio</div>
                                <br>
                                <ul class="ul-password">
                                    <li id="mayus">1 Mayusculas.</li>
                                    <li id="lower">11 Minusculas.</li>
                                    <li id="numbers">3 Digitos.</li>
                                    <li id="len">Minimo 15 caracteres.</li>
                                </ul>
                            </div>

                            <div class="col">
                                <label>Repita Contraseña*</label>
                                <input type="password" class="form-control" name="pass2" id="pass2" placeholder="Password" >
                                <div class="invalid-feedback ">La contraseña debe ser igual dato</div>
                                <span id="msg-error"></span>

                            </div>
                        </div>

                        <div class="form-row">
                            <label >Perfil*</label>
                            <select class="form-control" name="perfil" id="perfil">
                                <option value="">Seleccionar perfil</option>
                                <%//Listar los paises en la base de datos
                                    List<Perfil> listaPerfil
                                            = (List<Perfil>) request.getAttribute("lstPerfil");
                                    for (Perfil perfil : listaPerfil) {
                                        out.print("<option value='" + perfil.getIdPerfil()
                                                + "'>" + perfil.getNombrePerfil() + "</option>");
                                    }
                                %>
                            </select>
                            <div class="invalid-feedback ">El campo perfil debe ser obligatorio</div>
                        </div>  
                        <br>
                        <div class="form-check checkbox">
                            <input class="form-check-input" type="checkbox" id="check" >
                            <label class="form-check-label" for="check">
                                Terminos y condiciones
                            </label>
                            <div class="invalid-feedback ">Este campo es obligatorio</div>
                        </div>

                        <br>
                        <input type="submit" value="Registrar Usuario" class="btn btn-primary btn-block" id="btnGuardar">
                    </form>
                </div>
            </div>
            <div class="col-md-2"></div>

        </div>
    </div>

    <!--SCRIPT-->
    <script src="bootstrap/JS/popper.min.js" type="text/javascript"></script>
    <script src="bootstrap/JS/jquery.min.js" type="text/javascript"></script>
    <script src="bootstrap/JS/bootstrap.min.js" type="text/javascript"></script>
    <script src="sweetarlert/sweetalert2.min.js" type="text/javascript"></script>
    <script src="js/validacionUsuario.js" type="text/javascript"></script>
    <script src="js/nomUsuario.js" type="text/javascript"></script>
    <script src="js/password.js" type="text/javascript"></script>
    <script src="js/validacionLetras.js" type="text/javascript"></script>
</body>

</html>