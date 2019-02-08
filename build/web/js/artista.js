$(function () {

//BOTON DE ACTIVAR O DESACTIVAR EL ARTISTA
    $(".btnActivar").click(function () {

        var idUsuario = $(this).attr("idUsuario");
        var activarUsuario = $(this).attr("activarUsuario");

        var datos = new FormData();
        datos.append("activarId", idUsuario);
        datos.append("activarUsuario", activarUsuario);

        if (activarUsuario == 0) {
            $(this).removeClass("badge-success");
            $(this).addClass("badge-danger");
            $(this).html("Inactivo");
            $(this).attr("activarUsuario", 1);
        } else {
            $(this).removeClass("badge-danger");
            $(this).addClass("badge-success");
            $(this).html("Activo");
            $(this).attr("activarUsuario", 0);
        }

    });
}());

//MOSTRAR SOLO ARTISTAS ACTIVOS O TODOS
function cambiarLista() {
    // Get the checkbox
    var checkBox = document.getElementById("modoListar");

    if (checkBox.checked === true) {
        $("table tr").each(function () {
            var fila = $(this).find(":nth-child(6)");
            if (fila.text() === "Inactivo")
                $(this).hide();
        });
    } else {
        $("table tr").each(function () {
            var fila = $(this).find(":nth-child(6)");
            if (fila.text() === "Inactivo")
                $(this).show();
        });
    }
}


