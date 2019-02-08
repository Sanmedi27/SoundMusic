package co.com.soundMusic.EmpresaDifusora.TipoCosto;

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
public class CostoActividadDaoImpl implements ICostoActividadDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;
    //Constantes con las querys a la base de datos
    private static final String SELECT_COSTO_ACTIVIDAD;
    private static final String INSERT_COSTO_ACTIVIDAD;
    private static final String UPDATE_COSTO_ACTIVIDAD;
    private static final String UPDATE_FECHA_FINAL;
    private static final String SELECT_ULTIMO_ID;
    private static final String SELECT_COSTO_ACTIVIDAD_EMPRESA_ID;

    public CostoActividadDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public CostoActividad obtenerCostoActividad(int idCostoActividad) {
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_COSTO_ACTIVIDAD);
            ps.setInt(1, idCostoActividad);
            rs = ps.executeQuery();

            while (rs.next()) {
                CostoActividad costoActividad = new CostoActividad(idCostoActividad,
                        rs.getFloat("COSTO_POR_OPERACION"), rs.getDate("FECHA_CREACION"),
                        rs.getDate("FECHA_USO_FINAL"));

                return costoActividad;
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public int crearCostoActividad(CostoActividad costoActividad) {
        getConexion();
        int id = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_COSTO_ACTIVIDAD);

            ps.setFloat(1, costoActividad.getCostoPorOperacion());
            ps.setDate(2, costoActividad.getFechaCreacion());
            ps.executeUpdate();
            id = getUltimoIdCostoActividad();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void actualizarCostoActividad(CostoActividad costoActividad) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_COSTO_ACTIVIDAD);
            ps.setFloat(1, costoActividad.getCostoPorOperacion());
            ps.setInt(2, costoActividad.getIdCostoActividad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void eliminarCostoActividad(CostoActividad costoActividad) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_FECHA_FINAL);
            ps.setDate(1, costoActividad.getFechaUsoFinal());
            ps.setInt(2, costoActividad.getIdCostoActividad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int getUltimoIdCostoActividad() {
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ULTIMO_ID);
            while (rs.next()) {
                return rs.getInt("CURRVAL");
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public CostoActividad getCostoPorIdEmpresa(int IdEmpresa) {
        CostoActividad costo= new CostoActividad();
        try {
            getConexion();
            PreparedStatement ps = conexion.prepareStatement(SELECT_COSTO_ACTIVIDAD_EMPRESA_ID);
            ps.setInt(1, IdEmpresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                costo.setIdCostoActividad(rs.getInt("ID_COSTO_ACTIVIDAD"));
                costo.setCostoPorOperacion(rs.getFloat("COSTO_POR_OPERACION"));
                costo.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                costo.setFechaUsoFinal(rs.getDate("FECHA_USO_FINAL"));
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CostoActividadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return costo;
    }
    
    static {
        SELECT_COSTO_ACTIVIDAD = "SELECT COSTO_POR_OPERACION, FECHA_CREACION, FECHA_USO_FINAL"
                + "FROM COSTO_ACTIVIDAD"
                + "WHERE ID_COSTO_ACTIVIDAD=?";

        INSERT_COSTO_ACTIVIDAD = "INSERT INTO COSTO_ACTIVITIDAD (COSTO_POR_OPERACION,FECHA_CREACION)\n"
                + "VALUES (?,?)";

        UPDATE_COSTO_ACTIVIDAD = "UPDATE COSTO_ACTIVITIDAD \n"
                + "SET COSTO_POR_OPERACION=? \n"
                + "WHERE ID_COSTO_ACTIVIDAD=?";

        UPDATE_FECHA_FINAL = "UPDATE COSTO_ACTIVIDAD \n"
                + "SET FECHA_USO_FINAL=? \n"
                + "WHERE ID_COSTO_ACTIVIDAD=?";

        SELECT_ULTIMO_ID = "SELECT COSTO_ACTIVITIDAD_SEQ.CURRVAL\n"
                + "FROM DUAL";
        SELECT_COSTO_ACTIVIDAD_EMPRESA_ID = "SELECT ID_COSTO_ACTIVIDAD, COSTO_POR_OPERACION, FECHA_CREACION,FECHA_USO_FINAL \n"
                + "FROM COSTO_ACTIVITIDAD\n"
                + "WHERE ID_COSTO_ACTIVIDAD IN (\n"
                + "SELECT EMPRESA_DIFUSORA.ID_COSTO_ACTIVIDAD \n"
                + "FROM EMPRESA_DIFUSORA \n"
                + "WHERE EMPRESA_DIFUSORA.ID_EMPRESA_DIFUSORA=?)";
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
