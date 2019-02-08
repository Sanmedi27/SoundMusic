$(function () {
    var mayu = new RegExp("^(?=.*[A-Z])");
    var especial = new RegExp("^(?=.*[!@#$%&*])");
    var digitos = new RegExp("^(?=.*[0-9].*[0-9])");
    var min = new RegExp("^(?=.*[a-z].*[a-z].*[a-z].*[a-z])");
    var caracteres = new RegExp("^(?=.{8,})");
    var regExp = [mayu, min, especial, digitos, caracteres];
    var elementos = [$("#mayu"), $("#min"), $("#especial"), $("#digitos"), $("#caracteres")];
    $("#nomUsuario").on("keyup", function () {
        var usuario = $("#nomUsuario").val();
        var check = 0;
        for (var i = 0; i < 5; i++) {
            if (regExp[i].test(usuario)) {
                elementos[i].hide();
                check++;
            } else {
                elementos[i].show();
            }
        }
        if (check >= 0 && check <= 2) {
            $("#error1").text("Incorrecto").css("color", "red");
        } else if (check >= 3 && check <= 4) {
            $("#error1").text("Aceptable").css("color", "orange");
        } else if (check == 5) {
            $("#error1").text("Excelente").css("color", "green");
        }
    });
});