$(document).ready(function () {
    $("#btnGuardar").click(function () {
        var nom1 = $("#nom1").val();
        var apellido1 = $("#apellido1").val();
        var apellido2 = $("#apellido2").val();
        var textSex1 = $("#textSex1:checked");
        var textSex2 = $("#textSex2:checked");
        var email = $("#email").val();
        var nomUsuario = $("#nomUsuario").val();
        var pass1 = $("#pass1").val();
        var pass2 = $("#pass2").val();
        var numCel = $("#numCel").val();
        var pais = $("#pais option:selected");
        var check = $("#check:checked");

        if (nom1 == "") {
            $("#nom1").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#nom1").removeClass("is-invalid");

        }

        if (apellido1 == "") {
            $("#apellido1").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#apellido1").removeClass("is-invalid");
        }

        if (apellido2 == "") {
            $("#apellido2").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#apellido2").removeClass("is-invalid");

        }

        if ((textSex1.length == 0) && (textSex2.length == 0)) {
            $("#textSex1").addClass("is-invalid");
            $("#textSex2").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#textSex1").removeClass("is-invalid");
            $("#textSex2").removeClass("is-invalid");
        }

        if (email == "") {
            $("#email").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#email").removeClass("is-invalid");

        }

        if (nomUsuario == "") {
            $("#nomUsuario").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#nomUsuario").removeClass("is-invalid");
        }

        if (pass1 == "") {
            $("#pass1").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#pass1").removeClass("is-invalid");
        }

        if (pass2 == "") {
            $("#pass2").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#pass2").removeClass("is-invalid");
        }

        if (numCel == "") {
            $("#numCel").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#numCel").removeClass("is-invalid");
        }

        if (pais.val() == "") {
            $("#pais").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#pais").removeClass("is-invalid");
        }

        if (check.length == 0) {
            $("#check").addClass("is-invalid");
            alerta();
            return false;
        } else {
            $("#check").removeClass("is-invalid");

        }

    });
    function alerta() {
        swal({
            type: 'error',
            title: 'Oops...',
            text: 'Debes completar los campos que esta seleccionado el signo *'
        });
    }
});