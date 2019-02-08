$(function () {
    var mayus = new RegExp("^(?=.*[A-Z])");
//    var special = new RegExp("^(?=.*[!@#$%&*].*[!@#$%&*].*[!@#$%&*])");
    var numbers = new RegExp("^(?=.*[0-9].*[0-9].*[0-9])");
    var lower = new RegExp("^(?=.*[a-z].*[a-z].*[a-z].*[a-z].*[a-z].*[a-z].*[a-z].*[a-z].*[a-z].*[a-z].*[a-z])");
    var len = new RegExp("^(?=.{15,})");
    var regExp = [mayus, numbers, lower, len];
    var elementos = [$("#mayus"), $("#numbers"), $("#lower"), $("#len")];
    $("#pass1").on("keyup", function () {
        var pass = $("#pass1").val();
        var check = 0;
        for (var i = 0; i < 4; i++) {
            if (regExp[i].test(pass)) {
                elementos[i].hide();
                check++;
            } else {
                elementos[i].show();
            }
        }
        if (check >= 0 && check <= 1) {
            $("#error2").text("Muy insegura").css("color", "red");
        } else if (check >= 2 && check <= 3) {
            $("#error2").text("Poco segura").css("color", "orange");
        } else if (check === 4) {
            $("#error2").text("Muy segura").css("color", "green");
        }
    });
    $(document).ready(function () {
        $("#img-ojo-1").mousedown(function () {
            $("#password_us").removeAttr("type");
        });
        $("#img-ojo-1").mouseup(function () {
            $("#password_us").attr("type", "password");
        });
    });
    $("#pass2").on("keyup", function () {
        var check = 0;
        var pass = $("#pass2").val();
        for (var i = 0; i < 4; i++) {
            if (regExp[i].test(pass)) {
                check++;
            }
        }
        if (check >= 0 && check <= 1) {
            $("#error2").text("Muy insegura").css("color", "red");
        } else if (check >= 2 && check <= 3) {
            $("#error2").text("Poco segura").css("color", "orange");
        } else if (check === 4) {
            $("#error2").text("Muy segura").css("color", "green");
        }
    });
    $(document).ready(function () {
        $("#img-ojo-2").mousedown(function () {
            $("#pass2").removeAttr("type");
        });
        $("#img-ojo-2").mouseup(function () {
            $("#pass2").attr("type", "password");
        });
    });
    $(document).ready(function () {
        $("#pass2").keyup(function () {
            var password = $("#pass1").val();
            var repitaPassword = $("#pass2").val();
            if (password === repitaPassword) {
                $("#msg-error").text("Coincide").css("color", "green");
            } else {
                $("#msg-error").text("No coincide").css("color", "red");
            }
            if (repitaPassword === "") {
                $("#msg-error").text("No se puede dejar en blanco").css("color", "red");
            }
        });
    });
});