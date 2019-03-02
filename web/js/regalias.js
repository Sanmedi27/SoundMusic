$(function () {

    $(document).ready(function () {
        $("#btnGuardar").click(function () {
            var nomArtista = $("#nomArtista option:selected");
            var nomEmpresa = $("#nomEmpresa option:selected");
            var numOp = $("#numOp").val();

            if (nomArtista.val() == "") {
                $("#nomArtista").addClass("is-invalid");
                alerta();
                return false;
            } else {
                $("#nomArtista").removeClass("is-invalid");
            }
            if (nomEmpresa.val() == "") {
                $("#nomEmpresa").addClass("is-invalid");
                alerta();
                return false;
            } else {
                $("#nomEmpresa").removeClass("is-invalid");
            }
            if (numOp == "") {
                $("#numOp").addClass("is-invalid");
                alerta();
                return false;
            } else {
                $("#numOp").removeClass("is-invalid");
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



    $(".btnActivar").click(function () {

        var idRegalias = $(this).attr("idRegalias");
        var activarRegalias = $(this).attr("activarRegalias");


        var datos = new FormData();
        datos.append("activarId", idRegalias);
        datos.append("activarRegalias", activarRegalias);

        if (activarRegalias == 0) {

//            swal({
//                title: '¿Estas Seguro de Pagar?',
//                text: "Si acepta la condicion puede pagar al artista de este mes.",
//                type: 'warning',
//                showCancelButton: true,
//                confirmButtonColor: '#3085d6',
//                cancelButtonColor: '#d33',
//                confirmButtonText: 'Si, Pagar Ya!'
//            }).then((result) => {
//                if (result.value) {
//                    swal(
//                            'Pago Exitosamente!',
//                            'Has pagado la regalia del artista de este mes.',
//                            'success'
//                            );
//
//                    $(this).removeClass("badge-success");
//                    $(this).addClass("badge-danger");
//                    $(this).html("Pendiente");
//                    $(this).attr("activarRegalias", 1);
//                }
//            });


        } else {

//            swal({
//                title: '¿Estas Seguro de Cancelar el Pago?',
//                text: "Si acepta la condicion, el artista no va llegar el pago del salario de este mes",
//                type: 'warning',
//                showCancelButton: true,
//                confirmButtonColor: '#3085d6',
//                cancelButtonColor: '#d33',
//                confirmButtonText: 'Si, cancelar el pago!'
//            }).then((result) => {
//                if (result.value) {
//                    swal(
//                            'Pago Cancelado!',
//                            '',
//                            'success'
//                            );
//
//                    $(this).removeClass("badge-danger");
//                    $(this).addClass("badge-success");
//                    $(this).html("Pago Exitosamente");
//                    $(this).attr("activarRegalias", 0);
//
//                }
//            });
        }

    });


    //CALCULAR LA OPERACION LA REGALIA DEL ARTISTA Y APARECE LOS LOGROS
    $("input#numOp").on("keyup", function () {
        var valor = $(this).val();
        var total = 0;
        var empresa = $("#nomEmpresa").val();
//        $("input#totalCanciones").val(valor);
        if (empresa == "Spotify") {
            total = valor * 5;
            $("input#totalOp").val("$" + total + " USD");
            if (total >= 100.00) {
                console.log("Disco de Oro");
                notificacionDiscoOro();
            } else
            if (total >= 50.00) {
                console.log("Disco de Platino");
                notificacionDiscoPlata();
            }

        } else
        if (empresa == "Napster") {
            total = valor * 8;
            $("input#totalOp").val("$" + total + " USD");
            if (total >= 100.00) {
                console.log("Disco de Oro");
                notificacionDiscoOro();
            } else
            if (total >= 50.00) {
                console.log("Disco de Platino");
                notificacionDiscoPlata();
            }
        } else
        if (empresa == "!Music") {
            total = valor * 10;
            $("input#totalOp").val("$" + total + " USD");
            if (total >= 100.00) {
                console.log("Disco de Oro");
                notificacionDiscoOro();
            } else
            if (total >= 50.00) {
                console.log("Disco de Platino");
                notificacionDiscoPlata();
            }
        }
    });

    function notificacionDiscoOro() {
        setTimeout(function () {
            $.bootstrapGrowl("Felicidades el artista obtubo un disco de Oro!", {
                ele: 'body', // which element to append to
                type: 'info', // (null, 'info', 'danger', 'success')
                offset: {from: 'bottom', amount: 20}, // 'top', or 'bottom'
                align: 'center', // ('left', 'right', or 'center')
                width: 500, // (integer, or 'auto')
                delay: 4000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
                allow_dismiss: true, // If true then will display a cross to close the popup.
                stackup_spacing: 10 // spacing between consecutively stacked growls.
            });
        }, 2000);
    }

    function notificacionDiscoPlata() {
        setTimeout(function () {
            $.bootstrapGrowl("Felicidades el artista obtubo un disco de platino!", {
                ele: 'body', // which element to append to
                type: 'info', // (null, 'info', 'danger', 'success')
                offset: {from: 'bottom', amount: 20}, // 'top', or 'bottom'
                align: 'center', // ('left', 'right', or 'center')
                width: 500, // (integer, or 'auto')
                delay: 4000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
                allow_dismiss: true, // If true then will display a cross to close the popup.
                stackup_spacing: 10 // spacing between consecutively stacked growls.
            });
        }, 2000);
    }

}());



