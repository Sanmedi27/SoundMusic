<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Music Sound</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse nav" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link " href="home.jsp" id="home">Home</a>
            </li>
            <li class="nav-item">
                <!--<a class="nav-link" href="usuario.jsp" id="usuarios">Usuarios</a>-->
                <a class="nav-link" href="controladorUsuario?opcion=listarUsuarios" id="usuarios">Usuarios</a>
            </li>
            <li class="nav-item">
                <!--<a class="nav-link" href="empresa.jsp" id="empresas">Empresas Difusoras</a>-->
                <a class="nav-link" href="controladorEmpresaDifusora?opcion=listarEmpresa" id="empresas">Empresas Difusoras</a>
            </li>
            <li class="nav-item ">
                <!--<a class="nav-link" href="artista.jsp" id="artista">Artistas</a>-->
                <a class="nav-link" href="controladorArtista?opcion=listarArtistas" id="artista">Artistas</a>
            </li>
            <li class="nav-item ">
                <!--<a class="nav-link" href="regalias.jsp" id="regalias">Regalias</a>-->
                <a class="nav-link" href="ControladorRegalias?opcion=listarRegalias" id="regalias">Regalias</a>
            </li>
            <li class="nav-item ">
                <!--<a class="nav-link" href="auditoria.jsp" id="auditoria">Log de Auditoria</a>-->
                <a class="nav-link" href="controladorLogAuditoria?opcion=listarLogAuditoria" id="auditoria">Log de Auditoria</a>
            </li>            
        </ul>
        <div class="row">
            <div class="col display-flex">
                <!-- <a href ="login.jsp" class="btn btn-danger" type="button" click="cerrarSesion()" id="salir">Salir</a>>-->
                <form action="controladorLogin" method="get">
                    <input type ="hidden" name="opcion" value="cerrarSesion" />
                    <input type="submit" value="Salir" class="btn btn-danger" click="cerrarSesion()" id="salir"/>
                </form>
            </div>
        </div>
    </div>
</nav>