package co.com.soundMusic.Login.Usuario;

import co.com.soundMusic.Contacto.Contacto;
import co.com.soundMusic.Contacto.ContactoDaoImpl;
import co.com.soundMusic.Login.CuentaUsuario.UsuarioLogin;
import co.com.soundMusic.Login.CuentaUsuario.UsuarioLoginDaoImpl;
import co.com.soundMusic.Seguridad.Perfiles.Perfil;
import co.com.soundMusic.Seguridad.Perfiles.PerfilDaoImpl;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class Usuario {

    private Integer idUsuario;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaCreacion;
    private String status;
    private String genero;
    private Integer idPerfil;
    private Integer idUsuarioLogin;
    private Integer idContacto;
    private Perfil perfil;
    private Contacto contacto;
    private UsuarioLogin usuarioLogin;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date fechaCreacion, String status, String genero, Integer idPerfil, Integer idUsuarioLogin, Integer idContacto) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.genero = genero;
        this.idPerfil = idPerfil;
        this.idUsuarioLogin = idUsuarioLogin;
        this.idContacto = idContacto;
    }

    public Usuario(Integer idUsuario, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, Date fechaCreacion,
            String status, String genero, Integer idPerfil, Integer idUsuarioLogin,
            int idContacto) {
        this.idUsuario = idUsuario;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.genero = genero;
        this.idPerfil = idPerfil;
        this.idUsuarioLogin = idUsuarioLogin;
        this.idContacto = idContacto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Integer getIdUsuarioLogin() {
        return idUsuarioLogin;
    }

    public void setIdUsuarioLogin(Integer idUsuarioLogin) {
        this.idUsuarioLogin = idUsuarioLogin;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setidPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public UsuarioLogin getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(UsuarioLogin usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idUsuario;
        hash = 61 * hash + Objects.hashCode(this.primerNombre);
        hash = 61 * hash + Objects.hashCode(this.primerApellido);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (!Objects.equals(this.primerNombre, other.primerNombre)) {
            return false;
        }
        return Objects.equals(this.primerApellido, other.primerApellido);
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario
                + ", primerNombre=" + primerNombre
                + ", segundoNombre=" + segundoNombre
                + ", primerApellido=" + primerApellido
                + ", segundoApellido=" + segundoApellido
                + ", fechaCreacion=" + fechaCreacion
                + ", status=" + status + '}';
    }

    public Object obtenerUsuarioLogeado(String nom_usuario, String password_us) {
        UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
        List<Usuario> lstUsuario = daoUsuario.obtenerUsuarios();
        for (Usuario usuario : lstUsuario) {
            if (usuario.getUsuarioLogin().getNombreUsuario().equalsIgnoreCase(nom_usuario)
                    & usuario.getUsuarioLogin().getContrasena().equalsIgnoreCase(password_us)) {
                return usuario;
            }
        }
        return null;
    }

    public void obtenerContactoUsuario() {
        ContactoDaoImpl daoContacto = new ContactoDaoImpl(true);
        this.setContacto(daoContacto.obtenerContacto(this.idContacto));
    }

    public void obtenerPerfilUsuario() {
        PerfilDaoImpl daoPerfil = new PerfilDaoImpl(true);
        this.setPerfil(daoPerfil.obtenerPerfil(this.idPerfil));
    }

    public void obtenerUsuarioLogin() {
        UsuarioLoginDaoImpl daoUsuarioLogin = new UsuarioLoginDaoImpl(true);
        this.setUsuarioLogin(daoUsuarioLogin.obtenerUsuarioLogin(this.idUsuarioLogin));
    }

}
