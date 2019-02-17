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
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;
    //Constantes con las querys a la base de datos
    private static final String SELECT_LOG_AUDITORIA;
    private static final String INSERT_LOG_AUDITORIA;
    private static final String SELECT_LOG_AUDITORIA_POR_USUARIO;
    private static final String SELECT_ULTIMO_ID;

    public LogAuditoriaDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<LogAuditoria> obtenerLogAuditoria() {
        getConexion();
        List<LogAuditoria> listaLogAuditoria = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_LOG_AUDITORIA);

            while (rs.next()) {
                LogAuditoria logAuditoria = new LogAuditoria();

                logAuditoria.setIdLogAuditoria(rs.getInt("ID_LOG_AUDITORIA"));
                logAuditoria.setFecha(rs.getTimestamp("FECHA"));
                logAuditoria.setIdUsuario(rs.getInt("ID_USUARIO"));
                logAuditoria.setIdOperaciones(rs.getInt("ID_OPERACION"));

                logAuditoria.obtenerPermiso();
                logAuditoria.obtenerUsuario();

                listaLogAuditoria.add(logAuditoria);
            }

        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaLogAuditoria;
    }

    @Override
    public void crearLog(LogAuditoria logAuditoria) {
        getConexion();
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
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    @Override
    public List<LogAuditoria> obtenerLogAuditoriaPorUsuario(int idUsuario) {
        getConexion();
        List<LogAuditoria> listaLogAuditoria = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_LOG_AUDITORIA_POR_USUARIO);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                LogAuditoria logAuditoria = new LogAuditoria();

                logAuditoria.setIdLogAuditoria(rs.getInt("ID_LOG_AUDITORIA"));
                logAuditoria.setFecha(rs.getTimestamp("FECHA"));
                logAuditoria.setIdUsuario(idUsuario);
                logAuditoria.setIdOperaciones(rs.getInt("ID_OPERACION"));

                logAuditoria.obtenerPermiso();
                logAuditoria.obtenerUsuario();

                listaLogAuditoria.add(logAuditoria);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaLogAuditoria;
    }

    public int getUltimoIdLog() {
        int idLog = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ULTIMO_ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                idLog = rs.getInt("CURRVAL");
                return idLog;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(rs);
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(LogAuditoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idLog;
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

        SELECT_ULTIMO_ID = "SELECT LOG_AUDITORIA_SEQ.CURRVAL\n"
                + "FROM DUAL";
    }

    private void getConexion() {
        if (isProduction) {
            conexion = DBUtil.getConexion();
        } else {
            conexion = DBUtil.getTestConexion();
        }
        stmt = null;
        rs = null;
    }
}
