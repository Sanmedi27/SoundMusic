package co.com.soundMusic.Seguridad.Permisos;

import co.com.soundMusic.utilidades.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class PermisosDaoImpl implements IPermisosDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;

    //Constantes con las querys a la base de datos
    private static final String SELECT_PERMISOS;
    private static final String SELECT_PERMISO_POR_ID;
    private static final String INSERT_PERMISO;
    private static final String UPDATE_PERMISO;
    private static final String DELETE_PERMISO;
    private static final String SELECT_ULTIMO_ID;

    public PermisosDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<Permisos> obtenerPermisos() {
        getConexion();
        List<Permisos> listaPermisos = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_PERMISOS);

            while (rs.next()) {
                Permisos permisos = new Permisos();
                permisos.setIdPermiso(rs.getInt("ID_PERMISO"));
                permisos.setNombrePermiso(validacion(rs.getString("NOMBRE_PERMISO")));

                listaPermisos.add(permisos);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaPermisos;
    }

    @Override
    public Permisos obtenerPermiso(int idPermisos) {
        getConexion();
        Permisos permiso = new Permisos();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_PERMISO_POR_ID);
            ps.setInt(1, idPermisos);
            rs = ps.executeQuery();
            while (rs.next()) {
                permiso.setIdPermiso(idPermisos);
                permiso.setNombrePermiso(validacion(rs.getString("NOMBRE_PERMISO")));

                return permiso;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permiso;
    }

    @Override
    public int crearPermiso(Permisos permisos) {
        getConexion();
        int id = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_PERMISO);

            ps.setString(1, permisos.getNombrePermiso());
            ps.executeUpdate();
            id = getUltimoIdPermiso();
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void actualizarPermiso(Permisos permisos) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_PERMISO);

            ps.setString(1, permisos.getNombrePermiso());
            ps.setInt(2, permisos.getIdPermiso());
            ps.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void eliminarPermiso(int idPermiso) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE_PERMISO);
            ps.setInt(1, idPermiso);
            ps.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getUltimoIdPermiso() {
        int idPermiso = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ULTIMO_ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                idPermiso = rs.getInt("CURRVAL");
                return idPermiso;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(rs);
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(PermisosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idPermiso;
    }

    static {
        SELECT_PERMISOS = "SELECT NOMBRE_PERMISO\n"
                + "FROM PERMISO ORDER BY ID_PERMISO";

        SELECT_PERMISO_POR_ID = "SELECT NOMBRE_PERMISO\n"
                + "FROM PERMISO \n"
                + "WHERE ID_PERMISO=?";

        INSERT_PERMISO = "INSERT INTO PERMISO (NOMBRE_PERMISO)\n"
                + "VALUES (?)";

        UPDATE_PERMISO = "UPDATE PERMISO \n"
                + "SET NOMBRE_PERMISO=? \n"
                + "WHERE ID_PERMISO=?";

        DELETE_PERMISO = "DELETE \n"
                + "FROM PERMISO \n"
                + "WHERE ID_PERMISO=?";

        SELECT_ULTIMO_ID = "SELECT PERMISO_SEQ.CURRVAL\n"
                + "FROM DUAL";
    }

    private String validacion(String aValidar) {
        if (aValidar != null) {
            return aValidar.trim();
        } else {
            return "";
        }
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
