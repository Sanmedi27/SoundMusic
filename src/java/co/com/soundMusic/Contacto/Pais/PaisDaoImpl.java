//package co.com.soundMusic.Contacto.Pais;
//
//import co.com.soundMusic.utilidades.DBUtil;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.commons.dbutils.DbUtils;
//
//public class PaisDaoImpl implements IPaisDao {
//
//    //Conexion a la base de datos
//    private final Connection conexion;
//
//    private Statement stmt;
//    private ResultSet rs;
//    //Constantes con las querys a la base de datos
//    private static final String SELECT_PAISES;
//    private static final String SELECT_PAIS_POR_ID;
//    private static final String INSERT_PAIS;
//    private static final String UPDATE_PAIS;
//
//    public PaisDaoImpl(Boolean production) {
//        if (production) {
//            conexion = DBUtil.getConexion();
//        } else {
//            conexion = DBUtil.getTestConexion();
//        }
//        stmt = null;
//        rs = null;
//    }
//
//    @Override
//    public List<Pais> obtenerPaises() {
//        List<Pais> listaPaises = new ArrayList<>();
//        try {
//            stmt = conexion.createStatement();
//            rs = stmt.executeQuery(SELECT_PAISES);
//
//            while (rs.next()) {
//                Pais pais = new Pais();
//                pais.setIdPais(rs.getInt("ID_PAIS"));
//                pais.setNombre(rs.getString("NOMBRE"));
//                listaPaises.add(pais);
//            }
//
//        } catch (SQLException | NullPointerException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(PaisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (conexion != null) {
//                    DbUtils.closeQuietly(rs);
//                    DbUtils.closeQuietly(stmt);
//                    DbUtils.closeQuietly(conexion);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(PaisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return listaPaises;
//    }
//
//    @Override
//    public Pais obtenerPais(int idPais) {
//        Pais pais = new Pais();
//        try {
//            PreparedStatement ps = conexion.prepareStatement(SELECT_PAIS_POR_ID);
//            ps.setInt(1, idPais);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                pais.setIdPais(idPais);
//                pais.setNombre(rs.getString("NOMBRE"));
//                return pais;
//            }
//        } catch (SQLException | NullPointerException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(PaisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (conexion != null) {
//                    DbUtils.closeQuietly(rs);
//                    DbUtils.closeQuietly(conexion);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(PaisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return pais;
//    }
//
//    @Override
//    public void crearPais(Pais pais) {
//        try {
//            PreparedStatement ps = conexion.prepareStatement(INSERT_PAIS);
//            ps.setString(1, pais.getNombre());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(PaisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            DbUtils.closeQuietly(conexion, stmt, rs);
//        }
//    }
//
//    @Override
//    public void actualizarPais(Pais pais) {
//        try {
//            PreparedStatement ps = conexion.prepareStatement(UPDATE_PAIS);
//            ps.setString(1, pais.getNombre());
//            ps.setInt(2, pais.getIdPais());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(PaisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            DbUtils.closeQuietly(conexion, stmt, rs);
//        }
//    }
//
//    static {
//        SELECT_PAISES = "SELECT ID_PAIS, NOMBRE\n"
//                + "FROM PAIS\n"
//                + "ORDER BY ID_PAIS";
//
//        SELECT_PAIS_POR_ID = "SELECT ID_PAIS, NOMBRE\n"
//                + "FROM PAIS\n"
//                + "WHERE ID_PAIS=?";
//
//        INSERT_PAIS = "INSERT INTO PAIS (NOMBRE)\n"
//                + "VALUES (?)";
//
//        UPDATE_PAIS = "UPDATE PAIS \n"
//                + "SET NOMBRE=?\n"
//                + "WHERE ID_PAIS=?";
//    }
//}
