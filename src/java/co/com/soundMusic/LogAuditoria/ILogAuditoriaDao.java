package co.com.soundMusic.LogAuditoria;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface ILogAuditoriaDao {

    List<LogAuditoria> obtenerLogAuditoria();

    List<LogAuditoria> obtenerLogAuditoriaPorUsuario(int idUsuario);

    void crearLog(LogAuditoria logAuditoria);
}
