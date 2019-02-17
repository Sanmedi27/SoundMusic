package co.com.soundMusic.LogAuditoria;

import co.com.soundMusic.Seguridad.Permisos.Permisos;
import co.com.soundMusic.Login.Usuario.Usuario;
import co.com.soundMusic.Login.Usuario.UsuarioDaoImpl;
import co.com.soundMusic.Seguridad.Permisos.PermisosDaoImpl;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class LogAuditoria {

    private int idLogAuditoria;
    private Timestamp fecha;
    private int idUsuario;
    private int idOperaciones;
    private Usuario usuario;
    private Permisos operaciones;

    public LogAuditoria() {
        this.usuario = new Usuario();
        this.operaciones = new Permisos();
    }

    public LogAuditoria(int idLogAuditoria, Timestamp fecha, int idUsuario, int idOperaciones) {
        this.idLogAuditoria = idLogAuditoria;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idOperaciones = idOperaciones;
    }

    public LogAuditoria(int idLogAuditoria, int idUsuario, int idOperaciones) {
        this.idLogAuditoria = idLogAuditoria;
        this.idUsuario = idUsuario;
        this.idOperaciones = idOperaciones;
    }

    public int getIdLogAuditoria() {
        return idLogAuditoria;
    }

    public void setIdLogAuditoria(int idLogAuditoria) {
        this.idLogAuditoria = idLogAuditoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Permisos getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(Permisos operaciones) {
        this.operaciones = operaciones;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdOperaciones() {
        return idOperaciones;
    }

    public void setIdOperaciones(int idOperaciones) {
        this.idOperaciones = idOperaciones;
    }

    public void obtenerPermiso() {
        PermisosDaoImpl daoPermisos = new PermisosDaoImpl(true);
        this.setOperaciones(daoPermisos.obtenerPermiso(this.getIdOperaciones()));
    }

    public void obtenerUsuario() {
        UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
        this.setUsuario(daoUsuario.obtenerUsuario(this.getIdUsuario()));
    }

}
