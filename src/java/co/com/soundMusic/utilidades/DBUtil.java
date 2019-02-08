package co.com.soundMusic.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class DBUtil {

    public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public static final String USER = "SOUNDMUSIC";
    public static final String PASS = "SOUNDMUSIC2018";
    public static final String USER_TEST = "DBTest";
    public static final String PASS_TEST = "DBTest";
//    private static Connection conexion = null;
    //   private static BasicDataSource conexionPool;

    /**
     * Obtener conección a base de datos.     
     * @return Connection
     */
    public static Connection getConexion() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Obtener conexion a la base de datos de prueba
     * @return Connection
     */
    public static Connection getTestConexion() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            return DriverManager.getConnection(URL, USER_TEST, PASS_TEST);

        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
   public static Connection getConexion2() {
        conexionPool = new BasicDataSource();
        String nombreUsuario = "SOUNDMUSIC";
        String password = "SOUNDMUSIC2018";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";

        conexionPool.setUsername(nombreUsuario);
        conexionPool.setPassword(password);
        conexionPool.setDriverClassName("oracle.jdbc.OracleDriver");
        conexionPool.setUrl(url);
        conexionPool.setInitialSize(5);
        try {
            conexion = conexionPool.getConnection();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexion;
    }

    public static Connection getTestConexion2() {
        conexionPool = new BasicDataSource();
        String nombreUsuario = "DBTest";
        String password = "DBTest";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";

        conexionPool.setUsername(nombreUsuario);
        conexionPool.setPassword(password);
        conexionPool.setDriverClassName("oracle.jdbc.OracleDriver");
        conexionPool.setUrl(url);
        conexionPool.setInitialSize(5);
        //Cargar driver de la base de datos oracle
        try {
            conexion = conexionPool.getConnection();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexion;
    }*/
}
