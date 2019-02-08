$(function () {
//VALIDAR FORMULARIOS
    $(document).ready(function () {
        $("#btnGuardar").click(function () {
            var nom1 = $("#nom1").val();
            var apellido1 = $("#apellido1").val();
            var apellido2 = $("#apellido2").val();
            var textSex1 = $("#textSex1:checked");
            var textSex2 = $("#textSex2:checked");
            var pais = $("#pais option:selected");
            var nomArtista = $("#nomArtista").val();

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

            if (pais.val() == "") {
                $("#pais").addClass("is-invalid");
                alerta();
                return false;
            } else {
                $("#pais").removeClass("is-invalid");
            }

            if (nomArtista == "") {
                $("#nomArtista").addClass("is-invalid");
                alerta();
                return false;
            } else {
                $("#nomArtista").removeClass("is-invalid");
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


    //SUBIENDO FOTOS DEL ARTISTA Y EL ALBUN

    $(".nuevaFoto").change(function () {

        var imagen = this.files[0];

        /*=============================================
         VALIDAMOS EL FORMATO DE LA IMAGEN SEA JPG O PNG
         =============================================*/
        if (imagen["type"] != "image/jpeg" && imagen["type"] != "image/png") {

            $(".nuevaFoto").val("");

            swal({
                title: "Error al subir la imagen",
                text: "¡La imagen debe estar en formato JPG o PNG!",
                type: "error",
                confirmButtonText: "¡Cerrar!"
            });

        } else if (imagen["size"] > 2000000) {

            $(".nuevaFoto").val("");

            swal({
                title: "Error al subir la imagen",
                text: "¡La imagen no debe pesar más de 2MB!",
                type: "error",
                confirmButtonText: "¡Cerrar!"
            });

        } else {

            var datosImagen = new FileReader;
            datosImagen.readAsDataURL(imagen);

            $(datosImagen).on("load", function (event) {

                var rutaImagen = event.target.result;

                $(".previsualizar").attr("src", rutaImagen);

            });

        }
    });


}());


