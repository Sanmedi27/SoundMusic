package co.com.soundMusic.Negocio.Regalias;

import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividad;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresa;
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
public class RegaliaDaoImpl implements IRegaliaDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;
    //Constantes con las querys a la base de datos
    private static final String SELECT_REGALIAS;
    private static final String SELECT_REGALIAS_SIMPLE;
    private static final String SELECT_REGALIA_POR_ID;
    private static final String INSERT_REGALIA;
    private static final String UPDATE_REGALIA;
    private static final String SELECT_ULTIMO_ID;

    public RegaliaDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<Regalia> obtenerRegalias() {
        getConexion();
        List<Regalia> listaRegalias = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_REGALIAS);

            while (rs.next()) {
                ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
                CostoActividad costo = new CostoActividad();
                Regalia regalia = new Regalia();

                //Datos CostoActividad
                costo.setIdCostoActividad(rs.getInt("ID_COSTO"));
                costo.setCostoPorOperacion(rs.getFloat("COSTO_POR_OPERACION"));
                costo.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                costo.setFechaUsoFinal(rs.getDate("FECHA_USO_FINAL"));
                //Datos ArtistaEmpresa
                artistaEmpresa.setIdArtistaEmpresa(rs.getInt("ID_ARTISTA_EMPRESA"));
                artistaEmpresa.getArtista().setIdArtista(rs.getInt("ID_ARTISTA"));
                artistaEmpresa.getArtista().setNombreArtistico(rs.getString("NOMBRE_ARTISTICO"));
                artistaEmpresa.getEmpresaDifusora().setIdEmpresaDifusora(rs.getInt("ID_EMPRESA_DIFUSORA"));
                artistaEmpresa.getEmpresaDifusora().setNombre(rs.getString("NOMBRE"));
                //Datos Regalia
                regalia.setIdRegalia(rs.getInt("ID_REGALIA"));
                regalia.setTotalGanado(rs.getFloat("TOTAL_GANADO"));
                regalia.setNumeroOperaciones(rs.getInt("NUMERO_OPERACIONES"));
                regalia.setFecha(rs.getTimestamp("FECHA"));
                regalia.setArtistaEmpresa(artistaEmpresa);
                regalia.setCosto(costo);

                listaRegalias.add(regalia);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaRegalias;
    }

    public List<Regalia> obtenerRegaliasSimple() {
        getConexion();
        List<Regalia> listaRegalias = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_REGALIAS_SIMPLE);

            while (rs.next()) {
                ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
                CostoActividad costo = new CostoActividad();
                Regalia regalia = new Regalia();

                //Datos CostoActividad
                costo.setIdCostoActividad(rs.getInt("ID_COSTO"));
                //Datos ArtistaEmpresa
                artistaEmpresa.setIdArtistaEmpresa(rs.getInt("ID_ARTISTA_EMPRESA"));
                artistaEmpresa.getArtista().setIdArtista(rs.getInt("ID_ARTISTA"));
                artistaEmpresa.getEmpresaDifusora().setIdEmpresaDifusora(rs.getInt("ID_EMPRESA_DIFUSORA"));
                //Datos Regalia
                regalia.setIdRegalia(rs.getInt("ID_REGALIA"));
                regalia.setTotalGanado(rs.getFloat("TOTAL_GANADO"));
                regalia.setNumeroOperaciones(rs.getInt("NUMERO_OPERACIONES"));
                regalia.setFecha(rs.getTimestamp("FECHA"));
                regalia.setArtistaEmpresa(artistaEmpresa);
                regalia.setCosto(costo);

                listaRegalias.add(regalia);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaRegalias;
    }

    @Override
    public Regalia obtenerRegalia(int idRegalia) {
        getConexion();
        Regalia regalia = new Regalia();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_REGALIA_POR_ID);
            ps.setInt(1, idRegalia);
            rs = ps.executeQuery();

            while (rs.next()) {
                ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
                CostoActividad costo = new CostoActividad();;

                //Datos CostoActividad
                costo.setIdCostoActividad(rs.getInt("ID_COSTO"));
                costo.setCostoPorOperacion(rs.getFloat("COSTO_POR_OPERACION"));
                costo.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                costo.setFechaUsoFinal(rs.getDate("FECHA_USO_FINAL"));
                //Datos ArtistaEmpresa
                artistaEmpresa.setIdArtistaEmpresa(rs.getInt("ID_ARTISTA_EMPRESA"));
                artistaEmpresa.getArtista().setIdArtista(rs.getInt("ID_ARTISTA"));
                artistaEmpresa.getEmpresaDifusora().setIdEmpresaDifusora(rs.getInt("ID_EMPRESA_DIFUSORA"));
                //Datos Regalia
                regalia.setIdRegalia(rs.getInt("ID_REGALIA"));
                regalia.setTotalGanado(rs.getFloat("TOTAL_GANADO"));
                regalia.setNumeroOperaciones(rs.getInt("NUMERO_OPERACIONES"));
                regalia.setFecha(rs.getTimestamp("FECHA"));
                regalia.setArtistaEmpresa(artistaEmpresa);
                regalia.setCosto(costo);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return regalia;
    }

    @Override
    public int crearRegalia(Regalia regalia) {
        getConexion();
        int id = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_REGALIA);

            ps.setFloat(1, regalia.getTotalGanado());
            ps.setInt(2, regalia.getNumeroOperaciones());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, regalia.getArtistaEmpresa().getIdArtistaEmpresa());
            ps.setInt(5, regalia.getCosto().getIdCostoActividad());
            ps.executeUpdate();
            id = getUltimoIdRegalia();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void actualizarRegalia(Regalia regalia) throws SQLException {
        getConexion();
        PreparedStatement ps = conexion.prepareStatement(UPDATE_REGALIA);
        ps.setFloat(1, regalia.getTotalGanado());
        ps.setTimestamp(2, regalia.getFecha());
        ps.setInt(3, regalia.getArtistaEmpresa().getIdArtistaEmpresa());
        ps.setInt(4, regalia.getCosto().getIdCostoActividad());
        ps.setInt(5, regalia.getIdRegalia());
        ps.executeUpdate();
    }

    static {
        SELECT_REGALIAS = "SELECT REG.ID_REGALIA, REG.TOTAL_GANADO,REG.NUMERO_OPERACIONES ,REG.FECHA,\n"
                + "REG.ID_ARTISTA_EMPRESA, REG.ID_COSTO, ARTEMP.ID_ARTISTA, ARTEMP.ID_EMPRESA_DIFUSORA,\n"
                + "AR.NOMBRE_ARTISTICO, EMDI.NOMBRE,\n"
                + "COSTA.COSTO_POR_OPERACION, COSTA.FECHA_CREACION, COSTA.FECHA_USO_FINAL \n"
                + "FROM REGALIA REG INNER JOIN ARTISTA_EMPRESA ARTEMP \n"
                + "ON REG.ID_ARTISTA_EMPRESA = ARTEMP.ID_ARTISTA_EMPRESA \n"
                + "INNER JOIN ARTISTA AR\n"
                + "ON ARTEMP.ID_ARTISTA = AR.ID_ARTISTA\n"
                + "INNER JOIN EMPRESA_DIFUSORA EMDI\n"
                + "ON ARTEMP.ID_EMPRESA_DIFUSORA=EMDI.ID_EMPRESA_DIFUSORA\n"
                + "INNER JOIN COSTO_ACTIVITIDAD COSTA\n"
                + "ON REG.ID_COSTO = COSTA.ID_COSTO_ACTIVIDAD\n"
                + "ORDER BY ID_REGALIA";

        SELECT_REGALIAS_SIMPLE = "SELECT REG.ID_REGALIA, REG.TOTAL_GANADO,REG.NUMERO_OPERACIONES ,REG.FECHA,\n"
                + "REG.ID_ARTISTA_EMPRESA, REG.ID_COSTO, ARTEMP.ID_ARTISTA, ARTEMP.ID_EMPRESA_DIFUSORA \n"
                + "FROM REGALIA REG INNER JOIN ARTISTA_EMPRESA ARTEMP \n"
                + "ON REG.ID_ARTISTA_EMPRESA = ARTEMP.ID_ARTISTA_EMPRESA \n"
                + "ORDER BY ID_REGALIA";

        SELECT_REGALIA_POR_ID = "SELECT REG.ID_REGALIA, REG.TOTAL_GANADO,REG.NUMERO_OPERACIONES ,REG.FECHA,\n"
                + "REG.ID_ARTISTA_EMPRESA, REG.ID_COSTO, \n"
                + "ARTEMP.ID_ARTISTA, ARTEMP.ID_EMPRESA_DIFUSORA,\n"
                + "COSTA.COSTO_POR_OPERACION, COSTA.FECHA_CREACION, COSTA.FECHA_USO_FINAL \n"
                + "FROM REGALIA REG INNER JOIN ARTISTA_EMPRESA ARTEMP \n"
                + "ON REG.ID_ARTISTA_EMPRESA = ARTEMP.ID_ARTISTA_EMPRESA \n"
                + "INNER JOIN COSTO_ACTIVITIDAD COSTA\n"
                + "ON REG.ID_COSTO = COSTA.ID_COSTO_ACTIVIDAD\n"
                + "WHERE ID_REGALIA=?";

        INSERT_REGALIA = "INSERT INTO REGALIA (TOTAL_GANADO,NUMERO_OPERACIONES, FECHA, ID_ARTISTA_EMPRESA, ID_COSTO)\n"
                + "VALUES(?,?,?,?,?)";

        UPDATE_REGALIA = "UPDATE REGALIA \n"
                + "SET TOTAL_GANADO=?, FECHA=?, \n"
                + "ID_ARTISTA_EMPRESA=?, ID_COSTO=? \n"
                + "WHERE ID_REGALIA=?";

        SELECT_ULTIMO_ID = "SELECT REGALIA_SEQ.CURRVAL\n"
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

    private int getUltimoIdRegalia() {
        int idRegalia = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ULTIMO_ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                idRegalia = rs.getInt("CURRVAL");
                return idRegalia;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(rs);
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(RegaliaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idRegalia;
    }
}
