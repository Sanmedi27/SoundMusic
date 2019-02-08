package co.com.soundMusic.LogAuditoria;

import co.com.soundMusic.Login.Usuario.Usuario;
import co.com.soundMusic.Seguridad.Permisos.Permisos;
import co.com.soundMusic.utilidades.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class LogAuditoriaDaoImpl implements ILogAuditoriaDao {

    //Conexion a la base de datos
    private final Connection conexion;

    private Statement stmt;
    private ResultSet rs;
    //Constantes con las querys a la base de datos
    private static final String SELECT_LOG_AUDITORIA;
    private static final String INSERT_LOG_AUDITORIA;
    private static final String SELECT_LOG_AUDITORIA_POR_USUARIO;

    public LogAuditoriaDaoImpl(Boolean production) {
        if (production) {
            conexion = DBUtil.getConexion();
        } else {
            conexion = DBUtil.getTestConexion();
        }
        stmt = null;
        rs = null;
    }

    @Override
    public List<LogAuditoria> obtenerLogAuditoria() {
        List<LogAuditoria> listaLogAuditoria = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_LOG_AUDITORIA);

            while (rs.next()) {
                int idLogAuditoria = rs.getInt("ID_LOG_AUDITORIA");
                Timestamp fecha = rs.getTimestamp("FECHA");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idOperaciones = rs.getInt("ID_OPERACION");

                LogAuditoria logAuditoria = new LogAuditoria(idLogAuditoria, fecha,
                        new Usuario(idUsuario), new Permisos(idOperaciones));

                logAuditoria.obtenerPermiso();
                logAuditoria.obtenerUsuario();

                listaLogAuditoria.add(logAuditoria);
            }

        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexion != null) {
                DbUtils.closeQuietly(conexion, stmt, rs);
            }
        }
        return listaLogAuditoria;
    }

    @Override
    public void crearLog(LogAuditoria logAuditoria) {
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_LOG_AUDITORIA);

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2, logAuditoria.getUsuario().getIdUsuario());
            ps.setInt(3, logAuditoria.getOperaciones().getIdPermiso());
            ps.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexion != null) {
                DbUtils.closeQuietly(conexion, stmt, rs);
            }
        }
    }

    @Override
    public List<LogAuditoria> obtenerLogAuditoriaPorUsuario(int idUsuario) {
        List<LogAuditoria> listaLogAuditoria = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_LOG_AUDITORIA_POR_USUARIO);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idLogAuditoria = rs.getInt("ID_LOG_AUDITORIA");
                Timestamp fecha = rs.getTimestamp("FECHA");
                int idOperaciones = rs.getInt("ID_OPERACION");

                LogAuditoria logAuditoria = new LogAuditoria(idLogAuditoria, fecha,
                        new Usuario(idUsuario), new Permisos(idOperaciones));

                logAuditoria.obtenerPermiso();
                logAuditoria.obtenerUsuario();

                listaLogAuditoria.add(logAuditoria);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexion != null) {
                DbUtils.closeQuietly(conexion, stmt, rs);
            }
        }
        return listaLogAuditoria;
    }

    static {
        SELECT_LOG_AUDITORIA = "SELECT ID_LOG_AUDITORIA,FECHA,ID_USUARIO,ID_OPERACION\n"
                + "FROM LOG_AUDITORIA \n"
                + "ORDER BY ID_LOG_AUDITORIA";

        INSERT_LOG_AUDITORIA = "INSERT INTO LOG_AUDITORIA (FECHA,ID_USUARIO,ID_OPERACION)\n"
                + "VALUES (?,?,?)";

        SELECT_LOG_AUDITORIA_POR_USUARIO = "SELECT ID_LOG_AUDITORIA,FECHA,ID_USUARIO,ID_OPERACION\n"
                + "FROM LOG_AUDITORIA \n"
                + "WHERE ID_USUARIO=? \n"
                + "ORDER BY ID_LOG_AUDITORIA";
    }

}
