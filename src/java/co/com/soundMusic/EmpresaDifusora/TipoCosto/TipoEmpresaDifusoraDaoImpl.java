package co.com.soundMusic.EmpresaDifusora.TipoCosto;

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
public class TipoEmpresaDifusoraDaoImpl implements ITipoEmpresaDifusoraDao {

//Conexion a la base de datos
    private Connection conexion;
    private Statement stmt;
    private ResultSet rs;
    private Boolean isProduction = true;
    //Constantes con las querys a la base de datos
    private static final String SELECT_TIPOS_EMPRESAS;
    private static final String SELECT_TIPO_EMPRESA_POR_ID;
    private static final String INSERT_TIPO_EMPRESA;
    private static final String UPDATE_TIPO_EMPRESA;
    private static final String SELECT_ULTIMO_ID;

    public TipoEmpresaDifusoraDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<TipoEmpresaDifusora> obtenerTipoEmpresaDifusora() {
        List<TipoEmpresaDifusora> lstTipoEmpresa = new ArrayList<>();
        getConexion();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_TIPOS_EMPRESAS);

            while (rs.next()) {
                int idTipoEmpresaDifusora = rs.getInt("ID_TIPO_ACTIVIDAD");
                String tipoActividad = rs.getString("NOMBRE");

                TipoEmpresaDifusora tipoEmpresa = new TipoEmpresaDifusora(idTipoEmpresaDifusora, tipoActividad);

                lstTipoEmpresa.add(tipoEmpresa);
            }

        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstTipoEmpresa;
    }

    @Override
    public TipoEmpresaDifusora obtenerTipoEmpresaDifusora(int idTipoEmpresaDifusora) {
        getConexion();
        TipoEmpresaDifusora tipoEmpresa = new TipoEmpresaDifusora();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_TIPO_EMPRESA_POR_ID);

            ps.setInt(1, idTipoEmpresaDifusora);

            rs = ps.executeQuery();
            while (rs.next()) {
                tipoEmpresa.setIdTipoActividad(idTipoEmpresaDifusora);
                tipoEmpresa.setTipoActividad(rs.getString("NOMBRE"));
                return tipoEmpresa;
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public int crearTipoEmpresaDifusora(TipoEmpresaDifusora tipoEmpresaDifusora) {
        getConexion();
        int id = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_TIPO_EMPRESA);
            ps.setString(1, tipoEmpresaDifusora.getTipoActividad());
            ps.executeUpdate();
            id = getUltimoIdTipoEmpresa();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void actualizarTipoEmpresaDifusora(TipoEmpresaDifusora tipoEmpresaDifusora) {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_TIPO_EMPRESA);
            ps.setString(1, tipoEmpresaDifusora.getTipoActividad());
            ps.setInt(2, tipoEmpresaDifusora.getIdTipoActividad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private int getUltimoIdTipoEmpresa() {
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ULTIMO_ID);
            while (rs.next()) {
                return rs.getInt("CURRVAL");
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TipoEmpresaDifusoraDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    static {
        SELECT_TIPOS_EMPRESAS = "SELECT ID_TIPO_ACTIVIDAD, NOMBRE \n"
                + "FROM TIPO_ACTIVIDAD \n"
                + "ORDER BY ID_TIPO_ACTIVIDAD";

        SELECT_TIPO_EMPRESA_POR_ID = "SELECT NOMBRE \n"
                + "FROM TIPO_ACTIVIDAD \n"
                + "WHERE ID_TIPO_ACTIVIDAD=?";

        INSERT_TIPO_EMPRESA = "INSERT INTO TIPO_ACTIVIDAD (NOMBRE)\n"
                + "VALUES(?)";

        UPDATE_TIPO_EMPRESA = "UPDATE TIPO_ACTIVIDAD\n"
                + "SET NOMBRE=? \n"
                + "WHERE ID_TIPO_ACTIVIDAD=?";

        SELECT_ULTIMO_ID = "SELECT TIPO_ACTIVIDAD_SEQ.CURRVAL\n"
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
