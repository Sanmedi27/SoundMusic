//package co.com.soundMusic.Contacto;
//
//import co.com.soundMusic.utilidades.DBUtil;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.commons.dbutils.DbUtils;
//
///**
// *
// * @author Santiago Medina Pelaez
// */
//public class ContactoDaoImpl implements IContactoDao {
//
//    //Conexion a la base de datos
//    private Connection conexion;
//    private Boolean isProduction = true;
//    private Statement stmt;
//    private ResultSet rs;
//    //Constantes con las querys a la base de datos
//    private static final String SELECT_CONTACTO_POR_ID;
//    private static final String INSERT_CONTACTO;
//    private static final String UPDATE_CONTACTO;
//    private static final String SELECT_ULTIMO_ID;
//
//    public ContactoDaoImpl(Boolean production) {
//        isProduction = production;
//    }
//
//    @Override
//    public Contacto obtenerContacto(int idContacto) {
//        getConexion();
//        try {
//            PreparedStatement ps = conexion.prepareStatement(SELECT_CONTACTO_POR_ID);
//            ps.setInt(1, idContacto);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                String[] datosContacto = {rs.getString("CELULAR"),
//                    validacion(rs.getString("TELEFONO")),
//                    validacion(rs.getString("DIRECCION")),
//                    validacion(rs.getString("BARRIO")),
//                    validacion(rs.getString("EMAIL"))};
//
//                Contacto contacto = new Contacto(idContacto, datosContacto, rs.getInt("ID_CIUDAD"));
//                contacto.obtenerCiudad();
//
//                return contacto;
//            }
//        } catch (SQLException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (conexion != null) {
//                    DbUtils.closeQuietly(rs);
//                    DbUtils.closeQuietly(conexion);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public int crearContacto(Contacto contacto) {
//        getConexion();
//        int id = -1;
//        try {
//            PreparedStatement ps = conexion.prepareStatement(INSERT_CONTACTO);
//
//            ps.setString(1, contacto.getCelular());
//            ps.setString(2, contacto.getTelefono());
//            ps.setString(3, contacto.getDireccion());
//            ps.setString(4, contacto.getBarrio());
//            ps.setString(5, contacto.getEmail());
//            ps.setInt(6, contacto.getCiudad().getIdCiudad());
//            ps.executeUpdate();
//            id = getUltimoIdContacto();
//        } catch (SQLException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (conexion != null) {
//                    DbUtils.closeQuietly(conexion);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return id;
//    }
//
//    @Override
//    public void actualizarContacto(Contacto contacto) {
//        getConexion();
//        try {
//            PreparedStatement ps = conexion.prepareStatement(UPDATE_CONTACTO);
//
//            ps.setString(1, contacto.getCelular());
//            ps.setString(2, contacto.getTelefono());
//            ps.setString(3, contacto.getDireccion());
//            ps.setString(4, contacto.getBarrio());
//            ps.setString(5, contacto.getEmail());
//            ps.setInt(6, contacto.getCiudad().getIdCiudad());
//            ps.setInt(7, contacto.getIdContacto());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (conexion != null) {
//                    DbUtils.closeQuietly(conexion);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private int getUltimoIdContacto() {
//        try {
//            stmt = conexion.createStatement();
//            rs = stmt.executeQuery(SELECT_ULTIMO_ID);
//            while (rs.next()) {
//                return rs.getInt("CURRVAL");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Excepción " + ex.getMessage());
//            Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (conexion != null) {
//                    DbUtils.closeQuietly(rs);
//                    DbUtils.closeQuietly(stmt);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ContactoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return -1;
//    }
//
//    static {
//        SELECT_CONTACTO_POR_ID = "SELECT CELULAR, TELEFONO, DIRECCION, BARRIO, EMAIL, ID_CIUDAD\n"
//                + "FROM CONTACTO\n"
//                + "WHERE ID_CONTACTO=?";
//
//        INSERT_CONTACTO = "INSERT INTO CONTACTO (CELULAR, TELEFONO, DIRECCION,BARRIO,EMAIL, ID_CIUDAD) \n"
//                + "VALUES (?,?,?,?,?,?)";
//
//        UPDATE_CONTACTO = "UPDATE CONTACTO\n"
//                + "SET  CELULAR=?, TELEFONO=?, DIRECCION=?, BARRIO=?,  EMAIL=?, ID_CIUDAD=?\n"
//                + "WHERE ID_CONTACTO=?";
//
//        SELECT_ULTIMO_ID = "SELECT CONTACTO_SEQ.CURRVAL\n"
//                + "FROM DUAL";
//    }
//
//    private String validacion(String aValidar) {
//        if (aValidar != null) {
//            return aValidar.trim();
//        } else {
//            return "";
//        }
//    }
//
//    private void getConexion() {
//        if (isProduction) {
//            conexion = DBUtil.getConexion();
//        } else {
//            conexion = DBUtil.getTestConexion();
//        }
//        stmt = null;
//        rs = null;
//    }
//}
