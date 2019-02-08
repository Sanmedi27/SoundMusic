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
    private Usuario usuario;
    private Permisos operaciones;

    public LogAuditoria() {
    }

    public LogAuditoria(int idLogAuditoria, Timestamp fecha, Usuario usuario, Permisos operaciones) {
        this.idLogAuditoria = idLogAuditoria;
        this.fecha = fecha;
        this.usuario = usuario;
        this.operaciones = operaciones;
    }

    public LogAuditoria(int idLogAuditoria, Usuario usuario, Permisos operaciones) {
        this.idLogAuditoria = idLogAuditoria;
        this.usuario = usuario;
        this.operaciones = operaciones;
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

    public void obtenerPermiso() {
        PermisosDaoImpl daoPermisos = new PermisosDaoImpl();
        try {
            this.setOperaciones(daoPermisos.obtenerPermiso(this.operaciones.getIdPermiso()));
        } catch (SQLException ex) {
            Logger.getLogger(LogAuditoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerUsuario() {
        UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl(true);
        this.setUsuario(daoUsuario.obtenerUsuario(this.usuario.getIdUsuario()));
    }
}
