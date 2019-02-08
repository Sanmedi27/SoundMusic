package co.com.soundMusic.Login.CuentaUsuario;

import co.com.soundMusic.utilidades.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class UsuarioLoginDaoImpl implements IUsuarioLoginDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;
    //Constantes con las querys a la base de datos
    private static final String SELECT_USUARIOLOGIN_POR_ID;
    private static final String INSERT_USUARIOLOGIN;
    private static final String UPDATE_USUARIOLOGIN;
    private static final String SELECT_ULTIMO_ID;

    public UsuarioLoginDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public UsuarioLogin obtenerUsuarioLogin(int idUsuarioLogin) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_USUARIOLOGIN_POR_ID);
            ps.setInt(1, idUsuarioLogin);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("NOMBRE_USUARIO");
                String contrasenaUsuario = rs.getString("CONTRASENA");

                UsuarioLogin usuarioLogin = new UsuarioLogin(idUsuarioLogin,
                        nombreUsuario, contrasenaUsuario);
                return usuarioLogin;
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conexion, stmt, rs);
        }
        return null;
    }

    @Override
    public int crearUsuarioLogin(UsuarioLogin usuarioLogin) {
        getConexion();
        int id=-1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_USUARIOLOGIN);
            ps.setString(1, usuarioLogin.getNombreUsuario());
            ps.setString(2, usuarioLogin.getContrasena());
            ps.executeUpdate();
            id=getUltimmoIdUsuarioLogin();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void actualizarUsuarioLogin(UsuarioLogin usuarioLogin) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_USUARIOLOGIN);
            ps.setString(1, usuarioLogin.getNombreUsuario());
            ps.setString(2, usuarioLogin.getContrasena());
            ps.setInt(3, usuarioLogin.getIdUsuarioLogin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getUltimmoIdUsuarioLogin() {        
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ULTIMO_ID);
            while (rs.next()) {
                int idUsuarioLogin = rs.getInt("CURRVAL");
                return idUsuarioLogin;
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);                    
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UsuarioLoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    static {
        SELECT_USUARIOLOGIN_POR_ID = "SELECT NOMBRE_USUARIO,CONTRASENA\n"
                + "FROM USUARIO_LOGIN\n"
                + "WHERE ID_USUARIO_LOGIN=?";

        INSERT_USUARIOLOGIN = "INSERT INTO USUARIO_LOGIN (NOMBRE_USUARIO,CONTRASENA)\n"
                + "VALUES (?,?)";

        UPDATE_USUARIOLOGIN = "UPDATE USUARIO_LOGIN\n"
                + "SET NOMBRE_USUARIO=?,CONTRASENA=?\n"
                + "WHERE ID_USUARIO_LOGIN=?";

        SELECT_ULTIMO_ID = "SELECT USUARIO_LOGIN_SEQ.CURRVAL\n"
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
